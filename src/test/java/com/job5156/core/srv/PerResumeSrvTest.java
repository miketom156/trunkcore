package com.job5156.core.srv;

import com.job5156.core.TestBase;
import com.job5156.core.bo.mapping.PerResumeBo;
import com.job5156.core.eao.per.PerUserEao;
import com.job5156.webapp.model.per.PerUser;
import com.job5156.core.srv.per.PerResumeSrv;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
/**
 * <p></p>
 * Date:2015/6/18 17:28
 * @author hjs
 * @version 1.0
 */
public class PerResumeSrvTest extends TestBase {
    @Resource
    private PerResumeSrv perResumeSrv;
    @Resource
    private PerUserEao perUserEao;

    @Test
    public void testGetResumeVo(){
        PerUser perUser = perUserEao.get(DEFAULT_PID);
        PerResumeBo perResumeVo = perResumeSrv.getResumeVo(perUser.getDefaultPerResume());
        PerResumeBo.IntentInfoVo intentInfoVo = perResumeVo.getIntentInfoVo();
        //测试点：判断期望工作地点
        Assert.assertEquals("期望工作地点","14010600,14010500",intentInfoVo.getJobLocation());
        //测试点：判断期望工作职位是否正确
        Assert.assertEquals("期望工作职位","3019,3011",intentInfoVo.getJobCode());
        //测试点：判断自定义职位是否正确
        Assert.assertEquals("自定义职位","",intentInfoVo.getJobName());
    }

}
