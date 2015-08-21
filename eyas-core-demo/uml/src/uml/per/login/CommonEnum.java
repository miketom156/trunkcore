package uml.per.login;

import com.google.common.collect.Lists;
import com.job5156.core.common.Constants;
import com.job5156.core.common.PersistEnum;

import java.util.Collections;
import java.util.List;

/**
 * 项目常用枚举类型集合
 * 所有的Enum类型定义都集中在这里，此类类似静态常量一样的存在
 */
public class CommonEnum {

    /**
     * 职位类型枚举
     * 用于标记职位的类型信息
     */
    public enum PositionStatusGroup {
        /**
         * 正在招聘【企业职位管理列表】
         */
        RECRUITING(
                Constants.POS_STATUS_POST,
                Constants.POS_STATUS_POST_WAIT
        ),
        /**
         * 正在招聘【职位搜索】
         */
        RECRUITING_SEARCH(
                Constants.POS_STATUS_POST,
                Constants.POS_STATUS_POST_WAIT,
                Constants.POS_STATUS_WAIT_FOR_FINISH
        ),
        /**
         * 停止招聘
         */
        STOPRECRUIT(
                Constants.POS_STATUS_STOP,
                Constants.POS_STATUS_COM_EXPIRED,
                Constants.POS_STATUS_EXPIRED
        ),
        /**
         * 待处理招聘
         */
        WAITRECRUIT(
                Constants.POS_STATUS_WAIT_FOR_FINISH,
                Constants.POS_STATUS_WAIT_FOR_AUDIT,
                Constants.POS_STATUS_TIMEOUT,
                Constants.POS_STATUS_DENIED
        ),
        /**
         * 职位状态为删除
         */
        RECYCLERECRUIT(
                Constants.POS_STATUS_DELETED
        ),
        /**
         * 所有招聘
         */
        ALLRECRUIT(
                Constants.POS_STATUS_POST,
                Constants.POS_STATUS_POST_WAIT,
                Constants.POS_STATUS_STOP,
                Constants.POS_STATUS_COM_EXPIRED,
                Constants.POS_STATUS_EXPIRED,
                Constants.POS_STATUS_WAIT_FOR_FINISH,
                Constants.POS_STATUS_WAIT_FOR_AUDIT,
                Constants.POS_STATUS_TIMEOUT,
                Constants.POS_STATUS_DENIED
//                Constants.POS_STATUS_DELETED //所有职位中不包括回收站中的
        ),

        /**
         * 所有
         */
        ALL(
                Constants.POS_STATUS_POST,
                Constants.POS_STATUS_POST_WAIT,
                Constants.POS_STATUS_STOP,
                Constants.POS_STATUS_COM_EXPIRED,
                Constants.POS_STATUS_EXPIRED,
                Constants.POS_STATUS_WAIT_FOR_FINISH,
                Constants.POS_STATUS_WAIT_FOR_AUDIT,
                Constants.POS_STATUS_TIMEOUT,
                Constants.POS_STATUS_DENIED,
                Constants.POS_STATUS_DELETED //回收站
        );

        private List<Integer> value;

        private PositionStatusGroup(Integer... values) {
            List<Integer> list = Lists.newArrayList();
            Collections.addAll(list, values);
            this.value = list;
        }

        public List<Integer> getValue() {
            return value;
        }
    }

    public enum AcceptResumeType {
        /**
         * 所有简历类型
         */
        ALL(-1, "所有"),

        /**
         * 默认值：正式简历，不包含名片
         */
        COMMON(0, "正式简历"),

        /**
         * 仅长沙简历
         */
        CHANGSHA(1, "长沙简历"),

        /**
         * 仅名片
         */
        CARD(4, "名片");
        private Integer value;
        private String descript;

        public Integer getValue() {
            return value;
        }

        public static AcceptResumeType getStatusForValue(Integer value) {
            if (value == null)
                return AcceptResumeType.COMMON;

            for (AcceptResumeType status : values()) {
                if (status.getValue().intValue() == value.intValue())
                    return status;
            }
            return AcceptResumeType.COMMON;
        }

        private AcceptResumeType(Integer value, String descript) {
            this.value = value;
            this.descript = descript;
        }

        @Override
        public String toString() {
            return descript;
        }

    }
    
    /**
     * 
    * @ClassName: LogType 
    * @Description: 日志类型（在AOP切面中的注解日志类型）
    * @author xh 
    * @date 2013-9-12 下午1:47:38 
    *
     */
    public enum LogType {
    	/**
    	 * 后台用户操作日志
    	 */
    	SYS_OPERATOR_LOG,
    	/**
    	 * 后台用户登陆日志
    	 */
    	SYS_LOGIN_LOG;
    }
    
