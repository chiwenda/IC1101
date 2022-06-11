package com.ic1101.biz.system.api.oauth2.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ：chiwd
 * @description：创建访问令牌
 * @date ：2022/6/11 21:51
 */
public class OAuth2AccessTokenCreateReqDTO {

    @NotNull(message = "用户编号不能为空")
    private Long userId;

    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    @NotNull(message = "客户端编号不能为空")
    private String clientId;

    /**
     * 授权范围
     */
    private List<String> scopes;
}
