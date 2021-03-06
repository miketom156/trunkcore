package com.job5156.core.eao.com;

import com.job5156.core.common.util.MD5Builder;
import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.com.ComUser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ComUserEao extends BaseHibernateEao<ComUser, Integer> {

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ComUser find(String username, String password) {
        String hql = "FROM ComUser WHERE username = ? AND delStatus != -1";
        List<ComUser> comUserList = list(hql, new Object[]{username});
        if(CollectionUtils.isNotEmpty(comUserList)){
            for(ComUser comUser : comUserList) {
                String md5Password = MD5Builder.md5(comUser.getUsername(), password);
                if(StringUtils.equals(md5Password, comUser.getPassword())){
                    return comUser;
                }
            }
        }
        return null;
	}

	public ComUser findByIdAnComId(Integer id, Integer comId) {
		String hql = "FROM ComUser WHERE id = ? AND comId = ?";
		return unique(hql, new Object[] { id, comId });
	}

	public ComUser findUserBySubsidary(Integer comId, Integer deptId) {
		String hql = "FROM ComUser WHERE comId = ? AND deptId = ?";
		return unique(hql, new Object[] { comId, deptId });
	}

	public ComUser findMajorUserByComId(Integer comId) {
		String hql = "FROM ComUser WHERE comId = ? AND roleType = 0";
		return unique(hql, new Object[] { comId });
	}

	public int findComUsernameUsage(String username, Integer comId) {
		String hql = "SELECT COUNT(*) FROM ComUser WHERE username = ? AND comId != ?";
		return this.<Number> aggregate(hql, new Object[] { username, comId }).intValue();
	}

	public List<ComUser> findComUserList(Integer comId) {
		String hql = " FROM ComUser where comId = ? AND delStatus != -1";
		return list(hql, new Object[] { comId });
	}

	public boolean validateUserName(Integer currentId, String username) {
		String hql = "FROM ComUser WHERE id != ? and username = ? and del_status != -1";
		List<ComUser> userList = list(hql, new Object[] { currentId, username });
		if (userList == null)
			return false;
		return userList.size() > 0;
	}

}
