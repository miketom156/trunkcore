package com.job5156.core.common.util;

import com.job5156.core.common.Constants;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {

    public static boolean checkEmailValid(String strEmail){
        if(StringUtils.isNotBlank(strEmail)) {
            String checkemail = Constants.PATTERN_EMAIL;
            Pattern pattern = Pattern.compile(checkemail);
            Matcher matcher = pattern.matcher(strEmail);
            return matcher.matches();
        } else {
            return false;
        }
    }

    public static boolean checkMobileValid(String strPhoneNum){
        if(StringUtils.isNotBlank(strPhoneNum)) {
            String checkphone = Constants.PATTERN_MOBILE;
            Pattern pattern = Pattern.compile(checkphone);
            Matcher matcher = pattern.matcher(strPhoneNum);
            return matcher.matches();
        } else {
            return false;
        }
    }

    /**
     * 判断当前时间是否为工作时间
     * @return
     */
    public static boolean checkIsWorkingTime() {
        return DateTime.now().isAfter(DateTime.now().withHourOfDay(8).withMinuteOfHour(0)) //在8:00以后
                && DateTime.now().isBefore(DateTime.now().withHourOfDay(17).withMinuteOfHour(30)); //在17:30以前
    }
}
