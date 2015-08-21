package com.job5156.core.common.helper;

import com.google.common.collect.Maps;
import com.job5156.core.common.Constants;
import com.job5156.core.common.option.OptionMap;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.Map;

@Component
public class DomainHelper {

    @Value("${domain.main}")
    private String main;
    @Value("${domain.system}")
    private String system;
    @Value("${domain.search}")
    private String search;
    @Value("${domain.person}")
    private String person;
    @Value("${domain.company}")
    private String company;
    @Value("${domain.pub}")
    private String pub;
    @Value("${domain.img}")
    private String img;
    @Value("${domain.touch}")
    private String touch;

    @Value("${domain.sjzt}")
    private String sjzt;
    @Value("${domain.sjzt.img}")
    private String sjztImg;
    @Value("${domain.jzjob}")
    private String jzjob;
    @Value("${domain.jsrc}")
    private String jsrc;

    @Value("${domain.dongguan}")
    private String dongguan;
    @Value("${domain.shenzhen}")
    private String shenzhen;
    @Value("${domain.guangzhou}")
    private String guangzhou;
    @Value("${domain.zhongshan}")
    private String zhongshan;
    @Value("${domain.huizhou}")
    private String huizhou;
    @Value("${domain.jiangmen}")
    private String jiangmen;
    @Value("${domain.foshan}")
    private String foshan;
    @Value("${domain.changsha}")
    private String changsha;
    @Value("${domain.chongqing}")
    private String chongqing;
    @Value("${domain.nanchang}")
    private String nanchang;
    @Value("${domain.ningbo}")
    private String ningbo;
    @Value("${domain.wuhan}")
    private String wuhan;
    @Value("${domain.xian}")
    private String xian;
    @Value("${domain.chenzhou}")
    private String chenzhou;
    @Value("${domain.nanjing}")
    private String nanjing;
    @Value("${domain.hengyang}")
    private String hengyang;
    @Value("${domain.zhuhai}")
    private String zhuhai;
    @Value("${domain.xiangtan}")
    private String xiangtan;
    @Value("${domain.jingzhou}")
    private String jingzhou;
    @Value("${domain.qingyuan}")
    private String qingyuan;
    @Value("${domain.hefei}")
    private String hefei;
    @Value("${domain.shunde}")
    private String shunde;
    @Value("${domain.zhaoqing}")
    private String zhaoqing;
    @Value("${domain.xianning}")
    private String xianning;
    @Value("${domain.shaoyang}")
    private String shaoyang;
    @Value("${domain.yongzhou}")
    private String yongzhou;
    @Value("${domain.xiansite}")
    private String xiansite;

    public String getMain() {
        return main;
    }

    public String getSystem() {
        return system;
    }

    public String getSearch() {
        return search;
    }

    public String getPerson() {
        return person;
    }

    public String getCompany() {
        return company;
    }

    public String getPub() {
        return pub;
    }

    public String getImg() {
        return img;
    }

    public String getSjzt() {
        return sjzt;
    }

    public String getSjztImg() {
        return sjztImg;
    }

    public String getJzjob() {
        return jzjob;
    }

    public String getJsrc() {
        return jsrc;
    }

    public String getDongguan() {
        return dongguan;
    }

    public String getShenzhen() {
        return shenzhen;
    }

    public String getGuangzhou() {
        return guangzhou;
    }

    public String getZhongshan() {
        return zhongshan;
    }

    public String getHuizhou() {
        return huizhou;
    }

    public String getJiangmen() {
        return jiangmen;
    }

    public String getFoshan() {
        return foshan;
    }

    public String getChangsha() {
        return changsha;
    }

    public String getChongqing() {
        return chongqing;
    }

    public String getNanchang() {
        return nanchang;
    }

    public String getNingbo() {
        return ningbo;
    }

    public String getWuhan() {
        return wuhan;
    }

    public String getXian() {
        return xian;
    }

