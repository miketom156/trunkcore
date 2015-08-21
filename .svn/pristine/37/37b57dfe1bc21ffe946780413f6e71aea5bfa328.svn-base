package com.job5156.core.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.job5156.core.bo.mail.MailBo.MailServer;

import java.util.Map;

/**
 * 邮件发送常量配置
 * 
 * @author leo
 * 
 */
public class MailConstants {

    public static final String  MAIL_ADDRESS_SEPARATE_CHAR = ";";       //多个邮件地址分隔符
    public static final Integer MAIL_TYPE_SIMPLIFIED = 0;				//简体邮件
    public static final Integer MAIL_TYPE_TRADITIONAL = 1;				//繁体邮件
    
    public static final Integer MAIL_SEND_WAIT = 0;						//等待发送
    public static final Integer MAIL_SEND_SUCCESS = 1;					//发送成功
    public static final Integer MAIL_SEND_FAIL = 2;						//发送失败
    
	public static final Integer MAIL_SUBSCRIBE_YES = 1;						// 邮件订阅状态[已订阅]
	public static final Integer MAIL_SUBSCRIBE_NO = 0;						// 邮件订阅状态[未订阅]
    
    public static final Integer MAIL_TASK_PER_APPLY_JOB = 1; 			//个人应聘发送求职简历
    public static final Integer MAIL_TASK_PER_SEND_OUT = 2;				//个人外发简历
    public static final Integer MAIL_TASK_PER_CONNECT_REGISTER = 3;		//个人第三方注册
    public static final Integer MAIL_TASK_PER_FORGET_PASS = 4;			//个人忘记密码
    public static final Integer MAIL_TASK_PER_REGISTER = 5;				//个人注册
    public static final Integer MAIL_TASK_PER_MAIL_VALID = 6;			//个人邮箱确认
    public static final Integer MAIL_TASK_PER_RESERVE_POS = 7;			//个人预约职位
    public static final Integer MAIL_TASK_PER_RESERVE_COM = 8;			//个人预约企业
    
    public static final Integer MAIL_TASK_COM_REGISTER = 1001;			//企业注册
    public static final Integer MAIL_TASK_COM_SEND_FORWARD = 1002;		//企业转发简历
    public static final Integer MAIL_TASK_COM_INVITE_NOTIFY = 1003;		//企业邀请面试通知
    public static final Integer MAIL_TASK_COM_VIDEO_INVITE = 1004;		//企业视频面试
    public static final Integer MAIL_TASK_COM_VIDEO_NOTIFY = 1005;		//企业视频面试通知
    public static final Integer MAIL_TASK_COM_TEST_INVITE = 1006;		//企业测评邀请
    public static final Integer MAIL_TASK_COM_ASK_PER_SEND_RESUME = 1007; //企业请求个人发正式简历

    public static final Integer MAIL_TASK_ACT_AWARDS_NOTIFY = 1100;		//抽奖活动生成奖池通知
    public static final Integer MAIL_TASK_ACT_FAIR_2015_NOTIFY = 1101;	//制造业专题客户联系销售顾问

    public static final Integer MAIL_TASK_SYS_FEEDBACK_REPLY = 2001;	//投诉反馈回复
    public static final Integer MAIL_TASK_SYS_COM_RIGHT_AUDIT = 2002;	//企业会员开通-给企业发邮件
    public static final Integer MAIL_TASK_SYS_COM_RIGHT_AUDIT_SALER = 2008;	//企业会员开通-给招聘顾问发邮件 
    public static final Integer MAIL_TASK_SYS_POSITION_AUDIT = 2003;	//职位信息审查
    public static final Integer MAIL_TASK_SYS_RECOMMEND_PER_TO_COM = 2004;	//推荐人才给企业
    public static final Integer MAIL_TASK_SYS_RECOMMEND_POS_TO_PER = 2005;	//推荐职位给个人
    public static final Integer MAIL_TASK_SYS_COM_RETURN_POINTS = 2006;			//后台给企业返还点数-给企业发邮件
    public static final Integer MAIL_TASK_SYS_COM_RETURN_POINTS_SALER = 2007; 	//后台给企业返还点数-给招聘顾问发邮件
    public static final Integer MAIL_TASK_SYS_RESUME_AUDIT = 2009;	//简历信息审查
    public static final Integer MAIL_TASK_SYS_ADD_RESUME_REMIND = 2010;  //后台收录个人简历提醒
    
/*	public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND = 3001;		// 系统根据个人求职意向发送职位推荐
	public static final Integer MAIL_TASK_SYSTEM_POS_SUBSCRIBE = 3002;		// 系统根据职位搜索器发送职位推荐
	public static final Integer MAIL_TASK_SYSTEM_RESUME_VIEWED = 3003;		// 系统发送企业查看了个人简历邮件
	public static final Integer MAIL_TASK_SYSTEM_RES_SUBSCRIBE = 3004;		// 系统根据简历搜索器发送简历推荐
	public static final Integer MAIL_TASK_SYSTEM_COM_RECEIVE_RES_NUM_REMIND = 3005;	// 企业收到投递简历数量过少邮件提醒
	public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND_LARGE = 3006;	// 给1.5年内登录过的个人用户发职位推荐
	public static final Integer MAIL_TASK_SYSTEM_BIRTHDAY_WISH = 3007;		// 系统发送生日祝福邮件
	public static final Integer MAIL_TASK_SYSTEM_LONG_TERM_NO_LOGIN = 3008;	// 系统发送长期未登陆邮件
	public static final Integer MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT = 3009;	// 系统简历不完善提醒邮件*/

