package com.job5156.core.entity.count;

import java.io.Serializable;
import java.util.Date;

/**
 * 访问IP量实体
 * 
 * @author leo
 * 
 */
public class PvAccessIp implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 				// 记录ID
	private String userIp; 				// 访问IP
	private Integer cityCode; 			// IP所在城市代码
	private String cityAddr; 			// IP所在地址
	private Integer countNum; 			// 当天访问次数
	private Date viewFirstDate;		// 上站时间
	private Date viewLastDate; 		// 最后访问时间
	private String fromUrl; 			// 来源URL地址[来路]
	private String targetUrl; 			// 目标URL地址[入口]
	private String currentUrl; 			// 当前访问地址
	private Integer viewNum; 			// 已浏览网页数(刷新也算)
	private Integer siteId; 			// 站点ID

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityAddr() {
		return cityAddr;
	}

	public void setCityAddr(String cityAddr) {
		this.cityAddr = cityAddr;
	}

	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

	public Date getViewFirstDate() {
		return viewFirstDate;
	}

	public void setViewFirstDate(Date viewFirstDate) {
		this.viewFirstDate = viewFirstDate;
	}

	public Date getViewLastDate() {
		return viewLastDate;
	}

	public void setViewLastDate(Date viewLastDate) {
		this.viewLastDate = viewLastDate;
	}

	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getCurrentUrl() {
		return currentUrl;
	}

	public void setCurrentUrl(String currentUrl) {
		this.currentUrl = currentUrl;
	}

	public Integer getViewNum() {
		return viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
}
