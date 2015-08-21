package com.job5156.core.bo.mapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.job5156.core.common.Constants;
import com.job5156.core.common.util.JsonValidator;
import com.job5156.core.common.util.KeyMaskUtil;
import com.job5156.webapp.model.per.PerResume;
import com.job5156.webapp.model.per.PerUser;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

public class ResumePreviewBo {
    private Integer id;                    //简历信息ID
	private String resumeNo;               //简历编号
    private Integer accountType;           //账号类型
    private Integer accountId;             //个人用户账号ID
    private String resumeName;             //简历名称
    private Integer resumeType;            //简历类型:1为长沙类型的简历
    private Integer resumeId;              //简历ID  中文简历 和 英文简历相互对应
    private String resumeStyle;              //简历模板，默认为default
    private Integer languageType;          //语言类型:0为中文，1为英文
    private String userLastEditBy;         //用户最后修改人
    private Date userLastEditDate;         //用户的最后修改时间
    private String adminLastEditBy;        //后台管理员最后修改人
    private Date adminLastEditDate;        //后台管理员最后修改时间
    private Date freDate;                  //简历刷新时间
    private Integer resumeGrade;           //简历类型：0-普通,1-精英(猎头) 2-模具人才. 101:中高级人才（进查询库）  105:中高级人才(保密级不进查询库)
    private Date passDate;                 //审核时间
    private Integer comeFrom;              //简历来源
    private Integer havePhoto;             //是否有照片：0为无，1为有
    private Integer pass;                  //-10:自杀式简历，不让登录，-9：封锁,-3：审核不合格，-1：未开通，0:未完善，1：暂时开通，10：已开通
    private Integer flag;                  //简历保密　0:完全公开　1:完全保密　2:公开但联系方式保密
    private Integer readCount;             //被企业查看次数
    private Integer resFormwork;           //简历模板：0-6 默认为0
    private Double perfectNum;             //简历完整度数
    private Integer perfectInfo;           //用户简历信息的完整的0:不可投递，1：可投递（填写所有必填项）
    private Integer displayEnResume;       //是否显示英文简历(0:默认不显示,1:显示)
	private boolean hideContact;           //是否隐藏联系人信息

    private IntentInfoVo intentInfoVo;             //求职意向信息
    private List<LanguageInfoVo> languageInfoVoList;           //语言技能信息
    private List<CertificateInfoVo> certificateInfoVoList;        //证书信息
    private List<ProjectInfoVo> projectInfoVoList;            //项目信息
    private List<AccessoryInfoVo> accessoryInfoVoList;          //附件信息
    private List<WorkInfoVo> workInfoVoList;           //工作经历信息
    private List<EducationInfoVo> educationInfoVoList; //教育经历信息
    private List<TrainInfoVo> trainInfoVoList;         //培训经历信息

    private PerUserVo perUserVo;
    private BasicDataVo basicDataVo;
    
    //以下为企业查看个人简历用到的字段（freemark模板显示时做判断用）
    private transient Boolean buyFlag ;  //企业是否已经购买简历
    private transient Boolean loginFlag; //企业是否登录
    private transient String  modType;   //不同的模块(人才库，收到的简历...)显示button不一样
    private transient Integer posId;     //应聘的职位ID
    private transient String posName;    //应聘的职位名称
    private transient Date applyDate;    //应聘的日期
    private transient Boolean addressShowFlag; //地址是否显示
    private transient Boolean isSysUser; //是否为后台系统用户

