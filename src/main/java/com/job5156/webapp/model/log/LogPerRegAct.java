package com.job5156.webapp.model.log;

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
 * Function:送话费注册记录表
 *
 * @autor:leeton
 * Date: 15-3-5
 * Time: 下午3:52
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class LogPerRegAct implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    private Integer actType; // 活动类型
    private Integer nodeId;  //上线ID
    private String nodeSpreadKey;   //上线的推广KEY
    private String nodeMobile;      //上线的手机
    private Integer perUserId;     //
    private Integer perResumeId;  //
    private String perUserName;  //
    private String mobile;      //
    private String email;       //
    private String ip;          //
    private Integer comeFrom;   //  来源CommonEnum.ComeFrom
    private Integer areaCode;   //  OptionCityMap
    private Integer siteId;   //
    private Integer mySpreadNum;     //推广的下线人数
    private Integer myResumePass;  //我的简历是否通过审核，0：否 1：是
    private Integer regNum;        //注册数
    private Integer validResumeNum;//有效的简历数（通过了审核）
    private Integer payable;       //（应付）
    private Integer paid;          //（实付）
    private Date paidDate;         //支付日期
    private String  fromUrl;     // 推广(来源)链接
    private Integer editorId;      //最后的编辑者ID
    private Integer modifyType;    //修改的原因类型  LogPerRegActVo.Modity
    private Date createDate;       //
    private Date updateDate;       //



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActType() {
        return actType;
    }

    public void setActType(Integer actType) {
        this.actType = actType;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeSpreadKey() {
        return nodeSpreadKey;
    }

    public void setNodeSpreadKey(String nodeSpreadKey) {
        this.nodeSpreadKey = nodeSpreadKey;
    }

    public String getNodeMobile() {
        return nodeMobile;
    }

    public void setNodeMobile(String nodeMobile) {
        this.nodeMobile = nodeMobile;
    }

    public Integer getPerUserId() {
        return perUserId;
    }

    public void setPerUserId(Integer perUserId) {
        this.perUserId = perUserId;
    }

    public Integer getPerResumeId() {
        return perResumeId;
    }

    public void setPerResumeId(Integer perResumeId) {
        this.perResumeId = perResumeId;
    }

    public String getPerUserName() {
        return perUserName;
    }

    public void setPerUserName(String perUserName) {
        this.perUserName = perUserName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(Integer comeFrom) {
        this.comeFrom = comeFrom;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getMySpreadNum() {
        return mySpreadNum;
    }

    public void setMySpreadNum(Integer mySpreadNum) {
        this.mySpreadNum = mySpreadNum;
    }

    public Integer getMyResumePass() {
        return myResumePass;
    }

    public void setMyResumePass(Integer myResumePass) {
        this.myResumePass = myResumePass;
    }

    public Integer getRegNum() {
        return regNum;
    }

    public void setRegNum(Integer regNum) {
        this.regNum = regNum;
    }

    public Integer getValidResumeNum() {
        return validResumeNum;
    }

    public void setValidResumeNum(Integer validResumeNum) {
        this.validResumeNum = validResumeNum;
    }

    public Integer getPayable() {
        return payable;
    }

    public void setPayable(Integer payable) {
        this.payable = payable;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public Integer getEditorId() {
        return editorId;
    }

    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }

    public Integer getModifyType() {
        return modifyType;
    }

    public void setModifyType(Integer modifyType) {
        this.modifyType = modifyType;
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
}
