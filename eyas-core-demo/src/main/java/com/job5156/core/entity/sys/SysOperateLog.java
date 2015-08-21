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

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysOperateLog implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;                  // 主键
    private Integer moduleId;            //所属模块id
    private String method;               //操作方法
    private String params;               //操作方法参数json
    private String description;          //操作描述
    private String operatorName;         //操作人姓名
    private String operatorIp;           //操作人IP
    private Integer operatorAccount;     //操作人账户id
    private Integer operatorAccountType; //操作人账户类别，0=个人，1=企业，2=系统
    private Date operateBeginDate;       //操作开始时间
    private Date operateEndDate;         //操作结束时间
    private Integer operateTime;         //操作消耗的时间
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getOperatorIp() {
		return operatorIp;
	}
	public void setOperatorIp(String operatorIp) {
		this.operatorIp = operatorIp;
	}
	public Integer getOperatorAccount() {
		return operatorAccount;
	}
	public void setOperatorAccount(Integer operatorAccount) {
		this.operatorAccount = operatorAccount;
	}
	public Integer getOperatorAccountType() {
		return operatorAccountType;
	}
	public void setOperatorAccountType(Integer operatorAccountType) {
		this.operatorAccountType = operatorAccountType;
	}
	public Date getOperateBeginDate() {
		return operateBeginDate;
	}
	public void setOperateBeginDate(Date operateBeginDate) {
		this.operateBeginDate = operateBeginDate;
	}
	public Date getOperateEndDate() {
		return operateEndDate;
	}
	public void setOperateEndDate(Date operateEndDate) {
		this.operateEndDate = operateEndDate;
	}
	public Integer getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Integer operateTime) {
		this.operateTime = operateTime;
	}
}
