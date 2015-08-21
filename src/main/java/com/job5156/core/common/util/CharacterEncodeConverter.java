package com.job5156.core.common.util;

import java.io.UnsupportedEncodingException;

/**
 * 
* 字符转换工具类
* @author xh 
* @date 2014-12-27 下午4:29:39 
*
 */
public class CharacterEncodeConverter {
	private static final String GBK = "GBK";
	private static final String UTF_8 = "UTF-8";
    
	/**
	 * 
	* utf8字符串转gbk字符串
	* @param @param content
	* @param @return
	* @param @throws UnsupportedEncodingException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
    public static String convertUTF2GBK(String content) throws UnsupportedEncodingException {
    	byte[] srcByte = content.getBytes(UTF_8);
    	StringBuffer str = new StringBuffer();
    	int len = srcByte.length;
    	int char1, char2, char3;
    	int count = 0;
    	
    	while (count < len) {
    		char1 = (int) srcByte[count] & 0xff;
    		switch (char1 >> 4) {
    			case 0:
    			case 1:
    			case 2:
    			case 3:
    			case 4:
    			case 5:
    			case 6:
    			case 7:
    				count++;
    				str.append((char) char1);
    				break;
    			case 12:
    			case 13:
    				count += 2;
    				if (count > len) {
    					break;
    				}
    				
    				char2 = (int) srcByte[count - 1];
    				if ((char2 & 0xC0) != 0x80) {
    					break;
    				}
    				str.append((char) (((char1 & 0x1F) << 6) | (char2 & 0x3F)));
    				break;
    			case 14:
    				count += 3;
    				if (count > len) {
    					throw new UnsupportedEncodingException();
    				}
    				
    				char2 = (int) srcByte[count - 2];
    				char3 = (int) srcByte[count - 1];
    				if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80)) {
    					break;
    				}
    				str.append((char) (((char1 & 0x0F) << 12) | ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0)));
    				break;
    			default:
    				break;
    		}
    	}
    	String temps = new String(str.toString().getBytes(GBK),GBK);
    	return temps;
    }
}