    /** 每一类分配使用连续的20个流水序号，退订时使用同类的第一个ID,统计时可用enum把同一区间归为一组 **/
    public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND = 3010;     // 系统根据个人求职意向发送职位推荐 [3010-3129]
    public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND_D1 = 3011;  // 系统根据个人求职意向发送职位推荐(1天未登陆)
    public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND_D3 = 3012;  // 系统根据个人求职意向发送职位推荐(3天未登陆)
    public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND_D7 = 3013;  // 系统根据个人求职意向发送职位推荐(7天未登陆)
    public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND_D15 = 3015; // 系统根据个人求职意向发送职位推荐(15天未登陆)

    public static final Integer MAIL_TASK_SYSTEM_POS_SUBSCRIBE = 3030; // 系统根据职位搜索器发送职位推荐 [3030-3049]

    public static final Integer MAIL_TASK_SYSTEM_RES_SUBSCRIBE = 3050; // 系统根据简历搜索器发送简历推荐 [3050-3069]
    public static final Integer MAIL_TASK_SYSTEM_COM_RECEIVE_RES_NUM_REMIND = 3070; // 企业收到投递简历数量过少邮件提醒
    public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND_LARGE = 3100;     // 给1.5年内未登录过的个人用户发职位推荐 [3100-3129]
    public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND_LARGE_P1 = 3101;  // 给1.5年内未登录过的个人用户发职位推荐(15-273天未登陆)
    public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND_LARGE_P2 = 3102;  // 给1.5年内未登录过的个人用户发职位推荐(274-540天未登陆)

    public static final Integer MAIL_TASK_SYSTEM_LONG_TERM_NO_LOGIN = 3130;      // 系统发送长期未登陆邮件 [3130-3149]
    public static final Integer MAIL_TASK_SYSTEM_LONG_TERM_NO_LOGIN_D30 = 3131;  // 系统发送长期未登陆邮件(30天) [3130-3149]
    public static final Integer MAIL_TASK_SYSTEM_LONG_TERM_NO_LOGIN_D90 = 3132;  // 系统发送长期未登陆邮件(90天) [3130-3149]
    public static final Integer MAIL_TASK_SYSTEM_LONG_TERM_NO_LOGIN_D180 = 3133; // 系统发送长期未登陆邮件(180天) [3130-3149]

    public static final Integer MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT = 3150;        // 系统简历不完善提醒邮件 [3150-3169]
    public static final Integer MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT_D1 = 3151;     // 系统简历不完善提醒邮件(1天)
    public static final Integer MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT_D15 = 3160;    // 系统简历不完善提醒邮件(15天)
    public static final Integer MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT_D30 = 3161;    // 系统简历不完善提醒邮件(30天)
    public static final Integer MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT_D90 = 3162;    // 系统简历不完善提醒邮件(90天)
    public static final Integer MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT_D180 = 3163;   // 系统简历不完善提醒邮件(180天)
    public static final Integer MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT_D360 = 3164;   // 系统简历不完善提醒邮件(360天)
    public static final Integer MAIL_TASK_SYSTEM_COMRIGHT_EXPIRE_D15 = 3165;       // 企业会员服务到期提前(15天)提醒

