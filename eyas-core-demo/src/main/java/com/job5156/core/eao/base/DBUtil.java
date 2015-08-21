package com.job5156.core.eao.base;

import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;

/**
 * Created by pzm on 2014/11/6.
 */
public class DBUtil {
    public static String escapeSearchSql(String searchSql,String escapeSymbol){
        return searchSql.
                replaceAll("%",escapeSymbol+"%").
                replaceAll("_",escapeSymbol+"_").
                replaceAll("\\(",escapeSymbol+"(").
                replaceAll("\\)",escapeSymbol+")").
                replaceAll("'",escapeSymbol+"'").
                replaceAll("\"",escapeSymbol+"\"").
                replaceAll("\\?",escapeSymbol+"?");
    }

    public static void logSql(Logger logger,String sql,Object[] args){
        if (logger.isDebugEnabled()) {
            StringBuffer sbf = new StringBuffer("sql:"+sql+"\n");
            for(int i=1;i<=args.length;i++){
                sbf.append("args["+i+"]:"+ ObjectUtils.toString(args[i-1],"[null object]")+"\n");
            }
            logger.debug(sbf);
        }
    }
}
