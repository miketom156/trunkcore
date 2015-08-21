package com.job5156.core.srv.count;

import com.job5156.core.eao.count.PVPersonRegisterEao;
import com.job5156.core.entity.count.PvPersonRegister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>用户注册统计核心业务类</p>
 * Date:2015/5/20 10:50
 *
 * @author pzm
 * @version 1.0
 */
@Service
@Transactional(value = "transactionManagerAction")
public class PVPersonRegisterSrv {
    @Resource
    private PVPersonRegisterEao pvPersonRegisterEao;

    /**
     * 新增用户注册统计
     * @param personRegister
     */
    public void save(PvPersonRegister personRegister) {
        pvPersonRegisterEao.save(personRegister);
    }

    /**
     * 根据用户id查找注册统计
     * @param userId 用户id
     * @return
     */
    public PvPersonRegister getByUserId(Integer userId) {
        return pvPersonRegisterEao.getByUserId(userId);
    }
}
