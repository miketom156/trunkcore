package com.job5156.core.entity.com;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 企业简历管理--黑名单实体类
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComBlacklist implements Serializable {

	private static final long serialVersionUID = 1L ;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	private Integer comId ;
	private Integer perUserId ;// 个人用户id
	private Integer resumeId ;// 简历id
	private Integer posId ;// 职位ID
	private String userName ;// 用户名称
	private String posName ;// 职位名称
	private Integer gender ;// 性别
	private Integer age ;// 年龄
	private Integer degree ;// 学历
	private String schoolName ;// 学校名称
	private Integer location ;// 现所在地区
	private String speciality ;// 专业
	private String jobLocation ;// 希望工作地区
	private String email ;// 邮箱
	private String mixInfo ;// 混合信息
	private Integer applyNumer ;// 应聘次数
	private String mark ;// 不符合,符合,待定,已面试,待邀请,已邀请,自定义标签
	private String remark ;// 标注内容
	private Integer sysRecommend ;// 1:后台推荐的简历
	private Integer readFlag ;// 阅读状态1已阅读
	private Integer delStatus ;// -1已删除
	private Integer receiveType ;// 类型（1为视频,其它为网络应聘）
	private Integer sendFlag ;//发送通知信标志[ 1.视频面试通知 2.到场馆面试通知 3.到办公室面试通知 4.侍考虑 5.婉拒 6.自动回复]
	private String sourceType ;// 删除来源类型 receive收到的简历 store简历人才库 interview已邀请的面试
	private Integer sourceId ;// 删除来源id
	private Integer buyFlag;//是否购买了此份简历（0:否 1：是）
	private Date createDate ;// 创建时间[应聘时间]
	private Date updateDate ;// 更新时间
	private Integer jobyearType;//工作年限类型
	private String keywords;  //简历关键字
	public Integer getId() {
		return id ;
	}

	public void setId(Integer id) {
		this.id = id ;
	}

	public Integer getComId() {
		return comId ;
	}

	public void setComId(Integer comId) {
		this.comId = comId ;
	}

	public Integer getPerUserId() {
		return perUserId ;
	}

	public void setPerUserId(Integer perUserId) {
		this.perUserId = perUserId ;
	}

	public Integer getResumeId() {
		return resumeId ;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId ;
	}

	public Integer getPosId() {
		return posId ;
	}

	public void setPosId(Integer posId) {
		this.posId = posId ;
	}

	public String getUserName() {
		return userName ;
	}

	public void setUserName(String userName) {
		this.userName = userName ;
	}

	public String getPosName() {
		return posName ;
	}

	public void setPosName(String posName) {
		this.posName = posName ;
	}

	public Integer getGender() {
		return gender ;
	}

	public void setGender(Integer gender) {
		this.gender = gender ;
	}

	public Integer getAge() {
		return age ;
	}

	public void setAge(Integer age) {
		this.age = age ;
	}

	public Integer getDegree() {
		return degree ;
	}

	public void setDegree(Integer degree) {
		this.degree = degree ;
	}

	public String getSchoolName() {
		return schoolName ;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName ;
	}

	public Integer getLocation() {
		return location ;
	}

	public void setLocation(Integer location) {
		this.location = location ;
	}

	public String getSpeciality() {
		return speciality ;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality ;
	}

	public String getJobLocation() {
		return jobLocation ;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation ;
	}

	public Integer getApplyNumer() {
		return applyNumer ;
	}

	public void setApplyNumer(Integer applyNumer) {
		this.applyNumer = applyNumer ;
	}

	public String getMark() {
		return mark ;
	}

	public void setMark(String mark) {
		this.mark = mark ;
	}

	public String getRemark() {
		return remark ;
	}

	public void setRemark(String remark) {
		this.remark = remark ;
	}

	public Integer getSysRecommend() {
		return sysRecommend ;
	}

	public void setSysRecommend(Integer sysRecommend) {
		this.sysRecommend = sysRecommend ;
	}

	public Integer getReadFlag() {
		return readFlag ;
	}

	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag ;
	}

	public Integer getDelStatus() {
		return delStatus ;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus ;
	}

	public Integer getReceiveType() {
		return receiveType ;
	}

	public void setReceiveType(Integer receiveType) {
		this.receiveType = receiveType ;
	}

	public Integer getSendFlag() {
		return sendFlag ;
	}

	public void setSendFlag(Integer sendFlag) {
		this.sendFlag = sendFlag ;
	}

	public Date getCreateDate() {
		return createDate ;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate ;
	}

	public Date getUpdateDate() {
		return updateDate ;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate ;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMixInfo() {
		return mixInfo ;
	}

	public void setMixInfo(String mixInfo) {
		this.mixInfo = mixInfo ;
	}

	public String getSourceType() {
		return sourceType ;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType ;
	}

	public Integer getSourceId() {
		return sourceId ;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId ;
	}

    public Integer getBuyFlag() {
        return buyFlag;
    }

    public void setBuyFlag(Integer buyFlag) {
        this.buyFlag = buyFlag;
    }

	public Integer getJobyearType() {
		return jobyearType;
	}

	public void setJobyearType(Integer jobyearType) {
		this.jobyearType = jobyearType;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	

}
