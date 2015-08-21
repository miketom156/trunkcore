package com.job5156.webapp.model.act;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 抽奖活动-用户机会
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ActUserChance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id;      // 主键
    private Date createDate ; // 创建时间
    private Date updateDate ; // 修改时间
    private  Integer stkId;//本次活动ID
    private  Integer userId;// 用户ID
    private  Integer userType;// 用户类型 0:个人用户 1：企业用户
    private  Integer chanceNum;//机会数量 不得大于最大机会数maxNum
    private  Integer usedNum ;//已使用机会数量
    private  Integer maxNum;// 最大机会数量

    private  String  chanceRule;//机会规则 json方式存放信息

    @Transient
    private Map<String,Integer> ruleMap=new HashMap<>(); //规则MAP

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

    public Integer getStkId() {
        return stkId;
    }

    public void setStkId(Integer stkId) {
        this.stkId = stkId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getChanceNum() {
        return chanceNum;
    }

    public void setChanceNum(Integer chanceNum) {
        this.chanceNum = chanceNum;
    }

    public Integer getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Integer usedNum) {
        this.usedNum = usedNum;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public String getChanceRule() {
        return chanceRule;
    }

    public void setChanceRule(String chanceRule) {
        this.chanceRule = chanceRule;
    }

    public Map<String, Integer> getRuleMap() {
        return ruleMap;
    }

    public void setRuleMap(Map<String, Integer> ruleMap) {
        this.ruleMap = ruleMap;
    }
}
