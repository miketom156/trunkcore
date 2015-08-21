package com.job5156.core.srv.com;

import com.job5156.core.eao.com.talebase.TaleBaseComTestReportEao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 * Date:2015/6/12 10:00
 *
 * @author hjs
 * @version 1.0
 */
@Service
@Transactional(value="transactionManager")
public class TaleBaseComTestSrv {
    @Resource
    private TaleBaseComTestReportEao taleBaseComTestReportEao;

    /**
     * 获取用户的测评邀请信息，状态为未进行与进行中
     *
     * @param perId
     * @return
     */
    public List<Object> findByComTestReport(Integer perId) {
        return taleBaseComTestReportEao.findByList(perId);
    }


}
