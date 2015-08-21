package com.job5156.core.entity;

import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 个人简历信息实体
 * </p>
 * 
 * Date:2015-4-16 下午2:25:28
 * 
 * @author leo
 * @version 1.0
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class PerResumeTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 简历信息ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 账号类型
	 */
	private Integer accountType;
	/**
	 * 个人用户账号ID
	 */
	private Integer accountId;
	/**
	 * 简历名称
	 */
	private String resumeName;
	/**
	 * 简历类型:0：普通简历 1: 为长沙类型的简历 4: 名片,参见：CommonEnum.ResumeType
	 */
	private Integer resumeType;
	/**
	 * 简历ID 中文简历 和 英文简历相互对应
	 */
	private Integer resumeId;
	/**
	 * 简历模板，默认为default
	 */
	private String resumeStyle;
	/**
	 * 语言类型:0为中文，1为英文
	 */
	private Integer languageType;
	/**
	 * 用户最后修改人
	 */
	private String userLastEditBy;
	/**
	 * 用户的最后修改时间
	 */
	private Date userLastEditDate;
	/**
	 * 后台管理员最后修改人
	 */
	private String adminLastEditBy;
	/**
	 * 后台管理员最后修改时间
	 */
	private Date adminLastEditDate;
	/**
	 * 简历刷新时间
	 */
	private Date freDate;
	/**
	 * 简历类型：0-普通,1-精英(猎头) 2-模具人才. 101:中高级人才（进查询库） 105:中高级人才(保密级不进查询库)
	 */
	private Integer resumeGrade;
	/**
	 * 审核时间
	 */
	private Date passDate;
	/**
	 * 简历来源 600=来自于手机页面注册
	 */
	private Integer comeFrom;
	/**
	 * 是否有照片：0为无，1为有
	 */
	private Integer havePhoto;
	/**
	 * -10:自杀式简历，不让登录，-9：封锁,-3：审核不合格，-1：未开通(待审核），0:未完善，1：暂时开通 10：已开通
	 * 参见:CommonEnum.ResumeStatus
	 */
	private Integer pass;
	/**
	 * 简历保密　0:完全公开　1:完全保密　2:公开但联系方式保密 //提升到帐号级(publicSetting)
	 */
	private Integer flag;
	/**
	 * 是否显示英文简历(0:默认不显示,1:显示)
	 */
	private Integer displayEnResume;
	/**
	 * 专业技能
	 */
	private String skillInfo;

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

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getResumeName() {
		return resumeName;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	public Integer getResumeType() {
		return resumeType;
	}

	public void setResumeType(Integer resumeType) {
		this.resumeType = resumeType;
	}

	public Integer getResumeId() {
		return resumeId;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}

	public String getResumeStyle() {
		return resumeStyle;
	}

	public void setResumeStyle(String resumeStyle) {
		this.resumeStyle = resumeStyle;
	}

	public Integer getLanguageType() {
		return languageType;
	}

	public void setLanguageType(Integer languageType) {
		this.languageType = languageType;
	}

	public String getUserLastEditBy() {
		return userLastEditBy;
	}

	public void setUserLastEditBy(String userLastEditBy) {
		this.userLastEditBy = userLastEditBy;
	}

	public Date getUserLastEditDate() {
		return userLastEditDate;
	}

	public void setUserLastEditDate(Date userLastEditDate) {
		this.userLastEditDate = userLastEditDate;
	}

	public String getAdminLastEditBy() {
		return adminLastEditBy;
	}

	public void setAdminLastEditBy(String adminLastEditBy) {
		this.adminLastEditBy = adminLastEditBy;
	}

	public Date getAdminLastEditDate() {
		return adminLastEditDate;
	}

	public void setAdminLastEditDate(Date adminLastEditDate) {
		this.adminLastEditDate = adminLastEditDate;
	}

	public Date getFreDate() {
		return freDate;
	}

	public void setFreDate(Date freDate) {
		this.freDate = freDate;
	}

	public Integer getResumeGrade() {
		return resumeGrade;
	}

	public void setResumeGrade(Integer resumeGrade) {
		this.resumeGrade = resumeGrade;
	}

	public Date getPassDate() {
		return passDate;
	}

	public void setPassDate(Date passDate) {
		this.passDate = passDate;
	}

	public Integer getComeFrom() {
		return comeFrom;
	}

	public void setComeFrom(Integer comeFrom) {
		this.comeFrom = comeFrom;
	}

	public Integer getHavePhoto() {
		return havePhoto;
	}

	public void setHavePhoto(Integer havePhoto) {
		this.havePhoto = havePhoto;
	}

	public Integer getPass() {
		return pass;
	}

	public void setPass(Integer pass) {
		this.pass = pass;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getDisplayEnResume() {
		return displayEnResume;
	}

	public void setDisplayEnResume(Integer displayEnResume) {
		this.displayEnResume = displayEnResume;
	}

	public String getSkillInfo() {
		return skillInfo;
	}

	public void setSkillInfo(String skillInfo) {
		this.skillInfo = skillInfo;
	}
}
