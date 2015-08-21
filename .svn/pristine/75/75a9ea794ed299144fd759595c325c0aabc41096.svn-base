package com.job5156.webapp.model.sys.system;

import com.google.common.collect.Lists;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.OrderBy;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 后台用户角色实体对象
 * </p>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysRole implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id; 			// 角色id
	@NotBlank(message = "{sys.role.name.is.notempty}")
	private String name; 			// 角色名称
	private String descript; 		// 角色描述
	
	private List<SysUser> userList = Lists.newArrayList();          //用户此角色的用户列表
	private List<SysOperate> operateList = Lists.newArrayList();	//权限操作列表
	private List<SysDataRight> dataRightList = Lists.newArrayList();//数据权限列表
	
	@ManyToMany(mappedBy = "roleList", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)	
	public List<SysUser> getUserList() {
		return userList;
	}

	public void setUserList(List<SysUser> userList) {
		this.userList = userList;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_role_operate", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "operate_id") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<SysOperate> getOperateList() {
		return operateList;
	}

	public void setOperateList(List<SysOperate> operateList) {
		this.operateList = operateList;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = {CascadeType.REMOVE})
	public List<SysDataRight> getDataRightList() {
		return dataRightList;
	}

	public void setDataRightList(List<SysDataRight> dataRightList) {
		this.dataRightList = dataRightList;
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

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
}
