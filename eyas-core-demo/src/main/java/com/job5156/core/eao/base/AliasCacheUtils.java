package com.job5156.core.eao.base;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 
* @ClassName: AliasUtils 
* @Description: 别名缓存工具类，根据字段名称自动生成别名缓存并返回字段新名称
* @author xh 
* @date 2013-8-20 下午3:16:57 
*
 */
public class AliasCacheUtils {
	protected static final Logger logger = LoggerFactory.getLogger(AliasCacheUtils.class);
	
	private Map<String, PropertyAlias> aliasCache;
	private int count;
	
	public AliasCacheUtils() {
		this.aliasCache = new HashMap<String, PropertyAlias>();
		this.count = 0;
	}
	
	public Map<String, PropertyAlias> getAliasCache() {
		return aliasCache;
	}

	public void setAliasCache(Map<String, PropertyAlias> aliasCache) {
		this.aliasCache = aliasCache;
	}

	public String filterPropertyByAlias(String property) {
		//直接排除没有别名的属性
		if (property == null || property.indexOf(".") == -1) return property;
		
		String path = StringUtils.substringBeforeLast(property, ".");
		String field = StringUtils.substringAfterLast(property, ".");
		
		if(aliasCache.containsKey(path)) {
			return aliasCache.get(path).name + "." + field;
		} else {
			// 创建别名
			String aliasName = StringUtils.substringBeforeLast(property, ".") + count++;
			aliasCache.put(path, new PropertyAlias(path, aliasName));
			return aliasName + "." + field;
		}
	}
	
	public String filterFieldName(String property){
		if (property == null || property.indexOf(".") == -1) return property;
		return StringUtils.substringAfterLast(property, ".");
	}
	
	/*private String createAliasCache(String path, String aliasStr) {
		if(aliasStr.indexOf(".") == -1) {
			String aliasName = aliasStr + count++;
			aliasCache.put(path, new PropertyAlias(path, aliasName));
			return aliasName;
		} else {
			String[] aliasArray = aliasStr.split(".");
			for(String alias : aliasArray) {
				String aliasName = alias + count++;
				aliasCache.put(path, new PropertyAlias(path, aliasName));
			}
			
		}
	}*/
	
	public class PropertyAlias {
		private String path;
		private String name;
		
		public PropertyAlias(String path, String name) {
			this.path = path;
			this.name = name;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