    /**
     * 
    * @ClassName: ResumeStatus 
    * @Description: 简历状态枚举 
    * @author xh 
    * @date 2013-9-11 下午3:24:54 
    *
     */
    public enum ResumeStatus {
    	/**
    	 * 被后台用户删除
    	 */
    	SYS_DELETE(-12, "被后台用户删除"),

    	/**
    	 * 被用户自己删除
    	 */
    	USER_DELETE(-11, "被用户自己删除"),

    	/**
    	 * 自杀式简历
    	 */
    	SUICIDE(-10, "自杀式简历"),

    	/**
    	 * 封锁
    	 */
    	LOCK(-9, "封锁"),

        /**
         * 机器审核，有敏感字
         */
        DUBIOUS(-8, "有敏感字"),

    	/**
    	 * 审核不合格
    	 */
    	AUDIT_FAILURE(-3, "审核不合格"),

    	/**
    	 * 未开通，待审核
    	 */
    	UNAUTHORIZE(-1, "未开通"),

    	/**
    	 * 未完善
    	 */
    	UNPERFECT(0, "未完善"),

    	/**
    	 * 临时开通
    	 */
    	TEMPAUTHORIZE(1, "暂时开通"),

    	/**
    	 * 已开通
    	 */
    	AUTHORIZED(10, "已开通");
    	
    	private Integer value;
    	private String descript;
    	
    	public Integer getValue() {
			return value;
		}
    	
    	public static ResumeStatus getResumeStatusForValue(Integer value) {
			if(value == null)
				return ResumeStatus.UNPERFECT;
			
			for(ResumeStatus status : values()) {
				if(status.getValue().intValue() == value.intValue())
					return status;
			}
			return ResumeStatus.UNPERFECT;
		}

		private ResumeStatus(Integer value, String descript) {
    		this.value = value;
    		this.descript = descript;
    	}

		@Override
		public String toString() {
			return descript;
		}
    }

    /**
     * 简历类别
     * 注意：旧数据有长沙简历类别，正式简历包含长沙简历
     */
    public enum ResumeType {

        /**
         * 正式简历
         */
        COMMON(0, "正式简历"),

        /**
        * 正式简历(长沙)
        */
        CHANGSHA(1, "长沙简历"),

        /**
         * 名片（微简历）
         */
        CARD(4, "名片");

        private Integer value;
        private String descript;

        private ResumeType(Integer value, String descript) {
            this.value = value;
            this.descript = descript;
        }

        public Integer getValue() {
            return value;
        }

        public static ResumeType getResumeTypeForValue(Integer value) {
            if(value == null)
                return ResumeType.COMMON;
            for(ResumeType status : values()) {
                if(status.getValue().intValue() == value.intValue())
                    return status;
            }
            return ResumeType.COMMON;
        }

        @Override
        public String toString() {
            return descript;
        }
    }
    
    /**
     * 职位状态
     * @author leo
     *
     */
    public enum PositionStatus {
    	POST(Constants.POS_STATUS_POST, "正常招聘"),
    	POST_WAIT(Constants.POS_STATUS_POST_WAIT, "正在招聘"),
    	WAIT_FOR_FINISH(Constants.POS_STATUS_WAIT_FOR_FINISH, "已审核待完善"),
    	WAIT_FOR_AUDIT(Constants.POS_STATUS_WAIT_FOR_AUDIT, "待审核"),
    	STOP(Constants.POS_STATUS_STOP, "停止招聘"),
        COM_EXPIRED(Constants.POS_STATUS_COM_EXPIRED, "已过期"),
    	EXPIRED(Constants.POS_STATUS_EXPIRED, "已过期"),
    	TIMEOUT(Constants.POS_STATUS_TIMEOUT, "超时屏蔽"),
        DENIED(Constants.POS_STATUS_DENIED, "未通过审核"),
        DELETED(Constants.POS_STATUS_DELETED, "已删除");

    	private int value;
		private String descript;
		
		public int getValue() {
			return value;
		}
		
		public String getDescript() {
			return descript;
		}

		private PositionStatus(int value, String descript) {
			this.value = value;
			this.descript = descript;
		}
		
		public static String getDescriptByValue(int value) {
			for(PositionStatus status : PositionStatus.values()) {
				if(status.getValue() == value)
					return status.getDescript();
			}
			return "";
		}
    }
    
