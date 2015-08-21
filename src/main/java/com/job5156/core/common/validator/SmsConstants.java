package com.job5156.core.common.validator;


public class SmsConstants {

    // ==========  企业邀请面试的手机短信模板   ========== Start
    public static final String SMS_TEMPLATE_COM_INVITE_OFFICE ="尊敬的{userName}：{companyName}邀请您面试（{position}），请于{date}到{address}，{busLine}{interviewNote}联系人：{contactPerson}，联系电话：{contactPhone}。[关注智通人才网微信服务号（job5156-com），求职面试更轻松！]";
    public static final String SMS_TEMPLATE_COM_INVITE_FAIR ="尊敬的{userName}：{companyName}邀请您{date}到{address}{marketNo}面试{posName}。凭短信免费入场，如该企业未到可面试该馆其他企业。";
    public static final String SMS_TEMPLATE_COM_REFUSE_BY_FAIR = "{userName}：{companyName}感谢您预约面试{posName}，经评估觉得您暂不符合我们的要求，再次感谢您的关注。";
    // ==========  企业邀请面试的手机短信模板   ========== End
    //给手机发送登录用的动态密码
    public static final String SMS_TEMPLATE_PER_DYNAMIC_CODE ="您获取的动态密码为：%s，请在%s分钟内使用否则失效，请勿泄露，如非本人获取请忽略。";
    //后台简历审核短信模板
    public static final String SMS_SYS_RESUME_AUDIT = "尊敬的用户{userName}，您好！{content}[关注智通人才网微信服务号（job5156-com），求职面试更轻松！]";
}
