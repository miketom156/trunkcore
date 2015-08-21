package com.job5156.core.srv.per;

import org.apache.log4j.Logger;

import com.job5156.core.srv.per.iper.IPer;

/**
 * 抽象骨架类，承接接口的方法，实现公共行为等
 * 实现个人的一些共用的行为，比如应聘等
 *
 * @autor:leeton
 * Date: 2014/11/7
 * Time: 20:34
 */
abstract class PerImpl implements IPer {
    private static Logger logger = Logger.getLogger(PerImpl.class);


    /**
     * 个人应聘职位
     * @param posNo  职位编号（搜索库id)
     * @param perUserId 个人ID
     * @param resumeId  简历ID
     * @param userSecondApply  用户确认是否进行第2次应聘
     * @return
     */
    @Override
    public abstract PerEnum.ApplyErrType apply(Integer posNo, Integer perUserId,Integer resumeId,boolean userSecondApply);



}
