package com.job5156.webapp.model.sys.logo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <P>
 * 后台logo尺寸
 * </P>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysLogoSize implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 		// 记录ID
	@NotNull(message = "{sys.logoSize.length.is.notempty}")
	private Integer length; 	// LOGO长度
	@NotNull(message = "{sys.logoSize.width.is.notempty}")
	private Integer width;	 	// LOGO宽度
	private Integer state; 		// 使用状态[1-启用 0-停用]
	private String descript;	// 描述

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
}
