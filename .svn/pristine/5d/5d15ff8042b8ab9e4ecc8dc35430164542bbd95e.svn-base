package com.job5156.core.entity.sys.mail;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 邮件发送计划任务-发送邮件
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysMailPlanMails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id;     // 主键

    private Integer  mailPlanId;     // 主键

    private Integer  status;   // -1:发送失败  0:待发送  1：发送成功

    private String   emailVals;//邮件-替换模板值  以json方式存放key-value

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMailPlanId() {
        return mailPlanId;
    }

    public void setMailPlanId(Integer mailPlanId) {
        this.mailPlanId = mailPlanId;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmailVals() {
        return emailVals;
    }

    public void setEmailVals(String emailVals) {
        this.emailVals = emailVals;
    }

}
