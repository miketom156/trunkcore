package com.job5156.core.common.sys;

public class CommonEnum {
	/**
	 * 
	 * @ClassName: OperatorType
	 * @Description: 操作类型枚举
	 * @author xh
	 * @date 2013-9-13 下午3:00:43
	 * 
	 */
	public enum OperatorType {
		/**
		 * 审核通过 
		 */
		AUDIT_PASS("审核通过"),
		/**
		 * 审核未通过
		 */
		AUDIT_FAILURE("审核未通过"),
		/**
		 * 修改
		 */
		UPDATE("修改"),
		/**
		 * 删除
		 */
		DELETE("删除"),
		/**
		 * 已封锁
		 */
		LOCKED("已封锁"),
		/**
		 * 已解锁
		 */
		UNLOCK("已解锁"),
		/**
		 * 已删除该简历投递记录(ComReceive表中的记录)
		 */
		DELETE_APPLY("删除");

		private String value;

		OperatorType(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}

	/**
	 * 
	 * @ClassName: OperatorContent
	 * @Description: 操作内容
	 * @author xh
	 * @date 2013-9-13 下午3:13:32
	 * 
	 */
	public enum OperatorContent {
		AUDIT_PASS("的简历被审核通过"), AUDIT_FAILURE("的简历不完善，审核未通过"),

		// 修改
		BASE_INFO_UPDATE("的简历其个人基本信息被修改"), EDUCATION_INFO_UPDATE("的简历其教育信息被修改"), TRAIN_INFO_UPDATE("的简历其培训信息被修改"), WORK_INFO_UPDATE(
				"的简历其工作经验被修改"), INTENT_INFO_UPDATE("的简历其求职意向被修改"), LANGUAGE_INFO_UPDATE("的简历其语言技能被修改"), CERTIFICATE_INFO_UPDATE(
				"的简历其证书信息被修改"), PROJECT_INFO_UPDATE("的简历其项目经验被修改"), ATTACHMENT_INFO_UPDATE("的简历其附件信息被修改"),

		// 删除
		BASE_INFO_DELETE("的简历其个人基本信息被删除"), EDUCATION_INFO_DELETE("的简历其教育信息被删除"), TRAIN_INFO_DELETE("的简历其培训信息被删除"), WORK_INFO_DELETE(
				"的简历其工作经验被删除"), INTENT_INFO_DELETE("的简历其求职意向被删除"), LANGUAGE_INFO_DELETE("的简历其语言技能被删除"), CERTIFICATE_INFO_DELETE(
				"的简历其证书信息被删除"), PROJECT_INFO_DELETE("的简历其项目经验被删除"), ATTACHMENT_INFO_DELETE("的简历其附件信息被删除"), RESUME_DELETE(
				"的简历被删除"),APPLY_INFO_DELETE("该简历的应聘记录已被删除"),

		// 锁定
		RESUME_LOCKED("的账户被封锁,原因是:"),
		// 解锁
		RESUME_UNLOCKED("的账户被解锁");

		private String value;

		OperatorContent(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}

	/**
	 * <p>
	 * 后台用户状态<br>
	 * Y:正常状态<br>
	 * N:被暂停使用<br>
	 * S:非正式用户<br>
	 * </p>
	 * 
	 * @author leo
	 * 
	 */
	public enum UserStatus {
		NORMAL("Y"), SUSPEND("N"), INFORMAL("S");

		private String value;

		UserStatus(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}

	/**
	 * <p>
	 * 后台用户等级
	 * </p>
	 * 
	 * @author leo
	 * 
	 */
	public enum UserGrade {
		ADMINISTRATOR(1, "管理员"), DIRECTOR(2, "总监"), MANAGER(3, "经理"), HEAD(4, "主管"), GENERAL(5, "一般人员");

		private Integer value;
		private String name;

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		private UserGrade(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public static String getNameByValue(Integer value) {
			for (UserGrade grade : UserGrade.values()) {
				if (grade.getValue().equals(value)) {
					return grade.getName();
				}
			}
			return "";
		}
	}

	/**
	 * <p>
	 * 后台模块状态<br>
	 * NORMAL:正常状态<br>
	 * FORBID:停用状态<br>
	 * </p>
	 * 
	 * @author leo
	 * 
	 */
	public enum ModuleStatus {
		NORMAL(1), FORBID(0);

		private Integer value;

