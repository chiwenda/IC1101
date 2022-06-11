package com.ic1101.base.security.core.hanler;

import com.ic1101.common.pojo.CommonResult;
import com.ic1101.common.util.servlet.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ic1101.common.exception.enums.ErrorCodeConstants.UNAUTHORIZED;

/**
 * 认证入口点
 * 认证异常时，被ExceptionTranslationFilter 调用当前类
 *
 * @author cwd
 * @date 6/10/22 11:25 AM
 */
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        log.debug("[访问 URL({}) 时，没有登录]", request.getRequestURI(), authException);
        ServletUtils.writeJson(response, CommonResult.error(UNAUTHORIZED));//输出到网页
    }
}
