package com.job5156.core.common.searcher.entity.singleton;

import com.google.common.collect.Lists;
import com.job5156.core.common.util.JndiUtil;
import com.job5156.searcher.ejb.ResumeSearcher;
import com.job5156.searcher.ejb.ResumeUpdater;
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
public class SingletonResumeSearcher {
    private static final Logger logger = Logger.getLogger(SingletonResumeSearcher.class);
    private static volatile SingletonResumeSearcher instance;

    private List<Searcher> resumeSearcherList;
    private List<Updater> resumeUpdaterList;

    public List<Searcher> getResumeSearcherList() {
        return resumeSearcherList;
    }

    public void setResumeSearcherList(List<Searcher> resumeSearcherList) {
        this.resumeSearcherList = resumeSearcherList;
    }

    public List<Updater> getResumeUpdaterList() {
        return resumeUpdaterList;
    }

    public void setResumeUpdaterList(List<Updater> resumeUpdaterList) {
        this.resumeUpdaterList = resumeUpdaterList;
    }

    private SingletonResumeSearcher() {
    }

    public static SingletonResumeSearcher getInstance(List<String> searchServers) {
        if(instance == null) {
            synchronized (SingletonPosSearcher.class){
                if(instance == null) {
                    instance = new SingletonResumeSearcher();
                    instance.setResumeSearcherList(assembleSearcher(searchServers));
                    instance.setResumeUpdaterList(assembleUpdater(searchServers));
                }
            }
        }
        return instance;
    }

    /**
     * 初始化搜索器
     */
    private static List<Searcher> assembleSearcher(List<String> searchServers) {
        List<Searcher> resumeSearcherList = Lists.newArrayList();
        for(String searchServer : searchServers) {
            logger.warn("resumeSearcher initial start --> server = " + searchServer);
            Searcher searcher = JndiUtil.getJndiService(ResumeSearcher.JNDI_NAME, searchServer);
            if(searcher != null) {
                logger.warn("resumeSearcher initial finish --> server = " + searchServer);
                resumeSearcherList.add(searcher);
            }
        }
        return resumeSearcherList;
    }

    /**
     * 初始化更新器
     */
    private static List<Updater> assembleUpdater(List<String> searchServers) {
        List<Updater> resumeUpdaterList = Lists.newArrayList();
        for(String searchServer : searchServers) {
            logger.warn("resumeUpdater initial start --> server = " + searchServer);
            Updater updater = JndiUtil.getJndiService(ResumeUpdater.JNDI_NAME, searchServer);
            if(updater != null) {
                logger.warn("resumeUpdater initial finish --> server = " + searchServer);
                resumeUpdaterList.add(updater);
            }
        }
        return resumeUpdaterList;
    }

}
