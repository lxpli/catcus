package com.work.manager.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射泛型类型工具类
 * @author Jat
 *
 */
public class ReflectUtil {
	/**
	 * 获得参数化类型的泛型类型，取第一个参数的泛型类型，（默认去的第一个）
	 * @param clazz 参数化类型
	 * @return 泛型类型
	 */
	@SuppressWarnings("rawtypes")
	public static Class getClassGenricType(final Class clazz) {
		return getClassGenricType(clazz, 0);
	}
	
	/**
	 * 根据参数索引获得参数化类型的泛型类型，（通过索引取得）
	 * @param clazz 参数化类型
	 * @param index 参数索引
	 * @return 泛型类型
	 */
	@SuppressWarnings("rawtypes")
	public static Class getClassGenricType(
			final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
	
	/** 
     * 利用反射获取指定对象的指定属性 
     * @param obj 目标对象 
     * @param fieldName 目标属性 
     * @return 目标属性的值 
     */  
    public static Object getFieldValue(Object obj, String fieldName) {  
        Object result = null;  
        Field field = ReflectUtil.getField(obj, fieldName);  
        if (field != null) {  
           field.setAccessible(true);  
           try {  
               result = field.get(obj);  
           } catch (IllegalArgumentException e) {  
               e.printStackTrace();  
           } catch (IllegalAccessException e) {  
               e.printStackTrace();  
           }  
        }  
        return result;  
    }  
     
    /** 
     * 利用反射获取指定对象里面的指定属性 
     * @param obj 目标对象 
     * @param fieldName 目标属性 
     * @return 目标字段 
     */  
    private static Field getField(Object obj, String fieldName) {  
        Field field = null;  
       for (Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {  
           try {  
               field = clazz.getDeclaredField(fieldName);  
               break;  
           } catch (NoSuchFieldException e) {  
               //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。  
           }  
        }  
        return field;  
    }  

    /** 
     * 利用反射设置指定对象的指定属性为指定的值 
     * @param obj 目标对象 
     * @param fieldName 目标属性 
      * @param fieldValue 目标值 
     */  
    public static void setFieldValue(Object obj, String fieldName,  
           String fieldValue) {  
        Field field = ReflectUtil.getField(obj, fieldName);  
        if (field != null) {  
           try {  
               field.setAccessible(true);  
               field.set(obj, fieldValue);  
           } catch (IllegalArgumentException e) {  
               e.printStackTrace();  
           } catch (IllegalAccessException e) {  
               e.printStackTrace();  
           }  
        }  
     }  
}
