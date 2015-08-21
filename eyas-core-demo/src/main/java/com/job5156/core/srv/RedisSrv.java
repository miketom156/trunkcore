package com.job5156.core.srv;

import com.job5156.core.common.Constants;
import com.job5156.core.common.util.MD5Builder;
import com.job5156.core.eao.RedisEao;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * <p>redis缓存核心业务类</p>
 * Date:2015/5/20 10:50
 *
 * @author pzm
 * @version 1.0
 */
@Service
public class RedisSrv {
    @Resource
    private RedisEao redisEao;

    /**
     * 判断Redis key是否存在
     * @param key Key
     * @return 存在返回true，否则返回false
     */
    public Boolean isKeyExist(String key) {
        return redisEao.isKeyExist(key);
    }

    /**
     * 将值对象写入Redis，保存类型为Hash（哈希表）
     * @param key Key
     * @param vo 值对象的实体，需带待写入的值
     * @return 成功写入返回true，否则返回false
     */
    public <T> Boolean writeRedisVoToHash(String key, T vo) {
        return redisEao.writeRedisVoToHash(key, vo);
    }

    /**
     * 从左向右读取对象列表，索引位置错误不返回异常，返回列表为空
     * @param key
     * @param cla
     *@param begin
     * 			索引开始位置[0：第一个元素]
     * @param end
     * 			索引结束位置[-1：最后一个元素，-2：倒数第2个元素...]   @return
     */
    public <T> List<T> readObjectFromList(String key, Class<T> cla, int begin, int end) {
        return redisEao.readObjectFromList(key, cla, begin, end);
    }

    /**
     * 将内容推入队列，队列是类型为SortedSet的Redis数据结构
     * 用zAdd的方法
     * @param key Key
     * @param list 待写入的列表值
     */
    public void zAdd(String key, List<String> list) {
        redisEao.zAdd(key, list);
    }

    /**
     * 从Redis的Set中移除某个值
     * @param key
     * @param value
     * @return
     */
    public Boolean removeFromSet(String key, String value) {
        return redisEao.removeFromSet(key, value);
    }

    public void writeToSet(String key, List<String> list) {
        redisEao.writeToSet(key, list);
    }

    /**
     * 按长度将Redis的List限制到指定大小
     * @param key Key
     * @param length 长度
     */
    public void trimList(String key, Integer length) {
        redisEao.trimList(key, length);
    }

    /**
     * 判断某个值是否存在在Redis的Set中
     * @param key Key
     * @param value 需要判断的值
     * @return 存在返回True，否则返回False
     */
    public Boolean isInSet(String key, String value) {
        return redisEao.isInSet(key, value);
    }

    /**
     *
     * 获取Redis key的生存期
     *
     * @param key@return Long    返回类型
     * @throws
     */
    public Long getKeyExpireTime(String key) {
        return redisEao.getKeyExpireTime(key);
    }

    /**
     * 从Redis的Map中移除某个key及value映射
     * @param key
     * @param mapKey
     * @return
     */
    public Boolean removeFromMap(String key, String mapKey) {
        return redisEao.removeFromMap(key, mapKey);
    }

    /**
     * 从左到右保留LIST中begin到end的元素[另外一重含义：删除不在范围内的元素]
     * @param key
     * @param begin
     * 			索引开始位置[0：第一个元素]
     * @param end
     */
    public void trimList(String key, int begin, int end) {
        redisEao.trimList(key, begin, end);
    }

    public <T> void writeObjectToMap(String key, String mapkey, T mapVal) {
        redisEao.writeObjectToMap(key, mapkey, mapVal);
    }

    /**
     * 返回有序集sortedSet中，指定数量的元素列表
     * @param key
     * @param count
     * 			元素数量
     * @return
     */
    public List<String> zRange(String key, long count) {
        return redisEao.zRange(key, count);
    }

    /**
     * 将字符串移出REDIS
     * 移出KEY对应的LIST里表中，所有的值等于VALUE的元素
     * @param key
     * @param value
     * @return
     */
    public Boolean removeFromList(String key, String value) {
        return redisEao.removeFromList(key, value);
    }

    /**
     *  将内容从Redis的Set中读取
     * @param key Key
     * @return Redis中保存的Set内容
     */
    public Set<String> readFromSet(String key) {
        return redisEao.readFromSet(key);
    }

    /**
     * 从Redis中读取Map结构数据并封装到Map对象返回
     * @param key
     * @param cla
     * @return
     */
    public <T> Map<String, T> readObjectFromMap(String key, Class<T> cla) {
        return redisEao.readObjectFromMap(key, cla);
    }

    /**
     * 将内容从Redis的List中读取
     * @param key Key
     * @return 返回对应的列表
     */
    public List<String> readFromList(String key) {
        return redisEao.readFromList(key);
    }

    /**
     * 将内容插入到Redis，保存类型为List
     * 此方法只针对key不存在的情况，将list全部写入（所以用rpush的方法）
     * 如果key已经存在了，返回false
     * @param key Key
     * @param list 待写入的列表值
     * @return 成功写入返回true，否则返回false
     */
    public Boolean writeToList(String key, List<String> list) {
        return redisEao.writeToList(key, list);
    }

    /**
     * 从Set中移除n条内容
     * @param key
     * @param valueList
     * @return
     */
    public void sRem(String key, List<String> valueList) {
        redisEao.sRem(key, valueList);
    }

    /**
     * 将内容写入到Redis，保存类型为Map
     * @param key
     * @param map
     */
    public <T> void writeObjectToMap(String key, Map<String, T> map) {
        redisEao.writeObjectToMap(key, map);
    }

