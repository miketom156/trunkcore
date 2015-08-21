package com.job5156.webapp.model.com.vas;

import com.job5156.core.common.option.BaseOption;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>企业服务项表。一个服务项可能是单独一项服务；可能是多个服务项组成一个套餐，见企业套餐表。</p>
 * Date: 2015/5/26 19:27
 *
 * @version 1.0
 * @author: Lu Weibiao
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComServiceItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String itemNo; //服务项编号
    private String name; //服务项名称
    private String description; //服务描述
    private Integer serviceTypeId; //服务类型id。标识单个服务的兑现规则.参见CommonEnum.ComServiceType
    private Integer serviceNum; //提供的服务数量，如5个简历点数
    private BigDecimal originalPrice; //服务原价
    private BigDecimal discountAmount; //'优惠金额'
    private Integer servicePackageId; //所属套餐id
    private Integer activationStatus; //上架/激活状态。 参见ActivationStatus
    private String creator; //后台创建人
    private Date createDate; //创建时间'
    private String editor; //后台修改人
    private Date editDate; //修改时间
    private Integer delStatus; //标识记录删除状态。0为存在，-1为已删除；默认为0

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceTypeId", insertable = false, updatable = false)
    private ComServiceItemType comServiceItemType;

    /**
     * 上架/激活状态
     */
    public static class  ActivationStatus {
        public final static BaseOption<Integer,String> INACTIVATED = new BaseOption<>(0,"未激活");
        public final static BaseOption<Integer,String> ACTIVATED = new BaseOption(1,"已激活");

        public final static Map<Integer,String> optionMap = new LinkedHashMap<>();
        static {
            optionMap.put(INACTIVATED.code,INACTIVATED.name);
            optionMap.put(ACTIVATED.code,ACTIVATED.name);
        }
        private ActivationStatus(){}
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
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

    public Integer getServicePackageId() {
        return servicePackageId;
    }

    public void setServicePackageId(Integer servicePackageId) {
        this.servicePackageId = servicePackageId;
    }

    public Integer getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(Integer activationStatus) {
        this.activationStatus = activationStatus;
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

    public ComServiceItemType getComServiceItemType() {
        return comServiceItemType;
    }

    public void setComServiceItemType(ComServiceItemType comServiceItemType) {
        this.comServiceItemType = comServiceItemType;
    }
}
