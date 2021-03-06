package com.job5156.core.common.helper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.job5156.core.bo.mapping.PerResumeBo;
import com.job5156.core.common.Constants;
import com.job5156.core.common.option.OptionMap;
import com.job5156.core.common.pagination.Page;
import com.job5156.core.eao.per.PerResumeEao;
import com.job5156.core.eao.per.PerUserEao;
import com.job5156.webapp.model.com.*;
import com.job5156.webapp.model.irc.IrcReserve;
import com.job5156.webapp.model.per.PerResume;
import com.job5156.webapp.model.per.PerUser;
import com.job5156.webapp.model.per.job.PerInviteLog;
import com.job5156.webapp.model.per.job.PerPosApply;
import com.job5156.webapp.model.per.job.PerPosFavorite;
import com.job5156.webapp.model.per.job.PerViewLog;
import com.job5156.core.bo.form.ComReceiveAssistBo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 个人求职数据的mixInfo信息填充，按道理说这个应该是数据导入的时候要处理的问题
 * 由于在测试阶段数据较多，为了确保数据是能正常显示的，临时通过这里来进行处理
 * 当数据导入能完全处理这件事情的时候
 * 将这个Component以及相应的调用删除
 */
@Component
public class TempHelper {

    @Resource
    private PerUserEao perUserEao;

    @Resource
    private PerResumeEao perResumeEao;

    private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-dao.xml","applicationContext-resources.xml");

    private JdbcTemplate getJdbcTemplate() {
        return  (JdbcTemplate) ctx.getBean("jdbcTemplate");
    }

    private JdbcTemplate getJdbcTemplateAction() {
        return  (JdbcTemplate) ctx.getBean("jdbcTemplateAction");
    }

    private JdbcTemplate getJdbcTemplateIrc() {
        return  (JdbcTemplate) ctx.getBean("jdbcTemplateIrc");
    }



    //=========下面是企业部分的mixInfo

    private Map<String, String> updateResumeMixInfo(Integer perId, String updateTable) {
        PerUser perUser = perUserEao.get(perId);
        if(perUser != null) {
            PerResumeBo vo;
            try {
                vo = perResumeEao.getResumeVo(perUser.getResId());
            } catch (Exception ex) {
                vo = null;
            }

            if(vo != null) {

                int degree = 0;
                String schoolName = "";
                String speciality = "";
                if(vo.getEducationInfoVoList() != null) {
                    for(PerResumeBo.EducationInfoVo eduVo : vo.getEducationInfoVoList()) {
                        if(eduVo !=null && eduVo.getDegree() != null && eduVo.getDegree() > degree) {
                            degree = eduVo.getDegree();
                            schoolName = eduVo.getSchoolName();
                            speciality = eduVo.getSpeciality();
                        }
                    }
                }

                ComReceiveAssistBo craVo = new ComReceiveAssistBo();
                if(vo.getWorkInfoVoList() != null && CollectionUtils.size(vo.getWorkInfoVoList()) > 0){
                    PerResumeBo.WorkInfoVo workVo = vo.getWorkInfoVoList().get(0);
                    craVo.setLastPosName(workVo.getJobName());
//                        craVo.setLastJobLocation(workVo.get); //个人简历无此数据
                    craVo.setComName(workVo.getComName());
                    craVo.setStart(workVo.getBegin());
                    craVo.setEnd(workVo.getEnd());
                }

                craVo.setJobyearType(perUser.getJobyearType());
                craVo.setMobile(perUser.getMobile());
                if(vo.getIntentInfoVo() != null) {
                    craVo.setSkill(vo.getIntentInfoVo().getProfessionSkill());
                }
//                    craVo.setFileName();
//                    craVo.setFilePath();

                //计算年龄的公式修改为精确到年与搜索引擎匹配
                DateTime bDate =  new DateTime(perUser.getBirthday());
                bDate =  bDate.monthOfYear().setCopy(1).dayOfMonth().setCopy(1);
                DateTime nDate = DateTime.now() ;
                nDate =  nDate.monthOfYear().setCopy(1).dayOfMonth().setCopy(2);
                Integer age = Years.yearsBetween(bDate, nDate).getYears();

                if(perUser.getResId() != null && perUser.getResId() > 0 && StringUtils.isNotBlank(perUser.getUserName())) {
                    getJdbcTemplateAction().update("update " + updateTable + " set " +
                            "resume_id = ? , " +
                            "user_name=? , " +
                            "gender = ? , " +
//                        "job_location = ? , " +
//                        "location = ? , " +
                            "age = ? , " +
                            "degree = ? , " +
                            "mix_info = ? , " +
                            "school_name = ? , " +
                            "speciality = ?, " +
                            "jobyear_type = ? " +
                            "where per_user_id = ?",
                            perUser.getResId(),
                            perUser.getUserName(),
                            perUser.getGender(),
//                            vo.getIntentInfoVo().getJobLocation(),
//                            "",
                            (age < 16 || age > 65) ? 16 : age,
                            degree,
                            (new Gson()).toJson(craVo),
                            schoolName,
                            speciality,
                            perUser.getJobyearType(),
                            perUser.getId()
                    );
                }

                Map<String, String> map = Maps.newHashMap();
                map.put("resumeId", String.valueOf(perUser.getResId()));
                map.put("userName", perUser.getUserName());
                map.put("gender", String.valueOf(perUser.getGender()));
                map.put("age", String.valueOf((age < 16 || age > 65) ? 16 : age));
                map.put("degree", String.valueOf(degree));
                map.put("mixInfo", (new Gson()).toJson(craVo));
                map.put("schoolName", schoolName);
                map.put("speciality", speciality);
                map.put("schoolName", schoolName);
                map.put("jobyearType", String.valueOf(perUser.getJobyearType()));

                return map;
            }
        }
        return null;
    }

