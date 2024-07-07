package com.example.demo.global.oauth.google;

import com.example.demo.global.oauth.client.OauthMemberClient;
import com.example.demo.global.oauth.google.client.GoogleApiClient;
import com.example.demo.global.oauth.google.dto.GoogleMemberResponse;
import com.example.demo.global.oauth.google.dto.GoogleToken;
import com.example.demo.domain.oauth.dto.OauthMember;
import com.example.demo.domain.oauth.dto.OauthServerType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class GoogleMemberClient implements OauthMemberClient {

    private final GoogleApiClient googleApiClient;
    private final GoogleOauthConfig googleOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.GOOGLE;
    }

    @Override
    public OauthMember fetch(String authCode) {
        GoogleToken tokenInfo = googleApiClient.fetchToken(tokenRequestParams(authCode));
        GoogleMemberResponse googleMemberResponse = googleApiClient.fetchMember(tokenInfo.access_token());
        return googleMemberResponse.toDomain();
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", googleOauthConfig.getClientId());
            params.add("client_secret", googleOauthConfig.getClientSecret());
            params.add("code", authCode);
            params.add("redirect_uri", googleOauthConfig.getRedirectUri());
            params.add("token_uri", googleOauthConfig.getTokenUri());
            params.add("resource_uri", googleOauthConfig.getResourceUri());
            return params;
    }
}
