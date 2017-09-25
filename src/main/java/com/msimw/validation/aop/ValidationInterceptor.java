package com.msimw.validation.aop;

import com.msimw.validation.api.Validation;
import com.msimw.validation.exception.ValidationException;
import com.msimw.validation.result.ValidationResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.beans.IntrospectionException;
import java.lang.reflect.Method;

/**
 * Created by 胡明 on 16-6-17.
 * 参数较验
 * with JDK1.7
 */
public class ValidationInterceptor {

    private static final   Log LOGGER = LogFactory.getLog(ValidationInterceptor.class);

    /**
     * 核心校验器
     */
    private Validation validation;

    /**
     * 获取目标方法2String
     *
     * @param jp  join point
     * @return 校验key
     */
    public static String getValidationKey(JoinPoint jp) {
        String packageName = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        return packageName +"."+ methodName;
    }

    /**
     * 获取参数
     *
     * @param jp join point
     * @return 校验参数
     */
    public static String getRequestParam(JoinPoint jp) {
        StringBuilder key = new StringBuilder();

        Object[] args = jp.getArgs();
        key.append("(");
        for (Object arg : args) {
            key.append(arg).append(",");
        }
        key.replace(key.length() - 1, key.length(), ")");
        return key.toString();
    }

    /**
     * 较验
     *
     * @param jp join point
     * @throws IntrospectionException
     * @return 代理返回
     */
    public Object validated(ProceedingJoinPoint jp) throws Throwable {
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        ValidationResult validated = validation.validated(method, jp.getArgs());
        if (validated == null) {
            LOGGER.debug("统一参数校验:目标方法："+getValidationKey(jp)+",数据校验通过");
            return jp.proceed();
        }
        LOGGER.debug("统一参数校验:目标方法："+getValidationKey(jp)+",数据校验不通过");
        LOGGER.debug("参数:"+getRequestParam(jp));
        return resultHandler(method, validated);
    }

    /**
     * 结果处理
     *
     * @param method 方法
     * @param resultEntity 验证结果
     * @return 教研结果
     */
    private Object resultHandler(Method method, ValidationResult resultEntity) {
        Class<?> returnType = method.getReturnType();
        throw new ValidationException(resultEntity);
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }
}
