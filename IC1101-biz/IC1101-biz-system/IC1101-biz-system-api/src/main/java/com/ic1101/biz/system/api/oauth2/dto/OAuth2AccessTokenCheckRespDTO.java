package com.ic1101.biz.system.api.oauth2.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 访问令牌的校验
 *
 * @author cwd
 * @date 6/13/22 10:33 AM
 */
@Data
public class OAuth2AccessTokenCheckRespDTO implements Serializable {
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 租户编号
     */
    private Long tenantId;
    /**
     * 授权范围的数组
     */
    private List<String> scopes;
}
