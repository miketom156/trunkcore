package com.job5156.webapp.model.proj;

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
 * <p>
 * 集团官网,联系方式实体
 * </p>
 *
 * Date: 2015/5/5 下午13:44
 * @autor:赵满
 * @version：1.0
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ProjOfficialSiteContact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 联系电话
     */
     private String phone;

    /**
     * 公司地址（详细）
     */
     private String  address;

    /**
     * 所属省
     */
     private Integer provinceCode;

    /**
     * 所属市
     */
     private Integer cityCode;
     
     /**
      * 所属事业部
      */
     private Integer department;

    /**
     * 服务项目
     */
     private String serviceProj;

    /**
     * 默认排序
     */
    private Integer sort;

    /**
     * 操作人
     * @return
     */
    private String editUser;
    
    /**
     * 操作时间
     * @return
     */
    private Date editDate;

    public Integer getId() { return id; }

    public String getName() { return name; }

    public String getPhone() { return phone; }

    public String getAddress() { return address; }

    public Integer getProvinceCode() { return provinceCode; }

    public Integer getCityCode() { return cityCode; }

    public String getServiceProj() { return serviceProj; }

    public Integer getSort() { return sort; }

    public String getEditUser() { return editUser; }

    public void setId(Integer id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setPhone(String phone) { this.phone = phone; }

    public void setAddress(String address) { this.address = address; }

    public void setProvinceCode(Integer provinceCode) { this.provinceCode = provinceCode; }

    public void setCityCode(Integer cityCode) { this.cityCode = cityCode; }

    public void setServiceProj(String serviceProj) { this.serviceProj = serviceProj; }

    public void setSort(Integer sort) { this.sort = sort; }

    public void setEditUser(String editUser) { this.editUser = editUser; }

	public Integer getDepartment() { return department;	}

	public void setDepartment(Integer department) {	this.department = department;	}

	public Date getEditDate() {	return editDate;	}

	public void setEditDate(Date editDate) { this.editDate = editDate;	}

}
