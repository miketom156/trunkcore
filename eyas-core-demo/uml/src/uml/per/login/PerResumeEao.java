package uml.per.login;

import com.google.common.collect.Lists;
import com.job5156.core.common.Constants;

/**
 * Created by Administrator on 2015-6-9.
 */
public interface PerResumeEao {
    public RedisEao redisDao = null;

    public ResumeCheckHelper resumeCheckHelper = null;

    public PerResumeScoreHelper perResumeScoreHelper = null;

    public PerResumeCheckHelper perResumeCheckHelper = null;
    /**
     * 修改简历
     * @param model 简历实体
     */
    public void update(PerResume model);

    public void addToSync(Integer id);


}
