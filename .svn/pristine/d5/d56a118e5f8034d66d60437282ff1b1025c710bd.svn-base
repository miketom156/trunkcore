package com.job5156.webapp.model.proj;

import com.job5156.webapp.model.per.PerResume;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>兼职信息记录表</p>
 * 
 * Date:2015年4月29日 上午8:42:16
 * @author Andy
 * @version 1.0
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ProjPtimeRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 记录id，主键、自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * 通行证id
	 */
	private Integer accountId;

	/**
	 * 微名片的id
	 */
	private Integer resumeId;

	/**
	 * 兼职职位id
	 */
	private Integer partTimeId;

	/**
	 * 投递时间
	 */
	private Date deliveryTime;

	/**
	 * 投递后的状态（0表示无操作，1表示邀请，2表示录用，3弃用）
	 */
	private Integer state;
	
	@NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resumeId", insertable = false, updatable = false)
    private PerResume perResume;
	
	@NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partTimeId", insertable = false, updatable = false)
    private ProjPtimeInfo projPtimeInfo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getResumeId() {
		return resumeId;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}

	public Integer getPartTimeId() {
		return partTimeId;
	}

	public void setPartTimeId(Integer partTimeId) {
		this.partTimeId = partTimeId;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public PerResume getPerResume() {
		return perResume;
	}

	public void setPerResume(PerResume perResume) {
		this.perResume = perResume;
	}

	public ProjPtimeInfo getProjPtimeInfo() {
		return projPtimeInfo;
	}

	public void setProjPtimeInfo(ProjPtimeInfo projPtimeInfo) {
		this.projPtimeInfo = projPtimeInfo;
	}
}
