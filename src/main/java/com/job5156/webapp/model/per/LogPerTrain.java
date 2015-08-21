package com.job5156.webapp.model.per;

import java.io.Serializable;
import java.util.Date;

public class LogPerTrain implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;//ID

	private Date createDate;//点击日期
	
	private String course;//意向学习项目
	
	private Integer perUserId;//用户id
	
	private String username;
	
	private Integer gender;//性别：1为男，2为女
	
	private Date birthday;//生日
	
	private Integer degree;//学历 
	
	private String speciality;//专业名称
	
	private String mobile;//手机号码
	
	private String email; //电子邮箱
	
	private String qq;//QQ
	
	private Integer location;//所在地省/市所对应的 编号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPerUserId() {
		return perUserId;
	}

	public void setPerUserId(Integer perUserId) {
		this.perUserId = perUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}
	
	

}