    public String getChenzhou() {
        return chenzhou;
    }

    public String getNanjing() {
        return nanjing;
    }

    public String getHengyang() {
        return hengyang;
    }

    public String getZhuhai() {
        return zhuhai;
    }

    public String getXiangtan() {
        return xiangtan;
    }

    public String getJingzhou() {
        return jingzhou;
    }

    public String getQingyuan() {
        return qingyuan;
    }

    public String getHefei() {
        return hefei;
    }

    public String getShunde() {
        return shunde;
    }
    
    public String getZhaoqing() {
		return zhaoqing;
	}

    public String getXianning() {
        return xianning;
    }

    public String getShaoyang() {
        return shaoyang;
    }

    public String getTouch() {
        return touch;
    }

    public String getYongzhou() {
        return yongzhou;
    }

    public String getXiansite() {
        return xiansite;
    }

    /**
     * 
    * 域名对应的区域编码
    * @author xh 
    * @date 2014-6-5 上午9:34:23 
    *
     */
    public enum DomainLocations {
    	DONGGUAN(14010000, "dg"),
    	SHENZHEN(14020000, "sz"),
    	GUANGZHOU(14030000, "gz"),
    	ZHONGSHAN(14040000, "zs"),
    	HUIZHOU(14070000, "hz"),
    	JIANGMEN(14080000, "jm"),
    	FOSHAN(14090000, "fs"),
    	CHANGSHA(28010000, "cs"),
    	CHONGQING(13000000, "cq"),
    	NANCHANG(29010000, "nc"),
    	NINGBO(15020000, "nb"),
    	WUHAN(27010000, "wh"),
    	XIAN(23010000, "sjztjob"),
    	XIANSITE(23010000, "xa"),
    	CHENZHOU(28070000, "cz"),
    	NANJING(16010000, "nj"),
    	HENGYANG(28060000, "hy"),
    	ZHUHAI(14050000, "zh"),
    	XIANGTAN(28020000, "xt"),
    	JINGZHOU(27040000, "jz"),
    	QINGYUAN(14110000, "qy"),
    	HEFEI(24010000, "hf"),
    	ZHAOQING(14130000, "zq"),
    	XIANNING(27100000,  "xn"),
        SHAOYANG(28120000,  "sy"),
        YONGZHOU(28130000,  "yz"),
    	MAIN(0, "main");
    	
    	private Integer location;
    	private String prefix;
    	
    	public Integer getLocation() {
			return location;
		}

		public String getPrefix() {
			return prefix;
		}

		private DomainLocations(Integer location, String prefix) {
    		this.location = location;
    		this.prefix = prefix;
    	}
		
		public static DomainLocations getDomainByLocation(Integer location) {
			for(DomainLocations domain : DomainLocations.values()) {
				if(OptionMap.getCityCodeBits(domain.getLocation()).equals(OptionMap.getCityCodeBits(location)))
					return domain;
			}
			return MAIN;
		}
    }

    public String byName(String name){
        BeanWrapper wrapper = new BeanWrapperImpl(this);
        for(PropertyDescriptor propertyDescriptor : wrapper.getPropertyDescriptors()) {
            if(StringUtils.equals(propertyDescriptor.getName(), name)) {
                return ObjectUtils.toString(wrapper.getPropertyValue(propertyDescriptor.getName()),"");
            }
        }
        return "";
    }


