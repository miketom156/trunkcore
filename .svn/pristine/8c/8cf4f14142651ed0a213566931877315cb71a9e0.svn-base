package com.job5156.webapp.model.irc;

import com.job5156.core.common.IrcOrderConstants;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class IrcOrderItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;// 订单项编号
	private Integer cusType;//客户类型
	private Integer cusId;//客户编号
	private Long orderId;//所属订单编号
	private Integer proId;//产品编号
	private String proName;//产品名称
	private BigDecimal price;//产品价格
	private String attr;//产品属性
	private Integer quantity;//购买数量
	private Integer status;//订单项状态
	private BigDecimal totalfee;//订单项总价
	private Date createdAt;//订单项创建时间
	private Date updatedAt;//订单项修改时间
	
	public IrcOrderItem(){
	}
	
public IrcOrderItem(Integer cusId, int cusType,Integer proId, String proName,
	            BigDecimal price, String attr, Integer quantity, int status,
	            Date createdAt) {
	        this.cusType = IrcOrderConstants.CusType.COMPANY;
	        this.cusId = cusId;
	        this.proId = proId;
	        this.proName = proName;
	        this.price = price;
	        this.attr = attr;
	        this.quantity = quantity;
	        this.status = status;
	        this.createdAt = createdAt;
	        this.totalfee = new BigDecimal(price.doubleValue() * quantity).setScale(2, BigDecimal.ROUND_HALF_UP);
	    }
	   
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCusType() {
		return cusType;
	}
	public void setCusType(Integer cusType) {
		this.cusType = cusType;
	}
	public Integer getCusId() {
		return cusId;
	}
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getTotalfee() {
		return totalfee;
	}
	public void setTotalfee(BigDecimal totalfee) {
		this.totalfee = totalfee;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
