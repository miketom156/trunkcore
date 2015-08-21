package com.job5156.searcher.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.job5156.core.common.option.OptionMap;
import com.job5156.core.common.option.OptionMap.OptionType;
import com.job5156.core.common.util.ChineseCodeChangeUtil;
import com.job5156.webapp.model.per.PerResume;
import com.job5156.webapp.model.per.PerUser;
import com.job5156.core.bo.mapping.PerResumeBo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.Years;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ResumeBrief {

    @Id
    private Integer id;                    //简历信息ID
    private Integer accountId;             //个人用户账号ID
    private String resumeName;             //简历名称
    private Integer resumeType;            //简历类型:1为长沙类型的简历
    private Integer resumeId;              //简历ID  中文简历 和 英文简历相互对应
    private Integer languageType;          //语言类型:0为中文，1为英文
    private Date userLastEditDate;         //用户的最后修改时间
    private Date freDate;                  //简历刷新时间
    private Integer resumeGrade;           //简历类型：0-普通,1-精英(猎头) 2-模具人才. 101:中高级人才（进查询库）  105:中高级人才(保密级不进查询库)
    private Date passDate;                 //审核时间
    private Integer comeFrom;              //简历来源
    private Integer havePhoto;             //是否有照片：0为无，1为有
    private Integer pass;                  //-10:自杀式简历，不让登录，-9：封锁,-3：审核不合格，-2：过滤状态，-1：未开通，0:未完善，1：暂时开通，10：已开通
    private Integer readCount;             //被企业查看次数
    private Integer resFormwork;            //简历模板：0-6 默认为0
    private Double perfectNum;            //简历完整度数
    private Integer perfectInfo;           //用户简历信息的完整的0:不可投递，1：可投递（填写所有必填项）

    private Date createDate;              //创建时间
    private Date loginDate;               //登录时间
    private Integer publicSettings;       //公开状态：0-完全公开，1-完全保密，2-对智通公开
    private String userName;              //个人用户简历名称
    private Integer gender;               //性别：1为男，2为女
    private Date birthday;                //出生日期
    private Integer location;             //所在地省/市所对应的 编号
    private Integer locationTown;         //所在地城镇所对应的编号
    private String email;                 //电子邮箱
    private Integer cardType;             //证件类型
    private String cardNum;               //证件号码
    private Integer hometown;             //户口所在地 省/市所对应编码
    private Integer hometownTown;         //户口所在地 城镇对应的编码
    private String phone;                 //固定电话
    private String mobile;                //手机号码
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
    private String fileName;              //头像文件名称
    private String filePath;              //头像文件路径
    private String jsoninfo;
    private String mobilePlace;			  //手机归属地Json字符串
    private transient Boolean buyFlag;  //是否购买(此字段不入库，只做显示控制）
    @Transient
    private String[] keywords;  //关键字(此字段不入库，只用于高亮操作）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getLanguageType() {
        return languageType;
    }

    public void setLanguageType(Integer languageType) {
        this.languageType = languageType;
    }

    public Date getUserLastEditDate() {
        return userLastEditDate;
    }

    public void setUserLastEditDate(Date userLastEditDate) {
        this.userLastEditDate = userLastEditDate;
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
        return address;
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

    public String getJsoninfo() {
        return jsoninfo;
    }

    public void setJsoninfo(String jsoninfo) {
        this.jsoninfo = jsoninfo;
    }

    public Boolean getBuyFlag() {
        return buyFlag;
    }

    public void setBuyFlag(Boolean buyFlag) {
        this.buyFlag = buyFlag;
    }
    
    public String getMobilePlace() {
		return mobilePlace;
	}

	public void setMobilePlace(String mobilePlace) {
		this.mobilePlace = mobilePlace;
	}
	
	public String getJobyearStr(){
		if(jobyearType != null){
			return OptionMap.getValue(OptionType.OPT_PER_WORKYEAR, jobyearType);
		}
		return "";
	}
	
    //========== 非POJO方法 ==========
	public Map<String, Object> getJsonMap() {
        //从Jsoninfo中获取一些有用的内容，保存在一个map中
        Map<String, Object> jsonMap = Maps.newHashMap();

        if (StringUtils.isNotBlank(getJsoninfo())) {
            Gson gson = new Gson();
            Map<String, Object> jsonObjMap = gson.fromJson(getJsoninfo(), new TypeToken<Map<String, Object>>() {
            }.getType());

            PerResume resume = gson.fromJson(gson.toJson(jsonObjMap.get("resume")), PerResume.class);
            PerUser user = gson.fromJson(gson.toJson(jsonObjMap.get("user")), PerUser.class);

            PerResumeBo resumeVo = new PerResumeBo(resume);

            if (user.getGender() != null && user.getGender() > 0) {
                String str = OptionMap.getValue(OptionMap.OptionType.OPT_GENDER, user.getGender());
                jsonMap.put("genderStr", StringUtils.isNotBlank(str) ? str : "保密");
            }
            if (user.getBirthday() != null) {
                //注意，此处简历对年龄的计算公式改为仅精确到年
                DateTime bDate = new DateTime(user.getBirthday());
                bDate = bDate.monthOfYear().setCopy(1).dayOfMonth().setCopy(1);
                DateTime nDate = DateTime.now();
                nDate = nDate.monthOfYear().setCopy(1).dayOfMonth().setCopy(2);
                jsonMap.put("age", Years.yearsBetween(bDate, nDate).getYears() + "岁");//年龄
            }
            if (user.getLocation() != null) {
                String str = OptionMap.getFullAddr(user.getLocation());
                jsonMap.put("locationStr", str);
            }

            if (CollectionUtils.isNotEmpty(resumeVo.getEducationInfoVoList())) {
                int highDegree = 0;
                for (PerResumeBo.EducationInfoVo infoVo : resumeVo.getEducationInfoVoList()) {
                    if (infoVo.getDegree() != null && infoVo.getDegree() > highDegree) {
                    	//暂时先手工将MBA调整为硕士，待整体调整完后直接取VO得值
                        highDegree = infoVo.getDegree() == 7 ? 6 : infoVo.getDegree();
                        jsonMap.put("degree", infoVo.getDegree());
                        jsonMap.put("degreeStr", OptionMap.getValue(OptionMap.OptionType.OPT_PER_DEGREE, infoVo.getDegree()));
                        jsonMap.put("school", infoVo.getSchoolName());
                        jsonMap.put("speciality", infoVo.getSpeciality());
                    }
                }
            }

            PerResumeBo.IntentInfoVo intentVo = resumeVo.getIntentInfoVo();
            if (intentVo != null) {

                //期望职位
                List<String> jobNameList = Lists.newArrayList();
                List<Integer> jobCodeList = Lists.newArrayList();
                if (StringUtils.isNotBlank(intentVo.getJobCode())) {
                    for (String jobCode : intentVo.getJobCode().split(",")) {
                        String jobCodeStr = OptionMap.getValue(OptionMap.OptionType.OPT_POSITION, NumberUtils.toInt(jobCode, 0));
                        if (!jobNameList.contains(jobCodeStr)) {
                            jobNameList.add(jobCodeStr);
                        }
                        if (!jobCodeList.contains(NumberUtils.toInt(jobCode, 0))) {
                            jobCodeList.add(NumberUtils.toInt(jobCode, 0));
                        }
                    }
                }
                if (StringUtils.isNotBlank(intentVo.getJobName())) {
                    for (String jobCodeStr : intentVo.getJobName().split(",")) {
                        if (!jobNameList.contains(jobCodeStr)) {
                            jobNameList.add(jobCodeStr);
                        }
                    }
                }
                jsonMap.put("expectJobStr", StringUtils.join(jobNameList, " "));
                jsonMap.put("expectJob", StringUtils.join(jobNameList, ","));

                //期望地区
                List<String> jobLocationStrList = Lists.newArrayList();
                List<Integer> jobLocationList = Lists.newArrayList();
                if (StringUtils.isNotBlank(intentVo.getJobLocation())) {
                    for (String location : intentVo.getJobLocation().split(",")) {
                        String locationStr = OptionMap.getFullAddr(NumberUtils.toInt(location, 0));
                        if (!jobLocationStrList.contains(locationStr)) {
                            jobLocationStrList.add(locationStr);
                        }
                        if (!jobLocationList.contains(NumberUtils.toInt(location, 0))) {
                            jobLocationList.add(NumberUtils.toInt(location, 0));
                        }
                    }
                }
                jsonMap.put("expectLocationStr", StringUtils.join(jobLocationStrList, ","));
                jsonMap.put("expectLocation", StringUtils.join(jobLocationList, ","));

                //期望月薪
                if (intentVo.getSalary() != null) {
                    String salaryStr = OptionMap.getValue(OptionType.OPT_PER_NOWSALARY, intentVo.getSalary());
                    jsonMap.put("expectSalary", intentVo.getSalary());
                    jsonMap.put("expectSalaryStr", StringUtils.isNotBlank(salaryStr) ? salaryStr : "面议");
                }
                //关键字
                jsonMap.put("keyword", StringUtils.isNotBlank(intentVo.getKeywords()) ? intentVo.getKeywords() : "");
                //技能/评价
                jsonMap.put("skill", StringUtils.isNotBlank(intentVo.getProfessionSkill()) ? intentVo.getProfessionSkill() : "");

                //最近一份工作信息
                if (CollectionUtils.isNotEmpty(resumeVo.getWorkInfoVoList())) {
                    String lastJobName = "";
                    String lastComName = "";
                    String lastTimePeriod = "";
                    for (PerResumeBo.WorkInfoVo infoVo : resumeVo.getWorkInfoVoList()) {
                        if (StringUtils.isBlank(lastJobName)) {
                            lastJobName = infoVo.getJobName();
                        }
                        if (StringUtils.isBlank(lastComName)) {
                            lastComName = infoVo.getComName();
                        }
                        if (StringUtils.isBlank(lastTimePeriod)) {
                            lastTimePeriod = infoVo.getBegin() + "至" + (StringUtils.isNotBlank(infoVo.getEnd()) ? infoVo.getEnd() : "今");
                        }
                    }
                    jsonMap.put("lastJobName", lastJobName);
                    jsonMap.put("lastComName", lastComName);
                    jsonMap.put("lastTimePeriod", lastTimePeriod);
                }
            }
        }
        return jsonMap;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取经过高亮处理的jsonmap
     *
     * @return
     */
    public Map<String, Object> getJsonMapHighLight() {
        Map<String, Object> jsonObjMap = getJsonMap();
        if (ArrayUtils.isNotEmpty(keywords)) {
            for (String keyword : keywords) {
                if (jsonObjMap != null) {
                    highLightJsonMap(jsonObjMap, keyword);
                    if (StringUtils.isNotBlank(keyword)) {
                        String keywordtrdition = ChineseCodeChangeUtil.toTraditional(keyword);
                        if (!StringUtils.equals(keywordtrdition, keyword)) {
                            highLightJsonMap(jsonObjMap, keywordtrdition);
                        }
                    }
                }
            }
        }
        return jsonObjMap;
    }

    /**
     * 使得指定字符串中的关键字替换为包含于对应html标签中
     *
     * @param jsonObjMap
     * @param keyword
     */
    private void highLightJsonMap(Map<String, Object> jsonObjMap, String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return;
        }
        if (jsonObjMap.get("expectJobStr") != null &&
                jsonObjMap.get("expectJobStr").toString().toUpperCase().contains(keyword.toUpperCase())) {
            jsonObjMap.put("expectJobStr", jsonObjMap.get("expectJobStr").toString().replaceAll("(?i)(\\Q" + keyword + "\\E)", "<em>$1</em>"));
        }
        if (jsonObjMap.get("school") != null &&
                jsonObjMap.get("school").toString().toUpperCase().contains(keyword.toUpperCase())) {
            jsonObjMap.put("school", jsonObjMap.get("school").toString().replaceAll("(?i)(\\Q" + keyword + "\\E)", "<em>$1</em>"));
        }
        if (jsonObjMap.get("speciality") != null &&
                jsonObjMap.get("speciality").toString().toUpperCase().contains(keyword.toUpperCase())) {
            jsonObjMap.put("speciality", jsonObjMap.get("speciality").toString().replaceAll("(?i)(\\Q" + keyword + "\\E)", "<em>$1</em>"));
        }
        if (jsonObjMap.get("skill") != null
                && jsonObjMap.get("skill").toString().toUpperCase().contains(keyword.toUpperCase())) {
            jsonObjMap.put("skill", jsonObjMap.get("skill").toString().replaceAll("(?i)(\\Q" + keyword + "\\E)", "<em>$1</em>"));
        }
        if (jsonObjMap.get("lastJobName") != null
        		&& jsonObjMap.get("lastJobName").toString().toUpperCase().contains(keyword.toUpperCase())) {
        	jsonObjMap.put("lastJobName", jsonObjMap.get("lastJobName").toString().replaceAll("(?i)(\\Q" + keyword + "\\E)", "<em>$1</em>"));
        }
        if (jsonObjMap.get("lastComName") != null
        		&& jsonObjMap.get("lastComName").toString().toUpperCase().contains(keyword.toUpperCase())){
        	jsonObjMap.put("lastComName", jsonObjMap.get("lastComName").toString().replaceAll("(?i)(\\Q" + keyword + "\\E)", "<em>$1</em>"));
        }
        if (jsonObjMap.get("keyword") != null
        		&& jsonObjMap.get("keyword").toString().toUpperCase().contains(keyword.toUpperCase())){
        	jsonObjMap.put("keyword", jsonObjMap.get("keyword").toString().replaceAll("(?i)(\\Q" + keyword + "\\E)", "<em>$1</em>"));
        }
    }

    public PerResume getResume() {
        if (StringUtils.isNotBlank(getJsoninfo())) {
            Gson gson = new Gson();
            Map<String, Object> jsonObjMap = gson.fromJson(getJsoninfo(), new TypeToken<Map<String, Object>>() {
            }.getType());
            return gson.fromJson(gson.toJson(jsonObjMap.get("resume")), PerResume.class);
        }
        return null;
    }

    public PerUser getUser() {
        if (StringUtils.isNotBlank(getJsoninfo())) {
            Gson gson = new Gson();
            Map<String, Object> jsonObjMap = gson.fromJson(getJsoninfo(), new TypeToken<Map<String, Object>>() {
            }.getType());
            return gson.fromJson(gson.toJson(jsonObjMap.get("user")), PerUser.class);
        }
        return null;
    }
}
