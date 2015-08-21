package com.job5156.core.common.util;

import org.apache.commons.lang.StringUtils;

public class EmailMatchUitl {

    public static String match(String email) {
        if(StringUtils.endsWith(email,"@qq.com")) {
            return "http://mail.qq.com";
        }
        if(StringUtils.endsWith(email,"@163.com")) {
            return "http://mail.163.com";
        }
        if(StringUtils.endsWith(email,"@126.com")) {
            return "http://mail.126.com";
        }
        if(StringUtils.endsWith(email,"@139.com")) {
            return "http://mail.10086.cn";
        }
        if(StringUtils.endsWith(email,"@gmail.com")) {
            return "http://mail.google.com";
        }
        if(StringUtils.endsWith(email,"@job5156.com")) {
            return "http://mailsrv.job5156.com/";
        }
        return null;
    }
}
