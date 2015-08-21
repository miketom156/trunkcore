package com.job5156.webapp.model.per;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class PerResumeRefresh implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer userId;           //用户id
	private Date refreshTime;        //刷新时间
	private String fromSourceUrl;   //外站来源地址
	private String spreadItemTn;   //推广栏目编号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Date refreshTime) {
        this.refreshTime = refreshTime;
    }

    public String getFromSourceUrl() {
        return fromSourceUrl;
    }

    public void setFromSourceUrl(String fromSourceUrl) {
        this.fromSourceUrl = fromSourceUrl;
    }

    public String getSpreadItemTn() {
        return spreadItemTn;
    }

    public void setSpreadItemTn(String spreadItemTn) {
        this.spreadItemTn = spreadItemTn;
    }
}
