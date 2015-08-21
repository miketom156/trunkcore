package com.job5156.core.entity.sys.subsite;

import com.job5156.core.entity.com.ComInfo;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysComPosSchool implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer comId;             //企业id
	private String comName;            //企业名称
	private Date createDate;           //创建日期
	private Integer createUserId;      //创建人id
	private String createUserName;     //创建人姓名
	private Integer orderBy;           //排序号
	private Integer websiteId;         //分站点id
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name = "websiteId", insertable = false, updatable = false)
	private SysWebSite sysWebSite;                                //所属分站点
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name = "comId", insertable = false, updatable = false)
	private ComInfo comInfo;                                //所属企业
	
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getWebsiteId() {
		return websiteId;
	}
	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
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
