package com.job5156.core.common.helper;

import com.job5156.searcher.entity.PosBrief;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 * Date:2015/6/1510:20
 *
 * @author hjs
 * @version 1.0
 */
@Component
public class HighLightHelper {
    public PosBrief highLightPositon(PosBrief brief, String keyword) {

        if (StringUtils.isNotBlank(keyword)) {
            if (brief.getPosName().toUpperCase().contains(keyword.toUpperCase())) {
                brief.setPosName(brief.getPosName().replaceAll("(?i)(\\Q" + keyword + "\\E)", "<em>$1</em>"));
            }
            if (brief.getComName().toUpperCase().contains(keyword.toUpperCase())) {
                brief.setComName(brief.getComName().replaceAll("(?i)(\\Q" + keyword + "\\E)", "<em>$1</em>"));
            }
            if (StringUtils.isNotBlank(brief.getPosDesc())&&brief.getPosDesc().toUpperCase().contains(keyword.toUpperCase())) {
                brief.setPosDesc(brief.getPosDesc().replaceAll("(?i)(\\Q" + keyword + "\\E)", "<em>$1</em>"));
            }
        }
        return brief;
    }

//    public ResumeBrief highLightResume(ResumeBrief brief, String keyword) {
//        Gson gson = new Gson();
//        Map<String, Object> jsonObjMap = gson.fromJson(brief.getJsoninfo(), new TypeToken<Map<String, Object>>() {}.getType());
//        PerResume resume = gson.fromJson(gson.toJson(jsonObjMap.get("resume")), PerResume.class);
//        PerResumeVo resumeVo = new PerResumeVo(resume);
//        if(resumeVo.getIntentInfoVo() != null && StringUtils.isNotBlank(resumeVo.getIntentInfoVo().getProfessionSkill())){
//            if(resumeVo.getIntentInfoVo().getProfessionSkill().toUpperCase().contains(keyword.toUpperCase())) {
//                resumeVo.getIntentInfoVo().setProfessionSkill(resumeVo.getIntentInfoVo().getProfessionSkill().replaceAll("(?i)"+keyword, "<em>"+keyword+"</em>"));
//            }
//            //TODO
//        }
//
//        return brief;
//    }
}
