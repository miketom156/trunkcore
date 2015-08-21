package com.job5156.core.biz;

import com.job5156.core.TestBase;
import com.job5156.core.bo.BizRetBo;
import com.job5156.core.bo.form.per.PerPosApplyBo;
import com.job5156.core.bo.form.per.PerPosFavoriteBo;
import com.job5156.core.bo.form.per.PerViewLogBo;
import com.job5156.core.common.Constants;
import com.job5156.core.common.pagination.Page;
import com.job5156.core.eao.per.PerInviteLogEao;
import com.job5156.core.eao.per.PerPosApplyEao;
import com.job5156.core.eao.per.PerPosFavoriteEao;
import com.job5156.core.eao.per.PerViewLogEao;
import com.job5156.webapp.model.per.job.PerInviteLog;
import com.job5156.core.bo.BizRetCode;
import com.job5156.core.bo.mapping.PerInviteLogBo;
import com.job5156.webapp.model.per.job.PerPosApply;
import com.job5156.webapp.model.per.job.PerPosFavorite;
import com.job5156.webapp.model.per.job.PerViewLog;
import com.job5156.core.srv.per.PerViewLogSrv;
import org.apache.commons.lang.math.NumberUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * Date:2015/5/22 16:25
 *
 * @author pzm
 * @version 1.0
 */
public class PerJobBizTest extends TestBase {
    @Resource(name = "perJobBiz")
    private PerJobBiz perJobBiz;
    @Resource
    private PerViewLogEao perViewLogEao;
    @Resource
    private PerInviteLogEao perInviteLogEao;
    @Resource
    private PerPosApplyEao perPosApplyEao;
    @Resource
    private PerPosFavoriteEao perPosFavoriteEao;

    @Test
    public void testFindInviteCommon() {
        Integer perId = DEFAULT_PID, posId = 7986343;
        BizRetBo<Map<String, Object>> ret = perJobBiz.findInviteCommon(perId, 1, Constants.DEFAULT_PAGE_SIZE, true);
        if (ret.getRetCode() == BizRetCode.SUCCESS) {
            Map rsMap = ret.getItem();
            //测试点：多少家招聘企业邀请我面试
            Assert.assertEquals("comNum", new Integer(5), rsMap.get("comNum"));
            //测试点：未读记录数
            Assert.assertEquals("inviteUnreadCount", new Integer(3), rsMap.get("inviteUnreadCount"));

            Page<PerInviteLog> page = (Page<PerInviteLog>) rsMap.get("page");
            List<PerInviteLog> perInviteLogslist = page.getItems();
            //测试点：记录数是否正确
            Assert.assertEquals("count", 5, perInviteLogslist.size());
            //测试点：分页参数是否正确
            Assert.assertEquals("page:index", 1, page.getIndex());
            Assert.assertEquals("page:pageCount", 1, page.getContext().getPageCount());
            Assert.assertEquals("page:pageSize", 10, page.getContext().getPageSize());
            Assert.assertEquals("page:total", 5, page.getContext().getTotal());
            Assert.assertEquals("page:isHasNext", false, page.isHasNext());
            Assert.assertEquals("page:isHasPre", false, page.isHasPre());

            List<PerInviteLogBo> voList = (List<PerInviteLogBo>) rsMap.get("list");
            boolean hasSearchThePos = false;
            for (PerInviteLogBo vo : voList) {
                if (vo.getPosId().equals(posId)) {
                    hasSearchThePos = true;
                    //测试点：返回的vo属性是否正确
                    Assert.assertEquals("mixInfo:comIndustryStr", "贸易/商务/进出口", vo.getMixInfoVo().getComIndustryStr());
                    Assert.assertEquals("mixInfo:comPropertyStr", "台资企业", vo.getMixInfoVo().getComPropertyStr());
                    Assert.assertEquals("mixInfo:degreeStr", "大专", vo.getMixInfoVo().getDegreeStr());
                    Assert.assertEquals("mixInfo:jobLocationStr", "广东东莞", vo.getMixInfoVo().getJobLocationStr());
                    Assert.assertEquals("mixInfo:posStatusStr", "正常招聘", vo.getMixInfoVo().getPosStatusStr());
                    Assert.assertEquals("mixInfo:salaryStr", "面议", vo.getMixInfoVo().getSalaryStr());
                    Assert.assertEquals("mixInfo:taoLabelStr", "", vo.getMixInfoVo().getTaoLabelStr());
                    Assert.assertEquals("bookPeriod", "2015-05-25 10:00~2015-05-26 15:00", vo.getBookPeriod());
                    Assert.assertEquals("interviewAddress", "广东东莞市桥头镇东莞市桥头镇岗头工业区普世工业园办公大楼1F", vo.getInterviewAddress());
                    Assert.assertEquals("inviteDetail", "尊敬的陈义春：<a href=\"http://www.job5156.com/corp/160795\" target=\"_blank\">东莞普世饰品有限公司</a>邀请您面试（<a href=\"http://www.job5156.com/jobs/276421799\" target=\"_blank\">珠宝设计</a>），请于2015-05-25 10:00~2015-05-26 15:00到广东东莞市桥头镇东莞市桥头镇岗头工业区普世工业园办公大楼1F，乘车路线:所在地乘任意汽车到桥头车站，站内转六路公交车到终点站下车即可，联系人：钟小姐，联系电话：0769-81176666-8113。[关注智通人才网微信服务号（job5156-com），求职面试更轻松！]", vo.getInviteDetail());
                    Assert.assertEquals("userName", "珠宝设计", vo.getUserName());
                }
            }
            //测试点：是否能查到指定的职位
            if(!hasSearchThePos) {
                Assert.fail("not found pos:" + posId);
            }
            PerInviteLogBo vo = voList.get(0);
            Assert.assertEquals("perId", perId, vo.getPerUserId());

        } else {
            //测试点：应聘是否成功
            Assert.fail("invite fail:" + ret.getRetCodeVal());
        }

        //测试点：用户id空
        ret = perJobBiz.findInviteCommon(null, 1, Constants.DEFAULT_PAGE_SIZE, true);
        Assert.assertEquals("perUserId", BizRetCode.UNLOGIN, ret.getRetCode());
    }

