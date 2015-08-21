package com.job5156.webapp.model.easy;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class EasyRegister implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; // 主键
	private String customType; // com、per
	/**
	1 - 社保服务
    2 - 公积金服务
    3 - 代发工资
    4 - 社保开户
    5 - 公积金开户
    6 - 工会组建	
    7 - 劳动年审
    8 - 劳动风险管理	
    9 - 智事易在线
	 */
	private String requestItem; // 服务项目
	private String customName; // 企业全称、客户姓名
	private String customAddress; // 企业公司地址
	private String customContactPerson; // 企业联系人姓名、客户姓名
	private String customContactPhone; // 联系电话
	private String requestDetail; // 服务需求说明

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomType() {
		return customType;
	}

	public void setCustomType(String customType) {
		this.customType = customType;
	}

	public String getRequestItem() {
		return requestItem;
	}

	public void setRequestItem(String requestItem) {
		this.requestItem = requestItem;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getCustomAddress() {
		return customAddress;
	}

	public void setCustomAddress(String customAddress) {
		this.customAddress = customAddress;
	}

	public String getCustomContactPerson() {
		return customContactPerson;
	}

	public void setCustomContactPerson(String customContactPerson) {
		this.customContactPerson = customContactPerson;
	}

	public String getCustomContactPhone() {
		return customContactPhone;
	}

	public void setCustomContactPhone(String customContactPhone) {
		this.customContactPhone = customContactPhone;
	}

	public String getRequestDetail() {
		return requestDetail;
	}

	public void setRequestDetail(String requestDetail) {
		this.requestDetail = requestDetail;
	}
}
