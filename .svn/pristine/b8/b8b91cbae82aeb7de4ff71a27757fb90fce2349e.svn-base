package com.job5156.core.entity.per;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class PerSendout  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;//外发简历主键ID
	private Integer perUserId;//个人用户账号ID
	private String resumeName;//简历名称
	private String outEmail;//外发邮箱地址
	private String comName;//企业名称
	private String posName;//职位名称
	private String remark;//其他要求/说明
	private Integer emailFormat;//邮件格式【0简体1繁体】
	private Date creDate;//外发时间
    private Integer delStatus;// 删除状态 {-1 已删除}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

    public Integer getPerUserId() {
        return perUserId;
    }

    public void setPerUserId(Integer perUserId) {
        this.perUserId = perUserId;
    }

    public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public String getOutEmail() {
		return outEmail;
	}
	public void setOutEmail(String outEmail) {
		this.outEmail = outEmail;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getPosName() {
		return posName;
	}
	public void setPosName(String posName) {
		this.posName = posName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getEmailFormat() {
		return emailFormat;
	}
	public void setEmailFormat(Integer emailFormat) {
		this.emailFormat = emailFormat;
	}
	public Date getCreDate() {
		return creDate;
	}
	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}
    public Integer getDelStatus() {
        return delStatus;
    }
    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }
}
