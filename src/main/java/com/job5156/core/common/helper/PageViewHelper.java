package com.job5156.core.common.helper;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.job5156.core.common.Constants;
import com.job5156.core.common.helper.sys.subsite.SysWebSiteHelper;
import com.job5156.core.common.option.OptionMap;
import com.job5156.core.eao.RedisEao;
import com.job5156.webapp.model.count.PvAccess;
import com.job5156.webapp.model.count.PvAccessIp;
import com.job5156.webapp.model.sys.subsite.SysWebSite;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 流量访问组件
 * 
 * @author leo
 * 
 */
@Component
public class PageViewHelper {

	@Resource
	private RedisEao redisEao;
	
	@Resource
	private SysWebSiteHelper sysWebSiteHelper;

	// 配置默认站点名称
	private String defaultCity = "index";

	// 配置默认访问站点ID
	private Integer defaultSiteId = 33;

	private List<String> domainList = ImmutableList.of("5156.com", ".sjztjob.com", ".jzjob.com.cn", ".jsrc.com", ".rlzb.com", ".chitone.com.cn");

    // 过滤推广参数字符列表
    private List<String> filterTnStrList = ImmutableList.of("#", "/?360SS");
	/**
	 * 将访问实体写入到REDIS
	 * 
	 * @param pvAccess
	 */
	public void writePvAccessToRedis(PvAccess pvAccess) {
		redisEao.writeObjectToList(Constants.REDIS_KEY_PVCOUNT_ACCESS_LIST, pvAccess);
	}
	
	/**
	 * 将访问实体写入到REDIS
	 * 
	 * @param pvAccessIp
	 */
	public void writePvAccessIpToRedis(PvAccessIp pvAccessIp) {
		redisEao.writeObjectToList(Constants.REDIS_KEY_PVCOUNT_ACCESSIP_LIST, pvAccessIp);
	}

	/**
	 * 截取域名
	 * 
	 * @param url
	 * @return
	 */
	public String getUrlDomain(String url) {
		String domain = "";
		if (StringUtils.isBlank(url))
			return "";
		Pattern p = Pattern.compile(Constants.PATTERN_URL, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(url);
		if (matcher.find() && matcher.groupCount()>2) {
			domain = matcher.group(1);
		} else {
			String flagStr = "://";
			int b = url.indexOf(flagStr);
			if (b > 0) {
				if (url.indexOf("/", b + flagStr.length()) > 0) {
					domain = url.substring(b + flagStr.length(), url.indexOf("/", b + flagStr.length()));
				} else if (url.indexOf("?", b + flagStr.length()) > 0) {
					domain = url.substring(b + flagStr.length(), url.indexOf("?", b + flagStr.length()));
				} else {
					domain = url.substring(b + flagStr.length());
				}
			}
		}
		domain = filterDomainChar(domain);
		return domain;
	}

	/**
	 * 截取相对地址
	 * 
	 * @param url
	 *            ：URL地址
	 * @param domain
	 *            ：域名
	 * @return
	 */
	public String getUrlAddr(String url, String domain) {
		String addr = "";
		if (StringUtils.isBlank(url))
			return "";
		if (StringUtils.isBlank(domain))
			return "";
		int pos = url.indexOf(domain);
		if (pos > -1) {
			addr = url.substring(pos + domain.length());
		}
		return addr;
	}

	/**
	 * 校验是否来路
	 * 
	 * @param domain
	 * @return
	 */
	public boolean checkOuterAccess(String domain) {
		for (String innerDomain : domainList) {
			if (domain.toLowerCase().indexOf(innerDomain) != -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取URL地址中的参数MAP
	 * 
	 * @param url
	 * @return
	 */
    public Map<String, String> getUrlParameterMap(String url) {
        Map<String, String> paramMap = Maps.newHashMap();
        if (url.lastIndexOf("/#") != -1) {
            url = url.substring(0, url.lastIndexOf("/#"));
        } else if (url.lastIndexOf("#") != -1) {
            url = url.substring(0, url.lastIndexOf("#"));
        }
        int pos = url.indexOf("?");
        if (StringUtils.isNotBlank(url) && pos != -1) {
            String parameterStr = StringUtils.substringAfter(url, "?");
            String[] paramArr = StringUtils.split(parameterStr, "&");
            if (ArrayUtils.isNotEmpty(paramArr)) {
                for (String paramPairStr : paramArr) {
                    String[] data = StringUtils.split(paramPairStr, "=");
                    if (data != null && data.length == 2) {
                        paramMap.put(data[0], data[1]);
                    }
                }
            }
        }
        return paramMap;
    }

	/**
	 * 组装客户端信息到JSON
	 * 
	 * @param clientDetail
	 * @return
	 */
	public String assembleClientDetailToJson(String clientDetail) {
		Map<String, String> clientMap = Maps.newHashMap();
		if (StringUtils.isNotBlank(clientDetail)) {
			String[] clientPairArr = StringUtils.split(clientDetail, ",");
			if (ArrayUtils.isNotEmpty(clientPairArr)) {
				for (String clientPairStr : clientPairArr) {
					String[] clientArr = StringUtils.split(clientPairStr, ":");
					if (clientArr != null && clientArr.length == 2) {
						clientMap.put(clientArr[0], clientArr[1]);
					}
				}
			}
		}
		Gson gson = new Gson();
		return gson.toJson(clientMap);
	}

	/**
	 * 返回站点ID
	 */
	public Integer getSiteId(HttpServletRequest request) {
		String activeCity = ServletRequestUtils.getStringParameter(request, "ac", defaultCity);
		return this.getSiteIdByCity(activeCity);
	}

	/**
	 * 获取当前IP所在城市代码
	 * 
	 * @param addr
	 * @return
	 */
	public Integer getCityCode(String addr) {
		return OptionMap.getCityCodeByAddr(addr);
	}

	/**
	 * 根据域名获取站点ID
	 * 
	 * @param domain
	 * @return
	 */
	private Integer getSiteIdByCity(String city) {
		List<SysWebSite> webSiteList = sysWebSiteHelper.getWebSiteList();
		if(CollectionUtils.isNotEmpty(webSiteList)){
			for(SysWebSite webSite: webSiteList){
				if(StringUtils.equals(webSite.getNameEn(), city)){
					return webSite.getId();
				}
			}
		}
		return defaultSiteId;
	}

	/**
	 * 过滤域名中第一个字符':'
	 * 
	 * @param domain
	 * @return
	 */
	private String filterDomainChar(String domain) {
		while (domain.startsWith(":")) {
			domain = domain.substring(1);
		}
		return domain;
	}

    public String filterTnStr(String spreadParam){
        for(String filterStr : filterTnStrList){
            spreadParam = StringUtils.replace(spreadParam, filterStr, "");
        }
        return spreadParam;
    }
}
