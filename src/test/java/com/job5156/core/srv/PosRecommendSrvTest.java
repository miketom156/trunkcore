package com.job5156.core.srv;

import com.job5156.core.TestBase;
import com.job5156.core.bo.PosBriefSimpleBo;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 * Date:Administrator 2015-7-1
 *
 * @author hjs
 * @version 1.0
 */
public class PosRecommendSrvTest extends TestBase{

    @Resource
    private PosRecommendSrv posRecommendSrv;

    //按求职意向搜索职位
    @Test
    public void testGetPosListByIntentInfo(){
        List<PosBriefSimpleBo> posList = posRecommendSrv.getPosListByIntentInfo(DEFAULT_PID);
        //测试点：返回的posId是否正确
        Assert.assertEquals("posId",new Integer(7050244),posList.get(0).getPosId());
    }

    //职位搜索器的条件去搜索职位
    @Test
    public void testGetPosListBySearcher() {
        List<PosBriefSimpleBo> posList = posRecommendSrv.getPosListBySearcher(DEFAULT_PID);
        //测试点：返回的posId是否正确
        Assert.assertEquals("posId",new Integer(7997879),posList.get(0).getPosId());
    }

     //按应聘记录去搜索职位
    @Test
    public void testGetPosListByApplyLog() {
        List<PosBriefSimpleBo> posList = posRecommendSrv.getPosListByApplyLog(DEFAULT_PID);
        //测试点：返回的posId是否正确
        Assert.assertEquals("posId",new Integer(7931899),posList.get(0).getPosId());
    }

   // 按收藏夹条件搜索职位
    @Test
    public void testGetPosListByFavorite() {
        List<PosBriefSimpleBo> posList = posRecommendSrv.getPosListByFavorite(DEFAULT_PID);
        //测试点：返回的posId是否正确
        Assert.assertEquals("posId",new Integer(7502125),posList.get(0).getPosId());
    }


}
