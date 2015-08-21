package com.job5156.core.entity.com.talebase;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 企业服务申请
 * 
 * @author 夏安定
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class TalebaseComTestApply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer orderId;// 订单号
	private Integer comId;// 企业ID
	private Float amount;// 购买金额
	private Integer count;// 购买总数

	@OneToMany(mappedBy = "comTestApply", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<TalebaseComTestDependent> ctDependentList;// 订单与产品的关联表

	private Integer overplus;// 购买剩余
	private Date applyDate;// 申请日期
	private Date createDate;// 开通日期
	private Date endDate;// 有效日期
	private Date updateDate;//更新日期

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<TalebaseComTestDependent> getCtDependentList() {
		return ctDependentList;
	}

	public void setCtDependentList(
			List<TalebaseComTestDependent> ctDependentList) {
		this.ctDependentList = ctDependentList;
	}

	public Integer getOverplus() {
		return overplus;
	}

	public void setOverplus(Integer overplus) {
		this.overplus = overplus;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
