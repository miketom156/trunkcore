package com.job5156.core.common.helper;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.job5156.core.bo.mapping.PerResumeBo;
import com.job5156.core.common.Constants;
import com.job5156.core.entity.per.PerResume;
import com.job5156.core.entity.per.job.PerInviteLog;
import com.job5156.core.entity.per.job.PerPosApply;
import com.job5156.core.entity.per.job.PerPosFavorite;
import com.job5156.core.entity.per.job.PerViewLog;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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

    public void fillApplyMixInfo(PerPosApply perPosApply) {

        //2014-01-01之前的企业回复状态未转换
        if(perPosApply != null && perPosApply.getReplyStatus() != null
                 && perPosApply.getReplyStatus() == 4  && perPosApply.getCreTime().before(new DateTime("2014-01-01").toDate())){

            try {
                perPosApply.setReplyStatus(Constants.PER_POS_APPLY_REPLY_STATUS_READ);
                //暂时不写入数据库,先观察一段时间
               // getJdbcTemplateAction().update("UPDATE per_pos_apply SET reply_status = ? WHERE id=?",Constants.PER_POS_APPLY_REPLY_STATUS_READ, perPosApply.getId());
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
        if(perPosApply != null && StringUtils.isBlank(perPosApply.getMixInfo())) {

            Integer posId = perPosApply.getPosId();
            Integer comId = perPosApply.getComId();

            Map<String, Object> map = Maps.newHashMap();

            try {
                Map<String, Object> comMap = getJdbcTemplate().queryForMap("SELECT * FROM com_info WHERE id=?",comId);

                map.put("comName", comMap.get("com_name"));
                map.put("comIndustry", comMap.get("industry"));
                map.put("comProperty", comMap.get("property"));

                Map<String, Object> posMap = getJdbcTemplate().queryForMap("SELECT * FROM com_position WHERE id=?",posId);
                map.put("posName", posMap.get("pos_name"));
                map.put("jobLocation", posMap.get("work_location"));
                map.put("salary", posMap.get("salary"));
                map.put("degree", posMap.get("req_degree"));
                map.put("workYear", posMap.get("req_work_year"));
                map.put("description", posMap.get("description"));
                map.put("taoLabel", posMap.get("tao_label"));

                //            map.put("contactPhone", posMap.get("contact_phone"));
                //            map.put("contactPerson", posMap.get("contact_person"));

                if(map.get("posName") != null && StringUtils.isNotBlank(ObjectUtils.toString(map.get("posName")))) {
                    String mixInfo = new Gson().toJson(map);
                    getJdbcTemplateAction().update("UPDATE per_pos_apply SET mix_info = ? WHERE id=?", mixInfo, perPosApply.getId());
                    perPosApply.setMixInfo(mixInfo);
                } else {
                    getJdbcTemplateAction().update("UPDATE per_pos_apply SET mix_info = ? WHERE id=?", "{}", perPosApply.getId());
                    perPosApply.setMixInfo("{}");
                }

            } catch (EmptyResultDataAccessException ex) {
                getJdbcTemplateAction().update("UPDATE per_pos_apply SET mix_info = ? WHERE id=?", "{}", perPosApply.getId());
                perPosApply.setMixInfo("{}");
            } catch (Exception ex) {
                //不处理
            }
        }
    }

    public void fillInviteMixInfo(PerInviteLog perInviteLog) {

        if(perInviteLog != null && StringUtils.isBlank(perInviteLog.getMixInfo())) {
            Integer posId = perInviteLog.getPosId();
            Integer comId = perInviteLog.getComId();

            Map<String, Object> map = Maps.newHashMap();

            try {
                Map<String, Object> comMap = getJdbcTemplate().queryForMap("SELECT * FROM com_info WHERE id=?",comId);

                map.put("comName", comMap.get("com_name"));
                map.put("comIndustry", comMap.get("industry"));
                map.put("comProperty", comMap.get("property"));

                Map<String, Object> posMap = getJdbcTemplate().queryForMap("SELECT * FROM com_position WHERE id=?",posId);
                map.put("posName", posMap.get("pos_name"));
                map.put("jobLocation", posMap.get("work_location"));
                map.put("salary", posMap.get("salary"));
                map.put("degree", posMap.get("req_degree"));
                map.put("workYear", posMap.get("req_work_year"));
                map.put("description", posMap.get("description"));
                map.put("taoLabel", posMap.get("tao_label"));

                map.put("contactPhone", posMap.get("contact_phone"));
                map.put("contactPerson", posMap.get("contact_person"));
                map.put("posStatus", posMap.get("pos_status"));

                if(map.get("posName") != null && StringUtils.isNotBlank(ObjectUtils.toString(map.get("posName")))) {
                    String mixInfo = new Gson().toJson(map);
                    getJdbcTemplateAction().update("UPDATE per_invite_log SET mix_info = ? WHERE id=?", mixInfo, perInviteLog.getId());
                    perInviteLog.setMixInfo(mixInfo);
                } else {
                    getJdbcTemplateAction().update("UPDATE per_invite_log SET mix_info = ? WHERE id=?", "{}", perInviteLog.getId());
                    perInviteLog.setMixInfo("{}");
                }

            } catch (EmptyResultDataAccessException ex) {
                getJdbcTemplateAction().update("UPDATE per_invite_log SET mix_info = ? WHERE id=?", "{}", perInviteLog.getId());
                perInviteLog.setMixInfo("{}");
            } catch (Exception ex) {
                //不处理
            }
        }
    }

    public void fillFavMixInfo(PerPosFavorite perPosFavorite) {
        if(perPosFavorite != null && StringUtils.isBlank(perPosFavorite.getMixInfo())) {

            Integer posId = perPosFavorite.getPosId();
            Integer comId = perPosFavorite.getComId();

            Map<String, Object> map = Maps.newHashMap();

            try {
                Map<String, Object> comMap = getJdbcTemplate().queryForMap("SELECT * FROM com_info WHERE id=?",comId);

                map.put("comName", comMap.get("com_name"));
                map.put("comIndustry", comMap.get("industry"));
                map.put("comProperty", comMap.get("property"));

                Map<String, Object> posMap = getJdbcTemplate().queryForMap("SELECT * FROM com_position WHERE id=?",posId);
                map.put("posName", posMap.get("pos_name"));
                map.put("jobLocation", posMap.get("work_location"));
                map.put("salary", posMap.get("salary"));
                map.put("degree", posMap.get("req_degree"));
                map.put("workYear", posMap.get("req_work_year"));
                map.put("description", posMap.get("description"));
                map.put("taoLabel", posMap.get("tao_label"));

    //            map.put("contactPhone", posMap.get("contact_phone"));
    //            map.put("contactPerson", posMap.get("contact_person"));

                if(map.get("posName") != null && StringUtils.isNotBlank(ObjectUtils.toString(map.get("posName")))) {
                    String mixInfo = new Gson().toJson(map);
                    getJdbcTemplateAction().update("UPDATE per_pos_favorite SET mix_info = ? WHERE id=?", mixInfo, perPosFavorite.getId());
                    perPosFavorite.setMixInfo(mixInfo);
                } else {
                    getJdbcTemplateAction().update("UPDATE per_pos_favorite SET mix_info = ? WHERE id=?", "{}", perPosFavorite.getId());
                    perPosFavorite.setMixInfo("{}");
                }
            } catch (EmptyResultDataAccessException ex) {
                getJdbcTemplateAction().update("UPDATE per_pos_favorite SET mix_info = ? WHERE id=?", "{}", perPosFavorite.getId());
                perPosFavorite.setMixInfo("{}");
            } catch (Exception ex) {
                //不处理
            }
        }
    }

    public void fillViewMixInfo(PerViewLog perViewLog) {

        if(perViewLog != null && StringUtils.isBlank(perViewLog.getMixInfo())) {
            Integer comId = perViewLog.getComId();

            Map<String, Object> map = Maps.newHashMap();

            try {
                Map<String, Object> comMap = getJdbcTemplate().queryForMap("SELECT * FROM com_info WHERE id=?",comId);

                map.put("comName", comMap.get("com_name"));
                map.put("industry", comMap.get("industry"));
                map.put("property", comMap.get("property"));
                map.put("location", comMap.get("location"));
                map.put("employeeNumber", comMap.get("employee_number"));

                if(map.get("comName") != null && StringUtils.isNotBlank(ObjectUtils.toString(map.get("comName")))) {
                    String mixInfo = new Gson().toJson(map);
                    String comName = ObjectUtils.toString(comMap.get("com_name"),"");
                    getJdbcTemplateAction().update("UPDATE per_view_log SET mix_info = ? , com_name = ? WHERE id=?", mixInfo, comName, perViewLog.getId());
                    perViewLog.setMixInfo(mixInfo);
                    perViewLog.setComName(comName);
                } else {
                    getJdbcTemplateAction().update("UPDATE per_view_log SET mix_info = ? WHERE id=?", "{}", perViewLog.getId());
                    perViewLog.setMixInfo("{}");
                }
            } catch (EmptyResultDataAccessException ex) {
                getJdbcTemplateAction().update("UPDATE per_view_log SET mix_info = ?  WHERE id=?", "{}", perViewLog.getId());
                perViewLog.setMixInfo("{}");
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
}
