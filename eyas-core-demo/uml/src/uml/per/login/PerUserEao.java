package uml.per.login;

import uml.per.reg.PerUser;

import java.util.List;

/**
 * <p></p>
 * Date:2015/6/9 8:25
 *
 * @author hjs
 * @version 1.0
 */
public interface PerUserEao {

    /**
     * 根据账号密码查找用户
     * @param account 账号名
     * @param password 密码
     * @return 用户Model
     */
    public PerUser find(String account, String password);

    /**
     * 根据账号查找用户
     * @param account 账号
     * @return 用户实体列表
     */
    public List<PerUser> findByAccount(String account);

    /**
     * 修改个人用户信息
     * @param model
     * @return
     */
    public Integer update(PerUser model);

    /**
     * 根据id获取个人信息
     * @param pid
     * @return
     */
    public PerUser get(Integer pid);


}
