package com.job5156.core.bo.form;

import com.job5156.core.common.option.OptionMap;
import com.job5156.core.common.util.KeyMaskUtil;
import com.job5156.core.bo.mapping.ComContactBaseBo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComPositionBo extends ComContactBaseBo {
	private Integer id;
	private Integer pid; 				// 职位查询库id
	private Integer departmentId; 		// 部门关联ID
	private String deptName; 			// 部门名称
	private Integer contactId; 			// 联系方式ID
	@NotNull
	private List<Integer> posTypeList; 	// 职位类别
	@NotBlank
	private String posName; 			// 职位名称
	private Integer urgent; 			// 是否为急聘1=是
	private List<String> posKeywordList;// 职位关键字,最多5个
	private Integer property; 			// 工作性质，0=未选择，1=全职，2=兼职，3=实习 ,参见: OPT_MAP_JOBTYPE
    private Integer[] propertyArr;      // 工作性质,多选的形式，数组结构 参见: OPT_MAP_JOBTYPE
	@NotNull
	private Integer recruitmentNumber;	// 招聘人数，0=若干
	@NotNull
	private Date endDate; 				// 截止时间
	@NotNull
	private List<Integer> workLocationList; // 工作地区
	private Integer salary; 				// 薪资待遇
	private Integer negotiableFlag; 		// 显示为面议，0=否，1=是
	private List<String> taoLabelList; 		// 淘标签
	private Integer reqGender; 				// 性别要求，1=男，2=女，0=不限
	private Integer reqDegree; 				// 学历要求，0=未选择，
	private Integer reqAgeMin; 				// 最小年龄, 0=未选择
	private Integer reqAgeMax; 				// 最大年龄，0=未选择
	private Integer reqWorkYear; 			// 工作经验，0=未选择
	private Integer reqLocation; 			// 现所在地，0=未选择
	private Integer reqLanguage; 			// 语言要求，0=未选择
	private Integer reqLanguageLevel; 		// 语言等级，0=未选择
	private Integer filterDegree; 			// 学历是否必须符合要求，0=否，1=是
	private Integer filterAge; 				// 年龄是否必须符合要求，0=否，1=是
	private Integer filterWorkYear; 		// 工作经验是否必须符合要求，0=否，1=是
	private Integer filterGender; 			// 性别是否必须符合要求，0=否，1=是
	@NotBlank
	private String description; 			// 职位描述
	private String interviewNote; 			// 面试须知
	private Integer autoReply; 				// 接收到简历后是否自动回复，0=否，1=是
	private Integer templateFlag; 			// 是否为模板，0=否，1=是
	private Integer synType; 				// 联系人同步类型，0=同步更新到所有职位，1=同步更新到本部门所有职位（分子公司用户没有改选项）
											// -1：不同步(只用于当前职位）
	private Integer schoolFlag; 			// 是否为校园职位，0=否，1=是
	private Date createDate; 				// 创建时间
	private Date updateDate; 				// 职位修改时间
	private Date refreshDate; 				// 职位刷新时间
	private Integer posStatus; 				// 职位状态
	private Double score; 					// 职位得分
	private Integer viewCount; 				// 职位被查看次数
	private Integer resumeCount; 			// 收到的简历数
	private Integer resumeUnreadCount; 		// 未读简历数
	// 时间范围查询参数
	private Date searchStartDate; 			// 开始时间
	private Date searchEndDate; 			// 结束时间
	private Integer posId; 					// 职位id（因分页中已有默认参数id）
	private Integer sortType; 				// 排序方式(0=按照更新时间排序，1=按照部门名称排序)

	private ComInfoBo comInfoBo; 			// 关联的企业信息
	private ComDepartmentBo comDepartmentBo;// 关联的部门信息
	private BasicDataVo basicDataVo; 		// 职位基本信息
    private Integer acceptResumeType;       // -1:所有(包含名片)  0:正式简历(默认,不包含名片) 1：仅长沙简历 2：仅名片,参见 CommonEnum.acceptResumeType

	// 用于职位列表
	private int showType = 1; 				// 1为详情，2为列表

	// 用于职位导出到招聘会
	private int recruitType; 				// 1:正在招聘的职位 2：其它
	private String prodCate; 				// 产品的类别，参见IrcServiceItem实体类

	// 编码转换后的[Integer转换成String] 如：学历 5 --> 本科
	private List<String> locationList; 		// 转换后的工作地区
	private String locationListStr; 		// 转换后的工作地区字符串
	private String degree; 					// 转换后的学历
	private String posTypeStr; 				// 转换后的职位类别
	private String posKeywordStr; 			// 转换后的职位关键字
	private String name_taoLabel; 			// 淘标签
	private String salaryStr; 				// 转换后的薪水
	private String emailStr; 				// 转换后的邮箱
	private String addressStr; 				// 转换后的联系地址
	private String interviewAddressStr; 	// 转换后的面试地址
	private String propertyStr; 			// 转换后的工作性质
	private String reqWorkYearStr; 			// 转换后的工作经验
	private String reqGenderStr; 			// 转换后的性别
	private String reqLocationStr; 			// 转换后的现所在地
	private String reqLanguageStr; 			// 转换后的语言要求

	private Integer activeResumeCount; 		// 该职位收到的主动投递的简历数
	private Integer buyResumeCount; 		// 企业主动购买的简历数
	private Integer sysRecommandCount; 		// 后台推荐的简历数

	private Integer productId;				// 产品ID
	private Integer completeCycle;			// 完成期限

	private String seoDomain = ""; 			// 为了SEO域名跳转,这个字段只在读取的时候进行设置，不加入数据库
	private Integer posNo; 					// 搜索库主键（对应职位ID）
    public ComPositionBo() {
        comInfoBo = new ComInfoBo();
        comDepartmentBo = new ComDepartmentBo();
    }

    // ===================== NOT POJO  ==============================

	public Integer getPosNo() {
		if (id != null) {
			posNo = KeyMaskUtil.posIdToNo(id, (short) 1);
		}
		return posNo;
	}

	public static class BasicDataVo {
		private String beginDate;
		private String endDate;
		private String workLocation;
		private String interviewAddress;
		private String address;
		private String gender;

		public String getBeginDate() {
			return beginDate;
		}

		public void setBeginDate(String beginDate) {
			this.beginDate = beginDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getWorkLocation() {
			return workLocation;
		}

		public void setWorkLocation(String workLocation) {
			this.workLocation = workLocation;
		}

		public String getInterviewAddress() {
			return interviewAddress;
		}

		public void setInterviewAddress(String interviewAddress) {
			this.interviewAddress = interviewAddress;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

	}

    /**
     * 编码转换[学历转换 5-->本科 , 地区转换 1401-->东莞 等等]
     *
     * @param vo
     *            ComPositionVo
     */
    public void convert(ComPositionBo vo) {
        vo.setId(KeyMaskUtil.posIdToNo(vo.getId(), (short) 1));
        // 工作地区
        vo.setLocationList(new ArrayList<String>());
        List<Integer> workLocationList = vo.getWorkLocationList();
        if (CollectionUtils.isNotEmpty(workLocationList)) {
            for (Integer workLocation : workLocationList) {
                if (workLocation != null) {
                    vo.getLocationList().add(OptionMap.getCityAddr(workLocation));
                }
            }
        } else {
            vo.getLocationList().add("不限");
        }

        vo.setDegree(OptionMap.getValue(OptionMap.OptionType.OPT_PER_DEGREE, vo.getReqDegree()));
        if (vo.getReqDegree() == null || vo.getReqDegree() == 0) {
            vo.setDegree("不限");
        }
    }

	// ===================== getter && setter ==============================
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public List<Integer> getPosTypeList() {
		return posTypeList;
	}

	public void setPosTypeList(List<Integer> posTypeList) {
		this.posTypeList = posTypeList;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public Integer getUrgent() {
		return urgent;
	}

	public void setUrgent(Integer urgent) {
		this.urgent = urgent;
	}

	public List<String> getPosKeywordList() {
		return posKeywordList;
	}

	public void setPosKeywordList(List<String> posKeywordList) {
		this.posKeywordList = posKeywordList;
	}

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public Integer getRecruitmentNumber() {
		return recruitmentNumber;
	}

	public void setRecruitmentNumber(Integer recruitmentNumber) {
		this.recruitmentNumber = recruitmentNumber;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Integer> getWorkLocationList() {
		return workLocationList;
	}

	public void setWorkLocationList(List<Integer> workLocationList) {
		this.workLocationList = workLocationList;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getNegotiableFlag() {
		return negotiableFlag;
	}

	public void setNegotiableFlag(Integer negotiableFlag) {
		this.negotiableFlag = negotiableFlag;
	}

	public List<String> getTaoLabelList() {
		return taoLabelList;
	}

	public void setTaoLabelList(List<String> taoLabelList) {
		this.taoLabelList = taoLabelList;
	}

	public Integer getReqGender() {
		return reqGender == null ? 0 : reqGender;
	}

	public void setReqGender(Integer reqGender) {
		this.reqGender = reqGender;
	}

	public Integer getReqDegree() {
		return reqDegree == null ? 0 : reqDegree;
	}

	public void setReqDegree(Integer reqDegree) {
		this.reqDegree = reqDegree;
	}

	public Integer getReqAgeMin() {
		return reqAgeMin == null ? 0 : reqAgeMin;
	}

	public void setReqAgeMin(Integer reqAgeMin) {
		this.reqAgeMin = reqAgeMin;
	}

	public Integer getReqAgeMax() {
		return reqAgeMax == null ? 0 : reqAgeMax;
	}

	public void setReqAgeMax(Integer reqAgeMax) {
		this.reqAgeMax = reqAgeMax;
	}

	public Integer getReqWorkYear() {
		return reqWorkYear == null ? 0 : reqWorkYear;
	}

	public void setReqWorkYear(Integer reqWorkYear) {
		this.reqWorkYear = reqWorkYear;
	}

	public Integer getReqLocation() {
		return reqLocation == null ? 0 : reqLocation;
	}

	public void setReqLocation(Integer reqLocation) {
		this.reqLocation = reqLocation;
	}

	public Integer getReqLanguage() {
		return reqLanguage == null ? 0 : reqLanguage;
	}

	public void setReqLanguage(Integer reqLanguage) {
		this.reqLanguage = reqLanguage;
	}

	public Integer getReqLanguageLevel() {
		return reqLanguageLevel;
	}

	public void setReqLanguageLevel(Integer reqLanguageLevel) {
		this.reqLanguageLevel = reqLanguageLevel;
	}

	public Integer getFilterDegree() {
		return filterDegree;
	}

	public void setFilterDegree(Integer filterDegree) {
		this.filterDegree = filterDegree;
	}

	public Integer getFilterAge() {
		return filterAge;
	}

	public void setFilterAge(Integer filterAge) {
		this.filterAge = filterAge;
	}

	public Integer getFilterWorkYear() {
		return filterWorkYear;
	}

	public void setFilterWorkYear(Integer filterWorkYear) {
		this.filterWorkYear = filterWorkYear;
	}

	public Integer getFilterGender() {
		return filterGender;
	}

	public void setFilterGender(Integer filterGender) {
		this.filterGender = filterGender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInterviewNote() {
		return interviewNote;
	}

	public void setInterviewNote(String interviewNote) {
		this.interviewNote = interviewNote;
	}

	public Integer getAutoReply() {
		return autoReply;
	}

	public void setAutoReply(Integer autoReply) {
		this.autoReply = autoReply;
	}

	public Integer getTemplateFlag() {
		return templateFlag;
	}

	public void setTemplateFlag(Integer templateFlag) {
		this.templateFlag = templateFlag;
	}

	public Integer getSynType() {
		return synType;
	}

	public void setSynType(Integer synType) {
		this.synType = synType;
	}

	public Integer getSchoolFlag() {
		return schoolFlag;
	}

	public void setSchoolFlag(Integer schoolFlag) {
		this.schoolFlag = schoolFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getRefreshDate() {
		return refreshDate;
	}

	public void setRefreshDate(Date refreshDate) {
		this.refreshDate = refreshDate;
	}

	public Integer getPosStatus() {
		return posStatus;
	}

	public void setPosStatus(Integer posStatus) {
		this.posStatus = posStatus;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getResumeCount() {
		return resumeCount;
	}

	public void setResumeCount(Integer resumeCount) {
		this.resumeCount = resumeCount;
	}

	public Integer getResumeUnreadCount() {
		return resumeUnreadCount;
	}

	public void setResumeUnreadCount(Integer resumeUnreadCount) {
		this.resumeUnreadCount = resumeUnreadCount;
	}

	public Date getSearchStartDate() {
		return searchStartDate;
	}

	public void setSearchStartDate(Date searchStartDate) {
		this.searchStartDate = searchStartDate;
	}

	public Date getSearchEndDate() {
		return searchEndDate;
	}

	public void setSearchEndDate(Date searchEndDate) {
		this.searchEndDate = searchEndDate;
	}

	public Integer getPosId() {
		return posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public Integer getSortType() {
		return sortType;
	}

	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}

	public ComInfoBo getComInfoBo() {
		return comInfoBo;
	}

	public void setComInfoBo(ComInfoBo comInfoBo) {
		this.comInfoBo = comInfoBo;
	}

	public BasicDataVo getBasicDataVo() {
		return basicDataVo;
	}

	public ComDepartmentBo getComDepartmentBo() {
		return comDepartmentBo;
	}

	public void setComDepartmentBo(ComDepartmentBo comDepartmentBo) {
		this.comDepartmentBo = comDepartmentBo;
	}

	public void setBasicDataVo(BasicDataVo basicDataVo) {
		this.basicDataVo = basicDataVo;
	}

	public int getShowType() {
		return showType;
	}

	public void setShowType(int showType) {
		this.showType = showType;
	}

	public int getRecruitType() {
		return recruitType;
	}

	public void setRecruitType(int recruitType) {
		this.recruitType = recruitType;
	}

	public String getProdCate() {
		return prodCate;
	}

	public void setProdCate(String prodCate) {
		this.prodCate = prodCate;
	}

	public List<String> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<String> locationList) {
		this.locationList = locationList;
	}

	public String getLocationListStr() {
		locationListStr = StringUtils.join(getLocationList(), ",");
		return locationListStr;
	}

	public void setLocationListStr(String locationListStr) {
		this.locationListStr = locationListStr;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getPosTypeStr() {
		return posTypeStr;
	}

	public void setPosTypeStr(String posTypeStr) {
		this.posTypeStr = posTypeStr;
	}

	public String getPosKeywordStr() {
		return posKeywordStr;
	}

	public void setPosKeywordStr(String posKeywordStr) {
		this.posKeywordStr = posKeywordStr;
	}

	public String getName_taoLabel() {
		return name_taoLabel;
	}

	public void setName_taoLabel(String name_taoLabel) {
		this.name_taoLabel = name_taoLabel;
	}

	public String getSalaryStr() {
		return salaryStr;
	}

	public void setSalaryStr(String salaryStr) {
		this.salaryStr = salaryStr;
	}

	public String getEmailStr() {
		return emailStr;
	}

	public void setEmailStr(String emailStr) {
		this.emailStr = emailStr;
	}

	public String getAddressStr() {
		return addressStr;
	}

	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}

	public String getInterviewAddressStr() {
		return interviewAddressStr;
	}

	public void setInterviewAddressStr(String interviewAddressStr) {
		this.interviewAddressStr = interviewAddressStr;
	}

	public String getPropertyStr() {
		return propertyStr;
	}

	public void setPropertyStr(String propertyStr) {
		this.propertyStr = propertyStr;
	}

	public String getReqWorkYearStr() {
		return reqWorkYearStr;
	}

	public void setReqWorkYearStr(String reqWorkYearStr) {
		this.reqWorkYearStr = reqWorkYearStr;
	}

	public String getReqGenderStr() {
		return reqGenderStr;
	}

	public void setReqGenderStr(String reqGenderStr) {
		this.reqGenderStr = reqGenderStr;
	}

	public String getReqLocationStr() {
		return reqLocationStr;
	}

	public void setReqLocationStr(String reqLocationStr) {
		this.reqLocationStr = reqLocationStr;
	}

	public String getReqLanguageStr() {
		return reqLanguageStr;
	}

	public void setReqLanguageStr(String reqLanguageStr) {
		this.reqLanguageStr = reqLanguageStr;
	}

	public Integer getActiveResumeCount() {
		return activeResumeCount;
	}

	public void setActiveResumeCount(Integer activeResumeCount) {
		this.activeResumeCount = activeResumeCount;
	}

	public Integer getBuyResumeCount() {
		return buyResumeCount;
	}

	public void setBuyResumeCount(Integer buyResumeCount) {
		this.buyResumeCount = buyResumeCount;
	}

	public Integer getSysRecommandCount() {
		return sysRecommandCount;
	}

	public void setSysRecommandCount(Integer sysRecommandCount) {
		this.sysRecommandCount = sysRecommandCount;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getCompleteCycle() {
		return completeCycle;
	}

	public void setCompleteCycle(Integer completeCycle) {
		this.completeCycle = completeCycle;
	}

	public String getSeoDomain() {
		return seoDomain;
	}

	public void setSeoDomain(String seoDomain) {
		this.seoDomain = seoDomain;
	}

	public void setPosNo(Integer posNo) {
		this.posNo = posNo;
	}

    public Integer getAcceptResumeType() {
        return acceptResumeType;
    }

    public void setAcceptResumeType(Integer acceptResumeType) {
        this.acceptResumeType = acceptResumeType;
    }

    public Integer[] getPropertyArr() {
        return propertyArr;
    }

    public void setPropertyArr(Integer[] propertyArr) {
        this.propertyArr = propertyArr;
    }
}
