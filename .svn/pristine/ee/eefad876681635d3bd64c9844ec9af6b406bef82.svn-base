package com.job5156.core.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MANAGE_PAGE_SIZE = 25; //管理中心的分页数量
    public static final int HUMAN_PAGE_SIZE = 5; //人力知本官网历届学习回顾及资料下载分页数量
    public static final int DEFAULT_DELETE_FLAG = -1; //这个常量代表了列表假删除的默认状态，现设定-1为假删除的默认值
    public static final long DEFAULT_FILE_UPLOAD_SIZE = 1 * 1024 * 1024; // 1MB = 1048576 Bytes
    public static final String BAIDU_API_KEY = "IDyIxBcgh7zhEe9u7owZINPS" ; //百度开发者 API key

    public static final int EJB_ALIVE_CHECK_TIME = 10 * 60 * 1000; //10分钟

    public static final int SEARCH_PAGE_SIZE = 20;
    public static final String EYAS_ACTIVE_TIME = "2014-01";
    public static final String EYAS_IMAGE_URL = "http://img.job5156.com";
    public static final String FILTER_KEYWORD_FILENAME = "filter-keyword.txt"; //过滤关键字文件名

    public static final String PATTERN_EMAIL = "^([a-zA-Z]|\\d)|(([a-zA-Z]|\\d)([a-zA-Z]|\\d|-|\\.|_|~)*([a-zA-Z]|\\d))@((([a-zA-Z]|\\d)|(([a-zA-Z]|\\d)([a-zA-Z]|\\d|-|\\.|_|~)*([a-zA-Z]|\\d)))\\.)+(([a-zA-Z])|(([a-zA-Z])([a-zA-Z]|\\d|-|\\.|_|~)*([a-zA-Z])))$";
    public static final String PATTERN_MOBILE = "1[3-8]\\d{9}";
    public static final String PATTERN_PHONE = "^((\\+?[0-9]{2,4}\\-[0-9]{3,4}\\-)|([0-9]{3,4}\\-))?([0-9]{7,8})(\\-[0-9]+)?$";
    public static final String PATTERN_DATE = "(19|20)\\d{2}-(0?\\d|1[012])-(0?\\d|[12]\\d|3[01])";
    public static final String PATTERN_DATETIME = "(19|20)\\d{2}-(0?\\d|1[012])-(0?\\d|[12]\\d|3[01]) (\\d{2}:\\d{2}:\\d{2})";
    public static final String PATTERN_IP = "^[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}$";
    public static final String PATTERN_URL = "([^//]*?\\.(com|cn|net|org|biz|info|cc|tv))[/]";

    public static final String WEIXIN_TOKEN = "chtone";
    public static final String WEIXIN_APP_ID= "wx59635c9f0a4c26a6";
    public static final String WEIXIN_APP_SECRET = "b3fec6d82b5a14bab4de0a8a71e85e3c";
    public static final String WEIXIN_API_GETACESSTOKEN ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"; //获取accesstoken的grant_type是固定的这个参数
    public static final String WEIXIN_API_GETACESSTOKEN_EMBS ="http://192.168.2.240/embs/weuser/session/getAccessToken"; //从微信服务号外包方提供的接口获取accesstoken
    public static final String WEIXIN_API_SEND_TEXT_MSG_EMBS ="http://192.168.2.240/embs/weuser/session/pushWechatMessage"; //从微信服务号外包方提供接口发送message
    public static final String WEIXIN_API_GETQRCODE_TICKET_PREFIX ="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="; //其他参数以post方式提交
    public static final String WEIXIN_API_GETQRCODE_PREFIX ="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="; //获取加入后面的ticket参数
    public static final String WEIXIN_API_USERLIST_PREFIX ="https://api.weixin.qq.com/cgi-bin/user/get"; //获取用户列表
    public static final String WEIXIN_API_SEND_TEXT_MESSAGE_PREFIX ="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="; //其他参数以post方式提交
    public static final String WEIXIN_API_GET_JS_API_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
    public static final long WEIXIN_QRCODE_EXPIRE_TIME = (10+1)*60;//过期时间为十分钟 ,加1分钟是否知网络传输等消耗的时间导致页面显示未过期的二维码无效
    public static final String WEIXIN_QRCODE_REIDS_KEY = "weixin:qrcode:connect:";
    public static final String WEIXIN_JS_API_TICKET_REIDS_KEY = "weixin:jsApiTicket";//用于保存当前的jsApiTicket
    public static final String WEIXIN_LOCK_JS_API_TICKET_REIDS_KEY = "weixin:lock:jsApiTicket";//用作获取jsApiTicket的乐观锁
    public static final long WEIXIN_LOCK_JS_API_TICKET_EXPIRE_TIME = 60;//获取jsApiTicket的乐观锁过期时间为1分钟
    public static final long WEIXIN_JS_API_TICKET_EXPIRE_TIME = (115)*60;//jsApiTicket过期时间为115分钟

    //微信接口返回消息
    public static final int WEIXIN_RETURN_CODE_SUCCESS = 1;
    public static final int WEIXIN_RETURN_CODE_FAILURE = 0;
    public static final int WEIXIN_RETURN_CODE_UNBIND = -1;
    public static final int WEIXIN_RETURN_CODE_UNSIGNED = -2;

    //微信统计类型
    public static final int WEIXIN_COUNT_TYPE_FOLLOWER = 1;//关注者数量
    
    //游客订阅
    public static final Integer MAIL_TASK_VISITOR_SUBSCRIBE = 3400;

    /**
     * 由于原来的搜索器做了5页的缓存，所以导致如果不乘除此数的话，搜索结果翻页的时候结果会不准确
     * 为什么不作缓存，这是为了将搜索的逻辑更简化
     * 以前获取了这个缓存还要放到页面上，每次翻页将上次搜索的id带过去，后期维护容易造成混淆
     * 现在EJB搜索的负载不高，所以完全可以不作缓存
     * 但是由于尽量不修改SearchEngine的内容，所以这里取默认的buffer页面数
     * 使用方法是对每次搜索的条件param中的pageSize / 5, pageNo * 5
     * 当SearchEngine的buffer取消的时候这里也需要取消
     */
    public static final int SEARCH_BUFFER_PAGE_NUM= 5;

    public static final String SYS_LOGIN_AUTH_TOKEN = "chitonejob5156webadmin";    //后台登陆认证串

    public static final String COMMAND = "command";

    public static final String PER_LOGIN_REF_URL = "per_login_ref_url"; //用来将登录前的URL记录在Session，以便登录成功后自动返回该页
    public static final String COM_LOGIN_REF_URL = "com_login_ref_url"; //用来将登录前的URL记录在Session，以便登录成功后自动返回该页

    public static final String TASK_SYNC_START_FLAG = "task:sync:start:flag"; //记录是否执行同步程序的标志，Set类型，只有在这个set里面的进程才会运行
    public static final String TASK_SYNC_INIT_BEGIN_STEP_PREFIX = "task:sync:init:begin:step:"; //记录初始化的搜索同步，某个进程开始的ID（+进程名）
    
    public static final String ACT_RESUMECONTEST_REDISKEY = "act:resumeContest:jingzhou:upload:userid";//荆州简历大赛
    /**
     * 为了解决网络职位和互动招聘的进程竞争导致同步失败的问题，增加redis执行的锁机制
     * 当此锁存在时，新的职位同步进程，不管是网络还是互动招聘的，直接跳过同步
     * 当锁的启动进程结束时，释放锁（remove）
     * 同时此锁本身设置500秒的生存期（redis expire），这样就算进程执行失败，也能保证锁会被正常释放
     * key的内容为（启动时间+执行的进程名），类型为String
     */
    public static final String TASK_POSITION_SYNC_LOCK = "task:position:sync:lock";
    public static final int TASK_POSITION_SYNC_LOCK_TIME = 500;

    // ========== 混淆代码 ========== Start
    public static final String VALID_CODE_LOGIN_COM = "COM2013";
    public static final String VALID_CODE_LOGIN_PER = "PER2013";
    public static final String VALID_CODE_LOGIN_SYS = "SYS2013";
	// ========== 混淆代码 ========== End

    // ========== Cookie Key ========== Start
    public static final String COOKIE_KEY_COM = "com";
    public static final String COOKIE_KEY_PER = "per";
    public static final String COOKIE_KEY_SYS = "sys";
    public static final String COOKIE_KEY_CITY = "activeCity";
    public static final String COOKIE_GROUP_KEY_PVCOUNT = "pvcount";
    public static final String COOKIE_KEY_UVCOOKIE = "uvcookie"; //访客永久id
    public static final String COOKIE_KEY_JSCOOKIE = "jscookie"; //访客单次访问id
    public static final String COOKIE_KEY_ORGINFLAG = "orginflag"; //首次访问标识
    public static final String COOKIE_KEY_ACCESSFLAG = "accessflag"; //二次访问标识
	// ========== Cookie Key ========== End

    public static final String NEW_UVID_IN_REQUEST_ATTR = "new_uvid_in_request_attr"; //访客永久id
    public static final String NEW_JSID_IN_REQUEST_ATTR = "new_jsid_in_request_attr"; //访客单次访问id

	// ========== Redis Key ========== Start
    public static final String REDIS_KEY_INDEX_RECENT_POSITION_INFO = "index:recent:position:info"; //企业登录信息
    public static final String REDIS_KEY_SITE_IRC_INDEX_INFO_PREFIX = "site:irc:index:info:"; //分站点首页互动招聘信息（+站点ID）
    public static final String REDIS_KEY_SITE_IRC_INDEX_INFO_LIST_PREFIX = "site:irc:index:info:list:"; //分站点首页互动招聘信息列表（+站点ID）

    public static final String REDIS_KEY_SITE_NEW_POSITION_LIST = "site:new:position:list:2014:"; // 首页搜索最新职位列表 + 站点名 (2014新版)
    public static final String REDIS_KEY_SITE_HOT_POSITION_LIST = "site:hot:position:list:2014:"; // 首页搜索热门职位列表 + 站点名 (2014新版)
    public static final String REDIS_KEY_SITE_GUEST_LIKE_POSITION_LIST = "site:guest:like:position:list:2014:"; // 首页搜索猜您喜欢职位列表 + 站点名(2014新版)
    public static final String REDIS_KEY_TOUCH_GUEST_LIKE_KEYWORD = "touch:guest:like:keyword"; // 触屏版的搜索keyword


    public static final String REDIS_KEY_COM_LOGIN_INFO_PREFIX = "com:login:info:"; //企业登录信息（+cookie）
    public static final String REDIS_KEY_PER_LOGIN_INFO_PREFIX  = "per:login:info:"; //个人登录信息（+cookie）
    public static final String REDIS_KEY_SYS_LOGIN_INFO_PREFIX  = "sys:login:info:"; //后台登录信息（+cookie）
    public static final String REDIS_KEY_TOUCH_PER_LOGIN_INFO_PREFIX  = "per:login:touch:info:"; //个人登录信息（+cookie）

    public static final String REDIS_KEY_COM_LOGIN_CHECK_PREFIX = "com:login:check:"; //企业单点登录判断（+userid）
    public static final String REDIS_KEY_PER_LOGIN_CHECK_PREFIX = "per:login:check:"; //个人单点登录判断（+userid）
    public static final String REDIS_KEY_SYS_LOGIN_CHECK_PREFIX = "sys:login:check:"; //后台单点登录判断（+userid）

    public static final String REDIS_KEY_PER_LOGIN_DYNAMIC_CODE_PREFIX = "per:login:dynamic:check:"; //个人动态码登录判断（+userid）

    public static final String REDIS_KEY_PER_MAIL_VALIDATION_PREFIX = "per:mail:validation:"; //个人邮件验证（+validcode）
    public static final String REDIS_KEY_PER_SMS_VALIDATION_PREFIX = "per:sms:validation:"; //个人手机验证（+validcode）
    public static final String REDIS_KEY_PER_MAIL_VALIDATION_IS_SEND_PREFIX = "per:mail:validation:is:send:"; //个人邮件验证是否已经发送（+id）
    public static final String REDIS_KEY_PER_SMS_VALIDATION_IS_SEND_PREFIX = "per:sms:validation:is:send:"; //个人手机验证是否已经发送（+id）
    public static final String REDIS_KEY_PER_LOGIN_DYNAMIC_CODE_VALIDATION_IS_SEND_PREFIX = "per:dynamic:code:validation:is:send:"; //个人动态码是否已经发送（+id）

    public static final String REDIS_KEY_PER_DYNAMIC_INVITE_PREFIX = "per:dynamic:invite:"; //个人动态信息（邀请面试+查看记录） 后面跟 （+userid），保存为json格式的String类型

    public static final String REDIS_KEY_SEARCH_POS_HOT_KEY = "search:pos:hot:key"; //热门搜索的职位大类（职位大类ID:名字），有序list
    public static final String REDIS_KEY_SEARCH_POS_HOT_KEY_PREFIX = "search:pos:hot:key:"; //热门搜索（+0或+大类ID），有序list

    public static final String REDIS_KEY_SYNC_QUEUE_POSITION = "sync:queue:position"; //待同步职位队列（职位id列表）
    public static final String REDIS_KEY_SYNC_QUEUE_IRC_POSITION = "sync:queue:irc_position";//待同步招聘会职位队列（招聘会职位id列表）
    public static final String REDIS_KEY_SYNC_QUEUE_RESUME = "sync:queue:resume"; //待同步简历队列（简历id列表）
    public static final String REDIS_KEY_SYNC_QUEUE_COMPANY = "sync:queue:company"; //待同步简历队列（简历id列表）
    public static final String REDIS_KEY_SYNC_QUEUE_EXPORT_RESUME = "sync:queue:export_resume"; //待同步导出简历队列（简历id列表）

    public static final String REDIS_KEY_PER_INDEX_READ_TIME_PREFIX = "per:index:read:time:"; //个人首页动态5个栏目的上次读取时间（+invite, +recommend, +message, +fair, +company）再+:userid，保存的为时间的String）
    public static final String REDIS_KEY_COM_INDEX_READ_TIME_PREFIX = "com:index:read:time:"; //企业首页动态2个栏目的上次读取时间（+message, +fair）再+:comid，保存的为时间的String）

    public static final String REDIS_KEY_GENERATE_FILE_PREFIX = "generate:file:"; //网站后台及后台进程生成的文件内容（+filename，目录直接通过配置文件取出）

    public static final String REDIS_KEY_COM_MSG_SEND_LIST_PREFIX = "com:msg:send:list:"; //互动招聘/E聘通系统中，对外发送的短信最大数量（+企业ID:DayOfYear）

    public static final String REDIS_KEY_SYS_WEBSITE = "sys:website:list:"; //后台分站点信息
    public static final String REDIS_KEY_PVCOUNT_ACCESS_LIST = "pvcount:access:list";	  //统计系统数据采集存放PV数据
    public static final String REDIS_KEY_PVCOUNT_ACCESSIP_LIST = "pvcount:accessip:list"; //统计系统数据采集存放ip数据

	public static final String REDIS_KEY_TALEBASE_TOKEN_PREFIX = "talebase:auth:token:";//人才测评个人用户前缀
	public static final String REDIS_KEY_TALEBASE_CUSTOMER = "talebase:customer:id";//人才测评存放企业ID

    public static final String REDIS_KEY_LOGO_HITS_LIST = "logo:hits:list";		//LOGO点击量
    public static final String REDIS_KEY_LOGO_HITS_ESHOP_FAME_COM_LIST = "logo:hits:eshop:fame:com:list";      //电商知名企业logo点击量
    public static final String REDIS_KEY_LOGO_HITS_ESHOP_FAME_COM_SAVETIME = "logo:hits:eshop:fame:com:savetime"; //电商知名企业logo点击量上一次保存时间

    public static final String REDIS_KEY_LOGO_HITS_HOT_RECRUIT_COM_LIST = "logo:hits:hot:recruit:com:list";    //招聘企业logo点击量

    public static final String REDIS_KEY_COM_LESS_RECEIVE_RESUME_LIST = "com:less:receive:resume:lost";		//收到的简历太少的企业列表

    public static final String REDIS_KEY_SYS_EXP_POS_KEYWORD_LIST = "sys:setting:exposure:position:keyword:list";//后台网站设置曝光率职位关键字搜索列表
    public static final String REDIS_KEY_COM_SURVEY_READ_LIST = "com:survey:read:list";// 企业是否关闭或提交过调查问卷

    public static final String REDIS_KEY_SYS_BUY_RESUME = "sys:buy:resume:";//招聘顾问购买简历
    public static final String REDIS_KEY_PER_APPLY_COUNT_OF_DAY = "per:apply:count:";//个人一天应聘的总次数

    public static final String REDIS_KEY_SMS_FAILURE_LIST = "sms:failure:list";//发送失败的短信列表
    public static final String REDIS_KEY_MOBILE_API_TOKEN_PREFIX = "open:token:";//手机端token在Redis中的保存的key的前缀 +(token)

    public static final String REDIS_KEY_CAPTCHA_PREFIX = "v:code:";	//登陆验证码前缀
    
    public static final String REDIS_KEY_LIMIT_ACCESS_CAPTCHA_PREFIX = "access:limit:code:";	//访问限制验证码前缀
    public static final String REDIS_KEY_LIMIT_ACCESS_URL_PREFIX = "access:limit:url";     //访问限制URL前缀
    
    public static final String REDIS_KEY_ACCESS_ANALYSIS_END_TIME = "access:analysis:end:time";	//职位访问限制分析进程结束时间
    
    public static final String REDIS_KEY_ACCESS_LOG_LIST = "log:access:list";					//网站访问日志列表
    
    public static final String REDIS_KEY_POS_RESUME_RECOMMAND = "pos:resume:recommand";//职位简历互荐
    public static final String REDIS_KEY_POS_KEY_RECOMMAND  = "redis:key:recommand:";//推荐用户前缀标示
    public static final String REDIS_KEY_POS_SIMILAR_LIST_PREFIX = "pos:similar:list:prefix:";	//职位的相似职位列表前缀 +(posId)
    
    public static final String REDIS_KEY_ACCESS_LOG_POS_DETAIL = "log:access:pos:list";			//职位访问详细列表
    
    public static final String REDIS_KEY_POS_ACCESS_IP_LIST = "pos:access:ip:list";  			//职位访问IP列表
    public static final String REDIS_KEY_POS_ACCESS_IP_LIMIT_PREFIX = "pos:access:ip:limit:";	//职位访问IP限制前缀
    
    public static final String REDIS_KEY_SEARCH_ACCESS_IP_LIST = "search:access:ip:list";  			//搜索访问IP列表
    public static final String REDIS_KEY_SEARCH_ACCESS_IP_LIMIT_PREFIX = "search:access:ip:limit:";	//搜索访问IP限制前缀

    public static final String REDIS_KEY_SYS_DISTRICT_POSITION = "district:per:position:";			//园区职位json串

    public static final String REDIS_KEY_SYS_SCROLL_ADV_PREFIX = "sys:scroll:adv:";			//切屏广告html文件redis前缀

    public static final String REDIS_KEY_POS_HOT_DEGREE_PREFIX = "pos:hot:degree:prefix:";		//职位热门程度前缀 
    public static final String REDIS_KEY_ESHOP_POS_LIST = "eshop:pos:list:";			//电商人才专题职位列表前缀 + (类别)
    public static final String REDIS_KEY_ESHOP_INDEX_POS_LIST = "eshop:index:pos:list:"; //电商频道的首页职位列表

    public static final String REDIS_KEY_SAL_COM_POS_SUM = "sal:com:pos:sum";//业务员会员总数与职位总数
    public static final String REDIS_KEY_VIDEO_COM_POS_LIST_PREFIX  = "video:com:pos:list";// 视频招聘-企业所有的职位列表 + 公司id
    
    public static final String REDIS_KEY_POS_SEARCHER_COM = "pos:search:com";//企业管理中心增加调研广告

    public static final String REDIS_KEY_CMS_TO_SHOW_VISITOR = "cms:to:show:visitor";//关于我们网站动态游客
    public static final String REDIS_KEY_CMS_TO_SHOW_PER = "cms:to:show:per";//关于我们网站动态个人
    public static final String REDIS_KEY_CMS_TO_SHOW_COM = "cms:to:show:com";//关于我们网站动态企业
    public static final String REDIS_KEY_CMS_TO_SHOW_HUMAN = "cms:to:show:human";//人力资本首页专家团队
    public static final String REDIS_KEY_CMS_TO_HUMAN_RES_DOWN = "human:resouce:down:session";//人力资本历届学习回顾及资料下载sessionId
    public static final String REDIS_KEY_SUBSITE_SEO_PREFIX = "subsite:seo:"; //分站点（频道）的seo关键字前缀

    public static final String REDIS_KEY_INDEX_SHOW_ARTICLE = "index:show:article";// 网站首页展示HR微访谈页面
    public static final String REDIS_KEY_SYS_SWEEPSTAKES_PREFIX ="sweepstake:pool:pre"; //奖池pre-Key
    public static final String REDIS_KEY_RESUME_CHECK_ILLEGAL_KEYWORD = "resume:check:illegal:keyword";  //检测简历非法关键字

    public static final String  REDIS_KEY_WECHAT_FOLLOWER_COUNT_PREFIX = "wechat:follower:count:";//prefix+日期作为记录
    
    public static final String REDIS_KEY_COM_DEPARTMENT = "com:department:";//企业部门信息(+企业id)

    public static final String REDIS_KEY_PER_BEHAV_LOG_LIST = "per:behav:log:list"; //个人用户行为日志
    public static final String REDIS_KEY_COM_BEHAV_LOG_LIST = "com:behav:log:list"; //企业用户行为日志

    public static final String REDIS_KEY_RPO_RANDOMCODE = "rpo:random:code";//RPO项目接口动态码(10位随机码)
    public static final String REDIS_KEY_RPO_COMID = "rpo:comid";//RPO项目接口中的企业ID<数据结构List<Integer>集合>
    public static final String REDIS_KEY_TOUCH_REG_ACT_MONEY_PREFIX = "touch:reg:act:money:";//注册送话费的LOG + mobile

    public static final String REDIS_KEY_SUR_NAME_LIST = "sur:name:list";//百家姓列表KEY
    
    public static final String REDIS_KEY_DIDI_PER_REGISTER = "didi:per:register";
    public static final String REDIS_KEY_DIDI_PER_APPLY = "didi:per:apply";
    public static final String REDIS_KEY_DIDI_PER_RESERVE = "didi:per:reserve";
    public static final String REDSI_KEY_DIDI_EXCHANGE_PREFIX = "didi:exchange:";

    public static final String REDSI_KEY_SYS_COUNT_ITEM_SETTING = "sys_count_item_setting";
    // ========== Redis Key ========== End

    public static   final   String  REDIS_CAMPUS_HOT_POSITION="campus:hot_position";
    public static   final   String  REDIS_CAMPUS_ENT_COM="campus:ent_com";
    public static   final   String  REDIS_CAMPUS_CAREER_TALK="campus:career_talk";
    public static   final   String  REDIS_CAMPUS_INFO="campus:info";
    public static   final   Integer REDIS_CAMPUS_EXPIRE=60*60*24*2;//两天

    // ========== Redis Key Expire Time ========== Start
    public static final Integer REDIS_EXPIRE_TIME_COM_LOGIN = 30 * 24 * 60 * 60; //30 days
    public static final Integer REDIS_EXPIRE_TIME_PER_LOGIN = 30 * 24 * 60 * 60; //30 days
    public static final Integer REDIS_EXPIRE_TIME_SYS_LOGIN = 8 * 60 * 60; //5 hour
    public static final Integer REDIS_EXPIRE_TIME_PER_MAIL_VALIDATION = 1 * 60 * 60; //1 hours
    public static final Integer REDIS_EXPIRE_TIME_PER_SMS_VALIDATION = 1 * 10 * 60; //10分钟
    public static final Integer REDIS_EXPIRE_TIME_PER_LOGIN_DYNAMIC_CODE = 1 * 10 * 60; //10分钟

    public static final Integer REDIS_EXPIRE_TIME_COM_MSG_SEND_LIST = 24 * 60 * 60 ; //24小时
    public static final Integer REDIS_EXPIRE_TIME_DYNAMIC = 30 * 24 * 60 * 60; //30 days

    public static final Integer REDIS_EXPIRE_TIME_PVCOUNT_INFO = 1 * 10 * 60; //10分钟

    public static final Integer REDIS_EXPIRE_TIME_SITE_IRC_INDEX_INFO = 1 * 10 * 60; //10分钟

    public static final Integer REDIS_EXPIRE_TIME_IRC_SERVICE_ITEM  = 2 * 60 * 60; //2 hours

    public static final Integer REDIS_EXPIRE_TIME_SEARCH_POS_HOT_KEY = 10 * 24 * 60 * 60; //10 days
    public static final Integer REDIS_EXPIRE_TIME_MOBILE_API_TOKEN = 30 * 24 * 60 * 60; //30 days

    public static final Integer REDIS_EXPIRE_TIME_CAPTCHA = 1 * 10 * 60; //10分钟
    
    public static final Integer REDIS_EXPIRE_TIME_LIMIT_ACCESS_CAPTCHA = 1 * 2 * 60;//2分钟
    
    public static final Integer REDIS_EXPIRE_TIME_POS_ACCESS_IP_LIMIT = 1 * 60 * 60; //1 hours
    public static final Integer REDIS_EXPIRE_TIME_POS_SIMILAR_LIST = 4 * 60 * 60 ;	//4 hours
    public static final Integer REDIS_EXPIRE_TIME_ESHOP_POS_LIST = 4 * 60 * 60 ;	//4 hours
    public static final Integer REDIS_EXPIRE_TIME_ESHOP_INDEX_POS_LIST = 1 * 3 * 60 ;	//3分钟

    public static final Integer REDIS_EXPIRE_TIME_VIDEO_COM_POS_LIST  =  3 * 10 * 60; //30分钟
    public static final Integer REDIS_EXPIRE_TIME_SITE_NEW_POSITION_LIST =  1 * 10 * 60;   //10分钟
    public static final Integer REDIS_EXPIRE_TIME_GUEST_LIKE_POSITION_LIST =  1 * 10 * 60; //10分钟
    public static final Integer REDIS_EXPIRE_TIME_SITE_HOT_POSITION_LIST =  2 * 60 * 60; //2 hours
    public static final Integer REDIS_EXPIRE_TIME_ESHOP_FAME_LOGO_HITS =  1 * 10 * 60;   //10分钟
    
    public static final Integer REDIS_EXPIRE_TIME_COM_DEPARTMENT = 30 * 24 * 60 * 60; //30 days


    public static final Integer REDIS_EXPIRE_TIME_SWEEPSTAKES =1 * 60 * 60*24; //过期时间 1天
    public static final Integer REDIS_EXPIRE_TIME_TOUCH_REG_ACT_MONEY  = 1 * 60 * 60; //过期时间1小时

    // ========== Redis Key Expire Time ========== End

    // ========== Captcha Session Key ========== Start
    public static final String CAPTCHA_SESSION_KEY = "CAPTCHA_SESSION_KEY";
    public static final String CAPTCHA_SESSION_DATE = "CAPTCHA_SESSION_DATE";
    // ========== Captcha Session Key ========== End

    // ========== 定义上传图片的类型 ========== Start
    public static final String UPLOAD_TYPE_COM_LOGO = "com_logo"; //企业Logo图片
    public static final String UPLOAD_TYPE_COM_EXTRA = "com_extra"; //企业其他资料图片
    public static final String UPLOAD_TYPE_PER_PHOTO = "per_photo"; //个人简历照片图片
    public static final String UPLOAD_TYPE_PER_CERTIFICATION = "per_certification"; //个人简历证书图片
    public static final String UPLOAD_TYPE_PER_ATTACHMENT = "per_attachment"; //个人简历附件图片
    public static final String UPLOAD_TYPE_SYS_LOGO = "sys_logo"; //后台上传LOGO广告
    public static final String UPLOAD_TYPE_SYS_AVATAR = "sys_avatar"; //后台用户头像
    public static final String UPLOAD_TYPE_SYS_IRC_PROD="sys_irc_pord" ;//后台上传IRC产品图片
    public static final String UPLOAD_TYPE_SYS_IRC_THUMBNAIL="sys_irc_thumbnail" ;//后台上传IRC产品拇指图
    public static final String UPLOAD_TYPE_SYS_SPEC_SUBJECT ="sys_spec_subject" ;//后台上传的营销专题图片
    public static final String UPLOAD_TYPE_ACT_WECHAT = "act_wechat";
    public static final String UPLOAD_TYPE_CRM_WEIXINWAIBAO= "crm_weixin_waibao";
    // ========== 定义上传图片的类型 ========== End

    // ==================网站重要数据安全性处理的key===================Start
    public static final String WEB_IMPORTANT_SECURITY_KEY = "252@#rgdf$%";//加密与解密时需要的KEY
    public static final String WEB_IMPORTANT_SECURITY_LOCAL_KEY = "chitone";//此Key用来判断是否是标签请求
    // ==================网站重要数据安全性处理的key====================End

    public static final Integer MAIL_TASK_COM_USER_MODIFY = 3301;//企业中心－设置－修改账号信息邮件
    
    // ========== 定义上传图片的尺寸, 用于剪裁和缩放, 如果没有剪裁缩放要求的设置为0，两个参数分别为[宽，高] ========== Start
    public static final Map<String, Integer[]> UPLOAD_SIZE = Maps.newHashMap();

    static {
    	UPLOAD_SIZE.put(UPLOAD_TYPE_COM_LOGO, new Integer[]{150, 150});
    	UPLOAD_SIZE.put(UPLOAD_TYPE_COM_EXTRA, new Integer[]{0, 0});
    	UPLOAD_SIZE.put(UPLOAD_TYPE_PER_PHOTO, new Integer[]{120, 160});
    	UPLOAD_SIZE.put(UPLOAD_TYPE_PER_CERTIFICATION, new Integer[]{0,0});
    	UPLOAD_SIZE.put(UPLOAD_TYPE_PER_ATTACHMENT, new Integer[]{0,0});
    	UPLOAD_SIZE.put(UPLOAD_TYPE_SYS_LOGO, new Integer[]{0, 0});
    	UPLOAD_SIZE.put(UPLOAD_TYPE_SYS_AVATAR, new Integer[]{0, 0});
        UPLOAD_SIZE.put(UPLOAD_TYPE_SYS_IRC_PROD, new Integer[]{0, 0});
        UPLOAD_SIZE.put(UPLOAD_TYPE_SYS_IRC_THUMBNAIL, new Integer[]{180, 180});
        UPLOAD_SIZE.put(UPLOAD_TYPE_SYS_SPEC_SUBJECT, new Integer[]{0, 0});
        UPLOAD_SIZE.put(UPLOAD_TYPE_ACT_WECHAT, new Integer[]{0, 0});
        UPLOAD_SIZE.put(UPLOAD_TYPE_CRM_WEIXINWAIBAO, new Integer[]{0, 0});
    }
    // ========== 定义上传图片的尺寸, 用于剪裁和缩放, 如果没有剪裁缩放要求的设置为0 ========== End

    // ========== 第三方登录的账号类型 ========== Start
    public static final String CONNECT_TYPE_QQ = "qq";
    public static final String CONNECT_TYPE_WEIBO = "weibo";
    public static final String CONNECT_TYPE_WEIXIN = "weixin";
    // ========== 第三方登录的账号类型 ========== End

    // ========== 个人用户简历注册时所有使用的简历ID ========== Start
    public static final String STEP_PROCESS_RESUME = "step_process_resume";
    // ========== 个人用户简历注册时所有使用的简历ID ========== End

    // ========== 个人用户取回密码时所有使用的账号ID ========== Start
    public static final String STEP_PROCESS_FORGET = "step_process_forget";
    // ========== 个人用户取回密码时所有使用的账号ID ========== End

    // ========== 移动端找回密码的方式 ========== Start
    public static final int API_SEND_PASSWORLD_TYPE_SMS = 1;  // 发送手机短信
    public static final int API_SEND_PASSWORLD_TYPE_EMAIL = 2; // 发送邮邮件
    // ========== 移动端找回密码的方式 ========== End


    // ========== 职位状态枚举  ========== Start
	public static final int POS_STATUS_POST = 9; //正在招聘(已通过审核)：已审核的职位，如果企业再次修改内容的话，变成状态5重新审核
	public static final int POS_STATUS_POST_WAIT= 5; //正在招聘(等待审核)：用户工作时间发布的职位，通过审核后变成状态9
	public static final int POS_STATUS_WAIT_FOR_FINISH = 1; //已审核待完善：后台进行了审核，但有部分信息未完善的，变成此状态，当此状态的职位过了两天（缓冲期）还没有完善的话，变成状态 -15，此状态下的职位仍然可以进行搜索及招聘操作，若用户完善职位信息后状态变成5
	public static final int POS_STATUS_WAIT_FOR_AUDIT =0; //待审核：用户非工作时间发布的职位，通过审核后变成状态9
	public static final int POS_STATUS_STOP = -1; //停止招聘：企业因招聘需要，停止招聘的职位，非试用或正式会员发布的职位默认为此状态
	public static final int POS_STATUS_COM_EXPIRED = -3; //过期：企业过期的时候职位会变成这个状态，当企业重新开通的时候，将这个状态的职位自动修改为5
	public static final int POS_STATUS_EXPIRED = -5; //过期：职位到期的时候职位会变成这个状态
	public static final int POS_STATUS_TIMEOUT = -15; //超时屏蔽：当 1 状态的职位过了两天用户还没有修改的话，变成此状态，用户修改后变成状态0
	public static final int POS_STATUS_DENIED = -19; //未通过审核：有问题的职位，用户修改后变成状态0
    public static final int POS_STATUS_DELETED = -99; // 删除：回收站，企业将职位进行删除（放入回收站）操作，状态变成此状态
	// ========== 职位状态枚举  ========== End


    // ========== 企业购买过简历类别  ========== Start
    public static final int COM_NOT_BUY_RESUME = 0;   //未购买
    public static final int COM_HAS_BUY_RESUME = 1;   //已购买
    // ========== 企业购买过简历类别  ========== End

