package com.job5156.core.entity.sys.customer;

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
 * 客户对服务的反馈
 * User: Administrator
 * Date: 14-6-13
 * Time: 上午10:58
 */

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysCustomerFeedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**
     * ID
     */
    private Integer id;
    /**
     *  企业id
     */
    private Integer comId;
    /**
     *   用户id
     */
    private Integer sysUserId;
    /**
     *  服务反馈值
     */
    private Integer feedbackValue;
    /**
     * 反馈日期
     */
    private Date feedbackDate;
    /**
     * 反馈类型    1:对顾问服务的反馈   2：后续待定    3:...
     */
    private Integer feedbackType;
    /**
     *创建日期
     */
    private Date createDate;
    /**
     *修改日期
     */
    private Date updateDate;

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

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Integer getFeedbackValue() {
        return feedbackValue;
    }

    public void setFeedbackValue(Integer feedbackValue) {
        this.feedbackValue = feedbackValue;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public Integer getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(Integer feedbackType) {
        this.feedbackType = feedbackType;
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
