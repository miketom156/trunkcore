package com.job5156.core.bo.mapping;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.job5156.core.common.CommonEnum;
import com.job5156.core.common.option.OptionMap;
import com.job5156.core.common.util.KeyMaskUtil;
import com.job5156.webapp.model.per.job.PerInviteLog;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

public class PerInviteLogBo {

	private Integer id;
	private Integer comId;
	private Integer perUserId;//个人账号ID
	private Integer posId;//个人账号ID
	private Date creTime;// 记录生成时间
	private Date editTime;// 个人修改【备注或红旗标记】时间
    private Integer delStatus;// 删除状态 {-1 已删除}
	private Integer readStatus;  // 个人查看状态 未查看0 已查看1
	private Integer mark;// 旗子标记 
	private String remark;// 备注内容
	private String userName;//用户名称
	private String inviteDetail;//邀请详情内容
	private String interviewAddress;//面试地址
	private Integer roomNum;//视频面试房间号
    private String bookPeriod; //预约的面试时间段

    private MixInfoVo mixInfoVo;
    private Integer posFlag;
    private Integer posNo;
    private String UrlRoot; //链接路径，为触屏版与PC端区分

	public PerInviteLogBo(PerInviteLog perInviteLog) {
        if(perInviteLog != null) {
            BeanUtils.copyProperties(perInviteLog, this);
            if(StringUtils.isNotBlank(perInviteLog.getMixInfo()) && !StringUtils.equals("{}",perInviteLog.getMixInfo())){
                this.setMixInfoVo(new Gson().fromJson(perInviteLog.getMixInfo(), MixInfoVo.class));
            } else {
                this.setMixInfoVo(new MixInfoVo());
            }
            if (posId != null) {
                posNo = KeyMaskUtil.posIdToNo(getPosId(), (short) 1);
            }
        }
    }

    /**
     * 面试邀请的基本信息
     * @param vo
     * @return
     */
    public String getInviteMsg(PerInviteLogBo vo) {
        String msg = "";
        msg = "我们(<a href=" + vo.getUrlRoot() + "/corp-" + vo.getComId() + ".html>" + vo.getMixInfoVo().getComName() + "</a>)"
                + "已经收到您通过Job5156.com(智通人才网)发来的应聘资料，经过我们的评估，觉得您适合我们的职位"
                + "(<a href=" + vo.getUrlRoot() + "/job-" + vo.getPosNo() + ".html>" + vo.getMixInfoVo().getPosName() + "</a>)"
                + "现诚邀面试。";
        return msg;
    }

    public class MixInfoVo {
        private String posName;
        private String comName;
        private String jobLocation;
        private Integer salary;
        private Integer degree;
        private Integer workYear;
        private String description;
        private String taoLabel;
        private Integer comIndustry;
        private Integer comProperty;
        private String contactPhone;
        private String contactPerson;
        private Integer posStatus;  //职位状态

        public String getJobLocationStr(){
            List<String> list = Lists.newArrayList();
            if(StringUtils.isNotBlank(getJobLocation()) && !StringUtils.equals(getJobLocation(), "[]")) {
                Gson gson = new Gson();
                List<Integer> workLocationList = gson.fromJson(getJobLocation(), new TypeToken<List<Integer>>() {}.getType());
                if(CollectionUtils.isNotEmpty(workLocationList)) {
                    for(Integer workLocation : workLocationList) {
                        String locStr = OptionMap.getFullAddr(workLocation);
                        if(!list.contains(locStr)){
                            list.add(locStr);
                        }
                    }
                }
            }
            return StringUtils.join(list, " ");
        }

        public String getSalaryStr(){
            String str = "";
            if(getSalary() != null) {
                str = OptionMap.getValue(OptionMap.OptionType.OPT_SALARY, getSalary());
            }
            return StringUtils.isNotBlank(str) ? str : "薪资面议";
        }

        public String getDegreeStr() {
            String str = "";
            if(getDegree() != null) {
                str = OptionMap.getValue(OptionMap.OptionType.OPT_PER_DEGREE, getDegree());
            }
            return StringUtils.isNotBlank(str) ? str : "学历不限";
        }

