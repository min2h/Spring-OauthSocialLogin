package com.example.demo.global.oauth.kakao.authcode;


import com.example.demo.global.oauth.kakao.KakaoOauthConfig;
import com.example.demo.global.oauth.oauthcode.OauthCodeRequestUrlProvider;
import com.example.demo.domain.oauth.dto.OauthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *  설정한 정보를 담고 있는 클래스
 */

@Component
@RequiredArgsConstructor
public class KakaoOauthCodeRequestUrlProvider implements OauthCodeRequestUrlProvider {

    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.KAKAO;
    }

    @Override
    public String provide() {
        return UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", kakaoOauthConfig.getClientId())
                .queryParam("redirect_uri", kakaoOauthConfig.getRedirectUri())
                .queryParam("scope", String.join(",", kakaoOauthConfig.getScope()))
                .toUriString();
    }
}