    public ResumePreviewBo(){}
    public ResumePreviewBo(PerResume perResume) {
        if(perResume != null) {
            BeanUtils.copyProperties(perResume, this);

            Gson gson = new Gson();
            this.setIntentInfoVo(perResume.getIntentInfo() != null?gson.fromJson(perResume.getIntentInfo(), IntentInfoVo.class):new IntentInfoVo());
            this.setLanguageInfoVoList(gson.<List<LanguageInfoVo>>fromJson(perResume.getLanguageInfo(), new TypeToken<List<LanguageInfoVo>>() {}.getType()));
            this.setCertificateInfoVoList(gson.<List<CertificateInfoVo>>fromJson(perResume.getCertificateInfo(), new TypeToken<List<CertificateInfoVo>>() {}.getType()));
            this.setProjectInfoVoList(gson.<List<ProjectInfoVo>>fromJson(perResume.getProjectInfo(), new TypeToken<List<ProjectInfoVo>>() {}.getType()));
            this.setAccessoryInfoVoList(gson.<List<AccessoryInfoVo>>fromJson(perResume.getAccessoryInfo(), new TypeToken<List<AccessoryInfoVo>>() {}.getType()));
            this.setWorkInfoVoList(gson.<List<WorkInfoVo>>fromJson(perResume.getWorkInfo(), new TypeToken<List<WorkInfoVo>>() {}.getType()));
            this.setEducationInfoVoList(gson.<List<EducationInfoVo>>fromJson(perResume.getEducationInfo(), new TypeToken<List<EducationInfoVo>>() {}.getType()));
            this.setTrainInfoVoList(gson.<List<TrainInfoVo>>fromJson(perResume.getTrainInfo(), new TypeToken<List<TrainInfoVo>>() {}.getType()));

            this.setPerUserVo(new PerUserVo(perResume.getPerUser()));

        }
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResumeNo() {
        if(languageType == 1) {
            return ObjectUtils.toString(KeyMaskUtil.resumeIdToNo(resumeId));
        } else {
            return ObjectUtils.toString(KeyMaskUtil.resumeIdToNo(id));
        }
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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

	public boolean isHideContact() {
		return hideContact;
	}
	
	public void setHideContact(boolean hideContact) {
		this.hideContact = hideContact;
	}
	
	public IntentInfoVo getIntentInfoVo() {
		return intentInfoVo;
	}

    public void setIntentInfoVo(IntentInfoVo intentInfoVo) {
        this.intentInfoVo = intentInfoVo;
    }

    public List<LanguageInfoVo> getLanguageInfoVoList() {
        return languageInfoVoList;
    }

    public void setLanguageInfoVoList(List<LanguageInfoVo> languageInfoVoList) {
        this.languageInfoVoList = languageInfoVoList;
    }

    public List<CertificateInfoVo> getCertificateInfoVoList() {
        return certificateInfoVoList;
    }

    public void setCertificateInfoVoList(
            List<CertificateInfoVo> certificateInfoVoList) {
        this.certificateInfoVoList = certificateInfoVoList;
    }

    public List<ProjectInfoVo> getProjectInfoVoList() {
        return projectInfoVoList;
    }

    public void setProjectInfoVoList(List<ProjectInfoVo> projectInfoVoList) {
        this.projectInfoVoList = projectInfoVoList;
    }

    public List<AccessoryInfoVo> getAccessoryInfoVoList() {
        return accessoryInfoVoList;
    }

    public void setAccessoryInfoVoList(List<AccessoryInfoVo> accessoryInfoVoList) {
        this.accessoryInfoVoList = accessoryInfoVoList;
    }

    public List<WorkInfoVo> getWorkInfoVoList() {
        return workInfoVoList;
    }

    public void setWorkInfoVoList(List<WorkInfoVo> workInfoVoList) {
        this.workInfoVoList = workInfoVoList;
    }

    public List<EducationInfoVo> getEducationInfoVoList() {
        return educationInfoVoList;
    }

    public void setEducationInfoVoList(List<EducationInfoVo> educationInfoVoList) {
        this.educationInfoVoList = educationInfoVoList;
    }

    public List<TrainInfoVo> getTrainInfoVoList() {
        return trainInfoVoList;
    }

    public void setTrainInfoVoList(List<TrainInfoVo> trainInfoVoList) {
        this.trainInfoVoList = trainInfoVoList;
    }
    
    public PerUserVo getPerUserVo() {
        return perUserVo;
    }
    public void setPerUserVo(PerUserVo perUserVo) {
        this.perUserVo = perUserVo;
    }
    public BasicDataVo getBasicDataVo() {
        return basicDataVo;
    }
    public void setBasicDataVo(BasicDataVo basicDataVo) {
        this.basicDataVo = basicDataVo;
    }
    public Boolean getBuyFlag() {
        return buyFlag;
    }
    public void setBuyFlag(Boolean buyFlag) {
        this.buyFlag = buyFlag;
    }
    public Boolean getLoginFlag() {
        return loginFlag;
    }
    public void setLoginFlag(Boolean loginFlag) {
        this.loginFlag = loginFlag;
    }
    public void setResumeNo(String resumeNo) {
        this.resumeNo = resumeNo;
    }
    public String getModType() {
        return modType;
    }
    public void setModType(String modType) {
        this.modType = modType;
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
    public Date getApplyDate() {
        return applyDate;
    }
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Boolean getAddressShowFlag() {
        return addressShowFlag;
    }
    public void setAddressShowFlag(Boolean addressShowFlag) {
        this.addressShowFlag = addressShowFlag;
    }

    public Boolean getIsSysUser() {
		return isSysUser;
	}
	public void setIsSysUser(Boolean isSysUser) {
		this.isSysUser = isSysUser;
	}
	public Integer getDisplayEnResume() {
        return displayEnResume;
    }

    public void setDisplayEnResume(Integer displayEnResume) {
        this.displayEnResume = displayEnResume;
    }

    //============= 简历内部使用的Vo ===================
    public static class IntentInfoVo{
        private String id;               //id编号
        private String jobType;       //工作类型
        private String keywords;       //关键字
        private String negotiable;      //待遇
        private String treatment;      //待遇
        private String salary;        //期望薪水
        private String otherMandate;  //其他要求
        private String jobCode;        //期望工作职位
        private String jobName;        //自定义的职位
        private String jobLocation;    //期望工作地点
        private String checkindate;   //到岗时间
        private String professionSkill;//职业技能
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getJobType() {
            return jobType;
        }
        public void setJobType(String jobType) {
            this.jobType = jobType;
        }
        public String getKeywords() {
            return keywords;
        }
        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }
        public String getTreatment() {
            return treatment;
        }
        public void setTreatment(String treatment) {
            this.treatment = treatment;
        }
        public String getSalary() {
            return salary;
        }
        public void setSalary(String salary) {
            this.salary = salary;
        }
        public String getJobCode() {
            return jobCode;
        }
        public void setJobCode(String jobCode) {
            this.jobCode = jobCode;
        }
        public String getJobName() {
            return jobName;
        }
        public void setJobName(String jobName) {
            this.jobName = jobName;
        }
        public String getJobLocation() {
            return jobLocation;
        }
        public void setJobLocation(String jobLocation) {
            this.jobLocation = jobLocation;
        }
        public String getCheckindate() {
            return checkindate;
        }
        public void setCheckindate(String checkindate) {
            this.checkindate = checkindate;
        }
        public String getProfessionSkill() {
            return professionSkill;
        }
        public void setProfessionSkill(String professionSkill) {
            this.professionSkill = professionSkill;
        }
		public String getNegotiable() {
			return negotiable;
		}
		public void setNegotiable(String negotiable) {
			this.negotiable = negotiable;
		}
		public String getOtherMandate() {
			return otherMandate;
		}
		public void setOtherMandate(String otherMandate) {
			this.otherMandate = otherMandate;
		}
        
    }

    public static class LanguageInfoVo{
        private String id;                   //id编号
        private String skill;             //技能类别
        private String level;             //技能水平
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getSkill() {
            return skill;
        }
        public void setSkill(String skill) {
            this.skill = skill;
        }
        public String getLevel() {
            return level;
        }
        public void setLevel(String level) {
            this.level = level;
        }
    }

    public static class CertificateInfoVo{
        private String id;                   //id编号
        private Date acquireDate;          //获得时间
        private String certificateName;    //证书名称
        private String grade;              //成绩
        private String certificateFileName;//证书文件名称
        private String certificatePath;    //证书路径
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public Date getAcquireDate() {
            return acquireDate;
        }
        public void setAcquireDate(Date acquireDate) {
            this.acquireDate = acquireDate;
        }
        public String getCertificateName() {
            return certificateName;
        }
        public void setCertificateName(String certificateName) {
            this.certificateName = certificateName;
        }
        public String getGrade() {
            return grade;
        }
        public void setGrade(String grade) {
            this.grade = grade;
        }
        public String getCertificateFileName() {
            return certificateFileName;
        }
        public void setCertificateFileName(String certificateFileName) {
            this.certificateFileName = certificateFileName;
        }
        public String getCertificatePath() {
            return certificatePath;
        }
        public void setCertificatePath(String certificatePath) {
            this.certificatePath = certificatePath;
        }

        public String getUploadImageUrl(){
            if(StringUtils.isNotBlank(getCertificatePath()) && StringUtils.isNotBlank(getCertificatePath())) {
                String url;
                if(new DateTime(Constants.EYAS_ACTIVE_TIME).isAfter(new DateTime(getCertificatePath()))){ //旧数据
                    url = Constants.EYAS_IMAGE_URL + "/content/upload/resource/"+getCertificatePath()+"/"+getCertificateFileName();
                } else {
                    url = Constants.EYAS_IMAGE_URL + "/content/"+Constants.UPLOAD_TYPE_PER_CERTIFICATION+"/"+getCertificatePath()+"/"+getCertificateFileName();
                }
                return url;
            } else {
                return "";
            }
        }
    }

    public static class ProjectInfoVo{
        private String id;               //id编号
        private String begin;         //开始
        private String end;          //结束
        private String projectName;    //项目名称
        private String proDescribe;    //项目描述
        private String dutyDescribe;   //责任描述
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getBegin() {
            return begin;
        }
        public void setBegin(String begin) {
            this.begin = begin;
        }
        public String getEnd() {
            return end;
        }
        public void setEnd(String end) {
            this.end = end;
        }
        public String getProjectName() {
            return projectName;
        }
        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }
        public String getProDescribe() {
            return proDescribe;
        }
        public void setProDescribe(String proDescribe) {
            this.proDescribe = proDescribe;
        }
        public String getDutyDescribe() {
            return dutyDescribe;
        }
        public void setDutyDescribe(String dutyDescribe) {
            this.dutyDescribe = dutyDescribe;
        }
    }

    public static class AccessoryInfoVo{
        private String id;               //id编号
        private String uploadName;     //附件名称
        private String opusPath;       //作品路径
        private String description;    //附件描述
        private Date uploadDate;       //上传时间
        private String uploadPath;     //附件路径
        private String fileName;       //文件名称
        private String show;          //是否显示简历中：1为显示，0为不显示
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public void setShow(String show) {
            this.show = show;
        }
        public String getShow() {
            return show;
        }
        public Date getUploadDate() {
            return uploadDate;
        }
        public void setUploadDate(Date uploadDate) {
            this.uploadDate = uploadDate;
        }
        public String getUploadName() {
            return uploadName;
        }
        public void setUploadName(String uploadName) {
            this.uploadName = uploadName;
        }
        public String getOpusPath() {
            return opusPath;
        }
        public void setOpusPath(String opusPath) {
            this.opusPath = opusPath;
        }
        public String getUploadPath() {
            return uploadPath;
        }
        public void setUploadPath(String uploadPath) {
            this.uploadPath = uploadPath;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getFileName() {
            return fileName;
        }
        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        //========== 图片路径hack ==========
        public String getUploadImageUrl(){
            if(StringUtils.isNotBlank(getUploadPath()) && StringUtils.isNotBlank(getUploadPath())) {
                String url;
                if(new DateTime(Constants.EYAS_ACTIVE_TIME).isAfter(new DateTime(getUploadPath()))){ //旧数据
                    url = Constants.EYAS_IMAGE_URL + "/content/upload/resource/"+getUploadPath()+"/"+getFileName();
                } else {
                    url = Constants.EYAS_IMAGE_URL + "/content/"+Constants.UPLOAD_TYPE_PER_ATTACHMENT+"/"+getUploadPath()+"/"+getFileName();
                }
                return url;
            } else {
                return "";
            }
        }
    }
    
    public static class EducationInfoVo{
        private String id;                       //id编号
        private String begin;         //开始
        private String end;          //结束
        private String schoolName;             //学校名称
        private String speciality;             //专业名称
        private String creDate;                  //创建时间
        private String updDate;                  //修改时间
        private String specialityDescription;  //专业描述
        private String degree;                //学历
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getBegin() {
            return begin;
        }
        public void setBegin(String begin) {
            this.begin = begin;
        }
        public String getEnd() {
            return end;
        }
        public void setEnd(String end) {
            this.end = end;
        }
        public String getSchoolName() {
            return schoolName;
        }
        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }
        public String getSpeciality() {
            return speciality;
        }
        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }
        public String getCreDate() {
            return creDate;
        }
        public void setCreDate(String creDate) {
            this.creDate = creDate;
        }
        public String getUpdDate() {
            return updDate;
        }
        public void setUpdDate(String updDate) {
            this.updDate = updDate;
        }
        public String getSpecialityDescription() {
            return specialityDescription;
        }
        public void setSpecialityDescription(String specialityDescription) {
            this.specialityDescription = specialityDescription;
        }
        public String getDegree() {
            return degree;
        }
        public void setDegree(String degree) {
            this.degree = degree;
        }
    }
    
