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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 抽奖活动
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ActSweepstakes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id;      // 主键
    private Date createDate ; // 创建时间
    private Date updateDate ; // 修改时间
    private  String title;// 活动主题
    private  Date  startDate;  //   活动开始日期
    private Date expiryDate ;//  活动结束日期
    private Date beginTime ;// 抽奖开始时间,每天的几点开始
    private  Date endTime;//  抽奖结束时间,每天的几点结束
    private  String note;// 活动规则说明
    private  Integer status;// 活动状态   1：生效 0：待审核  -1：已结束     为方便排序，从大到小
    private  Integer poolNum;//   奖池数量
    private  Integer usedNum;// 已抽奖次数
    private  Integer chkUserId  ;// 审核者ID
    private  String  emptyName;//空奖项时显示的名称
    private  Date awardExpiryDate ;//  奖品逾期日期

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPoolNum() {
        return poolNum;
    }

    public void setPoolNum(Integer poolNum) {
        this.poolNum = poolNum;
    }

    public Integer getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Integer usedNum) {
        this.usedNum = usedNum;
    }

    public Integer getChkUserId() {
        return chkUserId;
    }

    public void setChkUserId(Integer chkUserId) {
        this.chkUserId = chkUserId;
    }

    public String getEmptyName() {
        return emptyName;
    }

    public void setEmptyName(String emptyName) {
        this.emptyName = emptyName;
    }

    public Date getAwardExpiryDate() {
        return awardExpiryDate;
    }

    public void setAwardExpiryDate(Date awardExpiryDate) {
        this.awardExpiryDate = awardExpiryDate;
    }

    public String  getNoteHtml(){
        if(this.note==null||note.trim().equals("")){
            return    "";
        }
        Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)");
        Matcher m = CRLF.matcher(note);
        if (m.find()) {
            note = m.replaceAll("<br>");
        }

        return    note;
    }
}
