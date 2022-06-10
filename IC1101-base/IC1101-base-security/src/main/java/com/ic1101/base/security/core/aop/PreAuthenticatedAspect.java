package com.ic1101.base.security.core.aop;

import com.ic1101.base.security.core.annotations.PreAuthenticated;
import com.ic1101.base.security.util.SecurityBaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import static com.ic1101.common.exception.util.ServiceExceptionUtil.exception;
import static org.ietf.jgss.GSSException.UNAUTHORIZED;

/**
 * @author ：chiwd
 * @description：用户未登录切面
 * @date ：2022/6/9 21:55
 */
@Slf4j
@Aspect
public class PreAuthenticatedAspect {

    /**
     * 登录拦截
     * @param joinPoint
     * @param preAuthenticated
     * @return
     */
    @Around("@annotation(preAuthenticated)")
    public Object around(ProceedingJoinPoint joinPoint, PreAuthenticated preAuthenticated) throws Throwable {
        if (null == SecurityBaseUtils.getLoginUser()) {
            throw exception(UNAUTHORIZED);
        }
        return joinPoint.proceed();
    }
}
