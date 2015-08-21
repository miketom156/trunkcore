package uml.per.reg;

/**
 * <p></p>
 * Date:2015/6/4 15:59
 *
 * @author pzm
 * @version 1.0
 */
public interface PerUserEao {
    public int findEmailUsage(String email, Integer perId);

    public Integer save(PerUser model);
}
