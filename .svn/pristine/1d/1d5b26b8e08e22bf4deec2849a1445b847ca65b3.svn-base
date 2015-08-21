package com.job5156.webapp.model.act;

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
 * 抽奖活动-奖品
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ActPrize implements Serializable   {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id;      // 主键
    private Date createDate ; // 创建时间
    private Date updateDate ; // 修改时间
    private  String name;//  奖品名称
    private  Integer type ;// 奖品类型 0：实物奖  1：虚拟奖  2:增值奖

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getTypeName(){
        if(type==null){
            return  "";
        }
        if (type==0){
            return  "实物奖";
        }else    if (type==1){
            return  "虚拟奖";
        }   else    if (type==2){
            return  "增值奖";
        }else{
            return  "";
        }
    }
}
