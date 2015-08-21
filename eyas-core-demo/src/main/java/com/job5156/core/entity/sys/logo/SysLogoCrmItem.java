package com.job5156.core.entity.sys.logo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 同步CRM的LOGO看板区
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysLogoCrmItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;				//主键ID
	@NotNull(message = "{sys.logoFile.crmSyncSet.siteId.is.notempty}")
	private Integer siteId;			//站点ID
	@NotNull(message = "{sys.logoFile.crmSyncSet.logoFileId.is.notempty}")
	private Integer logoFileId;		//LOGO文件ID
	@NotBlank(message = "{sys.logoFile.crmSyncSet.crmSiteName.is.notempty}")
	private String crmSiteName;		//CRM显示站点名称
	private Integer sort;			//LOGO块排序
	private Date createDate;		//创建日期
	private String createBy;		//创建人
	private Date editDate;			//修改日期
	private String editBy;			//修改人
	
	private SysLogoFile logoFile;   //LOGO看板文件
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "logoFileId", insertable = false, updatable = false)
	public SysLogoFile getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(SysLogoFile logoFile) {
		this.logoFile = logoFile;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getLogoFileId() {
		return logoFileId;
	}

	public void setLogoFileId(Integer logoFileId) {
		this.logoFileId = logoFileId;
	}

	public String getCrmSiteName() {
		return crmSiteName;
	}

	public void setCrmSiteName(String crmSiteName) {
		this.crmSiteName = crmSiteName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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
