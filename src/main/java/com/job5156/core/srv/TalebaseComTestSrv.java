package com.job5156.core.srv;

import com.google.common.collect.Maps;
import com.job5156.core.common.Constants;
import com.job5156.core.common.MailConstants;
import com.job5156.core.common.helper.WeiXinApiHelper;
import com.job5156.core.eao.RedisEao;
import com.job5156.core.eao.com.ComInfoEao;
import com.job5156.core.eao.com.ComPositionEao;
import com.job5156.core.eao.com.talebase.TaleBaseComTestApplyEao;
import com.job5156.core.eao.com.talebase.TaleBaseComTestAutoEao;
import com.job5156.core.eao.com.talebase.TaleBaseComTestDependentEao;
import com.job5156.core.eao.com.talebase.TaleBaseComTestReportEao;
import com.job5156.webapp.model.com.ComInfo;
import com.job5156.webapp.model.com.ComPosition;
import com.job5156.webapp.model.com.ComReceive;
import com.job5156.webapp.model.com.talebase.TalebaseComTestApply;
import com.job5156.webapp.model.com.talebase.TalebaseComTestAuto;
import com.job5156.webapp.model.com.talebase.TalebaseComTestDependent;
import com.job5156.webapp.model.com.talebase.TalebaseComTestReport;
import com.job5156.core.bo.mail.talebase.TalebaseComTestMailBo;
import com.job5156.core.bo.sms.InviteTestWeiXinMsgBo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p></p>
 * Date:2015/5/8 17:35
 *
 * @author pzm
 * @version 1.0
 */

@Service
@Transactional(value = "transactionManager")
public class TalebaseComTestSrv {
    @Resource
    private TaleBaseComTestAutoEao talebaseComTestAutoEao;//自动邀请测评

    @Resource
    private ComInfoEao comInfoEao;

    @Resource
    private MailSrv mailSrv;

    @Resource
    private TaleBaseComTestReportEao talebaseComTestReportEao;

    @Resource
    private RedisEao redisEao;

    @Resource
    private ComPositionEao comPositionEao;

    @Resource
    private WeiXinApiHelper weiXinApiHelper;

    @Resource
    private TaleBaseComTestDependentEao talebaseComTestDependentEao;

    @Resource
    private TaleBaseComTestApplyEao talebaseComTestApplyEao;

    /**
     * 自动邀请数据查询
     *
     * @param comId
     * @param posId
     * @return
     */
    public List<TalebaseComTestAuto> findByComTestAutoList(Integer comId, Integer posId) {
        String hql = "FROM TalebaseComTestAuto cta where cta.comId=? AND cta.posId=?";
        return talebaseComTestAutoEao.find(hql, comId, posId);
    }

