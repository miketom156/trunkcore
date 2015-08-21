package com.job5156.core.common.util;

import com.google.gson.Gson;
import com.job5156.core.bo.BaiDuAddressComponentBo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * 接口调整过，暂时用不到这个工具类
 */
@Deprecated
public class BaiDuApiUtil {

    public static Gson gson = new Gson();
    //调用api的密钥
    private static final String BAIDUAPI_AK = "9026208c3b9fc9616f9e4c8b7c58eef3";
    //百度提供的转换经纬度的api地址(逆编码)
    private static final String BAIDUAPI_URL_ADDR_COVERT = "http://api.map.baidu.com/geoconv/v1/";
    //百度提供的地址、坐标转换的api地址
    private static final String BAIDUAPI_URL_GEOCODINGAPI = "http://api.map.baidu.com/geocoder/v2/";

    private static Logger logger = Logger.getLogger(BaiDuApiUtil.class);



    public static JSONObject getLatAndLngByAddress(String address) {
        PostMethod postMethod = new PostMethod(BAIDUAPI_URL_GEOCODINGAPI);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        NameValuePair[] postData = new NameValuePair[3];
        postData[0] = new NameValuePair("ak", BAIDUAPI_AK);
        postData[1] = new NameValuePair("address", address);
        postData[2] = new NameValuePair("output", "json");
        postMethod.addParameters(postData);
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.executeMethod(postMethod);
        } catch (HttpException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        try {
            String response = postMethod.getResponseBodyAsString();
            JSONObject responseJson = new JSONObject(response);
            if (responseJson != null && responseJson.has("result")) {
                JSONObject result = responseJson.getJSONObject("result");
                if (result != null && result.has("location")) {
                    JSONObject location = result.getJSONObject("location");
                    if (location != null && location.get("lng") != null && location.get("lat") != null) {
                        return location;
                    }
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 使用百度坐标查询地址
     * @param lat
     * @param lng
     * @return
     */
    public static String  getAddInfoByBaiDuCoords(Double lat, Double lng){
        HttpClient httpClient = new HttpClient();
        DecimalFormat formatter = new DecimalFormat("#.###############");
        //根据转换后的经纬度通过百度api获得地址消息json串
        PostMethod postMethod = new PostMethod(BAIDUAPI_URL_GEOCODINGAPI);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        NameValuePair[] postData = new NameValuePair[3];
        postData[0] = new NameValuePair("ak", BAIDUAPI_AK);
        postData[1] = new NameValuePair("location", formatter.format(lat) + "," + formatter.format(lng));//纬度，经度
        postData[2] = new NameValuePair("output", "json");
        postMethod.addParameters(postData);
        try {
            httpClient.executeMethod(postMethod);
        } catch (HttpException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        try {
            return postMethod.getResponseBodyAsString();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 通过GBS经纬度从百度获取返回的地址详细信息json串
     * 先把GPS经纬度转换为百度的经纬度
     * 获取百度对应经纬度的地址消息
     *
     * @param lat GPS纬度
     * @param lng GPS经度
     * @return
     */
    public static String getAddInfoByGPSLatAndLng(Double lat, Double lng) {
        if (lat != null && lat != 0 && lng != null && lng != 0) {
            HttpClient httpClient = new HttpClient();
            DecimalFormat formatter = new DecimalFormat("#.###############");
            //GPS经纬度转换为百度经纬度
            PostMethod postMethodToBaiDuLatLng = new PostMethod(BAIDUAPI_URL_ADDR_COVERT);
            postMethodToBaiDuLatLng.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            NameValuePair[] postDataToBaiDuLatLng = new NameValuePair[2];
            postDataToBaiDuLatLng[0] = new NameValuePair("coords", formatter.format(lng) + "," + formatter.format(lat));//经度，纬度
            postDataToBaiDuLatLng[1] = new NameValuePair("ak", BAIDUAPI_AK);
            postMethodToBaiDuLatLng.addParameters(postDataToBaiDuLatLng);
            try {
                httpClient.executeMethod(postMethodToBaiDuLatLng);
            } catch (HttpException e) {
                logger.error(e.getMessage(), e);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
            try {
                JSONObject jo = new JSONObject(postMethodToBaiDuLatLng.getResponseBodyAsString());
                JSONArray tempResult = jo.getJSONArray("result");
                if (tempResult.length() > 0 &&
                        tempResult.getJSONObject(0) != null) {
                    JSONObject latAndLng = tempResult.getJSONObject(0);
                    if (latAndLng.getDouble("x") != 0 &&
                            latAndLng.getDouble("y") != 0) {
                        lng = latAndLng.getDouble("x");
                        lat = latAndLng.getDouble("y");
                        return getAddInfoByBaiDuCoords(lat,lng);
                    }
                }
            } catch (IOException e1) {
                logger.error(e1.getMessage(), e1);
            } catch (JSONException e) {
                logger.error(e.getMessage(), e);
            }

        }
        return null;
    }

    /**
     * 获取地址详细信息中的地址组件信息
     *
     * @param lat GPS纬度
     * @param lng GPS经度
     * @return
     */
    public static BaiDuAddressComponentBo getAddCompVoByGPS(Double lat, Double lng) {
        String addInfoJSON = getAddInfoByGPSLatAndLng(lat, lng);
        if (addInfoJSON != null && !StringUtils.equals("", addInfoJSON)) {
            JSONObject jo;
            try {
                jo = new JSONObject(addInfoJSON);
                BaiDuAddressComponentBo addressComponentVo = gson.fromJson(jo.getJSONObject("result").getJSONObject("addressComponent").toString(), BaiDuAddressComponentBo.class);
                if (addressComponentVo != null) {
                    return addressComponentVo;
                }
                return null;
            } catch (JSONException e) {
                logger.error(e.getMessage(), e);
//				e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 根据GPS经纬度从百度获取地址字符串
     *
     * @param lat GPS纬度
     * @param lng GPS经度
     * @return
     */
    public static String getFormattedAddrFromBdByGPS(Double lat, Double lng) {
        String addInfoJSON = getAddInfoByGPSLatAndLng(lat, lng);
        if (addInfoJSON != null && !StringUtils.equals("", addInfoJSON)) {
            JSONObject jo;
            try {
                jo = new JSONObject(addInfoJSON);
                String fomattedAdd = jo.getJSONObject("result").getString("formatted_address");
                if (fomattedAdd != null && !StringUtils.equals("", fomattedAdd)) {
                    return fomattedAdd;
                }
                return null;
            } catch (JSONException e) {
//    			logger.error(e.getMessage(), e);
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 从百度获取GPS经纬度对应的城市
     *
     * @param lat GPS纬度
     * @param lng GPS经度
     * @return
     */
    public static String getCityThroughBaiduByGPS(Double lat, Double lng) {
        BaiDuAddressComponentBo addressComponentVo = getAddCompVoByGPS(lat, lng);
        if (addressComponentVo != null && addressComponentVo.getCity() != null && !StringUtils.equals("", addressComponentVo.getCity())) {
            return addressComponentVo.getCity();
        }
        return null;
    }
}
