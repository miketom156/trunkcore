package com.job5156.core.common.util;

import com.google.common.hash.Hashing;

/**
 * Created by Andy on 2014/12/22.
 * <p>随机码的生成工具类</p>
 */
public class RandomUtil {

    /**
     *
     * 获取统计编号
     * @param @param itemCategoryList
     * @param @param itemId
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getCountItemTn() {
        //int baseNum = 10000 + rad.nextInt(10000);
        long baseNum = System.currentTimeMillis();
        //权重
        //int weight  = 0;

		/*for(Integer itemCategory : itemCategoryList) {
			weight += itemCategory;
		}
		baseNum = baseNum * weight;*/

        return Hashing.adler32().hashBytes(String.valueOf(baseNum).getBytes()).toString();
    }

}
