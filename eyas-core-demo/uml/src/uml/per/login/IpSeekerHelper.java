package uml.per.login;

/**
 * Created by Administrator on 2015-6-9.
 */
public interface IpSeekerHelper {
    /**
     * 根据IP获取国家和地区
     *
     * @param ip
     * @return
     */
    public String getCountryAndArea(String ip);
}
