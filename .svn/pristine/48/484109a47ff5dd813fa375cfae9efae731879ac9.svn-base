package com.job5156.core.common.helper;

import com.job5156.core.common.CommonEnum;
import com.job5156.core.common.util.CollectionSort;
import com.job5156.webapp.model.per.PerResume;
import com.job5156.webapp.model.per.PerUser;
import com.job5156.core.bo.mapping.PerResumeBo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class PerResumeCheckHelper {

    public String resumeCompleteMsg(PerResume perResume) {

        if (perResume == null) {
            return "简历不存在";
        }
        if (StringUtils.isBlank(perResume.getWorkInfo()) || StringUtils.equals(perResume.getWorkInfo(),"[]")){
            return "工作经历未填写";
        }
        if (StringUtils.isBlank(perResume.getEducationInfo()) || StringUtils.equals(perResume.getEducationInfo(),"[]")){
            return "教育经历未填写";
        }

        // 基本资料的完整度
        PerUser perUser = perResume.getPerUser();
        if (perUser == null) {
            return "用户不存在";
        }
        if (StringUtils.isBlank(perUser.getUserName())) {
            return "未填写姓名";
        }
        if (perUser.getGender() == null) {
            return "未填写性别";
        }
        if (perUser.getBirthday() == null || new DateTime(perUser.getBirthday()).isAfter(DateTime.now().minusYears(16))) { // 不满16岁
            return "用户年龄未满16周岁，或填写有误";
        }
        if (new DateTime(perUser.getBirthday()).isBefore(DateTime.now().minusYears(60))) { // 超过60岁
            return "用户年龄超过60周岁，或填写有误";
        }
        if (perUser.getJobyearType() == null || perUser.getJobyearType() < -1) {
            return "工作年限不正确";
        }
        if (perUser.getLocation() == null || perUser.getLocation() == 0) {
            return "未填写现所在地";
        }
        if (StringUtils.isBlank(perUser.getEmail())) {
            return "未填写Email";
        }
        // if(StringUtils.isBlank(perUser.getMobile()) ||
        // StringUtils.length(perUser.getMobile()) != 11){
        // return false;
        // }

		/*
         * TODO:暂时关闭求职状态的检查 if (perUser.getJobState() == null ||
		 * perUser.getJobState() < 0) { return 求职状态不正确; }
		 */

        return "";
    }

    /**
     * 判断简历是否完善
     *
     * @param perResume
     * @return
     */
    public boolean resumeComplete(PerResume perResume) {
        if (perResume == null) {
            return false;
        }
        if (StringUtils.isBlank(perResume.getWorkInfo()) || StringUtils.equals(perResume.getWorkInfo(),"[]")){
            return false;
        }
        if (StringUtils.isBlank(perResume.getEducationInfo()) || StringUtils.equals(perResume.getEducationInfo(),"[]")){
            return false;
        }

        // 基本资料的完整度
        PerUser perUser = perResume.getPerUser();
        if (perUser == null) {
            return false;
        }
        if (StringUtils.isBlank(perUser.getUserName())) {
            return false;
        }
        if (perUser.getGender() == null) {
            return false;
        }
        if (perUser.getBirthday() == null || new DateTime(perUser.getBirthday()).isAfter(DateTime.now().minusYears(16)) // 生日晚于16年前（不满16岁）
                || new DateTime(perUser.getBirthday()).isBefore(DateTime.now().minusYears(60))) { // 生日早于60年前（大于60岁）
            return false;
        }
        if (perUser.getJobyearType() == null || perUser.getJobyearType() < -1) {
            return false;
        }
        if (perUser.getLocation() == null || perUser.getLocation() == 0) {
            return false;
        }
        if (StringUtils.isBlank(perUser.getEmail())) {
            return false;
        }
        // if(StringUtils.isBlank(perUser.getMobile()) ||
        // StringUtils.length(perUser.getMobile()) != 11){
        // return false;
        // }

		/*
		 * 暂时关闭求职状态的检查 if (perUser.getJobState() == null ||
		 * perUser.getJobState() < 0) { return false; }
		 */

        return true;
    }

    public Integer getResumePassWhenUserUpdate(PerResume perResume) {
        if (CommonEnum.ResumeType.CARD.getValue() == perResume.getResumeType()) { //名片
            if (perResume == null || perResume.getPass() == null) {
                return CommonEnum.ResumeStatus.UNPERFECT.getValue();
            }

            if (CommonEnum.ResumeStatus.AUTHORIZED.getValue().equals(perResume.getPass())// 已开通
                    && StringUtils.isNotBlank(cardCompleteMsg(perResume))) { // 未完善
                return CommonEnum.ResumeStatus.UNPERFECT.getValue(); // 未完善
            }
            if (CommonEnum.ResumeStatus.TEMPAUTHORIZE.getValue().equals(perResume.getPass())// 暂时开通
                    && StringUtils.isNotBlank(cardCompleteMsg(perResume))) { // 未完善
                return CommonEnum.ResumeStatus.UNPERFECT.getValue(); // 未完善
            }
            if (CommonEnum.ResumeStatus.UNPERFECT.getValue().equals(perResume.getPass())// 未完善
                    && StringUtils.isBlank(cardCompleteMsg(perResume))) { // 已完善
                return CommonEnum.ResumeStatus.TEMPAUTHORIZE.getValue(); // 暂时开通
            }
            if (CommonEnum.ResumeStatus.AUDIT_FAILURE.getValue().equals(perResume.getPass())// 审核不合格
                    && StringUtils.isBlank(cardCompleteMsg(perResume))) { // 已完善
                return CommonEnum.ResumeStatus.UNAUTHORIZE.getValue(); // 未开通
            }
        }else{ //正式简历
            if (perResume == null || perResume.getPass() == null) {
                return CommonEnum.ResumeStatus.UNPERFECT.getValue();
            }

            if (CommonEnum.ResumeStatus.AUTHORIZED.getValue().equals(perResume.getPass())// 已开通
                    && !resumeComplete(perResume)) { // 未完善
                return CommonEnum.ResumeStatus.UNPERFECT.getValue(); // 未完善
            }
            if (CommonEnum.ResumeStatus.TEMPAUTHORIZE.getValue().equals(perResume.getPass())// 暂时开通
                    && !resumeComplete(perResume)) { // 未完善
                return CommonEnum.ResumeStatus.UNPERFECT.getValue(); // 未完善
            }
            if (CommonEnum.ResumeStatus.UNPERFECT.getValue().equals(perResume.getPass())// 未完善
                    && resumeComplete(perResume)) { // 已完善
                return CommonEnum.ResumeStatus.TEMPAUTHORIZE.getValue(); // 暂时开通
            }
            if (CommonEnum.ResumeStatus.AUDIT_FAILURE.getValue().equals(perResume.getPass())// 审核不合格
                    && resumeComplete(perResume)) { // 已完善
                return CommonEnum.ResumeStatus.UNAUTHORIZE.getValue(); // 未开通
            }
        }


        return perResume.getPass();
    }


    /**
     * 名片必填字段的检查
     *  当简历完成以下属性的填写，则为“名片填写完整”：
        姓名、性别、现所在地、出生日期、最高学历、工作年限、我的亮点、期望月薪、期望职位、最近就职企业、最近工作岗位 、手机号码且验证通过。
     * @param perResume
     * @return
     */
    public String cardCompleteMsg(PerResume perResume) {

        if (perResume == null) {
            return "微名片不存在";
        }
        if (StringUtils.isBlank(perResume.getWorkInfo()) || StringUtils.equals(perResume.getWorkInfo(),"[]")){
            return "工作经历未填写";
        }
        if (StringUtils.isBlank(perResume.getEducationInfo()) || StringUtils.equals(perResume.getEducationInfo(),"[]")){
            return "教育经历未填写";
        }

        PerResumeBo perResumeBo = new PerResumeBo(perResume);
        if (perResumeBo.getIntentInfoVo()!=null){
            PerResumeBo.IntentInfoVo intentInfoVo = perResumeBo.getIntentInfoVo();
           if(StringUtils.isBlank(intentInfoVo.getKeywords())) {
                return "我的亮点未填写";
            }
            if(intentInfoVo.getSalary() == null) {
                return "期望月薪未填写";
            }
            if(StringUtils.isBlank(intentInfoVo.getJobNameStr())) {
                return "期望职位未填写";
            }
        }

        if(CollectionUtils.isNotEmpty(perResumeBo.getWorkInfoVoList())){
            CollectionSort.sortTheList(perResumeBo.getWorkInfoVoList(), "id", "Asc");
            PerResumeBo.WorkInfoVo workInfoVo = perResumeBo.getWorkInfoVoList().get(0);
            if(workInfoVo == null || StringUtils.isBlank(workInfoVo.getJobNameStr())){
                return "最近工作岗位未填写";
            }
            if(workInfoVo == null || StringUtils.isBlank(workInfoVo.getComName())){
                return "最近就职企业未填写";
            }
        }

        // 基本资料的完整度
        PerUser perUser = perResume.getPerUser();
        if (perUser == null) {
            return "用户不存在";
        }
        if (StringUtils.isBlank(perUser.getUserName())) {
            return "未填写姓名";
        }
        if (perUser.getGender() == null) {
            return "未填写性别";
        }
        if (perUser.getBirthday() == null) {
            return "出生日期未填写";
        }
        if (perUser.getJobyearType() == null || perUser.getJobyearType() < -1) {
            return "工作年限不正确";
        }
        if (perUser.getLocation() == null || perUser.getLocation() == 0) {
            return "未填写现所在地";
        }
        if (StringUtils.isBlank(perUser.getEmail())) {
            return "未填写Email";
        }

        if (StringUtils.isBlank(perUser.getMobile()) || StringUtils.length(perUser.getMobile()) != 11) {
            return "未填写手机号";
        }

        return "";
    }



}
