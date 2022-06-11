package com.ic1101.biz.system.api.oauth2.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ：chiwd
 * @description：OAuth2.0 访问令牌的信息
 * @date ：2022/6/11 21:30
 */
@Data
@Accessors(chain = true)
public class OAuth2AccessTokenRespDTO implements Serializable {

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;
}
