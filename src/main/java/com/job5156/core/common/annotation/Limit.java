package com.job5156.core.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 用于网站部分业务禁止用户访问
 * </p>
 * 
 * <pre>
 * 在控制器相应入口加上<code>@Limit</code>注释，同时标明限制类型
 * </pre>
 * 
 * @author leo
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Limit {
	
	/**
	 * 
	* @ClassName LimitCategory
	* @Description 访问限制类别
	* @Author xh
	* @Date 2015-2-4下午5:38:12
	 */
	public enum LimitCategory {
		/**
		 * 访问频率过高
		 */
		HIGH_FREQUENCY,
		/**
		 * 禁止JSON访问
		 */
		JSON_FOBID;
	}

	/**
	 * 访问限制类型枚举
	 * 
	 * @author leo
	 * 
	 */
	public enum LimitType {
		/**
		 * 访问搜索频率过高
		 */
		SEARCH_ACCESS_WITH_HIGH_FREQUENCY(LimitCategory.HIGH_FREQUENCY),
		
		/**
		 * 访问职位频率过高
		 */
		POS_ACCESS_WITH_HIGH_FREQUENCY(LimitCategory.HIGH_FREQUENCY),
		
		/**
		 * 限制JSON请求访问
		 */
		JSON_REQUEST_FOBID(LimitCategory.JSON_FOBID),

        /**
         * 手机验证使用频率过高
         */
        VERIFY_MOBILE__WITH_HIGH_FREQUENCY(LimitCategory.HIGH_FREQUENCY);
		
		private LimitCategory limitCategory;

		public LimitCategory getLimitCategory() {
			return limitCategory;
		}

		private LimitType(LimitCategory limitCategory) {
			this.limitCategory = limitCategory;
		}
	}

	/**
	 * 限制访问类型
	 * 
	 * @return
	 */
	LimitType[] type();
	
	/**
	 * 
	* @Title useCaptchaValid
	* @Description 当访问频率过高时是否允许通过验证码验证后继续访问（防止机器人爬行，减少误杀正常访问）,
	* 默认为false,仅对访问频率过高类型的限制有效
	* @return
	 */
	boolean useCaptchaValid() default false;
}
