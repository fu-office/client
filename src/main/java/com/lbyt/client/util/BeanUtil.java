package com.lbyt.client.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * base useful tool
 * @author zhenglianfu
 *
 */
public class BeanUtil {
	
	/**	
	 * 
	 * 	if target or src is null, return src you gived
	 *  copy fields valus from src to target
	 *  base on setter and getter
	 *  your object must under the javaBean standard
	 *  
	 * @param target
	 * @param src
	 * @return
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public static Object bulidBean(Object target, Object src) throws IllegalAccessException, SecurityException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException{
		if (target == null || src == null) {
			return target;
		}
		Class<? extends Object> srcClass = Class.forName(src.getClass().getName());
		Class<? extends Object> targetClass = Class.forName(target.getClass().getName());
		Field[] srcFieleds = srcClass.getDeclaredFields();
		for (int i = 0, len = srcFieleds.length; i < len; i++) {
			String field = srcFieleds[i].getName();
			field = field.substring(0, 1).toUpperCase() + field.substring(1);
			Method setMethod, getMethod;
			try {
				setMethod = targetClass.getMethod("set" + field, srcFieleds[i].getType());
				getMethod = srcClass.getMethod("get" + field);
				setMethod.invoke(target, getMethod.invoke(src, null));
			}catch(Exception e){
				System.out.println(e);
			}
		}
		return target;
	}
	
}
