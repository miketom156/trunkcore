package com.job5156.core.bo.form.per;

import com.job5156.core.common.validator.EmailFormat;
import com.job5156.core.common.validator.MobileFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * <p></p>
 * Date:2015/5/16 9:44
 *
 * @author pzm
 * @version 1.0
 */
public class AppIrcRegisterFormBo extends RegisterBo {
    @NotBlank
    @EmailFormat
    private String username;              //用户名
    @NotBlank
    @Length(min = 6, max = 20)
    private String password;              //密码
    @NotBlank
    @Length(min = 6, max = 20)
    private String password1;             //确认密码
    @NotBlank
    private String name;                  //姓名
    @NotBlank
    @MobileFormat
    private String mobile;                //手机
    @NotBlank
    private String cardNum;               //身份证号码
    @NotBlank
    private String captcha;               //验证码
    @NotNull
    private Integer location;             //地区编码

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword1() {
        return password1;
    }
    public void setPassword1(String password1) {
        this.password1 = password1;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getCardNum() {
        return cardNum;
    }
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
    public String getCaptcha() {
        return captcha;
    }
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
    public Integer getLocation() {
        return location;
    }
    public void setLocation(Integer location) {
        this.location = location;
    }
}
