package com.job5156.core.biz;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.job5156.core.bo.BizRetBo;
import com.job5156.core.bo.BizRetCode;
import com.job5156.core.bo.PosBriefSimpleBo;
import com.job5156.core.bo.form.per.PerIndexInfoBo;
import com.job5156.core.bo.form.per.PerViewLogBo;
import com.job5156.core.bo.mapping.PerInviteLogBo;
import com.job5156.core.common.Constants;
import com.job5156.core.common.MsgConstants;
import com.job5156.core.common.helper.PosRecommendHelper;
import com.job5156.core.common.util.KeyMaskUtil;
import com.job5156.webapp.model.SiteMessage;
import com.job5156.webapp.model.com.ComPosition;
import com.job5156.webapp.model.irc.IrcReserve;
import com.job5156.webapp.model.per.PerFollowCom;
import com.job5156.webapp.model.per.job.PerInviteLog;
import com.job5156.webapp.model.per.job.PerViewLog;
import com.job5156.core.srv.AvatarSrv;
import com.job5156.core.srv.PosRecommendSrv;
import com.job5156.core.srv.RedisSrv;
import com.job5156.core.srv.com.ComPositionSrv;
import com.job5156.core.srv.com.TaleBaseComTestSrv;
import com.job5156.core.srv.per.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p></p>
 * Date:2015/6/11 17:28
 *
 * @author hjs
 * @version 1.0
 */
@Component
public class PerIndexBiz {
    @Resource
    private RedisSrv redisSrv;
    @Resource
    private PerViewLogSrv perViewLogSrv;
    @Resource
    private TaleBaseComTestSrv taleBaseComTestSrv;
    @Resource
    private AvatarSrv avatarSrv;
    @Resource
    private PerInviteLogSrv perInviteLogSrv;
    @Resource
    private PosRecommendSrv posRecommendSrv;
    @Resource
    private PerMessageSrv perMessageSrv;
    @Resource
    private PerJobFairSrv perJobFairSrv;
    @Resource
    private ComPositionSrv comPositionSrv;
    @Resource
    private PerFollowComSrv perFollowComSrv;
    @Resource
    private PosRecommendHelper posRecommendHelper;

