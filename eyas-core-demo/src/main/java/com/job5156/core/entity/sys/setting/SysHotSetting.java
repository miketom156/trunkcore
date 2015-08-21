package com.job5156.core.entity.sys.setting;

import com.job5156.core.entity.sys.subsite.SysWebSite;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysHotSetting implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String createMan;//创建人
	private Date createDate;//创建日期
	private String settingInfo;//热门设置信息json
	private String staticContent;//生成的静态文件内容
	private Integer type;//热门类型，1=热门职位，2=热门类别
	private Integer websiteId;//所属分站点
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name = "websiteId", insertable = false, updatable = false)
	private SysWebSite sysWebSite;                                //所属分站点
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getSettingInfo() {
		return settingInfo;
	}
	public void setSettingInfo(String settingInfo) {
		this.settingInfo = settingInfo;
	}
	public String getStaticContent() {
		return staticContent;
	}
	public void setStaticContent(String staticContent) {
		this.staticContent = staticContent;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getWebsiteId() {
		return websiteId;
	}
	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}
	public SysWebSite getSysWebSite() {
		return sysWebSite;
	}
	public void setSysWebSite(SysWebSite sysWebSite) {
		this.sysWebSite = sysWebSite;
	}
}
