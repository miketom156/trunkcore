package com.job5156.core.entity.log;

import java.util.Date;

/**
 * 访问职位详细记录
 * 
 * @author leo
 * 
 */
public class LogAccessPos {
	private Integer id; 			// 主键ID
	private Integer posId; 			// 职位ID
	private Integer posType;		// 职位类型[1-网络职位 2-招聘会职位]
	private Integer comId; 			// 企业ID
	private Integer userId; 		// 个人用户ID
	private String userName; 		// 个人用户姓名
	private String userIp; 			// 访问IP
	private String userAgent; 		// 客户端信息
	private String channel;			// 访问入口[web,api_weixin,api_mobile]
	private Date createDate; 		// 创建日期

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPosId() {
		return posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getPosType() {
		return posType;
	}

	public void setPosType(Integer posType) {
		this.posType = posType;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
