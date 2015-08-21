package com.job5156.webapp.model.com.vas;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>企业充值历史记录</p>
 * Date: 2015/6/1 9:41
 *
 * @version 1.0
 * @autor: Lu Weibiao
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComRechargeHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer orderId;//订单id
    private Integer comId;//企业id
    private Integer rechargeNum;//充值点数
    private String rechargeType;//充值类型
    private Integer previousNum;//充值前点数
    private Integer afterNum;//充值后点数
    private String editor; //后台修改人
    private Date editDate; //后台修改时间

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getRechargeNum() {
        return rechargeNum;
    }

    public void setRechargeNum(Integer rechargeNum) {
        this.rechargeNum = rechargeNum;
    }

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

    public Integer getPreviousNum() {
        return previousNum;
    }

    public void setPreviousNum(Integer previousNum) {
        this.previousNum = previousNum;
    }

    public Integer getAfterNum() {
        return afterNum;
    }

    public void setAfterNum(Integer afterNum) {
        this.afterNum = afterNum;
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
}
