package com.job5156.webapp.model.com.vas;

import com.job5156.core.common.option.BaseOption;
import com.job5156.webapp.model.com.ComInfo;
import com.job5156.webapp.model.com.ComUser;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>企业的产品服务订单</p>
 * Date: 2015/5/26 20:00
 *
 * @version 1.0
 * @author: Lu Weibiao
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComServiceOrder implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String orderNo; //订单编号
    private String productName; //商品名称
    private Integer orderStatus; //订单状态。参见OrderStatus
    private BigDecimal totalFee; //订单总额=商品总额+配送费用
    private BigDecimal productFee; //商品总额
    private BigDecimal deliveryFee; //'配送费'
    private BigDecimal realPaymentFee; //'实际支付金额'
    private Integer receiptApplyStatus; //开具发票状态。默认为未办理。
    private Integer comId; //企业id
    private String comName; //企业名称
    private Integer comUserId; //企业用户id
    private String comUserName; //企业用户账号名
    private String payWay; //支付方式。参见PayEnum
    private Integer payStatus; //支付状态
    private String payInfo; //支付信息。Json结构
    private Date payDate;//支付时间
    private String creator; //创建人
    private Date createDate; //创建时间
    private String editor; //后台修改人
    private Date editDate; //修改时间
    private Integer delStatus; //'标识记录删除状态。0为存在，-1为已删除'

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comId", insertable = false, updatable = false)
    private ComInfo comInfo;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comUserId", insertable = false, updatable = false)
    private ComUser comUser;

    @OneToMany(mappedBy="comServiceOrder", cascade = { CascadeType.REMOVE }, fetch=FetchType.LAZY)
    private List<ComServiceOrderItem> comServiceOrderItemList;

    /** 属性选项定义 **/
    //订单状态
    public static class OrderStatus{
        public final static BaseOption<Integer,String> ALL = new BaseOption<>(-1, "所有");
        public final static BaseOption<Integer,String> UNPAID = new BaseOption(0, "待支付");
        public final static BaseOption<Integer,String> FINISHED = new BaseOption(1, "已支付");
        public final static BaseOption<Integer,String> CANCELED = new BaseOption(2, "已取消/失效");

        public final static Map<Integer,String> optionMap = new LinkedHashMap<>();
        static {
            optionMap.put(ALL.code,ALL.name);
            optionMap.put(UNPAID.code,UNPAID.name);
            optionMap.put(FINISHED.code,FINISHED.name);
            optionMap.put(CANCELED.code,CANCELED.name);
        }
        private OrderStatus(){}
    }

    //支付状态
    public static class PayStatus{
        public final static BaseOption<Integer,String> ALL = new BaseOption<>(-1, "所有");
        public final static BaseOption<Integer,String> UNPAID = new BaseOption(0, "未支付");
        public final static BaseOption<Integer,String> PAYING = new BaseOption(1, "支付中");
        public final static BaseOption<Integer,String> PAID = new BaseOption(2, "已支付");

        public final static Map<Integer,String> optionMap = new LinkedHashMap<>();
        static {
            optionMap.put(ALL.code,ALL.name);
            optionMap.put(UNPAID.code,UNPAID.name);
            optionMap.put(PAYING.code,PAYING.name);
            optionMap.put(PAID.code,PAID.name);
        }
        private PayStatus(){}
    }

    //开具发票状态
    public static class ReceiptApplyStatus {
        public final static BaseOption<Integer,String> ALL = new BaseOption<>(-1, "所有");
        public final static BaseOption<Integer,String> UNAPPLY = new BaseOption(0,"未申请");
        public final static BaseOption<Integer,String> APPLYING = new BaseOption(1,"已申请办理");
        public final static BaseOption<Integer,String> PASS = new BaseOption(2,"已办理");

        public final static Map<Integer,String> optionMap = new LinkedHashMap<>();
        static {
            optionMap.put(ALL.code,ALL.name);
            optionMap.put(UNAPPLY.code,UNAPPLY.name);
            optionMap.put(APPLYING.code,APPLYING.name);
            optionMap.put(PASS.code,PASS.name);
        }
        private ReceiptApplyStatus(){}
    }

    //查询排序字段
    public static class OrderField {
        public final static BaseOption<Integer,String> CREATE_TIME = new BaseOption(0,"下单时间");
        public final static BaseOption<Integer,String> PAY_TIME = new BaseOption(1,"付款时间");
        public final static BaseOption<Integer,String> SERVICE_TYPE = new BaseOption(2,"服务类型");
        public final static BaseOption<Integer,String> RECEIPT_APPLY_STATUS = new BaseOption(3,"发票状态");

        public final static Map<Integer,String> optionMap = new LinkedHashMap<>();
        static {
            optionMap.put(CREATE_TIME.code, CREATE_TIME.name);
            optionMap.put(PAY_TIME.code, PAY_TIME.name);
            optionMap.put(SERVICE_TYPE.code, SERVICE_TYPE.name);
            optionMap.put(RECEIPT_APPLY_STATUS.code, RECEIPT_APPLY_STATUS.name);
        }
        private OrderField(){}
    }

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getProductFee() {
        return productFee;
    }

    public void setProductFee(BigDecimal productFee) {
        this.productFee = productFee;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public BigDecimal getRealPaymentFee() {
        return realPaymentFee;
    }

    public void setRealPaymentFee(BigDecimal realPaymentFee) {
        this.realPaymentFee = realPaymentFee;
    }

    public Integer getReceiptApplyStatus() {
        return receiptApplyStatus;
    }

    public void setReceiptApplyStatus(Integer receiptApplyStatus) {
        this.receiptApplyStatus = receiptApplyStatus;
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

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
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

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public ComUser getComUser() {
        return comUser;
    }

    public void setComUser(ComUser comUser) {
        this.comUser = comUser;
    }

    public ComInfo getComInfo() {
        return comInfo;
    }

    public void setComInfo(ComInfo comInfo) {
        this.comInfo = comInfo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<ComServiceOrderItem> getComServiceOrderItemList() {
        return comServiceOrderItemList;
    }

    public void setComServiceOrderItemList(List<ComServiceOrderItem> comServiceOrderItemList) {
        this.comServiceOrderItemList = comServiceOrderItemList;
    }
}