    public static final Integer MAIL_TASK_SYSTEM_RESUME_VIEWED = 3170;          // 系统发送企业查看了个人简历邮件 [3170-3189]
    public static final Integer MAIL_TASK_SYSTEM_BIRTHDAY_WISH = 3190;          // 系统发送生日祝福邮件
    public static final Integer MAIL_TASK_PER_LOGIN_FAILURE = 3210;             // 个人用户登录失败提醒找回密码
    public static final Integer MAIL_TASK_COM_LOGIN_DIFF_IP = 3230;             // 企业用户最近两次登录的IP不同
    public static final Integer MAIL_TASK_PER_NOT_RESUME = 3240;                // 个人用户没有填写简历
    public static final Integer MAIL_TASK_PER_LESS_APPLY_SEND_POS = 3250;       // 给投递简历过少的个人用户发职位推荐
    public static final Integer MAIL_TASK_PER_INTERVIEW_LESS = 3260;            // 个人用户收到面试邀请很少的提醒邮件
    public static final Integer MAIL_TASK_COM_SCHOOL_RECRUIT = 3270;            // 企业校园招聘ABC
    public static final Integer MAIL_TASK_COM_POS_EXPIRE = 3300;//企业职位到期提醒邮件
    public static final Integer MAIL_TASK_COM_USER_MODIFY = 3301;//企业中心－设置－修改账号信息邮件
    
    public static final Integer MAIL_TASK_SYSTEM_YZ_REMAND = 3401;//永州站点开通提醒邮件
    
    public static final Integer MAIL_TASK_SYSTEM_COM_POS_AUDIT_REMAND = 3501;//企业职位审核结果提醒邮件

    //使用第三方API发送的从4000开始
    public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND_WPAPI = 4000;// 使用[webpower]给1.5年内未登录过的个人用户发职位推荐
    public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND_WPAPI_P1 = 4001;// 使用[webpower]给(15-273)天未登录过的个人用户发职位推荐
    public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND_WPAPI_P2 = 4002;// 使用[webpower]给(273-540)天未登录过的个人用户发职位推荐
    public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND_WPAPI_P3 = 4003;// 使用[webpower]给(15-175)天未登录过的个人用户发职位推荐

    public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND_EYAPI_P1 = 4201;// 使用[EASEYE]给(15-175)天未登录过的个人用户发推荐职位
    public static final Integer MAIL_TASK_SYSTEM_POS_RECOMMEND_EYAPI_P2 = 4202;// 使用[EASEYE]给(175-540)天未登录过的个人用户发推荐职位
	
	public static final Integer MAIL_TASK_ACT_JOB_PLAN_SIGNUP = 7001;	//问卷调查活动报名
	
	public static final Integer MAIL_TASK_PROJ_IRC_PER_REGISTER = 8001;	//互动招聘个人简历注册
	
    public static final Integer MAIL_TASK_API_MOBILE_PER_APPLY_JOB = 9001;		//手机客户端个人发送职位申请
    public static final Integer MAIL_TASK_API_MOBILE_PER_BESPEAK_POS = 9002;	//手机客户端个人预约职位
    public static final Integer MAIL_TASK_API_MOBILE_PER_OUT_SEND = 9003;		//手机客户端个人外发简历
    public static final Integer MAIL_TASK_API_MOBILE_PER_FORGET_PASS = 9004;	//手机客户端个人找回密码
    public static final Integer MAIL_TASK_API_MOBILE_PER_USER_REGISTER = 9005;	//手机客户端个人注册
    
    public static final Integer MAIL_TASK_API_WEIXIN_PER_USER_REGISTER = 9050;  //微信注册个人账号

    public static final Integer MAIL_TASK_API_WEIXIN_WAIBAO_RETRIEVE_PASSWORD = 9060;  //外包网服务号找回密码
    
    public static final String UNSUBSCRIBE_PRIVATE_KEY = "mailback!s%&^#@";		// 邮件退订加密密钥
    
	/**系统订阅邮件配置，需要同步eyas-run项目配置**/
	public static final Map<Integer, Map<String, String>> mailTypeConfigMap = Maps.newHashMap();
	/**邮件任务类型**/
	public static final Map<Integer, String> sendMailTaskType = Maps.newHashMap();
	/**邮件任务类型映射邮件发送服务器，不配置采用默认的main，否则采用配置的服务器**/
	public static final Map<Integer, MailServer> mailServerMap = Maps.newHashMap();
	
