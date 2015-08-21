package com.job5156.core.bo.redis;

import org.hibernate.validator.constraints.NotBlank;

public class PerUserLoginRedisBo {

    @NotBlank
    private String id;

    private String ip;

    private String loginName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
