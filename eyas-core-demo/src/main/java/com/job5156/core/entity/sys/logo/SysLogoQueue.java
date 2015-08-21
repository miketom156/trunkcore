package com.job5156.core.entity.sys.logo;

import com.job5156.core.bo.mapping.SysHotRecruitPositionBo;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 后台LOGO上线队列
 * SysHotRecruitPositionVo
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysLogoQueue implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 		// 主键ID
	@NotNull(message = "{sys.logoBoard.resourceId.is.notempty}")
	private Integer resourceId; // LOGO资源ID
	@NotNull(message = "{sys.logoBoard.positionId.is.notempty}")
	private Integer positionId; // LOGO位置ID
	@NotNull(message = "{sys.logoBoard.beginDate.is.notempty}")
	private Date beginDate; 	// 显示开始时间
	@NotNull(message = "{sys.logoBoard.endDate.is.notempty}")
	private Date endDate; 		// 显示结束时间
	@NotNull(message = "{sys.logoBoard.buyFlag.is.notempty}")	
	private Integer buyFlag;	// 购买状态[0-免费 1-付费]
	private Date createDate;	// 创建日期
	private String createBy;    // 创建人
	private Date editDate;		// 修改日期
	private String editBy;		// 修改人




    private String comShortName;//公司简称
    private String positionInfo; // 职位信息内容(JSON格式内容)
    private String taoLabelInfo; // 淘标签内容(JSON格式内容)
    private List<SysHotRecruitPositionBo> recruitPositioinList;
    private List<String> taoLableList;
    private String  taoLabelStr; //淘标签（，分割）




	private SysLogoPosition logoPosition;    // LOGO位置
	private SysLogoResource logoResource;	 // LOGO资源
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "positionId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public SysLogoPosition getLogoPosition() {
		return logoPosition;
	}

	public void setLogoPosition(SysLogoPosition logoPosition) {
		this.logoPosition = logoPosition;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resourceId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public SysLogoResource getLogoResource() {
		return logoResource;
	}

	public void setLogoResource(SysLogoResource logoResource) {
		this.logoResource = logoResource;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public Integer getBuyFlag() {
		return buyFlag;
	}

	public void setBuyFlag(Integer buyFlag) {
		this.buyFlag = buyFlag;
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



    public String getComShortName() {
        return comShortName;
    }

    public void setComShortName(String comShortName) {
        this.comShortName = comShortName;
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
    @Transient
    public String getTaoLabelStr() {
        return taoLabelStr;
    }

    public void setTaoLabelStr(String taoLabelStr) {
        this.taoLabelStr = taoLabelStr;
    }
}
