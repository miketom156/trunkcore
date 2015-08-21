package com.job5156.webapp.model.sys.system;

import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>
 * 后台用户操作实体对象
 * </p>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysOperate implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 			// 操作id
	private String name; 			// 操作名称
	private String url; 			// 操作url
	private Integer moduleId; 		// 绑定模块id
	private Integer displayFlag;	// 是否在菜单显示[0-否 1-是]
	private Integer pageType;		// 页面类型[1-page 2-frame 默认page]
	private String displayName; 	// 菜单显示名称
	private String descript; 		// 操作描述
	private Integer sort;			// 排序

	private SysModule module; 		// 绑定模块

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	
	public Integer getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(Integer displayFlag) {
		this.displayFlag = displayFlag;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Integer getPageType() {
		return pageType;
	}

	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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
}
