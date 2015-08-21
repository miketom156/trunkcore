package com.job5156.core.eao.per;

import com.google.common.collect.Maps;
import com.job5156.core.common.Constants;
import com.job5156.core.common.helper.UploadHelper;
import com.job5156.core.common.util.MD5Builder;
import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.per.PerUser;
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

    @Resource
    private UploadHelper uploadHelper;

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

    public List<PerUser> getByIds(List<Integer> ids) {
        String hql = "FROM PerUser WHERE id in (:params)";
        return list(hql, new Object[] {ids});
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
    
    public PerUser findByUsernameOrEmail(String str) {
        String hqlAccount = "FROM PerUser WHERE account = ? and status != -1 order by id ASC";
        String hqlEmail = "FROM PerUser WHERE email = ? and status != -1 order by id ASC";

        PerUser perUser = this.unique(hqlAccount, str);
        if(perUser == null) {
            perUser = this.unique(hqlEmail, str);
        }
        return perUser;
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

    public List<PerUser> findByEmail(String email) {
        String hql = "FROM PerUser WHERE email = ?";
        return list(hql, new Object[] {email});
    }

    public List<PerUser> findByMobile(String mobile) {
        String hql = "FROM PerUser WHERE mobile = ? AND status != -1 ORDER BY loginDate DESC";
        return list(hql, new Object[] {mobile});
    }
    
    public int findMobileUsage(String mobile) {
        String hql = "SELECT COUNT(*) FROM PerUser WHERE mobile = ? AND mobileActivation = 1";
        return this.<Number>aggregate(hql, new Object[] {mobile}).intValue();
    }
    
    public PerUser findByEmailAndMobile(String email, String mobile) {
    	String hql = "FROM PerUser WHERE email = ? and mobile = ? and status != -1";
    	List<PerUser> perUserList = this.list(hql, new Object[] {email, mobile});
        if(CollectionUtils.isNotEmpty(perUserList)) {
        	return perUserList.get(0);
        }
        return null;
    }

    public Map<Integer, String> findPhotoInfo(List<Integer> perIds) {
        Map<Integer, String> map = Maps.newHashMap();

        if(CollectionUtils.isNotEmpty(perIds)) {
            String hql = "FROM PerUser WHERE id in (:params)";
            List<PerUser> userList = list(hql, new Object[] {perIds});

            for(PerUser user : userList) {
                map.put(
                        user.getId(),
                        uploadHelper.getUrl(Constants.UPLOAD_TYPE_PER_PHOTO, user.getFilePath(), user.getFileName())
                );
            }
        }

        return map;
    }

    /**
     * 提供数据导入用的，根据ID和limit返回相应的用户账号List
     * @param id
     * @param limit
     * @return
     */
    public List<PerUser> transferList(long id, int limit) {
        String hql = "FROM PerUser WHERE id > ? ORDER BY id ASC";
        return list(hql, 1, limit, new Object[] {id});
    }

    public List<Integer> getDefaultResumeIdListByUserIdList(List<Integer> userIdList) {
        if(CollectionUtils.isNotEmpty(userIdList)){
            String hql = "SELECT resId FROM PerUser WHERE id in (:params)";
            return list(hql,new Object[] {userIdList});
        } else {
            return null;
        }
    }
   
}
