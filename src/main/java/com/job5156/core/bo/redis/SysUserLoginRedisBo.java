package com.job5156.core.bo.redis;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2015-6-16.
 */
public class SysUserLoginRedisBo {
    /**
     * 系统用户id
     */
    @NotNull
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户访问ip
     */
    private String ip;
    /**
     * 部门id
     */
    private Integer deptId;
    /**
     * 用户等级
     */
    private Integer grade;
    /**
     * 用户状态
     */
    private String state;
    /**
     * 分站点权限
     */
    private String webSiteRight;
    /**
     * 登陆次数
     */
    private Long loginCount;
    /**
     * 上次登陆日期(格式:yyyy-MM-dd HH:mm:ss)
     */
    private String lastLoginDate;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWebSiteRight() {
        return webSiteRight;
    }

    public void setWebSiteRight(String webSiteRight) {
        this.webSiteRight = webSiteRight;
    }

    public Long getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
