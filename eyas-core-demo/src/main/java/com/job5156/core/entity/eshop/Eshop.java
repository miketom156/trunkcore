package com.job5156.core.entity.eshop;

import com.job5156.core.entity.com.ComInfo;
import com.job5156.core.entity.sys.logo.SysLogo;
import com.job5156.core.bo.mapping.EshopPositionBo;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 电商实体类
 * @author leeton
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Eshop implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; // 主键ID

	@NotNull(message = "{eshop.comId.is.notempty}")
	private Integer comId; // 企业ID

    @NotNull(message = "{eshop.logoId.is.notempty}")
	private Integer logoId; // LOGO图片ID

	private String positionInfo; // 职位信息内容(JSON格式内容)
	private String taoLabelInfo; // 淘标签内容(JSON格式内容)
	private String linkUrl; // LOGO广告链接地址
	private Integer sort; // 排序
	private Date createDate; // 创建日期
	private String createBy; // 创建人
	private Date editDate; // 修改日期
	private String editBy; // 修改人
    private Integer hits;//点击数

    @Transient
    private String logoUrl;  //LOGO图片全路径

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logoId", updatable = false, insertable = false)
    @NotFound(action = NotFoundAction.IGNORE)
	private SysLogo sysLogo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comId", updatable = false, insertable = false)
    @NotFound(action = NotFoundAction.IGNORE)
	private ComInfo comInfo;
	
	@Valid
	@NotNull(message = "{eshop.positionInfo.is.notempty}")
    @Transient
	private List<EshopPositionBo> positionList;

    @Transient
    private List<String> taoLableList;


	public SysLogo getSysLogo() {
		return sysLogo;
	}

	public void setSysLogo(SysLogo sysLogo) {
		this.sysLogo = sysLogo;
	}


	public ComInfo getComInfo() {
		return comInfo;
	}

	public void setComInfo(ComInfo comInfo) {
		this.comInfo = comInfo;
	}


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

    public String getPositionInfo() {
        return positionInfo;
    }

    public void setPositionInfo(String positionInfo) {
        this.positionInfo = positionInfo;
    }

    public String getTaoLabelInfo() {
        return taoLabelInfo;
    }

    public void setTaoLabelInfo(String taoLabelInfo) {
        this.taoLabelInfo = taoLabelInfo;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<EshopPositionBo> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<EshopPositionBo> positionList) {
        this.positionList = positionList;
    }

    public List<String> getTaoLableList() {
        return taoLableList;
    }

    public void setTaoLableList(List<String> taoLableList) {
        this.taoLableList = taoLableList;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }
}