    @Test
    public void testFindInviteTouch() {
        //todo
    }

    @Test
    public void testFindInviteApp() {
        //todo
    }

    @Test
    public void testFindInviteWechat() {
        //todo
    }

    @Test
    public void testFindInviteWeb() {
        //todo
    }
    //访问“谁看过我的简历”页面
    @Test
    public void testViewResume(){
        Integer perId = DEFAULT_PID;
        BizRetBo<Map<String, Object>> bizRetBo = perJobBiz.viewResume(perId,1,Constants.DEFAULT_PAGE_SIZE);
        if(bizRetBo.getRetCode().equals(BizRetCode.SUCCESS)){
            Map rsMap = bizRetBo.getItem();
            Integer size = (Integer) rsMap.get("size");
            //测试点：返回记录数是否正确
            Assert.assertTrue("size:", size == 1);

            List<PerViewLogBo> list = (List<PerViewLogBo>) rsMap.get("list");
            //测试点：返回的数据是否正确
            Assert.assertEquals("comName:","广州宝力机械科技有限公司东莞分公司",list.get(0).getComName());

            Page page = (Page) rsMap.get("page");
            List<PerViewLog> logList =  page.getItems();
            String mixInfo = "{\"location\":1401,\"comName\":\"广州宝力机械科技有限公司东莞分公司\",\"property\":4,\"employeeNumber\":2,\"industry\":13}";
            //测试点：返回page对象里面的mixInfo是否正确
           Assert.assertEquals("mixInfo",mixInfo,logList.get(0).getMixInfo());
        }
    }

    // 访问“应聘过的职位”页面
    @Test
    public void testApplyForJob(){
        Integer perId = DEFAULT_PID;
        BizRetBo<Map<String, Object>> bizRetBo = perJobBiz.applyForJob(perId,1,Constants.DEFAULT_PAGE_SIZE);
        if(bizRetBo.getRetCode().equals(BizRetCode.SUCCESS)){
            Map rsMap = bizRetBo.getItem();
            Integer size = (Integer) rsMap.get("size");
            //测试点：返回记录数是否正确
            Assert.assertTrue("size:", size == 1);

            List<PerPosApplyBo> list = (List<PerPosApplyBo>) rsMap.get("list");
            //测试点：返回的数据是否正确
            Assert.assertEquals("comName:","设计",list.get(0).getMixInfoVo().getPosName());
            Page page = (Page) rsMap.get("page");
            List<PerPosApply> logList =  page.getItems();
            String mixInfo = "{\"comIndustry\":21,\"degree\":4,\"jobLocation\":\"[14010000]\",\"description\":\"1、年龄25-40岁，大专以上学历，性别不限。\\r\\n2、有国内品牌中高档合金首饰设计经验。（附作品）\\r\\n3、对国内饰品流行趋势有超强的洞察力。\\r\\n4、熟悉电脑设计操作 。\",\"comName\":\"东莞市晨辉五金有限公司\",\"taoLabel\":\"[\\\"待遇良好\\\",\\\"免费食堂\\\"]\",\"posName\":\"设计\",\"salary\":0,\"workYear\":5,\"comProperty\":5}";
            //测试点：返回page对象里面的mixInfo是否正确
            Assert.assertEquals("mixInfo",mixInfo,logList.get(0).getMixInfo());
        }
    }


