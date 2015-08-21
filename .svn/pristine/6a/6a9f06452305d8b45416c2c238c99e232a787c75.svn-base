package com.job5156.core.entity.act;

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
 * 抽奖活动-中奖记录
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ActWinnerRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id;      // 主键
    private Date createDate ; // 创建时间
    private Date updateDate ; // 修改时间
    private  Integer stkId;// 本次活动ID
    private  Integer awardId;// 奖品ID
    private  String  awardNo;//中奖序号，按中奖时间生成
    private  String awardName;// 奖品名称
    private  Integer awardType;// 奖品类型
    private  Integer userId;// 用户ID
    private  String userAccount;// 账号
    private  Integer userType;// 用户类型 0:个人用户 1：企业用户
    private  Integer awardLev ;// 奖品级别 0：特等奖，1：一等奖,...
    private  Integer status;//  奖品状态   0：待兑换，1：待审核, 2：已兑换 3：逾期
    private Date exchangeTime;// 奖品兑换时间
    private Date expiryDate;// 奖品逾期时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getStkId() {
        return stkId;
    }

    public void setStkId(Integer stkId) {
        this.stkId = stkId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getAwardLev() {
        return awardLev;
    }

    public void setAwardLev(Integer awardLev) {
        this.awardLev = awardLev;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
    }


    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }


    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getAwardNo() {
        return awardNo;
    }

    public void setAwardNo(String awardNo) {
        this.awardNo = awardNo;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
