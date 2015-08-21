package com.job5156.core.entity.sys.statistics;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-8-28
 * Time: 下午4:40
 * To change this template use File | Settings | File Templates.
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysResumeFollowUp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;    //PK

    private Integer resumeId;                 //简历信息ID

    private String  userName;             //用户名

    private String  mobile;               //用户手机

    private String  phone;               //用户电话

    private Double perfectNum;            //简历完整度数

    private Integer pass;                  //-10:自杀式简历，不让登录，-9：封锁,-3：审核不合格，-1：未开通，0: 未完善，1：暂时开通，10：已开通

    private Date resumeCreateDate ;        //简历注册时间

    private Date exportDate ;             //导出创建时间

    private Integer gender;               //性别：1为男，2为女


    @Transient
    private Integer maskResumeId;                 //简历信息ID *102
    @Transient
    private Integer userId;                 //用户ID


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getPerfectNum() {
        return perfectNum;
    }

    public void setPerfectNum(Double perfectNum) {
        this.perfectNum = perfectNum;
    }

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }

    public Date getResumeCreateDate() {
        return resumeCreateDate;
    }

    public void setResumeCreateDate(Date resumeCreateDate) {
        this.resumeCreateDate = resumeCreateDate;
    }

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getMaskResumeId() {

        return resumeId==null?null:(resumeId*102);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setMaskResumeId(Integer maskResumeId) {
        this.maskResumeId = maskResumeId;
    }
}
