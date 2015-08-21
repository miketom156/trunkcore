/**
 * 
 */
package com.job5156.core.eao.per;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.job5156.core.bo.PerUserInfoFormBoTemplate.FilterComIdBo;
import com.job5156.core.bo.form.per.PerSubscribeEmailFormBo;
import com.job5156.core.bo.form.per.PerSubscribeMobileFormBo;
import com.job5156.core.common.MailConstants;
import com.job5156.webapp.model.com.ComInfo;
import com.job5156.webapp.model.per.PerSubscription;
import com.job5156.webapp.model.per.PerUser;


/**
 * @author lyh
 * @Description 
 * @date 2015年8月4日
 * 
 */
@Service
@Transactional(value="transactionManager")
public class PerSettingSrv {
	@Resource
	private PerSubscriptionEao perSubscriptionEao;
	
	@Resource
	private PerUserEao perUserEao;
	
	@Resource
	private PerResumeEao perResumeEao;
	
	/**
	 * 通过个人用户账号ID来获取所有的订阅记录
	 * @param userId
	 * @return
	 */
	public List<PerSubscription> findByUserId(Integer userId){
		return perSubscriptionEao.findByUserId(userId);
	}
	
	/**
	 * 取消屏蔽企业
	 * @param accountId 个人用户账号ID
	 * @param comId 企业ID
	 */
	public void cancelFilterCom(Integer accountId, Integer comId){
		if(comId == null || accountId == null){
			return;
		}
		PerUser perUser = perUserEao.get(accountId);
		String filterComId = perUser.getFilterComId();
		Gson gson = new GsonBuilder().serializeNulls().create();
		List<FilterComIdBo> list = gson.<List<FilterComIdBo>>fromJson(filterComId, new TypeToken<List<FilterComIdBo>>() {}.getType());
		if(list != null){
			for (FilterComIdBo filterComIdVo : list) {
				if(filterComIdVo.getId() != null && filterComIdVo.getId().intValue() ==  comId.intValue()){
					list.remove(filterComIdVo);
					break;
				}
			}
		}
		perUser.setFilterComId(gson.toJson(list));
		perUserEao.update(perUser);
		perResumeEao.addToSync(perUser.getResId());
	}
	
	/**
	 * 设置屏蔽企业
	 * @param accountId
	 * @param filterComId
	 */
	public void addFilterCom(Integer accountId, String filterComId){
		PerUser perUser = perUserEao.get(accountId);
		perUser.setFilterComId(filterComId);
		perUserEao.update(perUser);
		perResumeEao.addToSync(perUser.getResId());
	}
	
	/**
	 * List<FilterComIdVo>转换List<FilterComIdVo>
	 * @param comBaseList
	 * @return comList List<FilterComIdVo>
	 */
	public List<FilterComIdBo> toFilterComIdVo(List<ComInfo> comBaseList){
		List<FilterComIdBo> comList = new ArrayList<FilterComIdBo>();
		for(int i = 0; (i<20) && (i<comBaseList.size()); i++){
			ComInfo comBase = comBaseList.get(i);
			FilterComIdBo comIdVo = new FilterComIdBo();
			comIdVo.setId(comBase.getId());
			comIdVo.setName(comBase.getComName());
			comList.add(comIdVo);
		}
		return comList;
	}
	
	/**
	 * 更新邮件订阅状态
	 * @param vo
	 * @param userId
	 */
	public void updateEmailSubsciption(PerSubscribeEmailFormBo vo, Integer userId){
		List<PerSubscription> subscriptionList = perSubscriptionEao.findByUserIdAndMainType(userId, "email");
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (PerSubscription perSubscription : subscriptionList) {
			map.put(perSubscription.getSubType(), perSubscription.getId());
		}
		updateSubscribeStatus(vo.getEmailWebNews(), map, "emailWebNews", "email",userId);
		updateSubscribeStatus(vo.getEmailPosRecommend(), map, "emailPosRecommend", "email",userId);
		updateSubscribeStatus(vo.getEmailResumeRemind(), map, "emailResumeRemind", "email",userId);
		updateSubscribeStatus(vo.getEmailAdvertisement(), map, "emailAdvertisement", "email",userId);
		updateSubscribeStatus(vo.getEmailInviteInterview(), map, "emailInviteInterview", "email",userId);
	}
	
	/**
	 * 更新短信订阅状态
	 * @param vo
	 * @param userId
	 */
	public void updateMobileSubsciption(PerSubscribeMobileFormBo vo, Integer userId){
		List<PerSubscription> subscriptionList = perSubscriptionEao.findByUserIdAndMainType(userId, "mobile");
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (PerSubscription perSubscription : subscriptionList) {
			map.put(perSubscription.getSubType(), perSubscription.getId());
		}
		updateSubscribeStatus(vo.getMobileResumeNoPerfect(), map, "mobileResumeNoPerfect","mobile",userId);
		updateSubscribeStatus(vo.getMobileResumeLook(), map, "mobileResumeLook","mobile",userId);
		updateSubscribeStatus(vo.getMobileComResumeReply(), map, "mobileComResumeReply","mobile",userId);
		updateSubscribeStatus(vo.getMobileComInviteInterview(), map, "mobileComInviteInterview","mobile",userId);
		updateSubscribeStatus(vo.getMobileBirthdayWishes(), map, "mobileBirthdayWishes","mobile",userId);
	}
	
	public void updateSubscribeStatus(Integer subscribeStatus, Map<String, Integer> map, String subType, String mainType, Integer userId){
		PerSubscription perSubscription = null;
		Integer id = map.get(subType);
		if(id == null){
			perSubscription = new PerSubscription();
			perSubscription.setUserId(userId);
			perSubscription.setMainType(mainType);
			perSubscription.setSubType(subType);
			perSubscription.setSubscribeStatus(subscribeStatus);
			perSubscription.setModifyDate(DateTime.now().toDate());
			perSubscriptionEao.save(perSubscription);
		}else{
			perSubscription = perSubscriptionEao.get(id);
			perSubscription.setSubscribeStatus(subscribeStatus);
			perSubscription.setModifyDate(DateTime.now().toDate());
			perSubscriptionEao.update(perSubscription);
		}
	}
	
	/**
	 * 通过邮件点击退订操作
	 * @param userId
	 * 			用户账号ID
	 * @param type
	 * 			邮件任务类型ID(详细请查看Constants)
	 */
	public void unsubscribeMailType(Integer userId, Integer type){
		Map<String, String> mailConfigMap = MailConstants.mailTypeConfigMap.get(type);
		String mainType = mailConfigMap.get("mainType");
		String subType = mailConfigMap.get("subType");
		List<PerSubscription> subscriptionList = perSubscriptionEao.findByUserIdAndType(userId, mainType, subType);
		if(CollectionUtils.isNotEmpty(subscriptionList)){
			PerSubscription subscription = subscriptionList.remove(0);
			subscription.setSubscribeStatus(MailConstants.MAIL_SUBSCRIBE_NO);
			subscription.setModifyDate(new Date());
			perSubscriptionEao.update(subscription);
			
			/**删除重复记录**/
			for(PerSubscription subscribe : subscriptionList){
				perSubscriptionEao.deleteObject(subscribe);
			}
		}else{
			PerSubscription subscription = new PerSubscription();
			subscription.setMainType(mainType);
			subscription.setSubType(subType);
			subscription.setSubscribeStatus(MailConstants.MAIL_SUBSCRIBE_NO);
			subscription.setUserId(userId);
			subscription.setModifyDate(new Date());
			perSubscriptionEao.save(subscription);
		}
	}
}
