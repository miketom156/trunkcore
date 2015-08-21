package com.job5156.searcher.entity;

import java.util.List;

public class PosSearchFilter {

    private List<SearchFilterDetail> workYearFilter;
    private List<SearchFilterDetail> degreeFilter;
    private List<SearchFilterDetail> salaryFilter;
    private List<SearchFilterDetail> genderFilter;
    private List<SearchFilterDetail> industryFilter;
    private List<SearchFilterDetail> updateInFilter;

    public List<SearchFilterDetail> getWorkYearFilter() {
        return workYearFilter;
    }

    public void setWorkYearFilter(List<SearchFilterDetail> workYearFilter) {
        this.workYearFilter = workYearFilter;
    }

    public List<SearchFilterDetail> getDegreeFilter() {
        return degreeFilter;
    }

    public void setDegreeFilter(List<SearchFilterDetail> degreeFilter) {
        this.degreeFilter = degreeFilter;
    }

    public List<SearchFilterDetail> getSalaryFilter() {
        return salaryFilter;
    }

    public void setSalaryFilter(List<SearchFilterDetail> salaryFilter) {
        this.salaryFilter = salaryFilter;
    }

    public List<SearchFilterDetail> getGenderFilter() {
        return genderFilter;
    }

    public void setGenderFilter(List<SearchFilterDetail> genderFilter) {
        this.genderFilter = genderFilter;
    }

    public List<SearchFilterDetail> getIndustryFilter() {
        return industryFilter;
    }

    public void setIndustryFilter(List<SearchFilterDetail> industryFilter) {
        this.industryFilter = industryFilter;
    }

    public List<SearchFilterDetail> getUpdateInFilter() {
        return updateInFilter;
    }

    public void setUpdateInFilter(List<SearchFilterDetail> updateInFilter) {
        this.updateInFilter = updateInFilter;
    }

  

}
