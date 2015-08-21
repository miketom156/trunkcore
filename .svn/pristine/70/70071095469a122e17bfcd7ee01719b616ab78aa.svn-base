package com.job5156.core.srv;


import com.job5156.core.TestBase;
import com.job5156.core.eao.per.PerInviteLogEao;
import com.job5156.webapp.model.per.job.PerInviteLog;
import com.job5156.core.srv.per.PerInviteLogSrv;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 * Date:2015/6/10 14:25
 *
 * @author hjs
 * @version 1.0
 */
public class PerInviteLogSrvTest extends TestBase {
    @Resource
    private PerInviteLogSrv perInviteLogSrv;
    @Resource
    private PerInviteLogEao perInviteLogEao;


    @Test
    public void testFillInviteMixInfo(){
        PerInviteLog perInviteLog = new PerInviteLog();
        perInviteLog.setId(7668303);
        perInviteLog.setComId(160795);
        perInviteLog.setPosId(7986343);
        perInviteLogSrv.fillInviteMixInfo(perInviteLog);
        PerInviteLog perInviteLog1 = perInviteLogEao.get(7668303);
        String mixInfo = "{\"comIndustry\":10,\"contactPhone\":\"0769-81176666-8114\",\"degree\":4,\"jobLocation\":\"[14010000]\",\"posStatus\":9,\"comName\":\"东莞普世饰品有限公司\",\"description\":\"美术相关院校毕业\\r\\n熟练使用PS/AI 等设计软件\\r\\n对珠宝产品设计感兴趣，具有流行趋势的审美能力\",\"taoLabel\":\"[]\",\"posName\":\"珠宝设计\",\"salary\":0,\"workYear\":1,\"contactPerson\":\"钟小姐\",\"comProperty\":3}";
        //测试点：修改后的mixinfo信息是否正确
        Assert.assertEquals("mixInfo",mixInfo,perInviteLog1.getMixInfo());

    }

    @Test
    public void testFindPerInviteLogList(){
        List<PerInviteLog> perInviteLogList = perInviteLogSrv.findPerInviteLogList(DEFAULT_PID);
        //测试点：邀请个人面试记录信息是否正确
        Assert.assertEquals("InviteLogComId",new Integer(160795),perInviteLogList.get(0).getComId());
    }
}
