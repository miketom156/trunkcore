package com.job5156.core.bo.form.searcher;

import com.google.common.collect.Lists;
import com.job5156.core.common.option.OptionMap;
import com.job5156.core.common.option.OptionPositionMap;
import com.job5156.core.common.util.XssFilterStringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.ui.Model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Function:搜索的key与搜索字段对应MAP
 * key的命名规则为[单词的首字母+状态] ,例如：df -> degreeFrom
 * 一个单词的则为首尾字母,多个单词的则为各首字母
 * key的长度为2位.
 * f->From t->To u->Unlimit  l->list
 *
 * @autor:leeton
 * Date: 15-6-15
 * Time: 上午8:53
 */
public class SearchKeyMap {
    public final static Map<String, String> KEY_MAP = new LinkedHashMap<>();
    public final static Map<String, String> FILTER_MAP = new LinkedHashMap<>();
    public final static String SEPARATORCHARS = "_"; //搜索参数的分隔符
    public final static int KEY_LENGTH = 2; //key的长度
    public final static String KEY_WORD = "kw"; //KEY_WORD
    public final static String EN_KEY_WORD = "ek"; //英文或拼音格式的KEY_WORD

    static {//f->From t->To u->Unlimit  l->list
        KEY_MAP.put("ae", "age");//年龄
        KEY_MAP.put("b", "");
        KEY_MAP.put("cl", "comIdList"); //企业ID列表
        KEY_MAP.put("df", "degreeFrom");//学历下限
        KEY_MAP.put("dt", "degreeTo");//学历下限
        KEY_MAP.put("du", "degreeUnlimit");//学历不限
        KEY_MAP.put("e", "");
        KEY_MAP.put("f", "");
        KEY_MAP.put("gr", "gender");//性别
        KEY_MAP.put("h", "");
        KEY_MAP.put("il", "industryList"); //行业类别
        KEY_MAP.put("j", "");
        KEY_MAP.put("kw", "keyword");
        KEY_MAP.put("kt", "keywordType"); //查询类别：全文，职位，公司
        KEY_MAP.put("ll", "locationList"); //地区代码
        KEY_MAP.put("lt", "lat"); //纬度
        KEY_MAP.put("lg", "lng"); //经度
        KEY_MAP.put("m", "");
        KEY_MAP.put("n", "");
        KEY_MAP.put("o", "");
        KEY_MAP.put("pl", "propertyList"); //工时类别：全职、兼职、实习
        KEY_MAP.put("pt", "posTypeList");  //职位分类
        KEY_MAP.put("pn", "pageNo");  //页码
        KEY_MAP.put("ps", "pageSize");  //每页显示数量
        KEY_MAP.put("pi", "prodId");  //招聘会的商品ID=招聘会ID
        KEY_MAP.put("q", ""); //
        KEY_MAP.put("r", "");//薪金包含面议
        KEY_MAP.put("sy", "salary");//薪金等级（原）
        KEY_MAP.put("sf", "salaryFrom");//薪金上限(新增)
        KEY_MAP.put("st", "salaryTo"); //薪金下限(新增)
        KEY_MAP.put("sp", "salaryUnPublic"); //薪资待遇，包含未公开
        KEY_MAP.put("sb", "sortBy"); //排序方式
        KEY_MAP.put("tl", "taoLabelList"); //淘标签
        KEY_MAP.put("ui", "updateIn");  //职位的更新日期
        KEY_MAP.put("v", "");
        KEY_MAP.put("wf", "workyearFrom");//工龄下限
        KEY_MAP.put("wt", "workyearTo"); //工龄上限
        KEY_MAP.put("wu", "workyearUnlimit");  //工龄不限
        KEY_MAP.put("x", "");
        KEY_MAP.put("y", "");
        KEY_MAP.put("z", "");
    }

    static {//f->filter
        FILTER_MAP.put("fw", "filterWorkyear");//年龄
        FILTER_MAP.put("fd", "filterDegree");   //学历
        FILTER_MAP.put("fs", "filterSalary"); //薪资
        FILTER_MAP.put("fg", "filterGender");//性别
        FILTER_MAP.put("fi", "filterIndustry");//行业
        FILTER_MAP.put("fk", "filterKey");//关键字
    }