    public static class TrainInfoVo{
        private String id;                   //id编号
        private String begin;         //开始
        private String end;          //结束
        private String trainSchoolName;    //培训机构名称
        private String trainCourse;        //培训课程
        private String certificate;        //证书名称
        private String creDate;              //创建时间
        private String updDate;              //修改时间
        private String courseDescription;  //培训课程描述
        private String place;             //培训地点
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getBegin() {
            return begin;
        }
        public void setBegin(String begin) {
            this.begin = begin;
        }
        public String getEnd() {
            return end;
        }
        public void setEnd(String end) {
            this.end = end;
        }
        public String getTrainSchoolName() {
            return trainSchoolName;
        }
        public void setTrainSchoolName(String trainSchoolName) {
            this.trainSchoolName = trainSchoolName;
        }
        public String getTrainCourse() {
            return trainCourse;
        }
        public void setTrainCourse(String trainCourse) {
            this.trainCourse = trainCourse;
        }
        public String getCertificate() {
            return certificate;
        }
        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }
        public String getCreDate() {
            return creDate;
        }
        public void setCreDate(String creDate) {
            this.creDate = creDate;
        }
        public String getUpdDate() {
            return updDate;
        }
        public void setUpdDate(String updDate) {
            this.updDate = updDate;
        }
        public String getCourseDescription() {
            return courseDescription;
        }
        public void setCourseDescription(String courseDescription) {
            this.courseDescription = courseDescription;
        }
        public String getPlace() {
            return place;
        }
        public void setPlace(String place) {
            this.place = place;
        }
    }
    
    public static class WorkInfoVo{
        private String id;                   //id编号
        private String begin;         //开始
        private String end;          //结束
        private String comName;            //公司名称
        private String comType;           //公司性质
        private String comCalling;        //公司行业
        private String jobName;            //职位名称（自定义）
        private String jobFunctionId;     //职位Id（已有类别）
        private String description;        //工作描述
        private String leftreason;         //离职愿意
        private String section;            //部门名称
        private String comScale;           //企业规模
        private String creDate;              //创建时间
        private String updDate;              //修改时间
        private String timeDiff;            //时间差
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getBegin() {
            return begin;
        }
        public void setBegin(String begin) {
            this.begin = begin;
        }
        public String getEnd() {
            return end;
        }
        public void setEnd(String end) {
            this.end = end;
        }
        public String getComName() {
            return comName;
        }
        public void setComName(String comName) {
            this.comName = comName;
        }
        public String getComType() {
            return comType;
        }
        public void setComType(String comType) {
            this.comType = comType;
        }
        public String getComCalling() {
            return comCalling;
        }
        public void setComCalling(String comCalling) {
            this.comCalling = comCalling;
        }
        public String getJobName() {
            return jobName;
        }
        public void setJobName(String jobName) {
            this.jobName = jobName;
        }
        public String getJobFunctionId() {
            return jobFunctionId;
        }
        public void setJobFunctionId(String jobFunctionId) {
            this.jobFunctionId = jobFunctionId;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getLeftreason() {
            return leftreason;
        }
        public void setLeftreason(String leftreason) {
            this.leftreason = leftreason;
        }
        public String getSection() {
            return section;
        }
        public void setSection(String section) {
            this.section = section;
        }
        public String getComScale() {
            return comScale;
        }
        public void setComScale(String comScale) {
            this.comScale = comScale;
        }
        public String getCreDate() {
            return creDate;
        }
        public void setCreDate(String creDate) {
            this.creDate = creDate;
        }
        public String getUpdDate() {
            return updDate;
        }
        public void setUpdDate(String updDate) {
            this.updDate = updDate;
        }
        public String getTimeDiff() {
            return timeDiff;
        }
        public void setTimeDiff(String timeDiff) {
            this.timeDiff = timeDiff;
        }
    }
    
    public static class PerUserVo{
        private Integer id;
        private Integer loginCount;           //登录次数
        private Date freDate;                 //刷新时间
        private Date createDate;              //创建时间
        private Date loginDate;               //登录时间
        private Integer publicSettings;       //是否对智通公开默认为0 公开，1：不公开；
        private String comId;                 //过滤的企业ID
        private Integer vipMember;            //是否为个人会员 默认为0 ，1为会员
        private Integer refreshRemind;        //刷新提醒：0表示超过15天没有更新就提示，1表示永不提示。
        private String userName;              //个人用户简历名称
        private Integer gender;               //性别：1为男，2为女
        private Date birthday;                //出生日期
        private Integer location;             //所在地省/市所对应的 编号
        private Integer locationTown;         //所在地城镇所对应的编号
        private String email;                 //电子邮箱
//        private Integer hiddenEmail;          //电子邮箱是否显示：0为显示，1为隐藏
        private Integer cardType;             //证件类型
        private String cardNum;               //证件号码
        private Integer hometown;             //户口所在地 省/市所对应编码
        private Integer hometownTown;         //户口所在地 城镇对应的编码
        private String phone;                 //固定电话
//        private Integer hiddenPhone;          //是否显示固定电话：0为显示、1为隐藏
        private String mobile;                //手机号码
//        private Integer hiddenMobile;         //是否显示手机号码：0为显示、1为隐藏
        private String nation;                //民族
        private Integer marriage;             //婚姻状况所对应的编码
        private Integer stature;              //身高
        private Integer weight;               //体重
        private String zipcode;               //邮编
        private Integer mobileActivation;     //手机激活：0：未激活；1：已激活；
        private Integer identification;       //实名认证：0：未认证；1：已认证；
        private Integer mailActivation;       //邮件激活：0：未激活；1：已激活；
        private String address;               //详细地址
        private String homepage;              //个人主页
        private String qq;                    //QQ
        private Integer political;            //政治面貌
        private Integer jobyearType;          //工作年限类型
        private Integer jobState;             //求职状态类型
        private Integer nowSalary;            //目前薪水
        private String userNameEn;            //英文名称
        private String addressEn;             //英文地址
        private String nationEn;             //英文民族
        private String fileName;              //头像文件名称
        private String filePath;              //头像文件路径  
        private Integer resId;                //默认简历ID
        private Integer refreshDays;          //多少天没有刷新
        private String mobilePlace;
        
        public PerUserVo() {}
        
        public PerUserVo(PerUser perUser) {
            BeanUtils.copyProperties(perUser, this);
        }
        
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public Integer getLoginCount() {
            return loginCount;
        }
        public void setLoginCount(Integer loginCount) {
            this.loginCount = loginCount;
        }
        public Date getFreDate() {
            return freDate;
        }
        public void setFreDate(Date freDate) {
            this.freDate = freDate;
        }
        public Date getCreateDate() {
            return createDate;
        }
        public void setCreateDate(Date createDate) {
            this.createDate = createDate;
        }
        public Date getLoginDate() {
            return loginDate;
        }
        public void setLoginDate(Date loginDate) {
            this.loginDate = loginDate;
        }
        public Integer getPublicSettings() {
            return publicSettings;
        }
        public void setPublicSettings(Integer publicSettings) {
            this.publicSettings = publicSettings;
        }
        public String getComId() {
            return comId;
        }
        public void setComId(String comId) {
            this.comId = comId;
        }
        public Integer getVipMember() {
            return vipMember;
        }
        public void setVipMember(Integer vipMember) {
            this.vipMember = vipMember;
        }
        public Integer getRefreshRemind() {
            return refreshRemind;
        }
        public void setRefreshRemind(Integer refreshRemind) {
            this.refreshRemind = refreshRemind;
        }
        public String getUserName() {
            return userName;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }
        public Integer getGender() {
            return gender;
        }
        public void setGender(Integer gender) {
            this.gender = gender;
        }
        public Date getBirthday() {
            return birthday;
        }
        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
        public Integer getLocation() {
            return location;
        }
        public void setLocation(Integer location) {
            this.location = location;
        }
        public Integer getLocationTown() {
            return locationTown;
        }
        public void setLocationTown(Integer locationTown) {
            this.locationTown = locationTown;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public Integer getCardType() {
            return cardType;
        }
        public void setCardType(Integer cardType) {
            this.cardType = cardType;
        }
        public String getCardNum() {
            return cardNum;
        }
        public void setCardNum(String cardNum) {
            this.cardNum = cardNum;
        }
        public Integer getHometown() {
            return hometown;
        }
        public void setHometown(Integer hometown) {
            this.hometown = hometown;
        }
        public Integer getHometownTown() {
            return hometownTown;
        }
        public void setHometownTown(Integer hometownTown) {
            this.hometownTown = hometownTown;
        }
        public String getPhone() {
            return phone;
        }
        public void setPhone(String phone) {
            this.phone = phone;
        }
        public String getMobile() {
            return mobile;
        }
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        public String getNation() {
            return nation;
        }
        public void setNation(String nation) {
            this.nation = nation;
        }
        public Integer getMarriage() {
            return marriage;
        }
        public void setMarriage(Integer marriage) {
            this.marriage = marriage;
        }
        public Integer getStature() {
            return stature;
        }
        public void setStature(Integer stature) {
            this.stature = stature;
        }
        public Integer getWeight() {
            return weight;
        }
        public void setWeight(Integer weight) {
            this.weight = weight;
        }
        public String getZipcode() {
            return zipcode;
        }
        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }
        public Integer getMobileActivation() {
            return mobileActivation;
        }
        public void setMobileActivation(Integer mobileActivation) {
            this.mobileActivation = mobileActivation;
        }
        public Integer getIdentification() {
            return identification;
        }
        public void setIdentification(Integer identification) {
            this.identification = identification;
        }
        public Integer getMailActivation() {
            return mailActivation;
        }
        public void setMailActivation(Integer mailActivation) {
            this.mailActivation = mailActivation;
        }
        public String getAddress() {
        	JsonValidator validator = new JsonValidator();
        	Gson gson = new GsonBuilder().serializeNulls().create();
            if(validator.validate(address) && JsonValidator.validJsonToVo(address, new TypeToken<AddressBo>(){})) {
            	AddressBo addressBo = gson.fromJson(address, AddressBo.class);
            	return addressBo != null ? addressBo.toString() : "";
            }else{
            	return address;
            }
        }
        public void setAddress(String address) {
            this.address = address;
        }
        public String getHomepage() {
            return homepage;
        }
        public void setHomepage(String homepage) {
            this.homepage = homepage;
        }
        public String getQq() {
            return qq;
        }
        public void setQq(String qq) {
            this.qq = qq;
        }
        public Integer getPolitical() {
            return political;
        }
        public void setPolitical(Integer political) {
            this.political = political;
        }
        public Integer getJobyearType() {
            return jobyearType;
        }
        public void setJobyearType(Integer jobyearType) {
            this.jobyearType = jobyearType;
        }
        public Integer getJobState() {
            return jobState;
        }
        public void setJobState(Integer jobState) {
            this.jobState = jobState;
        }
        public Integer getNowSalary() {
            return nowSalary;
        }
        public void setNowSalary(Integer nowSalary) {
            this.nowSalary = nowSalary;
        }
        public String getUserNameEn() {
            return userNameEn;
        }
        public void setUserNameEn(String userNameEn) {
            this.userNameEn = userNameEn;
        }
        public String getAddressEn() {
        	JsonValidator validator = new JsonValidator();
        	Gson gson = new GsonBuilder().serializeNulls().create();
            if(validator.validate(addressEn) && JsonValidator.validJsonToVo(addressEn, new TypeToken<AddressBo>(){})) {
            	AddressBo addressBo = gson.fromJson(addressEn, AddressBo.class);
            	return addressBo != null ? addressBo.toString() : "";
            }else{
            	return addressEn;
            }
        }
        public void setAddressEn(String addressEn) {
            this.addressEn = addressEn;
        }

        public String getNationEn() {
            return nationEn;
        }

        public void setNationEn(String nationEn) {
            this.nationEn = nationEn;
        }

        public String getFileName() {
            return fileName;
        }
        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
        public String getFilePath() {
            return filePath;
        }
        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
        public Integer getResId() {
            return resId;
        }
        public void setResId(Integer resId) {
            this.resId = resId;
        }
        public Integer getRefreshDays() {
            return refreshDays;
        }
        public void setRefreshDays(Integer refreshDays) {
            this.refreshDays = refreshDays;
        }
		public String getMobilePlace() {
			return mobilePlace;
		}
		public void setMobilePlace(String mobilePlace) {
			this.mobilePlace = mobilePlace;
		}
    }
    
    public static class BasicDataVo{
        private String gender;
        private Integer age;
        private String location;             //所在地省/市所对应的 编号
        private String cardType;             //证件类型
        private String hometown;             //户口所在地 省/市所对应编码
        private String marriage;             //婚姻状况所对应的编码
        private String political;            //政治面貌
        private String jobyearType;          //工作年限类型
        private String jobState;             //求职状态类型
        private String nowSalary;            //目前薪水
        private String highDegree;           //最高学历
        private String schoolName;           //学校名称
        private String speciality;           //专业名称
        private String mobilePlace;			 //手机归属地
        public String getGender() {
            return gender;
        }
        public void setGender(String gender) {
            this.gender = gender;
        }
        public Integer getAge() {
            return age;
        }
        public void setAge(Integer age) {
            this.age = age;
        }
        public String getLocation() {
            return location;
        }
        public void setLocation(String location) {
            this.location = location;
        }
        public String getCardType() {
            return cardType;
        }
        public void setCardType(String cardType) {
            this.cardType = cardType;
        }
        public String getHometown() {
            return hometown;
        }
        public void setHometown(String hometown) {
            this.hometown = hometown;
        }
        public String getMarriage() {
            return marriage;
        }
        public void setMarriage(String marriage) {
            this.marriage = marriage;
        }
        public String getPolitical() {
            return political;
        }
        public void setPolitical(String political) {
            this.political = political;
        }
        public String getJobyearType() {
            return jobyearType;
        }
        public void setJobyearType(String jobyearType) {
            this.jobyearType = jobyearType;
        }
        public String getJobState() {
            return jobState;
        }
        public void setJobState(String jobState) {
            this.jobState = jobState;
        }
        public String getNowSalary() {
            return nowSalary;
        }
        public void setNowSalary(String nowSalary) {
            this.nowSalary = nowSalary;
        }
        public String getHighDegree() {
            return highDegree;
        }
        public void setHighDegree(String highDegree) {
            this.highDegree = highDegree;
        }
        public String getSchoolName() {
            return schoolName;
        }
        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }
        public String getSpeciality() {
            return speciality;
        }
        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }
		public String getMobilePlace() {
			return mobilePlace;
		}
		public void setMobilePlace(String mobilePlace) {
			this.mobilePlace = mobilePlace;
		}
    }
    
    /**
     * 企业查看多个简历时的用到的VO
     */
    public static class ResumeViewListVo{
        private PerResume perResume; //个人简历
    	private Integer sourceId;    //来源ID
        private Integer resumeId;    //简历ID
        private String  resumeStyle; //简历样式
        private String  userName;    //个人用户名
        private String userNameEn;// 个人英文名
        private String  contentCn;   //简历中文内容
        private String  contentEn;   //简历英文内容
        private String modType;       //查看简历的前调模块(见：Contants的简历来源）
        private String checkResult;   //各种验证的结果提示
        private Boolean errorFlag;     //各种验证是否都通过
        private Boolean secretFlag;    //是否设置了完全保密(true:完全保密）
        
        public PerResume getPerResume() {
            return perResume;
        }
        public void setPerResume(PerResume perResume) {
            this.perResume = perResume;
        }
        public Integer getSourceId() {
			return sourceId;
		}
		public void setSourceId(Integer sourceId) {
			this.sourceId = sourceId;
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
        public String getContentCn() {
            return contentCn;
        }
        public void setContentCn(String contentCn) {
            this.contentCn = contentCn;
        }
        public String getContentEn() {
            return contentEn;
        }
        public void setContentEn(String contentEn) {
            this.contentEn = contentEn;
        }
        public String getUserName() {
            return userName;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }
        public String getUserNameEn() {
            return userNameEn;
        }
        public void setUserNameEn(String userNameEn) {
            this.userNameEn = userNameEn;
        }
        public String getModType() {
            return modType;
        }
        public void setModType(String modType) {
            this.modType = modType;
        }
        public String getCheckResult() {
            return checkResult;
        }
        public void setCheckResult(String checkResult) {
            this.checkResult = checkResult;
        }
        public Boolean getErrorFlag() {
            return errorFlag;
        }
        public void setErrorFlag(Boolean errorFlag) {
            this.errorFlag = errorFlag;
        }
        public Boolean getSecretFlag() {
            return secretFlag;
        }
        public void setSecretFlag(Boolean secretFlag) {
            this.secretFlag = secretFlag;
        }

    }
    
    /**
     *简历查看的联系方式VO
     */
    public static class ResumeContactVo{
        private Integer resumeId; //简历ID
        private String cardNum;   //证件号
        private String mobile;    //手机
        private List<String> companyName; //工作过的公司名称
        private String avatar;    //头像
        private String lang ;     //语种:(en,cn)
        private String contact;   //联系方式freemark返回字串
        private String checkMsg ; //检查消息
        private String mobliePlace;
        private List<AccessoryInfoVo> accessoryInfoVoList; //简历附件信息
        private List<CertificateInfoVo> certificateInfoVoList; //简历证书信息
        
        public Integer getResumeId() {
            return resumeId;
        }
        public void setResumeId(Integer resumeId) {
            this.resumeId = resumeId;
        }
        public String getLang() {
            return lang;
        }
        public void setLang(String lang) {
            this.lang = lang;
        }
        public String getContact() {
            return contact;
        }
        public void setContact(String contact) {
            this.contact = contact;
        }
        public String getCheckMsg() {
            return checkMsg;
        }
        public void setCheckMsg(String checkMsg) {
            this.checkMsg = checkMsg;
        }
        public String getCardNum() {
            return cardNum;
        }
        public void setCardNum(String cardNum) {
            this.cardNum = cardNum;
        }
        public String getMobile() {
            return mobile;
        }
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        public List<String> getCompanyName() {
            return companyName;
        }
        public void setCompanyName(List<String> companyName) {
            this.companyName = companyName;
        }
        public String getAvatar() {
            return avatar;
        }
        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getMobliePlace() {
            return mobliePlace;
        }

        public void setMobliePlace(String mobliePlace) {
            this.mobliePlace = mobliePlace;
        }
        
		public List<AccessoryInfoVo> getAccessoryInfoVoList() {
			return accessoryInfoVoList;
		}
		
		public void setAccessoryInfoVoList(List<AccessoryInfoVo> accessoryInfoVoList) {
			this.accessoryInfoVoList = accessoryInfoVoList;
		}
		
		public List<CertificateInfoVo> getCertificateInfoVoList() {
			return certificateInfoVoList;
		}
		
		public void setCertificateInfoVoList(
				List<CertificateInfoVo> certificateInfoVoList) {
			this.certificateInfoVoList = certificateInfoVoList;
		}
    }

    //========== 图片路径hack ==========
    public String getAvatarUrl(){
        //TODO: 路径需要再思考下
        if(StringUtils.isNotBlank(perUserVo.getFilePath()) && StringUtils.isNotBlank(perUserVo.getFilePath())) {
            String url;
            if(new DateTime(Constants.EYAS_ACTIVE_TIME).isAfter(new DateTime(perUserVo.getFilePath()))){ //旧数据
                url = Constants.EYAS_IMAGE_URL + "/content/upload/resource/"+perUserVo.getFilePath()+"/"+perUserVo.getFileName();
            } else {
                url = Constants.EYAS_IMAGE_URL + "/content/"+Constants.UPLOAD_TYPE_PER_PHOTO+"/"+perUserVo.getFilePath()+"/"+perUserVo.getFileName();
            }
            return url;
        } else {
            String photo="/static/img/photoBlank.gif";
            if(perUserVo.getGender() == 1){
                photo = "/static/img/photoMale.gif";
            }  if(perUserVo.getGender() == 2){
                photo = "/static/img/photoFemale.gif";
            }
            return Constants.EYAS_IMAGE_URL + photo;
        }
    }
}
    