package com.job5156.core.srv;

import com.job5156.core.TestBase;
import com.job5156.webapp.model.com.ComPosition;
import com.job5156.core.srv.com.ComPositionSrv;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * Date:2015/6/18 17:28
 *
 * @author hjs
 * @version 1.0
 */
public class ComPositionSrvTest extends TestBase {

    @Resource
    private ComPositionSrv comPositionSrv;
    @Test
    public void test(){
        List<Integer> comIdLis = new ArrayList<>();
        comIdLis.add(160795);
        comIdLis.add(925726);
        List<ComPosition> comPositionList = comPositionSrv.getPosListByComId(comIdLis);
        //测试点：返回公司职位id是否正确
        Assert.assertEquals("comIdList", new Integer(8048725),comPositionList.get(0).getId());
    }
}
