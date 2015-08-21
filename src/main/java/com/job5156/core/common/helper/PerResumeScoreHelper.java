package com.job5156.core.common.helper;

import com.google.common.collect.Maps;
import com.job5156.core.bo.mapping.PerResumeBo;
import com.job5156.webapp.model.per.PerResume;
import com.job5156.core.bo.mapping.PerUserBo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 简历完整度计算组件
 */
@Component
public class PerResumeScoreHelper {

    public static Map<String, Score> scoreMap = Maps.newHashMap();

    /**
     * 计算得分
     * @param vo
     * @return
     */
    private Double countScore(PerResumeBo vo) {
        Double score = (double) 0;
        if(vo != null) {
            score+=getBasicInfoScore(vo.getBasicInfoVo());
            score+=CollectionUtils.isNotEmpty(vo.getEducationInfoVoList())?getEduScore(vo.getEducationInfoVoList().get(0)):0;
            score+=CollectionUtils.isNotEmpty(vo.getWorkInfoVoList())?getWorkScore(vo.getWorkInfoVoList().get(0)):0;
            score+=getIntentScore(vo.getIntentInfoVo());
            score+=CollectionUtils.isNotEmpty(vo.getTrainInfoVoList())?getTrainScore(vo.getTrainInfoVoList().get(0)):0;
            score+=CollectionUtils.isNotEmpty(vo.getLanguageInfoVoList())?getLangScore(vo.getLanguageInfoVoList().get(0)):0;
            score+=CollectionUtils.isNotEmpty(vo.getProjectInfoVoList())?getProjScore(vo.getProjectInfoVoList().get(0)):0;
            score+=CollectionUtils.isNotEmpty(vo.getCertificateInfoVoList())?getCertScore(vo.getCertificateInfoVoList().get(0)):0;
            score+=CollectionUtils.isNotEmpty(vo.getAccessoryInfoVoList())?getAttacheScore(vo.getAccessoryInfoVoList().get(0)):0;
        }
        return score;
    }

    /**
     * 通过传入的简历Vo计算完整度积分
     * @param vo
     * @return
     */
    public Double getScore(PerResumeBo vo) {
        Double score = countScore(vo);
        return score;
    }

    /**
     * 通过传入的简历Model计算完整度积分
     * @param perResume
     * @return
     */
    public Double getScore(PerResume perResume) {
        return getScore(new PerResumeBo(perResume));
    }

    public Map<String, Double> getRatioMap(PerResume perResume) {
        return getRatioMap(new PerResumeBo(perResume));
    }

    public Map<String, Double> getRatioMap(PerResumeBo vo) {
        Map<String, Double> map = Maps.newHashMap();
        map.put("all", getScore(vo) / 100);
        map.put("basic", getBasicInfoScore(vo.getBasicInfoVo()) / 31);
        map.put("edu", (CollectionUtils.isNotEmpty(vo.getEducationInfoVoList())?getEduScore(vo.getEducationInfoVoList().get(0)):0) / 10);
        map.put("work", (CollectionUtils.isNotEmpty(vo.getWorkInfoVoList())?getWorkScore(vo.getWorkInfoVoList().get(0)):0) / 30);
        map.put("intent", getIntentScore(vo.getIntentInfoVo()) / 19);
        map.put("train", (CollectionUtils.isNotEmpty(vo.getTrainInfoVoList())?getTrainScore(vo.getTrainInfoVoList().get(0)):0) / 2);
        map.put("lang", (CollectionUtils.isNotEmpty(vo.getLanguageInfoVoList())?getLangScore(vo.getLanguageInfoVoList().get(0)):0) / 2);
        map.put("proj", (CollectionUtils.isNotEmpty(vo.getProjectInfoVoList())?getProjScore(vo.getProjectInfoVoList().get(0)):0) / 2);
        map.put("cert", (CollectionUtils.isNotEmpty(vo.getCertificateInfoVoList())?getCertScore(vo.getCertificateInfoVoList().get(0)):0) / 2);
        map.put("attach", (CollectionUtils.isNotEmpty(vo.getAccessoryInfoVoList())?getAttacheScore(vo.getAccessoryInfoVoList().get(0)):0) / 2);
        return map;
    }


