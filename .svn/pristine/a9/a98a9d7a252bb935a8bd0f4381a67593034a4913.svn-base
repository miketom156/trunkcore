package com.job5156.core.common.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
/**
 * @author xh
 * @ClassName: XssFilterStringUtils
 * @Description: XSS过滤
 * @date 2013-11-8 上午10:21:28
 */
public class XssFilterStringUtils {
    public static String cleanXSS(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = StringUtils.trim(value);
            value = value.replaceAll("\\<.*script.*\\>", "");
            value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            //value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
            value = value.replaceAll("'", "&#39;");
            //value = value.replaceAll("\"", "&#34;");
            value = value.replaceAll("eval\\((.*)\\)", "");
            value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        }
        return value;
    }

    /**
     * @param @param  value
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: cancelXSSFilter
     * @Description: 取消XSS过滤（考虑到有些表单中有html内容，可以通过该方法恢复到过滤前状态）
     */
    public static String cancelXSSFilter(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
            value = value.replaceAll("&#40;", "\\(").replaceAll("&#41;", "\\)");
            value = value.replaceAll("&#39;", "'").replaceAll("&#34;", "\"");
        }
        return value;
    }

    /**
     * 删除特别字符，以防Xss
     * 注意：不可恢复，决定要最严格过滤时才使用
     * 主要针对输入的param为String类型时使用
     *
     * @param value
     * @return
     */
    public static String delXssChar(String value) {
        // & --> &amp;
        // < --> &lt;
        // > --> &gt;
        // " --> &quot;
        // ' --> &#x27;
        // / --> &#x2F;

        if (StringUtils.isNotBlank(value)) {
            value = value.replace("&#x2F;", "");
            value = value.replace("&#x27;", "");
            value = value.replace("&quot;", "");
            value = value.replace("&amp;", "");
            value = value.replace("&lt;", "");
            value = value.replace("&gt;", "");
            value = value.replace("&#39;", "");

            for (Pattern pattern : xssPatternList) {
                value = pattern.matcher(value).replaceAll("");
            }

            return value;
        }
        return "";
    }

    //Xss过滤规则
    private static List<Pattern> xssPatternList = new LinkedList();
    static {
        xssPatternList.add(Pattern.compile("<.*script.*>|onmouseover|prompt|document.cookie|alert", Pattern.CASE_INSENSITIVE ));
        xssPatternList.add(Pattern.compile("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", Pattern.CASE_INSENSITIVE ));
        xssPatternList.add(Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
        xssPatternList.add(Pattern.compile("[()'<>\\\\]"));
    }

    public static boolean matches(String value){
        if(value==null){
            return false;
        }
        for (Pattern pattern : xssPatternList) {
            if (pattern.matcher(value).find()) {
                //System.out.println(" match by pattern = [" + pattern + "] value= " + value);
                return true;
            }
        }
        return false;
    }

    public static boolean isFoundXss(HttpServletRequest request) {
        // 开始header校验，对header信息进行校验
     /*   if (this.checkHeader(request)) {
           return true;
        }*/
        // 开始parameter校验，对parameter信息进行校验
        if (checkParameter(request)) {
            return true;
        }
        //对url进行校验
        if (checkUri(request)) {
            return true;
        }
        return false;
    }



    /**
     * 没有违规的数据，就返回false;
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    private static boolean checkHeader(HttpServletRequest request){
        Enumeration<String> headerParams = request.getHeaderNames();
        while(headerParams.hasMoreElements()){
            String headerName = headerParams.nextElement();
            String headerValue = request.getHeader(headerName);
            if(matches(headerValue)){
                return true;
            }
        }
        return false;
    }

    /**
     * 检查URI是否非法
     * @param request
     * @return 没有就返回false
     */
    private static boolean checkUri(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (StringUtils.isNotBlank(uri)) {
            return matches(uri);
        }
        return false;
    }

    /**
     * 没有违规的数据，就返回false;
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    private static boolean checkParameter(HttpServletRequest request){
        Enumeration<String> names = request.getParameterNames();
        boolean isFound = false;
        while (names.hasMoreElements() && !isFound) {
            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            if (values.length > 0) {
                for (String value : values) {
                    if (matches(value)) {
                        isFound = true;
                        break;
                    }
                }
            }
        }
        return isFound;
    }


    /**
     * 检测CSRF攻击
     * @param request
     * @return 没有就返回false
     */
    @SuppressWarnings("unchecked")
    public static boolean isFoundCSRF(HttpServletRequest request) {

        List<String> myDomainList = Lists.newArrayList();
        myDomainList.add("localhost");
        myDomainList.add("127.0.0.1");
        myDomainList.add("192.168.");
        myDomainList.add("job5156.com");
        myDomainList.add("sjztjob.com");
        myDomainList.add("jzjob.com.cn");
        myDomainList.add("jsrc.com");

        String referer = ObjectUtils.toString(request.getHeader("Referer"), "");

        if(StringUtils.isBlank(referer)) return false;//地址栏直接输入网址，则非CSRF

        for(String myDomain : myDomainList) {
            if(referer.contains(myDomain)) {
                return false;
            }
        }

        return true;
    }


}
