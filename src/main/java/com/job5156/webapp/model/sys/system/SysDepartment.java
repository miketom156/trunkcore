package com.job5156.webapp.model.sys.system;

import com.google.common.collect.Lists;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台部门实体对象
 * </p>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 				// 部门id
	@NotBlank(message = "{sys.department.name.is.notempty}")
	private String name; 				// 部门名称
	private Integer parentId; 			// 父部门id
	private Integer deptType; 			// 部门性质[0-行政部门 1-业务部门]
	private Integer deptProperty; 		// 部门属性[10-大区 20-公司 30-部门 40-小组]
	private String descript;  			// 部门描述
	private Date createDate; 			// 创建日期
	
	private SysDepartment parentDepartment;   									//上级部门
	private List<SysDepartment> subDepartmentList = Lists.newArrayList();		//下级部门列表
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public SysDepartment getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(SysDepartment parentDepartment) {
		this.parentDepartment = parentDepartment;
	}
	
	@OneToMany(mappedBy = "parentId", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	public List<SysDepartment> getSubDepartmentList() {
		return subDepartmentList;
	}

	public void setSubDepartmentList(List<SysDepartment> subDepartmentList) {
		this.subDepartmentList = subDepartmentList;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getDeptType() {
		return deptType;
	}

	public void setDeptType(Integer deptType) {
		this.deptType = deptType;
	}

	public Integer getDeptProperty() {
		return deptProperty;
	}

	public void setDeptProperty(Integer deptProperty) {
		this.deptProperty = deptProperty;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
}
