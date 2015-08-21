package com.job5156.core.bo.mapping;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ComContactBaseBo {
	@NotBlank
    private String contactPerson;  // 联系人
    private Integer gender;        // 0未知 1先生 2女士
    private String contactPhone;   // 联系电话
    private Integer hidePhone;     // 1为不公开，其他为公开
    private String mobile;         // 手机号
    private Integer mobileAlert;   // 收到简历提醒，1提醒
    private Integer hideMobile;    // 1为不公开，其他为公开
    private String fax;            // 传真
    private Integer hideFax;       // 1为不公开，其他为公开
    @NotNull
    private List<String> emailList;// 多个邮箱
    private Integer hideEmail;     // 1为不公开，其他为公开
    private Integer rejectEmail;   // 1为拒收邮件，其他为收邮件
    private Integer emailFormat;   // 0简体 1繁体,
    private Integer location;      // 所在地区（城市）
    @NotNull
    private AddressBo addressBo;   // 通讯地址
    private AddressBo interviewAddressBo;  // 面试地址
    private String busLine;                // 乘车路线
    private String nearStation;            // 附近站点
    private String zipCode;                // 邮编
    private String website;                // 公司网址
    private Double lat;                    // 经度
    private Double lng;                    // 纬度
    
    public ComContactBaseBo() {
     	addressBo = new AddressBo();
    	interviewAddressBo = new AddressBo();
    }
    
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public Integer getHidePhone() {
		return hidePhone;
	}
	public void setHidePhone(Integer hidePhone) {
		this.hidePhone = hidePhone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getMobileAlert() {
		return mobileAlert;
	}
	public void setMobileAlert(Integer mobileAlert) {
		this.mobileAlert = mobileAlert;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public Integer getHideFax() {
		return hideFax;
	}
	public void setHideFax(Integer hideFax) {
		this.hideFax = hideFax;
	}
	public List<String> getEmailList() {
		return emailList;
	}
	public void setEmailList(List<String> emailList) {
		this.emailList = emailList;
	}
	public Integer getHideEmail() {
		return hideEmail;
	}
	public void setHideEmail(Integer hideEmail) {
		this.hideEmail = hideEmail;
	}

    public Integer getRejectEmail() {
        return rejectEmail;
    }

    public void setRejectEmail(Integer rejectEmail) {
        this.rejectEmail = rejectEmail;
    }

    public Integer getEmailFormat() {
		return emailFormat;
	}
	public void setEmailFormat(Integer emailFormat) {
		this.emailFormat = emailFormat;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	public AddressBo getAddressBo() {
		return addressBo;
	}
	public void setAddressBo(AddressBo addressBo) {
		this.addressBo = addressBo;
	}
	public AddressBo getInterviewAddressBo() {
		return interviewAddressBo;
	}
	public void setInterviewAddressBo(AddressBo interviewAddressBo) {
		this.interviewAddressBo = interviewAddressBo;
	}
	public String getBusLine() {
		return busLine;
	}
	public void setBusLine(String busLine) {
		this.busLine = busLine;
	}
	public String getNearStation() {
		return nearStation;
	}
	public void setNearStation(String nearStation) {
		this.nearStation = nearStation;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}

    public Integer getHideMobile() {
        return hideMobile;
    }

    public void setHideMobile(Integer hideMobile) {
        this.hideMobile = hideMobile;
    }
}
