/**
 * 
 */
package com.job5156.core.srv.per;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.core.eao.per.PerSearcherEao;
import com.job5156.core.eao.per.PerUserConnectEao;
import com.job5156.core.srv.base.BaseSrv;
import com.job5156.webapp.model.com.ComUser;
import com.job5156.webapp.model.per.PerSearcher;
import com.job5156.webapp.model.per.PerUser;
import com.job5156.webapp.model.per.PerUserConnect;

/**
 * @author lyh
 * @param <M>
 * @Description
 * @date 2015年8月4日
 */
@Service
@Transactional(value = "transactionManager")
public class PerUserConnectSrv extends BaseSrv<PerUserConnect, Integer> {
	@Override
	protected BaseHibernateEao<PerUserConnect, Integer> getBaseHibernateEao() {
		// TODO Auto-generated method stub
		return super.getBaseHibernateEao();
	}

	@Resource
	private PerUserConnectEao perUserConnectEao;

	@Override
	protected void initBaseHibernateEao() {
		// TODO Auto-generated method stub
		this.baseHibernateEao = perUserConnectEao;
	}

	
	
    /**
     * 根据第三方账号的信息
     * 获取已经绑定的账号
     * 若没有绑定定则返回null
     *
     * @param openId
     * @param type
     * @return
     */
    public PerUser getConnectAccount(String openId, String type) {
        List<PerUserConnect> perUserConnects = perUserConnectEao.find(openId, type);
        if (perUserConnects!= null&&perUserConnects.size()>0) {
            return perUserConnects.get(0).getPerUser();
        } else {
            return null;
        }
    }

    /**
     * 根据用户id返回绑定记录
     * @param userid
     * @param type
     * @return
     */
    public PerUserConnect getWeiXinConnectByUserId(Integer userid, String type) {
        List<PerUserConnect> perUserConnects = perUserConnectEao.findByUserIdAndType(userid, type);
        if (perUserConnects!= null&&perUserConnects.size()>0) {
            return perUserConnects.get(0);
        } else {
            return null;
        }
    }

    /**
     * 更新绑定信息，一般用于每次通过第三方登录的时候
     * 由于用户的昵称和accessToken可能会改变的
     * 为了保证存放的内容是最新的，将这两个变化的字段更新到数据库中
     *
     * @param perUserConnect
     * @param openName
     * @param accessToken
     */
    public void updateConnectInfo(PerUserConnect perUserConnect, String openName, String accessToken) {
        perUserConnect.setOpenName(openName);
        perUserConnect.setAccessToken(accessToken);
        perUserConnectEao.update(perUserConnect);
    }

    /**
     * 绑定账号
     * 将获取到的第三方平台信息绑定到现有的账号中
     *
     * @param openId
     * @param openName
     * @param type
     * @param accessToken
     * @param perId
     */
    public void connectAccount(String openId, String openName, String type, String accessToken, Integer perId) {
        PerUserConnect perUserConnect = new PerUserConnect();
        perUserConnect.setOpenId(openId);
        perUserConnect.setOpenName(openName);
        perUserConnect.setAccessToken(accessToken);
        perUserConnect.setConnectTime(DateTime.now().toDate());
        perUserConnect.setType(type);
        perUserConnect.setPerUserId(perId);

        perUserConnectEao.save(perUserConnect);
    }

    public List<PerUserConnect> listConnect(Integer perId) {
        return perUserConnectEao.listByPerUser(perId);
    }

    public void removeConnect(Integer perId, Integer id) {
        PerUserConnect perUserConnect = perUserConnectEao.get(id);
        if (perUserConnect != null && perUserConnect.getPerUserId().equals(perId)) {
            perUserConnectEao.delete(id);
        }
    }

    public void removeConnectByOpenid(String openId, Integer userId,String type) {
        List<PerUserConnect> perUserConnects = perUserConnectEao.find(openId, type);
        for (PerUserConnect perUserConnect : perUserConnects) {
            if (perUserConnect != null) {
                perUserConnectEao.delete(perUserConnect.getId());
            }
        }
       perUserConnects = perUserConnectEao.findByUserIdAndType(userId, type);
        for (PerUserConnect perUserConnect : perUserConnects) {
            if (perUserConnect != null) {
                perUserConnectEao.delete(perUserConnect.getId());
            }
        }
    }

    public void removeAndConnectNew(String openId,String openName, String type, String accessToken, Integer perId) {
        removeConnectByOpenid(openId, perId,type);
        connectAccount(openId, openName, type, accessToken, perId);
    }
    
    /**
     * 绑定微信、企业帐号
     * @param openId
     * @param openName
     * @param type
     * @param accessToken
     * @param id com_user id
     */
    public boolean bindComUser(String openId,String openName, String type, String accessToken, Integer id) {
    	if(null == getConnectComAccount(openId,type)) {
    		connectComAccount(openId, openName, type, accessToken, id);
    		return true;
    	}
    	return false;
    }
    
    /**
     * 绑定微信和企业账号
     *
     * @param openId
     * @param openName
     * @param type
     * @param accessToken
     * @param comId
     */
    public void connectComAccount(String openId, String openName, String type, String accessToken, Integer id) {
        PerUserConnect perUserConnect = new PerUserConnect();
        perUserConnect.setOpenId(openId);
        perUserConnect.setOpenName(openName);
        perUserConnect.setAccessToken(accessToken);
        perUserConnect.setConnectTime(DateTime.now().toDate());
        perUserConnect.setType(type);
        perUserConnect.setComUserId(id);

        perUserConnectEao.save(perUserConnect);
    }

    /**
     * 根据第三方账号的信息， 获取已经绑定的账号
     * 若没有绑定定则返回null
     * 企业帐号
     * @param openId
     * @param type
     * @return
     */
    public ComUser getConnectComAccount(String openId, String type) {
        List<PerUserConnect> perUserConnects = perUserConnectEao.findComUser(openId, type);
        if (perUserConnects != null && perUserConnects.size()>0) {
            return perUserConnects.get(0).getComUser();
        } else {
            return null;
        }
    }

    /**
     * 通过企业用id、帐号类型查询微信id
     * @param comUserId 企业用户id
     * @param type 帐号类型
     * @return
     */
    public List<String> findOpenIdByComUserId(Integer comUserId, String type) {
    	
        return perUserConnectEao.findOpenIdByComUserId(comUserId, type);
    }
	
	
	
	
	
}
