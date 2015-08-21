package com.job5156.core.entity.sys.logo;

import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <P>
 * 后台上传的LOGO图片
 * </P>
 * 
 * @author leo
 * 
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class SysLogo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; 			// 主键ID
	@NotNull(message = "{sys.logo.uploadId.is.notempty}")
	private Integer uploadId;		// 上传记录ID
	@NotNull(message = "{sys.logo.logoSizeId.is.notempty}")
	private Integer logoSizeId; 	// LOGO尺寸ID
	@NotNull(message = "{sys.logo.comId.is.notempty}")
	private Integer comId; 			// 公司ID
	private String comName;			// 公司名称
	private String descript; 		// LOGO描述
	@NotBlank(message = "{sys.logo.upload.fail}")
	private String logoPath; 		// 保存路径
	@NotBlank(message = "{sys.logo.upload.fail}")
	private String logoName;		// LOGO名称
	private Integer logoType;		// LOGO类型[1-图片 2-FLASH]
	private Date createDate; 		// 创建日期
	private String createBy; 		// 创建人
	
	private SysLogoSize sysLogoSize;	// LOGO尺寸
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "logoSizeId", updatable = false, insertable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public SysLogoSize getSysLogoSize() {
		return sysLogoSize;
	}

	public void setSysLogoSize(SysLogoSize sysLogoSize) {
		this.sysLogoSize = sysLogoSize;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLogoSizeId() {
		return logoSizeId;
	}

	public void setLogoSizeId(Integer logoSizeId) {
		this.logoSizeId = logoSizeId;
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

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public Integer getLogoType() {
		return logoType;
	}

	public void setLogoType(Integer logoType) {
		this.logoType = logoType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Integer getUploadId() {
		return uploadId;
	}

	public void setUploadId(Integer uploadId) {
		this.uploadId = uploadId;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}
}
