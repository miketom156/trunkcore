package com.job5156.core.bo.form.per;

import com.job5156.core.common.validator.EmailFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p></p>
 * Date:2015/5/16 9:39
 *
 * @author pzm
 * @version 1.0
 */
public class PerRegisterFormBo extends RegisterBo {
    @NotBlank
    @EmailFormat
    private String username;
    @NotBlank
    @Length(min = 6, max = 20)
    private String password;
    @NotBlank
    @Length(min = 6, max = 20)
    private String password1;
    @NotBlank
    private String captcha;

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
    public String getCaptcha() {
        return captcha;
    }
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
