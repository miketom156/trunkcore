package com.job5156.core.entity.sys.logo;

import com.google.common.collect.Lists;
import com.job5156.core.entity.sys.subsite.SysWebSite;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <P>
 * 后台LOGO管理-LOGO文件
 * </P>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysLogoFile implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; // 主键ID
	@NotNull(message = "{sys.logoFile.siteId.is.notempty}")
	private Integer siteId; // 站点ID
	@NotNull(message = "{sys.logoFile.fileName.is.notempty}")
	private String fileName; // 保存文件名称
	private Integer templateId; // 使用模板ID
	private Integer state; // 状态[0-停用 1-启用]
	private String descript; // 文件描述
	private Date createDate; // 创建日期
	private String createBy; // 创建人
	private Date editDate; // 修改日期
	private String editBy; // 修改人
    private  Integer  templeteType;//模板类型  0：只显LOGO(默认)  1：知名品牌(大)  2：知名品牌(小)
	private SysWebSite webSite; // 所属分站点
	private SysLogoCrmItem logoCrmItem; // CRM显示LOGO看板区
	private List<SysLogoTable> logoTableList = Lists.newArrayList(); // LOGO行列表

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "logoFile", cascade = { CascadeType.REMOVE })
	@OrderBy(value = "orderBy asc")
	public List<SysLogoTable> getLogoTableList() {
		return logoTableList;
	}

	public void setLogoTableList(List<SysLogoTable> logoTableList) {
		this.logoTableList = logoTableList;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "siteId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public SysWebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(SysWebSite webSite) {
		this.webSite = webSite;
	}

	@OneToOne(mappedBy = "logoFile", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	public SysLogoCrmItem getLogoCrmItem() {
		return logoCrmItem;
	}

	public void setLogoCrmItem(SysLogoCrmItem logoCrmItem) {
		this.logoCrmItem = logoCrmItem;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

    public Integer getTempleteType() {
        return templeteType;
    }

    public void setTempleteType(Integer templeteType) {
        this.templeteType = templeteType;
    }
}
