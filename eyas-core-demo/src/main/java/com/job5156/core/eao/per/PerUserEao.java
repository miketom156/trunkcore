package com.job5156.core.eao.per;

import com.google.common.collect.Maps;
import com.job5156.core.common.Constants;
import com.job5156.core.common.helper.UploadHelper;
import com.job5156.core.common.util.MD5Builder;
import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.core.entity.per.PerUser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 * <p>用户持久类</p>
 * Date:2015/5/20 10:50
 *
 * @author pzm
 * @version 1.0
 */
@Repository
public class PerUserEao extends BaseHibernateEao<PerUser, Integer> {
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * 根据账号密码查找用户
     * @param account 账号名
     * @param password 密码
     * @return 用户Model
     */
    public PerUser find(String account, String password) {
    	//允许绑定了手机的个人账户通过手机号码登录
        String hql = "FROM PerUser WHERE (account = ? or (mobile = ? and mobileActivation = 1)) and status = 0";
        List<PerUser> perUserList = list(hql, new Object[] {account, account});
        if(CollectionUtils.isNotEmpty(perUserList)){
            for(PerUser perUser : perUserList) {
                String md5Password = MD5Builder.md5(perUser.getAccount(), password);
                if(StringUtils.equals(md5Password, perUser.getPassword())){
                    return perUser;
                }
            }
        }
        return null;
    }

    /**
     * 查找email是否有被占用
     * @param email email
     * @param perId 除此id是否有其他id使用了email，为0则忽略此参数
     * @return 用户数量
     */
    public int findEmailUsage(String email, Integer perId) {
        String hqlAccount = "SELECT COUNT(*) FROM PerUser WHERE account = ? AND id != ? AND status!=-1";
        String hqlEmail = "SELECT COUNT(*) FROM PerUser WHERE email = ? AND id != ? AND status!=-1";
        int accountCount = this.<Number>aggregate(hqlAccount, new Object[] {email, perId}).intValue();
        int emailCount = this.<Number>aggregate(hqlEmail, new Object[] {email, perId}).intValue();
        return accountCount + emailCount;
    }

    /**
     * 查找手机号是否有被占用
     * @param mobile 手机号
     * @param perId 除此id是否有其他id使用了手机号，为0则忽略此参数
     * @return 用户数量
     */
    public int findMobileUsage(String mobile, Integer perId) {
        String hql = "SELECT COUNT(*) FROM PerUser WHERE mobile = ? AND id != ? AND mobileActivation = 1 and status != -1";
        return this.<Number>aggregate(hql, new Object[] {mobile, perId}).intValue();
    }

    /**
     * 根据账号查找用户
     * @param account 账号
     * @return 用户实体列表
     */
    public List<PerUser> findByAccount(String account) {
        String hql = "FROM PerUser WHERE account = ?";
        return list(hql, new Object[] {account});
    }

}