    /**
     * 个人中心首页动态 - 邀请面试
     * @param perUserId
     * @return
     */
    public BizRetBo<PerIndexInfoBo> infoInvite(Integer perUserId) {
        Date lastReadTime = redisSrv.getIndexReadTime(perUserId, "invite");
        Gson gson = new Gson();
        String redisKey = Constants.REDIS_KEY_PER_DYNAMIC_INVITE_PREFIX + perUserId;

        List<PerIndexInfoBo> infoList;
        if (redisSrv.isKeyExist(redisKey)) { //Redis缓存的内容存在
            String infoListJson = redisSrv.readFromString(redisKey);
            infoList = gson.fromJson(infoListJson, new TypeToken<List<PerIndexInfoBo>>() {
            }.getType());
            for (int i = 0; i < infoList.size(); i++) {
                if (new DateTime(lastReadTime).isBefore(new DateTime(infoList.get(i).getTime()))) {
                    infoList.get(i).setReadFlag(0);
                } else {
                    infoList.get(i).setReadFlag(1);
                }
            }
        } else { //Redis未缓存内容，从数据库中读取，并创建Redis内容
            List<PerViewLog> perViewLogList = perViewLogSrv.findPerViewLogList(perUserId);
            List<PerInviteLog> perInviteLogList = perInviteLogSrv.findPerInviteLogList(perUserId);
            List<Object> list = taleBaseComTestSrv.findByComTestReport(perUserId);
            infoList = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(list)) {
                infoList.add(this.invitedJob(list));
            }
            if (CollectionUtils.isNotEmpty(perViewLogList) || CollectionUtils.isNotEmpty(perInviteLogList)) {
                infoList.add(this.checkResume(perViewLogList, lastReadTime));
                infoList.add(this.invitationInterView(perInviteLogList, lastReadTime));
                if (CollectionUtils.isNotEmpty(infoList)) {
                    //排序
                    infoList = listDateSort(infoList);
                    //缓存到Redis中
                    redisSrv.writeToString(redisKey, gson.toJson(infoList));
                    redisSrv.setKeyExpire(redisKey, Constants.REDIS_EXPIRE_TIME_DYNAMIC); //缓存的生存期
                }
            }
        }
        redisSrv.updateIndexReadTime(perUserId, "invite");
        return new BizRetBo<>(BizRetCode.SUCCESS, infoList);
    }

    /**
     *个人中心首页动态 - 推荐职位
     * @param
     * @return
     */
    public BizRetBo<PerIndexInfoBo> infoRecommend(Integer perUserId, String redisKey, Integer itemSize) {
        Date lastReadTime = redisSrv.getIndexReadTime(perUserId, "recommend");
        List<PerIndexInfoBo> infoList = Lists.newArrayList();
        List<PosBriefSimpleBo> guestYouLikePosList = Lists.newArrayList();

        String site = "index"; //个人中心类同与首页
        if(StringUtils.isBlank(redisKey)){
            //组装猜您喜欢的 REDISKEY
            redisKey = posRecommendHelper.generateRedisKey(site);
        }
        guestYouLikePosList = posRecommendSrv.getGuestYouLikePosBriefList(site, "", perUserId, redisKey, itemSize);

        //将搜索出来的结果组装成信息显示在个人中心动态中
        if (CollectionUtils.isNotEmpty(guestYouLikePosList)) {
            for (PosBriefSimpleBo posBriefSimpleBo : guestYouLikePosList) {
                infoList.add(assemblyRecommendResults(posBriefSimpleBo, lastReadTime));
            }
        }
        redisSrv.updateIndexReadTime(perUserId, "recommend");
        return new BizRetBo<>(BizRetCode.SUCCESS, infoList);
    }

    /**
     * 个人中心首页动态 - 消息
     * @param perUserId
     * @return
     */
    public BizRetBo<PerIndexInfoBo> infoMessage(Integer perUserId) {
        Date lastReadTime = redisSrv.getIndexReadTime(perUserId, "message");
        List<SiteMessage> sysMessageList = perMessageSrv.findPerIndexMessageListBySys(perUserId);
        List<SiteMessage> askMessageList = perMessageSrv.findPerAskIsReplyed(perUserId);
        List<SiteMessage> replyApplyMsgList = perMessageSrv.findPerIndexMessageNotInterview(perUserId);
        List<PerIndexInfoBo> infoList = Lists.newArrayList();
        //系统消息
        if (CollectionUtils.isNotEmpty(sysMessageList)) {
            infoList.add(this.sysNews(sysMessageList,lastReadTime));
        }
        //个人与企业的交互消息（一问一答）
        if (CollectionUtils.isNotEmpty(askMessageList)) {
            infoList.add(this.perNews(askMessageList,lastReadTime));
        }
        //企业对个人的应聘回复：待考虑、婉拒
        if (CollectionUtils.isNotEmpty(replyApplyMsgList)) {
            infoList.add(this.comNews(replyApplyMsgList,lastReadTime));
        }
        redisSrv.updateIndexReadTime(perUserId, "message");
           return new BizRetBo<>(BizRetCode.SUCCESS, infoList);
    }

    /**
     * 个人中心首页动态 - 招聘会
     * @param perUserId
     * @return
     */
    public  BizRetBo<PerIndexInfoBo> infoFair(Integer perUserId) {
        Date lastReadTime = redisSrv.getIndexReadTime(perUserId, "fair");
        List<PerIndexInfoBo> infoList = Lists.newArrayList();
        //加入站内消息--招聘会类消息
        List<SiteMessage> siteMessageList = perMessageSrv.findPerIndexFairMsgList(perUserId);
        if (CollectionUtils.isNotEmpty(siteMessageList)) {
            infoList.add(this.recruitmentNews(siteMessageList, lastReadTime));
        }
        //加入招聘会预约表的消息
        List<IrcReserve> ircReserveList = perJobFairSrv.findComInviteByUserId(perUserId);
        if (CollectionUtils.isNotEmpty(ircReserveList)) {
            infoList.add(this.reservationNews(ircReserveList,lastReadTime));
        }
        redisSrv.updateIndexReadTime(perUserId, "fair");
        return new BizRetBo<>(BizRetCode.SUCCESS, infoList);
    }

    //个人中心首页动态 - 关注企业
    public  BizRetBo<PerIndexInfoBo>  infoCompany(Integer perUserId) {
        Date lastReadTime = redisSrv.getIndexReadTime(perUserId, "company");
        List<PerFollowCom> perFollowComList = perFollowComSrv.findByPerUserId(perUserId);
        List<PerIndexInfoBo> infoList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(perFollowComList)) {
            infoList.add(this.postOffice(perFollowComList,lastReadTime));
        }
        redisSrv.updateIndexReadTime(perUserId, "company");
           return new BizRetBo<>(BizRetCode.SUCCESS, infoList);
    }

    /**
     * 首页猜你喜欢
     * @param perUserId 用户id
     * @param keyword 搜索关键字
     * @param site 分站点
     * @param redisKey 缓存的rediskey
     * @param itemSize 查询条目数量
     * @return
     */
    public BizRetBo<PosBriefSimpleBo> guestYouLikePosList(Integer perUserId, String keyword, String site, String redisKey, Integer itemSize) {
        if (StringUtils.isBlank(site)) {
            site = "index";
        }
        //组装猜您喜欢的 REDISKEY
        if (StringUtils.isBlank(redisKey)) {
            //组装猜您喜欢的 REDISKEY
            redisKey = posRecommendHelper.generateRedisKey(site);
        }
        List<PosBriefSimpleBo> guestYouLikePosList = posRecommendSrv.getGuestYouLikePosBriefList(site, keyword, perUserId, redisKey, itemSize);
        return new BizRetBo<>(BizRetCode.SUCCESS, guestYouLikePosList);
    }

    /**
     * 邀请参加职位
     *
     * @param list
     * @return
     */
    private PerIndexInfoBo invitedJob(List<Object> list) {
        for (Object obj : list) {
            Object[] objs = (Object[]) obj;
            try {
                String inviteDateString = objs[6].toString();//邀请日期
                String endDateString = objs[7].toString();//有效日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date endDate = sdf.parse(endDateString);//有效日期
                Date date = new Date();
                Date inviteDate = sdf.parse(inviteDateString);
                StringBuilder viewStr = new StringBuilder();
                viewStr.append("<input type='hidden' id='reportId' name='reportId' value='" + objs[9] + "'/>");
                viewStr.append("<a href='/corp/" + objs[0] + "' target='_blank'><strong>" + objs[1] + "</strong></a>");
                viewStr.append("邀请您参加职位");
                viewStr.append("<a href='/jobs/" + objs[2] + "' target='_blank'>" + objs[3] + "</a>");
                if (endDate.getTime() - date.getTime() > 0) {
                    viewStr.append("的线上测评，请于有效期" + sdf.format(endDate) + "之前完成");
                    viewStr.append("<a href='/talent_exam/start/com_test/" + objs[9] + "' target='_blank'>马上测评</a>");
                } else {
                    viewStr.append("的线上测评，<font color='red'>测评已经过期" + sdf.format(endDate) + "<font>");
                    viewStr.append("<a href='javascript:;' style='border:0; text-decoration:underline;color:#888;cursor:text;' target='_blank' disabled ='disabled'>马上测评</a>");
                }
                PerIndexInfoBo infoVo = new PerIndexInfoBo();
                infoVo.setContent(viewStr.toString());
                infoVo.setTime(endDate);
                Integer comId = Integer.valueOf(String.valueOf(objs[0]));
                infoVo.setAvatar(avatarSrv.getComAvatar(comId));
                if (new DateTime(inviteDate).isBefore(new DateTime(endDate))) {
                    infoVo.setReadFlag(0);
                } else {
                    infoVo.setReadFlag(1);
                }
                return infoVo;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 查看简历
     *
     * @param perViewLogList
     * @param lastReadTime
     * @return
     */
    private PerIndexInfoBo checkResume(List<PerViewLog> perViewLogList, Date lastReadTime) {
        for (PerViewLog perViewLog : perViewLogList) {
            perViewLogSrv.fillViewMixInfo(perViewLog);
            if (StringUtils.isNotBlank(perViewLog.getMixInfo()) && !StringUtils.equals("{}", perViewLog.getMixInfo())) {
                PerViewLogBo perViewLogVo = new PerViewLogBo(perViewLog);
                System.out.print("comId" + perViewLog.getComId());
                System.out.print("AAA" + perViewLogVo.getMixInfoVo().getComName());
                String viewStr = "<a href='/corp/" + perViewLog.getComId() + "' target='_blank'><strong>" + perViewLogVo.getMixInfoVo().getComName() + "</strong></a>"
                        + "查看了您的"
                        + "<a href='/per/resume' target='_blank'>简历</a>"
                        + (null != perViewLog.getLookStatus() && perViewLog.getLookStatus() == 1 ? "及联系方式" : "");

                PerIndexInfoBo infoVo = new PerIndexInfoBo();
                infoVo.setContent(viewStr);
                infoVo.setTime(perViewLog.getCreTime());
                infoVo.setAvatar(avatarSrv.getComAvatar(perViewLog.getComId()));
                if (new DateTime(lastReadTime).isBefore(new DateTime(perViewLog.getCreTime()))) {
                    infoVo.setReadFlag(0);
                } else {
                    infoVo.setReadFlag(1);
                }
                return infoVo;
            }
        }
        return null;
    }

    /**
     * 邀请您参加面试
     *
     * @param perInviteLogList
     * @param lastReadTime
     * @return
     */
    private PerIndexInfoBo invitationInterView(List<PerInviteLog> perInviteLogList, Date lastReadTime) {
        for (PerInviteLog perInviteLog : perInviteLogList) {
            perInviteLogSrv.fillInviteMixInfo(perInviteLog);
            PerInviteLogBo perInviteLogVo = new PerInviteLogBo(perInviteLog);
            if (StringUtils.isNotBlank(perInviteLog.getMixInfo()) && !StringUtils.equals("{}", perInviteLog.getMixInfo())) {
                String inviteStr = "<a href='/corp/" + perInviteLogVo.getComId() + "' target='_blank'><strong>" + perInviteLogVo.getMixInfoVo().getComName() + "</strong></a>"
                        + "邀请您面试职位"
                        + "<a href='/jobs/" + KeyMaskUtil.posIdToNo(perInviteLogVo.getPosId(), (short) 1) + "' target='_blank'>" + perInviteLogVo.getMixInfoVo().getPosName() + "</a>"
                        + "[" + perInviteLogVo.getMixInfoVo().getJobLocationStr()
                        + "|" + perInviteLogVo.getMixInfoVo().getSalaryStr()
                        + "|" + perInviteLogVo.getMixInfoVo().getDegreeStr()
                        + "|" + perInviteLogVo.getMixInfoVo().getWorkYearStr() + "]";

                PerIndexInfoBo infoVo = new PerIndexInfoBo();
                infoVo.setContent(inviteStr);
                infoVo.setTime(perInviteLog.getCreTime());
                infoVo.setAvatar(avatarSrv.getComAvatar(perInviteLog.getComId()));
                if (new DateTime(lastReadTime).isBefore(new DateTime(perInviteLog.getCreTime()))) {
                    infoVo.setReadFlag(0);
                } else {
                    infoVo.setReadFlag(1);
                }
                return infoVo;
            }
        }
        return null;
    }

    /**
     * 系统消息
     *
     * @param sysMessageList
     * @param lastReadTime
     * @return
     */
    private PerIndexInfoBo sysNews(List<SiteMessage> sysMessageList, Date lastReadTime) {
        for (SiteMessage siteMessage : sysMessageList) {
            PerIndexInfoBo infoVo = new PerIndexInfoBo();
            String str = "";
            if (siteMessage.getComeFrom().equals(Constants.SITE_MESSAGE_SENDER_SYS)) {
                str = "智通人才网给您发来消息：" + siteMessage.getContent();
                infoVo.setAvatar(avatarSrv.getSysAvatar(0));
            }
            infoVo.setContent(str);
            infoVo.setTime(siteMessage.getCreateDate());
            if (new DateTime(lastReadTime).isBefore(new DateTime(siteMessage.getCreateDate()))) {
                infoVo.setReadFlag(0);
            } else {
                infoVo.setReadFlag(1);
            }
            if (StringUtils.isNotBlank(str)) {
                return infoVo;
            }
        }
        return null;
    }

    /**
     * 个人与企业的交互消息（一问一答）
     * @param askMessageList
     * @param lastReadTime
     * @return
     */
    private PerIndexInfoBo perNews(List<SiteMessage> askMessageList, Date lastReadTime) {

        for (SiteMessage siteMessage : askMessageList) {
            if (siteMessage == null || siteMessage.getCategory() == null) continue;
            if (siteMessage == null || siteMessage.getReplyMessage() == null) continue;

            PerIndexInfoBo infoVo = new PerIndexInfoBo();
            String str = "";
            if (StringUtils.equals(siteMessage.getCategory(), MsgConstants.PER_DYNAMIC_CATEGORY_INTERACTION)) {
                str = siteMessage.getTitle() + "</br>";
                str += "&nbsp;&nbsp;&nbsp;&nbsp;<a href='/corp/" + siteMessage.getComId() + "' target='_blank'><strong>" + siteMessage.getReplyMessage().getSourceName() + "</strong></a>"
                        + " : " + siteMessage.getReplyMessage().getContent();
                infoVo.setAvatar(avatarSrv.getComAvatar(siteMessage.getComId()));
            }
            infoVo.setContent(str);
            infoVo.setTime(siteMessage.getCreateDate());
            if (new DateTime(lastReadTime).isBefore(new DateTime(siteMessage.getCreateDate()))) {
                infoVo.setReadFlag(0);
            } else {
                infoVo.setReadFlag(1);
            }
            if (StringUtils.isNotBlank(str)) {
                return infoVo;
            }
        }
        return null;
    }

    /**
     * 企业对个人的应聘回复：待考虑、婉拒
     * @param replyApplyMsgList
     * @param lastReadTime
     * @return
     */
    private PerIndexInfoBo comNews(List<SiteMessage> replyApplyMsgList,Date lastReadTime){
        for (SiteMessage siteMessage : replyApplyMsgList) {
            if (siteMessage == null || siteMessage.getCategory() == null) continue;

            PerIndexInfoBo infoVo = new PerIndexInfoBo();
            String str = "";
            if (StringUtils.equals(siteMessage.getCategory(), MsgConstants.PER_DYNAMIC_CATEGORY_INVITE)) {
                str += "<a href='/corp/" + siteMessage.getComId() + "' target='_blank'><strong>" + siteMessage.getSourceName() + "</strong></a>"
                        + " 给您发来消息: " + siteMessage.getContent();
                infoVo.setAvatar(avatarSrv.getComAvatar(siteMessage.getComId()));
            }
            infoVo.setContent(str);
            infoVo.setTime(siteMessage.getCreateDate());
            if (new DateTime(lastReadTime).isBefore(new DateTime(siteMessage.getCreateDate()))) {
                infoVo.setReadFlag(0);
            } else {
                infoVo.setReadFlag(1);
            }
            if (StringUtils.isNotBlank(str)) {
                return infoVo;
            }
        }
        return null;
    }

    /**
     *加入站内消息--招聘会类消息
     * @param siteMessageList
     * @param lastReadTime
     * @return
     */
    private PerIndexInfoBo recruitmentNews(List<SiteMessage> siteMessageList,Date lastReadTime){
        for (SiteMessage message : siteMessageList) {
            if (message.getCategory() == null || message.getType() == null) {
                continue;
            }
            if (StringUtils.equals(message.getCategory(), MsgConstants.PER_DYNAMIC_CATEGORY_JOBFAIR)) {
                String str = message.getContent();

                PerIndexInfoBo infoVo = new PerIndexInfoBo();
                infoVo.setContent(str);
                infoVo.setTime(message.getCreateDate());
                infoVo.setAvatar(avatarSrv.getComAvatar(message.getComId()));
                if (new DateTime(lastReadTime).isBefore(new DateTime(message.getCreateDate()))) {
                    infoVo.setReadFlag(0);
                } else {
                    infoVo.setReadFlag(1);
                }
                return infoVo;
            }
        }
        return null;
    }

    /**
     * 加入招聘会预约表的消息
     * @param ircReserveList
     * @param lastReadTime
     * @return
     */
    private PerIndexInfoBo reservationNews(List<IrcReserve> ircReserveList,Date lastReadTime){
        for (IrcReserve ircReserve : ircReserveList) {
            if (StringUtils.isNotBlank(ircReserve.getComName())) {
                String str = "<a href='/corp/" + ircReserve.getComId() + "' target='_blank'><strong>" + ircReserve.getComName() + "</strong></a>"
                        + "邀请您参加" + ircReserve.getJfName() + "面试" + ircReserve.getPosName();

                PerIndexInfoBo infoVo = new PerIndexInfoBo();
                infoVo.setContent(str);
                infoVo.setTime(ircReserve.getResTime());
                infoVo.setAvatar(avatarSrv.getComAvatar(ircReserve.getComId()));
                if (new DateTime(lastReadTime).isBefore(new DateTime(ircReserve.getResTime()))) {
                    infoVo.setReadFlag(0);
                } else {
                    infoVo.setReadFlag(1);
                }
                return infoVo;
            }
        }
        return null;
    }

    /**
     * 关注企业发布职位
     * @param perFollowComList
     * @param lastReadTime
     * @return
     */
    private PerIndexInfoBo postOffice(List<PerFollowCom> perFollowComList,Date lastReadTime){
        List<Integer> comIdList = Lists.newArrayList();
        for (PerFollowCom perFollowCom : perFollowComList) {
            comIdList.add(perFollowCom.getComId());
        }
        List<ComPosition> comPositionList = comPositionSrv.getPosListByComId(comIdList);
        if (CollectionUtils.isNotEmpty(comPositionList)) {
            for (ComPosition comPosition : comPositionList) {
                String str = "<a href='/corp/" + comPosition.getComId() + "' target='_blank'><strong>" + comPosition.getComInfo().getComName() + "</strong></a>"
                        + "发布了职位"
                        + "<a href='/jobs/" + KeyMaskUtil.posIdToNo(comPosition.getId(), (short) 1) + "' target='_blank'>" + comPosition.getPosName() + "</a>";

                PerIndexInfoBo infoVo = new PerIndexInfoBo();
                infoVo.setContent(str);
                infoVo.setTime(comPosition.getCreateDate());
                infoVo.setAvatar(avatarSrv.getComAvatar(comPosition.getComId()));
                if (new DateTime(lastReadTime).isBefore(new DateTime(comPosition.getCreateDate()))) {
                    infoVo.setReadFlag(0);
                } else {
                    infoVo.setReadFlag(1);
                }
                return infoVo;
            }
        }
        return null;
    }

    /**
     * 将搜索出来的结果组装成信息显示在个人中心动态中
     * @param posBriefSimpleBo
     * @param lastReadTime
     * @return
     */
    private PerIndexInfoBo assemblyRecommendResults(PosBriefSimpleBo posBriefSimpleBo,Date lastReadTime){
            PerIndexInfoBo infoVo = new PerIndexInfoBo();
            String str = "智通人才网根据您的求职意向向您推荐职位："
                    + "<a href='/jobs/" + posBriefSimpleBo.getPosNo() + "' target='_blank'><strong>" + posBriefSimpleBo.getPosName() + "</strong></a>"
                    + "[" + posBriefSimpleBo.getCity() + "|" + posBriefSimpleBo.getSalaryStr() + "] - " + posBriefSimpleBo.getComName();
            infoVo.setContent(str);
            infoVo.setTime(DateTime.now().toDate());
            infoVo.setAvatar(avatarSrv.getSysAvatar(0));
            if (new DateTime(lastReadTime).isBeforeNow()) {
                infoVo.setReadFlag(0);
            } else {
                infoVo.setReadFlag(1);  //已读
            }
           return infoVo;
    }


    private List<PerIndexInfoBo> listDateSort(List<PerIndexInfoBo> list) {
        Collections.sort(list, new Comparator<PerIndexInfoBo>() {
            @Override
            public int compare(PerIndexInfoBo o1, PerIndexInfoBo o2) {
                try {
                    DateTime o1Time = new DateTime(o1.getTime());
                    DateTime o2Time = new DateTime(o2.getTime());
                    return o2Time.compareTo(o1Time);
                } catch (Exception e) {
                    e.printStackTrace();
                    // do nothing
                    // 一旦出现错误的时候，不排序直接保存即可
                }
                return 0;
            }
        });
        return list;
    }

}
