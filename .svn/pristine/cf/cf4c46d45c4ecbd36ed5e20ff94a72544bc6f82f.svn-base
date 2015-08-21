package com.job5156.core.entity.irc;

import com.job5156.core.common.IrcOrderConstants;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class IrcOrder implements Serializable{

	private static final long serialVersionUID = 1L;
	
    public static final int ORDER_CHECKOUTMETHOD_ONLINE = 1;   //线上支付
    public static final int ORDER_CHECKOUTMETHOD_OFFLINE = 2;  //线下支付
	
    @Id
    private Long id;
	
	private Integer cusType;	//顾客类型（1:企业会员，2:个人会员）
	private Integer cusId;		//顾客id
	private Integer orderType;	//订单类型（1:客户自己主动下单，2:job5156工作人员帮忙下单）
	private String couponCode;	//礼品券或积分券code
	private BigDecimal grandTotal;	//订单总价
	private BigDecimal totalPaid;	//实际结算金额
	private String discountDescription;//打折说明
	private Double discountPercent;		//折扣百分比
	private Double discountAmount;		//直接买满多少减多少
	private String cusEvaluate;			//订单评价
	private String cusNote;		//客户提交订单时填写的备注信息
	private Integer status;		//订单状态（1:创建待付款  2:付款成功等待确认  3:订单已取消  4:订单已确认）
	private Integer isInvoiceNeeded;	//是否需要发票
	private Integer isInvoiceBilling;	//是否已经开具发票
	private Integer invoiceId;	//业务系统或财务系统发票id，用于跟踪订单的发票开具情况
	private Integer invoiceType;//发票类别 （1:个人  2:公司）
	private String invoiceTitle;//发票抬头
	private String invoiceNote;//发票备注
	private Integer checkoutMethod;		//结算方式（1:网银结算  2:现金结算）
	private String remoteIp;			//操作用户IP
	private String operUserid;			//订单操作job5156内部工作人员id
	private String salesId;		//销售顾问工号
	private Date createdAt;		//订单创建时间
	private Date confirmAt;		//成交时间
	private Date updatedAt;		//订单修改时间
	private Integer isFundConfirm;	//CRM系统中用以判断是否到账的记录字段
	private Date fund_confirm_at;		//CRM系统中用以记录到账时间的字段
	private String fundConfirmUser;	//CRM系统中用以记录确认到账的人员姓名
	private Integer crmOrderStatus;		//CRM内部使用的状态码
	
	
    private transient Set<IrcOrderItem> orderItems = new HashSet<IrcOrderItem>();

	public IrcOrder() {
	        this.cusType = IrcOrderConstants.CusType.COMPANY;
	        this.orderType = IrcOrderConstants.OrderType.CUS_IN_PERSON;
	        this.isInvoiceBilling = 0;
	}
	    
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public BigDecimal getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
	public BigDecimal getTotalPaid() {
		return totalPaid;
	}
	public void setTotalPaid(BigDecimal totalPaid) {
		this.totalPaid = totalPaid;
	}
	public String getDiscountDescription() {
		return discountDescription;
	}
	public void setDiscountDescription(String discountDescription) {
		this.discountDescription = discountDescription;
	}
	public Double getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getCusEvaluate() {
		return cusEvaluate;
	}
	public void setCusEvaluate(String cusEvaluate) {
		this.cusEvaluate = cusEvaluate;
	}
	public String getCusNote() {
		return cusNote;
	}
	public void setCusNote(String cusNote) {
		this.cusNote = cusNote;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsInvoiceNeeded() {
		return isInvoiceNeeded;
	}
	public void setIsInvoiceNeeded(Integer isInvoiceNeeded) {
		this.isInvoiceNeeded = isInvoiceNeeded;
	}
	public Integer getIsInvoiceBilling() {
		return isInvoiceBilling;
	}
	public void setIsInvoiceBilling(Integer isInvoiceBilling) {
		this.isInvoiceBilling = isInvoiceBilling;
	}
	public Integer getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Integer getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public String getInvoiceNote() {
		return invoiceNote;
	}
	public void setInvoiceNote(String invoiceNote) {
		this.invoiceNote = invoiceNote;
	}
	public Integer getCheckoutMethod() {
		return checkoutMethod;
	}
	public void setCheckoutMethod(Integer checkoutMethod) {
		this.checkoutMethod = checkoutMethod;
	}
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	public String getOperUserid() {
		return operUserid;
	}
	public void setOperUserid(String operUserid) {
		this.operUserid = operUserid;
	}
	public String getSalesId() {
		return salesId;
	}
	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getConfirmAt() {
		return confirmAt;
	}
	public void setConfirmAt(Date confirmAt) {
		this.confirmAt = confirmAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Integer getIsFundConfirm() {
		return isFundConfirm;
	}
	public void setIsFundConfirm(Integer isFundConfirm) {
		this.isFundConfirm = isFundConfirm;
	}
	public Date getFund_confirm_at() {
		return fund_confirm_at;
	}
	public void setFund_confirm_at(Date fund_confirm_at) {
		this.fund_confirm_at = fund_confirm_at;
	}
	public String getFundConfirmUser() {
		return fundConfirmUser;
	}
	public void setFundConfirmUser(String fundConfirmUser) {
		this.fundConfirmUser = fundConfirmUser;
	}
	public Integer getCrmOrderStatus() {
		return crmOrderStatus;
	}
	public void setCrmOrderStatus(Integer crmOrderStatus) {
		this.crmOrderStatus = crmOrderStatus;
	}

    public Set<IrcOrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(Set<IrcOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
	
}
