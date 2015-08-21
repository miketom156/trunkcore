package com.job5156.core.srv.per.iper;

import com.job5156.core.srv.per.PerEnum;


/**
 * 注意:除了business层的抽象类，其它层禁止实现此接口。
 * 以避免扩展接口方法时，影响大多的类，造成接口“不可变更”
 *
 * 个人共用接口
 * 集合个人的行为
 * @autor:leeton
 * Date: 14-11-5
 * Time: 上午10:26
 */
public interface IPer {


    /**
     * 个人应聘职位
     * @param posNo  职位编号（搜索库id)
     * @param perUserId 个人ID
     * @param resumeId  简历ID
     * @param userSecondApply  用户确认是否进行第2次应聘
     * @return
     */
    public PerEnum.ApplyErrType apply(Integer posNo, Integer perUserId,Integer resumeId,boolean userSecondApply);




}