	static {
		/**邮件任务配置邮件发送服务器 **/
		mailServerMap.put(MAIL_TASK_SYS_POSITION_AUDIT, MailServer.MAIL_SERVER_MAIN);
		mailServerMap.put(MAIL_TASK_SYS_COM_RETURN_POINTS_SALER,  MailServer.MAIL_SERVER_EXCHANGE);
		
		/**邮件订阅退订配置**/
		mailTypeConfigMap.put(MAIL_TASK_SYSTEM_POS_RECOMMEND, setSubscribeMap("email", "emailPosRecommend"));
		mailTypeConfigMap.put(MAIL_TASK_SYSTEM_RESUME_VIEWED, setSubscribeMap("email", "emailResumeRemind"));
		mailTypeConfigMap.put(MAIL_TASK_SYSTEM_BIRTHDAY_WISH, setSubscribeMap("email", "birthdayWish"));
		mailTypeConfigMap.put(MAIL_TASK_SYSTEM_LONG_TERM_NO_LOGIN, setSubscribeMap("email", "longTermNoLogin"));
		mailTypeConfigMap.put(MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT, setSubscribeMap("email", "resumeNotPerfect"));
		
		/**邮件任务类型**/
		sendMailTaskType.put(MAIL_TASK_PER_APPLY_JOB, "个人应聘发送求职简历");
		sendMailTaskType.put(MAIL_TASK_PER_SEND_OUT, "个人外发简历");
		sendMailTaskType.put(MAIL_TASK_PER_CONNECT_REGISTER, "个人第三方注册");
		sendMailTaskType.put(MAIL_TASK_PER_FORGET_PASS, "个人忘记密码");
		sendMailTaskType.put(MAIL_TASK_PER_REGISTER, "个人注册");
		sendMailTaskType.put(MAIL_TASK_PER_MAIL_VALID, "个人邮箱确认");
		sendMailTaskType.put(MAIL_TASK_PER_RESERVE_POS, "个人预约职位");
		sendMailTaskType.put(MAIL_TASK_PER_RESERVE_COM, "个人预约企业");
		
		sendMailTaskType.put(MAIL_TASK_COM_REGISTER, "企业注册");
		sendMailTaskType.put(MAIL_TASK_COM_SEND_FORWARD, "企业转发简历");
		sendMailTaskType.put(MAIL_TASK_COM_INVITE_NOTIFY, "企业邀请面试通知");
		sendMailTaskType.put(MAIL_TASK_COM_VIDEO_INVITE, "企业视频面试");
		sendMailTaskType.put(MAIL_TASK_COM_VIDEO_NOTIFY, "企业视频面试通知");
		sendMailTaskType.put(MAIL_TASK_COM_TEST_INVITE, "企业测评邀请通知");
		
		sendMailTaskType.put(MAIL_TASK_SYS_FEEDBACK_REPLY, "后台投诉反馈回复");
		sendMailTaskType.put(MAIL_TASK_SYS_COM_RIGHT_AUDIT, "后台企业会员开通[发送给企业]");
		sendMailTaskType.put(MAIL_TASK_SYS_COM_RIGHT_AUDIT_SALER, "后台企业会员开通[发送给招聘顾问]");
		sendMailTaskType.put(MAIL_TASK_SYS_POSITION_AUDIT, "后台职位信息审查");
		sendMailTaskType.put(MAIL_TASK_SYS_RECOMMEND_PER_TO_COM, "后台推荐人才给企业");
		sendMailTaskType.put(MAIL_TASK_SYS_RECOMMEND_POS_TO_PER, "后台推荐职位给个人");
		sendMailTaskType.put(MAIL_TASK_SYS_COM_RETURN_POINTS, "后台给企业返还点数[发送给企业]");
		sendMailTaskType.put(MAIL_TASK_SYS_COM_RETURN_POINTS_SALER, "后台给企业返还点数[发送给招聘顾问]");
        sendMailTaskType.put(MAIL_TASK_SYS_RESUME_AUDIT, "简历信息审查");
        sendMailTaskType.put(MAIL_TASK_SYS_ADD_RESUME_REMIND, "后台收录个人简历提醒");

		sendMailTaskType.put(MAIL_TASK_ACT_JOB_PLAN_SIGNUP, "问卷调查活动报名");
		sendMailTaskType.put(MAIL_TASK_PROJ_IRC_PER_REGISTER, "互动招聘个人简历注册");
		
		sendMailTaskType.put(MAIL_TASK_API_MOBILE_PER_APPLY_JOB, "手机客户端个人发送职位申请");
		sendMailTaskType.put(MAIL_TASK_API_MOBILE_PER_BESPEAK_POS, "手机客户端个人预约职位");
		sendMailTaskType.put(MAIL_TASK_API_MOBILE_PER_OUT_SEND, "手机客户端个人外发简历");
		sendMailTaskType.put(MAIL_TASK_API_MOBILE_PER_FORGET_PASS, "手机客户端个人找回密码");
		sendMailTaskType.put(MAIL_TASK_API_MOBILE_PER_USER_REGISTER, "手机客户端个人注册");
		sendMailTaskType.put(MAIL_TASK_API_WEIXIN_PER_USER_REGISTER, "微信注册个人账号");

        sendMailTaskType.put(MAIL_TASK_PER_LOGIN_FAILURE,"个人用户登录失败提醒找回密码");

        sendMailTaskType.put(MAIL_TASK_SYSTEM_POS_RECOMMEND,"系统根据个人求职意向发送职位推荐");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_POS_RECOMMEND_D1,"系统根据个人求职意向发送职位推荐(1天未登陆)");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_POS_RECOMMEND_D3,"系统根据个人求职意向发送职位推荐(3天未登陆)");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_POS_RECOMMEND_D7,"系统根据个人求职意向发送职位推荐(7天未登陆)");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_POS_RECOMMEND_D15,"系统根据个人求职意向发送职位推荐(15天未登陆)");

        sendMailTaskType.put(MAIL_TASK_SYSTEM_LONG_TERM_NO_LOGIN,"系统发送长期未登陆邮件");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_LONG_TERM_NO_LOGIN_D30,"系统发送长期未登陆邮件(30天)");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_LONG_TERM_NO_LOGIN_D90,"系统发送长期未登陆邮件(90天)");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_LONG_TERM_NO_LOGIN_D180,"系统发送长期未登陆邮件(180天)");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_LONG_TERM_NO_LOGIN_D180,"系统发送长期未登陆邮件(180天)");

        sendMailTaskType.put(MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT,"系统简历不完善提醒邮件");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT_D1,"系统简历不完善提醒邮件(1天)");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT_D15,"系统简历不完善提醒邮件(15天)");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT_D30,"系统简历不完善提醒邮件(30天)");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT_D90,"系统简历不完善提醒邮件(90天)");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT_D180,"系统简历不完善提醒邮件(180天)");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_RESUME_NOT_PERFECT_D360,"系统简历不完善提醒邮件(360天)");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_COMRIGHT_EXPIRE_D15,"企业会员服务到期提前(15天)提醒");

        sendMailTaskType.put(MAIL_TASK_SYSTEM_POS_RECOMMEND_WPAPI,"使用[webpower]给1.5年内未登录过的个人用户发职位推荐");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_POS_RECOMMEND_WPAPI_P1,"使用[webpower]给1.5年内未登录过的个人用户发职位推荐(15-273天)");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_POS_RECOMMEND_WPAPI_P2,"使用[webpower]给1.5年内未登录过的个人用户发职位推荐(274-540天)");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_POS_RECOMMEND_WPAPI_P3,"使用[webpower]给1.5年内未登录过的个人用户发职位推荐(15-175天)");

        sendMailTaskType.put(MAIL_TASK_SYSTEM_POS_RECOMMEND_EYAPI_P1,"使用[EASEYE]给(15-175)天未登录过的个人用户发推荐职位");
        sendMailTaskType.put(MAIL_TASK_SYSTEM_POS_RECOMMEND_EYAPI_P2,"使用[EASEYE]给(175-540)天未登录过的个人用户发推荐职位");

        sendMailTaskType.put(MAIL_TASK_PER_NOT_RESUME,"个人用户没有填写简历");
        sendMailTaskType.put(MAIL_TASK_PER_LESS_APPLY_SEND_POS,"给投递简历过少的个人用户发职位推荐");
        sendMailTaskType.put(MAIL_TASK_PER_INTERVIEW_LESS,"个人用户收到面试邀请很少的提醒邮件");
        sendMailTaskType.put(MAIL_TASK_COM_SCHOOL_RECRUIT,"企业校园招聘ABC");
        sendMailTaskType.put(MAIL_TASK_COM_POS_EXPIRE, "企业职位到期提醒邮件");
        sendMailTaskType.put(MAIL_TASK_COM_USER_MODIFY, "企业中心－设置－修改账号信息邮件");
        
        sendMailTaskType.put(MAIL_TASK_SYSTEM_YZ_REMAND, "永州站点开通提醒邮件");
        
        sendMailTaskType.put(MAIL_TASK_SYSTEM_COM_POS_AUDIT_REMAND, "企业职位审核结果提醒邮件");

	}
	
    public static Map<String, String> setSubscribeMap(final String mainType, final String subType) {
        return ImmutableMap.of(
            "mainType", mainType,
            "subType", subType
        );
    }
    
}
