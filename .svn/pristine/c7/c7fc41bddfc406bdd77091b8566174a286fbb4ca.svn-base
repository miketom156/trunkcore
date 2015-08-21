package com.job5156.core.eao.com;

import com.google.common.collect.Lists;
import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.com.ComReceive;
import com.job5156.core.srv.com.ComReceiveSrv;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public class ComReceiveEao extends BaseHibernateEao<ComReceive, Integer> {

	@Resource(name="sessionFactoryAction")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	 public int countQuery(Object[] params, String hqlParam) {
		 
    	 String hql = "select count(*) from ComReceive where comId = ? AND delStatus!=-1 " + hqlParam;
         
    	 return this.<Number>aggregate(hql, params).intValue();
    }
	 
	public int countByPosIdAndType(Integer posId, Integer type) {
		String hql = "select count(*) from ComReceive where posId = ? AND receiveType = ?";
		
		return this.<Number>aggregate(hql, posId, type).intValue();
	}
    public int countByPosIdAndInterview(Integer posId) {
        String hql = "select count(*) from ComReceive where posId = ? AND receiveType = ? and createDate is not null ";

        return this.<Number>aggregate(hql, posId, ComReceiveSrv.ReceiveType.PER_INTERVIEW.getValue()).intValue();
    }

    public int countByPosIdAndRecommend(Integer posId) {
        String hql = "select count(*) from ComReceive where posId = ? AND sysRecommend = 1  and createDate is not null ";

        return this.<Number>aggregate(hql, posId).intValue();
    }

    public int countByPosIdAndBuy(Integer posId) {
        String hql = "select count(*) from ComStore where posId = ? AND buy_flag =1  and createDate is not null  ";

        return this.<Number>aggregate(hql, posId).intValue();
    }
    
	public List<ComReceive> query(int pn, int pageSize, Object[] params, String hqlParam){
		
		String hql = "from ComReceive where comId = ? AND delStatus!=-1 " + hqlParam;
		return list(hql, pn, pageSize, params);
	}
		
	public ComReceive findByComIdAndResumeId(Integer comId, Integer resumeId){
		String hql = "from ComReceive where comId = ? AND resumeId = ? AND delStatus!=-1 ";
		return unique(hql, new Object[]{comId, resumeId});
	}

    public List<Integer> tempFillUpMixInfoComList(){
        String hql = "SELECT DISTINCT comId FROM ComReceive where delStatus!=-1 AND NULLIF(mixInfo, '') IS NULL ";
        return list(hql);
    }

    public List<ComReceive> findNoMixInfoListByComId(Integer comId){
        String hql = "from ComReceive where comId = ? AND delStatus!=-1 AND NULLIF(mixInfo, '') IS NULL ";
        return list(hql, new Object[]{comId});
    }
	
	public List<ComReceive> findComReceiveApplyNumer(Integer comId, Integer posId, Integer perId, Date date) {
		String hql = "from ComReceive where comId = ? AND posId = ? AND perUserId = ? AND createDate > ?";
		return this.find(hql, new Object[]{ comId, posId, perId, date });
	}
	
	public List<ComReceive> findComReceive(Integer comId, Integer perId, Date date) {
		String hql = "from ComReceive where comId = ? AND posId = ? AND createDate > ?";
		return this.find(hql, new Object[]{ comId, perId, date });
	}
	
	public List<ComReceive> findComReceive(Integer comId, Integer posId, Integer perId) {
		String hql = "from ComReceive where comId = ? AND posId = ? AND perUserId = ? AND delStatus!=-1";
		return find(hql, new Object[]{ comId, posId, perId });
	}
	
	public ComReceive findComReceiveByNew(Integer comId, Integer posId, Integer perId) {
		//获取同一个人应聘同一个企业同一个职位最新的收到简历记录
		String hql = "from ComReceive where comId = ? AND posId = ? AND perUserId = ? AND delStatus!=-1 order by createDate desc";
		List<ComReceive> comReceiveList = find(hql, new Object[]{ comId, posId, perId });
		ComReceive comReceive = null;
		if(CollectionUtils.isNotEmpty(comReceiveList)) {
			return comReceiveList.get(0);
		}
		return comReceive;
	}

    /**
     *  获取同一个人同一份简历应聘同一个企业同一个职位最新的收到简历记录
     * @param comId
     * @param posId
     * @param resumeId
     * @return
     */
    public ComReceive findComReceiveByResumeId(Integer comId, Integer posId, Integer resumeId) {
        String hql = "from ComReceive where comId = ? AND posId = ? AND resumeId = ? AND delStatus!=-1 order by createDate desc";
        List<ComReceive> comReceiveList = find(hql, new Object[]{ comId, posId, resumeId });
        ComReceive comReceive = null;
        if(CollectionUtils.isNotEmpty(comReceiveList)) {
            return comReceiveList.get(0);
        }
        return comReceive;
    }

    public int count30DaysApply(Integer posId, Integer perUserId) {
        String hql = "select count(*) from ComReceive where posId = ? AND perUserId = ? AND createDate > ? ";
        return this.<Number>aggregate(hql, posId, perUserId, DateTime.now().minusDays(30).toDate()).intValue();
    }

    public int count30DaysApplyTimes(Integer posId, Integer perUserId) {
        String hql = "select sum(applyNumer) from ComReceive where posId = ? AND perUserId = ? AND updateDate > ? AND delStatus != -1 ";
        int applyNums = this.<Number>aggregate(hql,new Object[]{ posId, perUserId, DateTime.now().minusDays(30).toDate()}).intValue();

/*
        hql = " From ComReceive  where posId = ? AND perUserId = ? ORDER BY updateDate DESC ";
        ComReceive comReceive = unique(hql,new Object[]{posId,perUserId});
        if(comReceive !=null){
            int n = comReceive.getApplyNumer()==null?1:comReceive.getApplyNumer();
            applyNums =  applyNums + n - 1;
        }
*/

        return applyNums;
    }
    
    /**
     * 后台人员推荐的简历
     * @param comId
     * @param resumeId
     * @return
     */
    public Boolean checkResumeBySysRecommend(Integer comId, Integer resumeId) {
        String hql = "SELECT buyFlag FROM ComReceive WHERE resumeId = ? AND comId = ? AND sysRecommend = 1  AND buyFlag = 1";
        return unique(hql, new Object[]{resumeId, comId}) == null? false :true;
    }
    

    /**
     * 检查企业是否已购买了简历
     * 求职者投递的简历，企业可以查看联系方式，放入人才库不扣点
     * 后台推荐的简历并标注已购买的企业可以查看联系方式（未标已购买的不可以查看联系方式）
     * @param comId
     * @param resumeId
     * @return
     */
    public Boolean checkResumeIsBuy(Integer comId, Integer resumeId) {
        String hql = "FROM ComReceive WHERE resumeId = ? AND comId = ? AND (buyFlag = 1  OR sysRecommend != 1) ";
        return unique(hql, new Object[]{resumeId, comId}) == null? false :true;
    }
    /**
     * 统计某一个职位的已读简历
     * @param posId
     * @return
     */
    public Integer listReadCountByPosId(Integer comId, Integer posId) {
       // String hql = "SELECT count(*) FROM ComReceive WHERE comId = ? AND posId = ? AND readFlag = 1 AND delStatus != -1 ";
        //TODO:暂时不统计名片
        String hql = "SELECT count(*) FROM ComReceive WHERE comId = ? AND posId = ? AND readFlag = 1 AND delStatus != -1 AND receive_type != 4 ";
        return this.<Number>aggregate(hql, new Object[] {comId, posId }).intValue();
    }
    /**
     * 统计某一个职位的所有的简历
     * @param posId
     * @return
     */
    public Integer listCountAllByPosId(Integer comId, Integer posId) {
        // String hql = "SELECT count(*) FROM ComReceive WHERE comId = ? AND posId = ? AND delStatus != -1 ";
        //TODO:暂时不统计名片
        String hql = "SELECT count(*) FROM ComReceive WHERE comId = ? AND posId = ? AND delStatus != -1 AND receive_type != 4";
        return this.<Number>aggregate(hql, new Object[] {comId, posId }).intValue();
    }

    /**
     * 取得COMRECEIVE BY 公司ID,来源ID
     * @param comId
     * @param sourceId
     * @return
     */
    public ComReceive getByComIdAndSourceId(Integer comId ,Integer sourceId){
        String hql = "  FROM ComReceive WHERE comId = ? AND id = ? ";
        return unique(hql, new Object[]{ comId, sourceId});
    }

    /**
     * 查询当天应聘次数防止重复提交
     * @param comId
     * @param resumeId
     * @param posId
     * @return
     */
    public int countTodayApplyTimes(Integer comId, Integer resumeId,Integer posId){

        String hql = " SELECT count(*) FROM ComReceive WHERE comId = ? AND resumeId = ? AND delStatus!=-1 AND posId=? AND createDate >=? AND createDate < ?";
        return this.<Number>aggregate(hql, new Object[]{comId, resumeId,posId,DateTime.now().withTimeAtStartOfDay().toDate(),DateTime.now().secondOfDay().withMaximumValue().toDate()}).intValue();

    }

	/**
	 * 根据这几个参数查询ComReceive的对象
	 * 
	 * @param comId
	 * @param perId
	 * @param posId
	 * @return
	 */
	public ComReceive findByComReceive(Integer comId, Integer perId,
			Integer posId) {
		String hql = "FROM ComReceive WHERE comId=? AND perUserId=? AND posId=?";
		return unique(hql, comId, perId, posId);
	}
	
	/**
	 * 
	* 获取指定简历的所有ComReceive对象
	* @param @param resumeId
	* @param @return    设定文件 
	* @return List<ComReceive>    返回类型 
	* @throws
	 */
	public List<ComReceive> getComReceiveListByResumeId(Integer resumeId) {
		String hql = "FROM ComReceive WHERE resumeId=?";
		return find(hql, new Object[]{ resumeId });
	}
	
	/**
	 * 通过指定的comId获取ComReceive对象
	 * 
	 * @param comIds
	 * @return
	 */
	public List<ComReceive> findByComReceiveList(List<Integer> comIds){
		//new DateTime(DateUtils.addDays(new Date(), -300)).secondOfDay().withMinimumValue().toDate();
		Date startDate = null;
		
		/*
		 * 第一次同步的时间是9点，因此数据要从昨天的17点之后的数据
		 * 开始到现在9点的数据进行同步，第二次同步是下午17点，
		 * 因此起始时间是当前时间减去8小时到现在17点数据进行同步
		 */
		if(DateTime.now().getHourOfDay() == 9){
			startDate = new DateTime(DateUtils.addHours(new Date(), -16)).toDate();
		}else{
			startDate = new DateTime(DateUtils.addHours(new Date(), -8)).toDate();
		}
		
		Date endDate = DateTime.now().toDate();
		String hql = "FROM ComReceive WHERE comId IN (:ids) AND updateDate >= :startDate AND updateDate <= :endDate";
		Session session = getSession();
		Query query  = session.createQuery(hql);
		query.setParameterList("ids", comIds);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.list();
	}
	
	/**
	 * 通过指定的comId,日期获取ComReceive对象
	 * 
	 * @param comIds
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<ComReceive> findByComReceiveList(List<Integer> comIds,Date startDate,Date endDate){
		String hql = "FROM ComReceive WHERE comId IN (:ids) AND updateDate >= :startDate AND updateDate <= :endDate";
		Session session = getSession();
		Query query  = session.createQuery(hql);
		query.setParameterList("ids", comIds);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.list();
	}

    /**
     * 取得企业最新收到的100个简历
     * @param limits 条数
     * @return
     */
    public List<ComReceive> getCurrReceiveListLimit(Integer limits) {
        List<ComReceive> comReceiveList = Lists.newArrayList();
        String hql = " from ComReceive WHERE delStatus != -1 AND receiveType = 0 order by id DESC ";
        comReceiveList = list(hql,1,limits,null);
        return comReceiveList;
    }



}
