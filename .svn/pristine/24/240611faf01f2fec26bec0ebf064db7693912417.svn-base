package com.job5156.core.entity.irc;

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
public class IrcOrderStatusHistory implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private long orderId;//订单号
	private Integer isCusNotified;//是否已经通知客户，在电商上买东西，订单已成功下达，以及订单已配货，订单已经出货等，这些状态的变化，用户都会收到邮件通知
	private Integer isVisibleOnFront;//是否在前台页面显示（有些状态只用给内部看，不需要显示给用户看）
	private String comment;//备注说明
	private Integer status;//订单状态
	private String operUserid;//如果是内部员工操作，记录内部员工工号
	private Date createdAt;//数据创建时间
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public Integer getIsCusNotified() {
		return isCusNotified;
	}
	public void setIsCusNotified(Integer isCusNotified) {
		this.isCusNotified = isCusNotified;
	}
	public Integer getIsVisibleOnFront() {
		return isVisibleOnFront;
	}
	public void setIsVisibleOnFront(Integer isVisibleOnFront) {
		this.isVisibleOnFront = isVisibleOnFront;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOperUserid() {
		return operUserid;
	}
	public void setOperUserid(String operUserid) {
		this.operUserid = operUserid;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
