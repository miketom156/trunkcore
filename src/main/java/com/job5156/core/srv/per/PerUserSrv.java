package com.job5156.core.srv.per;

import com.job5156.core.bo.redis.PerUserLoginRedisBo;
import com.job5156.core.common.Constants;
import com.job5156.core.common.helper.TempHelper;
import com.job5156.core.common.util.MD5Builder;
import com.job5156.core.eao.RedisEao;
import com.job5156.core.eao.per.PerLoginHistoryEao;
import com.job5156.core.eao.per.PerResumeEao;
import com.job5156.core.eao.per.PerUserEao;
import com.job5156.webapp.model.per.PerResume;
import com.job5156.webapp.model.per.PerUser;
import com.job5156.core.srv.base.BaseSrv;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>用户核心业务类</p>
 * Date:2015/5/8 17:35
 *
 * @author pzm
 * @version 1.0
 */

@Service
@Transactional
public class PerUserSrv extends BaseSrv<PerUser, Integer> {
    @Resource
    private PerUserEao perUserEao;
    @Resource
    private RedisEao redisEao;
    @Resource
    private TempHelper tempHelper;
    @Resource
    private PerResumeEao perResumeEao;
    @Resource
    private PerLoginHistoryEao perLoginHistoryEao;

    @Override
    protected void initBaseHibernateEao() {
        super.baseHibernateEao = perUserEao;
    }

    /**
     * 校验email除自己外是否有其他人使用
     *
     * @param email email
     * @param perId 自己的id
     * @return
     */
    public boolean checkPerEmailExist(String email, Integer perId) {
        return perUserEao.findEmailUsage(email, perId) > 0;
    }

    /**
     * 校验email是否已存在
     *
     * @param email
     * @return
     */
    public boolean checkPerEmailExist(String email) {
        return checkPerEmailExist(email, 0);
    }

    /**
     * 校验手机除自己外是否有其他人使用
     *
     * @param mobile 手机号
     * @param perId  自己的id
     * @return
     */
    public boolean checkPerMobileExist(String mobile, Integer perId) {
        return perUserEao.findMobileUsage(mobile, perId) > 0;
    }

    /**
     * 校验手机是否已存在
     *
     * @param mobile 手机号
     * @return
     */
    public boolean checkPerMobileExist(String mobile) {
        return checkPerMobileExist(mobile, 0);
    }

    /**
     * 单点登录
     *
     * @param pid 登录用户id
     * @param ip  登录ip
     * @return
     */
    public String login(Integer pid, String ip) {
        PerUser perUser = perUserEao.get(pid);
        if (perUser == null) {
            return null;
        }
        PerUserLoginRedisBo vo = new PerUserLoginRedisBo();
        vo.setId(ObjectUtils.toString(perUser.getId()));
        vo.setIp(ip);
        vo.setLoginName(StringUtils.isNotBlank(perUser.getUserName()) ? perUser.getUserName() : perUser.getAccount());

        String cookie = MD5Builder.md5(Constants.VALID_CODE_LOGIN_PER, vo.getIp(), vo.getId());

        redisEao.writeRedisVoToHash(Constants.REDIS_KEY_PER_LOGIN_INFO_PREFIX + cookie, vo);
        redisEao.setKeyExpire(Constants.REDIS_KEY_PER_LOGIN_INFO_PREFIX + cookie, Constants.REDIS_EXPIRE_TIME_PER_LOGIN);
        // redisDao.writeToString(Constants.REDIS_KEY_PER_LOGIN_CHECK_PREFIX + vo.getId(), cookie); //单点登陆, 个人用户暂不需要
        // redisDao.setKeyExpire(Constants.REDIS_KEY_PER_LOGIN_CHECK_PREFIX + vo.getId(), Constants.REDIS_EXPIRE_TIME_PER_LOGIN);

        perUser.setLoginDate(DateTime.now().toDate());
//            perUser.setFreDate(DateTime.now().toDate());
        perUser.setLoginCount(perUser.getLoginCount() != null ? perUser.getLoginCount() + 1 : 1);
        perUserEao.update(perUser);

        PerResume perResume = perUser.getDefaultPerResume();
        if (perResume != null) {
//                perResume.setFreDate(DateTime.now().toDate());

            //Fix 数据导入异常
            perResume = tempHelper.fixDuplicateInfoListId(perResume);
            if (perResume.getResumeId() != null && perResume.getResumeId() > 0) {
                PerResume perResumeEn = perResumeEao.get(perResume.getResumeId());
                if (perResumeEn != null) {
                    perResumeEn = tempHelper.fixDuplicateInfoListId(perResumeEn);
                    perResumeEao.update(perResumeEn);
                }
            }
            //Fix 数据导入异常 End

            perResumeEao.update(perResume);
        }

        perLoginHistoryEao.addLoginHistory(perUser, null, ip, Constants.PER_LOGIN_FROM_WEB);
        return cookie;
    }

    /**
     * 根据账号密码找到用户
     *
     * @param account  用户账号
     * @param password 用户密码
     * @return
     */
    public PerUser find(String account, String password) {
        return perUserEao.find(account, password);
    }

    /**
     * 根据账号找到用户
     *
     * @param account 用户账号
     * @return
     */
    public PerUser find(String account) {
        return perUserEao.findByAccount(account).get(0);
    }
}
