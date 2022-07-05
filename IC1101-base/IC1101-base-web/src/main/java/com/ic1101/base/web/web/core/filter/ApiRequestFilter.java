package com.ic1101.base.web.web.core.filter;

import cn.hutool.core.util.StrUtil;
import com.ic1101.base.web.web.config.WebPropertiesX;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：chiwd
 * @description：TODO
 * @date ：2022/7/3 14:27
 */
@RequiredArgsConstructor
public abstract class ApiRequestFilter extends OncePerRequestFilter {

    private final WebPropertiesX webPropertiesX;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        //只过滤后台和app请求
        return !StrUtil.startWithAny(request.getRequestURI(), webPropertiesX.getAdminApi().getPrefix(),
                webPropertiesX.getAppApi().getPrefix());
    }
}
