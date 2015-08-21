package com.job5156.core.entity.sys.statistics;

import com.job5156.core.entity.per.PerUser;
import com.job5156.core.entity.sys.system.SysUser;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysAddResumeHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer perUserId;                //个人用户ID
	private Integer sysUserId;                //录入人ID
	private Date createDate;                  //录入时间ID
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perUserId", updatable = false, insertable = false)
	private PerUser perUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sysUserId", updatable = false, insertable = false)
	private SysUser sysUser;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPerUserId() {
		return perUserId;
	}
	public void setPerUserId(Integer perUserId) {
		this.perUserId = perUserId;
	}
	public Integer getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public PerUser getPerUser() {
		return perUser;
	}
	public void setPerUser(PerUser perUser) {
		this.perUser = perUser;
	}
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	
}
