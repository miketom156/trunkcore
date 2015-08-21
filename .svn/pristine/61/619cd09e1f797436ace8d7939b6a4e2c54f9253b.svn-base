package com.job5156.core.entity.sys.logo;

import com.google.common.collect.Lists;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <P>
 * 后台LOGO位置对象
 * </P>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysLogoPosition implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 				// LOGO位置ID
	private Integer logoTableId;		// LOGO表格ID
	@NotNull(message = "{sys.logoFile.boardSet.logoPosition.logoSizeId.is.notempty}")
	private Integer logoSizeId; 		// LOGO尺寸大小ID
	@NotBlank(message = "{sys.logoFile.boardSet.logoPosition.codeNo.is.notempty}")
	private String codeNo; 				// LOGO位置编号
	@NotNull(message = "{sys.logoFile.boardSet.logoPosition.rowNum.is.notempty}")
	private Integer rowNum;				// 行号
	@NotNull(message = "{sys.logoFile.boardSet.logoPosition.orderby.is.notempty}")
	private Integer orderBy;			// 排序
	private Integer rowspan;			// 合并行数
	private Integer colspan;			// 合并列数
	private Date createDate; 			// 创建时间
	private String createBy; 			// 创建人
	private Date editDate; 				// 修改时间
	private String editBy; 				// 修改人
	
	private SysLogoTable logoTable;		// LOGO行对应的TABLE
	private SysLogoSize logoSize;		// LOGO尺寸大小
	private List<SysLogoQueue> logoQueueList = Lists.newArrayList();   //LOGO位置排队的LOGO资源列表

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "logoPosition", cascade = {CascadeType.REMOVE})
	public List<SysLogoQueue> getLogoQueueList() {
		return logoQueueList;
	}

	public void setLogoQueueList(List<SysLogoQueue> logoQueueList) {
		this.logoQueueList = logoQueueList;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "logoTableId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public SysLogoTable getLogoTable() {
		return logoTable;
	}

	public void setLogoTable(SysLogoTable logoTable) {
		this.logoTable = logoTable;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "logoSizeId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public SysLogoSize getLogoSize() {
		return logoSize;
	}

	public void setLogoSize(SysLogoSize logoSize) {
		this.logoSize = logoSize;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLogoSizeId() {
		return logoSizeId;
	}

	public void setLogoSizeId(Integer logoSizeId) {
		this.logoSizeId = logoSizeId;
	}

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
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

	public Integer getLogoTableId() {
		return logoTableId;
	}

	public void setLogoTableId(Integer logoTableId) {
		this.logoTableId = logoTableId;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getRowspan() {
		return rowspan;
	}

	public void setRowspan(Integer rowspan) {
		this.rowspan = rowspan;
	}

	public Integer getColspan() {
		return colspan;
	}

	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}
}
