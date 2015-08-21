package com.job5156.core.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 用于标记当前网站的访问
 * </p>
 * 
 * <pre>
 * 标记进入网站某一个业务的访问，跟踪用户的访问行为
 * </pre>
 * 
 * @author leo
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Mark {
	
	/**
	 * 系统默认标记参数，存储在COOKIE中
	 */
	static final String SPREAD_KEY = "spreadParam";
	
	/**
	 * 标记访问类型枚举
	 * 
	 * @author leo
	 * 
	 */
	public enum MarkType {
		/**
		 * 电商频道首页访问标记
		 */
		ESHOP_MARK_SPREAD;
	}

	/**
	 * 标记访问类型
	 * 
	 * @return
	 */
	MarkType type();

	/**
	 * 推广访问VAL
	 * 
	 * @return
	 */
	String spreadParam() default "";
	
	/**
	 * 推广访问KEY
	 * @return
	 */
	String spreadKey() default SPREAD_KEY;
}
