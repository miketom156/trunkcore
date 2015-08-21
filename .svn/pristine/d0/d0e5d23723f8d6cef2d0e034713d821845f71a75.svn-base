package com.job5156.webapp.model.sys.subsite;

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
 * 网站分站点友情链接信息实体
 * </p>
 * 
 * @author djh
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysSubSiteFriendLinkInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;				//主键ID
	private String fileName;		//文件名
	private String description;		//描述
	private Integer siteId;		    //对应分站点的id
	private String siteName;		//对应分站点的名称
    private Integer perUserId;    //创建人id
    private String perUserName;  //创建人姓名
    private Date createDate;        //创建时间
    private String qq;//联系qq
    private Integer style;	//友情链接显示样式 1.分站点首页友情链接 2.友情链接内页链接  3.首页友情超链接的对应页面链接4.世纪智通友情超链接的对应页面链接

    public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getPerUserId() {
        return perUserId;
    }

    public void setPerUserId(Integer perUserId) {
        this.perUserId = perUserId;
    }

    public String getPerUserName() {
        return perUserName;
    }

    public void setPerUserName(String perUserName) {
        this.perUserName = perUserName;
    }

}
