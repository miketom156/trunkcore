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
 * 后台模块实体对象
 * </p>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysModule implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;          // 模块ID
	private Integer parentId;    // 父模块ID
	@NotBlank(message = "{sys.module.name.is.notempty}")
	private String name;         // 模块名称
	private Integer state;       // 模块状态[1-正常使用 0-禁止使用]
	private Integer orderBy;     // 模块排序
	private String descript;     // 模块描述
	private Date createDate; 	 // 创建日期
	private String createBy; 	 // 创建人
	private Date editDate; 	     // 修改日期
	private String editBy;       // 修改人

	private SysModule parentModule; // 父模块
	private List<SysModule> subModuleList = Lists.newArrayList();     // 子模块列表
	private List<SysOperate> operateList = Lists.newArrayList();      // 功能操作列表
	private List<SysDataRight> dataRightList = Lists.newArrayList();  // 模块对应数据权限列表

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public SysModule getParentModule() {
		return parentModule;
	}

	public void setParentModule(SysModule parentModule) {
		this.parentModule = parentModule;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
	public List<SysOperate> getOperateList() {
		return operateList;
	}

	public void setOperateList(List<SysOperate> operateList) {
		this.operateList = operateList;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "module", cascade = {CascadeType.REMOVE})
	public List<SysDataRight> getDataRightList() {
		return dataRightList;
	}

	public void setDataRightList(List<SysDataRight> dataRightList) {
		this.dataRightList = dataRightList;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentModule", cascade = {CascadeType.REMOVE})
	public List<SysModule> getSubModuleList() {
		return subModuleList;
	}

	public void setSubModuleList(List<SysModule> subModuleList) {
		this.subModuleList = subModuleList;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
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
