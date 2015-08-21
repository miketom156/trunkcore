package com.job5156.core.entity.per.job;

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
public class PerInviteLog implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer comId;
	private Integer perUserId;//个人账号ID
	private Integer posId;//职位ID
	private Date creTime;// 记录生成时间
	private Date editTime;// 个人修改【备注或红旗标记】时间
    private Integer delStatus;// 删除状态 {-1 已删除}
	private Integer readStatus;  // 个人查看状态 未查看0 已查看1
	private Integer mark;// 旗子标记
	private String remark;// 备注内容
	private String userName;//用户名称
	private String inviteDetail;//邀请详情内容
	private String interviewAddress;//面试地址
	private Integer roomNum;//视频面试房间号
	private String bookPeriod; //预约的面试时间段
	private String contactPerson; //联系人
    private String contactPhone; //联系电话

    private String mixInfo; //综合信息

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
	public Integer getPosId() {
		return posId;
	}
	public void setPosId(Integer posId) {
		this.posId = posId;
	}
	public Date getCreTime() {
		return creTime;
	}
	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	public Integer getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getInviteDetail() {
		return inviteDetail;
	}
	public void setInviteDetail(String inviteDetail) {
		this.inviteDetail = inviteDetail;
	}
	public String getInterviewAddress() {
		return interviewAddress;
	}
	public void setInterviewAddress(String interviewAddress) {
		this.interviewAddress = interviewAddress;
	}
	public Integer getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}
    public String getBookPeriod() {
        return bookPeriod;
    }

    public void setBookPeriod(String bookPeriod) {
        this.bookPeriod = bookPeriod;
    }
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

    public String getMixInfo() {
        return mixInfo;
    }

    public void setMixInfo(String mixInfo) {
        this.mixInfo = mixInfo;
    }
}
