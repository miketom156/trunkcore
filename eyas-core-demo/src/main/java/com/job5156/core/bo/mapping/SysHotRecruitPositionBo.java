package com.job5156.core.bo.mapping;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 热聘企业职位
 * 
 * @author leo
 * 
 */
public class SysHotRecruitPositionBo {

	@NotNull(message = "{sys.hotRecruit.positionId.is.notempty}")
	private Integer positionId; // 职位ID
	@NotBlank(message = "{sys.hotRecruit.positionName.is.notempty}")
	private String positionName; // 职位名称
	private Integer reqLocation;//职位地区
	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Integer getReqLocation() {
		return reqLocation;
	}

	public void setReqLocation(Integer reqLocation) {
		this.reqLocation = reqLocation;
	}
}