    public Double getBasicInfoScore(PerUserBo vo) {
        Double score = (double) 0;
        if(vo != null) {
            if(StringUtils.isNotBlank(vo.getUserName())){score=putScore(Score.BASE_USER_NAME,score,0);}
            if(vo.getGender() != null && vo.getGender() > 0){score=putScore(Score.BASE_GENDER,score,0);}
            if(vo.getBirthday() != null){score=putScore(Score.BASE_BIRTHDAY,score,0);}
            if(vo.getJobyearType() != null){score=putScore(Score.BASE_JOBYEAR,score,0);}
            if(vo.getLocation() != null){score=putScore(Score.BASE_LOCATION,score,0);}
            if(StringUtils.isNotBlank(vo.getEmail())){score=putScore(Score.BASE_EMAIL,score,0);}
            if(vo.getMailActivation() != null && vo.getMailActivation() > 0){score=putScore(Score.BASE_EMAIL_ACT,score,0);}
            if(StringUtils.isNotBlank(vo.getMobile())){score=putScore(Score.BASE_MOBILE,score,0);}
            if(vo.getMobileActivation() != null && vo.getMobileActivation() > 0){score=putScore(Score.BASE_MOBILE_ACT,score,0);}
            if(vo.getJobState() != null){score=putScore(Score.BASE_JOBSTATE,score,0);}
            if(StringUtils.isNotBlank(vo.getFileName())){score=putScore(Score.BASE_BIO_PIC,score,0);}
            if(vo.getHometown() != null && vo.getHometown() > 0){score=putScore(Score.BASE_HOMETOWN,score,0);}
            if(vo.getCardType() != null){score=putScore(Score.BASE_CARD_TYPE,score,0);}
            if(StringUtils.isNotBlank(vo.getCardNum())){score=putScore(Score.BASE_ID_CARD,score,0);}
            if(vo.getNowSalary() !=null && vo.getNowSalary() > 0){score=putScore(Score.BASE_NOW_SALARY,score,0);}
            if(vo.getStature() != null && vo.getStature() > 0){score=putScore(Score.BASE_STATURE,score,0);}
            if(StringUtils.isNotBlank(vo.getNation())){score=putScore(Score.BASE_NATION,score,0);}
            if(vo.getPolitical() != null){score=putScore(Score.BASE_POLITICAL,score,0);}
            if(vo.getMarriage() != null){score=putScore(Score.BASE_MARRIAGE,score,0);}
            if(StringUtils.isNotBlank(vo.getZipcode())){score=putScore(Score.BASE_ZIPCODE,score,0);}
            if(StringUtils.isNotBlank(vo.getAddressStr())){score=putScore(Score.BASE_ADDR,score,0);}
            if(StringUtils.isNotBlank(vo.getQq())){score=putScore(Score.BASE_QQ,score,0);}
            if(vo.getIdentification() != null){score=putScore(Score.BASE_IDENTIFICATION,score,0);}
        }
        return score;
    }

    public Double getEduScore(PerResumeBo.EducationInfoVo vo){
        Double score = (double) 0;
        if(vo != null) {
            if(StringUtils.isNotBlank(vo.getBegin())){score=putScore(Score.EDU_BEGIN,score,0);}
            if(StringUtils.isNotBlank(vo.getSchoolName())){score=putScore(Score.EDU_SCHOOL,score,0);}
            if(StringUtils.isNotBlank(vo.getSpeciality())){score=putScore(Score.EDU_SPECIALITY,score,0);}
            if(vo.getDegree() != null){score=putScore(Score.EDU_DEGREE,score,0);}
            if(StringUtils.isNotBlank(vo.getSpecialityDescription())){score=putScore(Score.EDU_DESC,score,0);}
        }
        return score;
    }

