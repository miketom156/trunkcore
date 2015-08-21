package com.job5156.core.common.util;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class MobileLocationUtil {
	
	private static final String URL = "http://www.ip138.com:8080/search.asp";
	
	private static final Log logger = LogFactory.getLog(MobileLocationUtil.class);
	
	/**
	 * 发送请求获取手机归属地信息字符串
	 * @param mobile 手机号码
	 * @return String 手机归属地信息Json字符串
	 */
	private static String getResult(String mobile){
		if(CheckUtil.checkMobileValid(mobile)){
			Map<String,String> params = Maps.newHashMap();
			params.put("action", "mobile");
			params.put("mobile", mobile);
			return HttpUtils.get(URL,params,HttpUtils.LANGUAGE_GB18030);
		}
		logger.warn("获取归属地信息时，手机号码参数错误："+mobile);
		return null;
	}
	
	/**
	 * 验证与格式化Json字符串
	 * @param json 手机号码归属地Json字符串
	 * @return
	 */
	private static String formatJson(String json) {

        if (StringUtils.isBlank(json)) {
            return "";
        }
        //处理HTML中的回车及换行符
        json = json.replaceAll("(\r\n|\r\n\t|\r|\n|\t|\n\r)", "");
        //ip138页面模板一
        ParseElement provinceCityElement = new ParseElement("<TR class=tdc bgcolor=#EFF1F3><TD align=\"center\">卡号归属地</td><!-- <td width=\"138\" align=\"center\">卡号归属地</TD> --> <TD class=\"tdc2\" align=\"center\">", "</TD>");
        ParseElement areaElement = new ParseElement("<TR class=tdc bgcolor=#EFF1F3><TD align=\"center\">区 号</TD><!-- <td></td> --><TD align=\"center\" class=tdc2>", "</TD>");
        ParseElement postCodeElement = new ParseElement("<TR class=tdc bgcolor=#EFF1F3><TD align=\"center\">邮 编</TD><TD align=\"center\" class=tdc2>", "<a href");

        String provinceCity = ParseUtils.parserValueByElement(json, provinceCityElement);
        String area = ParseUtils.parserValueByElement(json, areaElement);
        String postCode = ParseUtils.parserValueByElement(json, postCodeElement);

        if (StringUtils.isBlank(provinceCity)) {
            //ip138页面模板二
            provinceCityElement = new ParseElement("<TR class=tdc bgcolor=#EFF1F3><TD width=\"138\" align=\"center\">卡号归属地</TD><td align=\"center\" class=tdc2><!-- <td></td> -->", "</TD>");
            areaElement = new ParseElement("<TR class=tdc bgcolor=#EFF1F3><TD align=\"center\">区 号</TD><TD align=\"center\" class=tdc2>", "</TD>");
            postCodeElement = new ParseElement("<TR class=tdc bgcolor=#EFF1F3><TD align=\"center\">邮 编</TD><TD align=\"center\" class=tdc2>", "<a href");

            provinceCity = ParseUtils.parserValueByElement(json, provinceCityElement);
            area = ParseUtils.parserValueByElement(json, areaElement);
            postCode = ParseUtils.parserValueByElement(json, postCodeElement);
        }

        if (StringUtils.isBlank(provinceCity)) {
            //ip138页面模板三
            provinceCityElement = new ParseElement("<TR class=tdc bgcolor=#EFF1F3><TD width=\"138\" align=\"center\" noswap>卡号归属地</TD><TD width=* align=\"center\" class=tdc2>", "</TD>");
            areaElement = new ParseElement("<TR class=tdc bgcolor=#EFF1F3><TD align=\"center\">区 号</TD><TD align=\"center\" class=tdc2>", "</TD>");
            postCodeElement = new ParseElement("<TR class=tdc bgcolor=#EFF1F3><TD align=\"center\">邮 编</TD><TD align=\"center\" class=tdc2>", "<a href");

            provinceCity = ParseUtils.parserValueByElement(json, provinceCityElement);
            area = ParseUtils.parserValueByElement(json, areaElement);
            postCode = ParseUtils.parserValueByElement(json, postCodeElement);
        }

        if (StringUtils.isNotBlank(provinceCity)) {
            String[] pc = provinceCity.split("&nbsp;");
            //处理手机号码为直辖市没有省份的情况
            if (pc.length == 1) {
            	MobileLocation location = new MobileLocation();
                location.setProvince("");
                location.setCity(pc[0].replace("&nbsp;", "").replace("市", ""));
                location.setAreaCode(area);
                location.setPostCode(postCode);
                return new Gson().toJson(location);
            } else if (pc.length == 2) {
                MobileLocation location = new MobileLocation();
                location.setProvince(pc[0]);
                location.setCity(pc[1].replace("市", ""));
                location.setAreaCode(area);
                location.setPostCode(postCode);
                return new Gson().toJson(location);
            }
        }

		/*if(new JsonValidator().validate(json)){
			Gson gson = new Gson();
			MobileLocation location = gson.fromJson(json,MobileLocation.class);
			if(location!=null && location.getProvince()!=null){
				return gson.toJson(location);
			}
		}*/

        return "";
    }
	
	/**
	 * 获取手机归属地信息Json字符串
	 * @param mobile 手机号码
	 * @return
	 */
	public static String getJsonLocation(String mobile){
		String resule = getResult(mobile);
		return formatJson(resule);
	}
	
	/**
	 * 获取手机归属地省份与城市
	 * @param json 手机归属地信息Json字符串
	 * @return
	 */
	public static String getProvinceCity(String json){
		if(new JsonValidator().validate(json)){
			MobileLocation location = new Gson().fromJson(json,MobileLocation.class);
			if(location!=null && location.getProvince()!=null){
				return location.getProvince()+" "+location.getCity();
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		String result = getJsonLocation("15992771585");
		System.out.println(result);
		System.out.println(getProvinceCity(result));
		//System.out.println(getJsonLocation(null,result));
	}
	
	/**
	 * 
	* 解析工具类
	* @author xh 
	* @date 2014-10-13 下午2:14:15 
	*
	 */
	public static class ParseUtils {
		public static String parserValueByElement(String content, ParseElement element) {
			String parserContent = content;
			String value = "";
			
			int indexOf = parserContent.indexOf(element.getBegin());
			
			if(indexOf != -1) {
				parserContent = parserContent.substring(indexOf + element.getBegin().length(), parserContent.length());
				indexOf = parserContent.indexOf(element.getEnd());
				
				if(indexOf != -1) {
					value = parserContent.substring(0, indexOf);
				}
			}
			
			return value;
		}
	}
	
	/**
	 * 
	* 解析元素类
	* @author xh 
	* @date 2014-10-13 下午2:14:24 
	*
	 */
	public static class ParseElement {
		private String begin;
		private String end;
		
		public ParseElement(String begin, String end) {
			this.begin = begin;
			this.end = end;
		}
		
		public String getBegin() {
			return begin;
		}
		public String getEnd() {
			return end;
		}
	}
	
	public static class MobileLocation{
		
		@SerializedName("Province")
		private String province;	//省份
		
		@SerializedName("City")
		private String city;		//城市
		
		@SerializedName("AreaCode")
		private String areaCode;	//区号
		
		@SerializedName("PostCode")
		private String postCode;	//邮编

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getAreaCode() {
			return areaCode;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}
		
		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}

		public String getPostCode() {
			return postCode;
		}

		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}
	}
}
