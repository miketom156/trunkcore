package com.job5156.core.bo.mapping;

import com.job5156.core.common.util.KeyMaskUtil;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 电商职位
 * 
 * @author leeton
 * 
 */
public class EshopPositionBo {

	@NotNull(message = "{eshop.positionId.is.notempty}")
	private Integer positionId; // 职位ID
    private Integer posNo;      // 职位号
	@NotBlank(message = "{eshop.positionName.is.notempty}")
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

    public Integer getPosNo() {
        if(positionId != null && positionId >0) {
            posNo = KeyMaskUtil.posIdToNo(positionId, (short) 1);
        }
        return posNo;
    }

    public void setPosNo(Integer posNo) {
        this.posNo = posNo;
    }
}
