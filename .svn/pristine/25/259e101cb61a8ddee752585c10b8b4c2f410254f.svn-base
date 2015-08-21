package com.job5156.core.common.searcher.entity;


import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.job5156.core.common.option.OptionMap;
import com.job5156.webapp.model.com.ComInfo;
import com.job5156.webapp.model.com.ComPosition;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PosBrief {

    @Id
    private Integer id;
    private Integer posId;
    private String posName;// 职位名称
    private Integer posType;
    private Integer comId;
    private String comName;// 公司名称
    private String comDesc;//公司描述
    private Integer deptId;
    private String deptName;
    private Integer prodId;
    private String prodName;
    private Date srvTime;
    private String srvExtend;
    private Integer jobLocPc1;
    private Integer jobLocPc2;
    private Integer jobLocPc3;
    private Integer workedYear;
    private Integer degreeId;
    private Integer ageUp;
    private Integer ageDown;
    private Integer gender;
    private String posDesc;// 职位描述
    private Byte needHunt;
    private Double lat; // 经纬度
    private Double lng; // 经纬度
    private Date freDate;
    private Date postDate;
    private Date updateDate;
    private Integer posFlag;
    private Integer jobCode1;
    private Integer jobCode2;
    private Integer jobCode3;
    private Integer hotDegree;	//热度（数据来源用户应聘次数）

    private String jsoninfo;
    private String seoDomain = ""; //这个字段只在读取的时候进行设置，不加入数据库
    private transient int urgent; //急聘标记，只为显示，不入库
    private transient Integer salary; //薪资，只为显示，不入库
    private transient int rank;       //热度等级 ，只为显示，不入库


    //--------------------非POJO方法-------------------//
    public List<String> getJobLocations(){
        List<String> list = Lists.newArrayList();
        if(getJobLocPc1() != null && getJobLocPc1() > 0) {
            String locStr = OptionMap.getFullAddr(getJobLocPc1());
            if(!list.contains(locStr)){
                list.add(locStr);
            }
        }
        if(getJobLocPc2() != null && getJobLocPc2() > 0) {
            String locStr = OptionMap.getFullAddr(getJobLocPc2());
            if(!list.contains(locStr)){
                list.add(locStr);
            }
        }
        if(getJobLocPc3() != null && getJobLocPc3() > 0) {
            String locStr = OptionMap.getFullAddr(getJobLocPc3());
            if(!list.contains(locStr)){
                list.add(locStr);
            }
        }
        return list;
    }

    /**
     * 取得第一位城市
     * @return
     */
    public String getCity(){
        String locStr = "";
        if(getJobLocPc1() != null && getJobLocPc1() > 0) {
            locStr = OptionMap.getCityAddr(getJobLocPc1());
        }
        return locStr;
    }
    
    public String getDegreeStr() {
        String str = "";
        if(getDegreeId() != null) {
            str = OptionMap.getValue(OptionMap.OptionType.OPT_PER_DEGREE, getDegreeId());
        }

        return StringUtils.isNotBlank(str) ? str : "不限";
    }

    public String getWorkYearStr(){
        String str = "";
        if(getWorkedYear() != null) {
            str = OptionMap.getValue(OptionMap.OptionType.OPT_COM_REQ_WORKYEAR, getWorkedYear());
        }
        return StringUtils.isNotBlank(str) ? str : "不限";
    }

    public String getSrvExtendNo() {
        if(StringUtils.isNotBlank(ObjectUtils.toString(getSrvExtend()))){
            Map<String, String> extendMap = new Gson().fromJson(getSrvExtend(), new TypeToken<Map<String, String>>() {}.getType());
            if(extendMap != null && extendMap.containsKey("stallno")) {
                return extendMap.get("stallno");
            }
        }
        return "";
    }

    public ComPosition getComPosition(){
        Gson gson = new Gson();
        ComPosition comPosition = new ComPosition();
        if(StringUtils.isNotBlank(getJsoninfo())){
            Map<String, Object> jsonMap = gson.fromJson(getJsoninfo(), new TypeToken<Map<String, Object>>() {}.getType());
            comPosition =  gson.fromJson(gson.toJson(jsonMap.get("position")), ComPosition.class);
        }
        return comPosition;
    }

    public ComInfo getComInfo(){
    	Gson gson = new Gson();
        ComInfo comInfo = new ComInfo();
        if(StringUtils.isNotBlank(getJsoninfo())){
            Map<String, Object> jsonMap = gson.fromJson(getJsoninfo(), new TypeToken<Map<String, Object>>() {}.getType());
            comInfo = gson.fromJson(gson.toJson(jsonMap.get("company")), ComInfo.class);
        }
        return comInfo;
    }

    public String getSalaryStr(){
        String str = "";
        if(getSalary() != null && getSalary() > 0) {
            str = OptionMap.getValue(OptionMap.OptionType.OPT_SALARY, getSalary());
        }
        return StringUtils.isNotBlank(str) ? str : "";
    }

    public Integer getSalary(){
        if(salary !=null ) {
            return salary;
        }
        ComPosition comPosition = getComPosition();
        if(comPosition != null && comPosition.getSalary() != null){
            return NumberUtils.toInt(comPosition.getSalary()+"",0);
        }
        return 0;
    }

    //取得职位接受的简历类型
    public Integer getAcceptResumeType(){
        ComPosition comPosition = getComPosition();
        if(comPosition != null && comPosition.getAcceptResumeType() != null){
            return NumberUtils.toInt(comPosition.getAcceptResumeType()+"",0);
        }
        return 0;
    }

    public int getUrgent() {
        if(urgent > 0)return urgent;
        ComPosition comPosition = getComPosition();
        if(comPosition != null && comPosition.getUrgent() != null){
            return NumberUtils.toInt(comPosition.getUrgent()+"",0);
        }
        return 0;
    }

    //======================= getter && setter ========================
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public Integer getPosType() {
        return posType;
    }

    public void setPosType(Integer posType) {
        this.posType = posType;
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

    public String getComDesc() {
        return comDesc;
    }

    public void setComDesc(String comDesc) {
        this.comDesc = comDesc;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Date getSrvTime() {
        return srvTime;
    }

    public void setSrvTime(Date srvTime) {
        this.srvTime = srvTime;
    }

    public String getSrvExtend() {
        return srvExtend;
    }

    public void setSrvExtend(String srvExtend) {
        this.srvExtend = srvExtend;
    }

    public Integer getJobLocPc1() {
        return jobLocPc1;
    }

    public void setJobLocPc1(Integer jobLocPc1) {
        this.jobLocPc1 = jobLocPc1;
    }

    public Integer getJobLocPc2() {
        return jobLocPc2;
    }

    public void setJobLocPc2(Integer jobLocPc2) {
        this.jobLocPc2 = jobLocPc2;
    }

    public Integer getJobLocPc3() {
        return jobLocPc3;
    }

    public void setJobLocPc3(Integer jobLocPc3) {
        this.jobLocPc3 = jobLocPc3;
    }

    public Integer getWorkedYear() {
        return workedYear;
    }

    public void setWorkedYear(Integer workedYear) {
        this.workedYear = workedYear;
    }

    public Integer getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Integer degreeId) {
        this.degreeId = degreeId;
    }

    public Integer getAgeUp() {
        return ageUp;
    }

    public void setAgeUp(Integer ageUp) {
        this.ageUp = ageUp;
    }

    public Integer getAgeDown() {
        return ageDown;
    }

    public void setAgeDown(Integer ageDown) {
        this.ageDown = ageDown;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPosDesc() {
        return posDesc;
    }

    public void setPosDesc(String posDesc) {
        this.posDesc = posDesc;
    }

    public Byte getNeedHunt() {
        return needHunt;
    }

    public void setNeedHunt(Byte needHunt) {
        this.needHunt = needHunt;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Date getFreDate() {
        return freDate;
    }

    public void setFreDate(Date freDate) {
        this.freDate = freDate;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getPosFlag() {
        return posFlag;
    }

    public void setPosFlag(Integer posFlag) {
        this.posFlag = posFlag;
    }

    public String getJsoninfo() {
        return jsoninfo;
    }

    public void setJsoninfo(String jsoninfo) {
        this.jsoninfo = jsoninfo;
    }

    public Integer getJobCode1() {
		return jobCode1;
	}

	public void setJobCode1(Integer jobCode1) {
		this.jobCode1 = jobCode1;
	}

	public Integer getJobCode2() {
		return jobCode2;
	}

	public void setJobCode2(Integer jobCode2) {
		this.jobCode2 = jobCode2;
	}

	public Integer getJobCode3() {
		return jobCode3;
	}

	public void setJobCode3(Integer jobCode3) {
		this.jobCode3 = jobCode3;
	}

	public String getSeoDomain() {
        return seoDomain;
    }

    public void setSeoDomain(String seoDomain) {
        this.seoDomain = seoDomain;
    }

    public Integer getHotDegree() {
		return hotDegree;
	}

	public void setHotDegree(Integer hotDegree) {
		this.hotDegree = hotDegree;
	}



    public void setUrgent(int urgent) {
        this.urgent = urgent;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}