package com.ic1101.base.security.core.filter;

import cn.hutool.core.util.StrUtil;
import com.ic1101.base.security.config.SecurityProperties;
import com.ic1101.base.security.core.pojo.LoginUser;
import com.ic1101.base.security.util.SecurityBaseUtils;
import com.ic1101.base.web.core.util.WebBaseUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：chiwd
 * @description：token过滤器，验证token有效性
 * @date ：2022/6/11 11:49
 */
public class TokenAuthenticateFilter extends OncePerRequestFilter {

    @Resource
    private SecurityProperties securityProperties;

    private OAuth2Tok

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //token值
        String token = SecurityBaseUtils.getAuthorizationFromRequestHeader(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotEmpty(token)) {
            Integer userType = WebBaseUtils.getUserType(request);

        }
    }


    private LoginUser createLoginUserByToken(String token, Integer userType) {

    }
}
