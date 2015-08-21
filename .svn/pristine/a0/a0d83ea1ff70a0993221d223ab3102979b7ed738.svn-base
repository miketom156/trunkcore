package com.job5156.core.srv;

import com.job5156.core.TestBase;
import com.job5156.webapp.model.SiteMessage;
import com.job5156.core.srv.per.PerMessageSrv;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 * Date:2015/6/18 17:28
 *
 * @author hjs
 * @version 1.0
 */
public class PerMessageSrvTest extends TestBase{
    @Resource
    private PerMessageSrv perMessageSrv;

    @Test
    public void testFindPerIndexMessageListBySys(){
        List<SiteMessage> sysMessageList = perMessageSrv.findPerIndexMessageListBySys(DEFAULT_PID);
        String content = "尊敬的用户陈义春，您好：<br/>&nbsp;&nbsp; 感谢您注册智通人才网，您的简历资料已经通过我们的审核。祝您早日找到满意的工作-job5156.com";
        //测试点：判断系统(SYS)返回内容是否正确
        Assert.assertEquals("系统发送内容",content,sysMessageList.get(0).getContent());
    }
    @Test
    public void testFindPerAskIsReplyed(){
        //测试点：判断个人（PER）返回内容是否正确
        List<SiteMessage> askMessageList = perMessageSrv.findPerAskIsReplyed(DEFAULT_PID);
        Assert.assertEquals("个人发送内容","应聘【设计】",askMessageList.get(0).getContent());
    }
    @Test
    public void testFindPerIndexMessageNotInterview (){
        //测试点：判断企业(COM)返回内容是否正确
        List<SiteMessage> replyApplyMsgList = perMessageSrv.findPerIndexMessageNotInterview(DEFAULT_PID);
        String content = "我们（东莞市银特丰光学玻璃科技有限公司）已经收到您通过Job5156.com（智通人才网）发来的应聘资料，感谢应聘本公司职位。 在未来的一周时间内，我们会认真的阅读及评估您的资料。如果有进一步接触的必要，我们会及时与您联系。您已经成功对本公司职位投递了简历，除非您应聘不同职位或者本人的工作经历有了重大变化，否则不需要投递第二份简历。如果在一个月内未收到我们的答复，您可以自行处理自己的资料。\n";
        Assert.assertEquals("企业发送内容",content,replyApplyMsgList.get(0).getContent());

    }


}