    public Double getWorkScore(PerResumeBo.WorkInfoVo vo){
        Double score = (double) 0;
        if(vo != null) {
            if(StringUtils.isNotBlank(vo.getBegin())){ score = putScore(Score.WORK_BEGIN, score, 0);}
            if(StringUtils.isNotBlank(vo.getComName())){score = putScore(Score.WORK_COM_NAME, score, 0);}
            if(vo.getComType() != null){score = putScore(Score.WORK_COM_TYPE, score, 0);}
            if(vo.getComScale() != null){score = putScore(Score.WORK_COM_SCALE, score, 0);}
            if(vo.getComCalling() != null && vo.getComCalling() > 0){score = putScore(Score.WORK_COM_CALLING, score, 0);}
            if(StringUtils.isNotBlank(vo.getSection())){score = putScore(Score.WORK_COM_SECTION, score, 0);}
            if(StringUtils.isNotBlank(vo.getJobName())){score = putScore(Score.WORK_JOB_NAME, score, 0);}
            if(StringUtils.isNotBlank(vo.getLeftreason())){score = putScore(Score.WORK_LEFTREASON, score, 0);}

            if(StringUtils.isNotBlank(vo.getDescription())){
                Integer contentLength = StringUtils.length(vo.getDescription());
                if(contentLength > 200) {
                    score = putScore(Score.WORK_DESC, score, 13);
                } else if (contentLength > 100) {
                    score = putScore(Score.WORK_DESC, score, 12);
                } else if (contentLength > 20) {
                    score = putScore(Score.WORK_DESC, score, 10);
                } else {
                    score = putScore(Score.WORK_DESC, score, 7);
                }
            }
        }
        return score;
    }

    public Double getIntentScore(PerResumeBo.IntentInfoVo vo){
        Double score = (double) 0;
        if(vo != null) {
            if(StringUtils.isNotBlank(vo.getJobLocation())){score = putScore(Score.INTENT_JOB_AREA,score,0);}
            if(StringUtils.isNotBlank(vo.getJobCode())){score = putScore(Score.INTENT_JOB_CODE,score,0);}
            if(StringUtils.isNotBlank(vo.getKeywords())){
                int keywordCount = StringUtils.split(vo.getKeywords(), ",").length;
                int point = keywordCount > 5?5:keywordCount;
                score = putScore(Score.INTENT_KEYWORD,score,point);
            }
            if(vo.getSalary() != null){ score = putScore(Score.INTENT_SALARY,score,0);}
            if(vo.getCheckindate() != null){ score = putScore(Score.INTENT_CHECK_DATE,score,0);}

            if(StringUtils.isNotBlank(vo.getProfessionSkill())){
                Integer contentLength = StringUtils.length(vo.getProfessionSkill());
                if(contentLength > 200) {
                    score = putScore(Score.INTENT_SKILL,score,7);
                } else if(contentLength > 100) {
                    score =  putScore(Score.INTENT_SKILL,score,6);
                } else if(contentLength > 20) {
                    score = putScore(Score.INTENT_SKILL,score,4);
                } else {
                    score = putScore(Score.INTENT_SKILL,score,2);
                }
            }
        }
        return score;
    }

    public Double getTrainScore(PerResumeBo.TrainInfoVo vo){
        Double score = (double) 0;
        if(vo != null) {
            if(StringUtils.isNotBlank(vo.getBegin())){score = putScore(Score.TRAIN_BEGIN,score,0);}
            if(StringUtils.isNotBlank(vo.getTrainSchoolName())){score = putScore(Score.TRAIN_SCHOOL,score,0);}
            if(vo.getPlace() != null){score = putScore(Score.TRAIN_PLACE,score,0);}
            if(StringUtils.isNotBlank(vo.getTrainCourse())){score = putScore(Score.TRAIN_COURSE,score,0);}
            if(StringUtils.isNotBlank(vo.getCertificate())){score = putScore(Score.TRAIN_CERTIFICATE,score,0);}
            if(StringUtils.isNotBlank(vo.getCourseDescription())){score = putScore(Score.TRAIN_DESC,score,0);}
        }
        return score;
    }

