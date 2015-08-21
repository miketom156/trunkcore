package com.job5156.core.entity.per;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.job5156.core.common.option.OptionMap;
import com.job5156.core.entity.sys.manage.SysResumeAuditHistory;
import com.job5156.core.bo.mapping.PerResumeBo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class PerResume implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;                    //简历信息ID
	private Integer accountType;           //账号类型
	private Integer accountId;             //个人用户账号ID
	private String resumeName;             //简历名称
	private Integer resumeType;            //简历类型:0：普通简历 1: 为长沙类型的简历 4: 名片,参见：CommonEnum.ResumeType
	private Integer resumeId;              //简历ID  中文简历 和 英文简历相互对应
    private String resumeStyle;            //简历模板，默认为default
	private Integer languageType;          //语言类型:0为中文，1为英文
	private String userLastEditBy;         //用户最后修改人
	private Date userLastEditDate;         //用户的最后修改时间
	private String adminLastEditBy;        //后台管理员最后修改人
	private Date adminLastEditDate;        //后台管理员最后修改时间
	private Date freDate;                  //简历刷新时间
	private Integer resumeGrade;           //简历类型：0-普通,1-精英(猎头) 2-模具人才. 101:中高级人才（进查询库）  105:中高级人才(保密级不进查询库)
	private Date passDate;                 //审核时间
	private Integer comeFrom;              //简历来源 600=来自于手机页面注册
	private Integer havePhoto;             //是否有照片：0为无，1为有
	private Integer pass;                  //-10:自杀式简历，不让登录，-9：封锁,-3：审核不合格，-1：未开通(待审核），0:未完善，1：暂时开通  10：已开通 参见:CommonEnum.ResumeStatus
