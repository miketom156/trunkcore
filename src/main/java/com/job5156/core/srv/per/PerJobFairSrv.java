package com.job5156.core.srv.per;

import com.job5156.core.eao.per.PerReserveFairEao;
import com.job5156.webapp.model.irc.IrcReserve;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 * Date:2015/6/16 9:22
 *
 * @author hjs
 * @version 1.0
 */
@Service
@Transactional(value="transactionManagerIrc")
public class PerJobFairSrv {

    @Resource
    private PerReserveFairEao perReserveFairEao;
    /**
     * For 个人中心动态
     * @param perId
     * @return
     */
    public List<IrcReserve> findComInviteByUserId(Integer perId) {
        return perReserveFairEao.findComInviteByUserId(perId);
    }

}
