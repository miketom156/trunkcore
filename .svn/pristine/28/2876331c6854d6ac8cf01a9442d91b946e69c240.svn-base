package com.job5156.core.entity.com;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 后台企业作废/非法/释放操作历史
 * 
 * @author leo
 * 
 */

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysComManageHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;				//主键ID
	@NotNull(message = "{sys.comManageHistory.comId.is.notempty}")
	private Integer comId;			//企业ID
	@NotBlank(message = "{sys.comManageHistory.content.is.notempty}")
	private String content;			//内容描述
	@NotNull(message = "{sys.comManageHistory.state.is.notempty}")
	private Integer state;			//操作状态
	private Date createDate;		//创建日期
	private String createBy;		//创建人

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
}
