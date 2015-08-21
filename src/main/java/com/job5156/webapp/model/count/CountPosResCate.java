package com.job5156.webapp.model.count;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Andy on 2014/9/28.
 * 
 * 职位/简历数据统计
 */
public class CountPosResCate implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer workLocation; // 工作地区
	private Integer posType; // 职位类别
	private Integer reqDegree; // 学历要求，0=不限；1=初中；2=高中；3=中专；4=大专；5=本科；6=硕士；7=MBA；8=博士
	private Integer addPosCount; // 职位增量统计数量
	private Integer savePosCount; // 职位存量统计数量
	private Integer addResCount; // 简历增量统计数量
	private Integer saveResCount; // 简历存量统计数量
    private Integer activeResCount; // 简历活跃量统计数量
	private Date createDate; // 每次统计时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(Integer workLocation) {
		this.workLocation = workLocation;
	}

	public Integer getPosType() {
		return posType;
	}

	public void setPosType(Integer posType) {
		this.posType = posType;
	}

	public Integer getReqDegree() {
		return reqDegree;
	}

	public void setReqDegree(Integer reqDegree) {
		this.reqDegree = reqDegree;
	}

	public Integer getAddPosCount() {
		return addPosCount;
	}

	public void setAddPosCount(Integer addPosCount) {
		this.addPosCount = addPosCount;
	}

	public Integer getSavePosCount() {
		return savePosCount;
	}

	public void setSavePosCount(Integer savePosCount) {
		this.savePosCount = savePosCount;
	}

	public Integer getAddResCount() {
		return addResCount;
	}

	public void setAddResCount(Integer addResCount) {
		this.addResCount = addResCount;
	}

	public Integer getSaveResCount() {
		return saveResCount;
	}

	public void setSaveResCount(Integer saveResCount) {
		this.saveResCount = saveResCount;
	}

    public Integer getActiveResCount() {
        return activeResCount;
    }

    public void setActiveResCount(Integer activeResCount) {
        this.activeResCount = activeResCount;
    }

    public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
