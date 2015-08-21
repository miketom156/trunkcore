package com.job5156.core.common.util;

import com.google.common.hash.Hashing;
import org.apache.commons.lang.StringUtils;

public class MD5Builder {


//	private static Logger logger = Logger.getLogger(MD5Builder.class);

// 这里是使用Java messageDigest的旧方法，下面更新成用Guava的Hashing方法，更简洁及高效
//    public static String md5(String str) {
//        StringBuilder sbu = new StringBuilder();
//        try {
//            MessageDigest md5 = MessageDigest.getInstance("MD5");
//            byte[] out = md5.digest(str.getBytes());
//            for (byte b : out) {
//                int o = b & 0xFF;
//                if (o < 16) {
//                    sbu.append("0");
//                }
//                sbu.append(Integer.toHexString(o));
//            }
//        } catch (NoSuchAlgorithmException e) {
//            logger.error("MD5 generate error...");
//        }
//        return sbu.toString();
//    }

	public static String md5(String... strs) {
        String str = StringUtils.join(strs, ":");
        return Hashing.md5().hashBytes(str.getBytes()).toString();
	}

}
