package com.job5156.core.entity.sys.system;

import com.google.common.collect.Lists;
import com.job5156.core.common.helper.frame.ServerInformation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台用户实体对象
 * </p>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 			// 用户id
	@NotBlank(message = "{sys.user.account.is.notempty}")
	private String account; 		// 账号
	@NotBlank(message = "{sys.user.password.is.notempty}")
	@Length(min = 6, message = "{sys.user.password.minimun.length}")
	private String password; 		// 密码
	@NotBlank(message = "{sys.user.name.is.notempty}")
	private String name; 			// 用户名
	@NotNull(message = "{sys.user.deptid.is.notempty}")
	private Integer deptId; 		// 部门id
	private String workNumber; 		// 工号
	private String phone; 			// 联系电话
	private String mobile; 			// 手机号码
	private String userEmail; 		// 用户邮箱
	private String internalEmail; 	// 内部邮箱
	private String state; 			// 用户状态[Y-正式用户 N-非正式用户 S-账号暂停]
	private Integer grade; 			// 等级[1-管理员 2-总监 3-经理 4-主管 5-一般人员]
	private String webSiteRight; 	// 分站点权限[例：1401,1402]
	private String lastLoginIp; 	// 上次登陆ip
	private String lastLoginHost; 	// 上次登陆主机地址
	private Date lastLoginDate; 	// 上次登陆日期
	private Date firstLoginDate; 	// 首次登陆日期
	private Long loginCount; 		// 登陆次数
	private Integer advisorFlag; 	// 是否顾问[1-是 0-否]
	private Integer score; 			// 分数
	private String fax; 			// 传真
	private String avatarPath; 		// 用户头像路径
	private String avatarName;		// 用户头像名称
	private Integer resumeNum; 		// 可下载简历数量
	private Integer usedNum; 		// 已下载简历数量
	private String globalCall; 		// 全国呼叫中心[默认95105333]
	private Date createDate; 		// 创建日期
	private String createBy; 		// 创建人
	private Date editDate; 			// 修改日期
	private String editBy; 			// 修改人

	private SysDepartment department; // 所属部门
	private List<SysRole> roleList = Lists.newArrayList(); // 拥有的角色列表

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deptId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public SysDepartment getDepartment() {
		return department;
	}

	public void setDepartment(SysDepartment department) {
		this.department = department;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getWorkNumber() {
		return workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getInternalEmail() {
		return internalEmail;
	}

	public void setInternalEmail(String internalEmail) {
		this.internalEmail = internalEmail;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginHost() {
		return lastLoginHost;
	}

	public void setLastLoginHost(String lastLoginHost) {
		this.lastLoginHost = lastLoginHost;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getFirstLoginDate() {
		return firstLoginDate;
	}

	public void setFirstLoginDate(Date firstLoginDate) {
		this.firstLoginDate = firstLoginDate;
	}

	public Long getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Long loginCount) {
		this.loginCount = loginCount;
	}

	public Integer getAdvisorFlag() {
		return advisorFlag;
	}

	public void setAdvisorFlag(Integer advisorFlag) {
		this.advisorFlag = advisorFlag;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getResumeNum() {
		return resumeNum;
	}

	public void setResumeNum(Integer resumeNum) {
		this.resumeNum = resumeNum;
	}

	public Integer getUsedNum() {
		return usedNum;
	}

	public void setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
	}

	public String getGlobalCall() {
		return globalCall;
	}

	public void setGlobalCall(String globalCall) {
		this.globalCall = globalCall;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public String getEditBy() {
		return editBy;
	}

	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}

	public String getWebSiteRight() {
		return webSiteRight;
	}

	public void setWebSiteRight(String webSiteRight) {
		this.webSiteRight = webSiteRight;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public String getAvatarName() {
		return avatarName;
	}

	public void setAvatarName(String avatarName) {
		this.avatarName = avatarName;
	}

	@Transient
	public String getDepartmentStr() {
		StringBuffer departmentBuf = new StringBuffer();
		if (this.deptId != null) {
			ServerInformation serverInformation = ServerInformation.getInstance();
			Map<String, SysDepartment> departmentMap = serverInformation.getDepartmentMap();
			Map<String, String> departmentPathMap = serverInformation.getDepartmentPathMap();

			String departmentPathStr = departmentPathMap.get(String.valueOf(this.deptId));
			String[] departmentPathArr = StringUtils.split(departmentPathStr, ",");
			if (ArrayUtils.isNotEmpty(departmentPathArr)) {
				for (int i = 0; i < departmentPathArr.length; i++) {
					departmentBuf.append(departmentMap.get(departmentPathArr[i]).getName());
					if (i != departmentPathArr.length - 1) {
						departmentBuf.append(" > ");
					}
				}
			}
		}
		return departmentBuf.toString();
	}
}