    /**
     * 是否匹配key
     * @param keyStr
     * @return
     */
    public static Boolean isMatchKey(String keyStr) {
        for (String s : KEY_MAP.keySet()) {
            if(StringUtils.equalsIgnoreCase(s, keyStr)){
                return true;
            }
        }
        return false;
    }

    /**
     * 解析SEK KEY字串
     * @param keyStr
     * @return
     */
    public String parseReqStr(String keyStr) {
        String reqStr = "";
        if(StringUtils.isNotBlank(keyStr)) {
            String[] keyArr = StringUtils.split(keyStr, SearchKeyMap.SEPARATORCHARS);
            if (ArrayUtils.isNotEmpty(keyArr)) {
                for (String str : keyArr) {
                    if (StringUtils.length(str)>2) {
                        String k = StringUtils.left(str, 2);
                        String v = StringUtils.substring(str, 2, str.length());
                        String field = SearchKeyMap.KEY_MAP.get(k);
                        reqStr += field +"=" + v + "&";
                    }
                }
            }
        }
        return reqStr;
    }

    /**
     * 把城市或镇区，行业，职位大类开头的SEO字窜,转为SearchPosFormVo
     * @param qstr
     * @return
     */
    public static SearchPosFormBo searchSetKeyToVo(String qstr) {

        SearchPosFormBo vo = new SearchPosFormBo();
        if (StringUtils.isBlank(qstr)) {
            return vo;
        }
        String[] qArr = StringUtils.split(qstr, SearchKeyMap.SEPARATORCHARS);
        Integer cityCode = 0;
        Integer townCode = 0;
        String key1 = "";
        String key2 = "";
        //第1个位置
        if (qArr.length > 0) {
            key1 = qArr[0];
            cityCode = OptionMap.getCityCodeByValue(key1, "en");
            if (cityCode > 0) {
                qstr = StringUtils.replace(qstr, key1 + SearchKeyMap.SEPARATORCHARS, ""); //去掉城市字段

            } else { //设置行业

            }
        }
        //第2个位置
        if (qArr.length > 1) {
            key2 = qArr[1];
            townCode = OptionMap.getTownCodeByCityCodeAndTownEn(cityCode, key2);
            if (townCode > 0) {
                qstr = StringUtils.replace(qstr, key2 + SearchKeyMap.SEPARATORCHARS, ""); //去掉镇区字段
            }
        }
        //转换常规参数
        vo = keyStrToVo(qstr,vo);
        //设置城市
        if (cityCode > 0) {
            vo.setCityCode(cityCode);
            vo.setCityEn(key1);
            vo.setLocationList(Lists.newArrayList(cityCode));
        }
        //设置镇区
        if (townCode > 0) {
            vo.setLocationList(Lists.newArrayList(townCode));
            vo.setTownCode(townCode);
            vo.setTownEn(key2);
        }

        return vo;
    }


    /**
     * SEO KEY 转为 search vo
     * 注意keyMap有值的才转换
     * 注意使用的是apache BeanUtils
     * @param keyStr
     */
    public static SearchPosFormBo keyStrToVo(String keyStr, SearchPosFormBo vo) {
        //key 转换
        Map<String,Object> keyMap = seoKeyToMap(keyStr);
        if (MapUtils.isNotEmpty(keyMap)){
            vo = copyProperties(SearchPosFormBo.class, keyMap);
        }
        return vo;
    }

    /**
     * filter 字串转为 filterVo
     * @param filterVo
     * @param filterStr
     * @return
     */
    public static SearchPosFilterFormBo filterStrToVo(String filterStr, SearchPosFilterFormBo filterVo) {
        //filter 转换
        Map<String,Object> filterMap = filterStrToMap(filterStr);
        if (MapUtils.isNotEmpty(filterMap)){
            filterVo = copyProperties(SearchPosFilterFormBo.class, filterMap);
        }
        return filterVo;
    }