    public Map<String, String> subSiteUrlMap() {
        Map<String, String> map = Maps.newHashMap();
        BeanWrapper wrapper = new BeanWrapperImpl(this);
        for(PropertyDescriptor propertyDescriptor : wrapper.getPropertyDescriptors()) {
            String keyStr = propertyDescriptor.getName();
            if(!StringUtils.equalsIgnoreCase(keyStr, "class")
                    && !StringUtils.equalsIgnoreCase(keyStr, "main")
                    && !StringUtils.equalsIgnoreCase(keyStr, "system")
                    && !StringUtils.equalsIgnoreCase(keyStr, "search")
                    && !StringUtils.equalsIgnoreCase(keyStr, "person")
                    && !StringUtils.equalsIgnoreCase(keyStr, "company")
                    && !StringUtils.equalsIgnoreCase(keyStr, "pub")
                    && !StringUtils.equalsIgnoreCase(keyStr, "img")) {
                String valueStr = ObjectUtils.toString(wrapper.getPropertyValue(keyStr),"");
                if(StringUtils.isBlank(valueStr)){
                    valueStr = "/chitone/" + keyStr;
                }
                map.put(keyStr, valueStr);
            }
        }
        return map;
    }

    public String[] findMatchWebsiteSetting(String url, String serverName) {
        if(StringUtils.equalsIgnoreCase(StringUtils.left(serverName,2),"m.")){//过滤掉m域名，因为和jm.江门域名冲突
            return null;
        }
        if(StringUtils.startsWith(serverName,"s.")) { //过滤掉s域名，因为和长沙域名冲突
            return null;
        } else {
            Map<String, String> subSiteUrlMap = subSiteUrlMap();
            for(String[] website : Constants.CHITONE_WEBSITE_LIST) {
                if(subSiteUrlMap.get(website[0]).contains(serverName)
                        || url.contains("/chitone/"+website[0]) || url.contains("/chitoneV2/"+website[0])){
                    return website;
                }
            }
            return null;
        }
    }

    public String[] findMatchWebsiteSetting(String area) {
        for(String[] website : Constants.CHITONE_WEBSITE_LIST) {
            if(area.contains(website[1])){
                return website;
            }
        }
        return null;
    }
    public String[] findMatchWebsiteSettingByPinyin(String area) {
        for(String[] website : Constants.CHITONE_WEBSITE_LIST) {
            if(area.contains(website[0])){
                return website;
            }
        }
        return null;
    }

    public String[] fuzzyMatchWebsiteSetting(String area) {
        for(String[] website : Constants.CHITONE_WEBSITE_LIST) {
            if(website[1].indexOf(area)>=0||area.indexOf(website[1])>=0){
                return website;
            }
        }
        return null;
    }


    public String seoDomainByLocation(Integer locationCode) {
        if(locationCode != null) {
        	DomainLocations domain = DomainLocations.getDomainByLocation(locationCode);
            switch(domain) {
                case DONGGUAN:
                	return getDongguan();
                case SHENZHEN:
                	return getShenzhen();
                case GUANGZHOU:
                	return getGuangzhou();
                case ZHONGSHAN:
                	return getZhongshan();
                case HUIZHOU:
                	return getHuizhou();
                case JIANGMEN:
                	return getJiangmen();
                case FOSHAN:
                	return getFoshan();
                case CHANGSHA:
                	return getChangsha();
                case CHONGQING:
                	return getChongqing();
                case NANCHANG:
                	return getNanchang();
                case NINGBO:
                	return getNingbo();
                case WUHAN:
                	return getWuhan();
                case XIAN:
                	return getXian();
                case XIANSITE:
                	return getXiansite();
                case CHENZHOU:
                	return getChenzhou();
                case NANJING:
                	return getNanjing();
                case HENGYANG:
                	return getHengyang();
                case ZHUHAI:
                	return getZhuhai();
                case XIANGTAN:
                	return getXiangtan();
                case JINGZHOU:
                	return getJingzhou();
                case QINGYUAN:
                	return getQingyuan();
                case HEFEI:
                	return getHefei();
                case ZHAOQING:
                	return getZhaoqing();
                case XIANNING:
                	return getXianning();
                case SHAOYANG:
                	return getShaoyang();
                case YONGZHOU:
                	return getYongzhou();
                default:
                	return getMain();
            }
        } else {
        	return getMain();
        }
    }
}
