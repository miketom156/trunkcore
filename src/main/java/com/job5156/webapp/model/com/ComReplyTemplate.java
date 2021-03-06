package com.job5156.webapp.model.com;

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
 * 
 * @ClassName: ComPositionTemplate
 * @Description: 简历回复模板实体类
 * @author lqf & dyg
 * @date 2013-7-15
 * 
 */

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComReplyTemplate implements Serializable {
	private static final long serialVersionUID = 1L ;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;

	private Integer comId ;           // 公司ID
	private Integer msgType ;         // 信息类别(1:email 2:手机短信)
	private Integer templateType ;    // 模板(信息)类型(1:到公司面试，2：到场馆面试 3：视频面试 4：侍考虑 5：婉拒 6:自动回复）
	private String templateName ;     // 模板名称
	private String content ;          // 模板内容
	private Date createDate ;         // 创建日期
	private Date updateDate ;         // 修改日期
	private String interviewNote;     //面试须知

	// =============================

	public Integer getId() {
		return id ;
	}

	public void setId(Integer id) {
		this.id = id ;
	}

	public Integer getComId() {
		return comId ;
	}

	public void setComId(Integer comId) {
		this.comId = comId ;
	}

	public Integer getMsgType() {
		return msgType ;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType ;
	}

	public Integer getTemplateType() {
		return templateType ;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType ;
	}

	public String getTemplateName() {
		return templateName ;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName ;
	}

	public String getContent() {
		return content ;
	}

	public void setContent(String content) {
		this.content = content ;
	}

	public Date getCreateDate() {
		return createDate ;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate ;
	}

	public Date getUpdateDate() {
		return updateDate ;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getInterviewNote() {
		return interviewNote;
	}

	public void setInterviewNote(String interviewNote) {
		this.interviewNote = interviewNote;
	}

}
