package com.job5156.core.srv.per;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.job5156.core.bo.mapping.PerResumeBo;
import com.job5156.core.common.helper.FreeMarkerHelper;
import com.job5156.core.common.helper.PerResumeScoreHelper;
import com.job5156.core.common.helper.ResumePreviewHelper;
import com.job5156.core.eao.per.PerResumeEao;
import com.job5156.webapp.model.per.PerResume;
import com.job5156.core.srv.base.BaseSrv;
import com.job5156.core.bo.mapping.ResumePreviewBo;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p></p>
 * Date:2015/5/8 17:03
 *
 * @author pzm
 * @version 1.0
 */

@Service
@Transactional(value="transactionManager")
public class PerResumeSrv extends BaseSrv<PerResume,Integer> {
    @Resource
    private PerResumeEao perResumeEao;

    @Resource
    private FreeMarkerHelper freeMarkerHelper;

    @Resource
    private ResumePreviewHelper resumePreviewHelper;
    @Resource
    private PerResumeScoreHelper  perResumeScoreHelper;

    protected void initBaseHibernateEao() {
        super.baseHibernateEao = perResumeEao;
    }

    public ResumePreviewBo getPreviewCnVo(Integer id){
        PerResume resume = perResumeEao.get(id);
        if(resume != null && resume.getLanguageType() == 0){
            return new ResumePreviewBo(resume);
        } else if(resume != null && resume.getLanguageType() == 1){
            return perResumeEao.getPreviewVo(resume.getResumeId());
        } else {
            return null;
        }
    }

    public ResumePreviewBo getPreviewEnVo(Integer id){
        PerResume resume = perResumeEao.get(id);
        if(resume != null && resume.getLanguageType() == 1){
            return new ResumePreviewBo(resume);
        } else if(resume != null && resume.getLanguageType() == 0 && resume.getResumeId() != null && resume.getResumeId() > 0){
            return perResumeEao.getPreviewVo(resume.getResumeId());
        } else {
            return null;
        }
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
            return freeMarkerHelper.getNotCacheContent(template, resumePreviewHelper.translateResumePreviewBo(vo, null));
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
                return freeMarkerHelper.generateContent(template,resumePreviewHelper.translateResumePreviewVo(vo, null));
            } else {
                return freeMarkerHelper.generateContent(template,resumePreviewHelper.translateResumePreviewVo(perResumeDao.getPreviewVo(vo.getResumeId()), null));
            }
        }*/

    }

    public PerResumeBo getResumeVo(PerResume perResume){
        return perResumeEao.getResumeVo(perResume);
    }
    
    /**
     * 获取中文简历名称列表
     * @param accountId
     * @return
     */
    public List<Map<String, Object>> getResumeCnNameList(int accountId) {

        List<Map<String, Object>> list = Lists.newArrayList();
        List<PerResume> resumeList = perResumeEao.findResumeByAccountId(accountId);
        for (PerResume resume : resumeList) {
            if(resume.getLanguageType() == 0) {
                list.add(ImmutableMap.<String, Object>of(
                        "id", resume.getId(),
                        "resumeName", ObjectUtils.toString(resume.getResumeName(),"未命名简历"),
                        "perfectNum", perResumeScoreHelper.getScore(resume),
                        "dir", resume.getResumeStyle(),
                        "isDefault", resume == resume.getPerUser().getDefaultPerResume()
                ));
            }
        }
        return list;
    }
}
