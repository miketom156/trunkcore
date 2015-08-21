package com.job5156.core.common.util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * 
	* 删除Html标签 
	* @param @param htmlStr
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
    public static String delHTMLTag(String htmlStr) {
    	String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
	    String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
	    String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
	    String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符  
    	
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(htmlStr);  
        htmlStr = m_script.replaceAll(""); // 过滤script标签  
  
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
        Matcher m_style = p_style.matcher(htmlStr);  
        htmlStr = m_style.replaceAll(""); // 过滤style标签  
  
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        htmlStr = m_html.replaceAll(""); // 过滤html标签  
  
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
        Matcher m_space = p_space.matcher(htmlStr);  
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  
        return htmlStr.trim(); // 返回文本字符串  
    }
    
    /**
     * 
    * 从html中获取文本
    * @param @param htmlStr
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    public static String getTextFromHtml(String htmlStr) {
        htmlStr = delHTMLTag(htmlStr);
        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        return htmlStr;
    }
    
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    
    /**
     * 
    * 过滤xml非法字符
    * @param @param data
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    public static String checkXmlChar(String data) {
        StringBuffer appender = new StringBuffer("");
        //去除HTML、换行符、制表符、回车符
        data = getTextFromHtml(replaceBlank(data));
        if (StringUtils.isNotBlank(data)) {
            appender = new StringBuffer(data.length());
            
            for (int i = 0; i < data.length(); i++) {
                char ch = data.charAt(i);
                if ((ch == 0x9) || (ch == 0xA) || (ch == 0xD)
                        || ((ch >= 0x20) && (ch <= 0xD7FF))
                        || ((ch >= 0xE000) && (ch <= 0xFFFD))
                        || ((ch >= 0x10000) && (ch <= 0x10FFFF)))
                    appender.append(ch);
            }
        }
        
        String result = appender.toString();
        
        return result.replaceAll("]]>", "");
    }
}