    public Double getLangScore(PerResumeBo.LanguageInfoVo vo){
        Double score = (double) 0;
        if(vo != null) {
            if(vo.getLevel() != null){score = putScore(Score.LANG_LEVEL,score,0);}
            if(vo.getSkill() != null){score = putScore(Score.LANG_SKILL,score,0);}
        }
        return score;
    }

    public Double getProjScore(PerResumeBo.ProjectInfoVo vo){
        Double score = (double) 0;
        if(vo != null) {
            if(StringUtils.isNotBlank(vo.getBegin())){score = putScore(Score.PROJECT_BEGIN,score,0);}
            if(StringUtils.isNotBlank(vo.getProjectName())){score = putScore(Score.PROJECT_NAME,score,0);}
            if(StringUtils.isNotBlank(vo.getProDescribe())){score = putScore(Score.PROJECT_DESC,score,0);}
            if(StringUtils.isNotBlank(vo.getDutyDescribe())){score = putScore(Score.PROJECT_DUTY,score,0);}
        }
        return score;
    }

    public Double getCertScore(PerResumeBo.CertificateInfoVo vo){
        Double score = (double) 0;
        if(vo != null) {
            if(vo.getAcquireDate() != null){score= putScore(Score.CERT_DATE,score,0);}
            if(StringUtils.isNotBlank(vo.getCertificateName())){score = putScore(Score.CERT_NAME,score,0);}
            if(StringUtils.isNotBlank(vo.getCertificateFileName())){score = putScore(Score.CERT_PIC,score,0);}
            if(StringUtils.isNotBlank(vo.getGrade())){score = putScore(Score.CERT_GRADE,score,0);}
        }
        return score;
    }

    public Double getAttacheScore(PerResumeBo.AccessoryInfoVo vo){
        Double score = (double) 0;
        if(vo != null) {
            if(StringUtils.isNotBlank(vo.getUploadName())){score = putScore(Score.ATTACH_NAME,score,0);}
            if(StringUtils.isNotBlank(vo.getFileName())){score = putScore(Score.ATTACH_PIC,score,0);}
            if(StringUtils.isNotBlank(vo.getDescription())){score = putScore(Score.ATTACH_DESC,score,0);}
        }
        return score;
    }


    public Map<String,Score> getScore(PerResume perResume, Map<String,Score> scoreMap) {
        return getScore(new PerResumeBo(perResume) ,scoreMap);
    }

    /**
     * 完整度得分及检查信息封装到MAP
     * @param vo
     * @param scoreMap
     * @return
     */
    public Map<String,Score> getScore(PerResumeBo vo, Map<String,Score> scoreMap) {
        Double score = countScore(vo);
        Score.ALL.score = score;
        scoreMap = this.scoreMap;
        scoreMap.put("ALL", Score.ALL);
        return scoreMap;
    }

    /**
     * 对得分设值
     * @param scoreEnum
     * @param score //得分累加
     * @param point //当前得分 0：则表示使用默认得分
     */
    private double putScore(Score scoreEnum,double score,double point) {
        scoreEnum.score = point>0?point:scoreEnum.getDefScore();
        score += scoreEnum.score;
        scoreMap.put(scoreEnum.name(), scoreEnum);
        return score;
    }



    /**
     * 简历得分的设置
     * (栏目，默认得分，实际得分，提示信息)
     */
    public enum Score{
        BASE_USER_NAME("姓名",2,0,""),
        BASE_GENDER("性别",2,0,""),
        BASE_BIRTHDAY("出生日期",2,0,""),
        BASE_JOBYEAR("工作年限",2,0,""),
        BASE_LOCATION("现所在地",2,0,""),
        BASE_EMAIL("邮箱",2,0,""),
        BASE_EMAIL_ACT("邮箱",2,0,"邮箱未验证"),
        BASE_MOBILE("手机",2,0,""),
        BASE_MOBILE_ACT("手机",2,0,"手机未验证"),
        BASE_JOBSTATE("求职状态",2,0,""),
        BASE_BIO_PIC("头像",2,0,"头像未上传"),
        BASE_HOMETOWN("户口所在地",0.5,0,""),
        BASE_CARD_TYPE("证件分类",0.5,0,"证件分类未选择"),
        BASE_ID_CARD("身份证",0.5,0,""),
        BASE_NOW_SALARY("目前薪资",2,0,""),
        BASE_STATURE("身高",0.5,0,""),
        BASE_NATION("民族",0.5,0,"民族未填写"),
        BASE_POLITICAL("政治面貌",0.5,0,"政治面貌未填写"),
        BASE_MARRIAGE("婚姻状况",0.5,0,""),
        BASE_ZIPCODE("邮编",0.5,0,""),
        BASE_ADDR("详细地址",0.5,0,""),
        BASE_QQ("QQ",0.5,0,""),
        BASE_IDENTIFICATION("实名验证",0.5,0,"未实名验证"),

