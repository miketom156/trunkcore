package com.job5156.core.common.searcher.entity.singleton;

import com.google.common.collect.Lists;
import com.job5156.core.common.util.JndiUtil;
import com.job5156.searcher.ejb.PosSearcher;
import com.job5156.searcher.ejb.PosUpdater;
import com.job5156.searcher.ejb.Searcher;
import com.job5156.searcher.ejb.Updater;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * <p></p>
 * Date:2015/6/15 10:20
 *
 * @author hjs
 * @version 1.0
 */
public class SingletonPosSearcher {

    private static final Logger logger = Logger.getLogger(SingletonPosSearcher.class);
    private static volatile SingletonPosSearcher instance;

    private List<Searcher> posSearcherList;
    private List<Updater> posUpdaterList;

    public List<Searcher> getPosSearcherList() {
        return posSearcherList;
    }

    public void setPosSearcherList(List<Searcher> posSearcherList) {
        this.posSearcherList = posSearcherList;
    }

    public List<Updater> getPosUpdaterList() {
        return posUpdaterList;
    }

    public void setPosUpdaterList(List<Updater> posUpdaterList) {
        this.posUpdaterList = posUpdaterList;
    }

    private SingletonPosSearcher() {
    }

    public static SingletonPosSearcher getInstance(List<String> searchServers) {
        if(instance == null) {
            synchronized (SingletonPosSearcher.class){
                if(instance == null) {
                    instance = new SingletonPosSearcher();
                    instance.setPosSearcherList(assembleSearcher(searchServers));
                    instance.setPosUpdaterList(assembleUpdater(searchServers));
                }
            }
        }
        return instance;
    }

    /**
     * 初始化搜索器
     */
    private static List<Searcher> assembleSearcher(List<String> searchServers) {
        List<Searcher> posSearcherList = Lists.newArrayList();
        for(String searchServer : searchServers) {
            logger.warn("posSearcher initial start --> server = " + searchServer);
            Searcher searcher = JndiUtil.getJndiService(PosSearcher.JNDI_NAME, searchServer);
            if(searcher != null) {
                logger.warn("posSearcher initial finish --> server = " + searchServer);
                posSearcherList.add(searcher);
            }
        }
        return posSearcherList;
    }

    /**
     * 初始化更新器
     */
    private static List<Updater> assembleUpdater(List<String> searchServers) {
        List<Updater> posUpdaterList = Lists.newArrayList();
        for(String searchServer : searchServers) {
            logger.warn("posUpdater initial start --> server = " + searchServer);
            Updater updater = JndiUtil.getJndiService(PosUpdater.JNDI_NAME, searchServer);
            if(updater != null) {
                logger.warn("posUpdater initial finish --> server = " + searchServer);
                posUpdaterList.add(updater);
            }
        }
        return posUpdaterList;
    }

}
