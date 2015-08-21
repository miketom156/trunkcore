/**
 * 
 */
package com.job5156.core.bo.form.per;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.job5156.core.common.validator.EmailFormat;

/**
 * @author lyh
 * @Description 
 * @date 2015年8月11日
 * 
 */
public class PerSendoutFormBo {

	 @NotNull
	    private Integer resumeId;
	    @NotBlank
	    @EmailFormat
	    private String email;
	    @NotBlank
	    private String comName;
	    @NotBlank
	    private String posName;
	    private boolean useLetter;
	    private String remark;
	    @NotNull
	    @Range(min = 0, max = 1)
	    private Integer emailFormat;
	    private String validCode;

	    public Integer getResumeId() {
	        return resumeId;
	    }

	    public void setResumeId(Integer resumeId) {
	        this.resumeId = resumeId;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getComName() {
	        return comName;
	    }

	    public void setComName(String comName) {
	        this.comName = comName;
	    }

	    public String getPosName() {
	        return posName;
	    }

	    public void setPosName(String posName) {
	        this.posName = posName;
	    }

	    public boolean isUseLetter() {
	        return useLetter;
	    }

	    public void setUseLetter(boolean useLetter) {
	        this.useLetter = useLetter;
	    }

	    public String getRemark() {
	        return remark;
	    }

	    public void setRemark(String remark) {
	        this.remark = remark;
	    }

	    public Integer getEmailFormat() {
	        return emailFormat;
	    }

	    public void setEmailFormat(Integer emailFormat) {
	        this.emailFormat = emailFormat;
	    }

	    public String getValidCode() {
	        return validCode;
	    }

	    public void setValidCode(String validCode) {
	        this.validCode = validCode;
	    }
	
}
