package com.example.demo.global.oauth.kakao;

import com.example.demo.global.oauth.client.OauthMemberClient;
import com.example.demo.global.oauth.kakao.client.KakaoApiClient;
import com.example.demo.global.oauth.kakao.dto.KakaoMemberResponse;
import com.example.demo.global.oauth.kakao.dto.KakaoToken;
import com.example.demo.domain.oauth.dto.OauthMember;
import com.example.demo.domain.oauth.dto.OauthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


/**
 * https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#req-user-info
 * 위 링크 API 문서와 Description
 */
@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OauthMemberClient {

    private final KakaoApiClient kakaoApiClient;
    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.KAKAO;
    }


    /**
     (1) - 먼저 Auth Code를 통해서 AccessToken을 조회
     (2) - AccessToken을 가지고 회원 정보를 받아옴
     (3) - 회원 정보를 Member 객체로 변환
     */
    @Override
    public OauthMember fetch(String authCode) {
        KakaoToken tokenInfo = kakaoApiClient.fetchToken(tokenRequestParams(authCode)); // (1)
        KakaoMemberResponse kakaoMemberResponse = kakaoApiClient.fetchMember("Bearer " + tokenInfo.accessToken());  // (2)
        return kakaoMemberResponse.toDomain();  // (3)
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOauthConfig.getClientId());
        params.add("redirect_uri", kakaoOauthConfig.getRedirectUri());
        params.add("code", authCode);
        params.add("client_secret", kakaoOauthConfig.getClientSecret());
        return params;
    }
}