    /**
	 * 
	* @ClassName: CopyType 
	* @Description: 属性拷贝类型 
	* @author xh 
	* @date 2013-7-18 下午3:11:07 
	*
	 */
	public enum CopyType {
		/**
		 * 模型到Vo
		 */
		MODEL_TO_VO,
		/**
		 * Vo到模型
		 */
		VO_TO_MODEL
	}
    
    /**
     * 
    * @ClassName: Gender 
    * @Description: 联系人性别枚举
    * @author xh 
    * @date 2013-8-1 上午10:29:00 
    *
     */
    public enum Gender implements PersistEnum<Gender> {
    	NONE(0, "不限"),
    	MALE(1, "男"),
    	FEMALE(2, "女");
    	
    	private Integer value;
    	private String name;
    	
    	private Gender(Integer value, String name) {
    		this.value = value;
    		this.name = name;
    	}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public Integer getValue() {
			return value;
		}

		@Override
		public void setValue(Integer value) {
			this.value = value;
		}

		@Override
		public Gender returnEnum(Integer persistedValue) {
			if(persistedValue == null)
				return Gender.NONE;
			
			for(Gender gender : values()) {
				if(gender.getValue().intValue() == persistedValue.intValue())
					return gender;
			}
			return Gender.NONE;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
        public static String getNameByValue(int value) {
            for(Gender type : Gender.values()) {
                if(type.getValue() == value)
                    return type.getName();
            }
            return "";
        }
    }

    public enum PeriodEnum {

        PERIOD_UNLIMIT (0,"不限"),
        PERIOD_3_DAYS (3,"3天内"),
        PERIOD_7_DAYS (7,"一周内"),
        PERIOD_15_DAYS (15,"15天内"),
        PERIOD_30_DAYS (30,"30天内");

        private Integer value;
        private String name;

