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
public class PerPosApply  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;//主键,
	private Integer perUserId;//人个用户ID
	private Integer posId;//职位ID
	private Integer comId;//企业ID
	private Integer resumeId;//简历ID
	private Integer applyNum;//申请应聘次数
	private Date creTime;//记录生成时间【应聘时间】
	private Date editTime;//修改时间
    /**
     1	表示企业已邀请您面试
     2	表示企业正在考虑您的简历
     3	表示企业已婉言拒绝了您应聘意向
     4	表示简历邮件已阅读
     5	表示简历已经被企业收藏
     */
	private Integer replyStatus;//企业对此次应聘的回复状态
    private Integer delStatus;// 删除状态 {-1 已删除}
	private Integer mark;//旗子标记    后期扩充 增加两种状态：  null 为未读 ， 9为已读
	private String remark;//备注内容
    private String mixInfo; //综合信息
    private String fromSourceUrl; //外站来源地址
    private String spreadItemTn; //推广栏目编号
    private String resumeName;//简历名称
    private Integer resumeType; //0:普通简历 1：长沙简历 4：微简历   ,参见:CommonEnum.ResumeType为准

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
	public Integer getPosId() {
		return posId;
	}
	public void setPosId(Integer posId) {
		this.posId = posId;
	}
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}
	public Integer getResumeId() {
		return resumeId;
	}
	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}
	public Integer getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(Integer applyNum) {
		this.applyNum = applyNum;
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
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
    public Integer getReplyStatus() {
        return replyStatus;
    }
    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
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

    public String getMixInfo() {
        return mixInfo;
    }

    public void setMixInfo(String mixInfo) {
        this.mixInfo = mixInfo;
    }

    public String getFromSourceUrl() {
        return fromSourceUrl;
    }

    public void setFromSourceUrl(String fromSourceUrl) {
        this.fromSourceUrl = fromSourceUrl;
    }

    public String getSpreadItemTn() {
        return spreadItemTn;
    }

    public void setSpreadItemTn(String spreadItemTn) {
        this.spreadItemTn = spreadItemTn;
    }
    
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

    public Integer getResumeType() {
        return resumeType;
    }

    public void setResumeType(Integer resumeType) {
        this.resumeType = resumeType;
    }
}
