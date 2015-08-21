package com.job5156.webapp.model.com.vas;

import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>企业服务订单项</p>
 * Date: 2015/5/26 19:51
 *
 * @version 1.0
 * @autor: Lu Weibiao
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComServiceOrderItem implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer orderId; //所属订单id。外键
    private Integer serviceItemId; //服务项id。外键
    private String serviceName; //'服务项名称'
    private Integer serviceNum;//'服务数量。如简历点数'
    private BigDecimal originalPrice; //产品/服务原价
    private BigDecimal discountAmount; //优惠金额
    private String creator; //创建人
    private Date createDate; //创建时间
    private String editor; //后台修改人
    private Date editDate; //修改时间

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    private ComServiceOrder comServiceOrder;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceItemId", insertable = false, updatable = false)
    private ComServiceItem comServiceItem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(Integer serviceItemId) {
        this.serviceItemId = serviceItemId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(Integer serviceNum) {
        this.serviceNum = serviceNum;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public ComServiceItem getComServiceItem() {
        return comServiceItem;
    }

    public void setComServiceItem(ComServiceItem comServiceItem) {
        this.comServiceItem = comServiceItem;
    }

    public ComServiceOrder getComServiceOrder() {
        return comServiceOrder;
    }

    public void setComServiceOrder(ComServiceOrder comServiceOrder) {
        this.comServiceOrder = comServiceOrder;
    }
}
