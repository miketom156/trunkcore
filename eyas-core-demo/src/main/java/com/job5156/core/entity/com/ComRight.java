package com.job5156.core.entity.com;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.job5156.core.common.CommonEnum;
import com.job5156.core.entity.sys.system.SysUser;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ComRight
 *  @Description: 企业权限实体类 
 *
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComRight  implements Serializable {

	private static final long serialVersionUID = 1L ;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;//主键
	private Integer comId;//企业id
	private Integer memberType;//会员类型[1-试用会员 2-一月期会员 3-三月期会员 4-半年期会员 5:一年期会员 6:导入企业会员 7:终止企业会员]
	private Integer otherMember;//扩展类型[1-LOGO会员 2-热门推荐会员 11-体验会员 21-点数自定义会员]
	private Date validDate;//开通日期
	private Date expireDate;//截止日期
	private Integer maxPosNum;      //最大可发布职位数[-1:不限],默认为0
	private Integer maxResumeNum;   //最大可查看简历数(个/月),默认为0
	private Integer viewingResume;  //当月查看简历数,默认为0
	private Integer viewedResume;   //累积查看简历数
	private String checkUser;       //后台操作人姓名
	private Date checkDate;         //后台操作时间
	private Integer maxMsgNum;      //可发送短信点数,默认为0
	private Integer msgingNum;      //已发送短信条数,默认为0
	private Integer salerId;//业务员id
	private Integer money;//购买金额
	private Integer countNum;//(一共)购买点数
	private Integer useNum;//(一共)使用点数(显示联系方式)
	private String mark;//注备
	private Date createDate;//后台创建日期',
	private String createUserName;//后台创建人姓名
	private String restrictArea;//限制区域
	private Integer accountNum;//最大子账号数
	private Integer enterCareer;//是否允许进入事业起点 0：不可以 1：可以,默认为0

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comId", insertable = false, updatable = false)
    private ComInfo comInfo;                                //关联企业
    
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
	public Integer getOtherMember() {
		return otherMember;
	}
	public void setOtherMember(Integer otherMember) {
		this.otherMember = otherMember;
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
	public Integer getMaxResumeNum() {
		return maxResumeNum;
	}
	public void setMaxResumeNum(Integer maxResumeNum) {
		this.maxResumeNum = maxResumeNum;
	}
	public Integer getViewingResume() {
		return viewingResume;
	}
	public void setViewingResume(Integer viewingResume) {
		this.viewingResume = viewingResume;
	}
	public Integer getViewedResume() {
		return viewedResume;
	}
	public void setViewedResume(Integer viewedResume) {
		this.viewedResume = viewedResume;
	}
	public String getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Integer getMaxMsgNum() {
		return maxMsgNum;
	}
	public void setMaxMsgNum(Integer maxMsgNum) {
		this.maxMsgNum = maxMsgNum;
	}
	public Integer getMsgingNum() {
		return msgingNum;
	}
	public void setMsgingNum(Integer msgingNum) {
		this.msgingNum = msgingNum;
	}
	public Integer getSalerId() {
		return salerId;
	}
	public void setSalerId(Integer salerId) {
		this.salerId = salerId;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getCountNum() {
		return countNum;
	}
	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}
	public Integer getUseNum() {
		return useNum;
	}
	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getRestrictArea() {
		return restrictArea;
	}
	public void setRestrictArea(String restrictArea) {
		this.restrictArea = restrictArea;
	}
	public Integer getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(Integer accountNum) {
		this.accountNum = accountNum;
	}
	public Integer getEnterCareer() {
		return enterCareer;
	}
	public void setEnterCareer(Integer enterCareer) {
		this.enterCareer = enterCareer;
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
	/**
	 * 
	* @Title: getMemberTypeStatus 
	* @Description: 获取会员期限状态
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String getMemberTypeStatus() {
		String status = "";
		
		if(memberType != null && expireDate != null) {
			if(memberType >= CommonEnum.ComMemberType.Test.getValue() &&
					memberType <= CommonEnum.ComMemberType.OneYear.getValue()) {
				long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
				long days = (expireDate.getTime() - new Date().getTime()) / nd;
				
				if(days < 0) {
					status = "已过期";
				} else if(days >= 0 && days <16) {
					status = "即将过期";
				} else {
					status = "正常";
				}
			} else if(memberType == CommonEnum.ComMemberType.Import.getValue()) {
				status = "导入会员";
			} else if(memberType == CommonEnum.ComMemberType.Stop.getValue()) {
				status = "终止会员";
			}
		}
		
		return status;
	}
	
	@Transient
	public String getMemberTypeStr(){
		if(memberType != null){
			return CommonEnum.ComMemberType.getNameByValue(memberType);
		}
		return "";
	}
	
	@Transient
	public String getOtherMemberStr(){
		if(otherMember != null){
			return CommonEnum.ComOtherMemberType.getNameByValue(otherMember);
		}
		return "";
	}

	@Transient
	public List<Integer> getRestrictAreaList(){
		if(restrictArea != null){
			Gson gson = new Gson();
			return gson.<List<Integer>>fromJson(restrictArea, new TypeToken<List<Integer>>() {}.getType());
		}
		return null;
	}
}
