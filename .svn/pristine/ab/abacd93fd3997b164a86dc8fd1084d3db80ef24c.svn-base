package com.job5156.webapp.model.proj;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>邀请面试记录表</p>
 * 
 * Date:2015年4月29日 上午8:42:38
 * @author Andy
 * @version 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class ProjPtimeInvRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * 通行证Id
	 */
	private Integer accountId;
	
	/**
	 * 微名片的id
	 */
	private Integer resumeId;
	
	/**
	 * 兼职职位id
	 */
	private Integer partTimeId;
	
	/**
	 * 面试开始时间
	 */
	private Date interviewStartTime;
	
	/**
	 * 面试结束时间
	 */
	private Date interviewEndTime;
	
	/**
	 * 发布企业联系人姓名
	 */
	private String contactPeople;
	
	/**
	 * 发布企业联系电话
	 */
	private String contactPhone;
	
	/**
	 * 发布企业的详细地址
	 */
	private String detailedAddress;
	
	/**
	 * 兼职职位名称
	 */
	@Transient
	private String ptimeName;
	
	@Transient
	private String comName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getResumeId() {
		return resumeId;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}

	public Integer getPartTimeId() {
		return partTimeId;
	}

	public void setPartTimeId(Integer partTimeId) {
		this.partTimeId = partTimeId;
	}

	public Date getInterviewStartTime() {
		return interviewStartTime;
	}

	public void setInterviewStartTime(Date interviewStartTime) {
		this.interviewStartTime = interviewStartTime;
	}

	public Date getInterviewEndTime() {
		return interviewEndTime;
	}

	public void setInterviewEndTime(Date interviewEndTime) {
		this.interviewEndTime = interviewEndTime;
	}

	public String getContactPeople() {
		return contactPeople;
	}

	public void setContactPeople(String contactPeople) {
		this.contactPeople = contactPeople;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	public String getPtimeName() {
		return ptimeName;
	}

	public void setPtimeName(String ptimeName) {
		this.ptimeName = ptimeName;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
}
