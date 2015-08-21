package com.job5156.webapp.model.com.vas;

import com.job5156.core.common.option.BaseOption;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>企业服务项类型</p>
 * Date: 2015/6/1 9:10
 *
 * @version 1.0
 * @autor: Lu Weibiao
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComServiceItemType implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String key;//服务类型key，唯一性标识一种类型。
    private String name;//名称
    private String description;//描述
    private String creator;//后台创建人
    private Date createDate;//创建时间
    private String editor;//后台修改人
    private Date editDate;//修改时间


    public static class KEY{
        public final static BaseOption<String,String> RESUME_VIEW = new BaseOption<>("resumeView","简历查看点数");
        public final static BaseOption<String,String> POS_REFRESH = new BaseOption("posRefresh","职位刷新点数");
        public final static BaseOption<String,String> SMS = new BaseOption("sms","短信服务");
        /** “套餐”类型在涉及处理订单的服务类型时才使用到，ComServiceItemType库表不会写入该值 */
        public static final BaseOption<String, String> PACKAGE = new BaseOption<>("package","套餐");

        public final static Map<String,String> optionMap = new LinkedHashMap<>();

        static {
            optionMap.put(RESUME_VIEW.code,RESUME_VIEW.name);
            optionMap.put(POS_REFRESH.code,POS_REFRESH.name);
            optionMap.put(SMS.code,SMS.name);
            optionMap.put(PACKAGE.code, PACKAGE.name);
        }
        private KEY(){}
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }


}
