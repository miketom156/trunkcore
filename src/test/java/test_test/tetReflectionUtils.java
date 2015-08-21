/**
 * 
 */
package test_test;

import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils;

import com.job5156.core.entity.per.PerSearcher;

/**
 * @author lyh
 * @Description 
 * @date 2015年7月3日
 * 
 */
public class tetReflectionUtils {

	/** 
	 * @Description: TODO
	 * @param @param args   
	 * @return void
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Method me=ReflectionUtils.findMethod(PerSearcher.class, "getName");
		System.out.println(me);
		
		
	}

}
