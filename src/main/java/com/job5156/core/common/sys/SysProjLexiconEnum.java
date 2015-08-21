package com.job5156.core.common.sys;

import org.apache.commons.lang.StringUtils;

/**
 * 后台词库任务
 * 
 * @author leo
 * 
 */
public class SysProjLexiconEnum {

	/**
	 * 词库任务状态枚举设置
	 * 
	 * @author leo
	 * 
	 */
	public enum TaskStatusEnum {
		NOT_EXECUTE(0, "未执行"), EXECUTED(1, "已执行");

		private Integer value;
		private String name;

		private TaskStatusEnum(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public static String getNameByValue(Integer value) {
			if (value != null) {
				for (TaskStatusEnum taskStatus : TaskStatusEnum.values()) {
					if (taskStatus.getValue().equals(value)) {
						return taskStatus.getName();
					}
				}
			}
			return "";
		}
	}

	/**
	 * 子任务状态枚举设置
	 * 
	 * @author leo
	 * 
	 */
	public enum TaskItemStatusEnum {
		NOT_EXECUTE(0, "未执行"), SUCCESS(1, "成功"), FAILED(2, "失败");

		private Integer value;
		private String name;

		private TaskItemStatusEnum(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public static String getNameByValue(Integer value) {
			if (value != null) {
				for (TaskItemStatusEnum itemStatus : TaskItemStatusEnum.values()) {
					if (itemStatus.getValue().equals(value)) {
						return itemStatus.getName();
					}
				}
			}
			return "";
		}
	}

	/**
	 * 子任务调用类型
	 * 
	 * @author leo
	 * 
	 */
	public enum TaskItemCallTypeEnum {
		API("API"), EJB("EJB");

		private String name;

		public String getName() {
			return name;
		}

		private TaskItemCallTypeEnum(String name) {
			this.name = name;
		}

		public static TaskItemCallTypeEnum getInstance(String name) {
			for (TaskItemCallTypeEnum callType : TaskItemCallTypeEnum.values()) {
				if (StringUtils.equals(callType.getName(), name)) {
					return callType;
				}
			}
			return null;
		}
	}

	/**
	 * 子任务操作类型
	 * 
	 * @author leo
	 * 
	 */
	public enum TaskItemOperEnum {
		DEL("del"), ADD("add");

		private String name;

		public String getName() {
			return name;
		}

		private TaskItemOperEnum(String name) {
			this.name = name;
		}

		public static TaskItemOperEnum getInstance(String name) {
			for (TaskItemOperEnum oper : TaskItemOperEnum.values()) {
				if (StringUtils.equals(oper.getName(), name)) {
					return oper;
				}
			}
			return null;
		}
	}

}
