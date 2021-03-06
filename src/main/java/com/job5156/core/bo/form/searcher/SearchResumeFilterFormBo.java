package com.job5156.core.bo.form.searcher;

import com.job5156.core.common.option.OptionMap;
/**
 * <p></p>
 * Date:2015/6/15 10:20
 *
 * @author hjs
 * @version 1.0
 */
public class SearchResumeFilterFormBo {
    private Integer filterWorkyear;      //工作经验
    private Integer filterDegree;        //学历要求
    private Integer filterGender;        //性别要求
    private Integer filterSalary;        //薪资要求
    private String  filterKey;           //关键字过滤
    private Integer filterUpdateIn;		 //更新时间过滤

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

    public Integer getFilterGender() {
        return filterGender;
    }

    public void setFilterGender(Integer filterGender) {
        this.filterGender = filterGender;
    }

    public Integer getFilterSalary() {
        return filterSalary;
    }

    public void setFilterSalary(Integer filterSalary) {
        this.filterSalary = filterSalary;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }

    public Integer getFilterUpdateIn() {
        return filterUpdateIn;
    }

    public void setFilterUpdateIn(Integer filterUpdateIn) {
        this.filterUpdateIn = filterUpdateIn;
    }

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

    public String getFilterGenderStr(){
        if(getFilterGender() != null) {
            return OptionMap.getValue(OptionMap.OptionType.OPT_GENDER, getFilterGender());
        }
        return "";
    }

    public String getFilterSalaryStr(){
        if(getFilterSalary() != null) {
            return OptionMap.getValue(OptionMap.OptionType.OPT_PER_NOWSALARY, getFilterSalary());
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
                case 14:
                    name = "两周内";
                    break;
                case 30:
                    name = "一个月内";
                    break;
                case 90:
                    name = "三个月内";
                    break;
                case 180:
                    name = "半年内";
                    break;
                case 365:
                    name = "一年内";
                    break;
                default:
                    break;
            }
            return name;
        }
        return "";
    }
}
