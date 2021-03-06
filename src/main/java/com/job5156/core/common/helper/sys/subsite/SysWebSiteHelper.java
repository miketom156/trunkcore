package com.job5156.core.common.helper.sys.subsite;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.job5156.core.common.Constants;
import com.job5156.core.eao.RedisEao;
import com.job5156.webapp.model.sys.subsite.SysWebSite;
import com.job5156.core.srv.sys.subsite.SysWebSiteSrv;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import com.job5156.core.common.sys.CommonEnum.*;

/**
 * 后台分站点组件
 * 
 * @author leo
 * 
 */
@Component
public class SysWebSiteHelper {

	@Resource
	private SysWebSiteSrv sysWebSiteSrv;

	@Resource
	private RedisEao redisEao;

	/**
	 * 转化分站点权限到ID列表
	 * 
	 * @param webSiteRight
	 * @return
	 */
	public List<Integer> parseWebSiteRightToIdList(String webSiteRight) {
		List<Integer> idList = null;
		if (StringUtils.isNotBlank(webSiteRight)) {
			idList = Lists.newArrayList();
			for (String id : StringUtils.split(webSiteRight, ",")) {
				if (StringUtils.isNotBlank(id)) {
					idList.add(Integer.parseInt(id));
				}
			}
		}
		return idList;
	}

	/**
	 * 获取分站点列表<br>
	 * 先从REDIS中读取，如果没有则查询数据库并保存到REDIS
	 * 
	 * @return
	 */
	public List<SysWebSite> getWebSiteList() {
		if (redisEao.isKeyExist(Constants.REDIS_KEY_SYS_WEBSITE)) {
			return redisEao.readObjectFromList(Constants.REDIS_KEY_SYS_WEBSITE, SysWebSite.class);
		} else {
			List<SysWebSite> webSiteList = sysWebSiteSrv.queryEffectWebSiteListByTypeList(ImmutableList.of(
					WebSiteType.COMPANY.getValue(), WebSiteType.BRANCH_COMPANY.getValue(),
					WebSiteType.WEBSITE.getValue()));
			redisEao.writeObjectToList(Constants.REDIS_KEY_SYS_WEBSITE, webSiteList);
			return webSiteList;
		}
	}

	/**
	 * 转化分站点列表到MAP
	 * 
	 * @param webSiteList
	 * @return
	 */
	public Map<Integer, SysWebSite> parseWebSiteListToMap(List<SysWebSite> webSiteList) {
		Map<Integer, SysWebSite> map = Maps.newHashMap();
		if(CollectionUtils.isNotEmpty(webSiteList)){
			for(SysWebSite webSite : webSiteList){
				map.put(webSite.getId(), webSite);
			}
		}
		return map;
	}
}