        public String getWorkYearStr(){
            String str = "";
            if(getWorkYear() != null) {
                str = OptionMap.getValue(OptionMap.OptionType.OPT_COM_REQ_WORKYEAR, getWorkYear());
            }
            return StringUtils.isNotBlank(str) ? str : "经验不限";
        }

        public String getTaoLabelStr() {
            Gson gson = new Gson();
            List<String> taoLabelList = gson.fromJson(getTaoLabel(), new TypeToken<List<String>>() {}.getType());
            return StringUtils.join(taoLabelList, " ");
        }

        public String getComIndustryStr(){
            String str = "";
            if(getComIndustry() != null) {
                str = OptionMap.getValue(OptionMap.OptionType.OPT_INDUSTRY, getComIndustry());
            }
            return str;
        }

        public String getComPropertyStr(){
            String str = "";
            if(getComProperty() != null) {
                str = OptionMap.getValue(OptionMap.OptionType.OPT_COM_PROPERTY, getComProperty());
            }
            return str;
        }

        public String getPosStatusStr(){
            if(getPosStatus() != null){
                return CommonEnum.PositionStatus.getDescriptByValue(getPosStatus());
            }
            return "";
        }

        public Integer getPosStatus() {
            return posStatus;
        }

        public void setPosStatus(Integer posStatus) {
            this.posStatus = posStatus;
        }

        public String getPosName() {
            return posName;
        }

        public void setPosName(String posName) {
            this.posName = posName;
        }

        public String getComName() {
            return comName;
        }

        public void setComName(String comName) {
            this.comName = comName;
        }

        public String getJobLocation() {
            return jobLocation;
        }

        public void setJobLocation(String jobLocation) {
            this.jobLocation = jobLocation;
        }

        public Integer getSalary() {
            return salary;
        }

        public void setSalary(Integer salary) {
            this.salary = salary;
        }

        public Integer getDegree() {
            return degree;
        }

        public void setDegree(Integer degree) {
            this.degree = degree;
        }

        public Integer getWorkYear() {
            return workYear;
        }

        public void setWorkYear(Integer workYear) {
            this.workYear = workYear;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTaoLabel() {
            return taoLabel;
        }

        public void setTaoLabel(String taoLabel) {
            this.taoLabel = taoLabel;
        }

        public Integer getComIndustry() {
            return comIndustry;
        }

        public void setComIndustry(Integer comIndustry) {
            this.comIndustry = comIndustry;
        }

        public Integer getComProperty() {
            return comProperty;
        }

        public void setComProperty(Integer comProperty) {
            this.comProperty = comProperty;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getContactPerson() {
            return contactPerson;
        }

        public void setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
        }
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public Integer getPerUserId() {
		return perUserId;
	}

	public void setPerUserId(Integer perUserId) {
		this.perUserId = perUserId;
	}

	public Integer getPosId() {
		return posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}

	public Integer getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInviteDetail() {
		return inviteDetail;
	}

	public void setInviteDetail(String inviteDetail) {
		this.inviteDetail = inviteDetail;
	}

	public String getInterviewAddress() {
		return interviewAddress;
	}
	public void setInterviewAddress(String interviewAddress) {
		this.interviewAddress = interviewAddress;
	}

	public Integer getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

    public String getBookPeriod() {
        return bookPeriod;
    }

    public void setBookPeriod(String bookPeriod) {
        this.bookPeriod = bookPeriod;
    }

    public MixInfoVo getMixInfoVo() {
        return mixInfoVo;
    }

    public void setMixInfoVo(MixInfoVo mixInfoVo) {
        this.mixInfoVo = mixInfoVo;
    }

    public Integer getPosFlag() {
        return posFlag;
    }

    public void setPosFlag(Integer posFlag) {
        this.posFlag = posFlag;
    }

    public Integer getPosNo() {
        return posNo;
    }

    public void setPosNo(Integer posNo) {
        this.posNo = posNo;
    }

    public String getUrlRoot() {
        return UrlRoot;
    }

    public void setUrlRoot(String urlRoot) {
        UrlRoot = urlRoot;
    }




}
