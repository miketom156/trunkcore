package com.job5156.core.srv.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.job5156.core.common.CommonEnum.CopyType;

/**
 * 
* @ClassName: ModelVoConvertService 
* @Description: 
* @author xh 
* @date 2013-8-8 下午2:51:03 
* 
* @param <M>
* @param <V>
 */
public class ModelVoConvertSrv<M,V> {
	
	/**
	 * 
	* @Title: modelJsonToVoObject 
	* @Description: 实体类中的json属性转vo类中的对象属性
	* @param @param model
	* @param @param vo    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void modelJsonToVoObject(M model, V vo) {}
	
	/**
	 * 
	* @Title: voObjectToModelJson 
	* @Description: vo类中的对象属性转实体类中的对象属性
	* @param @param vo
	* @param @param model    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void voObjectToModelJson(V vo, M model) {}
	
	@SuppressWarnings ("unchecked")
    private Class<V> getTypeParameterClass()
    {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        return (Class<V>) paramType.getActualTypeArguments()[1];
    }
	
	/**
	 * 
	* @Title: getNoCopyField 
	* @Description: 获取不需要从vo拷贝到model中的字段数组,子类可以重载该方法
	* @param @return    设定文件 
	* @return String[]    返回类型 
	* @throws
	 */
	public String[] getIgnoreProperties() {
		return null;
	}
	
	public V toVo(M m) {
		V v = null;
		
		try {
			v = getTypeParameterClass().newInstance();
		} catch(IllegalAccessException illegalAccessException) {
		} catch (InstantiationException instantiationException) {
		}
		
		if(v != null)
			copyProperties(m, v, null, CopyType.MODEL_TO_VO);
		
    	return v;
	}
	
	public List<V> toListVo(List<M> modelList) {
		List<V> voList = Lists.newArrayList();

        if(modelList != null) {
            for(M m : modelList) {
                voList.add(toVo(m));
            }
        }
    	return voList;
	}
	
	/**
	 * 
	* @Title: copyProperties 
	* @Description: copyProperties重载方法
	* @param @param source
	* @param @param target
	* @param @param type    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void copyProperties(M model, V vo, CopyType type) {
		copyProperties(model, vo, getIgnoreProperties(), type);
	}
	
	/**
	 * 
	* @Title: 属性拷贝 
	* @Description: 属性拷贝
	* @param @param source
	* @param @param target
	* @param @param ignoreProperties
	* @param @param type    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void copyProperties(M model, V vo, String[] ignoreProperties, CopyType type) {
		Assert.notNull(model, "源对象不能为空");
		Assert.notNull(vo, "目标对象不能为空");
		
		//判断拷贝类型(复杂属性转换)
		if(type == CopyType.MODEL_TO_VO) {
			//拷贝基本属性
			BeanUtils.copyProperties(model, vo, ignoreProperties);
			modelJsonToVoObject(model, vo);
		} else {
			//拷贝基本属性
			BeanUtils.copyProperties(vo, model, ignoreProperties);
			voObjectToModelJson(vo, model);
		}
	}
}
