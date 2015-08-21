package com.job5156.core.eao.com;

import com.google.gson.Gson;
import com.job5156.core.common.searcher.entity.singleton.SingletonPosSearcher;
import com.job5156.searcher.entity.SearchResult;
import com.job5156.searcher.ejb.Searcher;
import com.job5156.searcher.parameter.PosSearchParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.rmi.RemoteException;
import java.util.List;

/**
 * <p></p>
 * Date:2015/6/29 17:23
 *
 * @author pzm
 * @version 1.0
 */
@Repository
public class SearchPosEao {
    private Gson gson = new Gson();
    //职位轮询指针
    private volatile Integer pollingPointer = 1;
    /**
     * EJB 搜索服务器的配置，自动从properties文件中读取，以逗号分隔
     */
    @Value("#{'${search.servers}'.split(',')}")
    private List<String> searchServers;

    /**
     * 搜索职位
     * 增加3次容错机制
     * 当出现搜索服务器远程调用异常的时候，清空全局的搜索器列表，重新初始化，以此来监测服务器的心跳指数
     * 当3次都出错的时候，不再重试，直接返回null
     * @param param 职位搜索条件组成的 param 对象
     * @return 搜索结果 SearchResult，包含（总数、每个结果的ID、分类统计等信息）
     */
    public SearchResult searchPos(PosSearchParam param) {
        SearchResult searchResult = null;

        Searcher posSearcher = this.getPollingPosSearcher();
        if(posSearcher != null) {
            String searchResultStr = "";
            try {
                searchResultStr = posSearcher.search(gson.toJson(param));
            } catch (RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
            searchResult = gson.fromJson(searchResultStr, SearchResult.class);
        }
        return searchResult;
    }

    /**
     * 以轮询的方式获取职位搜索器
     * @return
     */
    private Searcher getPollingPosSearcher(){
        SingletonPosSearcher singletonPosSearcher = SingletonPosSearcher.getInstance(searchServers);
        List<Searcher> posSearcherList = singletonPosSearcher.getPosSearcherList();
        if(CollectionUtils.isNotEmpty(posSearcherList) && posSearcherList.size() > 0) {
            synchronized(this){
                if(this.pollingPointer > posSearcherList.size()){
                    this.pollingPointer = 1;
                }
                return posSearcherList.get(this.pollingPointer++ - 1);
            }
        }
        return null;
    }
}
