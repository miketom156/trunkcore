package com.job5156.core.entity.sys.statistics;

import com.job5156.core.entity.sys.system.SysDepartment;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysResumePosRecommandHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer userId;
	private String userName;
	private Integer deptId;
	private String perIds;
	private String posIds;
	private Integer perCount;
	private Integer posCount;
	private Integer recommandType;
	private Date recommandDate;
	private String recommandResult;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deptId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private SysDepartment sysDepartment;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getPerIds() {
		return perIds;
	}
	public void setPerIds(String perIds) {
		this.perIds = perIds;
	}
	public String getPosIds() {
		return posIds;
	}
	public void setPosIds(String posIds) {
		this.posIds = posIds;
	}
	public Integer getPerCount() {
		return perCount;
	}
	public void setPerCount(Integer perCount) {
		this.perCount = perCount;
	}
	public Integer getPosCount() {
		return posCount;
	}
	public void setPosCount(Integer posCount) {
		this.posCount = posCount;
	}
	public Integer getRecommandType() {
		return recommandType;
	}
	public void setRecommandType(Integer recommandType) {
		this.recommandType = recommandType;
	}
	public Date getRecommandDate() {
		return recommandDate;
	}
	public void setRecommandDate(Date recommandDate) {
		this.recommandDate = recommandDate;
	}
	public SysDepartment getSysDepartment() {
		return sysDepartment;
	}
	public void setSysDepartment(SysDepartment sysDepartment) {
		this.sysDepartment = sysDepartment;
	}
	public String getRecommandResult() {
		return recommandResult;
	}
	public void setRecommandResult(String recommandResult) {
		this.recommandResult = recommandResult;
	}
}
