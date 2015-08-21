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
 * Function:咨询反馈实体
 *
 * @autor:赵满 Date: 2015/5/12 上午: 10:17
 *
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ProjOfficialSiteBusiness implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 关联业务名称
     */
    private String name;

    /**
     * 联络人
     */
    private String contactPerson;

    /**
     * outlook邮箱
     */
    private String outlookMail;

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
    
    public String getOutlookMail() {
        return outlookMail;
    }

    public void setOutlookMail(String outlookMail) {
        this.outlookMail = outlookMail;
    }

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

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    
	public Date getEditDate() {	
		return editDate;	
	}

	public void setEditDate(Date editDate) { 
		this.editDate = editDate;
	}

	public String getEditUser() {
		return editUser;
	}

	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}
	
	
}
