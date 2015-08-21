package com.job5156.core.common;

/**
 * 
* @ClassName: PersistEnum 
* @Description: 需要持久化的enum类，都需要实现的接口 
* @author xh 
* @date 2013-8-13 上午11:22:46 
* 
* @param <E>
 */
public interface PersistEnum<E extends Enum<?>> {
    /**
     * 获取被持久化字段的值
     *
     * @return 被持久化字段的值
     */
    Integer getValue();
    /**
     * 
    * @Title: setVlaue 
    * @Description: 设置持久化值
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    void setValue(Integer value);
     
    /**
     * 由被持久化的字段的值获取枚举类型
     *
     * @param persistedValue
     * @return
     */
    E returnEnum(Integer persistedValue);
}