//	private Integer flag;                  //简历保密　0:完全公开　1:完全保密　2:公开但联系方式保密 //提升到帐号级(publicSetting)
	private Integer readCount;             //被企业查看次数
	private Integer resFormwork;           //简历模板：0-6 默认为0
	private Double perfectNum;             //简历完整度数
	private Integer perfectInfo;           //用户简历信息的完整的0:不可投递，1：可投递（填写所有必填项）
	private String educationInfo;          //教育经历
	private String trainInfo;              //培训信息
	private String workInfo;               //工作经历/社会实践信息
	private String intentInfo;             //求职意向信息(包含：我的亮点，自我描述)
	private String languageInfo;           //语言技能信息
	private String certificateInfo;        //证书信息
	private String projectInfo;            //项目信息
	private String accessoryInfo;          //附件信息
    private Integer displayEnResume;       //是否显示英文简历(0:默认不显示,1:显示)
    private String skillInfo;    //专业技能

	@NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId", insertable = false, updatable = false)
    private PerUser perUser;
    
    @OneToMany(mappedBy="perResume", fetch=FetchType.LAZY)
	private List<SysResumeAuditHistory> resumeAuditHistory;

    //========== 非POJO方法 ==========================================

    public Map<String,Object> getMap() {
        Map<String, Object> jsonMap = Maps.newHashMap();

        PerResumeBo resumeVo = new PerResumeBo(this);

        if(CollectionUtils.isNotEmpty(resumeVo.getEducationInfoVoList())) {
            int highDegree = 0;
            for (PerResumeBo.EducationInfoVo infoVo : resumeVo.getEducationInfoVoList()) {
                if(infoVo.getDegree() != null && infoVo.getDegree() > highDegree){
                    highDegree = infoVo.getDegree();
                    jsonMap.put("degree", infoVo.getDegree());
                    jsonMap.put("degreeStr", OptionMap.getValue(OptionMap.OptionType.OPT_PER_DEGREE, infoVo.getDegree()));
                    jsonMap.put("school", infoVo.getSchoolName());
                    jsonMap.put("speciality", infoVo.getSpeciality());
                }
            }
        }

        PerResumeBo.IntentInfoVo intentVo = resumeVo.getIntentInfoVo();
        if(intentVo != null){

            //期望职位
            List<String> jobNameList =  Lists.newArrayList();
            List<Integer> jobCodeList =  Lists.newArrayList();
            if(StringUtils.isNotBlank(intentVo.getJobCode())) {
                for (String jobCode : intentVo.getJobCode().split(",")) {
                    String jobCodeStr = OptionMap.getValue(OptionMap.OptionType.OPT_POSITION, NumberUtils.toInt(jobCode, 0));
                    if(!jobNameList.contains(jobCodeStr)) {
                        jobNameList.add(jobCodeStr);
                    }
                    if(!jobCodeList.contains(NumberUtils.toInt(jobCode, 0))) {
                        jobCodeList.add(NumberUtils.toInt(jobCode, 0));
                    }
                }
            }
            if(StringUtils.isNotBlank(intentVo.getJobName())) {
                for (String jobCodeStr : intentVo.getJobName().split(",")) {
                    if(!jobNameList.contains(jobCodeStr)) {
                        jobNameList.add(jobCodeStr);
                    }
                }
            }
            jsonMap.put("expectJobStr", StringUtils.join(jobNameList, " "));
            jsonMap.put("expectJob", StringUtils.join(jobNameList, ","));

            //期望地区
            List<String> jobLocationStrList = Lists.newArrayList();
            List<Integer> jobLocationList = Lists.newArrayList();
            if(StringUtils.isNotBlank(intentVo.getJobLocation())) {
                for (String location : intentVo.getJobLocation().split(",")) {
                    String locationStr = OptionMap.getFullAddr(NumberUtils.toInt(location, 0));
                    if(!jobLocationStrList.contains(locationStr)) {
                        jobLocationStrList.add(locationStr);
                    }
                    if(!jobLocationList.contains(NumberUtils.toInt(location, 0))) {
                        jobLocationList.add(NumberUtils.toInt(location, 0));
                    }
                }
            }
            jsonMap.put("expectLocationStr", StringUtils.join(jobLocationStrList, " "));
            jsonMap.put("expectLocation", StringUtils.join(jobLocationList, ","));

            //期望月薪
            if(intentVo.getSalary() != null) {
                String salaryStr = OptionMap.getValue(OptionMap.OptionType.OPT_SALARY, intentVo.getSalary());
                jsonMap.put("expectSalary", intentVo.getSalary());
                jsonMap.put("expectSalaryStr", StringUtils.isNotBlank(salaryStr)?salaryStr : "面议");
            }
            //关键字(文字变更为：我的亮点)
            jsonMap.put("keyword", StringUtils.isNotBlank(intentVo.getKeywords())?intentVo.getKeywords() : "");
            //技能/评价(文字变更为：自我描述)
            jsonMap.put("skill", StringUtils.isNotBlank(intentVo.getProfessionSkill())?intentVo.getProfessionSkill() : "");

            //最近一份工作信息
            if(CollectionUtils.isNotEmpty(resumeVo.getWorkInfoVoList())) {
                String lastJobName = "";
                String lastComName = "";
                String lastTimePeriod = "";
                for (PerResumeBo.WorkInfoVo infoVo : resumeVo.getWorkInfoVoList()) {
                    if(StringUtils.isBlank(lastJobName)) {
                        lastJobName = infoVo.getJobName();
                    }
                    if(StringUtils.isBlank(lastComName)) {
                        lastComName = infoVo.getComName();
                    }
                    if(StringUtils.isBlank(lastTimePeriod)) {
                        lastTimePeriod = infoVo.getBegin() + "至" + ( StringUtils.isNotBlank(infoVo.getEnd()) ? infoVo.getEnd() : "今");
                    }
                }
                jsonMap.put("lastJobName", lastJobName);
                jsonMap.put("lastComName", lastComName);
                jsonMap.put("lastTimePeriod", lastTimePeriod);
            }
        }
        return jsonMap;
    }

    //================= getter && setter ====================================
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
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
	public Integer getResumeId() {
		return resumeId;
	}
	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}
    public String getResumeStyle() {
        return resumeStyle;
    }
    public void setResumeStyle(String resumeStyle) {
        this.resumeStyle = resumeStyle;
    }
    public Integer getLanguageType() {
		return languageType;
	}
	public void setLanguageType(Integer languageType) {
		this.languageType = languageType;
	}
	public String getUserLastEditBy() {
		return userLastEditBy;
	}
	public void setUserLastEditBy(String userLastEditBy) {
		this.userLastEditBy = userLastEditBy;
	}
	public Date getUserLastEditDate() {
		return userLastEditDate;
	}
	public void setUserLastEditDate(Date userLastEditDate) {
		this.userLastEditDate = userLastEditDate;
	}
	public String getAdminLastEditBy() {
		return adminLastEditBy;
	}
	public void setAdminLastEditBy(String adminLastEditBy) {
		this.adminLastEditBy = adminLastEditBy;
	}
	public Date getAdminLastEditDate() {
		return adminLastEditDate;
	}
	public void setAdminLastEditDate(Date adminLastEditDate) {
		this.adminLastEditDate = adminLastEditDate;
	}
	public Date getFreDate() {
		return freDate;
	}
	public void setFreDate(Date freDate) {
		this.freDate = freDate;
	}
	public Integer getResumeGrade() {
		return resumeGrade;
	}
	public void setResumeGrade(Integer resumeGrade) {
		this.resumeGrade = resumeGrade;
	}
	public Date getPassDate() {
		return passDate;
	}
	public void setPassDate(Date passDate) {
		this.passDate = passDate;
	}
	public Integer getComeFrom() {
		return comeFrom;
	}
	public void setComeFrom(Integer comeFrom) {
		this.comeFrom = comeFrom;
	}
	public Integer getHavePhoto() {
		return havePhoto;
	}
	public void setHavePhoto(Integer havePhoto) {
		this.havePhoto = havePhoto;
	}
	public Integer getPass() {
		return pass;
	}
	public void setPass(Integer pass) {
		this.pass = pass;
	}
	public Integer getReadCount() {
		return readCount;
	}
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
	public Integer getResFormwork() {
		return resFormwork;
	}
	public void setResFormwork(Integer resFormwork) {
		this.resFormwork = resFormwork;
	}
	public Double getPerfectNum() {
		return perfectNum;
	}
	public void setPerfectNum(Double perfectNum) {
		this.perfectNum = perfectNum;
	}
	public Integer getPerfectInfo() {
		return perfectInfo;
	}
	public void setPerfectInfo(Integer perfectInfo) {
		this.perfectInfo = perfectInfo;
	}
	public String getEducationInfo() {
		return educationInfo;
	}
	public void setEducationInfo(String educationInfo) {
		this.educationInfo = educationInfo;
	}
	public String getTrainInfo() {
		return trainInfo;
	}
	public void setTrainInfo(String trainInfo) {
		this.trainInfo = trainInfo;
	}
	public String getWorkInfo() {
		return workInfo;
	}
	public void setWorkInfo(String workInfo) {
		this.workInfo = workInfo;
	}
	public String getIntentInfo() {
		return intentInfo;
	}
	public void setIntentInfo(String intentInfo) {
		this.intentInfo = intentInfo;
	}
	public String getLanguageInfo() {
		return languageInfo;
	}
	public void setLanguageInfo(String languageInfo) {
		this.languageInfo = languageInfo;
	}
	public String getCertificateInfo() {
		return certificateInfo;
	}
	public void setCertificateInfo(String certificateInfo) {
		this.certificateInfo = certificateInfo;
	}
	public String getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(String projectInfo) {
		this.projectInfo = projectInfo;
	}
	public String getAccessoryInfo() {
		return accessoryInfo;
	}
	public void setAccessoryInfo(String accessoryInfo) {
		this.accessoryInfo = accessoryInfo;
	}
    public Integer getDisplayEnResume() {
        return displayEnResume;
    }
    public void setDisplayEnResume(Integer displayEnResume) {
        this.displayEnResume = displayEnResume;
    }
    public PerUser getPerUser() {
        return perUser;
    }
    public void setPerUser(PerUser perUser) {
        this.perUser = perUser;
    }
	public List<SysResumeAuditHistory> getResumeAuditHistory() {
		return resumeAuditHistory;
	}
	public void setResumeAuditHistory(List<SysResumeAuditHistory> resumeAuditHistory) {
		this.resumeAuditHistory = resumeAuditHistory;
	}
    public String getSkillInfo() {
        return skillInfo;
    }
    public void setSkillInfo(String skillInfo) {
        this.skillInfo = skillInfo;
    }
}
