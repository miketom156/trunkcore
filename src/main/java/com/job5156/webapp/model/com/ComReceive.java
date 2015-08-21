package com.job5156.webapp.model.com;

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
 * 企业收到的简历实体类
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComReceive implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer comId;
    private Integer perUserId;//个人用户id
    private Integer resumeId;//简历id
    private Integer posId;//职位ID
    private String userName;//用户名称
    private String posName;//职位名称
    private Integer gender;//性别
    private Integer age;//年龄
    private Integer degree;//学历
    private String schoolName;//学校名称
    private Integer location;//现所在地区
    private String speciality;//专业
    private String jobLocation;//希望工作地区
    private String email;//邮箱
    private String mixInfo;//混合信息
    private Integer applyNumer;//应聘次数
    private String mark;//不符合,符合,待定,已面试,待邀请,已邀请,自定义标签
    private String remark;//标注内容
    private Integer sysRecommend;//1:后台推荐的简历
    private Integer readFlag;//阅读状态1已阅读
    private Integer delStatus;//-1已删除
    private Integer receiveType;//类型（0=个人主动应聘，1=后台推荐）
    private Integer sendFlag;//发送通知信标志[ 1.视频面试通知 2.到场馆面试通知 3.到办公室面试通知 4.侍考虑 5.婉拒 6.自动回复]
    private Integer buyFlag;//是否购买了此份简历（0:否 1：是）
    private Date createDate;//创建时间[应聘时间]
    private Date updateDate;//更新时间
    private Double  matchRat = 0.0;//简历匹配度 -百分比显示
    private Integer  filterFlag;//简历是否被过滤（0:否 1：是）
    private Integer jobyearType;//工作年限类型
    private String keywords;//简历关键字
    private transient Integer resumeType;  //简历类型:0：普通简历 1: 为长沙类型的简历 2: 名片, 4:微名片 详细参见：CommonEnum.ResumeType
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getComId() {
        return comId;
    }
    public void setComId(Integer comId) {
        this.comId = comId;
    }
    public Integer getPerUserId() {
        return perUserId;
    }
    public void setPerUserId(Integer perUserId) {
        this.perUserId = perUserId;
    }
    public Integer getResumeId() {
        return resumeId;
    }
    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }
    public Integer getPosId() {
        return posId;
    }
    public void setPosId(Integer posId) {
        this.posId = posId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPosName() {
        return posName;
    }
    public void setPosName(String posName) {
        this.posName = posName;
    }
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getDegree() {
        return degree;
    }
    public void setDegree(Integer degree) {
        this.degree = degree;
    }
    public String getSchoolName() {
        return schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public Integer getLocation() {
        return location;
    }
    public void setLocation(Integer location) {
        this.location = location;
    }
    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public String getJobLocation() {
        return jobLocation;
    }
    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }
    public Integer getApplyNumer() {
        return applyNumer;
    }
    public void setApplyNumer(Integer applyNumer) {
        this.applyNumer = applyNumer;
    }
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getSysRecommend() {
        return sysRecommend;
    }
    public void setSysRecommend(Integer sysRecommend) {
        this.sysRecommend = sysRecommend;
    }
    public Integer getReadFlag() {
        return readFlag;
    }
    public void setReadFlag(Integer readFlag) {
        this.readFlag = readFlag;
    }
    public Integer getDelStatus() {
        return delStatus;
    }
    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }
    public Integer getReceiveType() {
        return receiveType;
    }
    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }
    public Integer getSendFlag() {
        return sendFlag;
    }
    public void setSendFlag(Integer sendFlag) {
        this.sendFlag = sendFlag;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public String getMixInfo() {
        return mixInfo;
    }
    public void setMixInfo(String mixInfo) {
        this.mixInfo = mixInfo;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
	public Integer getBuyFlag() {
        return buyFlag;
    }
    public void setBuyFlag(Integer buyFlag) {
        this.buyFlag = buyFlag;
    }

    public Double getMatchRat() {
        return matchRat;
    }

    public void setMatchRat(Double matchRat) {
        this.matchRat = matchRat;
    }

    public Integer getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(Integer filterFlag) {
        this.filterFlag = filterFlag;
    }
    
	public Integer getResumeType() {
		return resumeType;
	}
	
	public void setResumeType(Integer resumeType) {
		this.resumeType = resumeType;
	}
    
}
