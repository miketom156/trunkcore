package com.job5156.searcher.entity;

import com.job5156.searcher.ejb.Searcher;

/**
 * <p>
 * EJB搜索服务接口包装类
 * </p>
 * 
 * <pre>
 * 1、针对系统需要开启后台线程检测EJB服务的正常状态，对搜索的远程服务接口进行包装
 * 2、在项目启动之后会将加载的EJB服务信息保存到本类，提供后续使用支持
 * 3、当前类保存EJB服务的IP和服务名称及服务接口调用实例
 * </pre>
 * 
 * Date:2015-6-26 下午2:01:51
 * 
 * @author leo
 * @version 1.0
 */
public class EjbSearcher extends EjbServ {

	/**
	 * EJB搜索服务调用接口
	 */
	private Searcher searcher;

	public Searcher getSearcher() {
		return searcher;
	}

	public void setSearcher(Searcher searcher) {
		this.searcher = searcher;
	}

}