    /**
     * 邀请测评扣除点数
     * @param comReceive
     * @param comPosition
     * @param productId
     * @param inviteWeek
     * @return
     */
    public Map<String,Object> comTestInvite(ComReceive comReceive, ComPosition comPosition, Integer productId, Integer inviteWeek){
        Map<String,Object> paramMap = Maps.newHashMap();
        try {
            ComInfo comInfo = comInfoEao.get(comReceive.getComId());
            if(comInfo != null){
                TalebaseComTestReport ctr = new TalebaseComTestReport();
                ctr.setComId(comReceive.getComId());
                ctr.setPerId(comReceive.getPerUserId());
                ctr.setResumeId(comReceive.getResumeId());
                ctr.setPosId(null == comPosition ?comReceive.getPosId() : comPosition.getId());
                ctr.setProductId(productId);
                Date validDate = null;//有效日期
                if(inviteWeek != null && inviteWeek > 0){
                    if(inviteWeek == 1){
                        //1周内
                        validDate = DateUtils.addDays(new Date(), 7);
                    }else if(inviteWeek == 2){
                        //2周内
                        validDate = DateUtils.addDays(new Date(), 14);
                    }else{
                        //1月内
                        validDate = DateUtils.addMonths(new Date(), 1);
                    }
                    if(validDate != null){
                        ctr.setEndDate(validDate);
                    }
                }
                ctr.setInviteDate(new Date());//邀请日期
                if(validDate != null ){
                    if(!this.checkEmail(comReceive.getEmail())){
                        paramMap.put("mesage", "邮箱地址不合格！");
                        paramMap.put("posName", comReceive.getPosName());
                        paramMap.put("userName", comReceive.getUserName());
                        paramMap.put("resultType", "");
                        paramMap.put("result", false);
                        return paramMap;
                    }
                    //扣除测评产品的点数
                    boolean isSuccess = deductPointComtest(productId);
                    if(!isSuccess){
                        //返回的提示信息
                        paramMap.put("mesage", "邀请测评扣除产品点数失败！");
                        paramMap.put("posName", comReceive.getPosName());
                        paramMap.put("userName", comReceive.getUserName());
                        paramMap.put("resultType", "");
                        paramMap.put("result", false);
                        return paramMap;
                    }

                    TalebaseComTestMailBo ctMailVO = new TalebaseComTestMailBo(new TalebaseComTestMailBo.PlaceHolderVo(String.valueOf(comInfo.getId()), comInfo.getComName(), comReceive.getUserName(), comReceive.getPosName(), validDate,new Date()),
                            new String[]{comReceive.getEmail()});
                    mailSrv.insertEmailQueue(ctMailVO, MailConstants.MAIL_TASK_COM_TEST_INVITE);
                    //保存测评成员的相应信息
                    talebaseComTestReportEao.saveOrUpdate(ctr);
                    redisEao.del(Constants.REDIS_KEY_PER_DYNAMIC_INVITE_PREFIX + comReceive.getPerUserId());
                    //发送微信信息
                    ComPosition position = comPositionEao.get(comReceive.getPosId());
                    weiXinApiHelper.sendTextMessageToUserWithChecked(comReceive.getPerUserId(),
                            new InviteTestWeiXinMsgBo(comReceive.getComId(), comInfo.getComName(), comReceive.getUserName(), validDate,  position));
                    //返回的提示信息
                    paramMap.put("mesage", "邀请测评邮件发送成功！");
                    paramMap.put("posName", comReceive.getPosName());
                    paramMap.put("userName", comReceive.getUserName());
                    paramMap.put("resultType", "");
                    paramMap.put("result", true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paramMap;
    }

    /**
     * 正则表达式过滤邮件地址
     *
     * @param email
     * @return
     */
    private boolean checkEmail(String email) {
        if(StringUtils.isNotBlank(email)){
            Pattern pattern = Pattern.compile(Constants.PATTERN_EMAIL, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            return matcher.find();
        }
        return false;
    }

    /**
     * 扣除企业测评产品的点数
     *
     * @param productId
     * @return
     */
    private boolean deductPointComtest(Integer productId){
        boolean isSuccess = false;
        List<Integer> list = talebaseComTestDependentEao.getOrderList(productId);
        String orderIds = "";
        for(Integer id : list){
            orderIds += id + ",";
        }
        TalebaseComTestApply cta = talebaseComTestApplyEao.getComTestApply(orderIds);
        //先扣除总的产品点数
        if(cta.getOverplus() > 0){
            cta.setOverplus(cta.getOverplus()-1);
            talebaseComTestApplyEao.saveOrUpdate(cta);
        }
        //查询产品根据订单号与产品ID
        TalebaseComTestDependent ctd = talebaseComTestDependentEao.getComTestDependent(cta.getOrderId(), productId);
        if(ctd != null){
            if(null != ctd.getProductTotal() && null != ctd.getProductOverplus()){
                //产品总数
                if((ctd.getProductTotal() > 0) && (ctd.getProductOverplus() > 0)){
                    ctd.setProductOverplus(ctd.getProductOverplus()-1);
                    if(null != ctd.getProductSum()){
                        ctd.setProductSum(ctd.getProductSum()+1);
                    }else{
                        ctd.setProductSum(1);
                    }
                }
            }else{
                //如果产品总数没有给值，那么产品剩余数必须小于订单中的剩余数
                if((null == ctd.getProductTotal() || ctd.getProductTotal() == 0) && cta.getOverplus() > 0){
                    if(null != ctd.getProductSum()){
                        ctd.setProductSum(ctd.getProductSum()+1);
                    }else{
                        ctd.setProductSum(1);
                    }
                }
            }

            talebaseComTestDependentEao.saveOrUpdate(ctd);
            isSuccess = true;
        }
        return isSuccess;
    }
}
