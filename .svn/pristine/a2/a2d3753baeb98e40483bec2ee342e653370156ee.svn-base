package com.job5156.core.entity.sys;

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
 * 后台--给企业返点的实体类
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComReturnPoints implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;         // 主键
    private Date createDate ;   // 创建时间
    private Date updateDate ;   // 更新时间

    private Integer comId;
    private String comName;        //公司名
    private Integer salesId;
    private String salesName;       //业务员
    private Integer comOldPoints;   //返点前的点数
    private Integer comNewPoints;   //返点后的点数
    private Integer requestPoints;  //申请返点点数
    private Integer points;         //执行返点点数
    private Integer feedbackId;     //投诉与反馈的相关ID
    private Integer applicantId;    //申请人ID
    private String applicant;       //申请人
    private Integer auditId;        //审核人ID
    private String auditor;         //审核人
    private String auditDesc;       //审核意见描述
    private String reason;          //申请原因
    private Integer optionStatus;   //操作状态 -1:不通过 0:待审核 1:已返点

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

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public Integer getComOldPoints() {
        return comOldPoints;
    }

    public void setComOldPoints(Integer comOldPoints) {
        this.comOldPoints = comOldPoints;
    }

    public Integer getComNewPoints() {
        return comNewPoints;
    }

    public void setComNewPoints(Integer comNewPoints) {
        this.comNewPoints = comNewPoints;
    }

    public Integer getRequestPoints() {
        return requestPoints;
    }

    public void setRequestPoints(Integer requestPoints) {
        this.requestPoints = requestPoints;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditDesc() {
        return auditDesc;
    }

    public void setAuditDesc(String auditDesc) {
        this.auditDesc = auditDesc;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getOptionStatus() {
        return optionStatus;
    }

    public void setOptionStatus(Integer optionStatus) {
        this.optionStatus = optionStatus;
    }
}
