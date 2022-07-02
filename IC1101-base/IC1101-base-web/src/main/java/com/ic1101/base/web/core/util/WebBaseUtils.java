package com.ic1101.base.web.core.util;

import com.ic1101.base.web.config.WebProperties;
import com.ic1101.common.enums.UserTypeEnum;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * web工具包
 *
 * @author cwd
 * @date 6/13/22 9:37 AM
 */
public class WebBaseUtils {

    private static WebProperties webProperties;

    public WebBaseUtils(WebProperties webProperties) {
        WebBaseUtils.webProperties = webProperties;
    }

    private static final String REQUEST_ATTRIBUTE_LOGIN_USER_ID = "login_user_id";
    private static final String REQUEST_ATTRIBUTE_LOGIN_USER_TYPE = "login_user_type";

    public static Integer getUserType(HttpServletRequest request) {
        if (request == null) {
            return null;
        }

        //先从attribute中取
        Integer userType = (Integer) request.getAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_TYPE);
        if (userType != null) {
            return userType;
        }

        //从uri判断
        if (request.getRequestURI().startsWith(webProperties.getAdminApi().getPrefix())) {
            return UserTypeEnum.ADMIN.getValue();
        }
        if (request.getRequestURI().startsWith(webProperties.getAppApi().getPrefix())) {
            return UserTypeEnum.MEMBER.getValue();
        }
        return null;
    }

    /***
     * 获取登录用户ID
     * @return
     */
    public static Long getLoginUserId() {
        HttpServletRequest request = getRequest();
        return getLoginUserId(request);
    }

    public static Long getLoginUserId(HttpServletRequest request) {
        if (Objects.isNull(request)) {
            return null;
        }

        return (Long) request.getAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_ID);
    }

    public static HttpServletRequest getRequest() {
        //获取请求体中的属性
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;

        return servletRequestAttributes.getRequest();

    }
}
