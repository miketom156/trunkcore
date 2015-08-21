package com.job5156.core.entity.sys.audit;

import com.job5156.core.entity.com.ComInfo;
import com.job5156.core.entity.com.ComPosition;
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
public class SysPositionAuditHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer comId;
	private Integer posId;
	private Integer operatorId;
	private String operatorName;
	private Integer auditType;
	private Date auditDate;
	private String auditInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comId", insertable = false, updatable = false)
	private ComInfo comInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posId", insertable = false, updatable = false)
	private ComPosition comPosition;
	
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
	public Integer getPosId() {
		return posId;
	}
	public void setPosId(Integer posId) {
		this.posId = posId;
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
	public Integer getAuditType() {
		return auditType;
	}
	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
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
	public ComPosition getComPosition() {
		return comPosition;
	}
	public void setComPosition(ComPosition comPosition) {
		this.comPosition = comPosition;
	}
	public ComInfo getComInfo() {
		return comInfo;
	}
	public void setComInfo(ComInfo comInfo) {
		this.comInfo = comInfo;
	}
}