    public void fillComReceiveMixInfo(ComReceive comReceive) {
        if(comReceive != null && StringUtils.isBlank(comReceive.getMixInfo())) {
            Integer perId = comReceive.getPerUserId();

            try {
                Map<String, String> map = updateResumeMixInfo(perId, " com_receive ");

                if(NumberUtils.toInt(map.get("resumeId"),0) > 0 && map.get("userName") != null && StringUtils.isNotBlank(ObjectUtils.toString(map.get("userName")))) {
                    comReceive.setResumeId(NumberUtils.toInt(map.get("resumeId"),0));
                    comReceive.setUserName(map.get("userName"));
                    comReceive.setGender(NumberUtils.toInt(map.get("gender"),0));
                    comReceive.setAge(NumberUtils.toInt(map.get("age"),0));
                    comReceive.setDegree(NumberUtils.toInt(map.get("degree"),0));
                    comReceive.setMixInfo(map.get("mixInfo"));
                    comReceive.setSchoolName(map.get("schoolName"));
                    comReceive.setSpeciality(map.get("speciality"));
                    comReceive.setSchoolName(map.get("schoolName"));
                    comReceive.setJobyearType(NumberUtils.toInt(map.get("jobyearType"),0));
                } else {
                    getJdbcTemplateAction().update("UPDATE com_receive SET mix_info = ? WHERE id=?", "{}", comReceive.getId());
                    comReceive.setMixInfo("{}");
                }
            } catch (Exception ex) {
                //不处理
            }
        }
    }

    public void fillComRecycleMixInfo(ComRecycle comRecycle) {
        if(comRecycle != null && StringUtils.isBlank(comRecycle.getMixInfo())) {
            Integer perId = comRecycle.getPerUserId();

            try {
                Map<String, String> map = updateResumeMixInfo(perId, " com_recycle ");

                if(NumberUtils.toInt(map.get("resumeId"),0) > 0 && map.get("userName") != null && StringUtils.isNotBlank(ObjectUtils.toString(map.get("userName")))) {
                    comRecycle.setResumeId(NumberUtils.toInt(map.get("resumeId"),0));
                    comRecycle.setUserName(map.get("userName"));
                    comRecycle.setGender(NumberUtils.toInt(map.get("gender"),0));
                    comRecycle.setAge(NumberUtils.toInt(map.get("age"),0));
                    comRecycle.setDegree(NumberUtils.toInt(map.get("degree"),0));
                    comRecycle.setMixInfo(map.get("mixInfo"));
                    comRecycle.setSchoolName(map.get("schoolName"));
                    comRecycle.setSpeciality(map.get("speciality"));
                    comRecycle.setSchoolName(map.get("schoolName"));
                    comRecycle.setJobyearType(NumberUtils.toInt(map.get("jobyearType"),0));
                } else {
                    getJdbcTemplateAction().update("UPDATE com_recycle SET mix_info = ? WHERE id=?", "{}", comRecycle.getId());
                    comRecycle.setMixInfo("{}");
                }
            } catch (Exception ex) {
                //不处理
            }
        }
    }

