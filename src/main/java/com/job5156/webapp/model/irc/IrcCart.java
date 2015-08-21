package com.job5156.webapp.model.irc;

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

/**
 * 
 * @ClassName: IrcCat
 * @Description: 互动招聘购物车实体类
 * @author LQF
 * @date 2013-09-11
 * 
 */

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class IrcCart implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer           id;                   // 主键

    private Integer           comId;                // 企业ID
    private Integer           proNo;                // 产品编号
    private String            proAttr;              // 产品属性
    private BigDecimal        proPrice;             // 产品价格
    private String            proName;              // 产品名称
    private String            proDesc;              // 产品描述
    private String            proImgurl;            // 产品预览图片
    private String            proUrl;               // 产品详细页面
    private Integer           proBugcount;          // 购买数量
    private BigDecimal        proFree;              // 优惠百分比，eg:0.30
    private Date              creDate;              // 加入时间
    private Date              editDate;             // 更改时间
    private String            proCate;              // 商品类别
    private Integer           proPriv;              // 商品是否加入到整单打折 [1:不加入, 其他:加入]

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

    public Integer getProNo() {
        return proNo;
    }

    public void setProNo(Integer proNo) {
        this.proNo = proNo;
    }

    public String getProAttr() {
        return proAttr;
    }

    public void setProAttr(String proAttr) {
        this.proAttr = proAttr;
    }

    public BigDecimal getProPrice() {
        return proPrice;
    }

    public void setProPrice(BigDecimal proPrice) {
        this.proPrice = proPrice;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProImgurl() {
        return proImgurl;
    }

    public void setProImgurl(String proImgurl) {
        this.proImgurl = proImgurl;
    }

    public String getProUrl() {
        return proUrl;
    }

    public void setProUrl(String proUrl) {
        this.proUrl = proUrl;
    }

    public Integer getProBugcount() {
        return proBugcount;
    }

    public void setProBugcount(Integer proBugcount) {
        this.proBugcount = proBugcount;
    }

    public BigDecimal getProFree() {
        return proFree;
    }

    public void setProFree(BigDecimal proFree) {
        this.proFree = proFree;
    }

    public Date getCreDate() {
        return creDate;
    }

    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public String getProCate() {
        return proCate;
    }

    public void setProCate(String proCate) {
        this.proCate = proCate;
    }

    public Integer getProPriv() {
        return proPriv;
    }

    public void setProPriv(Integer proPriv) {
        this.proPriv = proPriv;
    }

}
