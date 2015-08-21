package com.job5156.core.entity.sys.customer;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * 客户及职位分月和周统计主动投递简，企业购买简，后台推荐简历的数量
 * User: Administrator
 * Date: 14-6-13
 * Time: 上午10:58
 */

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysResumeStat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**
     * 主键ID
     */
    private Integer id;
    /**
     *  企业id
     */
    private Integer comId;
    /**
     *  职位ID
     */
    private Integer positionId;

    /**
     *  主动投递简数量
     */
    private Integer deliveryQty;
    /**
     *  企业购买简数量
     */
    private Integer buyQty;
    /**
     * 后台推荐简历数量
     */
    private Integer recommendQty;

    /**
     * 第几周的统计数据（一年中的第X周）
     */
    private Integer countWeek;
    /**
     * 第几月的统计数据（一年中的第X个月）
     */
    private Integer countMonth;
    /**
     * 统计的年份
     */
    private Integer countYear;

    /**
     * 统计的类型,1为按月统计，2为按周统计
     */
    private Integer countType;
    /**
     *创建日期
     */
    private Date createDate;

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

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getDeliveryQty() {
        return deliveryQty;
    }

    public void setDeliveryQty(Integer deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    public Integer getBuyQty() {
        return buyQty;
    }

    public void setBuyQty(Integer buyQty) {
        this.buyQty = buyQty;
    }

    public Integer getRecommendQty() {
        return recommendQty;
    }

    public void setRecommendQty(Integer recommendQty) {
        this.recommendQty = recommendQty;
    }

    public Integer getCountWeek() {
        return countWeek;
    }

    public void setCountWeek(Integer countWeek) {
        this.countWeek = countWeek;
    }

    public Integer getCountMonth() {
        return countMonth;
    }

    public void setCountMonth(Integer countMonth) {
        this.countMonth = countMonth;
    }

    public Integer getCountYear() {
        return countYear;
    }

    public void setCountYear(Integer countYear) {
        this.countYear = countYear;
    }

    public Integer getCountType() {
        return countType;
    }

    public void setCountType(Integer countType) {
        this.countType = countType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
