package com.job5156.webapp.model.com;

import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: ComDepartment 
* @Description: 公司部门实体类 
* @author xh 
* @date 2013-7-2 上午9:48:53 
*
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComDepartment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	private Integer comId;                 //公司ID
	private Integer contactId;             //联系人ID
	private String deptName;               //部门名称
	private Integer sort;                  //排序号
	private Integer type;                  //类型，0=部门，1=分子公司
	private Integer delStatus;             //是否为删除状态，-1=已删除
	private Date createDate;               //创建日期
	private Date updateDate;               //修复日期
	//关联对象
	@ManyToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name = "comId", insertable = false, updatable = false)
	private ComInfo comInfo;     //相关企业
	@ManyToOne(fetch = FetchType.LAZY)    
	@NotFound(action=NotFoundAction.IGNORE)
    @JoinColumn(name = "contactId", insertable = false, updatable = false)
	private ComContact comContact;  //相关联系人
	
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
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
    public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	public ComInfo getComInfo() {
        return comInfo;
    }

    public void setComInfo(ComInfo comInfo) {
        this.comInfo = comInfo;
    }

    public ComContact getComContact() {
        return comContact;
    }

    public void setComContact(ComContact comContact) {
        this.comContact = comContact;
    }
}
