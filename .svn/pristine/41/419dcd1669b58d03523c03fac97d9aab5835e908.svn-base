package com.job5156.webapp.model.com;

import com.job5156.webapp.model.sys.system.SysUser;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComForgetPasswordReply implements Serializable {
	private static final long serialVersionUID = 1L ;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	private Integer userId;
	private Integer forgetId;
	private String replyContent;
	private Date createDate;
	private Integer replyFlag;//0=处理者回复 1=申请者回复
	
	@Transient
	private SysUser sysUser;//关联用户
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forgetId", insertable = false, updatable = false)
	private ComForgetPassword comForgetPassword;//关联的申请
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getForgetId() {
		return forgetId;
	}
	public void setForgetId(Integer forgetId) {
		this.forgetId = forgetId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getReplyFlag() {
		return replyFlag;
	}
	public void setReplyFlag(Integer replyFlag) {
		this.replyFlag = replyFlag;
	}
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public ComForgetPassword getComForgetPassword() {
		return comForgetPassword;
	}
	public void setComForgetPassword(ComForgetPassword comForgetPassword) {
		this.comForgetPassword = comForgetPassword;
	}
}
