package com.job5156.core.bo;



import com.job5156.searcher.entity.PosBrief;

import java.util.Date;

/**
 * Function:PosBrief 对应的简单VO
 *
 * @autor:LEETON
 * Date: 14-9-16
 * Time: 下午4:21
 */
public class PosBriefSimpleBo {

    private Integer posId; //主库的职位ID
    private Integer posNo; //搜索库的ID(职位NO)
    private String posName;
    private String salaryStr;
    private String city;    //职位的所在城市
    private Integer comId;
    private String comName;
    private String seoDomain ;
    private Integer rank;  //热度等级
    private Integer salary;  //薪资等级
    private Date freDate;
    private Date endDate;

    public PosBriefSimpleBo(PosBrief pos) {

        if(pos != null){
            setPosId(pos.getPosId());
            setPosNo(pos.getId());
            setPosName(pos.getPosName());
            setSalaryStr(pos.getSalaryStr());
            setCity(pos.getCity());
            setComName(pos.getComName());
            setComId(pos.getComId());
            setRank(pos.getRank());
            setSalary(pos.getSalary());
            setSeoDomain(pos.getSeoDomain());
            setFreDate(pos.getFreDate());
            setEndDate(pos.getComPosition().getEndDate());
        }
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public Integer getPosNo() {
        return posNo;
    }

    public void setPosNo(Integer posNo) {
        this.posNo = posNo;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getSeoDomain() {
        return seoDomain;
    }

    public void setSeoDomain(String seoDomain) {
        this.seoDomain = seoDomain;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Date getFreDate() {
        return freDate;
    }

    public void setFreDate(Date freDate) {
        this.freDate = freDate;
    }

    public String getSalaryStr() {
        return salaryStr;
    }

    public void setSalaryStr(String salaryStr) {
        this.salaryStr = salaryStr;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
