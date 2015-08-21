package uml.per.reg;

/**
 * <p>用户核心业务类</p>
 * Date:2015/6/4 15:54
 *
 * @author pzm
 * @version 1.0
 */
public interface PerUserSrv {
    public PerUserEao perUserEao = null;

    public boolean checkPerEmailExist(String email);

    public Integer save(PerUser model);
}
