package com.job5156.webapp.model;

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
public class RecuitAnalysis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String analyType; //人才分析类型，目前有2类【position=网络职位30天内应聘人才分析 ;fair=招聘会参会人才分析】
    private Integer analyId; //对应的id
    private Date analyDate; //分析时间
    private String analyResult; //分析的结果，json字段保存的格式

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnalyType() {
        return analyType;
    }

    public void setAnalyType(String analyType) {
        this.analyType = analyType;
    }

    public Integer getAnalyId() {
        return analyId;
    }

    public void setAnalyId(Integer analyId) {
        this.analyId = analyId;
    }

    public Date getAnalyDate() {
        return analyDate;
    }

    public void setAnalyDate(Date analyDate) {
        this.analyDate = analyDate;
    }

    public String getAnalyResult() {
        return analyResult;
    }

    public void setAnalyResult(String analyResult) {
        this.analyResult = analyResult;
    }
}
