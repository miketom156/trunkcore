package com.job5156.webapp.model.sys.mail;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 邮件发送计划任务
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysMailPlan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id;     // 主键

    private Integer status; //邮件状态 -2:作废 -1:未生效   0：待发送  1：正在发送  2：发送完毕
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;  //开始发送时间
    private String mailTitle; //邮件标题
    private String fromAddr; //发送邮件地址
    private String fromName; //发送邮件名称
    private Integer sendType; //  0:商业邮件发送   1：主邮件发送
    private String content;  //邮件内容
    private String countLog; // 统计日志
    private String dataSql;  //数据SQL

    private Integer campaignId ;   //商业邮件--活动ID
    private Integer mailingId;   //商业邮件--邮件ID
    private Integer groupId ;     //商业邮件--组ID

    private Date createDate ; // 创建时间
    private Date updateDate ; // 修改时间

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date starTime) {
        this.startTime = starTime;
    }

    public String getMailTitle() {
        return mailTitle;
    }

    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }

    public String getFromAddr() {
        return fromAddr;
    }

    public void setFromAddr(String fromAddr) {
        this.fromAddr = fromAddr;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendTpye) {
        this.sendType = sendTpye;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCountLog() {
        return countLog;
    }

    public void setCountLog(String countLog) {
        this.countLog = countLog;
    }

    public String getDataSql() {
        return dataSql;
    }

    public void setDataSql(String dataSql) {
        this.dataSql = dataSql;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public Integer getMailingId() {
        return mailingId;
    }

    public void setMailingId(Integer mailingId) {
        this.mailingId = mailingId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

}
