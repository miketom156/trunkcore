package com.job5156.webapp.model.sys.statistics;

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
 * Created with IntelliJ IDEA.
 * User: DJH
 * Date: 14-11-3
 * Time: 下午3:51
 * 微信统计量实体类
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysWechatCount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer addAmout;
    private Integer saveAmout;
    //统计类型
    private Integer type;//1:关注者数量
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAddAmout() {
        return addAmout;
    }

    public void setAddAmout(Integer addAmout) {
        this.addAmout = addAmout;
    }

    public Integer getSaveAmout() {
        return saveAmout;
    }

    public void setSaveAmout(Integer saveAmout) {
        this.saveAmout = saveAmout;
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
}
