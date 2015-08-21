/**
 * 
 */
package com.job5156.core.eao.com;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.com.ComContact;

/**
 * @author lyh
 * @Description 
 * @date 2015年8月11日
 * 
 */
@Repository
public class ComContactEao extends BaseHibernateEao<ComContact, Integer> {
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory ;
	}

	public List<ComContact> getComContactList(Integer comId) {

		String sql = " FROM ComContact WHERE comId = ? AND delStatus != -1  ORDER BY  defaultFlag DESC" ;
		return list(sql, new Object[] { comId }) ;
	}
	
	public ComContact getComContact(String comName, String email) {
		String sql = " SELECT a FROM ComContact a,ComInfo b WHERE a.comId=b.id and b.comName = ? and a.comContactBase.email like ? And a.delStatus != -1" ;
		return unique(sql, new Object[]{comName, "%\"" + email + "\"%"}) ;
	}
	
	public ComContact getDefaultComContact(Integer comId){
		
		String sql = " FROM ComContact WHERE comId = ? AND defaultFlag = 1 AND delStatus != -1" ;
		return unique(sql, new Object[]{comId});
	}

    public ComContact getComContactByEmail(String email){
		String sql = " FROM ComContact WHERE email LIKE ? AND delStatus != -1" ;
		return unique(sql, new Object[] {"%"+email+"%"});
	}

    public ComContact getComContact(Integer comId,String contactPerson,String contactPhone) {
        if(comId==null||StringUtils.isEmpty(contactPerson)||StringUtils.isEmpty(contactPhone)){
            return  null ;
        }
        String sql = " select a FROM ComContact a WHERE a.comId = ? AND a.comContactBase.contactPerson=? AND  a.comContactBase.contactPhone =?" ;
        return unique(sql,comId,contactPerson,contactPhone);
    }
    /**
     * 模糊查询企业的基本信息
     * @param userName
     * @return
     */
    public ComContact getComContact(String userName) {
		String sql = " SELECT a FROM ComContact a,ComUser b WHERE a.comId=b.comId and b.username = ? and b.roleType=? and a.delStatus != -1" ;
		return unique(sql, new Object[]{userName,0}) ;
	}
    /**
     * 模糊查询企业的基本信息
     * @param comName
     * @param email
     * @return
     */
    public List<ComContact> getComContacts(String comName, String email) {
		String sql = " SELECT a FROM ComContact a,ComInfo b WHERE a.comId=b.id and b.comName like ? and a.comContactBase.email like ? And a.delStatus != -1" ;
		return list(sql, new Object[]{"%" + comName + "%", "%" + email + "%"}) ;
	}
    /**
     * 模糊查询企业的基本信息
     * @param comName
     * @return
     */
    public List<ComContact> getComContactByComName(String comName) {
		String sql = " SELECT a FROM ComContact a,ComInfo b WHERE a.comId=b.id and b.comName like ?  And a.delStatus != -1" ;
		return list(sql, new Object[]{"%" + comName + "%"}) ;
	}
	
	

}
