package com.job5156.core.entity.sys.special;

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
 * 后台网站的营销专题实体对象
 * 对应旧版的sys_spSubject表
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysSpecialSubject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer spId;     //父节点
    private String spName;    //专题名称
    private String spPath;    //公司简介(图片上传路径)   媒体报道(时间)  热招职位(岗位类别)
    private String spUrl;     //公司简介(描述) 媒体报道(报纸名)  热招职位(无)
    private String editor;    //
    private String spDesc;    //公司简介(描述)			媒体报道(报纸名)		  热招职位(无)
    private String spOther;   //公司简介(无)	 媒体报道(新闻标题)  热招职位(岗位描述)
    private Integer spSort;
    private Integer category; //类别   1:参会企业 2：热招职位 3：媒体报道 4:企业名+职位 5：资讯类
    private Integer type;     //生成类型   1：纯文本  2：纯图片 3：图片加文本
    private Date createDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getSpPath() {
        return spPath;
    }

    public void setSpPath(String spPath) {
        this.spPath = spPath;
    }

    public String getSpUrl() {
        return spUrl;
    }

    public void setSpUrl(String spUrl) {
        this.spUrl = spUrl;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getSpOther() {
        return spOther;
    }

    public void setSpOther(String spOther) {
        this.spOther = spOther;
    }

    public Integer getSpSort() {
        return spSort;
    }

    public void setSpSort(Integer spSort) {
        this.spSort = spSort;
    }

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSpDesc() {
        return spDesc;
    }

    public void setSpDesc(String spDesc) {
        this.spDesc = spDesc;
    }
}
