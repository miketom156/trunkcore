package com.job5156.webapp.model.com.vas;

import com.job5156.core.common.option.BaseOption;
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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>企业增值服务发票申请记录</p>
 * Date: 2015/5/26 20:31
 *
 * @version 1.0
 * @autor: Lu Weibiao
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComReceiptApply implements Serializable{
    private static final long serialVersionUID = 1L;
    public static final BigDecimal MIN_ORDER_TOTAL_FEE_4_DELIVERY_FREE = BigDecimal.valueOf(1000);
    public static final BigDecimal DEFAULT_DELIVERY_FEE = new BigDecimal("0.01");//TODO 测试完后改回8元

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer comId; //企业id
    private String comName; //企业名称
    private Integer comUserId; //企业用户id
    private String comUserName; //企业用户账号名
    private String orderNos; //开具发票的多个订单编号。json格式(list)
    private BigDecimal orderTotalFee;//'订单总金额
    private Integer applyStatus; //申请状态。{0:审核中, 1:已审核通过, 2：审核不通过}
    private String invoiceTitle;//'发票抬头'
    private String address; //地址。json。参见AddressVo
    private String zipCode; //邮编
    private String consignee; //收件人
    private String mobilePhone; //手机号码
    private String telPhone; //固话号码
    private Integer deliveryFeeType;//快递费用类型。{在线支付（8元）：0，快递到付：1，由智通承担：2。}
    private BigDecimal deliveryFee; //配送费
    private String onlinePayWay;//在线支付方式。参见PayEnum.Provider
    private Integer payStatus;//快递费支付状态。选择在线支付配送费时用。参见ComServiceOrder
    private String payInfo; //在线第三方支付返回结果。json
    private String sysRemark; //后台操作备注
    private String creator; //创建人
    private Date createDate; //创建时间
    private String editor; //后台修改人
    private Date editDate; //后台修改时间
    private Integer delStatus;//标识记录删除状态。0为存在，-1为已删除

    //发票申请状态
    public static class ApplyStatus{
        public final static BaseOption<Integer,String> ALL = new BaseOption<>(-1,"所有");
        public final static BaseOption<Integer,String> AUDITING = new BaseOption(0,"已申请办理");
        public final static BaseOption<Integer,String> PASS = new BaseOption(1,"已办理");
//        public final static BaseOption<Integer,String> FAIL = new BaseOption(2,"审核不通过");

        public final static Map<Integer,String> optionMap = new LinkedHashMap<>();
        static {
            optionMap.put(ALL.code,ALL.name);
            optionMap.put(AUDITING.code,AUDITING.name);
            optionMap.put(PASS.code,PASS.name);
//            optionMap.put(FAIL.code,FAIL.name);
        }
        private ApplyStatus(){}
    }

    //快递费用类型
    public static class DeliveryFeeType{
        public final static BaseOption<Integer,String> ALL = new BaseOption<>(-1,"所有");
        public final static BaseOption<Integer,String> ONLINE_PAY = new BaseOption(0,"在线支付（" + DEFAULT_DELIVERY_FEE + "元）");
        public final static BaseOption<Integer,String> FREIGHT_COLLECT = new BaseOption(1,"快递到付");
        public final static BaseOption<Integer,String> FREE = new BaseOption(2,"由智通承担");

        public final static Map<Integer,String> optionMap = new LinkedHashMap<>();
        static {
            optionMap.put(ALL.code,ALL.name);
            optionMap.put(ONLINE_PAY.code,ONLINE_PAY.name);
            optionMap.put(FREIGHT_COLLECT.code,FREIGHT_COLLECT.name);
            optionMap.put(FREE.code,FREE.name);
        }
        private DeliveryFeeType(){}
    }

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

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public Integer getComUserId() {
        return comUserId;
    }

    public void setComUserId(Integer comUserId) {
        this.comUserId = comUserId;
    }

    public String getComUserName() {
        return comUserName;
    }

    public void setComUserName(String comUserName) {
        this.comUserName = comUserName;
    }

    public String getOrderNos() {
        return orderNos;
    }

    public void setOrderNos(String orderNos) {
        this.orderNos = orderNos;
    }

    public BigDecimal getOrderTotalFee() {
        return orderTotalFee;
    }

    public void setOrderTotalFee(BigDecimal orderTotalFee) {
        this.orderTotalFee = orderTotalFee;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public Integer getDeliveryFeeType() {
        return deliveryFeeType;
    }

    public void setDeliveryFeeType(Integer deliveryFeeType) {
        this.deliveryFeeType = deliveryFeeType;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getOnlinePayWay() {
        return onlinePayWay;
    }

    public void setOnlinePayWay(String onlinePayWay) {
        this.onlinePayWay = onlinePayWay;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getSysRemark() {
        return sysRemark;
    }

    public void setSysRemark(String sysRemark) {
        this.sysRemark = sysRemark;
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

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }
}
