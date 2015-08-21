package com.job5156.webapp.model.sys.subsite;

import com.job5156.webapp.model.com.ComInfo;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 事业单位招聘专区实体
 * 
 * @author 夏安定
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysHomeEnterprise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer comId; // 企业id
	private String comName; // 企业名称
	private Date beginDate; // 显示开始日期
	private Date endDate; // 显示结束日期
	private Integer newOrHot; // 新添加或是热门，0-都不是，1-为新的，2-为热门
	private Integer websiteId; // 分站点id
	private Integer positionType; // 职位类型，1-热门，0-最新，2-行业专区
	private Integer posType; // 职位类别
	private Integer orderBy; // 排序号
	private String positionJson; // 职位信息json
	private Integer updateUserId; // 操作人id
	private String updateUserName; // 操作人姓名
	private Date updateDate; // 最后修改日期

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "websiteId", insertable = false, updatable = false)
	private SysWebSite sysWebSite; // 所属分站点

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comId", insertable = false, updatable = false)
	private ComInfo comInfo; // 所属企业

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

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getNewOrHot() {
		return newOrHot;
	}

	public void setNewOrHot(Integer newOrHot) {
		this.newOrHot = newOrHot;
	}

	public Integer getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}

	public Integer getPositionType() {
		return positionType;
	}

	public void setPositionType(Integer positionType) {
		this.positionType = positionType;
	}

	public Integer getPosType() {
		return posType;
	}

	public void setPosType(Integer posType) {
		this.posType = posType;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getPositionJson() {
		return positionJson;
	}

	public void setPositionJson(String positionJson) {
		this.positionJson = positionJson;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public SysWebSite getSysWebSite() {
		return sysWebSite;
	}

	public void setSysWebSite(SysWebSite sysWebSite) {
		this.sysWebSite = sysWebSite;
	}

	public ComInfo getComInfo() {
		return comInfo;
	}

	public void setComInfo(ComInfo comInfo) {
		this.comInfo = comInfo;
	}
}
