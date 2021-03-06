package com.job5156.core.entity.irc;

import com.google.common.collect.Lists;
import com.job5156.core.common.CommonEnum;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class IrcJobfair   implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GenericGenerator(name = "idGenerator", strategy = "assigned")
    @GeneratedValue(generator = "idGenerator")
    private Integer jfId;//招聘会ID
    private String jfName;//招聘会名称
    private Date jfBeginTime;//招聘会开始时间
    private Date jfExpireTime;//招聘会截止时间
    private String jfAddress;//招聘会地址
    private Integer jfArea;
    private Integer jfReserveNum;
    private Integer jfComNum;
    private String jfDesc;//招聘会描述
    private String jfComTips;
    private String jfPerTips;
    private String jfGps;
    private String jfBanner;
    private Integer jfCanReserve;
    private Integer jfTop;
    
    @NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name = "jfId", insertable = false, updatable = false)
	private IrcProd ircProd;                                //关联招聘会

    public Integer getJfId() {
		return jfId;
	}
	public void setJfId(Integer jfId) {
		this.jfId = jfId;
	}
	public String getJfName() {
		return jfName;
	}
	public void setJfName(String jfName) {
		this.jfName = jfName;
	}
	public Date getJfBeginTime() {
		return jfBeginTime;
	}
	public void setJfBeginTime(Date jfBeginTime) {
		this.jfBeginTime = jfBeginTime;
	}
	public Date getJfExpireTime() {
		return jfExpireTime;
	}
	public void setJfExpireTime(Date jfExpireTime) {
		this.jfExpireTime = jfExpireTime;
	}
	public String getJfAddress() {
		return jfAddress;
	}
	public void setJfAddress(String jfAddress) {
		this.jfAddress = jfAddress;
	}
	public Integer getJfArea() {
		return jfArea;
	}
	public void setJfArea(Integer jfArea) {
		this.jfArea = jfArea;
	}
	public Integer getJfReserveNum() {
		return jfReserveNum;
	}
	public void setJfReserveNum(Integer jfReserveNum) {
		this.jfReserveNum = jfReserveNum;
	}
	public Integer getJfComNum() {
		return jfComNum;
	}
	public void setJfComNum(Integer jfComNum) {
		this.jfComNum = jfComNum;
	}
	public String getJfDesc() {
		return jfDesc;
	}
	public void setJfDesc(String jfDesc) {
		this.jfDesc = jfDesc;
	}
	public String getJfComTips() {
		return jfComTips;
	}
	public void setJfComTips(String jfComTips) {
		this.jfComTips = jfComTips;
	}
	public String getJfPerTips() {
		return jfPerTips;
	}
	public void setJfPerTips(String jfPerTips) {
		this.jfPerTips = jfPerTips;
	}
	public String getJfGps() {
		return jfGps;
	}
	public void setJfGps(String jfGps) {
		this.jfGps = jfGps;
	}
	public String getJfBanner() {
		return jfBanner;
	}
	public void setJfBanner(String jfBanner) {
		this.jfBanner = jfBanner;
	}
	public Integer getJfCanReserve() {
		return jfCanReserve;
	}
	public void setJfCanReserve(Integer jfCanReserve) {
		this.jfCanReserve = jfCanReserve;
	}
	public Integer getJfTop() {
		return jfTop;
	}
	public void setJfTop(Integer jfTop) {
		this.jfTop = jfTop;
	}
	public IrcProd getIrcProd() {
		return ircProd;
	}
	public void setIrcProd(IrcProd ircProd) {
		this.ircProd = ircProd;
	}
	
	/**---------------非POJO方法---------------**/
    public String getPopularityStar() {
        int divideNum = 50;
        int count = getJfReserveNum();
        if(count < divideNum) {
            return "star1";
        } else if(count >= divideNum && count < divideNum * 2) {
            return "star2";
        } else if(count >= divideNum * 2 && count < divideNum * 3) {
            return "star3";
        } else if(count >= divideNum * 3 && count < divideNum * 4) {
            return "star4";
        } else if(count >= divideNum * 4) {
            return "star5";
        } else {
            return "star1";
        }
    }

    public String getJfDescABBR(){
        if(StringUtils.isNotBlank(getJfDesc())){
            return StringUtils.abbreviate(StringUtils.deleteWhitespace(getJfDesc().replaceAll("<[^>]*>", "")), 130);
        } else {
            return "";
        }
    }

    public List<String> getJfGpsList(){
        if(StringUtils.isNotBlank(getJfGps())) {
            return Lists.newArrayList(getJfGps().split("\\r\\n"));
        } else {
            return Lists.newArrayList();
        }
    }

    public List<String> getJfPerTipsList(){
        if(StringUtils.isNotBlank(getJfPerTips())) {
            return Lists.newArrayList(getJfPerTips().split("\\r\\n"));
        } else {
            return Lists.newArrayList();
        }
    }

    public List<String> getJfComTipsList(){
        if(StringUtils.isNotBlank(getJfComTips())) {
            return Lists.newArrayList(getJfComTips().split("\\r\\n"));
        } else {
            return Lists.newArrayList();
        }
    }
    
    /**
     * 
    * @Title getExpire
    * @Description 获取招聘会是否已过期（true=已过期，false=未过期）
    * @return
     */
    public boolean getIsExpire() {
    	//判断招聘会是否未过期
    	if(!new DateTime(getJfExpireTime()).isBeforeNow())
    		return false;
    	
    	return true;
    }
    
    /**
     * 
    * @Title getCanBuy
    * @Description 获取企业是否可以购买（预约）招聘会
    * @return
     */
    public boolean getCanBuy() {
    	//判断招聘会是否已发布
    	if(getIrcProd() != null && getIrcProd().getProdStatus() == CommonEnum.IrcPordStatus.PUBLISHED.getStatus()) {
    		//判断招聘会是否未过期且招聘会开始时间晚于等于当前日期
    		if(!new DateTime(getJfExpireTime()).isBeforeNow() && !new DateTime(getJfBeginTime()).isBeforeNow()) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    /**
     * 
    * @Title getCanReserve
    * @Description 获取个人是否可以预约招聘会
    * @return
     */
    public boolean getCanReserve() {
    	//判断是否可以接受预约
    	if(getJfCanReserve() == 1) {
    		//判断招聘会是否未过期
    		if(!new DateTime(getJfExpireTime()).isBeforeNow()) {
    			return true;
    		}
    	}
    	
    	return false;
    }

    /**
     * 获取招聘会当前状态，根据时间和是否可预约，可能返回的值有
     * 0 = canJoin ： 个人可以预约，企业可以购买
     * 1 = canBuy ： 个人不可预约，企业可以购买
     * 2 = start：已开始，个人不可预约，企业不可购买，倒计时显示已开始
     * 3 = expire ：过期，个人不可预约，企业不可购买，倒计时显示已开始，招聘会名称前显示已过期
     * @return
     */
    public Integer getJfState() {
        if(new DateTime(getJfExpireTime()).isBeforeNow()){
            return 3;
        }

        if(new DateTime(getJfExpireTime()).isAfterNow() && new DateTime(getJfBeginTime()).isBeforeNow()) {
            return 2;
        }

        if(new DateTime(getJfExpireTime()).isAfterNow() && new DateTime(getJfBeginTime()).isAfterNow()) {
            if(1 == getJfCanReserve()) {
                return 0;
            }
            return 1;
        }
        return 3;
    }
}
