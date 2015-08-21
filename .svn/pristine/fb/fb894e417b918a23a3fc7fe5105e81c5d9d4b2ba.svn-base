package com.job5156.webapp.model.com;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @ClassName: ComBusinessLicense
 * @Description: 公司企业营业执照实体类
 * @date 2015-6-16
 *
 */

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComBusinessLicense implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer           id;					// 主键ID
	private Integer           comId;                // 企业ID
	private String            uploadUri;            // 企业营业执照图片上传路径
	private Date              uploadDate;           // 企业营业执照上传时间
	private Date              checkDate;            // 企业营业执照审核时间
	private String            checkEditor;          // 企业营业执照审核人
	private Integer           state;                // 审核状态 0：未审核，1：审核通过，-1：审核未通过
	private Integer           passFlag;				//曾经审核通过的标志，为了判断不重复更新发布数目+3：0.未通过 1.通过了

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comId", insertable = false, updatable = false)
	private ComInfo comInfo;                        //相关企业

	public ComInfo getComInfo() {
		return comInfo;
	}
	public void setComInfo(ComInfo comInfo) {
		this.comInfo = comInfo;
	}
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

	public String getUploadUri() {
		return uploadUri;
	}
	public void setUploadUri(String uploadUri) {
		this.uploadUri = uploadUri;
	}

	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckEditor() {
		return checkEditor;
	}
	public void setCheckEditor(String checkEditor) {
		this.checkEditor = checkEditor;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getPassFlag() {
		return passFlag;
	}
	public void setPassFlag(Integer passFlag) {
		this.passFlag = passFlag;
	}


}
