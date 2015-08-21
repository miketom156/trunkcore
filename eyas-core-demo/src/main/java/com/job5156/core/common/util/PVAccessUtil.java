package com.job5156.core.common.util;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by pzm on 2014/11/1.
 */
public class PVAccessUtil {
    private static final String compareDate = "2010-10-1";      //参照日期

    /**
     * 根据访问时间返回对应的索引*
     */
    public static long getPVAccessTableIndex(Date credate) {
        long DAY = 24L * 60L * 60L * 1000L;
        long dayDiff = (credate.getTime() - new DateTime(compareDate).getMillis()) / DAY;
        return dayDiff % 20 + 1;
    }

    /**
     * 根据访问时间返回对应的表名*
     */
    public static String getPVAccessTableName(Date credate) {
        return "PV_Access_Bak_" + getPVAccessTableIndex(credate);
    }
}