        EDU_BEGIN("入读时间",3,0,""),
        EDU_END("毕业时间",0.5,0,""),
        EDU_SCHOOL("毕业院校",3,0,""),
        EDU_SPECIALITY("专业",0.5,0,""),
        EDU_DEGREE("学历",3,0,""),
        EDU_DESC("教育经历详情",0.5,0,""),

        WORK_BEGIN("任职开始时间",3,0,""),
        WORK_COM_NAME("任职的公司名称",3,0,""),
        WORK_COM_TYPE("公司性质",0.5,0,""),
        WORK_COM_SCALE("企业规模",0.5,0,""),
        WORK_COM_CALLING("公司行业",3,0,""),
        WORK_COM_SECTION("部门名称",2,0,""),
        WORK_JOB_NAME("职位名称",3,0,""),
        WORK_LEFTREASON("离职原因",3,0,""),
        WORK_DESC("工作历经描述",7,0,""),

        INTENT_JOB_AREA("期望的工作地区",2,0,""),
        INTENT_JOB_CODE("期望的工作职位",2,0,""),
        INTENT_KEYWORD("我的亮点",5,0,""),
        INTENT_SALARY("期望薪资",2,0,""),
        INTENT_CHECK_DATE("到岗时间",1,0,""),
        INTENT_SKILL("专业技能",2,0,""),  //2-7

        TRAIN_BEGIN("培训开始日期",0.5,0,""),
        TRAIN_SCHOOL("培训机构",0.5,0,""),
        TRAIN_PLACE("培训地点",0.5,0,""),
        TRAIN_COURSE("培训课程",0.5,0,""),
        TRAIN_CERTIFICATE("培训取得的证书",0.1,0,""),
        TRAIN_DESC("培训的描述",0.3,0,""),

        LANG_LEVEL("语言水平",1,0,""),
        LANG_SKILL("语种",1,0,""),

        PROJECT_BEGIN("项目开始日期", 0.5, 0, ""),
        PROJECT_NAME("项目名称", 0.5, 0, ""),
        PROJECT_DESC("项目内容", 0.5, 0, ""),
        PROJECT_DUTY("项目业绩", 0.5, 0, ""),

        CERT_DATE("证书获得日期", 0.5, 0, ""),
        CERT_NAME("证书名称", 0.5, 0, ""),
        CERT_PIC("证书上传的图片", 0.5, 0, ""),
        CERT_GRADE("证书等级", 0.5, 0, ""),

        ATTACH_NAME("附件名称", 0.5, 0, ""),
        ATTACH_PIC("附件上传的图片", 0.5, 0, "请上传附件的图片"),
        ATTACH_DESC("附件描述", 1, 0, ""),

        ALL("总得分", 0, 0, "");

        private String item;      //栏目名称
        private double defScore;  //默认得分
        private double score;     //实际得分
        private String msg;       //检查后的信息

        private Score(String item, double defScore, double score, String msg) {
            this.item = item;
            this.defScore = defScore;
            this.score = score;
            this.msg = msg;
        }

        public String getMsg() {
            String msg = "";
            if (this.getDefScore() > this.getScore()) {
                 msg = this.msg == "" ? item + "不完整" :  this.msg;
            }
            return msg;
        }

        public String getItem() {
            return item;
        }

        public double getDefScore() {
            return defScore;
        }

        public double getScore() {
            return score;
        }
    }




}
