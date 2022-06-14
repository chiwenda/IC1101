package com.ic1101.base.web.core.util;

import com.ic1101.base.web.config.WebProperties;
import com.ic1101.common.enums.UserTypeEnum;

import javax.servlet.http.HttpServletRequest;

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
}
