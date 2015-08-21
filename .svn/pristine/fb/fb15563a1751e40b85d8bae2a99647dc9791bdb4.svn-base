package com.job5156.core.entity.sys.statistics;

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
 * Created with IntelliJ IDEA.
 * User: djh
 * Date: 14-11-13
 * Time: 下午3:30
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class CountAccessModeDaily implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer deviceAccessCount;               //设备访问数
    private Integer allAccessCount;                  //访问记录总数
    private Integer resumeAddApiCallCount;           //简历注册接口调用数
    private Integer resumeRefreshApiCallCount;       //简历刷新接口调用数量
    private Integer applicantApiCallCount;           //应聘接口调用数量
    private Integer applicantApiCallPerCount;        //应聘接口调用的个人数量
    private Integer perLoginCount;                   //登录的个人数量
    private Date countDate;                          //统计日期
    private String apiCallType;                      //接口调用类型，mobile，wechat
    private String hardware;                         //硬件类型，android，iphone
    private String osversion;                        //系统版本

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceAccessCount() {
        return deviceAccessCount;
    }

    public void setDeviceAccessCount(Integer deviceAccessCount) {
        this.deviceAccessCount = deviceAccessCount;
    }

    public Integer getAllAccessCount() {
        return allAccessCount;
    }

    public void setAllAccessCount(Integer allAccessCount) {
        this.allAccessCount = allAccessCount;
    }

    public Integer getResumeAddApiCallCount() {
        return resumeAddApiCallCount;
    }

    public void setResumeAddApiCallCount(Integer resumeAddApiCallCount) {
        this.resumeAddApiCallCount = resumeAddApiCallCount;
    }

    public Integer getResumeRefreshApiCallCount() {
        return resumeRefreshApiCallCount;
    }

    public void setResumeRefreshApiCallCount(Integer resumeRefreshApiCallCount) {
        this.resumeRefreshApiCallCount = resumeRefreshApiCallCount;
    }

    public Integer getApplicantApiCallCount() {
        return applicantApiCallCount;
    }

    public void setApplicantApiCallCount(Integer applicantApiCallCount) {
        this.applicantApiCallCount = applicantApiCallCount;
    }

    public Integer getApplicantApiCallPerCount() {
        return applicantApiCallPerCount;
    }

    public void setApplicantApiCallPerCount(Integer applicantApiCallPerCount) {
        this.applicantApiCallPerCount = applicantApiCallPerCount;
    }

    public Integer getPerLoginCount() {
        return perLoginCount;
    }

    public void setPerLoginCount(Integer perLoginCount) {
        this.perLoginCount = perLoginCount;
    }

    public Date getCountDate() {
        return countDate;
    }

    public void setCountDate(Date countDate) {
        this.countDate = countDate;
    }

    public String getApiCallType() {
        return apiCallType;
    }

    public void setApiCallType(String apiCallType) {
        this.apiCallType = apiCallType;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }
}