    public void fillComStoreMixInfo(ComStore comStore) {
        if(comStore != null && StringUtils.isBlank(comStore.getMixInfo())) {
            Integer perId = comStore.getPerUserId();

            try {
                Map<String, String> map = updateResumeMixInfo(perId, " com_store ");
                if(NumberUtils.toInt(map.get("resumeId"),0) > 0 && map.get("userName") != null && StringUtils.isNotBlank(ObjectUtils.toString(map.get("userName")))) {
                    comStore.setResumeId(NumberUtils.toInt(map.get("resumeId"),0));
                    comStore.setUserName(map.get("userName"));
                    comStore.setGender(NumberUtils.toInt(map.get("gender"),0));
                    comStore.setAge(NumberUtils.toInt(map.get("age"),0));
                    comStore.setDegree(NumberUtils.toInt(map.get("degree"),0));
                    comStore.setMixInfo(map.get("mixInfo"));
                    comStore.setSchoolName(map.get("schoolName"));
                    comStore.setSpeciality(map.get("speciality"));
                    comStore.setSchoolName(map.get("schoolName"));
                    comStore.setJobyearType(NumberUtils.toInt(map.get("jobyearType"),0));
                } else {
                    getJdbcTemplateAction().update("UPDATE com_store SET mix_info = ? WHERE id=?", "{}", comStore.getId());
                    comStore.setMixInfo("{}");
                }
            } catch (Exception ex) {
                //不处理
            }
        }
    }

    public void fillComBlacklistMixInfo(ComBlacklist comBlacklist) {
        if(comBlacklist != null && StringUtils.isBlank(comBlacklist.getMixInfo())) {
            Integer perId = comBlacklist.getPerUserId();

            try {
                Map<String, String> map = updateResumeMixInfo(perId, " com_blacklist ");
                if(NumberUtils.toInt(map.get("resumeId"),0) > 0 && map.get("userName") != null && StringUtils.isNotBlank(ObjectUtils.toString(map.get("userName")))) {
                    comBlacklist.setResumeId(NumberUtils.toInt(map.get("resumeId"),0));
                    comBlacklist.setUserName(map.get("userName"));
                    comBlacklist.setGender(NumberUtils.toInt(map.get("gender"),0));
                    comBlacklist.setAge(NumberUtils.toInt(map.get("age"),0));
                    comBlacklist.setDegree(NumberUtils.toInt(map.get("degree"),0));
                    comBlacklist.setMixInfo(map.get("mixInfo"));
                    comBlacklist.setSchoolName(map.get("schoolName"));
                    comBlacklist.setSpeciality(map.get("speciality"));
                    comBlacklist.setSchoolName(map.get("schoolName"));
                    comBlacklist.setJobyearType(NumberUtils.toInt(map.get("jobyearType"),0));
                } else {
                    getJdbcTemplateAction().update("UPDATE com_blacklist SET mix_info = ? WHERE id=?", "{}", comBlacklist.getId());
                    comBlacklist.setMixInfo("{}");
                }
            } catch (Exception ex) {
                //不处理
            }
        }
    }

