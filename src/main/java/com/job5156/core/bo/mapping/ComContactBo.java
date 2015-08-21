package com.job5156.core.bo.mapping;

import java.util.Date;

public class ComContactBo extends ComContactBaseBo {

    private Integer id;
    private Integer comId;// 企业ID
    private Integer defaultFlag;// 1为默认联系方式
    private Date createDate; //创建日期
	private Date updateDate;// 修改日期
	
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

	public Integer getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
