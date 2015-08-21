package com.job5156.webapp.model.sys.statistics;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 
* 统计栏目设置实体
* @author xh 
* @date 2014-4-14 上午11:13:30 
*
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysCountItemSetting implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String itemName;          //栏目名称
    @Transient
	private Integer itemType;          //栏目类型
    private Integer itemTypeNo;                //栏目类型（real）
	private String itemUrl;           //栏目URL(json格式)
	private String itemCategory;      //栏目类别(json格式)
	private Integer itemMode;		  //统计方式(0-推广参数统计  1-直接访问统计，2-sem推广参数统计.默认为1)
	private String tn;                //统计编号
	private Date beginDate;           //开始日期
	private Date endDate;             //结束日期
	private Date createDate;          //创建日期
	private Date updateDate;          //修改日期
	private String updateUserName;    //修改人
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getItemUrl() {
		return itemUrl;
	}
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public String getTn() {
		return tn;
	}
	public void setTn(String tn) {
		this.tn = tn;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	public Integer getItemMode() {
		return itemMode;
	}
	public void setItemMode(Integer itemMode) {
		this.itemMode = itemMode;
	}

    public Integer getItemTypeNo() {
        return itemTypeNo;
    }

    public void setItemTypeNo(Integer itemTypeNo) {
        this.itemTypeNo = itemTypeNo;
    }
}
