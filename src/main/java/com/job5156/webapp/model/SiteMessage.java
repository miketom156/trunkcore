package com.job5156.webapp.model;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * @ClassName: SiteMessage
 * @Description: 公司/个人站内消息实体类 <br>
 *               因个人ID,企业ID都是自增ID,放在一起时非唯一,故用comeform+企业ID来区分收件人/发件人
 * @author lqf
 * @date 2013-9-02
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SiteMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer           id;

    private String            category;             // 动态分组，用于首页分组显示，同一分组内可能有多种类型的动态
    private String            type;                 // 动态的类型，内容根据动态类型在程序中生成

    private String            comeFrom;             // 消息来源类别(COM:表示来自企业,PER:表示来自个人,SYS:表示来自系统)
    private Integer           comId;                // 企业ID
    private transient Integer comUserId;            // 企业用户ID(子帐号等)
    private Integer           perUserId;            // 个人ID
    private String            perUserName;          // 个人名称(收件人)
    private transient Integer resumeId;
    private Integer           groupId;              // 分组ID,用做排序,(旧数据导入:replyId=0时为记录ID,否则等于replyId)
    private Integer           replyId;              // 所回复的消息Id,默认为0表示首发而不是回复
    private Integer           sourceId;             // 消息的产生者ID，这里可能是企业、个人或系统
    private String            sourceName;           // 消息的产生者的名字,这里可能是企业、个人或系统
    private String            photo;                // 头像
    private String            title;                // 标题
    private String            content;              // 内容


    private Integer           posId;                // 职位id
    private String            posName;              // 职位名称
    private Integer           readFlag;             // 是否已读(0为未读，1为已读)
    private Integer           draftFlag;            // 草稿(0.普通 1.草稿)
    private Integer           perDelFlag;           // 0:表示正常,-1:表示个人删除
    private Integer           comDelFlag;           // 0:表示正常,-1:表示企业删除
    private Integer           replyFlag;            // 是否回复(企业与个人对同一消息最多只能回复一次)
    private Integer           publicFlag;           // 回复内容是否公开显示(0:默认不公开,1:公开)
    private Date              createDate;           // 创建日期
    private Date              updateDate;           // 修复日期
   
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replyId", insertable = false, updatable = false)
    private SiteMessage replyMessage;

	public SiteMessage getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(SiteMessage replyMessage) {
		this.replyMessage = replyMessage;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getComUserId() {
        return comUserId;
    }

    public void setComUserId(Integer comUserId) {
        this.comUserId = comUserId;
    }

    public Integer getPerUserId() {
        return perUserId;
    }

    public void setPerUserId(Integer perUserId) {
        this.perUserId = perUserId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        if(StringUtils.equalsIgnoreCase(getComeFrom(),"PER")) {
            return getPerUserName();
        } else if(StringUtils.equalsIgnoreCase(getComeFrom(),"SYS")) {
            return "智通人才网";
        }
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPosId() {
		return posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public Integer getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(Integer readFlag) {
        this.readFlag = readFlag;
    }

    public Integer getDraftFlag() {
        return draftFlag;
    }

    public void setDraftFlag(Integer draftFlag) {
        this.draftFlag = draftFlag;
    }

    public Integer getPerDelFlag() {
        return perDelFlag;
    }

    public void setPerDelFlag(Integer perDelFlag) {
        this.perDelFlag = perDelFlag;
    }

    public Integer getComDelFlag() {
        return comDelFlag;
    }

    public void setComDelFlag(Integer comDelFlag) {
        this.comDelFlag = comDelFlag;
    }

    public Integer getReplyFlag() {
        return replyFlag;
    }

    public void setReplyFlag(Integer replyFlag) {
        this.replyFlag = replyFlag;
    }

    public Integer getPublicFlag() {
        return publicFlag;
    }

    public void setPublicFlag(Integer publicFlag) {
        this.publicFlag = publicFlag;
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

    public String getPerUserName() {
        return perUserName;
    }

    public void setPerUserName(String perUserName) {
        this.perUserName = perUserName;
    }

	public Integer getResumeId() {
		return resumeId;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
