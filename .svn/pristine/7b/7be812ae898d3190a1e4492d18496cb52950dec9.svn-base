package com.job5156.webapp.model;

import com.job5156.webapp.model.com.ComInfo;
import com.job5156.webapp.model.sys.ComReturnPoints;
import com.job5156.webapp.model.sys.system.SysUser;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 帮助中心的反馈和后台对反馈内容的回复
 * 反馈：不需要登录;个人和企业账号都登录，企业ID优先
 *
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class PubFeedback implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer perUserId;//个人用户ID
	private Integer comId;//企业ID
	private Integer type;//提问类型1-建议 2-纠错 3-投诉 4-手机客户端反馈
	private String subject;//主题【网站功能建议、招聘会建议、其他建议】
	private String content;//提问内容
	private Integer starLevel;//评分
	private String name;//反馈人
	private String email;//邮箱
	private String mobile;//手机
	private String url;//反馈路径
	private Date createDate;//创建日期[反馈日期]
	
	private Integer flag;//回复标识 默认0=未回复,1=已回复，2=待回复，3=不处理
	private Date replyDate;//回复日期
	private String replyContent;//回复内容
	private Integer replyUserId;//回复人ID[后台用户id]
	private String sysMsg;//站内信
    private Integer pointsId; // 关联返点申请单的ID

	@ManyToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name = "replyUserId", insertable = false, updatable = false)
	private SysUser replyUser;//回复人

    @NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pointsId", insertable = false, updatable = false)
	private ComReturnPoints points;//返点申请单
    
    @NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comId", insertable = false, updatable = false)
	private ComInfo comInfo;//申请企业

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPerUserId() {
		return perUserId;
	}
	public void setPerUserId(Integer perUserId) {
		this.perUserId = perUserId;
	}
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Integer getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(Integer replyUserId) {
		this.replyUserId = replyUserId;
	}
	public String getSysMsg() {
		return sysMsg;
	}
	public void setSysMsg(String sysMsg) {
		this.sysMsg = sysMsg;
	}
	public SysUser getReplyUser() {
		return replyUser;
	}
	public void setReplyUser(SysUser replyUser) {
		this.replyUser = replyUser;
	}

    public Integer getPointsId() {
        return pointsId;
    }

    public void setPointsId(Integer pointsId) {
        this.pointsId = pointsId;
    }

    public ComReturnPoints getPoints() {
        return points;
    }

    public void setPoints(ComReturnPoints points) {
        this.points = points;
    }
    
	public ComInfo getComInfo() {
		return comInfo;
	}
	
	public void setComInfo(ComInfo comInfo) {
		this.comInfo = comInfo;
	}
}