        private PeriodEnum(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    
    /**
     * 订单支付状态枚举
     */
    public enum PayStatus {
        /**
         * 未支付
         */
        UNPAID(0),
        /**
         * 已支付
         */
        PAID(1);

        private final int key;

        public int getKey() {
            return key;
        }

        private PayStatus(int k) {
            this.key = k;
        }

        /**
         * <li>从外部传入一个int value，生成对应枚举实例 </li>
         * @param key
         * @return
         */
        static public PayStatus newInstance(int key) {
            for (PayStatus payStatus : PayStatus.values()) {
                if (payStatus.getKey() == key) {
                    return payStatus;
                }
            }
            return null;
        }
    }
    
    /**
     * 购买状态
     * @author leo
     *
     */
    public enum BuyFlag {
    	/**
    	 * 免费
    	 */
    	FREE(0),
    	/**
    	 * 付费
    	 */
    	PAY(1);
    	
    	private final int key;

        public int getKey() {
            return key;
        }
        
        private BuyFlag(int k){
        	this.key = k;
        }
    }
    
    /**
	 * 
	* @ClassName: memberType 
	* @Description: 会员类型
	* @author xh 
	* @date 2013-10-21 下午2:42:45 
	*
	 */
	public enum ComMemberType {
		Test(1, "试用会员"),
		OneMonth(2, "一月期会员"),
		ThreeMonth(3, "三月期会员"),
		SixMonth(4, "半年期会员"),
		OneYear(5, "一年期会员"),
		Import(6, "导入企业会员"),
		Stop(7, "终止企业会员");
		
		private int value;
		private String name;
		
		public int getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
		
		/**
		 * 
		* @Title: getNameByValue 
		* @Description: 通过值获取枚举名称
		* @param @param value
		* @param @return    设定文件 
		* @return String    返回类型 
		* @throws
		 */
		public static String getNameByValue(int value) {
			for(ComMemberType type : ComMemberType.values()) {
				if(type.getValue() == value)
					return type.getName();
			}
			return "";
		}

		private ComMemberType(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}
	
	/**
	 * 扩展会员类型
	 * @author leo
	 *
	 */
	public enum ComOtherMemberType {
		LOGO(1, "LOGO会员"),
		RECOMMEND(2, "热门推荐会员"),
		TESTED(3, "体验会员"),
		CUSTOM(4, "点数自定义会员");
		
		private int value;
		private String name;
		
		public int getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
		
		private ComOtherMemberType(int value, String name) {
			this.value = value;
			this.name = name;
		}
		
		public static String getNameByValue(int value) {
			for(ComOtherMemberType type : ComOtherMemberType.values()) {
				if(type.getValue() == value)
					return type.getName();
			}
			return "";
		}
	}
	
	/**
	 * 企业状态
	 * @author leo
	 *
	 */
	public enum CompanyStatus {
		REGISTER(0, "新注册"),
		OPENED(1, "已开通"),
		COMMON(2, "公共库企业"),
		LOCKED(-1, "被锁定"),
		INVALID(-2, "作废"),
		UNLAW(-3, "非法企业"),
		DELETED(-4, "已删除");
		
		private int value;
		private String name;
		
		public int getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
		
		private CompanyStatus(int value, String name) {
			this.value = value;
			this.name = name;
		}
		
		public static String getNameByValue(int value) {
			for(CompanyStatus status : CompanyStatus.values()) {
				if(status.getValue() == value)
					return status.getName();
			}
			return "";
		}
	}
	
	/**
	 * 企业账号类型
	 * @author leo
	 *
	 */
	public enum ComUserType {
		MAJOR(0, "主账号"),
		CHILD(1, "子账号"),
		BRANCH(2, "分公司账号");
		
		private int value;
		private String name;
		
		public int getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
		
		private ComUserType(int value, String name) {
			this.value = value;
			this.name = name;
		}
		
		public static String getNameByValue(int value) {
			for(ComUserType type : ComUserType.values()) {
				if(type.getValue() == value)
					return type.getName();
			}
			return "";
		}
	}
	
	/**
	 * 工作性质：0=未选择，1=全职，2=兼职，3=实习
	 */
	public enum PositionPropertyType {
		UNKNOW(0, "未选择"),
		FULLTIME(1, "全职"),
		PARTTIME(2, "兼职"),
		INTERN(3, "实习");
       
		private int value;
		private String name;
		private PositionPropertyType(int value, String name) {
			this.value = value;
			this.name = name;
		}
       
		public static String getNameByValue(int value) {
			for(PositionPropertyType type : PositionPropertyType.values()) {
				if(type.getValue() == value)
					return type.getName();
			}
			return "";
		}
       
		public int getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
	}

	/**
     *
    * 简历来源
    * @author xh
    * @date 2014-2-26 下午4:10:00
    *
     */
    public enum ComeFrom {
    	WEB(0, "网络注册"),
    	SUN(2, "阳光网"),
    	AD(3, "直推广告"),
    	GOOGLE(4, "Google"),
    	BAIDU(5, "Baidu"),
    	AD2(6, "直推广告2"),
    	AD3(7, "直推广告3"),
    	AD8(8, "51注册"),
    	SITE_REG(20, "现场录入"),
    	INPUTFVIP(30, "现场VIP导入"),
    	VIP(31, "VIP录入"),
    	SQSM(40, "十全十美注册"),
    	LINGXIUGAOJI(50, "领袖高级人才导入"),

    	IT_INDUSTRY(60, "IT行业"),
    	HARDWARE_MOULD_INDUSTRY(61, "五金模具行业"),
    	ELECTRONICS_INDUSTRY(62, "电子电器行业"),
    	PRINT_PACKAGE_INDUSTRY(63, "印刷包装行业"),
    	HOTEL_TOURING_INDUSTRY(64, "旅游酒店行业"),
    	SCHOOL(65, "校园频道"),
    	CAREERGO_CB(70, "事业起点合作"),

    	JOBCN_REG(100, "卓博(jobcn)导入"),
    	JOB51_REG(101, "前程无忧(51job)导入"),
    	CJOL(102, "中国人才热线(cjol)导入"),
    	JOBSKY(103, "博天简历(job-sky)导入"),
    	ZHHR(104, "珠海简历(zhhr)导入"),
    	HNRCSC(105, "湖南人才网(hnrcsc)导入"),
    	CHINAHR(106, "中华英才网(Chinahr)导入"),
    	HRM(107, "联英人才网(hrm)导入"),
    	JOBAAA(108, "JOBAAA导入"),
    	CS(109, "长沙导入"),
    	NC(110, "南昌导入"),
    	ZHAOPIN(111, "智联招聘(zhaopin)导入"),
    	FRRC(112, "芙蓉人才网(frrc)导入"),
    	JOB168(113, "南方人才网(job168)导入"),
    	CQRC(114, "重庆人才网(cqrc)导入"),
    	CQJOB(118, "重庆人才网(cqjob)导入"),
    	JXRC(119, "江西人才网(jxrencai)导入"),
    	ZS(120, "中山(智海)简历"),
    	JM(121, "江门(新会)简历"),
    	FS_0757RC(122, "佛山(大佛山)简历"),
    	NB_NBRC(123, "宁波人才网(nbrc)"),
    	JOB001(124, "国际人才网(job001.cn)"),
    	CARJOB(125, "中国汽车人才网导入(http://www.carjob.com.cn)"),
    	CSHR(126, "长沙人才网导入(http://www.cshr.com.cn)"),
    	JXDLRC(127, "德林人才网导入(http://www.jxrczpw.com)"),
    	RC0757(128, "大佛山人才网简历导入(http://www.0757rc.com)"),
    	JOBSKY_1(129, "博天人才网(http://www.job-sky.com)"),
    	JJRW(130, "家具人才网(http://www.jjrw.com)"),
    	SYS_RC0757(131, "大佛山人才网后台导入(http://www.0757rc.com)"),
    	ZSJJOB(132, "珠三角人才网(http://www.zsjjob.com)"),
    	NBRC(133, "宁波人才网(www.nbrc.com.cn)"),
    	HNJ(134, "湖南招聘网(http://www.hnz.com.cn)"),
    	NJRSRC(135, "南京招聘网(http://www.njrsrc.com)"),
    	NAJIE(136, "纳杰人才网(http://www.333job.com)"),
    	HNHYRC(137, "衡阳人才网(http://www.hnhyrc.com)"),
    	KUAIJIE(138, "快捷人才网(http://jz.hr1000.com)"),
    	XAJOB(139, "中国西安人才网(http://www.xajob.com)"),

    	DONGGUAN(200, "东莞"),
    	FOSHAN(201, "佛山"),
    	XIAN(202, "西安"),
    	ZHONGSHAN(203, "中山"),
    	JIANGMEN(204, "江门"),
    	TIANJIN(205, "天津"),
    	BEIJING(206, "北京"),
    	SHANGHAI(207, "上海"),
    	GUANGZHOU(208, "广州"),
    	SHENZHEN(209, "深圳"),
    	WUHAN(210, "武汉"),
    	HUIZHOU(211, "惠州"),
    	SUZHOU(212, "苏州"),
    	CHANGSHA(213, "长沙"),
    	CHONGQING(214, "重庆"),
    	CHENGDU(215, "成都"),
    	NANCHANG(216, "南昌"),
    	NINGBO(217, "宁波"),
    	ZHEJIANG(218, "浙江"),
    	CQNEWS(219, "重庆华龙网"),
    	SNS(220, "职友圈"),
    	WUXI(221, "无锡"),
    	CHENZHOU(222, "郴州"),
    	DALIAN(223, "大连"),
    	QINGDAO(224, "青岛"),
    	HEFEI(225, "合肥"),
    	ZHENGZHOU(226, "郑州"),
    	XUZHOU(227, "徐州"),
    	NANJING(228, "南京"),
    	HENGYANG(229, "衡阳"),
    	ZHUHAI(230, "珠海"),
    	XIANGTAN(231, "湘潭"),
    	JINGZHOU(232, "荆州"),

        THIRD_BINDING_REG(400, "第三方绑定注册"),
        MOBILE_REG(500, "手机客户端"),
    	FAST_REG(600, "快速注册"),
        WEBCHATWIND_REG(601, "微疯注册"),
        WECHAT_REG(602, "微信服务号注册"),
        TOUCH_REG(603, "触屏版注册"),
        PTIME_REG(604, "兼职地图注册"),

        //注册送话费从900--1999
        ACT_REG_DG(900,"注册送话费-莞城-线下"),
        ACT_REG_CA(901,"注册送话费-长安-线下"),
        ACT_REG_TX(902,"注册送话费-塘厦-线下"),
        ACT_REG_ZS(904,"注册送话费-中山-线下"),
        ACT_REG_JM(908,"注册送话费-江门-线下"),
        ACT_REG_FS(909,"注册送话费-佛山-线下"),

        ACT_REG_NET_M_WB(903,"注册送话费-总部-微博"),
        ACT_REG_NET_M_WX(910,"注册送话费-总部-微信"),
        ACT_REG_NET_WWW(911,"注册送话费-总部-网站"),
        ACT_REG_NET_DG(912,"注册送话费-东莞-网站"),
        ACT_REG_DG_SCHOOL(913,"注册送话费-莞城-校园推广(有文案)"),
        ACT_REG_DG_OPENAREA1(914,"注册送话费-莞城-公共区域"),
        ACT_REG_DG_OPENAREA2(915,"注册送话费-莞城-公共区域(有文案)"),
        ACT_REG_DG_OPR(916,"注册送话费-莞城-外包-线下"),

        ACT_REG_NET_NJ(920,"注册送话费-南京-线上"),//16010000
        ACT_REG_OFFLINE_NJ(921,"注册送话费-南京-线下"),

        ACT_REG_NET_CZ(930,"注册送话费-郴州-线上"),
        ACT_REG_OFFLINE_CZ(931,"注册送话费-郴州-线下"),

        ACT_REG_NET_NB(940,"注册送话费-宁波-线上"),
        ACT_REG_OFFLINE_NB(941,"注册送话费-宁波-线下"),

        ACT_REG_NET_NC(950,"注册送话费-南昌-线上"),
        ACT_REG_OFFLINE_NC(951,"注册简历送话费-南昌-招聘会现场"),

        ACT_REG_NET_CS(960,"注册送话费-长沙-线上"),
        ACT_REG_OFFLINE_CS(961,"注册送话费-长沙-线下"),

        ACT_REG_NET_WH(970,"注册送话费-武汉-线上"),
        ACT_REG_OFFLINE_WH(971,"注册送话费-武汉-线下"),

        ACT_REG_NET_JZ(980,"注册送话费-荆州-线上"),
        ACT_REG_OFFLINE_JZ(981,"注册送话费-荆州-线下"),

        ACT_REG_NET_HF(990,"注册送话费-合肥-线上"),
        ACT_REG_OFFLINE_HF(991,"注册送话费-合肥-线下"),

        ACT_REG_NET_HY(1010,"注册送话费-衡阳-线上"),
        ACT_REG_HY_IRC(1011,"注册送话费-衡阳-招聘会现场"),
        ACT_REG_HY_SCHOOL(1012,"注册送话费-衡阳-校园推广"),

        ACT_REG_NET_XT(1020,"注册送话费-湘潭-线上"),
        ACT_REG_OFFLINE_XT(1021,"注册送话费-湘潭-招聘会"),

        ACT_REG_NET_XN(1030,"注册送话费-咸宁-线上"),
        ACT_REG_OFFLINE_XN(1031,"注册送话费-咸宁-线下"),

        ACT_REG_NET_SY(1040,"注册送话费-邵阳-线上"),
        ACT_REG_OFFLINE_SY(1041,"注册送话费-邵阳-线下"),

        ACT_REG_NET_YZ(1050,"注册送话费-永州-线上"),
        ACT_REG_OFFLINE_YZ(1051,"注册送话费-永州-线下"),

        ACT_REG_NET_JM(1060,"注册送话费-江门-线上"),
        ACT_REG_OFFLINE_JM(1061,"注册送话费-江门-线下"),

        ACT_REG_NET_FS(1070,"注册送话费-佛山-线上"),
        ACT_REG_OFFLINE_FS(1071,"注册送话费-佛山-线下"),

        ACT_REG_NET_ZS(1080,"注册送话费-中山-线上"),
        ACT_REG_OFFLINE_ZS(1081,"注册送话费-中山-线下"),

        ACT_REG_NET_GZ(1090,"注册送话费-广州-线上"),
        ACT_REG_OFFLINE_GZ(1091,"注册送话费-广州-线下"),

        ACT_REG_NET_SZ(1100,"注册送话费-深圳-线上"),
        ACT_REG_OFFLINE_SZ(1101,"注册送话费-深圳-线下"),

        ACT_REG_NET_HZ(1110,"注册送话费-惠州-线上"),
        ACT_REG_OFFLINE_HZ(1111,"注册送话费-惠州-线下"),

        ACT_REG_NET_ZH(1120,"注册送话费-珠海-线上"),
        ACT_REG_OFFLINE_ZH(1121,"注册送话费-珠海-线下"),

        ACT_REG_NET_QY(1130,"注册送话费-清远-线上"),
        ACT_REG_OFFLINE_QY(1131,"注册送话费-清远-线下"),

        ACT_REG_NET_SD(1140,"注册送话费-顺德-线上"),
        ACT_REG_OFFLINE_SD(1141,"注册送话费-顺德-线下"),

        ACT_REG_NET_ZQ(1150,"注册送话费-肇庆-线上"),
        ACT_REG_OFFLINE_ZQ(1151,"注册送话费-肇庆-线下"),

        ACT_REG_NET_CQ(1160,"注册送话费-重庆-线上"),
        ACT_REG_OFFLINE_CQ(1161,"注册送话费-重庆-线下"),

        ACT_REG_NET_XA(1170,"注册送话费-西安-线上"),
        ACT_REG_OFFLINE_XA(1171,"注册送话费-西安-线下"),

        ACT_REG_ONLINE_TENXIN(1180,"注册送话费-腾讯广点通"),

        ACT_REG_SPEC_CHANAN(1190, "注册抽话费-长安-招聘会"),
        ACT_REG_SPEC_FOSHAN(1200, "注册抽话费-佛山-招聘会"),
        ACT_REG_SPEC_ZHENSAN(1210, "注册抽话费-中山-招聘会"),
        ACT_REG_SPEC_XANGTAN(1220, "注册抽话费-湘潭-招聘会"),
        ACT_REG_SPEC_ZHUZOU(1230, "注册抽话费-郴州-招聘会"),
        ACT_REG_SPEC_JINZOU(1240, "注册抽话费-荆州-招聘会"),
        ACT_REG_SPEC_NANJIN(1250, "注册抽话费-南京-招聘会"),
        ACT_REG_SPEC_TANXIA(1260, "注册抽话费-塘厦-招聘会"),
        ACT_REG_SPEC_JIANGMAN(1270, "注册抽话费-江门-招聘会"),
        ACT_REG_SPEC_HENGYANG(1280,"注册抽话费-衡阳-招聘会"),


        SCHOOL_DG_ZIYUEJISU(2000,"校园活动-东莞职业技术学院");


        private int value;
    	private String name;
    	
    	public int getValue() {
			return value;
		}
    	
    	public String getName() {
    		return name;
    	}

		private ComeFrom(int value, String name) {
    		this.value = value;
    		this.name = name;
    	}

        public static ComeFrom getTypeByValue(Integer value) {
            if(value == null) return ComeFrom.WEB;
            for(ComeFrom status : values()) {
                if(status.getValue() == value.intValue()) return status;
            }
            return ComeFrom.WEB;
        }
    }
    
    /**
     * 
    * 互动招聘地区
    * @author xh 
    * @date 2014-2-27 下午2:53:49 
    *
     */
    public enum IrcArea {
    	GC("东莞莞城", 116, 14010000),
        CA("东莞长安", 26, 14010000),
        TX("东莞塘厦", 54, 14010000),
        CZ("郴州", 57, 28070000),
        FS("佛山", 63, 14090000),
        JM("江门", 70, 14080000),
        ZS("中山", 91, 14040000),
        CQ("重庆", 96, 13000000),
        WH("武汉", 102, 27010000),
        CS("长沙", 104, 28010000),
        NC("南昌", 110, 29010000),
        NB("宁波", 134, 15020000),
        NJ("南京", 184, 16010000),
        HY("衡阳", 185, 28060000),
        JZ("荆州", 186, 27040000),
        XA("西安", 74, 23010000),
        HF("合肥", 175, 24010000),
        XT("湘潭", 212, 28020000);
    	
    	//招聘会地区
    	private Integer area;
        //招聘会地区名称
        private String areaName;
    	//招聘会地区所属城市
    	private Integer location;
    	
    	public Integer getArea() {
			return area;
		}

		public String getAreaName() {
			return areaName;
		}

		public Integer getLocation() {
			return location;
		}
		
		/**
		 * 
		* 通过地区id获取城市编码
		* @param @param area
		* @param @return    设定文件 
		* @return Integer    返回类型 
		* @throws
		 */
		public static Integer getLocationByArea(int area) {
			for(IrcArea ircArea : IrcArea.values()) {
				if(ircArea.getArea() == area)
					return ircArea.getLocation();
			}
			return 0;
		}

		private IrcArea(String areaName, Integer area, Integer location) {
    		this.area = area;
    		this.areaName = areaName;
    		this.location = location;
    	}
    }

    /**
     * 企业简历管理 => 评定标签
     * 不符合/待定/符合/待邀请/已邀请/已面试/面试通过/面试不过/录用
     */
    public enum ComReceiveMark {

        WAIT_OPTION(0,"待处理"),
        NOPASS(1, "不符合"),
        THINK(2, "待定"),
        PASS(3, "符合"),
        WAIT_INVITE(4, "待邀请"),
        INVITED(5, "已邀请"),
        INTERVIEW(6, "已面试"),
        INTERVIEW_PASS(7, "面试通过"),
        INTERVIEW_NO_PASS(8, "面试不过"),
        OFFER(9, "录用"),
        REQ_RESUME(12, "邀请发简历");

        private int value;
        private String descript;

        public int getValue() {
            return value;
        }

        public String getDescript() {
            return descript;
        }

        private ComReceiveMark(int value, String descript) {
            this.value = value;
            this.descript = descript;
        }

        public static String getDescriptByValue(int value) {
            for(PositionStatus status : PositionStatus.values()) {
                if(status.getValue() == value)
                    return status.getDescript();
            }
            return "";
        }
    }


    /**
     * 访问入口通道
     * @author leo
     *
     */
    public enum AccessChannel {
    	WEB("web"), API_WEIXIN("api_weixin"), API_MOBILE("api_mobile");
    	
    	private String value;
    	
    	public String getValue(){
    		return value;
    	}
    	
    	private AccessChannel(String value){
    		this.value = value;
    	}
    }

    /**
     * 急聘标志
     */
    public enum urgentFlag {

        UN_URGENT(0,"普通"),

        URGENT(1,"急聘");

        private int value;
        private String name;

        urgentFlag(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 地区类型
     * @author leo
     *
     */
    public enum AreaType {
    	PROV, CITY, TOWN;
    }
    /**
     * IRC服务项的服务状态
     */
    public enum ServiceStatus{

        CANCEL(-1,"服务已取消"),
        WAIT_COMFIRM(1,"待顾问确认"),
        AUDITING(2,"待会务审核"),
        AUDIT_FAILURE(3,"会务审批失败"),
        AUDIT_SUCCESS(4,"会务审批通过"),
        ENTRY_POSITION(5,"待录入职位"),
        AUDIT_POSITION(6,"待审核职位"),
        PRINTING_POSTER(7,"待打印海报"),
        AUDIT_POS_FAIL(8,"职位审批失败"),
        PRINTED_POSTER(9,"已打印海报"),
        WAIT_MAKING_POSTER(14,"等待制作广告"),
        MAKING_POSTER(10,"开始制作广告"),
        WAIT_POSTER(11,"等待广告上线"),
        PUBLIC_POSTER(12,"开始上线广告"),
        WAIT_COMPOSING(19,"等待报纸排版"),
        START_COMPOSING(16,"开始报纸排版"),
        WAIT_PRINTING(15,"等待报纸印刷"),
        START_PRINTING(17,"开始报纸印刷"),
        SERVICE_END(100,"服务结束");

        private Integer value;
        private String name;


        private ServiceStatus(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public ServiceStatus getServiceStatus(Integer value) {
            for(ServiceStatus serviceStatus : values()) {
                if(serviceStatus.getValue() == value)
                    return serviceStatus;
            }
            return null;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

    }


    /**
     * 搜索的排序值
     */
    public enum SearchSortBy{

        UPDATETIME(Constants.SORT_BY_UPDATETIME,"更新时间"),
        DEGREE(Constants.SORT_BY_DEGREE_AND_UPDATEDATE,"学历"),
        WORKYEAR(Constants.SORT_BY_WORKYEAR,"工作经验"),
        CREAEDATE(Constants.SORT_BY_CREATEDATE,"初次发布时间"),
        URGENT(Constants.SORT_BY_URGENT,"急聘"),
        HOT(Constants.SORT_BY_HOT,"热度（查看数）"),
        SALARY(Constants.SORT_BY_SALARY,"薪资");

        private int value;
        private String name;

        private SearchSortBy(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

    }
    
    /**
	 * 
	* @ClassName: ReCommandType 
	* @Description: 推荐方式
	* @author xh 
	* @date 2013-9-26 上午9:15:28 
	*
	 */
	public enum ReCommandType {
		/**
		 * 把求职者推荐给企业
		 */
		PER_TO_COM("把求职者推荐给企业"),
		/**
		 * 把企业推介给求职者
		 */
		COM_TO_PER("把企业推介给求职者"),
		/**
		 * 求职者与企业互推
		 */
		COM_PER_ALL("求职者与企业互推");
		
		public static ReCommandType getReCommandType(Integer value) {
            for(ReCommandType reCommandType : values()) {
                if(reCommandType.ordinal() == value)
                    return reCommandType;
            }
            return null;
        }
		
		private String recommandName;
		
		public String getRecommandName() {
			return recommandName;
		}

		private ReCommandType(String recommandName) {
			this.recommandName = recommandName;
		}

		@Override
		public String toString() {
			return recommandName;
		}
	}
	
	/**
	 * <p>招聘会产品状态</p>
	 * 
	 * Date:2015-4-23 下午2:09:08
	 * @author xh
	 * @version 1.0
	 */
	public enum IrcPordStatus {
		/**
		 * 未发布
		 */
		UNPUBLISHED(1, "未发布"),
		/**
		 * 已发布
		 */
		PUBLISHED(2, "已发布"),
		/**
		 * 下架
		 */
		STOPPUBLISH(3, "下架"),
		/**
		 * 过期
		 */
		EXPIRE(4, "过期");
		
		private Integer status;
		private String desc;
		
		public Integer getStatus() {
			return status;
		}

		public String getDesc() {
			return desc;
		}

		private IrcPordStatus(Integer status, String desc) {
			this.status = status;
			this.desc = desc;
		}
		
	}

}
