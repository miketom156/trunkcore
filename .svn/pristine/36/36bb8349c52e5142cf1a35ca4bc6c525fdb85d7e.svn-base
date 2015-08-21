package com.job5156.core.eao.per;

import com.google.common.collect.Lists;
import com.job5156.core.common.CommonEnum;
import com.job5156.core.common.Constants;
import com.job5156.core.common.helper.PerResumeCheckHelper;
import com.job5156.core.common.helper.PerResumeScoreHelper;
import com.job5156.core.common.helper.ResumeCheckHelper;
import com.job5156.core.eao.RedisEao;
import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.core.entity.per.PerResume;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
/**
 * <p>用户简历持久类</p>
 * Date:2015/5/20 10:50
 *
 * @author pzm
 * @version 1.0
 */
@Repository
public class PerResumeEao extends BaseHibernateEao<PerResume, Integer> {

	@Resource
	private RedisEao redisEao;

    @Resource
    private PerResumeScoreHelper perResumeScoreHelper;

    @Resource
    private PerResumeCheckHelper perResumeCheckHelper;

    @Resource
    private ResumeCheckHelper resumeCheckHelper;
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * 修改简历
     * @param model 简历实体
     */
	@Override
	public void update(PerResume model) {
		String checkKeyword = resumeCheckHelper.checkResumeKeyword(model);
		model.setPerfectNum(perResumeScoreHelper.getScore(model));
		model.setPass(perResumeCheckHelper.getResumePassWhenUserUpdate(model));
		//检测到存在非法关键字
        if(StringUtils.isNotBlank(checkKeyword)){
        	model.setPass(CommonEnum.ResumeStatus.AUDIT_FAILURE.getValue());
        	model.setPassDate(new Date());
        	model.setAdminLastEditBy("系统过滤非法关键字");
        	model.setAdminLastEditDate(new Date());
        }
		super.update(model);
		addToSync(model.getId());
	}

	public void addToSync(Integer id) {
		if (id != null && id > 0) {
			redisEao.zAdd(Constants.REDIS_KEY_SYNC_QUEUE_RESUME, Lists.newArrayList(ObjectUtils.toString(id)));
			redisEao.zAdd(Constants.REDIS_KEY_SYNC_QUEUE_EXPORT_RESUME, Lists.newArrayList(ObjectUtils.toString(id)));
		}
	}
}
