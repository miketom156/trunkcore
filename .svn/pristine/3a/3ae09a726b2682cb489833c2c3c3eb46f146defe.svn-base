package com.job5156.core.srv.per;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.job5156.core.bo.form.per.PerPosFavoriteBo;
import com.job5156.core.common.helper.TempHelper;
import com.job5156.core.common.pagination.Page;
import com.job5156.core.common.pagination.PageUtil;
import com.job5156.core.eao.com.ComInfoEao;
import com.job5156.core.eao.com.ComPositionEao;
import com.job5156.core.eao.per.PerPosFavoriteEao;
import com.job5156.webapp.model.com.ComInfo;
import com.job5156.webapp.model.com.ComPosition;
import com.job5156.webapp.model.per.job.PerPosFavorite;
import com.job5156.core.srv.base.BaseSrv;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
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
public class PerPosFavoriteSrv extends BaseSrv<PerPosFavorite, Integer> {
    @Resource
    private PerPosFavoriteEao perPosFavoriteEao;
    @Resource
    private ComInfoEao comInfoEao;
    @Resource
    private ComPositionEao comPositionEao;

    @Override
    protected void initBaseHibernateEao() {
        this.baseHibernateEao = perPosFavoriteEao;
    }

    /**
     * 职位收藏夹
     * @param pn 页码
     * @param pageSize 每页的数目
     * @param command PerPosFavorite
     * @return Page《PerViewLog》
     */
    public Page<PerPosFavorite> query(int pn, int pageSize, PerPosFavorite command) {
        return PageUtil.getPage(perPosFavoriteEao.countQuery(command), pn, perPosFavoriteEao.query(pn, pageSize, command), pageSize);
    }

    /**
     *职位收藏夹信息添加到PerPosFavoriteBoList
     * @param logs
     * @return
     */
    public List<PerPosFavoriteBo> toFavoriteVo(List<PerPosFavorite> logs){
        List<PerPosFavoriteBo> perInviteLogVoList = Lists.newArrayList();
        for (PerPosFavorite perPosFavorite : logs) {
            this.fillFavMixInfo(perPosFavorite);
            if(StringUtils.isNotBlank(perPosFavorite.getMixInfo()) && !StringUtils.equals("{}",perPosFavorite.getMixInfo())) {
                perInviteLogVoList.add(new PerPosFavoriteBo(perPosFavorite));
            }
        }
        return perInviteLogVoList;
    }

    /**
     * 更新职位收藏夹记录的mixInfo方法
     * @param perPosFavorite 职位收藏夹记录
     * <per>
     * 1. 获取职位信息
     * 2. 获取企业信息
     * 3. 保存企业信息与职位信息到职位收藏夹记录中的mixInfo
     * </per>
     */
    public void fillFavMixInfo(PerPosFavorite perPosFavorite) {
        if(perPosFavorite != null && StringUtils.isBlank(perPosFavorite.getMixInfo())) {
            Integer posId = perPosFavorite.getPosId();
            Integer comId = perPosFavorite.getComId();
            Map<String, Object> map = Maps.newHashMap();
            try {
                ComPosition comPosition = comPositionEao.get(posId);
                if (StringUtils.isBlank(comPosition.getPosName())) {
                    perPosFavorite = perPosFavoriteEao.get(perPosFavorite.getId());
                    perPosFavorite.setMixInfo("{}");
                    perPosFavoriteEao.update(perPosFavorite);
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
                    perPosFavorite = perPosFavoriteEao.get(perPosFavorite.getId());
                    perPosFavorite.setMixInfo(mixInfo);
                    perPosFavoriteEao.update(perPosFavorite);
                }
            } catch (Exception ex) {
                perPosFavorite = perPosFavoriteEao.get(perPosFavorite.getId());
                perPosFavorite.setMixInfo("{}");
                perPosFavoriteEao.update(perPosFavorite);
            }
        }
    }

}
