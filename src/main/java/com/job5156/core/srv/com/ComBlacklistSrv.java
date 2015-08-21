package com.job5156.core.srv.com;

import com.job5156.core.eao.com.ComBlacklistEao;
import com.job5156.webapp.model.com.ComBlacklist;
import com.job5156.core.srv.base.BaseSrv;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p></p>
 * Date:2015/5/13 14:39
 *
 * @author pzm
 * @version 1.0
 */
@Service
public class ComBlacklistSrv extends BaseSrv<ComBlacklist,Integer> {
    @Resource
    private ComBlacklistEao comBlacklistEao;

    protected void initBaseHibernateEao() {
        this.baseHibernateEao = comBlacklistEao;
    }

    public boolean checkExistBlacklist(Integer comId, Integer perUserId) {
        return comBlacklistEao.checkExistBlacklist(comId, perUserId);
    }
}
