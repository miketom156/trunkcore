/**
 * 
 */
package com.job5156.core.srv.per;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.job5156.core.bo.form.per.PerSendoutFormBo;
import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.core.eao.per.PerLetterEao;
import com.job5156.core.eao.per.PerResumeEao;
import com.job5156.core.eao.per.PerSendoutEao;
import com.job5156.core.srv.base.BaseSrv;
import com.job5156.webapp.model.per.PerSendout;


/**
 * @author lyh
 * @Description
 * @date 2015年8月11日
 */
@Service
@Transactional(value="transactionManagerAction")
public class PerSendoutSrv extends BaseSrv<PerSendout, Integer> {

	@Resource
	private PerSendoutEao perSendoutEao;

    @Override
	protected BaseHibernateEao<PerSendout, Integer> getBaseHibernateEao() {
		// TODO Auto-generated method stub
		return super.getBaseHibernateEao();
	}

	
	@Override
	protected void initBaseHibernateEao() {
		// TODO Auto-generated method stub
		this.baseHibernateEao=perSendoutEao;
	}
	
	@Resource 
	private PerLetterEao perLetterEao;
	
	@Resource 
	private PerResumeEao perResumeEao;



	/**
	 * 通过个人用户ID查询该用户的所有外发简历记录
	 * @param userId
	 * @return
	 */
	public List<PerSendout> getPerSendoutList(int userId){
		return perSendoutEao.findPerSendoutList(userId);
	}

    /**
     * 保存外发简历记录
     * @param perUserId
     * @param resumeName
     * @param vo
     */
	public void save(Integer perUserId, String resumeName, PerSendoutFormBo vo) {
    	PerSendout perSendout = new PerSendout();
        perSendout.setPerUserId(perUserId);
        perSendout.setDelStatus(0);
        perSendout.setCreDate(DateTime.now().toDate());
        perSendout.setEmailFormat(vo.getEmailFormat());
        perSendout.setRemark(vo.getRemark());
        perSendout.setComName(vo.getComName());
        perSendout.setPosName(vo.getPosName());
        perSendout.setOutEmail(vo.getEmail());
        perSendout.setResumeName(resumeName);
       	perSendoutEao.save(perSendout);
    	
	}
	
	/**
	 * 通过个人用户ID查询该用户的当天所有外发简历记录次数
	 * @param userId
	 * @return
	 */
	public int findSendoutCountByToday(int userId){
		return perSendoutEao.findSendoutCountByToday(userId);
	}
	
    public void delSendout(PerSendout perSendout) {
        perSendout.setDelStatus(-1);
        update(perSendout);
    }
    

}
