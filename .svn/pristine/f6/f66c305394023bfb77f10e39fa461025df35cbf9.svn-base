package com.job5156.webapp.model.proj;

import com.job5156.webapp.model.sys.system.SysUser;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 兼职职位发布信息表
 * </p>
 * 
 * Date:2015年4月13日 下午3:27:11
 * 
 * @author Andy
 * @version 1.0
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ProjPtimeInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 兼职id，主键、自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * 后台操作人员id
	 */
	private Integer sysUserId;

	/**
	 * 兼职名称
	 */
	@NotBlank(message = "{proj.parttime.ptimeName.is.notempty}")
	private String ptimeName;

	/**
	 * 类型id
	 */
	@NotNull(message = "{proj.parttime.type.is.notempty}")
	private Integer type;

	/**
	 * 地区id
	 */
/*	@NotNull(message = "{proj.parttime.area.is.notempty}")*/
	private Integer area;

	/**
	 * 判断兼职是否热门（默认是0为不热门，1是热门）
	 */
	private Integer hot;
	
	/**
	 * 判断兼职是否高薪（默认是0为不是高薪，1是高薪）
	 */
	private Integer salaries;

	/**
	 * 发布兼职公司的名称
	 */
	@NotBlank(message = "{proj.parttime.comName.is.notempty}")
	private String comName;

	/**
	 * 招聘时间限制（最后招聘的时间）
	 */
	@NotNull(message = "{proj.parttime.timeLimit.is.notempty}")
	private Date timeLimit;

	/**
	 * 工资待遇（钱的数额）
	 */
	@NotNull(message = "{proj.parttime.salary.is.notempty}")
	private Integer salary;

	/**
	 * 工资待遇的单位(1表示小时，2表示半天，3表示一天)
	 */
	@NotNull(message = "{proj.parttime.unit.is.notempty}")
	private Integer unit;

	/**
	 * 工作开始时间
	 */
	@NotNull(message = "{proj.parttime.startDate.is.notempty}")
	private Date startDate;

	/**
	 * 工作结束时间
	 */
	@NotNull(message = "{proj.parttime.endDate.is.notempty}")
	private Date endDate;

	/**
	 * 招聘人数
	 */
	@NotNull(message = "{proj.parttime.recruitmentNum.is.notempty}")
	private Integer recruitmentNum;

	/**
	 * 兼职描述
	 */
	private String descript;

	/**
	 * 发布企业联系人姓名
	 */
	@NotBlank(message = "{proj.parttime.comUserName.is.notempty}")
	private String comUserName;

	/**
	 * 发布企业联系电话
	 */
	@NotBlank(message = "{proj.parttime.phone.is.notempty}")
	private String phone;

	/**
	 * 发布企业的详细地址
	 */
	@NotBlank(message = "{proj.parttime.address.is.notempty}")
	private String address;

	/**
	 * 发布时间
	 */
	private Date releaseTime;

	/**
	 * 招聘状态（0表示正常，1表示停止，2表示重新开始，3表示已过期）
	 */
	private Integer state;

	/**
	 * 显示（默认值1表示显示,0表示不显示）
	 */
	private Integer delFlag;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 编辑时间
	 */
	private Date editeDate;
	
	/**
	 * 纬度
	 */
	private String lat;
	
	/**
	 * 经度
	 */
	private String lng;
	
	/**
	 * 百度地图X坐标
	 */
	private String X;
	
	/**
	 * 百度地图Y坐标
	 */
	private String Y;
	
	

	
	@NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sysUserId", insertable = false, updatable = false)
    private SysUser sysUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getPtimeName() {
		return ptimeName;
	}

	public void setPtimeName(String ptimeName) {
		this.ptimeName = ptimeName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Integer getSalaries() {
		return salaries;
	}

	public void setSalaries(Integer salaries) {
		this.salaries = salaries;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public Date getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Date timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getRecruitmentNum() {
		return recruitmentNum;
	}

	public void setRecruitmentNum(Integer recruitmentNum) {
		this.recruitmentNum = recruitmentNum;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getComUserName() {
		return comUserName;
	}

	public void setComUserName(String comUserName) {
		this.comUserName = comUserName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEditeDate() {
		return editeDate;
	}

	public void setEditeDate(Date editeDate) {
		this.editeDate = editeDate;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getX() {
		return X;
	}

	public void setX(String x) {
		X = x;
	}

	public String getY() {
		return Y;
	}

	public void setY(String y) {
		Y = y;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
}
