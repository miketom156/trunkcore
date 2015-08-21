package com.job5156.core.common.util;

import org.apache.commons.lang.StringUtils;

/**
 * 邮件工具类
 * @author leo
 *
 */
public class EmailUtil {

	/**
	 * 写邮件队列时，过滤特殊字符
	 * @param content
	 * @return
	 */
	public static String filterHtmlToEntity(String content){
		if(StringUtils.isNotBlank(content)){
			content = content.replaceAll("<", "&lt;");
			content = content.replaceAll(">", "&gt;");
			content = content.replaceAll("'", "&#39;");
			content = content.replaceAll("\"", "&quot;");
		}
		return content;
	}
	
	/**
	 * 发送邮件时，还原特殊字符为HTML标签
	 * @param content
	 * @return
	 */
	public static String filterEntityToHtml(String content){
		if(StringUtils.isNotBlank(content)){
			content = content.replaceAll("&lt;", "<");
			content = content.replaceAll("&gt;", ">");
			content = content.replaceAll("&#39;", "'");
			content = content.replaceAll("&quot;", "\"");
		}
		return content;
	}
}
