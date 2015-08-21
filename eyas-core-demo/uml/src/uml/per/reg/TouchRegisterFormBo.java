package uml.per.reg;

/**
 * <p></p>
 * Date:2015/5/16 9:43
 *
 * @author pzm
 * @version 1.0
 */
public class TouchRegisterFormBo extends RegisterBo{
    private String email;  //账号以及email地址
    private String mobile; //手机
    private String password;  //密码
    private String password1;  //重复密码
    private String captcha;    //随机验证码
    private String serverName;  //服务器域名
    private String verifyCode;            // (发到手机等)验证码
    private Integer areaCode; //注册的地区代码 OptionCityMap
    private Integer sysUserId;

    //以下为推广属性
    private Integer nodeId = 0;  //上线ID
    private String nodeSpreadKey;   //上线的推广KEY
    private String nodeMobile;      //上线的手机

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeSpreadKey() {
        return nodeSpreadKey;
    }

    public void setNodeSpreadKey(String nodeSpreadKey) {
        this.nodeSpreadKey = nodeSpreadKey;
    }

    public String getNodeMobile() {
        return nodeMobile;
    }

    public void setNodeMobile(String nodeMobile) {
        this.nodeMobile = nodeMobile;
    }
}
