package com.job5156.core.common.helper;

import com.job5156.core.common.Constants;
import com.job5156.core.eao.RedisEao;
import com.job5156.webapp.model.per.PerResume;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>简历新增/修改操作检查简历内容是否包含非法关键字</p>
 * Date:Administrator 2015-6-23
 *
 * @author hjs
 * @version 1.0
 */
@Component
public class ResumeCheckHelper {

    @Resource
    private RedisEao redisEao;

    /**
     * 检测简历中的非法关键字并返回
     * @param resume
     * @return
     */
    public String checkResumeKeyword(PerResume resume){
        String checkKeyword = this.checkIllegalKeyword(resume.getIntentInfo());
        if(StringUtils.isNotBlank(checkKeyword)){
            return checkKeyword;
        }
        checkKeyword = this.checkIllegalKeyword(resume.getEducationInfo());
        if(StringUtils.isNotBlank(checkKeyword)){
            return checkKeyword;
        }
        checkKeyword = this.checkIllegalKeyword(resume.getWorkInfo());
        if(StringUtils.isNotBlank(checkKeyword)){
            return checkKeyword;
        }
        checkKeyword = this.checkIllegalKeyword(resume.getTrainInfo());
        if(StringUtils.isNotBlank(checkKeyword)){
            return checkKeyword;
        }
        checkKeyword = this.checkIllegalKeyword(resume.getProjectInfo());
        if(StringUtils.isNotBlank(checkKeyword)){
            return checkKeyword;
        }
        return checkKeyword;
    }

    /**
     * <p>
     * 检测内容是否包含非法关键字
     * <p>
     *
     * <pre>
     * 说明：如果检测到非法关键字，则在业务中将修改简历状态
     * </pre>
     *
     * @param content
     *            内容
     * @return 返回检测到的非法关键字
     */
    public String checkIllegalKeyword(String content) {
        if (StringUtils.isNotBlank(content)) {
            String key = Constants.REDIS_KEY_RESUME_CHECK_ILLEGAL_KEYWORD;
            List<String> keywordList = redisEao.zRange(key, 0, -1);
            if (CollectionUtils.isNotEmpty(keywordList)) {
                for (String keyword : keywordList) {
                    if (StringUtils.contains(content, keyword)) {
                        return keyword;
                    }
                }
            }
        }
        return StringUtils.EMPTY;
    }
}
