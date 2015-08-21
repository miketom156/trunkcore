package com.job5156.webapp.model.proj;

import com.job5156.core.common.sys.SysProjLexiconEnum.TaskStatusEnum;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 词库更新任务实体
 * </p>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ProjLexiconTask implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 			// 主键ID
	private String name; 			// 任务名称
	private String type; 			// 操作类型[add-新增 del-删除]
	private String words; 			// 关键词集合(用“;”分隔)
	private Integer state; 			// 执行状态[0-未执行 1-已执行]
	private Date createDate; 		// 创建日期
	private String createBy; 		// 创建人
	private Date editDate;			// 修改时间 
	private String editBy;			// 修改人
	
	private List<ProjLexiconTaskItem> itemList;		// 子任务项列表
	
	@OneToMany(mappedBy = "lexiconTask", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	public List<ProjLexiconTaskItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<ProjLexiconTaskItem> itemList) {
		this.itemList = itemList;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
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
	
	@Transient
	public String getStatusStr(){
		return TaskStatusEnum.getNameByValue(state);
	}
}
