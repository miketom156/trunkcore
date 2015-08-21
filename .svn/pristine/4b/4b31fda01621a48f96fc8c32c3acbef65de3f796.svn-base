package com.job5156.webapp.model.mail;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 邮件队列
 * 
 * @author leo
 * 
 */
public class EmailQueue implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; // 记录ID
	private Integer sendFlag; // 发送标记[0-待发送 1-发送成功 2-发送失败]
	private String title; // 邮件标题
	private String fromAddr; // 来源地址
	private String fromName; // 来源名称
	private String toAddr; // 目标地址(多个地址用“;”分隔)
	private Date sendDate; // 发送时间
	private Date resendDate; // 重新发送时间
	private Integer taskId; // 发送邮件任务ID，详细请查看Constants配置
	private String content; // 邮件内容
	private Integer emailType; // 邮件类型[0-简体 1-繁体, 默认为0]
	private Date createDate; // 创建日期
	@Transient
	private String taskName; // 邮件任务类型名称

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(Integer sendFlag) {
		this.sendFlag = sendFlag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFromAddr() {
		return fromAddr;
	}

	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}

	public String getToAddr() {
		return toAddr;
	}

	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getResendDate() {
		return resendDate;
	}

	public void setResendDate(Date resendDate) {
		this.resendDate = resendDate;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getEmailType() {
		return emailType;
	}

	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

}
