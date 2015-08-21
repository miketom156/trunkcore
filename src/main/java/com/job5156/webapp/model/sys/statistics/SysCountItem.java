package com.job5156.webapp.model.sys.statistics;

import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysCountItem implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer itemId;
	private Integer ipCount;
	private Integer pvCount;
	private Integer regResumeCount;
	private Integer spreadCount;
	private Date countDate;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name = "itemId", insertable = false, updatable = false)
	private SysCountItemSetting sysCountItemSetting;           //所属分站点
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getIpCount() {
		return ipCount;
	}
	public void setIpCount(Integer ipCount) {
		this.ipCount = ipCount;
	}
	public Integer getPvCount() {
		return pvCount;
	}
	public void setPvCount(Integer pvCount) {
		this.pvCount = pvCount;
	}
	public Integer getRegResumeCount() {
		return regResumeCount;
	}
	public void setRegResumeCount(Integer regResumeCount) {
		this.regResumeCount = regResumeCount;
	}
	public Integer getSpreadCount() {
		return spreadCount;
	}
	public void setSpreadCount(Integer spreadCount) {
		this.spreadCount = spreadCount;
	}
	public Date getCountDate() {
		return countDate;
	}
	public void setCountDate(Date countDate) {
		this.countDate = countDate;
	}
	public SysCountItemSetting getSysCountItemSetting() {
		return sysCountItemSetting;
	}
	public void setSysCountItemSetting(SysCountItemSetting sysCountItemSetting) {
		this.sysCountItemSetting = sysCountItemSetting;
	}
}
