package com.ic1101.base.security.core.hanler;

import com.ic1101.base.security.util.SecurityBaseUtils;
import com.ic1101.common.pojo.CommonResult;
import com.ic1101.common.util.servlet.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ic1101.common.exception.enums.ErrorCodeConstants.FORBIDDEN;

/**
 * @author ：chiwd
 * @description：资源无权限处理
 * @date ：2022/6/11 10:56
 */
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        log.error("[commence][访问 URL({}) 时，用户({}) 权限不够]", request.getRequestURI(),
                SecurityBaseUtils.getLoginUserId(), accessDeniedException);
        //403
        ServletUtils.writeJson(response, CommonResult.error(FORBIDDEN));
    }
}
