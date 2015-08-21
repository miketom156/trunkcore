package com.job5156.core.common.util;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 * 主要用于网站KEY的映射
 * </p>
 * 
 * <pre>
 * 1、用于职位和简历ID和编号之间的转换<br>
 * 2、用于职位热度保存在REDIS中的KEY前缀计算<br>
 * </pre>
 */
public class KeyMaskUtil {

	/**
	 * 生成新的id主键, 高8位表示职位类别(最多7种),低32位表示职位的真实id(最大的职位id是268435455)，否则会溢出
	 * 
	 * @param posId
	 * @param posType
	 *            1:网络职位，2:招聘会，3:招聘报
	 * @return
	 */
	public static int posIdToNo(int posId, short posType) {
		long _posType = (long) posType << 28;
		long result = _posType | posId;
		return (int) result;
	}

	/**
	 * 从返回的职位luceneId中提取posid和posType
	 * 
	 * @param posNo
	 * @return
	 */
	public static int[] posNoToIdAndType(long posNo) {
		int[] idAndType = new int[2];
		idAndType[0] = (int) ((posNo << 36) >> 36); //职位ID
		idAndType[1] = (int) (posNo >> 28);         //职位类别
		return idAndType;
	}

	public static long resumeIdToNo(int resumeId) {
		return resumeId * 102;
	}

	public static int resumeNoToId(long resumeNo) {
		if (resumeNo % 102 == 0) {
			return (int) (resumeNo / 102);
		} else {
			return 0;
		}
	}

	/**
	 * 计算职位ID对应保存的REDIS区域前缀
	 * @param posId
	 * @return
	 */
	public static int calPosIdRedisRegion(int posId){
		return posId % 100;
	}

    /**
     *   此方法是对数字进行混淆，解密方法见 deNumberMask
     *   @param  number 被位移加密的数字
     *   @param  posType 混淆字符串，只能是 ascii编码内的字符，否则出错  .有效字符只取前四位
     * @return
     */
    public static long enNumberMask(int number, String posType) {
        if(posType==null||posType.length()==0) {
            posType="null";
        }else if(posType.length()==1){
            posType+=(" "+" "+" ");
        }else if(posType.length()==2){
            posType+=(" "+" ");
        } else if(posType.length()==3){
            posType+=" ";
        }
        byte[]  bts;
        try {
            bts= posType.getBytes("ascii");
        } catch (UnsupportedEncodingException e) {
            throw  new RuntimeException("method:enNumberMask UnsupportedEncodingException ");
        }
        return  ((long)bts[2]<<56)| ((long)bts[0]<<48)| ((long)bts[1]<<8)| ((long)bts[3])|((long)number<<16);
    }

    /**
     *   此方法是对混淆后的数字进行还原，加密密方法见 enNumberMask
     *   @param  maskNumber 被混淆后的数字
     * @return
     */
    public static Object[] deNumberMask(long maskNumber) {
        short s2=(short) (maskNumber>>56);
        short s0=(short) ((maskNumber<<8)>>56);
        short s1=(short) ((maskNumber<<48)>>56);
        short s3=(short) ((maskNumber<<56)>>56);
        int number=(int)((maskNumber<<16)>>32) ;
        return  new Object[]{number,String.copyValueOf(new char[]{(char)s0,(char)s1,(char)s2,(char)s3}).trim()};
    }
}