    /**
     * 搜索key转为MAP值对
     * @param keyStr
     * @return
     */
    public static Map<String, Object> seoKeyToMap(String keyStr) {
        Map<String,Object> keyMap = new HashMap();
        if(StringUtils.isNotBlank(keyStr)) {
            String[] keyArr = StringUtils.split(keyStr, SearchKeyMap.SEPARATORCHARS);
            if (ArrayUtils.isNotEmpty(keyArr)) {
                for (String str : keyArr) {
                    if (StringUtils.length(str)>2) {
                        String k = StringUtils.left(str, 2);
                        String v = StringUtils.substring(str, 2, str.length());
                        if (StringUtils.isNotBlank(v)) {
                            XssFilterStringUtils.delXssChar(v);
                        }
                        String field = KEY_MAP.get(k);
                        //职位关键为英文的情况
                        if (StringUtils.equalsIgnoreCase(field, "keyword") && strIsEnglish(v) && StringUtils.indexOf(v,",")==-1) {
                            v = SearchHotWordMap.HOT_WORD.get(v);
                        }
                        //职位类别为英文的情况
                        if (StringUtils.equalsIgnoreCase(field, "posTypeList") && strIsEnglish(v) && StringUtils.indexOf(v,",")==-1) {
                            Integer posTypeKey = OptionPositionMap.getKeyByValueAndType(v, OptionMap.ST);
                            v = ObjectUtils.toString(posTypeKey);
                        }
                        //行业类别为英文的情况
                        if (StringUtils.equalsIgnoreCase(field, "industryList") && strIsEnglish(v) && StringUtils.indexOf(v,",")==-1) {
                            Integer industryKey = OptionMap.getKeyByValueAndType(OptionMap.OptionType.OPT_INDUSTRY,v, OptionMap.ST);
                            v = ObjectUtils.toString(industryKey);
                        }

                        List<String> vs = Arrays.asList(StringUtils.split(v, ","));
                        if(StringUtils.contains(field,"List")) {//列表
                            List<Integer> intList = Lists.newArrayList();
                            for (String s : vs) {//数值型列表
                                if (NumberUtils.isNumber(s)) {
                                    intList.add(NumberUtils.toInt(s));
                                }
                            }
                            if (CollectionUtils.isNotEmpty(intList)) {
                                keyMap.put(field, intList);
                            }else{
                                keyMap.put(field, vs);
                            }
                        }else {//单项
                            if (StringUtils.isNotBlank(v)) {
                                if (k.contains("Unlimit") || k.contains("UnPublic")) {//Boolean 属性的key做特别处理
                                    if (StringUtils.isNotBlank(v)) {
                                        keyMap.put(field, true);
                                    }else{
                                        keyMap.put(field, field);
                                    }
                                }else{
                                    keyMap.put(field, v);
                                }
                            }
                        }
                    }
                }
            }
        }
        return  keyMap;
    }

    /**
     * filterStr转过滤VO
     * @param filterStr
     * @return
     */
    private static Map<String, Object> filterStrToMap(String filterStr) {
        Map<String,Object> keyMap = new HashMap();
        if(StringUtils.isNotBlank(filterStr)) {
            String[] keyArr = StringUtils.split(filterStr, SearchKeyMap.SEPARATORCHARS);
            if (ArrayUtils.isNotEmpty(keyArr)) {
                for (String str : keyArr) {
                    if (StringUtils.length(str) > 2) {
                        String k = StringUtils.left(str, 2);
                        String v = StringUtils.substring(str, 2, str.length());
                        if (StringUtils.isNotBlank(v)) {
                            XssFilterStringUtils.delXssChar(v);
                        }
                        String field = FILTER_MAP.get(k);
                        if (StringUtils.isNotBlank(v) && StringUtils.isNotBlank(field)) {
                            if (k.contains("Unlimit") || k.contains("UnPublic")) {//Boolean 属性的key做特别处理
                                if (StringUtils.isNotBlank(v)) {
                                    keyMap.put(field, true);
                                } else {
                                    keyMap.put(field, field);
                                }
                            } else {
                                keyMap.put(field, v);
                            }
                        }
                    }
                }
            }
        }
        return  keyMap;
    }

