package com.job5156.core.entity.per;

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
public class PerLetter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;                   //求职信id
	private Integer userId;               //账号id
	private String title;                 //求职信名称
	private String jobLetter;             //求职信内容
	private Date updateTime;              //修改时间
	private Integer defaultLetter;        //默认求职信：1为默认，0为不是默认；
	private Integer sign;                 //求职信标识

	public PerLetter(){
		defaultLetter = 0;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getJobLetter() {
		return jobLetter;
	}
	public void setJobLetter(String jobLetter) {
		this.jobLetter = jobLetter;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getDefaultLetter() {
		return defaultLetter;
	}
	public void setDefaultLetter(Integer defaultLetter) {
		this.defaultLetter = defaultLetter;
	}
	public Integer getSign() {
		return sign;
	}
	public void setSign(Integer sign) {
		this.sign = sign;
	}
}
