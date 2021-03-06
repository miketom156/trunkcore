package com.job5156.core.common.helper;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.job5156.webapp.model.com.ComPosition;
import com.job5156.webapp.model.irc.IrcComPosition;
import com.job5156.webapp.model.per.PerResume;
import com.job5156.core.bo.mapping.PerResumeBo;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PosFilterHelper {

    /**
     * 当用户进行应聘的时候，判断职位的过滤限制，看看所投递的简历是否满足职位的要求
     * @param comPosition
     * @param perResume
     * @return  满足要求时返回空字符串，否则返回错误原因
     */
    public String checkPosFilter(ComPosition comPosition, PerResume perResume) {

        if(comPosition.getFilterAge() != null && comPosition.getFilterAge() == 1) {
            if(perResume.getPerUser().getBirthday() == null) {
                return "对不起，您的年龄不符合该职位的要求";
            } else {
                Integer age = Years.yearsBetween(new DateTime(perResume.getPerUser().getBirthday()), DateTime.now()).getYears();
                if(comPosition.getReqAgeMax() > 0 && comPosition.getReqAgeMax() < age) {
                    return "对不起，您的年龄不符合该职位的要求";
                }
                if(comPosition.getReqAgeMin() > 0 && comPosition.getReqAgeMin() > age) {
                    return "对不起，您的年龄不符合该职位的要求";
                }
            }
        }

        // 学历过滤
        if(comPosition.getFilterDegree() != null && comPosition.getFilterDegree() == 1) {
            String eduStr = perResume.getEducationInfo();
            if(StringUtils.isBlank(eduStr)) {
                return "对不起，您的学历不符合该职位的要求";
            }

            Integer highDegree = 0;
            List<PerResumeBo.EducationInfoVo> eduVoList = new Gson().fromJson(eduStr, new TypeToken<List<PerResumeBo.EducationInfoVo>>() {}.getType());
            for(PerResumeBo.EducationInfoVo eduVo : eduVoList) {
                highDegree = (eduVo.getDegree() != null && eduVo.getDegree() > highDegree) ? eduVo.getDegree() : highDegree;
            }

            if(highDegree < comPosition.getReqDegree()) {
                return "对不起，您的学历不符合该职位的要求";
            }
        }

        //性别过滤
        if(comPosition.getFilterGender() != null && comPosition.getFilterGender() == 1) {
            if(comPosition.getReqGender() != 0 && !comPosition.getReqGender().equals(perResume.getPerUser().getGender())) {
                return "对不起，您的性别不符合该职位的要求";
            }
        }

        //工作经验过滤
        if(comPosition.getFilterWorkYear() != null && comPosition.getFilterWorkYear() == 1) {
            if(comPosition.getReqWorkYear() > perResume.getPerUser().getJobyearType()) {
                return "对不起，您的工作经验不符合该职位的要求";
            }
        }

        //语言水平过滤
        //TODO:语言水平过滤暂时不起用
/*        if(comPosition.getReqLanguage() != null && comPosition.getReqLanguage() > 0 && comPosition.getReqLanguageLevel() != null && comPosition.getReqLanguageLevel() >0) {

            PerResumeVo vo = new PerResumeVo(perResume);
            if(vo==null ){
                return "对不起，您的语言水平不符合该职位的要求";
            }
            List<PerResumeVo.LanguageInfoVo> languageInfoVoList = vo.getLanguageInfoVoList();
            if(CollectionUtils.isEmpty(languageInfoVoList)){
                return "对不起，您的语言水平不符合该职位的要求";
            }
            for (PerResumeVo.LanguageInfoVo languageInfoVo : languageInfoVoList) {
                if(languageInfoVo.getSkill() == null || languageInfoVo.getLevel() == null){
                    return "对不起，您的语言水平不符合该职位的要求";
                }
                  if(languageInfoVo.getSkill().equals(comPosition.getReqLanguage())
                          && languageInfoVo.getLevel() < comPosition.getReqLanguageLevel()){
                      return "对不起，您的语言水平不符合该职位的要求";
                  }
            }
        }*/

        return "";
    }

    /**
     * 当用户进行预约的时候，判断职位的过滤限制，看看所投递的简历是否满足职位的要求
     * @param ircComPosition
     * @param perResume
     * @return  满足要求时返回空字符串，否则返回错误原因
     */
    public String checkIrcPosFilter(IrcComPosition ircComPosition, PerResume perResume) {

        if(ircComPosition.getFilterAge() != null && ircComPosition.getFilterAge() == 1) {
            if(perResume.getPerUser().getBirthday() == null) {
                return "对不起，您的年龄不符合该职位的要求";
            } else {
                Integer age = Years.yearsBetween(new DateTime(perResume.getPerUser().getBirthday()), DateTime.now()).getYears();
                if(ircComPosition.getReqAgeMax() > 0 && ircComPosition.getReqAgeMax() < age) {
                    return "对不起，您的年龄不符合该职位的要求";
                }
                if(ircComPosition.getReqAgeMin() > 0 && ircComPosition.getReqAgeMin() > age) {
                    return "对不起，您的年龄不符合该职位的要求";
                }
            }
        }

        // 学历过滤
        if(ircComPosition.getFilterDegree() != null && ircComPosition.getFilterDegree() == 1) {
            String eduStr = perResume.getEducationInfo();
            if(StringUtils.isBlank(eduStr)) {
                return "对不起，您的学历不符合该职位的要求";
            }

            Integer highDegree = 0;
            List<PerResumeBo.EducationInfoVo> eduVoList = new Gson().fromJson(eduStr, new TypeToken<List<PerResumeBo.EducationInfoVo>>() {}.getType());
            for(PerResumeBo.EducationInfoVo eduVo : eduVoList) {
                highDegree = (eduVo.getDegree() != null && eduVo.getDegree() > highDegree) ? eduVo.getDegree() : highDegree;
            }

            if(highDegree < ircComPosition.getReqDegree()) {
                return "对不起，您的学历不符合该职位的要求";
            }
        }

        //性别过滤
        if(ircComPosition.getFilterGender() != null && ircComPosition.getFilterGender() == 1) {
            if(ircComPosition.getReqGender() != 0 && !ircComPosition.getReqGender().equals(perResume.getPerUser().getGender())) {
                return "对不起，您的性别不符合该职位的要求";
            }
        }

        //工作经验过滤
        if(ircComPosition.getFilterWorkYear() != null && ircComPosition.getFilterWorkYear() == 1) {
            if(ircComPosition.getReqWorkYear() > perResume.getPerUser().getJobyearType()) {
                return "对不起，您的工作经验不符合该职位的要求";
            }
        }

        return "";
    }
}
