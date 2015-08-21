package com.job5156.webapp.model.proj;

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
 * Function:咨询反馈实体
 *
 * @autor:赵满 Date: 2015/5/12 上午: 10:17
 *
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ProjOfficialSiteFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 信息类型
     */
    private String type;

    /**
     * 反馈人
     */
    private String feedbackPerson;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 关联业务
     */
    private Integer businessId;

    /**
     * 用户留言
     */
    private String leaveMessage;

    /**
     *反馈时间
     */
    private Date feedbackDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFeedbackPerson() {
        return feedbackPerson;
    }

    public void setFeedbackPerson(String feedbackPerson) {
        this.feedbackPerson = feedbackPerson;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getLeaveMessage() {
        return leaveMessage;
    }

    public void setLeaveMessage(String leaveMessage) {
        this.leaveMessage = leaveMessage;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}