    public void fillComInterviewMixInfo(ComInterview comInterview) {
        if(comInterview != null && StringUtils.isBlank(comInterview.getMixInfo())) {
            Integer perId = comInterview.getPerUserId();

            try {
                Map<String, String> map = updateResumeMixInfo(perId, " com_interview ");
                if(NumberUtils.toInt(map.get("resumeId"),0) > 0 && map.get("userName") != null && StringUtils.isNotBlank(ObjectUtils.toString(map.get("userName")))) {
                    comInterview.setResumeId(NumberUtils.toInt(map.get("resumeId"),0));
                    comInterview.setUserName(map.get("userName"));
                    comInterview.setGender(NumberUtils.toInt(map.get("gender"),0));
                    comInterview.setAge(NumberUtils.toInt(map.get("age"),0));
                    comInterview.setDegree(NumberUtils.toInt(map.get("degree"),0));
                    comInterview.setMixInfo(map.get("mixInfo"));
                    comInterview.setSchoolName(map.get("schoolName"));
                    comInterview.setSpeciality(map.get("speciality"));
                    comInterview.setSchoolName(map.get("schoolName"));
                    comInterview.setJobyearType(NumberUtils.toInt(map.get("jobyearType"),0));
                } else {
                    getJdbcTemplateAction().update("UPDATE com_blacklist SET mix_info = ? WHERE id=?", "{}", comInterview.getId());
                    comInterview.setMixInfo("{}");
                }
            } catch (Exception ex) {
                //不处理
            }
        }
    }

    public PerResume fixDuplicateInfoListId(PerResume perResume) {
        if(perResume != null) {
            PerResumeBo perReusmeVo = new PerResumeBo(perResume);
            List<PerResumeBo.EducationInfoVo> educationInfoVoList = perReusmeVo.getEducationInfoVoList();
            if(CollectionUtils.isNotEmpty(educationInfoVoList) && CollectionUtils.size(educationInfoVoList) > 1){
                for (int i = 0; i < educationInfoVoList.size(); i++) {
                    PerResumeBo.EducationInfoVo vo = educationInfoVoList.get(i);
                    vo.setId((long) i + 1);
                    educationInfoVoList.set(i, vo);
                }
                perReusmeVo.setEducationInfoVoList(educationInfoVoList);
            }

            List<PerResumeBo.WorkInfoVo> workInfoVoList = perReusmeVo.getWorkInfoVoList();
            if(CollectionUtils.isNotEmpty(workInfoVoList) && CollectionUtils.size(workInfoVoList) > 1){
                for (int i = 0; i < workInfoVoList.size(); i++) {
                    PerResumeBo.WorkInfoVo vo = workInfoVoList.get(i);
                    vo.setId((long) i + 1);
                    workInfoVoList.set(i, vo);
                }
                perReusmeVo.setWorkInfoVoList(workInfoVoList);
            }

            List<PerResumeBo.AccessoryInfoVo> accessoryInfoVoList = perReusmeVo.getAccessoryInfoVoList();
            if(CollectionUtils.isNotEmpty(accessoryInfoVoList) && CollectionUtils.size(accessoryInfoVoList) > 1){
                for (int i = 0; i < accessoryInfoVoList.size(); i++) {
                    PerResumeBo.AccessoryInfoVo vo = accessoryInfoVoList.get(i);
                    vo.setId((long) i + 1);
                    accessoryInfoVoList.set(i, vo);
                }
                perReusmeVo.setAccessoryInfoVoList(accessoryInfoVoList);
            }

            List<PerResumeBo.CertificateInfoVo> certificateInfoVoList = perReusmeVo.getCertificateInfoVoList();
            if(CollectionUtils.isNotEmpty(certificateInfoVoList) && CollectionUtils.size(certificateInfoVoList) > 1){
                for (int i = 0; i < certificateInfoVoList.size(); i++) {
                    PerResumeBo.CertificateInfoVo vo = certificateInfoVoList.get(i);
                    vo.setId((long) i + 1);
                    certificateInfoVoList.set(i, vo);
                }
                perReusmeVo.setCertificateInfoVoList(certificateInfoVoList);
            }

            List<PerResumeBo.TrainInfoVo> trainInfoVoList = perReusmeVo.getTrainInfoVoList();
            if(CollectionUtils.isNotEmpty(trainInfoVoList) && CollectionUtils.size(trainInfoVoList) > 1){
                for (int i = 0; i < trainInfoVoList.size(); i++) {
                    PerResumeBo.TrainInfoVo vo = trainInfoVoList.get(i);
                    vo.setId((long) i + 1);
                    trainInfoVoList.set(i, vo);
                }
                perReusmeVo.setTrainInfoVoList(trainInfoVoList);
            }

            List<PerResumeBo.LanguageInfoVo> languageInfoVoList = perReusmeVo.getLanguageInfoVoList();
            if(CollectionUtils.isNotEmpty(languageInfoVoList) && CollectionUtils.size(languageInfoVoList) > 1){
                for (int i = 0; i < languageInfoVoList.size(); i++) {
                    PerResumeBo.LanguageInfoVo vo = languageInfoVoList.get(i);
                    vo.setId((long) i + 1);
                    languageInfoVoList.set(i, vo);
                }
                perReusmeVo.setLanguageInfoVoList(languageInfoVoList);
            }

            perResume = perReusmeVo.toModel();
        }
        return perResume;
    }

