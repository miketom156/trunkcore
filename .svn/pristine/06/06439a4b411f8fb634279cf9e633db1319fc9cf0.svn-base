package com.job5156.core.common.util;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.job5156.core.bo.BaiduMapBo;
import com.job5156.core.common.Constants;
import com.job5156.core.common.option.OptionCityMap;
import com.job5156.core.bo.BaiduIpBo;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Function: 百度地图组件
 *
 * @autor:leeton
 * Date: 14-12-31
 * Time: 上午10:37
 * json示例：http://api.map.baidu.com/geocoder/v2/?ak="+Constants.BAIDU_API_KEY +"&location="+lat+","+lng+"&output=json&pois=1
 * 参见：http://developer.baidu.com/map/
 */
public class BaiduMapUtils {
    private static Logger logger = Logger.getLogger(BaiduMapUtils.class);

    private static final String MAP_API_URL = "http://api.map.baidu.com/geocoder/v2/"; //百度地图
    private static final String IP_API_URL = "http://api.map.baidu.com/location/ip";   //百度IP取得地址URL接口

    /**
     * 通过经纬度取得百度地图信息
     * @param lat
     * @param lng
     * @return
     */
    public static BaiduMapBo getMapInfoByGps(Float lat, Float lng) {
        BaiduMapBo vo = new BaiduMapBo();
        if (lat!= null && lng != null && lat > 0 && lng > 0) {
            Gson gson = new Gson();
            String url = MAP_API_URL+"?ak="+ Constants.BAIDU_API_KEY +"&location="+lat+","+lng+"&output=json&pois=1";
            String retStr = getByUrl(url);
            if (StringUtils.isNotBlank(retStr)) {
                retStr = StringUtils.replace(retStr, "renderReverse&&renderReverse(", "");
                retStr = StringUtils.replace(retStr, ")", "");
                vo = gson.fromJson(retStr,BaiduMapBo.class);
            }
        }else{
            vo.setStatus(-1);
        }
        return vo;
    }

    /**
     * 从地址得到经纬度
     * @param cityCnName
     * @return
     */
    public static BaiduMapBo.Location getGeoByCityName(String cityCnName) {
        BaiduMapBo vo = new BaiduMapBo();
        BaiduMapBo.Location loc = new BaiduMapBo.Location();
        loc.setLat(0);
        loc.setLng(0);
        if (StringUtils.isNotBlank(cityCnName)) {
            Gson gson = new Gson();
            // http://api.map.baidu.com/geocoder/v2/?address=百度大厦&output=json&ak=E4805d16520de693a3fe707cdc962045&callback=showLocation
            String url = MAP_API_URL+"?address="+cityCnName+"&output=json&ak="+Constants.BAIDU_API_KEY +"&callback=showLocation";
            String retStr = getByUrl(url);
            if (StringUtils.isNotBlank(retStr)) {
                retStr = StringUtils.replace(retStr, "showLocation&&showLocation(", "");
                retStr = StringUtils.replace(retStr, ")", "");
                vo = gson.fromJson(retStr,BaiduMapBo.class);
            }
        }
        if (vo.getStatus() == 0 && vo.getResult() != null) { //调用成功
            loc = vo.getResult().getLocation();
        }
        return loc;
    }

    /**
     * 通过IP取得所在地的信息
     * @param ip
     * @return
     */
    public static BaiduIpBo getAddByIp(String ip) {

        BaiduIpBo vo = new BaiduIpBo();
        if (StringUtils.isNotBlank(ip)) {
            Gson gson = new Gson();
            String url = IP_API_URL + "?ak=" + Constants.BAIDU_API_KEY + "&ip=" + ip + "&coor=bd09ll";
            String retStr = getByUrl(url);
            vo = gson.fromJson(retStr,BaiduIpBo.class);
        }else{
            vo.setStatus(-1);
        }
        return vo;
    }



    public static String getByUrl(String url) {
        StringBuilder sb = new StringBuilder("");
        BufferedReader in = null;
        try {
            URL tirc = new URL(url);
            in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
            String res;
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ObjectUtils.toString(sb);
    }

    public static void main(String[] args) {

        Map<Integer, Map<String, String>> cityMap = OptionCityMap.OPT_MAP_CITY;
        List<String> cityList = Lists.newArrayList();
/*

        for (Integer i : OptionCityMap.OPT_MAP_CITY.keySet()) {
           String cityName = OptionMap.getCityAddr(i);
            cityList.add(cityName);
        }

        for (String city : cityList) {
            if (!StringUtils.contains("其他", city)) {
                BaiduMapVo.Location loc = new BaiduMapVo.Location();
                loc = getGeoByCityName(city);
                System.out.println(city + "," + loc.getLat() + "," + loc.getLng());
            }
        }
*/
        BaiduMapBo.Location loc = new BaiduMapBo.Location();
        loc = getGeoByCityName("台湾");
        System.out.println("loc = [" + loc.getLat() +"," +loc.getLng() + "]");

        BaiduMapBo vo = new BaiduMapBo();
        vo = getMapInfoByGps(loc.getLat(), loc.getLng());
        System.out.println("address = [" + vo.getResult().getFormatted_address() + "]");
    }


}
