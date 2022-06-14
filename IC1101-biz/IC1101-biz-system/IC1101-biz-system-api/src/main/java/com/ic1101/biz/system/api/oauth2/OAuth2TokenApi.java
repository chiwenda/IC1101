package com.ic1101.biz.system.api.oauth2;

import com.ic1101.biz.system.api.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import com.ic1101.biz.system.api.oauth2.dto.OAuth2AccessTokenCreateReqDTO;
import com.ic1101.biz.system.api.oauth2.dto.OAuth2AccessTokenRespDTO;

import javax.validation.Valid;

/**
 * @author ：chiwd
 * @description：token api
 * @date ：2022/6/11 12:23
 */
public interface OAuth2TokenApi {


    /**
     * 创建访问令牌
     */
    OAuth2AccessTokenRespDTO createAccessToken(@Valid OAuth2AccessTokenCreateReqDTO reqDTO);


    /**
     * 刷新访问令牌
     *
     * @param refreshToken 刷新令牌
     * @param clientId     k客户端编号
     * @return 访问令牌信息
     */
    OAuth2AccessTokenRespDTO refreshAccessToken(String refreshToken, String clientId);


    /**
     * 校验访问令牌
     * @param accessToken 令牌
     * @return 访问令牌信息
     */
    OAuth2AccessTokenCheckRespDTO checkAccessToken(String accessToken);


}
