package com.job5156.core.srv.com;

import com.job5156.core.common.Constants;
import com.job5156.core.eao.SiteMessageEao;
import com.job5156.webapp.model.SiteMessage;
import com.job5156.webapp.model.com.ComInfo;
import com.job5156.webapp.model.com.ComPosition;
import com.job5156.webapp.model.per.PerResume;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p></p>
 * Date:2015/5/8 16:04
 *
 * @author pzm
 * @version 1.0
 */

@Service
@Transactional(value = "transactionManagerAction")
public class ComMessageSrv {
    private Logger logger = Logger.getLogger(ComMessageSrv.class);
    @Resource
    private SiteMessageEao siteMessageEao;
    /**
     *
     * 消息类型
     * @author xh
     * @date 2014-11-12 上午10:15:21
     *
     */
    public enum SiteMessageType {
        /**
         * 个人发送消息给企业
         */
        PER_TO_COM,
        /**
         * 企业发送消息给个人
         */
        COM_TO_PER;
    }

    public Boolean perSendMessageToCom(PerResume perResume, ComPosition comPosition, String title, String content, String category, String type) {
        ComInfo comInfo = comPosition.getComInfo();
        return sendMessage(
                perResume.getPerUser().getId(),
                perResume.getPerUser().getUserName(),
                perResume.getId(), comPosition.getComId(), comInfo.getComName(), comPosition.getId(), comPosition.getPosName(), title, content, category, type, SiteMessageType.PER_TO_COM);
    }

    private Boolean sendMessage(Integer perUserId, String perUserName, Integer resumeId, Integer comId, String comName, Integer posId, String posName, String title, String content, String category, String type, SiteMessageType msgType) {
        try {

            SiteMessage siteMessage = new SiteMessage();

            String comNameStr = StringUtils.left(comName, 100);
            String posNameStr = StringUtils.left(posName, 500);
            String titleStr = StringUtils.left(title, 100);
            String contentStr = StringUtils.left(content, 1024);

            siteMessage.setCategory(category);
            siteMessage.setType(type);
            siteMessage.setPerUserId(perUserId);
            siteMessage.setPerUserName(ObjectUtils.toString(perUserName));
            siteMessage.setComId(comId);
            siteMessage.setComDelFlag(0);
            siteMessage.setPerDelFlag(0);
            siteMessage.setResumeId(resumeId);

            if (msgType == SiteMessageType.COM_TO_PER) {
                siteMessage.setComeFrom(Constants.SITE_MESSAGE_SENDER_COM);
                siteMessage.setSourceId(comId);
                siteMessage.setSourceName(ObjectUtils.toString(comNameStr));
            } else if (msgType == SiteMessageType.PER_TO_COM) {
                siteMessage.setSourceId(perUserId);
                siteMessage.setComeFrom(Constants.SITE_MESSAGE_SENDER_PER);
                siteMessage.setSourceName(ObjectUtils.toString(perUserName));
            }

            siteMessage.setPosId(posId);
            siteMessage.setPosName(ObjectUtils.toString(posNameStr));
            siteMessage.setTitle(ObjectUtils.toString(titleStr));
            siteMessage.setContent(ObjectUtils.toString(contentStr));
            siteMessage.setReplyId(0);
            siteMessage.setReadFlag(0);
            siteMessage.setPublicFlag(0);
            siteMessage.setDraftFlag(0);
            siteMessage.setCreateDate(DateTime.now().toDate());
            siteMessage.setUpdateDate(DateTime.now().toDate());
            saveMessage(siteMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存站内消息失败:" + e);
            return false;
        }
    }

    /**
     * 保存站点消息(包含更新groupId)
     *
     * @param siteMessage
     * @return
     */
    @Transactional("action")
    public boolean saveMessage(SiteMessage siteMessage) {
        try {
            Integer id = siteMessageEao.save(siteMessage);
            if (id != null && id > 0) {
                try {
                    siteMessage.setId(id);
                    siteMessage.setGroupId(id);
                    siteMessageEao.update(siteMessage);
                    return true;
                } catch (Exception e) {
                    logger.error("更新站内消息的groupId出错了.");
                    e.printStackTrace();
                    return false;
                }
            }
        } catch (Exception e) {
            logger.error("保存站内消息失败");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
