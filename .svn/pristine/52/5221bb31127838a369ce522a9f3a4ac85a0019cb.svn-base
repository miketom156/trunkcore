package com.job5156.core.entity.com.talebase;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 产品列表对应的报告类型
 * 
 * @author 夏安定
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class TalebaseComTestProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer productId;// 产品ID
	private String productName;// 产品名称
	private String productLogo;// 产品Logo
	private String productNumber;// 产品编号
	private String productIntro;// 产品介绍
	private String productDesc;// 产品描述
	private String notice;// 注意事项
	private int quizCount;// 题目数量

	@OneToMany(mappedBy = "comTestProduct", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<TalebaseComTestProRepType> comTPRTList;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductLogo() {
		return productLogo;
	}

	public void setProductLogo(String productLogo) {
		this.productLogo = productLogo;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductIntro() {
		return productIntro;
	}

	public void setProductIntro(String productIntro) {
		this.productIntro = productIntro;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public int getQuizCount() {
		return quizCount;
	}

	public void setQuizCount(int quizCount) {
		this.quizCount = quizCount;
	}

	public List<TalebaseComTestProRepType> getComTPRTList() {
		return comTPRTList;
	}

	public void setComTPRTList(List<TalebaseComTestProRepType> comTPRTList) {
		this.comTPRTList = comTPRTList;
	}
}
