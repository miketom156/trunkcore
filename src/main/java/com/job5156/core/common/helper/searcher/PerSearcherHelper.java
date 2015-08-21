package com.job5156.core.common.helper.searcher;

import com.google.common.collect.Lists;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>搜索器帮助类</p>
 * Date:2015/6/26 16:10
 *
 * @author pzm
 * @version 1.0
 */
@Component
public class PerSearcherHelper {
    /**
     * 为了兼容简历搜索器旧数据，这里对内容进行一次转化，以满足新的搜索功能
     * <pre>
     * List<Integer>locationList->List<Integer> workareaList
     * Double location->List<Integer> locationList
     * Integer hometown->List<Integer> hometownList
     * </pre>
     * @param content
     * @return
     */
    public String parsePerSearcherContent(String content){
        if(StringUtils.isNotBlank(content)){
            JSONObject jsonObject = new JSONObject(content);
            JSONArray locationArr = jsonObject.isNull("locationList") ? null : jsonObject.getJSONArray("locationList");

            if(locationArr != null && locationArr.length() > 0 && StringUtils.contains(locationArr.toString(), ".")){
                jsonObject.remove("locationList");

                List<Integer> locationList = Lists.newArrayList();
                if(locationArr != null && locationArr.length() > 0){
                    for(int i=0; i<locationArr.length(); i++){
                        if(NumberUtils.isNumber(ObjectUtils.toString(locationArr.get(i)))){
                            locationList.add(Double.valueOf(ObjectUtils.toString(locationArr.get(i))).intValue());
                        }
                    }
                }
                jsonObject.put("locationList", new JSONArray(locationList));
                return jsonObject.toString();
            }
        }
        return content;
    }
}
