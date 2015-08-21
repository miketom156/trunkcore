package com.job5156.core.common.util;

import org.apache.log4j.Logger;
import org.joda.time.format.*;

import java.util.Date;

/**
 * 基于Joda的日期转换工具类
 * @author leeton
 */
public class DateTransferUtils {
    private static final Logger logger = Logger.getLogger(DateTransferUtils.class);
    private static DateTimeFormatter formatter;

    static {
        DateTimeParser[] parsers = {
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").getParser(),
                DateTimeFormat.forPattern("yyyy-MM-dd").getParser(),
                DateTimeFormat.forPattern("yyyy/MM/dd").getParser(),
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss").getParser(),
                DateTimeFormat.forPattern("yyyy-MM").getParser(),
                DateTimeFormat.forPattern("yyyy").getParser(),
                //可增加新的格式化字符串
        };
        formatter = new DateTimeFormatterBuilder().append( null, parsers ).toFormatter();
    }

    /**
     * 字串转为日期
     * @param dateStr
     * @return
     */
    public static Date strToDate(String dateStr) {

        Date date = null;
        try {
            date = formatter.parseDateTime(dateStr).toDate();
        } catch (Exception e) {
            throw new IllegalArgumentException("日期转换出错，可能是传入的日期字串格式有误: [" + dateStr +"]");
        }
        return  date;
    }

}
