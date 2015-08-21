package com.job5156.core.srv.per;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.job5156.core.bo.form.per.PerPosApplyBo;
import com.job5156.core.common.Constants;
import com.job5156.core.common.helper.TempHelper;
import com.job5156.core.common.pagination.Page;
import com.job5156.core.common.pagination.PageUtil;
import com.job5156.core.eao.com.ComInfoEao;
import com.job5156.core.eao.com.ComPositionEao;
import com.job5156.core.eao.per.PerPosApplyEao;
import com.job5156.webapp.model.com.ComInfo;
import com.job5156.webapp.model.com.ComPosition;
import com.job5156.webapp.model.per.job.PerPosApply;
import com.job5156.core.srv.base.BaseSrv;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * Date:Administrator 2015-6-24
 *
 * @author hjs
 * @version 1.0
 */
@Service
@Transactional(value="transactionManagerAction")
public class PerPosApplySrv extends BaseSrv<PerPosApply, Integer> {
    @Resource
    private PerPosApplyEao perPosApplyEao;
    @Resource
    private ComInfoEao comInfoEao;
    @Resource
    private ComPositionEao comPositionEao;

    @Override
    protected void initBaseHibernateEao() {
        this.baseHibernateEao = perPosApplyEao;
    }

    /**
     * 共应聘的职位数目
     * @param userId
     * @return
     */
    public int findApplyPosNumList(Integer userId){
        return perPosApplyEao.findPosNumById(userId);
    }

    /**
     * 应聘过的职位
     * @param pn 页码
     * @param pageSize 每页的数目
     * @param command PerPosApply
     * @return Page《PerViewLog》
     */
    public Page<PerPosApply> query(int pn, int pageSize, PerPosApply command) {
        return PageUtil.getPage(perPosApplyEao.countQuery(command), pn, perPosApplyEao.query(pn, pageSize, command), pageSize);

    }
    /**
     *应聘过的职位信息添加到PerPosApplyBoList
     * @param logs
     * @return
     */
    public List<PerPosApplyBo> toApplyVo(List<PerPosApply> logs){
        List<PerPosApplyBo> perPosApplyVoList =  Lists.newArrayList();
        for (PerPosApply perPosApply : logs) {
            this.fillApplyMixInfo(perPosApply);
            if(StringUtils.isNotBlank(perPosApply.getMixInfo()) && !StringUtils.equals("{}",perPosApply.getMixInfo())) {
                perPosApplyVoList.add(new PerPosApplyBo(perPosApply));
            }
        }
        return perPosApplyVoList;
    }


    /**
     * 更新应聘职位记录的mixInfo方法
     * @param perPosApply 应聘职位记录
     * <per>
     * 1. 获取职位信息
     * 2. 获取企业信息
     * 3. 保存企业信息与职位信息到应聘职位记录中的mixInfo
     * </per>
     */
    public void fillApplyMixInfo(PerPosApply perPosApply) {
        //2014-01-01之前的企业回复状态未转换
        if(perPosApply != null && perPosApply.getReplyStatus() != null
                && perPosApply.getReplyStatus() == 4  && perPosApply.getCreTime().before(new DateTime("2014-01-01").toDate())){
            try {
                perPosApply.setReplyStatus(Constants.PER_POS_APPLY_REPLY_STATUS_READ);
                //暂时不写入数据库,先观察一段时间
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
        if(perPosApply != null && StringUtils.isBlank(perPosApply.getMixInfo())) {
            Integer posId = perPosApply.getPosId();
            Integer comId = perPosApply.getComId();
            Map<String, Object> map = Maps.newHashMap();
            try {
                ComPosition comPosition = comPositionEao.get(posId);
                if (StringUtils.isBlank(comPosition.getPosName())) {
                    perPosApply = perPosApplyEao.get(perPosApply.getId());
                    perPosApply.setMixInfo("{}");
                    perPosApplyEao.update(perPosApply);
                }else{
                    map.put("posName", comPosition.getPosName());
                    map.put("jobLocation", comPosition.getWorkLocation());
                    map.put("salary", comPosition.getSalary());
                    map.put("degree", comPosition.getReqDegree());
                    map.put("workYear", comPosition.getReqWorkYear());
                    map.put("description", comPosition.getDescription());
                    map.put("taoLabel", comPosition.getTaoLabel());

                    ComInfo comInfo = comInfoEao.get(comId);
                    map.put("comName", comInfo.getComName());
                    map.put("comIndustry", comInfo.getIndustry());
                    map.put("comProperty", comInfo.getProperty());

                    String mixInfo = new Gson().toJson(map);
                    perPosApply = perPosApplyEao.get(perPosApply.getId());
                    perPosApply.setMixInfo(mixInfo);
                    perPosApplyEao.update(perPosApply);
                }
            } catch (Exception ex) {
                perPosApply = perPosApplyEao.get(perPosApply.getId());
                perPosApply.setMixInfo("{}");
                perPosApplyEao.update(perPosApply);
            }
        }
    }
}
