/**
 * 
 */
package com.job5156.core.common.helper;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.job5156.core.common.Constants;
import com.job5156.core.common.util.MD5Builder;
import com.job5156.core.eao.RedisEao;

/**
 * @author lyh
 * @Description
 * @date 2015年8月4日
 */
@Component
public class ValidationHelper {
		@Resource
		private RedisEao redisEao;

		/**
		 * 生成邮件验证码，此验证码自带一定的时效性 对应id如果在一定时间内生成过验证码的，不重复生成第二次，而是直接返回空字符串
		 * 
		 * @param mail
		 * @param id
		 * @return
		 */
		public String buildMailValidationCode(String mail, Integer id) {
			String isSendKey = Constants.REDIS_KEY_PER_MAIL_VALIDATION_IS_SEND_PREFIX + id;
			if (redisEao.isKeyExist(isSendKey)) { // 验证已经生成过了，在有此key的情况下不重新生成
				return "";
			} else {
				String validCode = MD5Builder.md5(mail, ObjectUtils.toString(System.currentTimeMillis()), ObjectUtils.toString(id));
				redisEao.writeToString(Constants.REDIS_KEY_PER_MAIL_VALIDATION_PREFIX + validCode, ObjectUtils.toString(id, ""));
				redisEao.setKeyExpire(Constants.REDIS_KEY_PER_MAIL_VALIDATION_PREFIX + validCode, Constants.REDIS_EXPIRE_TIME_PER_MAIL_VALIDATION);
				redisEao.writeToString(isSendKey, validCode);
				redisEao.setKeyExpire(isSendKey, Constants.REDIS_EXPIRE_TIME_PER_MAIL_VALIDATION);
				return validCode;
			}
		}

		public Integer checkMailValidationCode(String validCode) {
			if (!redisEao.isKeyExist(Constants.REDIS_KEY_PER_MAIL_VALIDATION_PREFIX + validCode)) {
				return 0; // Code在Redis中已过期
			} else {
				Integer id = NumberUtils.toInt(redisEao.readFromString(Constants.REDIS_KEY_PER_MAIL_VALIDATION_PREFIX + validCode), 0);
				// 这里需要考虑是否要将这个redis key直接移除掉？还是等它自己的生存周期过期？
				return id;
			}
		}

		/**
		 * 生成短信验证码，此验证码自带一定的时效性 对应id如果在一定时间内生成过验证码的，不重复生成第二次，而是直接返回空字符串
		 * 
		 * @param mobile
		 * @param id
		 * @return
		 */
		public String buildSMSValidationCode(String mobile, Integer id) {
			String isSendKey = Constants.REDIS_KEY_PER_SMS_VALIDATION_IS_SEND_PREFIX + id;
			if (redisEao.isKeyExist(isSendKey)) { // 验证已经生成过了，在有此key的情况下不重新生成
				return "";
			} else {
				// 随机生成6位数字
				String[] code = new String[6];
				for (int i = 0; i < 6; i++) {
					code[i] = ObjectUtils.toString((int) (Math.random() * 10));
				}
				String validCode = StringUtils.join(code);
				redisEao.writeToString(Constants.REDIS_KEY_PER_SMS_VALIDATION_PREFIX + validCode, ObjectUtils.toString(id, ""));
				redisEao.setKeyExpire(Constants.REDIS_KEY_PER_SMS_VALIDATION_PREFIX + validCode, Constants.REDIS_EXPIRE_TIME_PER_SMS_VALIDATION);
				redisEao.writeToString(isSendKey, validCode);
				redisEao.setKeyExpire(isSendKey, Constants.REDIS_EXPIRE_TIME_PER_SMS_VALIDATION);
				return validCode;
			}
		}

