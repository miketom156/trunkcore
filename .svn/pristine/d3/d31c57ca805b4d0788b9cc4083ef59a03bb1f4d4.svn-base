package com.job5156.core.bo.mapping;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.job5156.core.common.CommonEnum.ResumeStatus;
import com.job5156.core.common.Constants;
import com.job5156.core.common.option.OptionMap;
import com.job5156.core.common.option.OptionMap.OptionType;
import com.job5156.core.common.util.JsonDateSerializerUtil;
import com.job5156.core.common.validator.DateFormat;
import com.job5156.core.entity.per.PerResume;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PerResumeBo {
	private Integer id;                    //简历信息ID
	private Integer accountType;           //账号类型
	private Integer accountId;             //个人用户账号ID
	private String resumeName;             //简历名称
	private Integer resumeType;            //简历类型:1为长沙类型的简历
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
	private Integer comeFrom;              //简历来源
	private Integer havePhoto;             //是否有照片：0为无，1为有
	private Integer pass;                  //-10:自杀式简历，不让登录，-9：封锁,-3：审核不合格，-1：未开通，0:未完善，1：暂时开通，10：已开通
	private Integer flag;                  //简历保密　0:完全公开　1:完全保密　2:公开但联系方式保密
	private Integer readCount;             //被企业查看次数
	private Integer resFormwork;            //简历模板：0-6 默认为0
	private Double perfectNum;            //简历完整度数
	private Integer perfectInfo;           //用户简历信息的完整的0:不可投递，1：可投递（填写所有必填项）
	private Integer age;                   //年龄
    private Integer displayEnResume;       //是否显示英文简历(0:默认不显示,1:显示)
	
	private EducationInfoVo maxDegree;             //最高学历
	
    private IntentInfoVo intentInfoVo;             //求职意向信息/我的亮点/自我描述
    private List<LanguageInfoVo> languageInfoVoList;             //语言技能信息
    private List<CertificateInfoVo> certificateInfoVoList;       //证书信息 | 获奖信息
    private List<ProjectInfoVo> projectInfoVoList;               //项目信息
    private List<AccessoryInfoVo> accessoryInfoVoList;           //附件信息
    private List<WorkInfoVo> workInfoVoList;           //工作经历信息
    private List<EducationInfoVo> educationInfoVoList; //教育经历信息
    private List<TrainInfoVo> trainInfoVoList;         //培训经历信息
    private List<SkillVo> skillVoList;                 //专业技能

    private PerUserBo basicInfoVo;

    private transient PerResume mappingModel;
    private transient boolean isDefault = false ;         //默认简历(只用于显示做识别）

    public PerResumeBo() {
    }

    public PerResumeBo(PerResume perResume) {
        if(perResume != null) {
            BeanUtils.copyProperties(perResume, this);
            Gson gson = new Gson();
            if(StringUtils.isNotBlank(perResume.getIntentInfo())) {
                IntentInfoVo intentInfoVo = gson.fromJson(perResume.getIntentInfo(), IntentInfoVo.class);
                String jobLocation = intentInfoVo.getJobLocation();
                if(StringUtils.endsWith(jobLocation, ",")){
                    intentInfoVo.setJobLocation(StringUtils.substringBeforeLast(jobLocation, ","));
                }
                this.setIntentInfoVo(intentInfoVo);
            } else {
                IntentInfoVo intentInfoVo = new IntentInfoVo();
                intentInfoVo.setJobType(1);
                this.setIntentInfoVo(intentInfoVo);
            }
            this.setLanguageInfoVoList(gson.<List<LanguageInfoVo>>fromJson(perResume.getLanguageInfo(), new TypeToken<List<LanguageInfoVo>>() {}.getType()));
            this.setCertificateInfoVoList(gson.<List<CertificateInfoVo>>fromJson(perResume.getCertificateInfo(), new TypeToken<List<CertificateInfoVo>>() {}.getType()));
            this.setProjectInfoVoList(gson.<List<ProjectInfoVo>>fromJson(perResume.getProjectInfo(), new TypeToken<List<ProjectInfoVo>>() {}.getType()));
            this.setAccessoryInfoVoList(gson.<List<AccessoryInfoVo>>fromJson(perResume.getAccessoryInfo(), new TypeToken<List<AccessoryInfoVo>>() {}.getType()));
           // this.setWorkInfoVoList(gson.<List<WorkInfoVo>>fromJson(perResume.getWorkInfo(), new TypeToken<List<WorkInfoVo>>() {}.getType()));
            this.setEducationInfoVoList(gson.<List<EducationInfoVo>>fromJson(perResume.getEducationInfo(), new TypeToken<List<EducationInfoVo>>() {}.getType()));
            this.setTrainInfoVoList(gson.<List<TrainInfoVo>>fromJson(perResume.getTrainInfo(), new TypeToken<List<TrainInfoVo>>() {}.getType()));

            this.setBasicInfoVo(new PerUserBo(perResume.getPerUser()));

            //计算出年龄
            if(this.getBasicInfoVo() !=null && this.getBasicInfoVo().getBirthday() != null) {
                //计算年龄的公式修改为精确到年与搜索引擎匹配
                DateTime bDate =  new DateTime(this.getBasicInfoVo().getBirthday());
                bDate =  bDate.monthOfYear().setCopy(1).dayOfMonth().setCopy(1);
                DateTime nDate = DateTime.now() ;
                nDate =  nDate.monthOfYear().setCopy(1).dayOfMonth().setCopy(2);
                this.setAge(Years.yearsBetween(bDate, nDate).getYears());
            }
            //专业技能
            this.setSkillVoList(gson.<List<SkillVo>>fromJson(perResume.getSkillInfo(), new TypeToken<List<SkillVo>>() {}.getType()));

            //发现工作经验的id有重复，这里做加值处理
            List<WorkInfoVo> workVoList = gson.<List<WorkInfoVo>>fromJson(perResume.getWorkInfo(), new TypeToken<List<WorkInfoVo>>() {}.getType());
            if (CollectionUtils.isNotEmpty(workVoList)) {
            	int i = 1;
            	Map<Long, WorkInfoVo> workIdMap = Maps.newHashMap();
            	
            	for (WorkInfoVo workInfoVo : workVoList) {
            		if(workIdMap.containsKey(workInfoVo.getId())) {
            			workInfoVo.setId(workInfoVo.getId() + i);
            		}
            		workIdMap.put(workInfoVo.getId(), workInfoVo);
            		
            		i++;
            	}
            }
            this.setWorkInfoVoList(workVoList);

            this.mappingModel = perResume;
        }
    }

    public String toJson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new JsonDateSerializerUtil())
                .setDateFormat(java.text.DateFormat.LONG)
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .serializeNulls()
                .create();
        return gson.toJson(this);
    }

    public PerResume toModel() {
        if(this.mappingModel != null) {
            BeanUtils.copyProperties(this, this.mappingModel);

            Gson gson = new GsonBuilder().serializeNulls().create();
            this.mappingModel.setIntentInfo(this.getIntentInfoVo() != null ? gson.toJson(this.getIntentInfoVo()) : null);
            this.mappingModel.setLanguageInfo(this.getLanguageInfoVoList() != null ? gson.toJson(this.getLanguageInfoVoList()) : null);
            this.mappingModel.setCertificateInfo(this.getCertificateInfoVoList() != null ? gson.toJson(this.getCertificateInfoVoList()) : null);
            this.mappingModel.setProjectInfo(this.getProjectInfoVoList() != null ? gson.toJson(this.getProjectInfoVoList()) : null);
            this.mappingModel.setAccessoryInfo(this.getAccessoryInfoVoList() != null ? gson.toJson(this.getAccessoryInfoVoList()) : null);
            this.mappingModel.setEducationInfo(this.getEducationInfoVoList() != null ? gson.toJson(this.getEducationInfoVoList()) : null);
            this.mappingModel.setTrainInfo(this.getTrainInfoVoList() != null ? gson.toJson(this.getTrainInfoVoList()) : null);
            this.mappingModel.setWorkInfo(this.getWorkInfoVoList() != null ? gson.toJson(this.getWorkInfoVoList()) : null);
            this.mappingModel.setSkillInfo(this.getSkillVoList() != null ? gson.toJson(this.getSkillVoList()) : null);

            return this.mappingModel;
        } else {
            return null;
        }
    }

    //============ NOT POJO =======================
    public void setEducationInfoVoList(List<EducationInfoVo> educationInfoVoList) {
        this.educationInfoVoList = educationInfoVoList;
        //获取最高学历
        if(educationInfoVoList == null) return;

        EducationInfoVo degree = null;
        for(EducationInfoVo education : educationInfoVoList) {
            Integer maxDegree = (degree == null || degree.degree == null) ? 0 : degree.degree;
            Integer curDegree = education.degree == null ? 0 : education.degree;
            if(degree == null || curDegree > maxDegree)
                degree = education;
        }
        this.setMaxDegree(degree);
    }

    //=========== SUB CLASS =======================
    /**
     *     求职意向
     */
    public static class IntentInfoVo{
        private Long id;               //id编号
        private Date creDate;          //创建时间
        private Date updDate;          //修改时间
        private Integer jobType ;      //工作类型  1:全职 2:兼职 3：实习 参见: CommonEnum.PositionPropertyType
        private String treatment;      //待遇
        private Integer salary;        //期望薪水
        private Boolean negotiable;    //可面议
        private String otherMandate;   //其他要求
        private String jobCode;        //期望工作职位
        private String jobName;        //自定义职位
        private String jobLocation;    //期望工作地点
        private Integer checkindate;   //到岗时间
        @Length(min=0,max = 400,message = "我的亮点不能超过200个中文字")
        private String keywords;       //关键字 文字变更为：我的亮点
        @Length(min=0,max = 2000,message = "自我描述不能超过1000个中文字")
        private String professionSkill;//职业技能 文字变更为：自我描述
        private Integer[] jobTypeArr;  //工作类型多选时使用


        //=========== SUB CLASS NOT POJO ============
        //到岗时间对应文字
        public String getCheckindateStr() {
            return OptionMap.getValue(OptionType.OPT_PER_ARRIVAL, checkindate);
        }

       //期望职位类型对应文字
       public String getJobCodeStr() {
           List<String> jobNameList = Lists.newArrayList();
           if(StringUtils.isNotBlank(jobCode)) {
               if(StringUtils.isNotBlank(jobCode)) {
                   for (String code : jobCode.split(",| ")) {
                       String jobCodeStr = OptionMap.getValue(OptionMap.OptionType.OPT_POSITION, NumberUtils.toInt(code, 0));
                       if(!jobNameList.contains(jobCodeStr)) {
                           jobNameList.add(jobCodeStr);
                       }
                   }
               }
           }
           return StringUtils.join(jobNameList, ",");
       }

        //期望岗位对应文字
        public String getJobNameStr() {
            List<String> jobNameList = Lists.newArrayList();

            if(StringUtils.isNotBlank(jobCode)) {
                for (String code : jobCode.split(",| ")) {
                    String jobCodeStr = OptionMap.getValue(OptionMap.OptionType.OPT_POSITION, NumberUtils.toInt(code, 0));
                    if(!jobNameList.contains(jobCodeStr)) {
                        jobNameList.add(jobCodeStr);
                    }
                }
            }
            
            if(StringUtils.isNotBlank(jobName)) {
                for (String jobCodeStr : jobName.split(",| ")) {
                    if(!jobNameList.contains(jobCodeStr)) {
                        jobNameList.add(jobCodeStr);
                    }
                }
            }

            return StringUtils.join(jobNameList, ",");
        }

        //待遇对应文字
        public String getSalaryStr() {
            if(salary == null || salary == 0) {
                return "面议";
            } else {
                return "(RMB) " + OptionMap.getValue(OptionType.OPT_SALARY, salary);
            }
        }

        //希望地区对应文字
        public String getJobLocationStr() {
            List<String> jobLocationList = Lists.newArrayList();

            if(StringUtils.isNotBlank(jobLocation)) {
                for (String location : jobLocation.split(",")) {
                    String locationStr = OptionMap.getFullAddr(NumberUtils.toInt(location, 0));
                    if(!jobLocationList.contains(locationStr)) {
                        jobLocationList.add(locationStr);
                    }
                }
            }

            return StringUtils.join(jobLocationList, ",");
        }

        //工作类型转换
        public String getJobTypeStr(){
            if(jobType != null ){
                return OptionMap.getValue(OptionType.OPT_JOBTYPE, jobType);
            }
            return "";
        }
        //========= SUB CLASS  getter && setter =================
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public Date getCreDate() {
            return creDate;
        }
        public void setCreDate(Date creDate) {
            this.creDate = creDate;
        }
        public Date getUpdDate() {
            return updDate;
        }
        public void setUpdDate(Date updDate) {
            this.updDate = updDate;
        }
        public Integer getJobType() {
            return jobType;
        }
        public void setJobType(Integer jobType) {
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
        public Integer getSalary() {
            return salary;
        }
        public void setSalary(Integer salary) {
            this.salary = salary;
        }
        public Boolean getNegotiable() {
            return negotiable;
        }
        public void setNegotiable(Boolean negotiable) {
            this.negotiable = negotiable;
        }
        public String getOtherMandate() {
            return otherMandate;
        }
        public void setOtherMandate(String otherMandate) {
            this.otherMandate = otherMandate;
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
        public Integer getCheckindate() {
            return checkindate;
        }
        public void setCheckindate(Integer checkindate) {
            this.checkindate = checkindate;
        }
        public String getProfessionSkill() {
            return professionSkill;
        }
        public void setProfessionSkill(String professionSkill) {
            this.professionSkill = professionSkill;
        }
        public Integer[] getJobTypeArr() {
            return jobTypeArr;
        }

        public void setJobTypeArr(Integer[] jobTypeArr) {
            this.jobTypeArr = jobTypeArr;
        }
    }

    /**
     * 语言
     */
    public static class LanguageInfoVo{
        private Long id;                   //id编号
        @NotNull
        private Integer skill;             //技能类别
        @NotNull
        private Integer level;             //技能水平
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public Integer getSkill() {
            return skill;
        }
        public void setSkill(Integer skill) {
            this.skill = skill;
        }
        public Integer getLevel() {
            return level;
        }
        public void setLevel(Integer level) {
            this.level = level;
        }

        //语言对应文字
        public String getSkillStr() {
            return OptionMap.getValue(OptionType.OPT_LANGUAGE, skill);
        }
        public String getLevelStr() {
            return OptionMap.getValue(OptionType.OPT_LANGUAGE, level);
        }
    }

    /**
     * 证书
     */
    public static class CertificateInfoVo{
        private Long id;                   //id编号
        @NotNull
        @DateFormat
        private Date acquireDate;          //获得时间
        @NotBlank
        private String certificateName;    //证书名称
        private String grade;              //成绩
        private String certificateFileName;//证书文件名称
        private String certificatePath;    //证书路径
        private Boolean isPrize = false;   //是否是获奖
        //======== not pojo ==================
        public String getUploadImageUrl(){
            if(StringUtils.isNotBlank(getCertificatePath()) && StringUtils.isNotBlank(getCertificatePath())) {
                String url;
                if(new DateTime(Constants.EYAS_ACTIVE_TIME).isAfter(new DateTime(getCertificatePath()))){ //旧数据
                    url = Constants.EYAS_IMAGE_URL + "/content/upload/resource/"+getCertificatePath()+"/"+getCertificateFileName();
                } else {
                    url = Constants.EYAS_IMAGE_URL + "/content/"+ Constants.UPLOAD_TYPE_PER_CERTIFICATION+"/"+getCertificatePath()+"/"+getCertificateFileName();
                }
                return url;
            } else {
                return "";
            }
        }

        public Boolean getIsPrize() {
            return isPrize==null?false:isPrize;
        }
        //======== getter && setter ========
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
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

        public void setIsPrize(Boolean isPrize) {
            this.isPrize = isPrize;
        }
    }

    /**
     * 项目经验
     */
    public static class ProjectInfoVo{
        private Long id;               //id编号
        @NotBlank
        private String begin;         //开始
        private String end;          //结束
        @NotBlank
        private String projectName;    //项目名称
        @NotBlank
        @Length(min=0,max = 600,message = "项目介绍不能超过300个中文字")
        private String proDescribe;    //项目描述
        @NotBlank
        @Length(min=0,max = 600,message = "项目业绩不能超过300个中文字")
        private String dutyDescribe;   //项目业绩
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
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

    /**
     * 附件信息
     */
    public static class AccessoryInfoVo{
        private Long id;               //id编号
        @NotBlank
        private String uploadName;     //附件名称
        private String opusPath;       //作品路径
        private String description;    //附件描述
        private Date uploadDate;       //上传时间
       // @NotBlank
        private String uploadPath;     //附件路径
       // @NotBlank
        private String fileName;       //文件名称
        private Integer show;          //是否显示简历中：1为显示，0为不显示

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
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
        public Date getUploadDate() {
            return uploadDate;
        }
        public void setUploadDate(Date uploadDate) {
            this.uploadDate = uploadDate;
        }
        public Integer getShow() {
            return show;
        }
        public void setShow(Integer show) {
            this.show = show;
        }
        public String getFileName() {
            return fileName;
        }
        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
        public String getUploadImageUrl(){
            if(StringUtils.isNotBlank(getUploadPath()) && StringUtils.isNotBlank(getUploadPath())) {
                String url;
                if(new DateTime(Constants.EYAS_ACTIVE_TIME).isAfter(new DateTime(getUploadPath()))){ //旧数据
                    url = Constants.EYAS_IMAGE_URL + "/content/upload/resource/"+getUploadPath()+"/"+getFileName();
                } else {
                    url = Constants.EYAS_IMAGE_URL + "/content/"+ Constants.UPLOAD_TYPE_PER_CERTIFICATION+"/"+getUploadPath()+"/"+getFileName();
                }
                return url;
            } else {
                return "";
            }
        }
    }

    /**
     * 教育经历
     */
    public static class EducationInfoVo{
        private Long id;                       //id编号
        @NotBlank
        private String begin;         //开始
        private String end;           //结束
        @NotBlank
        private String schoolName;             //学校名称
        private String speciality;             //专业名称
        private Date creDate;                  //创建时间
        private Date updDate;                  //修改时间
        private String specialityDescription;  //专业描述
        @NotNull
        private Integer degree;                //学历

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
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
        public Date getCreDate() {
            return creDate;
        }
        public void setCreDate(Date creDate) {
            this.creDate = creDate;
        }
        public Date getUpdDate() {
            return updDate;
        }
        public void setUpdDate(Date updDate) {
            this.updDate = updDate;
        }
        public String getSpecialityDescription() {
            return specialityDescription;
        }
        public void setSpecialityDescription(String specialityDescription) {
            this.specialityDescription = specialityDescription;
        }
        public Integer getDegree() {
            return degree;
        }
        public void setDegree(Integer degree) {
            this.degree = degree;
        }

        //学历对应文字
        public String getDegreeStr() {
            return OptionMap.getValue(OptionType.OPT_PER_DEGREE, degree);
        }
    }

    /**
     * 培训
     */
    public static class TrainInfoVo{
        private Long id;                   //id编号
        @NotBlank
        private String begin;         //开始
        private String end;           //结束
        @NotBlank
        private String trainSchoolName;    //培训机构名称
        @NotBlank
        private String trainCourse;        //培训课程
        private String certificate;        //证书名称
        private Date creDate;              //创建时间
        private Date updDate;              //修改时间
        @Length(min=0,max = 600,message = "培训描述不能超过300个中文字")
        private String courseDescription;  //培训课程描述
        private Integer place;             //培训地点

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
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
        public Date getCreDate() {
            return creDate;
        }
        public void setCreDate(Date creDate) {
            this.creDate = creDate;
        }
        public Date getUpdDate() {
            return updDate;
        }
        public void setUpdDate(Date updDate) {
            this.updDate = updDate;
        }
        public String getCourseDescription() {
            return courseDescription;
        }
        public void setCourseDescription(String courseDescription) {
            this.courseDescription = courseDescription;
        }
        public Integer getPlace() {
            return place;
        }
        public void setPlace(Integer place) {
            this.place = place;
        }

        //培训地点对应文字
        public String getPlaceStr() {return OptionMap.getFullAddr(place);}
    }

    /**
     * 工作经验
     */
    public static class WorkInfoVo{
        private Long id;                   //id编号
        @NotBlank
        private String begin;              //开始
        private String end;                //结束
        @NotBlank
        private String comName;            //公司名称
        private Integer comType;           //公司性质
        private Integer comCalling;        //公司行业
        private String jobName;            //职位名称（自定义）
        private Integer jobFunctionId;     //职位Id（已有类别）
        @NotBlank
        @Length(min=10,max = 2000,message = "工作（实习）描述不能超过1000个中文字")
        private String description;        //工作描述
        private String leftreason;         //离职愿意
        private String section;            //部门名称
        private Integer comScale;           //企业规模
        private Date creDate;              //创建时间
        private Date updDate;              //修改时间
        private Boolean isTrainee = false; //false :社会人员 true：  （实习生则WorkInfoVo为实习经历)
        private Integer salary;            //薪资水平（等级）
        private String boss;               //上司
        private Integer underlingNum;      //下属人数
        private String achievement;        //工作成果

        //企业性质对应文字
        public String getComTypeStr() {
            return OptionMap.getValue(OptionType.OPT_COM_PROPERTY, comType);
        }

        //企业行业对应文字
        public String getComCallingStr() {
            return OptionMap.getValue(OptionType.OPT_INDUSTRY, comCalling);
        }

        //企业规模对应文字
        public String getComScaleStr() {
            return OptionMap.getValue(OptionType.OPT_COM_EMPLOYEE, comScale);
        }

        //职位名称
        public String getJobNameStr() {
            if(StringUtils.isNotBlank(jobName)) {
                return jobName;
            } else {
                return OptionMap.getValue(OptionType.OPT_POSITION, jobFunctionId);
            }
        }

        //薪资字串
        public String getSalaryStr() {
            if(salary == null || salary == 0) {
                return "面议";
            } else {
                return OptionMap.getValue(OptionType.OPT_SALARY, salary) + "(RMB/月)";
            }
        }

        public Boolean getIsTrainee() {
            return isTrainee == null?false:isTrainee;
        }

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
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
        public Integer getComType() {
            return comType;
        }
        public void setComType(Integer comType) {
            this.comType = comType;
        }
        public Integer getComCalling() {
            return comCalling;
        }
        public void setComCalling(Integer comCalling) {
            this.comCalling = comCalling;
        }
        public String getJobName() {
            return jobName;
        }
        public void setJobName(String jobName) {
            this.jobName = jobName;
        }
        public Integer getJobFunctionId() {
            return jobFunctionId;
        }
        public void setJobFunctionId(Integer jobFunctionId) {
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
        public Integer getComScale() {
            return comScale;
        }
        public void setComScale(Integer comScale) {
            this.comScale = comScale;
        }
        public Date getCreDate() {
            return creDate;
        }
        public void setCreDate(Date creDate) {
            this.creDate = creDate;
        }
        public Date getUpdDate() {
            return updDate;
        }
        public void setUpdDate(Date updDate) {
            this.updDate = updDate;
        }

        public void setIsTrainee(Boolean isTrainee) {
            this.isTrainee = isTrainee;
        }

        public Integer getSalary() {
            return salary;
        }

        public void setSalary(Integer salary) {
            this.salary = salary;
        }

        public String getBoss() {
            return boss;
        }

        public void setBoss(String boss) {
            this.boss = boss;
        }

        public Integer getUnderlingNum() {
            return underlingNum;
        }

        public void setUnderlingNum(Integer underlingNum) {
            this.underlingNum = underlingNum;
        }

        public String getAchievement() {
            return achievement;
        }

        public void setAchievement(String achievement) {
            this.achievement = achievement;
        }
    }


    /**
     * 专业技能
     */
    public static class SkillVo{

        private Long id;           //序号
        private String  name;      //技能名称
        private Integer level;     //掌握程度（等级）
        private Integer duration;  //使用时长(年）
        private Boolean isTransform ; //是否完成了数据的转换

        public String getLevelStr(){ //掌握程度（等级）字串格式
            if (level != null) {
                return OptionMap.getValue(OptionType.OPT_LANGUAGE, level);
            }
            return "";
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public Boolean getIsTransform() {
            return isTransform;
        }

        public void setIsTransform(Boolean isTransform) {
            this.isTransform = isTransform;
        }
    }

    /**
     * 自我描述 -- 我的亮点
     */
    public static class BriefVo {
        private String label; //我的亮点
        private String desc;  //描述

        public String getLabel() {
            return label;
        }
        public void setLabel(String label) {
            this.label = label;
        }
        public String getDesc() {
            return desc;
        }
        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    //========================= GETTER && SETTER ===============================

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

    public PerResume getMappingModel() {
        return mappingModel;
    }

    public void setMappingModel(PerResume mappingModel) {
        this.mappingModel = mappingModel;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getDisplayEnResume() {
        return displayEnResume;
    }

    public void setDisplayEnResume(Integer displayEnResume) {
        this.displayEnResume = displayEnResume;
    }

    public String getPassStr() {
        return ResumeStatus.getResumeStatusForValue(pass).toString();
    }

    public EducationInfoVo getMaxDegree() {
        return maxDegree;
    }

    public void setMaxDegree(EducationInfoVo maxDegree) {
        this.maxDegree = maxDegree;
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

    public void setCertificateInfoVoList(List<CertificateInfoVo> certificateInfoVoList) {
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

    public List<TrainInfoVo> getTrainInfoVoList() {
        return trainInfoVoList;
    }

    public void setTrainInfoVoList(List<TrainInfoVo> trainInfoVoList) {
        this.trainInfoVoList = trainInfoVoList;
    }

    public PerUserBo getBasicInfoVo() {
        return basicInfoVo;
    }

    public void setBasicInfoVo(PerUserBo basicInfoVo) {
        this.basicInfoVo = basicInfoVo;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public List<SkillVo> getSkillVoList() {
        return skillVoList;
    }

    public void setSkillVoList(List<SkillVo> skillVoList) {
        this.skillVoList = skillVoList;
    }
}
