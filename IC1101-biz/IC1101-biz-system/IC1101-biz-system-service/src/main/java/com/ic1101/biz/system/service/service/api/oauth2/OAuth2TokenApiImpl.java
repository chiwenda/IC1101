package com.ic1101.biz.system.service.service.api.oauth2;

import com.ic1101.biz.system.api.oauth2.OAuth2TokenApi;
import com.ic1101.biz.system.api.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import com.ic1101.biz.system.api.oauth2.dto.OAuth2AccessTokenCreateReqDTO;
import com.ic1101.biz.system.api.oauth2.dto.OAuth2AccessTokenRespDTO;
import com.ic1101.biz.system.service.service.oauth2.OAuth2TokenService;

import javax.annotation.Resource;

/**
 * @author cwd
 * @date 6/13/22 10:48 AM
 */
public class OAuth2TokenApiImpl implements OAuth2TokenApi {


    @Resource
    private OAuth2TokenService oAuth2TokenService;

    @Override
    public OAuth2AccessTokenRespDTO createAccessToken(OAuth2AccessTokenCreateReqDTO reqDTO) {
        return null;
    }

    @Override
    public OAuth2AccessTokenRespDTO refreshAccessToken(String refreshToken, String clientId) {
        return null;
    }

    @Override
    public OAuth2AccessTokenCheckRespDTO checkAccessToken(String accessToken) {

    }
}
