package com.job5156.webapp.model.count;

import java.io.Serializable;
import java.util.Date;

/**
 * 访问量实体
 * 
 * @author leo
 * 
 */
public class PvAccess implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;				// 记录ID
	private String userIp; 			// 访问IP
	private Integer siteId; 		// 站点
	private String fromDomain; 		// 来源域名
	private String fromAddr; 		// 来源相对地址
	private String targetDomain; 	// 目标域名
	private String targetAddr; 		// 目标相对地址
	private String clientDetail; 	// 客户端信息(保存json格式)
	private String assignedIdentity;// 系统分配标识符
	private Date acceptDate; 		// 访问时间
	private String spreadParam; 	// 推广参数
	private String semSpreadParam; 	// sem推广参数
	private Integer originFlag; 	// 标记是否来路[0-否 1-是]
    private String fromSourceUrl; //外站来源地址

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

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getFromDomain() {
		return fromDomain;
	}

	public void setFromDomain(String fromDomain) {
		this.fromDomain = fromDomain;
	}

	public String getFromAddr() {
		return fromAddr;
	}

	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}

	public String getTargetDomain() {
		return targetDomain;
	}

	public void setTargetDomain(String targetDomain) {
		this.targetDomain = targetDomain;
	}

	public String getTargetAddr() {
		return targetAddr;
	}

	public void setTargetAddr(String targetAddr) {
		this.targetAddr = targetAddr;
	}

	public String getClientDetail() {
		return clientDetail;
	}

	public void setClientDetail(String clientDetail) {
		this.clientDetail = clientDetail;
	}

	public String getAssignedIdentity() {
		return assignedIdentity;
	}

	public void setAssignedIdentity(String assignedIdentity) {
		this.assignedIdentity = assignedIdentity;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getSpreadParam() {
		return spreadParam;
	}

	public void setSpreadParam(String spreadParam) {
		this.spreadParam = spreadParam;
	}

	public Integer getOriginFlag() {
		return originFlag;
	}

	public void setOriginFlag(Integer originFlag) {
		this.originFlag = originFlag;
	}

    public String getFromSourceUrl() {
        return fromSourceUrl;
    }

    public void setFromSourceUrl(String fromSourceUrl) {
        this.fromSourceUrl = fromSourceUrl;
    }

    public String getSemSpreadParam() {
        return semSpreadParam;
    }

    public void setSemSpreadParam(String semSpreadParam) {
        this.semSpreadParam = semSpreadParam;
    }
}
