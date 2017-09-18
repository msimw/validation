package com.msimw.validation.service;


import com.msimw.validation.annotation.Validated;
import com.msimw.validation.api.Validation;
import com.msimw.validation.handler.ValidationHandler;
import com.msimw.validation.result.ValidationResult;
import com.msimw.validation.util.clasz.ClassUtil;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.FileInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by msimw on 17-3-28.
 *
 * @version 1.0.0
 * @Description: 数据校验处理器
 * @Author: msimw
 * @date 17-3-28 下午4:23
 */
@Service
public class ValidationImpl implements Validation {


    /**
     * 参数校验器
     */
    private List<ValidationHandler> validationHandlers;


    /**
     * 　校验方法
     *
     * @param method
     * @param args
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Override
    public ValidationResult validated(Method method, Object... args) throws NoSuchFieldException, IllegalAccessException {

        if (CollectionUtils.isEmpty(validationHandlers) || method == null || args == null || args.length < 1) {
            return null;
        }
        String[] methodParamNames = ClassUtil.getMethodParamNames(method);//获取方法上所有参数with JDK1.7 使用jdk代理获取不到
        // method.getParameters() WITH JDK1.8

        Class<?>[] parameterTypes = method.getParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();//get method param anno
        if (parameterTypes == null || parameterTypes.length < 1) {
            return null;
        }

        for (int i = 0; i < parameterTypes.length; i++) {
            Annotation[] annotations = findAnnotation(parameterAnnotations, i);//找到对应参数上的注解
            Validated validated = findAnnotation(parameterAnnotations, i, Validated.class);
            if (annotations == null && validated == null) {
                continue;
            }
            if (ClassUtil.isWrapClassAndString(method.getParameterTypes()[i]) || validated == null) {//基础数据校验
                for (Annotation annotation : annotations) {//安注解先后依次校验
                    ValidationHandler validationHandler = getValidationHandler(annotation);//根据注解找到注解处理器
                    if (validationHandler == null) {
                        continue;
                    }
                    ValidationResult handlerResult = validationHandler.handler(annotation, methodParamNames[i], args[i]);
                    if (handlerResult != null) {
                        return handlerResult;
                    }
                }
            } else {
                return objValidated(args[i], validated, method.getParameterTypes()[i]);
            }
        }

        return null;
    }


    @Override
    public void setValidationHandlers(List<ValidationHandler> validationHandlers) {
        this.validationHandlers = validationHandlers;
    }


    /**
     * pojo 对象校验
     *
     * @param obj
     * @param validated
     * @param type
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private ValidationResult objValidated(Object obj, Validated validated, Class type) throws NoSuchFieldException, IllegalAccessException {
        if (type == null || validated == null) {
            return null;
        }
        Field[] fields = getFieldsByType(type);
        if (ObjectUtils.isEmpty(fields)) {
            return null;
        }
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getAnnotations();
            if (annotations == null) {
                continue ;
            }
            Object fieldVal = null;
            if (obj != null) {
                fieldVal = field.get(obj);
            }
            for (Annotation annotation : annotations) {
                ValidationResult resultEntity = checkGroupAndValidated(field, fieldVal, validated, annotation);
                if (resultEntity != null) {
                    return resultEntity;
                }
            }
        }
        return null;
    }


    /**
     *
     * 根据分组校验
     *
     * @param validated
     * @param annotation
     * @return
     */
    private ValidationResult checkGroupAndValidated(Field field, Object obj, Validated validated, Annotation annotation) throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> attributes = AnnotationUtils.getAnnotationAttributes(annotation);
        if (attributes == null) {
            return null;
        }
        Class<?>[] groups = (Class<?>[]) attributes.get("groups");
        Class<?>[] values = validated.value();
        ValidationHandler validationHandler = getValidationHandler(annotation);
        if (validationHandler == null) {
            return null;
        }
        if (ObjectUtils.isEmpty(values) && ObjectUtils.isEmpty(groups)) {
            return validationHandler.handler(annotation, field.getName(), obj);
        }
        for (Class<?> group : groups) {
            for (Class<?> value : values) {
                if (group == value) {
                    return validationHandler.handler(annotation, field.getName(), obj);
                }
            }
        }
        return null;
    }


    /**
     * 获取相应注解的执行
     *
     * @param annotation
     * @return
     */
    private ValidationHandler getValidationHandler(Annotation annotation) {
        if (CollectionUtils.isEmpty(this.validationHandlers)) {
            return null;
        }

        for (ValidationHandler validationHandler : this.validationHandlers) {
            Type[] types = validationHandler.getClass().getGenericInterfaces();
            if (types == null) {
                return null;
            }
            for (Type type : types) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    if (parameterizedType.getRawType() == ValidationHandler.class) {//WITH 1.7
                        Class clazs = (Class) parameterizedType.getActualTypeArguments()[0];
                        if (annotation.annotationType() == clazs) {
                            return validationHandler;
                        }
                    }
                }
            }
        }
        return null;
    }


    /**
     * 获取指定位置的参数
     *
     * @param annos
     * @param index
     * @param clasz
     * @return
     */
    public <T extends Annotation> T findAnnotation(Annotation[][] annos, int index, Class<T> clasz) {
        if (annos == null || annos.length < 1) {
            return null;
        }

        Annotation[] annotations = annos[index];

        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == clasz) {
                return (T) annotation;
            }
        }

        return null;
    }


    /**
     * 获取一个类的所有字段，包括父类的
     * @param type
     * @return
     */
    private  Field[] getFieldsByType(Class<?> type){
        if(type==null){
            return null;
        }

        List<Class<?>> allSuperclass = getAllSuperclass(type);
        allSuperclass.add(type);

        if (CollectionUtils.isEmpty(allSuperclass)){
            return type.getDeclaredFields();
        }

        Map<String,Field> fieldMap = new HashMap<>();
        for(Class<?> sup:allSuperclass){
            Field[] fs = sup.getDeclaredFields();
            if(fs==null||fs.length<1){
                continue;
            }
            for(Field f:fs){
                fieldMap.put(f.getName(),f);
            }
        }
        return fieldMap.values().toArray(new Field[0]);

    }

    /**
     * 获取一个类的所有父类
     * @param type
     * @return
     */
    public  List<Class<?>> getAllSuperclass(Class<?> type){
        LinkedList<Class<?>> temp = new LinkedList<>();
        temp.add(type);
        List<Class<?>> sups = new ArrayList<>();

        while (!temp.isEmpty()){
            Class<?> superclass = temp.removeFirst().getSuperclass();
            sups.add(superclass);
            if(superclass.getSuperclass()!=null){
                temp.add(superclass);
            }
        }
        Collections.reverse(sups);//倒序，越顶层类在越前面
        return sups;
    }



    /**
     * 获取制定位置的参数
     *
     * @param annos
     * @param index
     * @return
     */
    public <T extends Annotation> T[] findAnnotation(Annotation[][] annos, int index) {
        if (annos == null || annos.length < 1) {
            return null;
        }

        return (T[]) annos[index];
    }


    /**
     * 增加新的校验器
     *
     * @param validationHandlers
     */
    public void addValidationHandlers(List<ValidationHandler> validationHandlers) {
        if (this.validationHandlers != null) {
            this.validationHandlers.addAll(validationHandlers);
            return;
        }
        this.validationHandlers = validationHandlers;
    }


}
