package com.job5156.core.srv;

import com.job5156.core.TestBase;
import com.job5156.core.eao.per.PerViewLogEao;
import com.job5156.webapp.model.per.job.PerViewLog;
import com.job5156.core.srv.per.PerViewLogSrv;
import org.junit.Assert;
import org.junit.Test;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 * Date:2015/6/18 16:46
 *
 * @author hjs
 * @version 1.0
 */
public class PerViewLogSrvTest  extends TestBase {
    @Resource
    private PerViewLogSrv perViewLogSrv;
    @Resource
    private PerViewLogEao perViewLogEao;

    @Test
    public void testFindPerViewLogList(){
        List<PerViewLog> perViewLogList = perViewLogSrv.findPerViewLogList(DEFAULT_PID);
        //测试点：企业信息是否正确
        Assert.assertEquals("ViewLogComId",new Integer(1408),perViewLogList.get(0).getComId());
    }

    @Test
    public void testFillViewMixInfo(){
        PerViewLog perViewLog = new PerViewLog();
        perViewLog.setId(46815109);
        perViewLog.setComId(1408);
        perViewLogSrv.fillViewMixInfo(perViewLog);
        perViewLog = perViewLogEao.get(perViewLog.getId());
        String mixInfo = "{\"location\":1401,\"comName\":\"广州宝力机械科技有限公司东莞分公司\",\"property\":4,\"employeeNumber\":2,\"industry\":13}";
        //测试点：perViewLog的mixInfo是否正确
        Assert.assertEquals("PerViewMixInfo",mixInfo,perViewLog.getMixInfo());
    }


}
