package com.job5156.core.entity.act;

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
public class ActAward implements Serializable,Comparable   {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id;      // 主键
    private Date createDate ; // 创建时间
    private Date updateDate ; // 修改时间
    private  Integer stkId;// 本次活动ID
    private  String name;//  奖品名称
    private  Integer lev;//  奖品级别 0：特等奖，1：一等奖,...
    private  Integer qty ;// 奖品数量
    private  Integer usedQty ;// 已被抽中数量
    private  Integer type ;// 奖品类型   0：实物奖  1：虚拟奖  2:增值奖
    private Date activeDate ; // 有效日期

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

    public Integer getStkId() {
        return stkId;
    }

    public void setStkId(Integer stkId) {
        this.stkId = stkId;
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


    public Integer getLev() {
        return lev;
    }

    public void setLev(Integer lev) {
        this.lev = lev;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUsedQty() {
        return usedQty;
    }

    public void setUsedQty(Integer usedQty) {
        this.usedQty = usedQty;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
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

    @Override
    public int compareTo(Object o) {
        if(this.getLev()>((ActAward )o).getLev())  {
                return  1;
        } else if(this.getLev()==((ActAward )o).getLev())  {
            return  0;
        } else {
            return   -1;
        }
    }
}
