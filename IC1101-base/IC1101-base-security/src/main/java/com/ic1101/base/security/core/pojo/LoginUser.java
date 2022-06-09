package com.ic1101.base.security.core.pojo;

import lombok.Data;

/**
 * @author ：chiwd
 * @description：登录的用户信息
 * @date ：2022/6/9 22:35
 */
@Data
public class LoginUser {

    /**
     * 用户编号
     */
    private Long id;

    /**
     * 租户编号
     */
    private Long tenantId;

    /**
     * 用户类型
     */
    private Integer userType;
}
