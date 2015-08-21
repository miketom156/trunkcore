package com.job5156.core.entity.sys.logo;

import com.google.common.collect.Lists;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <P>
 * 后台LOGO资源对象
 * </P>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysLogoResource implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 			// LOGO资源ID
	@NotNull(message = "{sys.logoResource.comId.is.notempty}")
	private Integer comId; 			// 公司ID
	@NotNull(message = "{sys.logoResource.logoId.is.notempty}")
	private Integer logoId; 		// 上传LOGOID
	@NotNull(message = "{sys.logoResource.sizeId.is.notempty}")
	private Integer logoSizeId; 	// LOGO尺寸大小ID
	@NotNull(message = "{sys.logoResource.logoType.is.notempty}")
	private Integer logoType; 		// LOGO类型[1-图片 2-FLASH]
	private String linkUrl; 	// 链接地址
	@NotNull(message = "{sys.logoBoard.linkType.is.notempty}")
	private Integer linkType; 	// 链接类型[1-图片文字链接 2-FLASH内部链接]
	@NotBlank(message = "{sys.logoResource.descript.is.notempty}")
	private String descript; 		// LOGO内容描述
	private Date createDate; 		// 创建时间
	private String createBy; 		// 创建人
	private Date editDate;			// 修改时间
	private String editBy; 			// 修改人
	
	private SysLogo logo;						// 上传的LOGO图片
	private SysLogoSize logoSize;				// LOGO尺寸
	private List<SysLogoQueue> logoQueueList = Lists.newArrayList();;	// LOGO在线队列
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "logoResource", cascade = {CascadeType.REMOVE})
	public List<SysLogoQueue> getLogoQueueList() {
		return logoQueueList;
	}

	public void setLogoQueueList(List<SysLogoQueue> logoQueueList) {
		this.logoQueueList = logoQueueList;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "logoId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public SysLogo getLogo() {
		return logo;
	}

	public void setLogo(SysLogo logo) {
		this.logo = logo;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "logoSizeId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public SysLogoSize getLogoSize() {
		return logoSize;
	}

	public void setLogoSize(SysLogoSize logoSize) {
		this.logoSize = logoSize;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public Integer getLogoId() {
		return logoId;
	}

	public void setLogoId(Integer logoId) {
		this.logoId = logoId;
	}

	public Integer getLogoSizeId() {
		return logoSizeId;
	}

	public void setLogoSizeId(Integer logoSizeId) {
		this.logoSizeId = logoSizeId;
	}

	public Integer getLogoType() {
		return logoType;
	}

	public void setLogoType(Integer logoType) {
		this.logoType = logoType;
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

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}
}
