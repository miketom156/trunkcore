/**
 * 
 */
package com.job5156.core.common.helper.per;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.job5156.core.bo.mapping.ResumePreviewBo;
import com.job5156.core.common.helper.FreeMarkerHelper;
import com.job5156.core.common.helper.ResumePreviewHelper;
import com.job5156.core.eao.per.PerResumeEao;

/**
 * @author lyh
 * @Description 
 * @date 2015年8月12日
 * 
 */
@Component
public class PerResumePreviewHelper {

    @Resource
    private FreeMarkerHelper freeMarkerHelper;

    @Resource
    private PerResumeEao perResumeEao;

    @Resource
    private ResumePreviewHelper resumePreviewHelper;

    public String getCnContent(ResumePreviewBo vo) {
        return getPreviewContent(vo, "cn", "resume.ftl");
    }

    public String getEnContent(ResumePreviewBo vo) {
        return getPreviewContent(vo, "en", "resume.ftl");
    }
    
    public String getAppCnContent(ResumePreviewBo vo) {
        return getPreviewContent(vo, "cn", "resume_app.ftl");
    }
    
    public String getAppEnContent(ResumePreviewBo vo) {
        return getPreviewContent(vo, "en", "resume_app.ftl");
    }

    public String getCnMailContent(ResumePreviewBo vo) {
        return getPreviewContent(vo, "cn", "resumeMail.ftl");
    }

    public String getEnMailContent(ResumePreviewBo vo) {
        return getPreviewContent(vo, "en", "resumeMail.ftl");
    }

    private  String getPreviewContent(ResumePreviewBo vo, String lang, String template) {
    	 if(vo == null) {
             return "";
         }
    	 if (StringUtils.equals("cn", lang)) {
           return freeMarkerHelper.getNotCacheContent(template, resumePreviewHelper.translateResumePreviewBo(vo, lang));
         } else {
             return freeMarkerHelper.getNotCacheContent(template, resumePreviewHelper.translateResumePreviewBo(vo, lang));
         }
    	
        /*if(vo == null) {
            return "";
        } else {
            boolean matchLang = false;
            if(StringUtils.equals(lang, "cn")) {
                if(vo.getLanguageType() == 0) {
                    matchLang = true;
                }
            } else if(StringUtils.equals(lang, "en")) {
                if(vo.getLanguageType() == 1) {
                    matchLang = true;
                }
            }
            if(matchLang) {
                return freeMarkerHelper.generateContent(template,resumePreviewHelper.translateResumePreviewBo(vo, null));
            } else {
                return freeMarkerHelper.generateContent(template,resumePreviewHelper.translateResumePreviewBo(perResumeEao.getPreviewVo(vo.getResumeId()), null));
            }
        }*/

    }
}
