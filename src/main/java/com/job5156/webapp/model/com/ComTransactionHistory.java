package com.job5156.webapp.model.com;

import com.job5156.webapp.model.sys.system.SysUser;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <P>
 * 企业历史交易记录实体
 * </P>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComTransactionHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;                   	//交易记录id
	private Integer comId; 					//公司id
	private Integer memberType;				//会员类型[1-试用会员 2-一月期会员 3-三月期会员 4-半年期会员 5:一年期会员 6:导入企业会员 7:终止企业会员]
	private Date validDate;					//开通时间
	private Date expireDate;				//截止时间
	private Integer maxPosNum; 				//发布职位[-1不限]
	@Deprecated
	private Integer maxResumeNum;			//查看简历(个/月)
	private Integer maxMsgNum;				//短信通知点数
	private Integer countNum;				//简历下载点数
	private Integer posRefreshNum;			//职位刷新点数
	private Integer salerId;				//业务员id
	private String checkUser;				//后台操作人姓名
	private String restrictArea;			//限制区域
	private Date createDate;				//创建日期
	private Date editDate;					//修改日期
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comId", insertable = false, updatable = false)
	private ComInfo comInfo;//相关企业信息
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salerId", insertable = false, updatable = false)
	private SysUser salerUser;//业务员信息

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

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getMaxPosNum() {
		return maxPosNum;
	}

	public void setMaxPosNum(Integer maxPosNum) {
		this.maxPosNum = maxPosNum;
	}

    @Deprecated
	public Integer getMaxResumeNum() {
		return maxResumeNum;
	}

    @Deprecated
	public void setMaxResumeNum(Integer maxResumeNum) {
		this.maxResumeNum = maxResumeNum;
	}

	public Integer getMaxMsgNum() {
		return maxMsgNum;
	}

	public void setMaxMsgNum(Integer maxMsgNum) {
		this.maxMsgNum = maxMsgNum;
	}

	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

	public Integer getSalerId() {
		return salerId;
	}

	public void setSalerId(Integer salerId) {
		this.salerId = salerId;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public String getRestrictArea() {
		return restrictArea;
	}

	public void setRestrictArea(String restrictArea) {
		this.restrictArea = restrictArea;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public ComInfo getComInfo() {
		return comInfo;
	}

	public void setComInfo(ComInfo comInfo) {
		this.comInfo = comInfo;
	}

	public SysUser getSalerUser() {
		return salerUser;
	}

	public void setSalerUser(SysUser salerUser) {
		this.salerUser = salerUser;
	}

	public Integer getPosRefreshNum() {
		return posRefreshNum;
	}

	public void setPosRefreshNum(Integer posRefreshNum) {
		this.posRefreshNum = posRefreshNum;
	}
}
