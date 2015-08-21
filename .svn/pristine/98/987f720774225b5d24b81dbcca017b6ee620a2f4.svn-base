package com.job5156.core.entity.sys.subsite;

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
 * 友情链接实体
 * </p>
 * 
 * @author djh
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysFriendLink implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;				//主键ID
    private Integer subSiteInfoId;  //分站点友情链接信息ID
	private String comName;		    //公司名
	private String link;		    //对应链接
    private Integer perUserId;    //创建人id
    private String perUserName;  //创建人姓名
    private Date createDate;        //创建时间
    private Integer  isNofollow;  //1:是 ， 0:否
    private Integer  isShowIndex;  //是否在首页显示，1:是 ， 0:否
    private Integer  type;  //链接类型，详见FriendLinkTypeEnum

    public Integer getIsNofollow() {
        return isNofollow;
    }

    public void setIsNofollow(Integer nofollow) {
        isNofollow = nofollow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubSiteInfoId() {
        return subSiteInfoId;
    }

    public void setSubSiteInfoId(Integer subSiteInfoId) {
        this.subSiteInfoId = subSiteInfoId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsShowIndex() {
        return isShowIndex;
    }

    public void setIsShowIndex(Integer showIndex) {
        isShowIndex = showIndex;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