 //访问“职位收藏夹”页面
    @Test
    public void testPostFavorites(){
        Integer perId = DEFAULT_PID;
        BizRetBo<Map<String, Object>> bizRetBo = perJobBiz.postFavorites(perId, 1, Constants.DEFAULT_PAGE_SIZE);
         if(bizRetBo.getRetCode().equals(BizRetCode.SUCCESS)){
             Map rsMap = bizRetBo.getItem();
             Integer size = (Integer) rsMap.get("size");
             //测试点：返回记录数是否正确
             Assert.assertTrue("size:", size == 1);

             List<PerPosFavoriteBo> list = (List<PerPosFavoriteBo>) rsMap.get("list");
             //测试点：返回的数据是否正确
             Assert.assertEquals("comName:","饰品设计",list.get(0).getMixInfoVo().getPosName());
             Page page = (Page) rsMap.get("page");
             List<PerPosFavorite> logList =  page.getItems();
             String mixInfo = "{\"comIndustry\":10,\"degree\":4,\"jobLocation\":\"[14020000,14010000,14030000]\",\"comName\":\"深圳荣兴饰品有限公司\",\"description\":\"岗位职责：岗位职责：根据最新流行趋势，设计时尚首饰、发饰等\\r\\n任职资格：有饰品设计经验，懂饰品相关最新潮流\\r\\n工作时间：不限制工作时间（按设计作品计算工资），工资面议\",\"taoLabel\":\"[]\",\"posName\":\"饰品设计\",\"salary\":0,\"workYear\":2,\"comProperty\":5}";
             //测试点：返回page对象里面的mixInfo是否正确
             Assert.assertEquals("mixInfo",mixInfo,logList.get(0).getMixInfo());
         }
    }


    //删除简历查看记录
    @Test
    public void testDelViewRecord(){
        String ids = "46815109,";
        perJobBiz.delViewRecord(ids);
        PerViewLog perViewLog= perViewLogEao.get(46815109);
        //测试点：状态是否为已假删除 -1
        Assert.assertEquals("delStatus",new Integer(-1),perViewLog.getDelStatus());
    }

    //更新简历查看记录的标记和备注内容
    @Test
    public void testUpdateViewRemark(){
        String markText = "吃苦耐劳";
        String markLabel = "1";
        String id = "46815109";
        perJobBiz.updateViewRemark(markText,markLabel,id);
        PerViewLog perViewLog = perViewLogEao.get(NumberUtils.toInt(id));
        //测试点：修改后的标记
        Assert.assertEquals("delStatus",new Integer(1),perViewLog.getMark());
    }

    //删除邀请面试记录
    @Test
    public void testDelInviteRecord(){
        String ids = "7520216,";
        perJobBiz.delInviteRecord(ids);
        PerInviteLog perInviteLog =perInviteLogEao.get(7520216);
        //测试点：状态是否为已假删除 -1
        Assert.assertEquals("delStatus",new Integer(-1),perInviteLog.getDelStatus());
    }

    //更新邀请面试记录的标记和备注内容
    @Test
    public void  testUpdateInviteRemark(){
       String markText = "吃苦耐劳";
       String markLabel = "1";
       String id = "7520216";
       perJobBiz.updateInviteRemark(markText, markLabel, id);
       PerInviteLog perInviteLog = perInviteLogEao.get(NumberUtils.toInt(id));
       //测试点：修改后的标记
       Assert.assertEquals("delStatus",new Integer(1),perInviteLog.getMark());

   }

    //删除应聘过的职位记录
    @Test
    public void testDelApplyForJob() {
        String ids = "32676225,";
        perJobBiz.delApplyForJob(ids);
        PerPosApply perPosApply =perPosApplyEao.get(32676225);
        //测试点：状态是否为已假删除 -1
        Assert.assertEquals("delStatus",new Integer(-1),perPosApply.getDelStatus());
    }

    //更新应聘过的职位记录的标记和备注内容
    @Test
    public void testUpdateApplyRemark(){
        String markText = "吃苦耐劳";
        String markLabel = "1";
        String id = "32676225";
        perJobBiz.updateApplyRemark(markText, markLabel, id);
        PerPosApply perPosApply = perPosApplyEao.get(NumberUtils.toInt(id));
        //测试点：修改后的标记
        Assert.assertEquals("delStatus",new Integer(1),perPosApply.getMark());
    }

    //删除职位收藏记录
    @Test
    public void testFavoriteDel(){
        String ids = "4755410,";
        perJobBiz.favoriteDel(ids);
        PerPosFavorite perPosFavorite =perPosFavoriteEao.get(4755410);
        //测试点：状态是否为已假删除 -1
        Assert.assertEquals("delStatus",new Integer(-1),perPosFavorite.getDelStatus());
    }

    //更新职位收藏记录的标记和备注内容
    @Test
    public void testFavoriteRemark(){
        String markText = "吃苦耐劳";
        String markLabel = "1";
        String id = "4755410";
        perJobBiz.favoriteRemark(markText, markLabel, id);
        PerPosFavorite perPosFavorite = perPosFavoriteEao.get(NumberUtils.toInt(id));
        //测试点：修改后的标记
        Assert.assertEquals("delStatus",new Integer(1),perPosFavorite.getMark());
    }





}
