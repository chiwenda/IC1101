package com.ic1101.base.log.core.aop;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.ic1101.base.log.core.annotation.Log;
import com.ic1101.base.log.dto.LogReqDTO;
import com.ic1101.base.web.web.core.util.WebBaseUtils;
import com.ic1101.common.enums.UserTypeEnum;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author ：chiwd
 * @description：日志aop
 * @date ：2022/7/7 20:29
 */
@Slf4j
@Aspect
public class LogAspect {

    /**
     * 日志内容
     */
    private static final ThreadLocal<String> CONTENT = new TransmittableThreadLocal<>();

    /**
     * 扩展
     */
    private static final ThreadLocal<Map<String, Object>> EXTENDS = new TransmittableThreadLocal<>();


    /**
     * 日志环绕
     *
     * @param joinPoint
     * @param apiOperation swagger注解
     * @return
     */
    public Object around(ProceedingJoinPoint joinPoint, ApiOperation apiOperation) {
        Log annotation = getClassAnnotation(joinPoint, Log.class);

    }

    /**
     * 获取标注的注解
     *
     * @param joinPoint
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T extends Annotation> T getClassAnnotation(ProceedingJoinPoint joinPoint, Class<T> clazz) {
        //获取注解
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass().getAnnotation(clazz);
    }

    private Object getLog(ProceedingJoinPoint joinPoint, Log log, ApiOperation apiOperation) throws Throwable {
        //管理员才有日志
        Integer loginUserType = WebBaseUtils.getLoginUserType();
        if (!Objects.equals(loginUserType, UserTypeEnum.ADMIN.getValue())) {
            return joinPoint.proceed();
        }

        //日志开始时间
        Date startDate = new Date();
        try {
            joinPoint.proceed();

        } catch (Throwable throwable) {

        }
    }


    private void printLog(ProceedingJoinPoint joinPoint, Log log, ApiOperation apiOperation,
                          Date startDate, Object result, Throwable throwable) {
        //不记录日志
        if(!isEnableLog(joinPoint,log)){
            return;
        }


    }


    private void getLog(ProceedingJoinPoint joinPoint, Log log, ApiOperation apiOperation,
                          Date startTime, Object result, Throwable exception){
        LogReqDTO reqDTO = new LogReqDTO();

    }

    /**
     * 是否记录日志
     *
     * @param joinPoint
     * @param log
     * @return
     */
    private static boolean isEnableLog(ProceedingJoinPoint joinPoint, Log log) {
        //有标记Log
        if (Objects.nonNull(log)) {
            return log.enable();
        }
        return Objects.nonNull(getFirstLogRequestMethod(getRequestMethods(joinPoint)));
    }

    private static RequestMethod[] getRequestMethods(ProceedingJoinPoint joinPoint) {
        //获取请求注解
        RequestMapping requestMapping = AnnotationUtils.getAnnotation(
                ((MethodSignature) (joinPoint.getSignature())).getMethod(), RequestMapping.class);

        return Objects.nonNull(requestMapping) ? requestMapping.method() : new RequestMethod[]{};
    }

    private static RequestMethod getFirstLogRequestMethod(RequestMethod[] requestMethods) {
        if (ArrayUtil.isEmpty(requestMethods)) {
            return null;
        }

        return Arrays.stream(requestMethods).filter(requestMethod ->
                requestMethod == RequestMethod.POST
                        || requestMethod == RequestMethod.PUT
                        || requestMethod == RequestMethod.DELETE)
                .findFirst().orElse(null);
    }
}
