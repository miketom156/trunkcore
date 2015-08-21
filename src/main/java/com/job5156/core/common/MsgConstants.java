package com.job5156.core.common;


public class MsgConstants {

    // ========== 个人动态分组 category ========== Start
    public static final String PER_DYNAMIC_CATEGORY_INVITE = "invite";        //邀请面试类消息(线上)
    public static final String PER_DYNAMIC_CATEGORY_RECOMMEND = "recommend";  //推荐类消息(职位..)
    public static final String PER_DYNAMIC_CATEGORY_NOTIFY = "notify";        //系统消息
    public static final String PER_DYNAMIC_CATEGORY_INTERACTION= "interaction";  //交互类消息（企业与个人)
    public static final String PER_DYNAMIC_CATEGORY_JOBFAIR = "jobfair";      //招聘会消息
    public static final String PER_DYNAMIC_CATEGORY_ATTENTION = "attention";  //关注
    // ========== 个人动态分组 ========== End

    // ========== 个人动态分类 type ========== Start

    //PER_DYNAMIC_CATEGORY_INVITE
    public static final String PER_DYNAMIC_TYPE_COM_INTERVIEW_OFFICE = "com_interview_office";  // 邀请面试（到办公室）
    public static final String PER_DYNAMIC_TYPE_COM_CONSIDER_APPLY= "com_consider_apply";       // 企业正在考虑您的简历
    public static final String PER_DYNAMIC_TYPE_COM_REFUSE_APPLY= "com_refuse_apply";           // 企业已婉言拒绝了您应聘意向
    public static final String PER_DYNAMIC_TYPE_COM_INTERVIEW_VIDEO= "com_interview_video";     // 邀请面试（视频）

    //PER_DYNAMIC_CATEGORY_JOBFAIR
    public static final String PER_DYNAMIC_TYPE_COM_JOBFAIR_INVITE_WITH_POSITION = "com_jobfair_invite_with_position"; //招聘会邀请（有职位）
    public static final String PER_DYNAMIC_TYPE_COM_JOBFAIR_INVITE_WITHOUT_POSITION = "com_jobfair_invite_without_position"; //招聘会邀请（无职位）

    //PER_DYNAMIC_CATEGORY_RECOMMEND
    public static final String PER_DYNAMIC_TYPE_SYS_SUBSCRIBE_POSITION = "sys_subscribe_position";
    public static final String PER_DYNAMIC_TYPE_SYS_RECOMMEND_POSITION_AUTO = "sys_recommend_position_auto";
    public static final String PER_DYNAMIC_TYPE_SYS_RECOMMEND_POSITION_MANUAL = "sys_recommend_position_manual";

    //PER_DYNAMIC_CATEGORY_NOTIFY
    public static final String PER_DYNAMIC_TYPE_SYS_AUDIT_PASS = "sys_audit_pass";
    public static final String PER_DYNAMIC_TYPE_SYS_AUDIT_REJECT = "sys_audit_reject";
    public static final String PER_DYNAMIC_TYPE_SYS_WELCOME = "sys_welcome";
    public static final String PER_DYNAMIC_TYPE_SYS_LOGIN_REMIND = "sys_login_remind";
    public static final String PER_DYNAMIC_TYPE_SYS_REFRESH_REMIND = "sys_refresh_remind";
    public static final String PER_DYNAMIC_TYPE_SYS_JOBFAIR_REMIND = "sys_jobfair_remind";
    public static final String PER_DYNAMIC_TYPE_SYS_JOBFAIR_FORESHOW = "sys_jobfair_foreshow";
    public static final String PER_DYNAMIC_TYPE_COM_VIEW_RESUME = "com_view_resume";
    public static final String PER_DYNAMIC_TYPE_COM_VIEW_CONTACT = "com_view_contact";
    public static final String PER_DYNAMIC_TYPE_SYS_RETRUN_POINT_TO_COM = "sys_return_point_to_com"; //给企业返点

    //PER_DYNAMIC_CATEGORY_INTEACTION
    public static final String PER_DYNAMIC_TYPE_PER_ASK_COM_INFO = "per_ask_com_info";      //个人咨询企业信息
    public static final String PER_DYNAMIC_TYPE_PER_ASK_COM_POSITION = "per_ask_com_position"; //个人咨询企业职位
    public static final String PER_DYNAMIC_TYPE_INTERACTION_COM_REPLY_MSG = "com_reply_msg";   //企业对个人的回复
    public static final String PER_DYNAMIC_TYPE_PER_APPLY_COM_POS = "per_apply_com_pos";   //个人应聘企业职位

    //PER_DYNAMIC_CATEGORY_ATTENTION
    public static final String PER_DYNAMIC_TYPE_ATTENTION_COM_PUBLISH_POSITION = "com_publish_position";
    public static final String PER_DYNAMIC_TYPE_ATTENTION_COM_UPDATE_POSITION = "com_update_position";
    public static final String PER_DYNAMIC_TYPE_ATTENTION_COM_ATTEND_JOBFAIR = "com_attend_jobfair";
    // ========== 个人动态分类 ========== End




}