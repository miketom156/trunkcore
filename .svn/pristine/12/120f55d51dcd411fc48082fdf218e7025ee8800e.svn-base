package com.job5156.core.entity.act;

import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 活动调查问卷的具体题目
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ActSurveyQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer           id;  // 主键
    private Date createDate ;   // 创建时间[应聘时间]
    private Date updateDate ;   // 更新时间

    private String editor;      //编辑者
    private Integer editorId;   //编辑者ID

    private Integer surveyId;   //所属问卷ID
    private String question;    //问题
    private String answer;      //默认回答json格式
    private Integer currentVotes; //当前票数 |本题目回复数
    private Integer initVotes;    //初始票数


    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surveyId", insertable = false, updatable = false)
    private ActSurvey actSurvey;                                //关联问卷

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

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ActSurvey getActSurvey() {
        return actSurvey;
    }

    public void setActSurvey(ActSurvey actSurvey) {
        this.actSurvey = actSurvey;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Integer getEditorId() {
        return editorId;
    }

    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }

    public Integer getCurrentVotes() {
        return currentVotes;
    }

    public void setCurrentVotes(Integer currentVotes) {
        this.currentVotes = currentVotes;
    }

    public Integer getInitVotes() {
        return initVotes;
    }

    public void setInitVotes(Integer initVotes) {
        this.initVotes = initVotes;
    }
}