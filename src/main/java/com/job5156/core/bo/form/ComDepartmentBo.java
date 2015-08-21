package com.job5156.core.bo.form;

import com.job5156.core.bo.mapping.ComContactBo;
import org.hibernate.validator.constraints.NotBlank;

public class ComDepartmentBo {
	private Integer id;
	private Integer contactId;             //联系人ID
	@NotBlank
	private String deptName;               //部门名称
	private Integer sort;                  //排序号
    private Integer type;                  //类型，0=部门，1=分子公司
    private Integer sync;                  //是否同步联系人，1=是
	private ComContactBo comContactBo = new ComContactBo();     //联系人vo
	private ComSubsidaryUserBo comSubsidaryUserBo = new ComSubsidaryUserBo();        //关联用户
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getSync() {
		return sync;
	}
	public void setSync(Integer sync) {
		this.sync = sync;
	}
	public ComContactBo getComContactBo() {
		return comContactBo;
	}
	public void setComContactBo(ComContactBo comContactBo) {
		this.comContactBo = comContactBo;
	}
	public ComSubsidaryUserBo getComSubsidaryUserBo() {
		return comSubsidaryUserBo;
	}
	public void setComSubsidaryUserBo(ComSubsidaryUserBo comSubsidaryUserBo) {
		this.comSubsidaryUserBo = comSubsidaryUserBo;
	}
	
}
