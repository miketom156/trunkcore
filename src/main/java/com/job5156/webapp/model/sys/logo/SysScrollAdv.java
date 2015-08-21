package com.job5156.webapp.model.sys.logo;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: DJH
 * Date: 14-7-8
 * Time: 下午4:55
 * 切屏广告主表实体
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysScrollAdv implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;				//主键ID
    private String name; //广告名称
    private String description; //描述
    private String siteName; //所属站点名称
    private Integer siteId;//所属站点ID
    private Integer width; //图片宽度
    private Integer height;//图片高度
    private String siteIds;//所属站点[多个]ID --json array方式保存
    private String siteNames;//所属站点[多个]名称 --json array方式保存


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

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getSiteIds() {
        return siteIds;
    }

    public void setSiteIds(String siteIds) {
        this.siteIds = siteIds;
    }

    public String getSiteNames() {
        return siteNames;
    }

    public void setSiteNames(String siteNames) {
        this.siteNames = siteNames;
    }

    public String  getSiteNamesShow(){
        if(!StringUtils.isEmpty(siteNames)){
            List<String> names=new Gson().fromJson(siteNames,new TypeToken<List<String>>(){}.getType());
            return   StringUtils.join(names,",");
        }
        return "";
    }
    public String  getSiteIdsShow(){
        if(!StringUtils.isEmpty(siteIds)){
            List<String> names=new Gson().fromJson(siteIds,new TypeToken<List<String>>(){}.getType());
            return   StringUtils.join(names,",");
        }
        return "";
    }
}