    /**
     * 返回有序集合sortedSet中元素的个数
     * @param key
     * @return
     */
    public Long zCard(String key) {
        return redisEao.zCard(key);
    }

    /**
     * 从Redis中读取Map结构中某一个key的数据并返回
     * @param key
     * @param mapKey
     * @param cla
     * @return
     */
    public <T> T readObjectFromMap(String key, String mapKey, Class<T> cla) {
        return redisEao.readObjectFromMap(key, mapKey, cla);
    }

    /**
     * 删除并返回LIST中的首个元素
     * @param key
     * @param cla
     * @return
     */
    public <T> T popObjectFromList(String key, Class<T> cla) {
        return redisEao.popObjectFromList(key, cla);
    }

    /**
     * 查找某个值在sortedSet中的索引位置
     *
     * @param key sortedSet集合对应的key
     * @param value 要索引的元素值
     * @return
     */
    public Long zRank(String key, String value) {
        return redisEao.zRank(key, value);
    }

    /**
     * 增加数值保存到HASH的field中
     * @param key
     * @param field hash中的field字段
     * @param value 增加的数值
     * @return
     */
    public Long hIncrBy(String key, String field, Integer value) {
        return redisEao.hIncrBy(key, field, value);
    }

    public Long del(String key) {
        return redisEao.del(key);
    }

    /**
     * 将对象写入KEY对应的LIST对象列表
     * @param key
     * @param t
     * @return
     */
    public <T> Boolean writeObjectToList(String key, T t) {
        return redisEao.writeObjectToList(key, t);
    }

    public List<String> zRange(String key, long begin, long end) {
        return redisEao.zRange(key, begin, end);
    }

    /**
     * 将值对象从Redis的Hash（哈希表）中读取
     * @param key Key
     * @param vo 值对象的实体，存放读取出来的内容，传参时传一个new出来的空内容对象即可
     * @return 返回对应的值对象
     */
    public <T> T readRedisVoFromHash(String key, T vo) {
        return redisEao.readRedisVoFromHash(key, vo);
    }

    /**
     * 从左向右读取对象列表
     * @param key
     * @param cla
     * @return
     */
    public <T> List<T> readObjectFromList(String key, Class<T> cla) {
        return redisEao.readObjectFromList(key, cla);
    }

    /**
     * 将内容写入Redis，保存类型为String
     * @param key Key
     * @param value 内容
     */
    public void writeToString(String key, String value) {
        redisEao.writeToString(key, value);
    }

    /**
     * 获取redis中保存的map数据信息<br>
     * 注意：此方法调用在数据量较小时使用<br>
     * @param key
     * @return
     */
    public Map<String, Integer> hGetAll(String key) {
        return redisEao.hGetAll(key);
    }

    /**
     * 将内容从Redis的String类型中读取
     * @param key Key
     * @return 内容
     */
    public String readFromString(String key) {
        return redisEao.readFromString(key);
    }

    /**
     * 设置Redis key的生存期
     * @param key Key
     * @param seconds 存活期（秒）
     * @return 成功设置返回true，否则返回false
     */
    public Boolean setKeyExpire(String key, long seconds) {
        return redisEao.setKeyExpire(key, seconds);
    }

    /**
     * 将内容插入到Redis，保存类型为List
     * key不存在的话就创建，存在的话就append（lpush）
     * @param key Key
     * @param value 待写入的值
     * @return 成功写入返回true，否则返回false
     */
    public Boolean writeToList(String key, String value) {
        return redisEao.writeToList(key, value);
    }

    /**
     * 从SortedSet中移除n条内容
     * @param key
     * @param valueList
     * @return
     */
    public void zRem(String key, List<String> valueList) {
        redisEao.zRem(key, valueList);
    }

    /**
     * 给外部调用的main方法使用
     * @param redisTemplate
     */
    public void setRedisTemplate(RedisTemplate<Serializable, Serializable> redisTemplate) {
        redisEao.setRedisTemplate(redisTemplate);
    }

    /**
     * 将对象列表写入REDIS
     * 如果KEY已经存在，先删除，然后再写入
     * @param key
     * @param list
     * @return
     */
    public <T> Boolean writeObjectToList(String key, List<T> list) {
        return redisEao.writeObjectToList(key, list);
    }

    /**
     * 将内容写入到Redis，保存类型为Set
     * @param key Key
     * @param value 写入Set的值
     */
    public void writeToSet(String key, String value) {
        redisEao.writeToSet(key, value);
    }


    /**
     * 登陆验证码校验
     * @param captchaCode 校验码
     * @return
     */
    public boolean checkCaptchaCode(String captchaCode){
        if(StringUtils.isNotBlank(captchaCode)){
            String captchaKey = Constants.REDIS_KEY_CAPTCHA_PREFIX + StringUtils.lowerCase(captchaCode);
            boolean checkFlag = redisEao.isKeyExist(captchaKey);
            if(checkFlag){
                redisEao.del(captchaKey);
                return true;
            }
        }
        return false;
    }

    /**
     * 生成邮件验证码，此验证码自带一定的时效性
     * 对应id如果在一定时间内生成过验证码的，不重复生成第二次，而是直接返回空字符串
     * @param mail
     * @param id
     * @return
     */
    public String buildMailValidationCode(String mail, Integer id){
        String isSendKey = Constants.REDIS_KEY_PER_MAIL_VALIDATION_IS_SEND_PREFIX + id;
        if(redisEao.isKeyExist(isSendKey)) { //验证已经生成过了，在有此key的情况下不重新生成
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
}
