package uml.per.reg;

/**
 * <p></p>
 * Date:2015/6/5 11:07
 *
 * @author pzm
 * @version 1.0
 */
public interface PVPersonRegisterSrv {
    public PVPersonRegisterEao pvPersonRegisterEao = null;

    public void save(PVPersonRegister personRegister);
}
