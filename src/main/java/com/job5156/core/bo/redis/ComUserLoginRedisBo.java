package com.job5156.core.bo.redis;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2015-6-16.
 */
public class ComUserLoginRedisBo {
    @NotBlank
    private String comId;

    private String comName;

    @NotBlank
    private String comUserId;
    private String comUserName;
    private Integer canLoginDual;
    private Integer comUserRoleType;
    private String comUserDeptRights;
    private String loginFrom;		//登陆来源[网站后台：SYS, CRM: CRM]

//    private boolean member;
//    private String memberType;
//    private String memberRestrictArea;

    private String ip;

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComUserId() {
        return comUserId;
    }

    public void setComUserId(String comUserId) {
        this.comUserId = comUserId;
    }

    public String getComUserName() {
        return comUserName;
    }

    public void setComUserName(String comUserName) {
        this.comUserName = comUserName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getCanLoginDual() {
        return canLoginDual;
    }

    public void setCanLoginDual(Integer canLoginDual) {
        this.canLoginDual = canLoginDual;
    }

    public Integer getComUserRoleType() {
        return comUserRoleType;
    }

    public void setComUserRoleType(Integer comUserRoleType) {
        this.comUserRoleType = comUserRoleType;
    }

    public String getComUserDeptRights() {
        return comUserDeptRights;
    }

    public void setComUserDeptRights(String comUserDeptRights) {
        this.comUserDeptRights = comUserDeptRights;
    }

    public String getLoginFrom() {
        return loginFrom;
    }

    public void setLoginFrom(String loginFrom) {
        this.loginFrom = loginFrom;
    }
}
