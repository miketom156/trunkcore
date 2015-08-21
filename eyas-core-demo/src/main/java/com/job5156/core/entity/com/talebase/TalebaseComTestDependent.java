package com.job5156.core.entity.com.talebase;

import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

/**
 * 订单与产品的关联表
 * 
 * @author 夏安定
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class TalebaseComTestDependent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer orderId;// 订单号
	@Id
	private Integer productId;// 产品ID
	
	private Integer productTotal;// 产品总量
	private Integer productOverplus;// 产品剩余
	private Integer productSum;// 产品记录数

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId", insertable = false, updatable = false)
	private TalebaseComTestApply comTestApply;// 企业服务申请

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductTotal() {
		return productTotal;
	}

	public void setProductTotal(Integer productTotal) {
		this.productTotal = productTotal;
	}

	public Integer getProductOverplus() {
		return productOverplus;
	}

	public void setProductOverplus(Integer productOverplus) {
		this.productOverplus = productOverplus;
	}

	public TalebaseComTestApply getComTestApply() {
		return comTestApply;
	}

	public void setComTestApplyServe(TalebaseComTestApply comTestApply) {
		this.comTestApply = comTestApply;
	}

	public Integer getProductSum() {
		return productSum;
	}

	public void setProductSum(Integer productSum) {
		this.productSum = productSum;
	}
}
