package com.job5156.core.bo.form.searcher;

import com.job5156.core.common.option.OptionMap;

/**
 * <p></p>
 * Date:2015/6/13 10:20
 *
 * @author hjs
 * @version 1.0
 */
public class SearchPosFilterFormBo {

    private Integer filterWorkyear;
    private Integer filterDegree;
    private Integer filterSalary;
    private Integer filterGender;
    private Integer filterIndustry;
    private Integer filterUpdateIn;
    private String filterKey;

    public Integer getFilterWorkyear() {
        return filterWorkyear;
    }

    public void setFilterWorkyear(Integer filterWorkyear) {
        this.filterWorkyear = filterWorkyear;
    }

    public Integer getFilterDegree() {
        return filterDegree;
    }

    public void setFilterDegree(Integer filterDegree) {
        this.filterDegree = filterDegree;
    }

    public Integer getFilterSalary() {
        return filterSalary;
    }

    public void setFilterSalary(Integer filterSalary) {
        this.filterSalary = filterSalary;
    }

    public Integer getFilterGender() {
        return filterGender;
    }

    public void setFilterGender(Integer filterGender) {
        this.filterGender = filterGender;
    }

    public Integer getFilterIndustry() {
        return filterIndustry;
    }

    public void setFilterIndustry(Integer filterIndustry) {
        this.filterIndustry = filterIndustry;
    }

    public Integer getFilterUpdateIn() {
        return filterUpdateIn;
    }

    public void setFilterUpdateIn(Integer filterUpdateIn) {
        this.filterUpdateIn = filterUpdateIn;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }

    //------ 页面编码转换方法 ------
    public String getFilterWorkyearStr(){
        if(getFilterWorkyear() != null) {
            return OptionMap.getValue(OptionMap.OptionType.OPT_PER_WORKYEAR, getFilterWorkyear());
        }
        return "";
    }

    public String getFilterDegreeStr(){
        if(getFilterDegree() != null) {
            if(getFilterDegree() == 0) {
                return "不限";
            } else {
                return OptionMap.getValue(OptionMap.OptionType.OPT_PER_DEGREE, getFilterDegree());
            }
        }
        return "";
    }

    public String getFilterSalaryStr(){
        if(getFilterSalary() != null) {
            return  String.valueOf(getFilterSalary());
//            return OptionMap.getValue(OptionMap.OptionType.OPT_PER_NOWSALARY, getFilterSalary());
        }
        return "";
    }
    public String getFilterGenderStr(){
        if(getFilterGender() != null) {
            return OptionMap.getValue(OptionMap.OptionType.OPT_GENDER, getFilterGender());
        }
        return "";
    }
    public String getFilterIndustryStr(){
        if(getFilterIndustry() != null) {
            return OptionMap.getValue(OptionMap.OptionType.OPT_INDUSTRY, getFilterIndustry());
        }
        return "";
    }
    public String getFilterUpdateInStr(){
        if(getFilterUpdateIn() != null) {
            String name = "";
            switch(getFilterUpdateIn()) {
                case 1:
                    name = "一天内";
                    break;
                case 2:
                    name = "两天内";
                    break;
                case 7:
                    name = "一周内";
                    break;
                case 15:
                    name = "两周内";
                    break;
                case 30:
                    name = "一个月内";
                    break;
                case 90:
                    name = "三个月内";
                    break;
                default:
                    break;
            }
            return name;
        }
        return "";
    }
}
