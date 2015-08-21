package com.job5156.core.entity.per.job;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class PerViewLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer           id;
    private Integer           comId;                // 公司ID
    private Integer           perUserId;            // 个人账号ID
    private Integer           lookNum;              // 查看次数
    private Date              creTime;              // 创建记录时间（查看简历时间）
    private Date              editTime;             // 修改时间
    private Integer           lookStatus;           // 查看状态 {0 未查看联系方式； 1 已查看联系方式}
    private Integer           delStatus;            // 删除状态 {-1 已删除}
    private Integer           mark;                 // 旗子标记 1:适合，2:可考虑，3：不合适   后期扩充 增加两种状态：  null 为未读 ， 9为已读
    private String            remark;               // 备注内容
    private String            comName;              // 公司名称
    private String            comInfo;              // 公司简介
    private String mixInfo; //综合信息

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getPerUserId() {
        return perUserId;
    }

    public void setPerUserId(Integer perUserId) {
        this.perUserId = perUserId;
    }

    public Integer getLookNum() {
        return lookNum;
    }

    public void setLookNum(Integer lookNum) {
        this.lookNum = lookNum;
    }

    public Date getCreTime() {
        return creTime;
    }

    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Integer getLookStatus() {
        return lookStatus;
    }

    public void setLookStatus(Integer lookStatus) {
        this.lookStatus = lookStatus;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComInfo() {
        return comInfo;
    }

    public void setComInfo(String comInfo) {
        this.comInfo = comInfo;
    }

    public String getMixInfo() {
        return mixInfo;
    }

    public void setMixInfo(String mixInfo) {
        this.mixInfo = mixInfo;
    }
}
