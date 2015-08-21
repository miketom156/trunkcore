package com.job5156.webapp.model.sys.system;

import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 后台数据权限实体对象
 * </p>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysDataRight implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 			// 记录id
	@NotNull(message = "{sys.dataright.roleId.is.notempty}")
	private Integer roleId; 		// 角色id
	@NotNull(message = "{sys.dataright.moduleId.is.notempty}")
	private Integer moduleId; 		// 模块id
	private String rightValue; 		// 数据权限值
	private Date createDate; 		// 创建日期
	private String createBy; 		// 创建人
	private Date editDate; 			// 修改日期
	private String editBy; 			// 修改人
	
	private SysRole role;           // 关联角色对象
	private SysModule module;       // 关联模块对象
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "moduleId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public SysModule getModule() {
		return module;
	}

	public void setModule(SysModule module) {
		this.module = module;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getRightValue() {
		return rightValue;
	}

	public void setRightValue(String rightValue) {
		this.rightValue = rightValue;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public String getEditBy() {
		return editBy;
	}

	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}
}
