package com.job5156.core.entity.act;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 倍智测评订单实体
 * </p>
 * 
 * @author leo
 * 
 */
@Entity
@Table(name = "talebase_order")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class TaleBaseOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 订单ID
	 */
	private Integer id;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 邮件地址
	 */
	private String userEmail;
	/**
	 * 用户手机
	 */
	private String userMobile;
	/**
	 * 用户电话
	 */
	private String userPhone;
	/**
	 * 客户端IP
	 */
	private String clientIp;
	/**
	 * 测试产品ID
	 */
	private String productId;
	/**
	 * 测试产品名称
	 */
	private String productName;
	/**
	 * 订单类型[1-产品免费测试 2-白金会员免费测试 9-付费测试]
	 */
	private Integer orderType;
	/**
	 * 产品单价
	 */
	private BigDecimal price;
	/**
	 * 购买数量
	 */
	private Integer quantity;
	/**
	 * 订单总金额
	 */
	private BigDecimal amount;
	/**
	 * 币种(CNY:人民币)
	 */
	private String moneyType;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 支付信息，结构为JSON字符串
	 */
	private String paymentInfo;
	/**
	 * 支付方式
	 */
	private String payWay;
	/**
	 * 支付状态
	 */
	private Integer payStatus;
	/**
	 * 订单创建日期
	 */
	private Date createTime;
	/**
	 * 订单支付日期
	 */
	private Date payTime;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}
}