    public void fillIrcReservePerInfo(IrcReserve ircReserve) {
        if(ircReserve != null && StringUtils.isBlank(ircReserve.getUserName())) {
            Integer perId = ircReserve.getUserId();
            Integer resId = ircReserve.getResId();

            try {
                Map<String, Object> perMap = getJdbcTemplate().queryForMap("SELECT * FROM per_user WHERE id=?",perId);

                String birthday = ObjectUtils.toString(perMap.get("birthday"));
                Integer age = 16;
                if (StringUtils.isNotBlank(birthday)) {
                    age =Years.yearsBetween(new DateTime(birthday), DateTime.now()).getYears();
                    age = (age < 16 || age > 65) ? 16 : age ;
                }
                String userName = ObjectUtils.toString(perMap.get("userName"));
                Integer gender = NumberUtils.toInt(ObjectUtils.toString(perMap.get("gender")), 0);
                Integer jobyearType = NumberUtils.toInt(ObjectUtils.toString(perMap.get("jobyearType")), 0);
                String schoolName = "";
                int degree = 0;
                PerUser perUser = perUserEao.get(perId);
                if(perUser != null) {
                    PerResumeBo vo;
                    try {
                        vo = perResumeEao.getResumeVo(perUser.getResId());
                    } catch (Exception ex) {
                        vo = null;
                    }
                    if(vo != null) {
                        if(vo.getEducationInfoVoList() != null) {
                            for(PerResumeBo.EducationInfoVo eduVo : vo.getEducationInfoVoList()) {
                                if(eduVo !=null && eduVo.getDegree() != null && eduVo.getDegree() > degree) {
                                    degree = eduVo.getDegree();
                                    schoolName = eduVo.getSchoolName();
                                }
                            }
                        }
                    }
                }

                ircReserve.setUserName(userName);
                ircReserve.setGender(gender);
                ircReserve.setAge(age);
                ircReserve.setDegree(degree);
                ircReserve.setSchoolName(schoolName);
                ircReserve.setJobyearType(jobyearType);

                getJdbcTemplateIrc().update("UPDATE irc_reserve SET " +
                        " user_name = ?," +
                        " age = ?," +
                        " gender=?," +
                        " jobyear_type = ? " +
                        " WHERE " +
                        " res_id=? ",
                        userName,
                        age,
                        gender ,
                        jobyearType,
                        resId);

            } catch (Exception e) {
                //不处理
            }
        }
    }

