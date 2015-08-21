package com.job5156.core.entity.sys.irc;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 后台用户邀请参会人才面试
 * </p>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysIrcInvite implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 				// 主键ID
	@NotNull
	private Integer prodId; 			// 招聘会ID
	@NotNull
	private Integer comId; 				// 公司ID
	@NotNull
	private Integer posId; 				// 招聘会职位ID
	private String perIds; 				// 邀请面试用户ID串，用“,”分隔
	private Integer perCount; 			// 邀请面试用户数量
	private Integer opUserId; 			// 后台操作人ID
	private String opUserName; 			// 后台操作人姓名
	private Integer opDeptId; 			// 后台操作人部门ID
	private String jfName; 				// 招聘会名称
	private String comName; 			// 公司名称
	@NotBlank
	private String posName; 			// 招聘会职位名称
	private Date createDate; 			// 邀请时间

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public Integer getPosId() {
		return posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public String getPerIds() {
		return perIds;
	}

	public void setPerIds(String perIds) {
		this.perIds = perIds;
	}

	public Integer getPerCount() {
		return perCount;
	}

	public void setPerCount(Integer perCount) {
		this.perCount = perCount;
	}

	public Integer getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(Integer opUserId) {
		this.opUserId = opUserId;
	}

	public String getOpUserName() {
		return opUserName;
	}

	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}

	public Integer getOpDeptId() {
		return opDeptId;
	}

	public void setOpDeptId(Integer opDeptId) {
		this.opDeptId = opDeptId;
	}

	public String getJfName() {
		return jfName;
	}

	public void setJfName(String jfName) {
		this.jfName = jfName;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
