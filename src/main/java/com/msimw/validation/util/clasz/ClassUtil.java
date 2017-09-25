package com.msimw.validation.util.clasz;


import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ClassUtil {

    /**
     * @param clz class类型
     * @return 是否是基础类型
     */
    public static boolean isWrapClass(Class<?> clz) {
        try {
            return ((Class<?>) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param clz class类型
     * @return 是否是基础类型或者String
     */
    public static boolean isWrapClassAndString(Class<?> clz) {
        if (clz == String.class
                || ClassUtil.isWrapClass(clz)) {
            return true;
        }
        return false;
    }


    /**
     * 获取方法上参数名 JDK1.7
     * @param method 方法
     * @return 参数名称数组
     */
    public static String[] getMethodParamNames(Method method) {
        if (method == null) {
            return null;
        }
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();//with JDK1.7
        return discoverer.getParameterNames(method);
    }


    /**
     * 获取类上的字段
     *
     * @param classType class类型
     * @return 字段
     */
    public static Field[] getFields(Class classType) {
        if (classType == null) {
            return null;
        }
        return classType.getDeclaredFields();
    }


}
