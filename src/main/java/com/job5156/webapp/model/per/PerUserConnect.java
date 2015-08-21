package com.job5156.webapp.model.per;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.job5156.webapp.model.com.ComUser;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class PerUserConnect implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
     private Integer perUserId;
     private Integer accountId;			  //通行证ID
     private String openId;
     private Integer comUserId; //企业用户id
     private String openName;
     private String accessToken;
     private Date connectTime;
     private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perUserId", insertable = false, updatable = false)
    private PerUser perUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comUserId", insertable = false, updatable = false)
	private ComUser comUser;

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

    public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenName() {
        return openName;
    }

    public void setOpenName(String openName) {
        this.openName = openName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getConnectTime() {
        return connectTime;
    }

    public void setConnectTime(Date connectTime) {
        this.connectTime = connectTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PerUser getPerUser() {
        return perUser;
    }

    public void setPerUser(PerUser perUser) {
        this.perUser = perUser;
    }

	public Integer getComUserId() {
		return comUserId;
	}

	public void setComUserId(Integer comUserId) {
		this.comUserId = comUserId;
	}

	public ComUser getComUser() {
		return comUser;
	}

	public void setComUser(ComUser comUser) {
		this.comUser = comUser;
	}


}
