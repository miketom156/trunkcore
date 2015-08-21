package com.job5156.core.common.helper;

import com.google.common.collect.Lists;
import com.job5156.core.bo.mapping.ResumePreviewBo;
import com.job5156.core.common.option.OptionMap;
import com.job5156.core.common.util.MobileLocationUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResumePreviewHelper {

    /**
     * 把vo对象中属性值取出并获取到相应字符串值
     * @param vo
     * @return bo   
     */
    public ResumePreviewBo translateResumePreviewBo(ResumePreviewBo vo, String lang){
    	if(vo != null) {
    		vo = getValuesByBasicInfoVo(vo, lang);
            vo = getValuesByIntentInfoVo(vo, lang);
            vo = getValuesByLanguageList(vo, lang);
            vo = getValuesByWork(vo, lang);
            vo = getValuesByEducation(vo, lang);
            vo = getValuesByTrain(vo, lang);
        }
        
        return vo;
    }

    /**
     * 基本信息
     * 把vo对象中属性值取出并获取到相应字符串值
     * @param vo
     * @return bo
     */
    private ResumePreviewBo getValuesByBasicInfoVo(ResumePreviewBo vo, String lang){
        ResumePreviewBo.PerUserVo perUserVo = vo.getPerUserVo();
        ResumePreviewBo.BasicDataVo basicDataVo = new ResumePreviewBo.BasicDataVo();
        if(perUserVo != null ){
            //基本资料的必填项
            if (perUserVo.getJobyearType() != null) {
                basicDataVo.setJobyearType(OptionMap.getValueByType(OptionMap.OptionType.OPT_PER_WORKYEAR, perUserVo.getJobyearType(), lang));
            }
            if (perUserVo.getJobState() != null) {
                basicDataVo.setJobState(OptionMap.getValueByType(OptionMap.OptionType.OPT_PER_JOBSTATUS, perUserVo.getJobState(), lang));
            }
            if (perUserVo.getGender() != null) {
                basicDataVo.setGender(OptionMap.getValueByType(OptionMap.OptionType.OPT_GENDER, perUserVo.getGender(), lang));
            }
            if (perUserVo.getBirthday() != null) {
                //计算年龄的公司修改为精确到年与搜索引擎匹配
                DateTime bDate =  new DateTime(perUserVo.getBirthday());
                bDate =  bDate.monthOfYear().setCopy(1).dayOfMonth().setCopy(1);
                DateTime nDate = DateTime.now() ;
                nDate =  nDate.monthOfYear().setCopy(1).dayOfMonth().setCopy(2);
                basicDataVo.setAge(Years.yearsBetween(bDate, nDate).getYears());
            }
            if (perUserVo.getLocation() != null) {
                basicDataVo.setLocation(OptionMap.getFullAddr(perUserVo.getLocation(), lang));
            }
            //以下非必填
            if(perUserVo.getMarriage() != null){
                basicDataVo.setMarriage(OptionMap.getValueByType(OptionMap.OptionType.OPT_PER_MARRIAGE,perUserVo.getMarriage(), lang));
            }
            if(perUserVo.getNowSalary() != null){
                basicDataVo.setNowSalary(OptionMap.getValueByType(OptionMap.OptionType.OPT_PER_NOWSALARY,perUserVo.getNowSalary(), lang));
            }

            if(perUserVo.getPolitical() != null){
                basicDataVo.setPolitical(OptionMap.getValueByType(OptionMap.OptionType.OPT_PER_POLITIC,perUserVo.getPolitical(), lang));
            }

            if(perUserVo.getCardType() != null){
                basicDataVo.setCardType(OptionMap.getValueByType(OptionMap.OptionType.OPT_PER_CARDTYPE,perUserVo.getCardType(), lang));
            }

            if(perUserVo.getHometown() != null){
                basicDataVo.setHometown(OptionMap.getFullAddr(perUserVo.getHometown(), lang));
            }
            
            String json = perUserVo.getMobilePlace();
            String provinceCity = MobileLocationUtil.getProvinceCity(json);
            if(StringUtils.isNotBlank(provinceCity)){
            	perUserVo.setMobilePlace(provinceCity);
            }
        }
        vo.setBasicDataVo(basicDataVo);//perUser的数据有不完整的情况,返回new BasicDataVo防止显示报错
        return vo;
    }

    /**
     * 求职意向
     * 把vo对象中属性值取出并获取到相应字符串值
     * @param vo
     * @return bo
     */
    private ResumePreviewBo getValuesByIntentInfoVo(ResumePreviewBo vo, String lang){
        ResumePreviewBo.IntentInfoVo intentInfoVo = vo.getIntentInfoVo();
        if(intentInfoVo != null && (StringUtils.isNotEmpty(intentInfoVo.getJobCode()) || StringUtils.isNotBlank(intentInfoVo.getJobName()))){
            intentInfoVo.setJobType(OptionMap.getValueByType(OptionMap.OptionType.OPT_JOBTYPE, NumberUtils.toInt(intentInfoVo.getJobType(), 0), lang));
            List<String> jobCodeList =  Lists.newArrayList();
            if(StringUtils.isNotBlank(intentInfoVo.getJobCode())) {
            	String []jobCodeArr = intentInfoVo.getJobCode().split(",");
                for (String code : jobCodeArr) {
                    jobCodeList.add(OptionMap.getValueByType(OptionMap.OptionType.OPT_POSITION, NumberUtils.toInt(code, 0), lang));
                }
            }
            if(StringUtils.isNotBlank(intentInfoVo.getJobName())){
            	if(StringUtils.isNotBlank(StringUtils.join(jobCodeList,","))){
            		intentInfoVo.setJobCode(StringUtils.join(jobCodeList,",")+","+intentInfoVo.getJobName());
            	}else{
            		intentInfoVo.setJobCode(intentInfoVo.getJobName());
            	}
            }else{
            	intentInfoVo.setJobCode(StringUtils.join(jobCodeList,","));
            }
            List<String> jobLocationList = Lists.newArrayList();
            if(intentInfoVo.getJobLocation() != null) {
                String[] jobLocationArr = intentInfoVo.getJobLocation().split(",");
                for (String location : jobLocationArr) {
                    jobLocationList.add(OptionMap.getFullAddr((int)NumberUtils.toDouble(location, 0), lang));
                }
            }
            intentInfoVo.setJobLocation(StringUtils.join(jobLocationList,","));
            if(intentInfoVo.getCheckindate() != null){
                intentInfoVo.setCheckindate(OptionMap.getValueByType(OptionMap.OptionType.OPT_PER_ARRIVAL, NumberUtils.toInt(intentInfoVo.getCheckindate(), 0), lang));
            }
            if(intentInfoVo.getSalary() != null){
                if(NumberUtils.toInt(intentInfoVo.getSalary()) == 0 ){//选择面议时,可面议空
                    intentInfoVo.setNegotiable("");
                }
               intentInfoVo.setSalary(OptionMap.getValueByType(OptionMap.OptionType.OPT_SALARY, NumberUtils.toInt(intentInfoVo.getSalary(), 0), lang));

            }
            vo.setIntentInfoVo(intentInfoVo);
        }
        return vo;
    }

    /**
     * 语言技能
     * 把vo对象中属性值取出并获取到相应字符串值
     * @param vo
     * @return bo
     */
    private ResumePreviewBo getValuesByLanguageList(ResumePreviewBo vo, String lang){
        List<ResumePreviewBo.LanguageInfoVo> languageInfoVoList  = vo.getLanguageInfoVoList();
        if(CollectionUtils.isNotEmpty(languageInfoVoList)){
            for (ResumePreviewBo.LanguageInfoVo infoVo :languageInfoVoList) {
                infoVo.setLevel(OptionMap.getValueByType(OptionMap.OptionType.OPT_LANGUAGE, NumberUtils.toInt(infoVo.getLevel(), 0), lang));
                infoVo.setSkill(OptionMap.getValueByType(OptionMap.OptionType.OPT_LANGUAGE, NumberUtils.toInt(infoVo.getSkill(), 0), lang));

            }
            vo.setLanguageInfoVoList(languageInfoVoList);
        }
        return vo;
    }

    /**
     * 工作经验
     * 把vo对象中属性值取出并获取到相应字符串值
     * @param vo
     * @return bo
     */
    private ResumePreviewBo getValuesByWork(ResumePreviewBo vo, String lang){
        List<ResumePreviewBo.WorkInfoVo> workInfoVoList  = vo.getWorkInfoVoList();
        if(CollectionUtils.isNotEmpty(workInfoVoList)){
            for (ResumePreviewBo.WorkInfoVo infoVo :workInfoVoList) {
                infoVo.setTimeDiff(""); //这个呈现出来的效果不好，只显示为最后一份工作的工作时间，暂时去掉（空字符串在模板中自动隐藏）
                if(StringUtils.isNotEmpty(infoVo.getComType())){
                    infoVo.setComType(OptionMap.getValueByType(OptionMap.OptionType.OPT_COM_PROPERTY, NumberUtils.toInt(infoVo.getComType(), 0), lang));
                }
                if(StringUtils.isNotEmpty(infoVo.getComScale())){
                    infoVo.setComScale(OptionMap.getValueByType(OptionMap.OptionType.OPT_COM_EMPLOYEE, NumberUtils.toInt(infoVo.getComScale(), 0), lang));
                }
                if(StringUtils.isNotEmpty(infoVo.getComCalling())){
                    infoVo.setComCalling(OptionMap.getValueByType(OptionMap.OptionType.OPT_INDUSTRY, NumberUtils.toInt(infoVo.getComCalling(), 0), lang));
                }
            }
            vo.setWorkInfoVoList(workInfoVoList);
        }
        return vo;
    }

    /**
     * 教育经历
     * 把vo对象中属性值取出并获取到相应字符串值
     * @param vo
     * @return bo
     */
    private ResumePreviewBo getValuesByEducation(ResumePreviewBo vo, String lang){
        List<ResumePreviewBo.EducationInfoVo> educationInfoVoList  = vo.getEducationInfoVoList();
        if(CollectionUtils.isNotEmpty(educationInfoVoList)){
            ResumePreviewBo.BasicDataVo basicDataVo = vo.getBasicDataVo();
            basicDataVo.setHighDegree("0");
            for (ResumePreviewBo.EducationInfoVo infoVo :educationInfoVoList) {
                if(NumberUtils.toInt(basicDataVo.getHighDegree(), 0) < NumberUtils.toInt(infoVo.getDegree(), 0)){
                    basicDataVo.setHighDegree(infoVo.getDegree());
                    basicDataVo.setSchoolName(infoVo.getSchoolName());
                    basicDataVo.setSpeciality(infoVo.getSpeciality());
                }
                infoVo.setDegree(OptionMap.getValueByType(OptionMap.OptionType.OPT_PER_DEGREE, NumberUtils.toInt(infoVo.getDegree(), 0), lang));
            }
            basicDataVo.setHighDegree(OptionMap.getValueByType(OptionMap.OptionType.OPT_PER_DEGREE, NumberUtils.toInt(basicDataVo.getHighDegree(), 0), lang));
            vo.setEducationInfoVoList(educationInfoVoList);
            vo.setBasicDataVo(basicDataVo);
        }
        return vo;
    }

    /**
     * 培训经历
     * 把vo对象中属性值取出并获取到相应字符串值
     * @param vo
     * @return bo
     */
    private ResumePreviewBo getValuesByTrain(ResumePreviewBo vo, String lang){
        List<ResumePreviewBo.TrainInfoVo> trainInfoVoList  = vo.getTrainInfoVoList();
        if(CollectionUtils.isNotEmpty(trainInfoVoList)){
            for (ResumePreviewBo.TrainInfoVo infoVo :trainInfoVoList) {
                if(StringUtils.isNotEmpty(infoVo.getPlace())){
                    infoVo.setPlace(OptionMap.getCityAddr(NumberUtils.toInt(infoVo.getPlace(), 0), lang));
                }
            }
            vo.setTrainInfoVoList(trainInfoVoList);
        }
        return vo;
    }

}
