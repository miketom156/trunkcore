package com.job5156.webapp.model.sys.manage;

import com.job5156.core.common.CommonEnum.ResumeStatus;
import com.job5156.webapp.model.per.PerResume;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysResumeAuditHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer resumeId;
	private Integer historyId;
	private Integer operatorId;
	private String operatorName;
	private Integer pass;
	private Date auditDate;
	private String auditInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resumeId", insertable = false, updatable = false)
	private PerResume perResume;
	
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
	public Integer getHistoryId() {
		return historyId;
	}
	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Integer getPass() {
		return pass;
	}
	public void setPass(Integer pass) {
		this.pass = pass;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public String getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
	}
	public PerResume getPerResume() {
		return perResume;
	}
	public void setPerResume(PerResume perResume) {
		this.perResume = perResume;
	}
	
	//获取审核状态信息
	public String getResumeStatus() {
		return ResumeStatus.getResumeStatusForValue(pass).toString();
	}
}
