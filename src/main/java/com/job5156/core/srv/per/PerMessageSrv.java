package com.job5156.core.srv.per;

import com.job5156.core.eao.SiteMessageEao;
import com.job5156.webapp.model.SiteMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 * Date:2015/6/16 9:22
 *
 * @author hjs
 * @version 1.0
 */
@Service
@Transactional(value="transactionManagerAction")
public class PerMessageSrv {
    @Resource
    private SiteMessageEao siteMessageEao;

    public List<SiteMessage> findPerIndexMessageList(Integer perId) {
        return siteMessageEao.findPerIndexMessageList(perId);
    }

    public List<SiteMessage> findPerIndexFairMsgList(Integer perId) {
        return siteMessageEao.findPerIndexFairMsgList(perId);
    }

    public List<SiteMessage> findPerAskIsReplyed(Integer perUserId){
        return siteMessageEao.findPerAskIsReplyed(perUserId);
    }

    public List<SiteMessage> findPerIndexMessageListBySys(Integer perId) {
        return siteMessageEao.findPerIndexMessageListBySys(perId);
    }

    public List<SiteMessage> findPerIndexMessageNotInterview(Integer perId) {
        return siteMessageEao.findPerIndexMessageNotInterview(perId);
    }

    public Integer countByUserId(Integer perId){
        return siteMessageEao.countPerIndexMessage(perId);
    }
    public Integer countByUserIdAfter2015(Integer perId){
        return siteMessageEao.countPerIndexMessageAfter2015(perId);
    }

    public Integer countUnreadMsgByUserId(Integer perId){
        return siteMessageEao.countPerIndexUnreadMessage(perId);
    }
    public Integer countUnreadMsgByUserIdAfter2015(Integer perId){
        return siteMessageEao.countPerIndexUnreadMessageAfter2015(perId);
    }

    public List<SiteMessage> findPerIndexMessageListByPage(Integer perId,Integer pageNum,Integer pageSize) {
        return siteMessageEao.findPerIndexMessageListByPage(perId, pageNum,pageSize);
    }
}
