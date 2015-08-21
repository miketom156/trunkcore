package com.job5156.core.bo.form;

public class ComReceiveAssistBo {

	private String lastPosName;//最近一份工作的职位名
	private String comName;//最近一份工作的单位
//	private String lastJobLocation;//最近一份工作地点
	private String start;//最近一份工作开始时间
	private String end;//最近一份工作结束时间
	private String skill;//职业技能
	private String mobile;//联系电话[手机号]
	private Integer jobyearType;//工作年限类型
//	private Integer fileName;//头像名
//	private Integer filePath;//头像路径
    private String jobCode;    //期望工作职位
    private String jobName;    //自定义职位
	
	public String getLastPosName() {
		return lastPosName;
	}
	public void setLastPosName(String lastPosName) {
		this.lastPosName = lastPosName;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
//	public String getLastJobLocation() {
//		return lastJobLocation;
//	}
//	public void setLastJobLocation(String lastJobLocation) {
//		this.lastJobLocation = lastJobLocation;
//	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getJobyearType() {
		return jobyearType;
	}
	public void setJobyearType(Integer jobyearType) {
		this.jobyearType = jobyearType;
	}
//	public Integer getFileName() {
//		return fileName;
//	}
//	public void setFileName(Integer fileName) {
//		this.fileName = fileName;
//	}
//	public Integer getFileAbsolutePath() {
//		return filePath;
//	}
//	public void setFilePath(Integer filePath) {
//		this.filePath = filePath;
//	}


    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
