package com.job5156.core.bo.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class ComSubsidaryUserBo {
	private Integer id;
	@NotBlank
    @Length(min = 5, max = 50)
	private String username;                        //用户名
	
	@NotBlank
    @Length(min = 6, max = 20)
	private String password;                        //密码
	private Integer pointOwn;                       //用户分配的点数-1=不限，0=不可查看
	@NotNull
	private Integer canSetPosition;                 //用户是否可以发布职位（{0:不可以;1:可以}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPointOwn() {
		return pointOwn;
	}
	public void setPointOwn(Integer pointOwn) {
		this.pointOwn = pointOwn;
	}
	public Integer getCanSetPosition() {
		return canSetPosition;
	}
	public void setCanSetPosition(Integer canSetPosition) {
		this.canSetPosition = canSetPosition;
	}
	
}
