package com.job5156.webapp.model.proj;

import com.job5156.core.common.sys.SysProjLexiconEnum.TaskItemStatusEnum;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 词库更新任务项
 * </p>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ProjLexiconTaskItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 				// 主键ID
	@NotNull(message = "{lexiconTaskItem.taskId.is.notempty}")
	private Integer taskId; 			// 任务ID
	@NotBlank(message = "{lexiconTaskItem.host.is.notempty}")
	private String host; 				// 主机IP
	@NotBlank(message = "{lexiconTaskItem.oper.is.notempty}")
	private String oper;				// 操作[add, del]
	@NotBlank(message = "{lexiconTaskItem.callType.is.notempty}")
	private String callType; 			// 接口调用类型[ejb, api]
	private Integer state; 				// 执行状态[0-未执行 1-执行成功 2-执行失败]
	private String report; 				// 接口执行报告
	private Date createDate; 			// 创建日期
	private String createBy; 			// 创建人
	private Date executeDate; 			// 执行日期
	private String executeBy; 			// 执行人
	private Date editDate; 				// 修改日期
	private String editBy; 				// 修改人
	
	private ProjLexiconTask lexiconTask;	// 词库更新任务
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "taskId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public ProjLexiconTask getLexiconTask() {
		return lexiconTask;
	}

	public void setLexiconTask(ProjLexiconTask lexiconTask) {
		this.lexiconTask = lexiconTask;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}

	public String getExecuteBy() {
		return executeBy;
	}

	public void setExecuteBy(String executeBy) {
		this.executeBy = executeBy;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public String getEditBy() {
		return editBy;
	}

	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}
	
	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	@Transient
	public String getStatusStr(){
		return TaskItemStatusEnum.getNameByValue(state);
	}
}
