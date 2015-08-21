package com.job5156.webapp.model.per;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class PerLoginHistory implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    private Integer perUserId; //登录的个人账号ID
    private String perUserAccount; //登录的个人账号
    private String ip; //登录的IP
    private String area; //登录IP解释出来的地址
    private Date creTime; //登录时间
    private Integer sysUserId; //后台登录的用户ID
    private String sysUserAccount; //后台登录的账号
    private String sysUserName; //后台登录的用户姓名
    private String loginType; //登录类型 【web, sys, crm】
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPerUserId() {
		return perUserId;
	}
	public void setPerUserId(Integer perUserId) {
		this.perUserId = perUserId;
	}
	public String getPerUserAccount() {
		return perUserAccount;
	}
	public void setPerUserAccount(String perUserAccount) {
		this.perUserAccount = perUserAccount;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getCreTime() {
		return creTime;
	}
	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}
	public Integer getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}
	public String getSysUserAccount() {
		return sysUserAccount;
	}
	public void setSysUserAccount(String sysUserAccount) {
		this.sysUserAccount = sysUserAccount;
	}
	public String getSysUserName() {
		return sysUserName;
	}
	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
    
}
