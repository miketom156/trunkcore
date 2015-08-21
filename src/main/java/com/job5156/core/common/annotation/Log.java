package com.job5156.core.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* @ClassName: SysOperatorLog 
* @Description: 自定义后台操作日志注解
* @author xh 
* @date 2013-9-12 上午11:21:52 
*
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
	/**
     * 
    * 系统日志模块枚举
    * @author xh 
    * @date 2014-6-6 下午3:44:41 
    *
     */
    public enum LogModule {
    	/**
    	 * 企业职位管理模块
    	 */
    	COM_POSITION_MANGER(100),
    	/**
    	 * 企业信息管理模块
    	 */
    	COM_INFO_MANGER(101),
    	/**
    	 * 企业简历管理模块
    	 */
    	COM_RESUME_MANGER(102),
    	/**
    	 * 企业设置模块
    	 */
    	COM_SETTING(103),
    	/**
    	 * 个人用户设置简历公开状态
    	 */
    	PER_SET_PUBLIC_SETTING(201),
    	/**
    	 * 公共模块或不需要标注及记录的模块
    	 */
    	COMMON(999);
    	
    	private Integer moduleId;
    	
    	public Integer getModuleId() {
			return moduleId;
		}

		private LogModule(Integer moduleId) {
    		this.moduleId = moduleId;
    	}
    }
    
    /**
     * 
    * 账户类型（操作切面方法所属的用户类型）
    * @author xh 
    * @date 2014-6-6 下午8:10:47 
    *
     */
    public enum AccountType {
    	/**
    	 * 个人用户
    	 */
    	PER(0),
    	/**
    	 * 企业用户
    	 */
    	COM(1),
    	/**
    	 * 后台用户(系统用户)
    	 */
    	SYS(2);
    	
    	private Integer type;
    	
    	public Integer getType() {
			return type;
		}

		private AccountType(Integer type) {
    		this.type = type;
    	}
    }
	
    /**
     * 
    * 切面方法描述
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
	String desc();
	/**
	 * 
	* 账户类型（操作该方法所属的用户类型0=个人，1=企业，2=系统（后台））
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	AccountType userType();
	/**
	 * 
	* 所属模块
	* @param @return    设定文件 
	* @return LogModule    返回类型 
	* @throws
	 */
	LogModule module() default LogModule.COMMON;
}
