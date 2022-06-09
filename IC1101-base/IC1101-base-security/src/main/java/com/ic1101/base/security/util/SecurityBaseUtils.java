package com.ic1101.base.security.util;

import com.ic1101.base.security.core.pojo.LoginUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * @author ：chiwd
 * @description：安全工具类
 * @date ：2022/6/9 22:02
 */
public class SecurityBaseUtils {

    //注意有空格
    private static final String AUTHORIZATION_BEARER = "Bearer ";

    private SecurityBaseUtils() {
    }

    /**
     * @return token
     * @author: cwd
     * @description: 从请求中获取认证Token
     * @date: 2022/6/9 22:28
     */
    public static String getAuthorizationFromRequestHeader(HttpServletRequest request, String header) {
        String authorization = request.getHeader(header);
        if (!StringUtils.hasText(authorization)) {
            return null;
        }

        int index = authorization.indexOf(AUTHORIZATION_BEARER);

        if (index == -1) {
            return null;
        }

        return authorization.substring(index + AUTHORIZATION_BEARER.toCharArray().length).trim();
    }

    /**
     * @return 认证信息
     * @author: cwd
     * @description: 获取当前认证信息
     * @date: 2022/6/9 22:31
     */
    public static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();

        if (null == context) {
            return null;
        }

        return context.getAuthentication();
    }

    /**
     * @return
     * @author: cwd
     * @description: 获取登录用户信息
     * @date: 2022/6/9 22:40
     */
    public static LoginUser getLoginUser() {
        Authentication authentication = getAuthentication();

        if (null == authentication) {
            return null;
        }
        return authentication.getPrincipal() instanceof LoginUser ? (LoginUser) authentication.getPrincipal() : null;
    }

    public static void setLoginUser(LoginUser loginUser, HttpServletRequest request) {

    }

    /**
     * @return
     * @author: cwd
     * @description: 从Filter中将获取的信息封装到认证信息
     * @date: 2022/6/10 00:06
     */
    private static Authentication createAuthentication(LoginUser loginUser, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginUser, null, Collections.emptyList());

        authenticationToken.setDetails();
    }
}
