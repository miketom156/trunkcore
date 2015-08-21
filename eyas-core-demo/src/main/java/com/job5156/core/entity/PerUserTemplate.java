package com.job5156.core.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 个人用户信息实体对象
 * </p>
 * 
 * Date:2015-4-16 下午2:20:22
 * 
 * @author leo
 * @version 1.0
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class PerUserTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 账号id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 账号类型
	 */
	private Integer accountType;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 登录次数
	 */
	private Integer loginCount;
	/**
	 * 登录时间
	 */
	private Date loginDate;
	/**
	 * 公开状态：0-完全公开，1-完全保密，2-对智通公开
	 */
	private Integer publicSettings;
	/**
	 * 过滤的企业ID
	 */
	private String filterComId;
	/**
	 * 是否为个人会员 默认为0 ，1为会员
	 */
	private Integer vipMember;
	/**
	 * 刷新提醒：0表示超过15天没有更新就提示，1表示永不提示。
	 */
	private Integer refreshRemind;
	/**
	 * 存放用户登录时的IP地址
	 */
	private String ip;
	/**
	 * 猎头推荐人
	 */
	private String recommendName;
	/**
	 * 猎头推荐人联系方式
	 */
	private String recommendPhone;
	/**
	 * 个人用户简历名称
	 */
	private String userName;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 身高
	 */
	private Integer stature;
	/**
	 * 体重
	 */
	private Integer weight;
	/**
	 * 手机激活：0：未激活；1：已激活；
	 */
	private Integer mobileActivation;
	/**
	 * 政治面貌
	 */
	private Integer political;
	/**
	 * 目前薪水
	 */
	private Integer nowSalary;
	/**
	 * 默认简历ID
	 */
	private Integer resId;
	/**
	 * 账户状态-1=已删除，0=启用，1=禁用
	 */
	private Integer status;
	/**
	 * 手机归属地信息
	 */
	private String mobilePlace;
	/**
	 * 最高学历
	 */
	private Integer degree;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Integer getPublicSettings() {
		return publicSettings;
	}

	public void setPublicSettings(Integer publicSettings) {
		this.publicSettings = publicSettings;
	}

	public String getFilterComId() {
		return filterComId;
	}

	public void setFilterComId(String filterComId) {
		this.filterComId = filterComId;
	}

	public Integer getVipMember() {
		return vipMember;
	}

	public void setVipMember(Integer vipMember) {
		this.vipMember = vipMember;
	}

	public Integer getRefreshRemind() {
		return refreshRemind;
	}

	public void setRefreshRemind(Integer refreshRemind) {
		this.refreshRemind = refreshRemind;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRecommendName() {
		return recommendName;
	}

	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}

	public String getRecommendPhone() {
		return recommendPhone;
	}

	public void setRecommendPhone(String recommendPhone) {
		this.recommendPhone = recommendPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStature() {
		return stature;
	}

	public void setStature(Integer stature) {
		this.stature = stature;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getMobileActivation() {
		return mobileActivation;
	}

	public void setMobileActivation(Integer mobileActivation) {
		this.mobileActivation = mobileActivation;
	}

	public Integer getPolitical() {
		return political;
	}

	public void setPolitical(Integer political) {
		this.political = political;
	}

	public Integer getNowSalary() {
		return nowSalary;
	}

	public void setNowSalary(Integer nowSalary) {
		this.nowSalary = nowSalary;
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMobilePlace() {
		return mobilePlace;
	}

	public void setMobilePlace(String mobilePlace) {
		this.mobilePlace = mobilePlace;
	}

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}
}
