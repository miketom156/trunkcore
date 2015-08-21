package com.job5156.core.entity.sys.setting;

import com.job5156.core.entity.com.ComInfo;
import com.job5156.core.entity.sys.logo.SysLogo;
import com.job5156.core.bo.mapping.SysHotRecruitPositionBo;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 首页热聘企业
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysHotRecruitCom implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; // 主键ID
	@NotNull(message = "{sys.hotRecruit.comId.is.notempty}")
	private Integer comId; // 企业ID
	private Integer logoId; // LOGO图片ID
	private String positionInfo; // 职位信息内容(JSON格式内容)
	private String taoLabelInfo; // 淘标签内容(JSON格式内容)
	private Integer buyFlag; // 购买标记[0-免费 1-付费]
	private String linkUrl; // LOGO广告链接地址
	private Integer sort; // 排序
	private Date createDate; // 创建日期
	private String createBy; // 创建人
	private Date editDate; // 修改日期
	private String editBy; // 修改人
	
	private String logoPath;  //LOGO图片路径
	private SysLogo sysLogo;
	private ComInfo comInfo;
    private Integer hits;//点击数



    private String  taoLabelStr; //淘标签（，分割）

	@Valid
	@NotNull(message = "{sys.hotRecruit.positionInfo.is.notempty}")
	private List<SysHotRecruitPositionBo> recruitPositioinList;
//	@NotNull(message = "{sys.hotRecruit.taoLabelInfo.is.notempty}")
	private List<String> taoLableList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "logoId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public SysLogo getSysLogo() {
		return sysLogo;
	}

	public void setSysLogo(SysLogo sysLogo) {
		this.sysLogo = sysLogo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public ComInfo getComInfo() {
		return comInfo;
	}

	public void setComInfo(ComInfo comInfo) {
		this.comInfo = comInfo;
	}

	@Transient
	public List<SysHotRecruitPositionBo> getRecruitPositioinList() {
		return recruitPositioinList;
	}

	public void setRecruitPositioinList(List<SysHotRecruitPositionBo> recruitPositioinList) {
		this.recruitPositioinList = recruitPositioinList;
	}

	@Transient
	public List<String> getTaoLableList() {
        if(StringUtils.isNotEmpty(taoLabelStr)) {
            taoLabelStr=taoLabelStr.replace("，",",");
          return  Arrays.asList(taoLabelStr.split(","));
        }
		return taoLableList;
	}

	public void setTaoLableList(List<String> taoLableList) {
		this.taoLableList = taoLableList;
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

	public Integer getBuyFlag() {
		return buyFlag;
	}

	public void setBuyFlag(Integer buyFlag) {
		this.buyFlag = buyFlag;
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

	@Transient
	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }
    @Transient
    public String getTaoLabelStr() {
        return taoLabelStr;
    }

    public void setTaoLabelStr(String taoLabelStr) {
        this.taoLabelStr = taoLabelStr;
    }
}