    public void fillBuyHistoryPerInfo(ComResumeBuyHistory comResumeBuyHistory) {
        if(comResumeBuyHistory != null && StringUtils.isBlank(comResumeBuyHistory.getSchoolName())) {
            Integer perId = comResumeBuyHistory.getPerUserId();
            Integer id = comResumeBuyHistory.getId();

            try {
                Map<String, Object> perMap = getJdbcTemplate().queryForMap("SELECT * FROM per_user WHERE id=?",perId);

                String birthday = ObjectUtils.toString(perMap.get("birthday"));
                Integer age = 16;
                if (StringUtils.isNotBlank(birthday)) {
                    //计算年龄的公司修改为精确到年与搜索引擎匹配
                    DateTime bDate =  new DateTime(birthday);
                    bDate =  bDate.monthOfYear().setCopy(1).dayOfMonth().setCopy(1);
                    DateTime nDate = DateTime.now() ;
                    nDate =  nDate.monthOfYear().setCopy(1).dayOfMonth().setCopy(1);
                    age =Years.yearsBetween(bDate, nDate).getYears();
                    age = (age < 16 || age > 65) ? 16 : age ;
                }
                Integer gender = NumberUtils.toInt(ObjectUtils.toString(perMap.get("gender")), 0);
                Integer jobyearType = NumberUtils.toInt(ObjectUtils.toString(perMap.get("jobyearType")), 0);
                String schoolName = "";
                String speciality = "";
                String genderStr = OptionMap.getValueByType(OptionMap.OptionType.OPT_GENDER, gender, null);

                PerUser perUser = perUserEao.get(perId);
                if(perUser != null) {
                    PerResumeBo vo;
                    try {
                        vo = perResumeEao.getResumeVo(perUser.getResId());
                    } catch (Exception ex) {
                        vo = null;
                    }
                    if(vo != null) {
                        int degree  = 0;
                        if(vo.getEducationInfoVoList() != null) {
                            for(PerResumeBo.EducationInfoVo eduVo : vo.getEducationInfoVoList()) {
                                if(eduVo !=null && eduVo.getDegree() != null && eduVo.getDegree() > degree) {
                                    degree = eduVo.getDegree();
                                    schoolName = eduVo.getSchoolName();
                                    speciality = eduVo.getSpeciality();
                                }
                            }
                        }
                    }
                }
                String degreeStr = OptionMap.getValueByType(OptionMap.OptionType.OPT_PER_DEGREE, gender, null);;

                comResumeBuyHistory.setAge(age);
                comResumeBuyHistory.setGender(genderStr);
                comResumeBuyHistory.setDegree(degreeStr);
                comResumeBuyHistory.setSchoolName(schoolName);
                comResumeBuyHistory.setSpeciality(speciality);
                comResumeBuyHistory.setJobyear(jobyearType);

                getJdbcTemplate().update("UPDATE com_resume_buy_history SET " +
                        " age = ?," +
                        " gender = ?," +
                        " degree = ?," +
                        " school_name = ? " +
                        " speciality = ? " +
                        " jobyear = ? " +
                        " WHERE " +
                        " id=? ",
                        age,
                        genderStr,
                        degreeStr,
                        schoolName ,
                        speciality,
                        jobyearType,
                        id);

            } catch (Exception e) {
                //不处理
            }
        }
    }

    /**
     * 修复地区名称为地区代码(comstore)
     * @param page
     */
    public void fixComStoreJobLocationToCode(Page<ComStore> page) {
        if (page == null || CollectionUtils.isEmpty(page.getItems())) {
            return;
        }
        for (ComStore comStore : page.getItems()) {
            if (StringUtils.isNotBlank(comStore.getJobLocation())) {
                List<Integer> jobLocationList = Lists.newArrayList();
                String[] jobLocationArr = comStore.getJobLocation().split(",");
                for (String location : jobLocationArr) {
                    Integer code = OptionMap.getCityCodeByAddr(location);
                    if (code != null && code > 0 &&  !NumberUtils.isNumber(location)) {
                        jobLocationList.add(code);
                    }
                }
                if (CollectionUtils.isNotEmpty(jobLocationList)) {
                    comStore.setJobLocation(StringUtils.join(jobLocationList, ","));
                }
            }
        }
    }

    /**
     * 修复地区名称为地区代码 (comInterview)
     * @param page
     */
    public void fixComInterviewJobLocationToCode(Page<ComInterview> page) {
        if (page == null || CollectionUtils.isEmpty(page.getItems())) {
            return;
        }
        for (ComInterview comInterview : page.getItems()) {
            if (StringUtils.isNotBlank(comInterview.getJobLocation())) {
                List<Integer> jobLocationList = Lists.newArrayList();
                String[] jobLocationArr = comInterview.getJobLocation().split(",");
                for (String location : jobLocationArr) {
                    Integer code = OptionMap.getCityCodeByAddr(location);
                    if (code != null && code > 0 && !NumberUtils.isNumber(location)) {
                        jobLocationList.add(code);
                    }
                }
                if (CollectionUtils.isNotEmpty(jobLocationList)) {
                    comInterview.setJobLocation(StringUtils.join(jobLocationList, ","));
                }
            }
        }
    }

}
