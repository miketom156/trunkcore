package com.job5156.core.srv.com;

import com.job5156.core.eao.com.ComReceiveEao;
import com.job5156.webapp.model.com.ComReceive;
import com.job5156.core.srv.base.BaseSrv;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p></p>
 * Date:2015/5/8 17:35
 *
 * @author pzm
 * @version 1.0
 */

@Service
public class ComReceiveSrv extends BaseSrv<ComReceive,Integer> {
    /**
     *
     * 收到的简历类型
     * @author xh
     * @date 2014-2-20 上午10:57:52
     *
     */
    public enum ReceiveType {
        /**
         * 个人主动应聘
         */
        PER_INTERVIEW(0),
        /**
         * 后台系统推荐
         */
        SYS_RECOMMAND(1),
        /**
         * 企业邀请面试
         */
        COM_INVITATION(2),
        /**
         * 视频面试
         */
        VOID_INTERVIEW(3);

        private Integer value;

        public Integer getValue() {
            return value;
        }

        private ReceiveType(Integer value) {
            this.value = value;
        }
    }

    @Resource
    private ComReceiveEao comReceiveEao;

    @Override
    protected void initBaseHibernateEao() {
        super.baseHibernateEao = comReceiveEao;
    }
}
