package com.job5156.core.entity.irc;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 购买的招聘会产品
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class IrcServiceItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer           srvId;

    private Integer           comId;
    private String            comName;              // 企业名称
    private Long              orderId;              // 订单ID
    private Integer           orderItemId;          // 订单项ID
    private Integer           prodId;               // 产品ID
    private String            prodName;             // 产品名称
    private String            prodAttr;             // 产品属性
    private String            prodCate;             // 产品类别
    private Date              createTime;           // 创建时间
    private Date              serveTime;            // 服务时间
    private Date              completeTime;         // 完成时间
    private Integer           srvStatus;            // 服务状态
    private String            srvExtend;            // 服务项额外信息，目前只用来记录展位号
    private Integer           reserveNum;
    private String            srvEmpNo;             // 操作人，for CRM系统
    
    
    private transient String seoDomain = ""; //这个字段只在读取的时候进行设置，不加入数据库

    
    public Integer getSrvId() {
        return srvId;
    }

    public void setSrvId(Integer srvId) {
        this.srvId = srvId;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdAttr() {
        return prodAttr;
    }

    public void setProdAttr(String prodAttr) {
        this.prodAttr = prodAttr;
    }

    public String getProdCate() {
        return prodCate;
    }

    public void setProdCate(String prodCate) {
        this.prodCate = prodCate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getServeTime() {
        return serveTime;
    }

    public void setServeTime(Date serveTime) {
        this.serveTime = serveTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Integer getSrvStatus() {
        return srvStatus;
    }

    public void setSrvStatus(Integer srvStatus) {
        this.srvStatus = srvStatus;
    }

    public String getSrvExtend() {
        return srvExtend;
    }

    public void setSrvExtend(String srvExtend) {
        this.srvExtend = srvExtend;
    }

    public Integer getReserveNum() {
        return reserveNum;
    }

    public void setReserveNum(Integer reserveNum) {
        this.reserveNum = reserveNum;
    }

    public String getSrvEmpNo() {
        return srvEmpNo;
    }

    public void setSrvEmpNo(String srvEmpNo) {
        this.srvEmpNo = srvEmpNo;
    }
    public String getSeoDomain() {
		return seoDomain;
	}

	public void setSeoDomain(String seoDomain) {
		this.seoDomain = seoDomain;
	}
    

    //----------------Not POJO Method----------------



	public String getSrvExtendNo() {
        if(StringUtils.isNotBlank(ObjectUtils.toString(getSrvExtend()))){
            Map<String, String> extendMap = new Gson().fromJson(getSrvExtend(), new TypeToken<Map<String, String>>() {}.getType());
            if(extendMap != null && extendMap.containsKey("stallno")) {
                return extendMap.get("stallno");
            }
        }
        return "";
    }

    public String getPopularityStar() {
        int divideNum = 10;
        int count = getReserveNum();
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

}