		/**
		 * 删除已经验证过的KEY
		 * 
		 * @param mobile
		 * @param id
		 * @return
		 */
		public boolean delSMSValidationCode(String mobile, Integer id, String validCode) {
			String isSendKey = Constants.REDIS_KEY_PER_SMS_VALIDATION_IS_SEND_PREFIX + id;
			try {
				redisEao.del(isSendKey);
				redisEao.del(Constants.REDIS_KEY_PER_SMS_VALIDATION_PREFIX + validCode);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		public Integer checkSMSValidationCode(String validCode) {
			if (!redisEao.isKeyExist(Constants.REDIS_KEY_PER_SMS_VALIDATION_PREFIX + validCode)) {
				return 0; // Code在Redis中已过期
			} else {
				Integer id = NumberUtils.toInt(redisEao.readFromString(Constants.REDIS_KEY_PER_SMS_VALIDATION_PREFIX + validCode));
				// 这里需要考虑是否要将这个redis key直接移除掉？还是等它自己的生存周期过期？
				return id;
			}
		}

		/**
		 * 生成动态码，此动态码自带一定的时效性 对应id如果在一定时间内生成过动态码的，不重复生成第二次，而是直接返回空字符串
		 * 
		 * @param id
		 * @return
		 */
		public String buildDynamicCode(Integer id) {
			String isSendKey = Constants.REDIS_KEY_PER_LOGIN_DYNAMIC_CODE_VALIDATION_IS_SEND_PREFIX + id;
			if (redisEao.isKeyExist(isSendKey)) { // 动态码已经生成过了，在有此key的情况下不重新生成
				return "";
			} else {
				// 随机生成6位数字
				String[] code = new String[6];
				for (int i = 0; i < 6; i++) {
					code[i] = ObjectUtils.toString((int) (Math.random() * 10));
				}
				String validCode = StringUtils.join(code);
				redisEao.writeToString(Constants.REDIS_KEY_PER_LOGIN_DYNAMIC_CODE_PREFIX + validCode, ObjectUtils.toString(id, ""));
				redisEao.setKeyExpire(Constants.REDIS_KEY_PER_LOGIN_DYNAMIC_CODE_PREFIX + validCode, Constants.REDIS_EXPIRE_TIME_PER_LOGIN_DYNAMIC_CODE);
				redisEao.writeToString(isSendKey, validCode);
				redisEao.setKeyExpire(isSendKey, Constants.REDIS_EXPIRE_TIME_PER_LOGIN_DYNAMIC_CODE);
				return validCode;
			}
		}

		public Integer checkDynamicCode(String validCode) {
			if (!redisEao.isKeyExist(Constants.REDIS_KEY_PER_LOGIN_DYNAMIC_CODE_PREFIX + validCode)) {
				return 0; // Code在Redis中已过期
			} else {
				Integer id = NumberUtils.toInt(redisEao.readFromString(Constants.REDIS_KEY_PER_LOGIN_DYNAMIC_CODE_PREFIX + validCode));
				return id;
			}
		}

		/**
		 * 根据uuid及用户ID生成token
		 * 
		 * @param uuid
		 * @param userId
		 * @return
		 */
		public String buildMobileToken(String uuid, Integer userId) {

			if (StringUtils.isBlank(uuid) || userId <= 0) {
				return ""; // 没有传入uuid或userId
			} else {
				// 保存token信息到Redis
				// 保存的格式为string
				// 值内容应为 【 uuid,userid 】
				// 生存期为30天
				String token = MD5Builder.md5(uuid + userId);
				redisEao.writeToString(Constants.REDIS_KEY_MOBILE_API_TOKEN_PREFIX + token, uuid + "," + userId);
				redisEao.setKeyExpire(Constants.REDIS_KEY_MOBILE_API_TOKEN_PREFIX + token, Constants.REDIS_EXPIRE_TIME_MOBILE_API_TOKEN);
				return token;
			}
		}

		/**
		 * 登陆验证码校验
		 * 
		 * @param captchaCode
		 * @return
		 */
		public boolean checkCaptchaCode(String captchaCode) {
			if (StringUtils.isNotBlank(captchaCode)) {
				String captchaKey = Constants.REDIS_KEY_CAPTCHA_PREFIX + StringUtils.lowerCase(captchaCode);
				boolean checkFlag = redisEao.isKeyExist(captchaKey);
			   System.out.println(checkFlag+"---------");
				if (checkFlag) {
					redisEao.del(captchaKey);
					return true;
				}
			}
			return false;
		}

		/**
		 * 生成手机验证码，用户还没有帐号
		 * 
		 * @param mobile
		 *            手机号
		 * @return
		 */
		public String buildSMSValidationCode(String mobile) {
			String redisKey = Constants.REDIS_KEY_PER_SMS_VALIDATION_PREFIX + mobile;
			if (redisEao.isKeyExist(redisKey)) { // 验证已经生成过了，在有此key的情况下不重新生成
				return "";
			} else {
				// 随机生成6位数字
				String[] code = new String[6];
				for (int i = 0; i < 6; i++) {
					code[i] = ObjectUtils.toString((int) (Math.random() * 10));
				}
				String validCode = StringUtils.join(code);
				redisEao.writeToString(redisKey, validCode);
				redisEao.setKeyExpire(redisKey, Constants.REDIS_EXPIRE_TIME_PER_SMS_VALIDATION);
				return validCode;
			}
		}

		/**
		 * 比较手机验证码
		 * 
		 * @param mobile
		 * @param validCode
		 * @return
		 */
		public boolean checkSMSValidationCode(String mobile, String validCode) {
			String redisKey = Constants.REDIS_KEY_PER_SMS_VALIDATION_PREFIX + mobile;
			if (redisEao.isKeyExist(redisKey)) {
				String vCode = redisEao.readFromString(redisKey);
				return vCode.equals(validCode);
			}
			return false;
		}

		/**
		 * 删除已生成的验证码KEY
		 * 
		 * @param key
		 * @return
		 */
		public boolean delVcodeByRedisKey(String key) {
			try {
				redisEao.del(key);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

}
