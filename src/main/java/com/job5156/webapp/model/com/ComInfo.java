package com.job5156.webapp.model.com;

import com.google.common.collect.Lists;
import com.job5156.core.common.CommonEnum.CompanyStatus;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
* @ClassName: ComInfo
* @Description: 公司基本资料实体类 
* @date 2013-7-3 
*
 */

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComInfo implements Serializable {

	private static final long serialVersionUID = 1L ;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	private String comName ;   // 企业名称
	private String businessLicence ; // 营业执照
	private Integer property;// 企业性质
	private Integer industry ; // 企业所属行业
	private Integer location ; // 所在地区（城市）
	private Date foundDate ; // 企业成立日期
	private Integer registerFund ; // 注册资金
	private Integer employeeNumber ; // 员工数目
	private String companyIntroduction ; // 企业简介
	private Date createDate ; // 创建日期
	private Date updateDate ; // 修改日期
	private String lastEditor ; // 修改人
	private Integer status ; // 企业资料审核状态(0:新注册 1:已开通 2:公共库企业 -1:被锁定 -2:作废 -3:非法企业-4:已删除)
	private String filterPerId ; // 屏蔽个人id
	private String registerIp ; // 企业注册IP
	private Integer needHunter ; // 是否需要猎头服务
	private Integer salerId ; // 业务员ID,默认为0,分配业务员和审核开通都操作此字段
	private String salerName ; // 业务员姓名
	private Integer salerRead ; // 分配业务员之后.他是否看过此公司.默认为0:表示没看过 1:表示看过
	private String illegality ; // 非法企业，记录非法企业备注
	private Integer comFlag ; // 企业标识 1为正式资料，其他为导入企业
	private Integer terminate ; // 是否屏蔽[0-否 1-是] 后台使用
	private String crmComId ; // 对应CRM系统的企业ID号
	private String logoPath ; // 企业logo图片路径
	private String logoName ; // 企业logo图片名称
	private String taoLabel;  //职位诱惑（原淘标签）多个以,隔开保存
    private String homePage;    //企业网站网址
    private String weixin;    //企业微信号
    private String weibo;    //企业新浪微博号
    private Integer emailActivation;     //邮件是否验证通过：0：未验证；1：已验证；
    
	@OneToMany(mappedBy="comInfo", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<ComRight> comRightList;//企业权限/开通信息
	
	@OneToMany(mappedBy="comInfo", cascade = { CascadeType.REMOVE }, fetch=FetchType.LAZY)
	private List<ComPosition> comPositionList = Lists.newArrayList();//企业所有职位

    public String getTaoLabel() {
		return taoLabel;
	}

	public void setTaoLabel(String taoLabel) {
		this.taoLabel = taoLabel;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public Integer getEmailActivation() {
		return emailActivation;
	}

	public void setEmailActivation(Integer emailActivation) {
		this.emailActivation = emailActivation;
	}

	public Integer getId() {
		return id ;
	}

	public void setId(Integer id) {
		this.id = id ;
	}

	public String getComName() {
		return comName ;
	}

	public void setComName(String comName) {
		this.comName = comName ;
	}

	public String getBusinessLicence() {
		return businessLicence ;
	}

	public void setBusinessLicence(String businessLicence) {
		this.businessLicence = businessLicence ;
	}

	public Integer getIndustry() {
		return industry ;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry ;
	}

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public Date getFoundDate() {
		return foundDate ;
	}

	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate ;
	}

	public Integer getRegisterFund() {
		return registerFund ;
	}

	public void setRegisterFund(Integer registerFund) {
		this.registerFund = registerFund ;
	}

	public Integer getEmployeeNumber() {
		return employeeNumber ;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber ;
	}

	public String getCompanyIntroduction() {
		return companyIntroduction ;
	}

	public void setCompanyIntroduction(String companyIntroduction) {
		this.companyIntroduction = companyIntroduction ;
	}

	public Date getCreateDate() {
		return createDate ;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate ;
	}

	public Date getUpdateDate() {
		return updateDate ;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate ;
	}

	public String getLastEditor() {
		return lastEditor ;
	}

	public void setLastEditor(String lastEditor) {
		this.lastEditor = lastEditor ;
	}

	public Integer getStatus() {
		return status ;
	}

	public void setStatus(Integer status) {
		this.status = status ;
	}

	public String getFilterPerId() {
		return filterPerId ;
	}

	public void setFilterPerId(String filterPerId) {
		this.filterPerId = filterPerId ;
	}

	public String getRegisterIp() {
		return registerIp ;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp ;
	}

	public Integer getNeedHunter() {
		return needHunter ;
	}

	public void setNeedHunter(Integer needHunter) {
		this.needHunter = needHunter ;
	}

	public Integer getSalerId() {
		return salerId ;
	}

	public void setSalerId(Integer salerId) {
		this.salerId = salerId ;
	}

	public String getSalerName() {
		return salerName ;
	}

	public void setSalerName(String salerName) {
		this.salerName = salerName ;
	}

	public Integer getSalerRead() {
		return salerRead ;
	}

	public void setSalerRead(Integer salerRead) {
		this.salerRead = salerRead ;
	}

	public String getIllegality() {
		return illegality ;
	}

	public void setIllegality(String illegality) {
		this.illegality = illegality ;
	}

	public Integer getComFlag() {
		return comFlag ;
	}

	public void setComFlag(Integer comFlag) {
		this.comFlag = comFlag ;
	}

	public Integer getTerminate() {
		return terminate ;
	}

	public void setTerminate(Integer terminate) {
		this.terminate = terminate ;
	}

	public String getCrmComId() {
		return crmComId ;
	}

	public void setCrmComId(String crmComId) {
		this.crmComId = crmComId ;
	}

	public String getLogoPath() {
		return logoPath ;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath ;
	}

	public String getLogoName() {
		return logoName ;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName ;
	}

    public List<ComRight> getComRightList() {
        return comRightList;
    }

    public void setComRightList(List<ComRight> comRightList) {
        this.comRightList = comRightList;
    }

	public List<ComPosition> getComPositionList() {
		return comPositionList;
	}

	public void setComPositionList(List<ComPosition> comPositionList) {
		this.comPositionList = comPositionList;
	}
	
	@Transient
	public String getStatusStr(){
		if(status != null){
			return CompanyStatus.getNameByValue(status);
		}
		return "";
	}

    @Transient
    public ComRight getComRight() {
        if(CollectionUtils.isNotEmpty(getComRightList()) && CollectionUtils.size(getComRightList()) > 0){
            return getComRightList().get(0);
        }
        return null;
    }
}
