package com.job5156.core.bo;

/**
 * <p></p>
 * Date:2015/5/14 17:16
 *
 * @author pzm
 * @version 1.0
 */
public enum BizRetCode {
    /**
     * 操作成功
     */
    SUCCESS("success", "操作成功"),
    /**
     * 操作失败
     */
    FAIL("fail", "操作失败"),
    /**
     * 登录失败，请检查用户名和密码
     */
    LOGIN_USER_OR_PASSWORD_FAIL("login_user_or_password_fail", "登录失败，请检查用户名和密码"),
    /**
     * 登录失败
     */
    LOGIN_FAIL("login_fail", "登录失败"),
    /**
     * 请先登录再进行应聘操作
     */
    UNLOGIN_APPLY("unlogin_apply", "请先登录再进行应聘操作"),
    /**
     * 用户未登录或超时退出，请重新登录
     */
    UNLOGIN("unlogin", "用户未登录或超时退出，请重新登录"),
    /**
     * 请选择应聘该职位的简历
     */
    RESUME_NO_SELECT("resume_no_select", "请选择应聘该职位的简历"),
    /**
     * 简历信息不存在
     */
    RESUME_NOT_EXIST("resume_not_exist", "简历信息不存在"),
    /**
     * 当前登录用户没有这份简历
     */
    RESUME_NOT_OWNER("resume_not_owner", "当前登录用户没有这份简历"),
    /**
     * 用户信息不存在
     */
    USER_NOT_EXIST("user_not_exist", "用户信息不存在"),
    /**
     * 该职位信息不存在
     */
    POSITION_NOT_EXIST("position_not_exist", "该职位信息不存在"),
    /**
     * 该企业不存在
     */
    COM_NOT_EXIST("com_not_exist", "该企业不存在"),
    /**
     * 您的简历不完整，请先补充必填信息再执行操作${msg}
     */
    RESUME_INCOMPLATE("resume_incomplate", "您的简历不完整，请先补充必填信息再执行操作${msg}"),
    /**
     * 您的简历未审核通过，请提供真实的简历信息
     */
    RESUME_UNAUDIT("resume_unaudit", "您的简历未审核通过，请提供真实的简历信息"),
    /**
     * 该职位已经停止招聘了
     */
    POSITION_STOP("position_stop", "该职位已经停止招聘了"),
    /**
     * 应聘失败！此职位设置了应聘要求，很抱歉您的简历不符合该要求。
     */
    RESUME_UNMATCH("resume_unmatch", "应聘失败！此职位设置了应聘要求，很抱歉您的简历不符合该要求。"),
    /**
     * 应聘失败！企业设置对您的简历进行了过滤，请选择其他企业。
     */
    RESUME_BLACK("resume_black", "应聘失败！企业设置对您的简历进行了过滤，请选择其他企业。"),  //#1712
    /**
     * 同一职位30天内不能应聘超过2次
     */
    WARN_TWICE("warn_twice", "同一职位30天内不能应聘超过2次"),
    /**
     * 今天已经应聘过了,请不要重复提交!
     */
    WARN_DUPLICATE("warn_duplicate", "今天已经应聘过了,请不要重复提交!"),
    /**
     * 二次应聘
     */
    SECOND_APPLY("second_apply", "二次应聘"),
    /**
     * 一天内应聘不能超过500次
     */
    APPLY_LIMIT_500_ONEDAY("apply_limit_500_oneday", "一天内应聘不能超过500次"),
    /**
     * 3个月内应聘同一家公司最多只能应聘该公司的3个职位(取消2014-05-27）
     */
    LIMIT_BY_POSTION("limit_by_postion", "3个月内应聘同一家公司最多只能应聘该公司的3个职位"),
    /**
     * 您的求职名片未完善，暂不能应聘
     */
    CARD_NOT_PERFECT("card_not_perfect", "您的求职名片未完善，暂不能应聘"),
    /**
     * 本职位不接收名片应聘，您可以进一步完善简历以升级为正式简历。
     */
    NOT_ACCPET_CARD("not_accpet_card", "本职位不接收微名片应聘，您可以进一步完善简历以升级为正式简历"),
    /**
     * 你的手机号未验证，暂不能应聘
     */
    NOT_VERIFY_MOBILE("not_verify_mobile", "你的手机号未验证，暂不能应聘"),
    /**
     * 两次输入的密码不相同
     */
    PASSWORD_NOT_SAME("password_not_same", "两次输入的密码不相同"),
    /**
     * 邮件地址已存在
     */
    EMAIL_EXIST("email_exist", "邮件地址已存在"),
    /**
     * 手机号码已存在
     */
    MOBILE_EXIST("mobile_exist", "手机号码已存在"),
    /**
     * 验证码错误
     */
    CAPTCHACODE_NOT_RIGHT("captchacode_not_right", "验证码错误"),
    /**
     * 用户名不能为空
     */
    USER_NAME_EMPTY("user_name_empty", "用户名不能为空"),
    /**
     * 密码不能为空
     */
    PASSWORD_EMPTY("password_empty", "密码不能为空"),
    /**
     * 手机不能为空
     */
    MOBILE_EMPTY("mobile_empty", "手机不能为空"),
    /**
     * 手机号码格式不正确
     */
    MOBILE_NOT_VALID("mobile_not_valid", "手机号码格式不正确"),
    /**
     * 用户类型不正确
     */
    USER_TYPE_NOT_RIGHT("user_type_not_right", "用户类型不正确"),
    /**
     * 密码长度需为6~20位
     */
    PASSWORD_LENGTH_NOT_VALID("password_length_not_valid", "密码长度需为6~20位"),
    /**
     * Email地址格式不正确
     */
    EMAIL_NOT_VALID("email_not_valid", "Email地址格式不正确"),
    /**
     * 输入的信息不正确，请重新输入。
     */
    INPUT_INFO_NOT_RIGHT("input_info_not_right", "输入的信息不正确，请重新输入。"),
    /**
     * 该账号已经注册，请登录并绑定。
     */
    ACCOUNT_EXIST_WECHAT("account_exist_wechat", "该账号已经注册，请登录并绑定。"),
    /**
     * 已到达底部。
     */
    PAGE_END("page_end", "已到达底部。"),
    /**
     * 参数错误。
     */
    PARAM_ERROR("param_error", "参数错误。"),
    /**
     * 取不出更多数据。
     */
    NO_MORE_DATA("no_more_data", "取不出更多数据。"),
    /**
     * 检查没有发现错误
     */
    NOT_ERROR("not_error","");

    private String retCode;
    private String msg;

    public String getRetCode() {
        return retCode;
    }

    public String getMsg() {
        return msg;
    }

    private BizRetCode(String retCode, String msg) {
        this.retCode = retCode;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}