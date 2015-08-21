package com.job5156.core.bo.mapping;

import com.job5156.core.common.option.OptionMap;
import org.apache.commons.lang.StringUtils;

/**
 * 封装地址对象
 * @author leo
 *
 */
public class AddressBo {
	private String prov;// 省
    private String city;// 市
    private String dist;// 区
    private String addr;// 地址

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(!OptionMap.isCentralCity(prov) ? StringUtils.defaultIfBlank(prov, "") : "");
		buf.append(StringUtils.defaultIfBlank(city, ""));
		buf.append(StringUtils.defaultIfBlank(dist, ""));
		buf.append(StringUtils.defaultIfBlank(addr, ""));
		return buf.toString();
	}
}
