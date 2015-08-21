package com.job5156.webapp.model.com;

import com.google.common.collect.Lists;
import com.job5156.webapp.model.sys.system.SysUser;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComForgetPassword implements Serializable {

	private static final long serialVersionUID = 1L ;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	private Integer comId;//企业id
	private String comName;//企业名称
	private String email;//邮箱号
	private String contactPerson;//联系人
	private String telephone;
	private String mobile;//企业提交的手机号
	private String remark;//备注内容
	private Date createDate;//创建时间
	private Date updateDate;//更新时间
	private Date replyDate;//回复时间
	private String replyPerson;//回复人
	private String replyMobile;//回复手机
	private String replyContent;//回复内容
	private Integer replyFlag;//回复方式
	private Integer replyPersonId;//回复人id
	private String passwordInfo;//密码信息
	private Integer flag;//提示状态
	private Long expiredTime ;//   过期时间
	private String resetToken ;//   验证邮件链接标示
	private Integer hitNumber;// 点击次数
	private Boolean useful ;// 是否有效
	private Integer retrieveType;//密码取回方式1:邮件前台自助取回 2:通过后台人工取回（兼容旧系统0也代表后台人工取回）
	private String userName;//企业注册用户名称
	
	@Transient
	private String replyFlagStr;
	
	@Transient
	private SysUser sysUser;//关联用户
	
	@OneToMany(mappedBy="comForgetPassword", cascade = { CascadeType.REMOVE }, fetch=FetchType.LAZY)
	private List<ComForgetPasswordReply> forgetReplyList = Lists.newArrayList();
	
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
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public String getReplyPerson() {
		return replyPerson;
	}
	public void setReplyPerson(String replyPerson) {
		this.replyPerson = replyPerson;
	}
	public String getReplyMobile() {
		return replyMobile;
	}
	public void setReplyMobile(String replyMobile) {
		this.replyMobile = replyMobile;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Integer getReplyFlag() {
		return replyFlag;
	}
	public void setReplyFlag(Integer replyFlag) {
		this.replyFlag = replyFlag;
	}
	public Integer getReplyPersonId() {
		return replyPersonId;
	}
	public void setReplyPersonId(Integer replyPersonId) {
		this.replyPersonId = replyPersonId;
	}
	public String getPasswordInfo() {
		return passwordInfo;
	}
	public void setPasswordInfo(String passwordInfo) {
		this.passwordInfo = passwordInfo;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getReplyFlagStr() {
		return replyFlagStr;
	}
	public void setReplyFlagStr(String replyFlagStr) {
		this.replyFlagStr = replyFlagStr;
	}
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public List<ComForgetPasswordReply> getForgetReplyList() {
		return forgetReplyList;
	}
	public void setForgetReplyList(List<ComForgetPasswordReply> forgetReplyList) {
		this.forgetReplyList = forgetReplyList;
	}
	public Long getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Long expiredTime) {
		this.expiredTime = expiredTime;
	}
	public String getResetToken() {
		return resetToken;
	}
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	public Integer getHitNumber() {
		return hitNumber;
	}
	public void setHitNumber(Integer hitNumber) {
		this.hitNumber = hitNumber;
	}
	public Boolean getUseful() {
		return useful;
	}
	public void setUseful(Boolean useful) {
		this.useful = useful;
	}
	public Integer getRetrieveType() {
		return retrieveType;
	}
	public void setRetrieveType(Integer retrieveType) {
		this.retrieveType = retrieveType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
