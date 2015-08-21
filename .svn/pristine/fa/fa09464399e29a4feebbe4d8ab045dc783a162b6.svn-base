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
 * 抽奖活动-领奖记录
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ActAcceptRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id;      // 主键
    private Integer  stkId;      // 活动ID
    private String   stkTitle;      // 活动主题

    private Integer  winnerUserId;//中奖用户ID
    private Integer  winnerUserType;//中奖用户类型
    private String   winnerUserName;//中奖用户姓名

    private Integer  winnerRecordId;//中奖记录ID
    private String   winnerRecordNo;//中奖序号

    private String   winnerRecordName;//中奖奖品名称
    private Integer  winnerRecordLev;//奖品等级
    private Integer  winnerRecordType;//奖品类型


    private String   acceptName;//兑奖者姓名
    private String   acceptAddress ;//兑奖者地址，以方便邮件礼物
    private String   acceptPost ;//兑奖者邮编，以方便邮件礼物
    private String   acceptTel;  //兑奖者联系电话，以方便邮件礼物
    private String   acceptAccount;//Q币，填写QQ号； 微信红包，填写微信号;....
    private Date     acceptTime;//兑奖时间

    private Integer sysUserId;//发放者ID
    private Integer sysUserType;//发放者类型
    private String sysUserName;//发放者姓名

    private Date    expiryDate;//奖品逾期时间
    private Integer status;//状态  0：待兑换    1:待审核   2：已兑换  3：逾期


    private Date createDate ; // 创建时间
    private Date updateDate ; // 修改时间


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStkId() {
        return stkId;
    }

    public void setStkId(Integer stkId) {
        this.stkId = stkId;
    }

    public String getStkTitle() {
        return stkTitle;
    }

    public void setStkTitle(String stkTitle) {
        this.stkTitle = stkTitle;
    }

    public Integer getWinnerRecordId() {
        return winnerRecordId;
    }

    public void setWinnerRecordId(Integer winnerRecordId) {
        this.winnerRecordId = winnerRecordId;
    }

    public Integer getWinnerUserId() {
        return winnerUserId;
    }

    public void setWinnerUserId(Integer winnerUserId) {
        this.winnerUserId = winnerUserId;
    }

    public Integer getWinnerUserType() {
        return winnerUserType;
    }

    public void setWinnerUserType(Integer winnerUserType) {
        this.winnerUserType = winnerUserType;
    }

    public String getWinnerUserName() {
        return winnerUserName;
    }

    public void setWinnerUserName(String winnerUserName) {
        this.winnerUserName = winnerUserName;
    }


    public String getAcceptName() {
        return acceptName;
    }

    public void setAcceptName(String acceptName) {
        this.acceptName = acceptName;
    }

    public String getAcceptAddress() {
        return acceptAddress;
    }

    public void setAcceptAddress(String acceptAddress) {
        this.acceptAddress = acceptAddress;
    }

    public String getAcceptPost() {
        return acceptPost;
    }

    public void setAcceptPost(String acceptPost) {
        this.acceptPost = acceptPost;
    }

    public String getAcceptTel() {
        return acceptTel;
    }

    public void setAcceptTel(String acceptTel) {
        this.acceptTel = acceptTel;
    }


    public String getAcceptAccount() {
        return acceptAccount;
    }

    public void setAcceptAccount(String acceptAccount) {
        this.acceptAccount = acceptAccount;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getWinnerRecordNo() {
        return winnerRecordNo;
    }

    public void setWinnerRecordNo(String winnerRecordNo) {
        this.winnerRecordNo = winnerRecordNo;
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

    public Integer getSysUerType() {
        return sysUserType;
    }

    public void setSysUserType(Integer sysUserType) {
        this.sysUserType = sysUserType;
    }

    public String getWinnerRecordName() {
        return winnerRecordName;
    }

    public void setWinnerRecordName(String winnerRecordName) {
        this.winnerRecordName = winnerRecordName;
    }

    public Integer getWinnerRecordLev() {
        return winnerRecordLev;
    }

    public void setWinnerRecordLev(Integer winnerRecordLev) {
        this.winnerRecordLev = winnerRecordLev;
    }

    public Integer getWinnerRecordType() {
        return winnerRecordType;
    }

    public void setWinnerRecordType(Integer winnerRecordType) {
        this.winnerRecordType = winnerRecordType;
    }

    public Integer getSysUserType() {
        return sysUserType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


}