// ========== 企业发送信息(模板）类别  ========== Start
	public static final int COM_MESSAGE_INTERVIEW_VIDEO = 1;   //视频面试通知
	public static final int COM_MESSAGE_INTERVIEW_FAIR = 2;    //到场馆面试通知
	public static final int COM_MESSAGE_INTERVIEW_OFFICE = 3;  //到办公室面试通知
	public static final int COM_MESSAGE_CONSIDER = 4;          //侍考虑
	public static final int COM_MESSAGE_REFUSAL = 5;           //婉拒
	public static final int COM_MESSAGE_AUTOREPLY = 6;         //自动回复
	// ========== 企业发送信息(模板）类别  ========== End
	// ========== 企业获得简历的来源  ========== Start
    public static final int COM_CATCH_RESUME_FROM_PERSON = 0;   //个人投递
	public static final int COM_CATCH_RESUME_FROM_SYS = 1;      //后台推荐
	public static final int COM_CATCH_RESUME_FROM_SEARCH = 2;   //企业自搜索
	public static final int COM_CATCH_RESUME_FROM_VIDEO = 3;    //视频招聘
    public static final int COM_CATCH_RESUME_FROM_CARD = 4;     //名片投递
	// ========== 企业获得简历的来源  ========== End

    // ==========  站内回复状态   ========== Start
    public static final int PER_POS_APPLY_REPLY_STATUS_UNREAD = 0; //简历未阅读
    public static final int PER_POS_APPLY_REPLY_STATUS_READ = 1; //简历已阅读
    public static final int PER_POS_APPLY_REPLY_STATUS_CONSIDER = 2; //企业正在考虑您的简历
    public static final int PER_POS_APPLY_REPLY_STATUS_REFUSE = 3; //企业已婉言拒绝了您应聘意向
    public static final int PER_POS_APPLY_REPLY_STATUS_INVITE = 4; //企业已邀请您面试
    public static final int PER_POS_APPLY_REPLY_STATUS_FAV = 5; //简历已经被企业收藏
    // ==========  站内回复状态   ========== End

	// ========== 简历查看(来源)  ========== Start
	public static final String RESUME_FROM_RECEIVE = "receive";      //收到的简历
	public static final String RESUME_FROM_RALEBASE = "talebase";    //收到的简历邀请测评
	public static final String RESUME_FROM_INTERVIEW = "interview";  //邀请面试
	public static final String RESUME_FROM_STORE = "store";          //人才库
	public static final String RESUME_FROM_BLACKLIST = "blacklist";  //黑名单
	public static final String RESUME_FROM_RECYCLE = "recycle";      //回收站
	public static final String RESUME_FROM_SEARCH = "search";        //搜索
	public static final String RESUME_FROM_IRC = "irc";              //参会人才搜索
	public static final String RESUME_FROM_TRANSMIT = "transmit";    //转发简历
	public static final String RESUME_FROM_RECOMMAND = "recommand";  //后台推荐
	public static final String RESUME_FROM_CARD = "card";            //名片模块
	// ==========  简历查看(来源)  ========== END

	 /**
     * 2014-05-29 夏安定新增 测评项目中的授权client_id
     */
	// ===================企业测评测评完之后培智需要调用job5156.com接口，需要的key====================start
	public static final String TALEBASE_COM_TEST_CLIENT_ID="CLLonsbTeaku5WJ88bXGTw==";//培智分配给我们的KEY
	public static final String TALEBASE_COMTEST_KEY = "FFwwwjob005156RRRcomYJ88bWt=";//培智调用我们接口时的校验
	// ===================企业测评测评完之后培智需要调用job5156.com接口，需要的key====================end
    
    // ==========  职位搜索类型  ========== Start
    public static final int SEARCH_TYPE_POS_ALL = 0; //搜索全部职位
    public static final int SEARCH_TYPE_POS_OFFLINE = 1; //搜索线下（招聘会职位）
    public static final int SEARCH_TYPE_POS_ONLINE = 2; //搜索线上（网络职位）
    // ==========  职位搜索类型   ========== END
    // ==========  简历搜索类型  ========== Start
    public static final int SEARCH_TYPE_RESUME_SIMPLE = 1; //简单搜索
    public static final int SEARCH_TYPE_RESUME_ADVANCE = 2; //高级搜索
    public static final int SEARCH_TYPE_RESUME_PERID = 3; //按个人用户NO.搜索
    public static final int SEARCH_TYPE_RESUME_PERNAME = 4; //按个人姓名搜索
    public static final int SEARCH_TYPE_RESUME_EXACT = 5;	//精确搜索(3,4合并到一起)
    // ==========  简历搜索类型   ========== END

    // ==========  企业资料来源   ========== Start
    public static final int COM_FLAG_JOB5156 = 1;	//本网资料
    public static final int COM_FLAG_51JOB = 2;		//51job资料
    public static final int COM_FLAG_JOBCN = 3;		//jobcn资料
    public static final int COM_FLAG_JOB88 = 4;		//job88资料
    public static final int COM_FLAG_IMPORT = 5;	//导入资料
    public static final int COM_FLAG_CHINAHR = 6;	//chinaHr资料
    public static final int COM_FLAG_ZHAOPIN = 7;	//zhaopin资料
    public static final int COM_FLAG_JOB168 = 8;	//job168资料
    // ==========  企业资料来源   ========== End

    // ==========  站内消息来源   ========== Start
    public static final String SITE_MESSAGE_SENDER_COM = "COM";
    public static final String SITE_MESSAGE_SENDER_PER = "PER";
    public static final String SITE_MESSAGE_SENDER_SYS = "SYS";
    // ==========  站内消息来源   ========== End

    // ==========  站内阅读状态   ========== Start
    public static final int SITE_MESSAGE_UNREAD = 0;
    public static final int SITE_MESSAGE_READ= 1;
    // ==========  站内阅读状态   ========== End

    // ==========  站内回复状态   ========== Start
    public static final int SITE_MESSAGE_UNREPLY = 0;
    public static final int SITE_MESSAGE_REPLY= 1;
    // ==========  站内回复状态   ========== End

    // ==========  站内公开状态   ========== Start
    public static final int SITE_MESSAGE_CLOSED = 0;
    public static final int SITE_MESSAGE_OPEN= 1;
    // ==========  站内公开状态   ========== End

    // ==========  手机短信发送记录表的信息类别   ========== Start
    public static final Integer  SMS_COMMON = 0;      //普通手机短信
    public static final Integer  SMS_INVITE_VIDEO= 1; //视频面试邀请
    public static final Integer  SMS_INVITE_FAIR = 2; //场馆面试邀请
    public static final Integer  SMS_INVITE_OFFICE=3; //邀请到企业办公室面试
    // ==========  手机短信发送记录表的信息类别   ========== End

    public static final Integer URGENT_LIMIT = 10; //急聘职位限制数

    // ==========  E聘通IP配置   ========== Start
    /**
     * E聘通ip和地区的对应关系
     * 第一栏为可访问E聘通的ip
     * 第二栏为对应的地区编号（对应Prod里面的设置）
     * 第三栏为是否需要查看历史到场人才的功能（前一周内，0为不需要，1为需要）
     * 第四栏为对应的地区名称（目前只是用来查看，代码不使用）
     */
    public static final String[][] eRecruitIpSettingArray = {
            {"192.168", "0", "1", "测试"},
            {"61.235.124.116", "116", "0", "东莞莞城"},
            {"61.142.9.120", "26", "1", "东莞长安"},
            {"125.93.254.198", "54", "0", "东莞塘厦"},
            {"120.197.206.223", "63", "1", "佛山"},
            {"123.151.186.87", "63", "1", "佛山"},
            {"222.51.36.147", "104", "0", "长沙"},
            {"123.65.61.17", "70", "1", "江门"},
            {"113.140.18.50", "74", "0", "西安"},
            {"218.14.52.173", "91", "0", "中山"},
            {"218.2.96.154", "184", "0", "南京"}
    };
    // ==========  E聘通IP配置   ========== End

    // ==========  分站点   ========== Start
    /**
     * 第0栏，站点子域 <- 已取消，转移至domain.properties中进行配置
     * 第一栏，站点拼音
     * 第二栏，站点名
     * 第三栏，城市编码
     */
    public static final List<String[]> CHITONE_WEBSITE_LIST = Lists.newArrayList(
    		new String[]{"sjzt", "世纪智通", "23010000"},
    		new String[]{"xiansite", "西安", "23010000"},
            new String[]{"dongguan", "东莞", "14010000"},
            new String[]{"shenzhen", "深圳", "14020000"},
            new String[]{"guangzhou", "广州", "14030000"},
            new String[]{"zhongshan", "中山", "14040000"},
            new String[]{"huizhou", "惠州", "14070000"},
            new String[]{"jiangmen", "江门", "14080000"},
            new String[]{"foshan", "佛山", "14090000"},
            new String[]{"changsha", "长沙", "28010000"},
            new String[]{"chongqing", "重庆", "13000000"},
            new String[]{"nanchang", "南昌", "29010000"},
            new String[]{"ningbo", "宁波", "15020000"},
            new String[]{"wuhan", "武汉", "27010000"},
            new String[]{"sjzt", "世纪智通", "23010000"},
            new String[]{"xiansite", "西安", "23010000"},
            new String[]{"chenzhou", "郴州", "28070000"},
            new String[]{"nanjing", "南京", "16010000"},
            new String[]{"hengyang", "衡阳", "28060000"},
            new String[]{"zhuhai", "珠海", "14050000"},
            new String[]{"xiangtan", "湘潭", "28020000"},
            new String[]{"jingzhou", "荆州", "27040000"},
            new String[]{"qingyuan", "清远", "14110000"},
            new String[]{"hefei", "合肥", "24010000"},
            new String[]{"shunde", "顺德", "14230000"},
            new String[]{"zhaoqing", "肇庆", "14130000"},
            new String[]{"xianning", "咸宁", "27100000"},
            new String[]{"shaoyang", "邵阳", "28120000"},
            new String[]{"yongzhou", "永州", "28130000"}
    );
    // ==========  分站点   ========== End

    //字符串转DATE的常见日期格式,方便DateUtils.parseDate()使用
    public static final String[] datePattern = new String[]{
            "yyyy-MM-dd HH:mm:ss",
            "yyyy/MM/dd HH:mm:ss",
            "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd HH:mm:ss.S",
            "yyyy/MM/dd HH:mm:ss.S",
            "yyyy-MM-dd",
            "yyyy/MM/dd",
            "yyyy.MM.dd HH:mm:ss",
            "yyyy.MM.dd",
            "yyyy-MM",
            "yyyyMM",
            "yyyy/MM",
            "yyyyMMdd",
            "yyyyMMddHHmmss"
    };

    // ==========  登录企业管理中心来源   ========== Start
    public static final String COM_LOGIN_FROM_WEB = "web";	//通过账号密码前台登录
    public static final String COM_LOGIN_FROM_SYS = "sys";	//网站后台登录
    public static final String COM_LOGIN_FROM_CRM = "crm";	//CRM登录
    // ==========  登录企业管理中心来源   ========== End
    
    // ==========  登录个人管理中心来源   ========== Start
    public static final String PER_LOGIN_FROM_WEB = "web";	//通过账号密码前台登录
    public static final String PER_LOGIN_FROM_SYS = "sys";	//网站后台登录
    // ==========  登录个人管理中心来源   ========== End

    // ==========  营销专题文件内容的类型   ========== Start
    public static final Integer SUBJECT_CONTENT_TYPE_TEXT = 1;	//纯文本
    public static final Integer SUBJECT_CONTENT_TYPE_PHOTO = 2;	//纯图片
    public static final Integer SUBJECT_CONTENT_TYPE_TEXT_AND_PHOTO = 3;	//文本+图片
    public static final Map<Integer, String> subjectContentTypeMap = new LinkedHashMap<Integer, String>();
    static {
        subjectContentTypeMap.put(SUBJECT_CONTENT_TYPE_TEXT, "纯文本");
        subjectContentTypeMap.put(SUBJECT_CONTENT_TYPE_PHOTO, "纯图片");
        subjectContentTypeMap.put(SUBJECT_CONTENT_TYPE_TEXT_AND_PHOTO, "文本+图片");
    };
    //父类专题类别
    public static final Integer SUBJECT_CATEGORY_FAIR_COM = 1;         //参会企业
    public static final Integer SUBJECT_CATEGORY_HOT_POSITION = 2;     //热招职位
    public static final Integer SUBJECT_CATEGORY_MEDIA_REPORT = 3;     //媒体报道
    public static final Integer SUBJECT_CATEGORY_COM_AND_POSITION = 4; //企业名+职位
    public static final Integer SUBJECT_CATEGORY_INFO = 5;             //资讯类

    public static final Map<Integer, String> subjectCategoryMap = new LinkedHashMap<Integer, String>();
    static {
        subjectCategoryMap.put(SUBJECT_CATEGORY_FAIR_COM, "参会企业");
        subjectCategoryMap.put(SUBJECT_CATEGORY_HOT_POSITION, "热招职位");
        subjectCategoryMap.put(SUBJECT_CATEGORY_MEDIA_REPORT, "媒体报道");
        subjectCategoryMap.put(SUBJECT_CATEGORY_COM_AND_POSITION, "企业名+职位");
        subjectCategoryMap.put(SUBJECT_CATEGORY_INFO, "资讯类");
    };
    // ==========  营销专题文件内容的类型   ========== End

    // ==========  简历的语言类型   ========== Start
    public static final Integer RESUME_CN = 0 ; //中文简历
    public static final Integer RESUME_EN = 1 ; //英文简历
    // ==========  简历的语言类型   ========== End

    /**
     * 主机、地址、用户名、密码
     */
    public static final String[][] MAIL_BUSINESS_SETTINGS = {
            new String[]{"smtp.vip1.job5156.com","report001@vip1.job5156.com","report001@vip1.job5156.com","ZTMGFWwlist001"},
            new String[]{"smtp.vip1.job5156.com","report002@vip1.job5156.com","report002@vip1.job5156.com","ZTMGFWwlist002"},
            new String[]{"smtp.vip1.job5156.com","report003@vip1.job5156.com","report003@vip1.job5156.com","ZTMGFWwlist003"},
            new String[]{"smtp.vip1.job5156.com","report004@vip1.job5156.com","report004@vip1.job5156.com","ZTMGFWwlist004"},
            new String[]{"smtp.vip1.job5156.com","report005@vip1.job5156.com","report005@vip1.job5156.com","ZTMGFWwlist005"},
            new String[]{"smtp.vip1.job5156.com","report006@vip1.job5156.com","report006@vip1.job5156.com","ZTMGFWwlist006"},
            new String[]{"smtp.vip1.job5156.com","report007@vip1.job5156.com","report007@vip1.job5156.com","ZTMGFWwlist007"},
            new String[]{"smtp.vip1.job5156.com","report008@vip1.job5156.com","report008@vip1.job5156.com","ZTMGFWwlist008"},
            new String[]{"smtp.vip1.job5156.com","report009@vip1.job5156.com","report009@vip1.job5156.com","ZTMGFWwlist009"},
            new String[]{"smtp.vip1.job5156.com","report010@vip1.job5156.com","report010@vip1.job5156.com","ZTMGFWwlist010"}
    };

    public static final String LOG_ACTION_APPLY_CLICK = "apply_click";//点击应聘动作：点击应聘
    public static final String LOG_ACTION_APPLY_LOGIN_SUCCESS = "apply_login_success";//登录成功（应聘流程）
    public static final String LOG_ACTION_APPLY_LOGIN_FAILURE = "apply_login_failure";//登录失败（应聘流程）
    public static final String LOG_ACTION_APPLY_LOGIN_FAILURE_CLOSE = "apply_login_failure_close";//登录失败点关闭
    public static final String LOG_ACTION_APPLY_LOGIN_FAILURE_FORGET = "apply_login_failure_forget";//登录失败点忘记密码
    public static final String LOG_ACTION_APPLY_SELECT_RESUME = "apply_select_resume";//真正应聘动作：选择简历
    public static final String LOG_ACTION_APPLY_SUCCESS = "apply_success";//应聘成功记录：应聘成功
    public static final String LOG_ACTION_APPLY_FAILURE = "apply_failure";//正真应聘失败记录：应聘失败
    public static final String LOG_ACTION_APPLY_WARN_DUPLICATE = "apply_warn_duplicate";//应聘出现重复提交的：重复提交
    public static final String LOG_ACTION_APPLY_WARN_TWICE = "apply_warn_twice";//应聘出现超过2次：应聘超过2次
    public static final String LOG_ACTION_APPLY_FAILURE_STOP = "apply_failure_stop";//出现职位停止招聘：停止招聘
    public static final String LOG_ACTION_APPLY_FAILURE_RESUME = "apply_failure_resume";//出现简历本身问题的：您的简历
    public static final String LOG_ACTION_APPLY_FAILURE_POSITION = "apply_failure_position";//出现职位信息不存在：职位信息不存在
    public static final String LOG_ACTION_PER_LOGIN_FAILURE = "per_login_failure";//个人登录失败
    public static final String LOG_ACTION_COM_LOGIN_FAILURE = "com_login_failure";//企业登录失败
    public static final String LOG_ACTION_PER_RESET_PASSWORD_STEP1= "per_reset_password_step1";//个人申请重置密码 - 第一步
    public static final String LOG_ACTION_PER_RESET_PASSWORD_STEP2 = "per_reset_password_step2";//个人申请重置密码 - 第二步
    public static final String LOG_ACTION_PER_RESET_PASSWORD_APPLY = "per_reset_password_apply";//个人申请重置密码 - 发送成功
    public static final String LOG_ACTION_PER_RESET_PASSWORD_PASS_VALID_MAIL = "per_reset_password_pass_valid_mail";//个人申请重置密码 - 验证邮件成功
    public static final String LOG_ACTION_PER_RESET_PASSWORD_PASS_VALID_MOBILE = "per_reset_password_pass_valid_mobile";//个人申请重置密码 - 验证手机成功
    public static final String LOG_ACTION_PER_RESET_PASSWORD_SUCCESS= "per_reset_password_success";//个人申请重置密码 - 修改成功
    public static final String LOG_ACTION_COM_LESS_RECEIVE_RESUME_NUM= "com_less_receive_resume_num";// 企业的职位收到简历过少的列表总数
    public static final String LOG_ACTION_PER_MOBILE_VERIFY= "log_action_per_mobile_verify";// 个人申请手机验证

    //搜索的排序值
    public static final int SORT_BY_UPDATETIME = 0;  //更新时间
    public static final int SORT_BY_DEGREE_AND_UPDATEDATE = 1;  //学历+时间
    public static final int SORT_BY_WORKYEAR  = 2;   //工作经验
    public static final int SORT_BY_CREATEDATE  = 3;   //初次发布时间
    public static final int SORT_BY_URGENT  = 4;      //急聘
    public static final int SORT_BY_HOT  = 5;         //热度（查看数）
    public static final int SORT_BY_SALARY  = 6;      //薪资

    public static final int SEARCH_POS_RANK_RADIX  = 100;      //搜索的职位热度基数值

    //统计来源分析-来源类型
    public static final int COUNT_FROM_SOURCE_TYPE_SEARCHENGINE=0;  //搜索引擎
    public static final int COUNT_FROM_SOURCE_TYPE_OUTTERLINK=1;    //外部链接
    public static final int COUNT_FROM_SOURCE_TYPE_SELFACCESS=2;    //自主访问

    public static final int COUNT_FROM_SOURCE_NATURAL=0;  //自然流量
    public static final int COUNT_FROM_SOURCE_SPREAD=1;   //推广栏目

    public static final String RESUME_NAME_BY_CARD = "我的微名片";
    public static final String CSRF_CHECK_NAME = "csrfKey";   //防止CSRF攻击的取值key

    public static final int COUNT_ITEM_SETTING_MODE_SPREAD_PARAM = 0;
    public static final int COUNT_ITEM_SETTING_MODE_DIRECT_ACCESS = 1;
    public static final int COUNT_ITEM_SETTING_MODE_SEM_SPREAD = 2;
}