package com.job5156.core.bo;

import java.util.Date;

/**
 * <p>
 * 用户表单封装类
 * </p>
 * 
 * Date:2015/4/8 14:20
 * 
 * @author pzm
 * @version 1.0
 */

public class PerUserInfoFormBoTemplate {
	/**
	 * 个人用户简历名称
	 */
	private String userName;
	/**
	 * 性别：1为男，2为女
	 */
	private Integer gender;
	/**
	 * 出生日期
	 */
	private Date birthday;
	/**
	 * 所在地省/市所对应的 编号
	 */
	private Integer location;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 工作年限类型
	 */
	private Integer jobyearType;
	/**
	 * 身份证
	 */
	private String cardNum;
	/**
	 * 默认简历ID
	 */
	private Integer resId;
	/**
	 * 最高学历
	 */
	private Integer degree;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getJobyearType() {
		return jobyearType;
	}

	public void setJobyearType(Integer jobyearType) {
		this.jobyearType = jobyearType;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	   // ========= SUB CLASS =================
    public static class FilterComIdBo{
        private Integer id;              //企业id
        private String name;             //企业名称
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
    }
	
	
}
