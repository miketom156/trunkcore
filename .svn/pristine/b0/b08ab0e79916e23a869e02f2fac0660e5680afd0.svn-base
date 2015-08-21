package com.job5156.core.bo.form.per;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.job5156.core.common.option.OptionMap;
import com.job5156.webapp.model.per.job.PerPosApply;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * <p></p>
 * Date:Administrator 2015-6-24
 *
 * @author hjs
 * @version 1.0
 */
public class PerPosApplyBo {
    private Integer id;//主键,
    private Integer perUserId;//人个用户ID
    private Integer posId;//职位ID
    private Integer comId;//企业ID
    private Integer applyNum;//申请应聘次数
    private Date creTime;//记录生成时间【应聘时间】
    private Date editTime;//修改时间
    private Integer emailIcoFlag;//邮件图标标记，详情看解决方案
    private Integer delStatus;// 删除状态 {-1 已删除}
    private Integer mark;//旗子标记
    private String remark;//备注内容
    private Integer replyStatus;//企业对此次应聘的回复状态
    private MixInfoVo mixInfoVo;
    private Integer posFlag;
    private Integer posNo;
    private Integer resumeId;
    private String resumeName;

    public PerPosApplyBo(PerPosApply perPosApply) {
        if(perPosApply != null) {
            BeanUtils.copyProperties(perPosApply, this);

            if(StringUtils.isNotBlank(perPosApply.getMixInfo())){
                this.setMixInfoVo(new Gson().fromJson(perPosApply.getMixInfo(), MixInfoVo.class));
            } else {
                this.setMixInfoVo(new MixInfoVo());
            }
        }
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public Integer getComId() {
        return comId;
    }
    public void setComId(Integer comId) {
        this.comId = comId;
    }
    public Integer getApplyNum() {
        return applyNum;
    }
    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
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
    public Integer getEmailIcoFlag() {
        return emailIcoFlag;
    }
    public void setEmailIcoFlag(Integer emailIcoFlag) {
        this.emailIcoFlag = emailIcoFlag;
    }
    public Integer getDelStatus() {
        return delStatus;
    }
    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
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
    public Integer getReplyStatus() {
        return replyStatus;
    }
    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
    }
    public MixInfoVo getMixInfoVo() {
        return mixInfoVo;
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

    public Integer getResumeId() {
        return resumeId;
    }
    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }
    public String getResumeName() {
        return resumeName;
    }
    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }
    public void setMixInfoVo(MixInfoVo mixInfoVo) {
        this.mixInfoVo = mixInfoVo;
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
    }
}