		ModuleStatus(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	/**
	 * <P>
	 * 后台组织架构权限选项<br>
	 * ALL:所有组织架构范围内<br>
	 * DEPARTMENT:当前部门<br>
	 * PERSONAL:个人<br>
	 * OTHER:其他<br>
	 * </P>
	 * 
	 * @author leo
	 * 
	 */
	public enum DepartmentRightOption {
		ALL(1), DEPARTMENT(3), PERSONAL(5), OTHER(7);

		private Integer value;

		DepartmentRightOption(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	/**
	 * <P>
	 * 后台地区权限选项<br>
	 * ALL:所有地区范围内<br>
	 * OTHER:其他<br>
	 * </P>
	 * 
	 * @author leo
	 * 
	 */
	public enum RegionRightOption {
		ALL(1), OTHER(7);

		private Integer value;

		RegionRightOption(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	/**
	 * <P>
	 * 显示状态<br>
	 * DISPLAY:显示<br>
	 * HIDE:隐藏<br>
	 * </P>
	 * 
	 * @author leo
	 * 
	 */
	public enum DisplayStatus {
		DISPLAY(1), HIDE(0);

		private Integer value;

		DisplayStatus(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	/**
	 * LOGO资源使用标记
	 * 
	 * @author leo
	 * 
	 */
	public enum LogoUseFlag {
		UNUSED(0, "未使用"), USED(1, "已使用");

		private Integer value;
		private String name;

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		private LogoUseFlag(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public static String getNameByValue(Integer value) {
			for (LogoUseFlag useFlag : LogoUseFlag.values()) {
				if (useFlag.getValue().equals(value)) {
					return useFlag.getName();
				}
			}
			return "";
		}
	}

	/**
	 * LOGO资源使用状态
	 * 
	 * @author leo
	 * 
	 */
	public enum LogoUseStatus {
		UNUSED(1, "未曾使用"), USING(3, "正在使用"), WAITING(5, "排队等待"), EXPIRED(7, "广告过期");

		private Integer value;
		private String name;

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		private LogoUseStatus(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public static String getNameByValue(Integer value) {
			for (LogoUseStatus useStatus : LogoUseStatus.values()) {
				if (useStatus.getValue().equals(value)) {
					return useStatus.getName();
				}
			}
			return "";
		}
	}

	/**
	 * LOGO类型
	 * 
	 * @author leo
	 * 
	 */
	public enum LogoType {
		PICTURE(1, "图片"), FLASH(2, "动画");

		private Integer value;
		private String name;

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		private LogoType(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public static String getNameByValue(Integer value) {
			for (LogoType logoType : LogoType.values()) {
				if (logoType.getValue().equals(value)) {
					return logoType.getName();
				}
			}
			return "";
		}
	}

	/**
	 * 分站点类型
	 * 
	 * @author leo
	 * 
	 */
	public enum WebSiteType {
		COMPANY(0, "总公司"), BRANCH_COMPANY(1, "分公司"), WEBSITE(2, "分站点"), OTHERS(3, "其他"), INDUSTRY(4, "行业专区");

		private Integer value;
		private String name;

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
		
		private WebSiteType(Integer value, String name) {
			this.value = value;
			this.name = name;
		}
		
		public static String getNameByValue(Integer value) {
			for (WebSiteType siteType : WebSiteType.values()) {
				if (siteType.getValue().equals(value)) {
					return siteType.getName();
				}
			}
			return "";
		}
	}

    /**
     * 返点执行审核状态
     */
    public enum AduitPointsEnum {

        AUDIT_NO_PASS (-1,"不通过"),
        AUDIT_WAIT (0,"待审核"),
        AUDIT_PASS (1,"已执行");

        private Integer value;
        private String name;

        private AduitPointsEnum(Integer value, String name) {
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
     * 抽奖活动状态   1：生效 0：待审核  -1：已结束     为方便排序，从大到小
     */
    public enum ActSweepstakesEnum {

        STK_EFFECTIVE (1,"生效"),
        WAIT_APPLY (0,"待审核"),
        STK_FINISHED (-1,"已结束");


        private Integer value;
        private String name;

        private ActSweepstakesEnum(Integer value, String name) {
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
     * 抽奖活动奖品状态  0：待兑换    1:待审核   2：已兑换  3：逾期
     */
    public enum ActPrizeEnum {

        WAIT_APPLY (0,"待申请"),
        WAIT_APPROVE (1,"待审核"),
        PRIZE_PASS (2,"已兑换"),
        PRIZE_EXPIRY (3,"已逾期");

        private Integer value;
        private String name;

        private ActPrizeEnum(Integer value, String name) {
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
}