    /**
     *
     * @Title: copyProperties
     * @Description: 拷贝解析值到对象中
     * @param @param c
     * @param @param valueMap
     * @param @return    设定文件
     * @return T    返回类型
     * @throws
     */
    private static <T> T copyProperties(Class<T> c, Map<String, Object> valueMap) {
        T t = null;
        try {
            t = c.newInstance();
            t = copyProperties(t, valueMap);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return t;
    }

    /**
     *
     * @Title: copyProperties
     * @Description: 拷贝解析值到对象中
     * @param @param t
     * @param @param valueMap
     * @param @return    设定文件
     * @return T    返回类型
     * @throws
     */
    private static <T> T copyProperties(T t, Map<String, Object> valueMap) {
        try {
            //拷贝属性
            org.apache.commons.beanutils.BeanUtils.populate(t, valueMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     判断表示是否全为英文
     */
    private static boolean strIsEnglish(String word) {
        if (StringUtils.isBlank(word)) {
            return false;
        }
        return word.matches("^[a-zA-Z]*");
    }

    /**
     * SEO 的key传值回前端
     * @param model
     * @param vo
     * @param filterFormVo
     */
    public static void mapModel(Model model, SearchPosFormBo vo, SearchPosFilterFormBo filterFormVo) {

        String provStr = OptionMap.getProvAddr(vo.getCityCode());
        model.addAttribute("provStr",provStr);  //省
        model.addAttribute("cityStr", OptionMap.getCityAddr(vo.getCityCode()));  //城市
        model.addAttribute("cityEn", vo.getCityEn());  //城市EN
        model.addAttribute("townStr", OptionMap.getTownAddr(vo.getTownCode()));  //镇区
        model.addAttribute("townEn",vo.getTownEn());  //镇区

        Integer industryCode = CollectionUtils.isNotEmpty(vo.getIndustryList())?vo.getIndustryList().get(0):0;
        model.addAttribute("industryEn", OptionMap.getValueByType(OptionMap.OptionType.OPT_INDUSTRY,industryCode,OptionMap.ST)); //行业类别(简化字串)
        Integer posTypeCode = CollectionUtils.isNotEmpty(vo.getPosTypeList()) ? vo.getPosTypeList().get(0) : 0;
        model.addAttribute("posTypeEn", OptionMap.getValueByType(OptionMap.OptionType.OPT_POSITION,posTypeCode,OptionMap.ST));  //职位类别(简化字串)
        model.addAttribute("posType",OptionMap.getValueByType(OptionMap.OptionType.OPT_POSITION,posTypeCode,OptionMap.NAME));  //职位类别(中文)
        model.addAttribute("posTypeCode",posTypeCode);  //职位类别的值

    }

    /**
     * 执行严格Xss过滤
     * @param obj
     * @return
     */
    public static <T> T delXss(T obj) {
        if (obj != null) {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object v = field.get(obj);
                    if (v != null && StringUtils.isNotBlank(v.toString())) {
                        if (StringUtils.isNotBlank(ObjectUtils.toString(v))) {
                            if (v instanceof Number) {
                                v = XssFilterStringUtils.delXssChar(ObjectUtils.toString(v));
                                field.set(obj, NumberUtils.toInt(v + ""));
                                continue;
                            }
                            if (v instanceof String) {
                                v = XssFilterStringUtils.delXssChar(ObjectUtils.toString(v));
                                field.set(obj, v);
                                continue;
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }


    public static String queryStringFormat(String queryStr) {
        if(StringUtils.isBlank(queryStr)){
            return "";
        }
        Map<String,String> queryMap = new HashMap<>();
        String[] qArr = StringUtils.split(queryStr, "&");
        for (String q : qArr) {
            String[] kvArr = StringUtils.split(q, "=");
            if (!queryMap.containsKey(kvArr[0]) && kvArr.length ==2) {
                queryMap.put(kvArr[0], kvArr[1]);
            }

        }
        if (MapUtils.isNotEmpty(queryMap)) {
            queryStr = "";
            for (String key : queryMap.keySet()) {
                queryStr += key +"="+queryMap.get(key)+"&";
            }
            queryStr = StringUtils.removeEnd(queryStr, "&");
        }

        return queryStr;
    }
}
