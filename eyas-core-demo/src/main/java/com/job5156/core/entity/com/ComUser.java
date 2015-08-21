package com.job5156.core.entity.com;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer comId;//企业ID
    private Integer deptId;//所属部门
    private String username;//账号名称
    private String password;//密码
    private Integer roleType;//用户权限类型{0:主账号;1:子账号;2:分公司账号}',
    private Date creDate;//创建时间
    private Date editDate;//修改时间
    private Date lastLoginDate;//最后一次登录时间
    private String lastLoginIp;//登陆IP
    private Integer loginCount;//登陆次数
    private Integer pointOwn;//可用最大点数
    private Integer pointUsed;//已用点数
    private Integer canLoginDual;//同一账号是否可以同时登陆
    private Integer canSetPosition;//'用户是否可以发布职位（{0:不可以;1:可以}，默认为可以）
    private Integer delStatus;// -1:已删除
    private String deptRights;//拥有的部门权限,json数组格式
    

    @ManyToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name = "comId", insertable = false, updatable = false)
    private ComInfo comInfo;

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

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

    public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getPointOwn() {
        return pointOwn;
    }

    public void setPointOwn(Integer pointOwn) {
        this.pointOwn = pointOwn;
    }

    public Integer getPointUsed() {
        return pointUsed;
    }

    public void setPointUsed(Integer pointUsed) {
        this.pointUsed = pointUsed;
    }

    public Integer getCanLoginDual() {
        return canLoginDual;
    }

    public void setCanLoginDual(Integer canLoginDual) {
        this.canLoginDual = canLoginDual;
    }

    public Integer getCanSetPosition() {
        return canSetPosition;
    }

    public void setCanSetPosition(Integer canSetPosition) {
        this.canSetPosition = canSetPosition;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public ComInfo getComInfo() {
        return comInfo;
    }

    public void setComInfo(ComInfo comInfo) {
        this.comInfo = comInfo;
    }

	public String getDeptRights() {
		return deptRights;
	}

	public void setDeptRights(String deptRights) {
		this.deptRights = deptRights;
	}

    
}
