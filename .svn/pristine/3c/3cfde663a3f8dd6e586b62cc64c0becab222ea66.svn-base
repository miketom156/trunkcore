package com.job5156.core.eao.per;

import com.google.common.collect.Lists;
import com.job5156.core.common.CommonEnum;
import com.job5156.core.common.Constants;
import com.job5156.core.common.helper.PerResumeCheckHelper;
import com.job5156.core.common.helper.PerResumeScoreHelper;
import com.job5156.core.common.helper.ResumeCheckHelper;
import com.job5156.core.eao.RedisEao;
import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.per.PerResume;
import com.job5156.core.bo.mapping.PerResumeBo;
import com.job5156.core.bo.mapping.ResumePreviewBo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
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

	public PerResumeBo getResumeVo(Integer id) {
		return new PerResumeBo(get(id));
	}

	public PerResumeBo getResumeVo(PerResume perResume) {
		return new PerResumeBo(perResume);
	}

	public ResumePreviewBo getPreviewVo(Integer id) {
		return new ResumePreviewBo(get(id));
	}

	public List<PerResume> findResumeByAccountId(int accountId) {
		String hql = "FROM PerResume WHERE accountId = ? order by id asc";
		return list(hql, new Object[] { accountId });
	}

	public List<PerResume> findResumeListByIds(List<Integer> ids) {
		if (CollectionUtils.isNotEmpty(ids)) {
			String hql = "FROM PerResume WHERE id in (:params)";
			return list(hql, new Object[] { ids });
		} else {
			return Lists.newArrayList();
		}
	}

	public List<Integer> findAccountIdListByResumeIdList(List<Integer> resumeIdList) {
		String hql = "SELECT accountId FROM PerResume WHERE id in (:params)";
		return list(hql, new Object[]{ resumeIdList });
	}

	@Override
	public Integer save(PerResume model) {
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
		Integer id = super.save(model);
		addToSync(id);
		return id;
	}

	public void sysAuditSave(PerResume model) {
		super.update(model);
		addToSync(model.getId());
	}

	@Override
	public void saveOrUpdate(PerResume model) {
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
		super.saveOrUpdate(model);
		addToSync(model.getId());
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

	public void justUpdate(PerResume model) {
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
	}

	public void updateNotSetPassAndPerfectNum(PerResume model) {
		super.update(model);
	}

	public void addToSync(Integer id) {
		if (id != null && id > 0) {
			redisEao.zAdd(Constants.REDIS_KEY_SYNC_QUEUE_RESUME, Lists.newArrayList(ObjectUtils.toString(id)));
			redisEao.zAdd(Constants.REDIS_KEY_SYNC_QUEUE_EXPORT_RESUME, Lists.newArrayList(ObjectUtils.toString(id)));
		}
	}

	/**
	 * 统计个人已创建的简历数
	 * 
	 * @param accountId
	 * @param languageType
	 *            0:中文 1:英文
	 * @return
	 */
	public int countResumeNum(Integer accountId, Integer languageType) {
		if (accountId == null) {
			return -1;
		}
		String hql = "SELECT count(*) FROM PerResume WHERE accountId = ? AND languageType = ? ";
		return this.<Number> aggregate(hql, new Object[] { accountId, languageType }).intValue();
	}
}
