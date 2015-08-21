package com.job5156.core.biz;

import com.job5156.core.bo.BizRetBo;
import com.job5156.core.bo.BizRetCode;
import com.job5156.core.bo.form.per.PerIndexInfoBo;
import com.job5156.core.bo.form.per.PerLetterFormBo;
import com.job5156.core.common.Constants;
import com.job5156.core.srv.per.PerLetterSrv;
import com.job5156.core.srv.per.PerViewLogSrv;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p></p>
 * Date:Administrator 2015-8-4
 *
 * @author hjs
 * @version 1.0
 */
@Component
public class PerLetterBiz {
    @Resource
    private PerLetterSrv perLetterSrv;

    /**
     * 求职信页面数据获取
     * @param perId
     * @return
     */
    public BizRetBo<PerLetterFormBo> getJobData(Integer perId) {
        PerLetterFormBo vo  = perLetterSrv.getJobLetter(perId);
        return new BizRetBo<>(BizRetCode.SUCCESS, vo);
    }

    /**
     * 保存或修改数据
     * @param bo
     * @param perId
     * @return
     */
    public boolean saveOrReplace(PerLetterFormBo bo,Integer perId) {
        perLetterSrv.analysisAndUpdateDate(bo, perId);
        return true;
    }
}
