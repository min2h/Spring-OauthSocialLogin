package com.example.demo.domain.global.oauth.google.authcode;

import com.example.demo.domain.global.oauth.google.GoogleOauthConfig;
import com.example.demo.domain.global.oauth.oauthcode.OauthCodeRequestUrlProvider;
import com.example.demo.domain.oauth.dto.OauthServerType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GoogleOauthCodeRequestUrlProvider implements OauthCodeRequestUrlProvider {

    private final GoogleOauthConfig googleOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.GOOGLE;
    }

    public GoogleOauthCodeRequestUrlProvider(GoogleOauthConfig googleOauthConfig) {
        this.googleOauthConfig = googleOauthConfig;
    }

    @Override
    public String provide() {
        return UriComponentsBuilder
                .fromUriString("https://accounts.google.com/o/oauth2/auth")
                .queryParam("response_type", "code")
                .queryParam("client_id", googleOauthConfig.getClientId())
                .queryParam("redirect_uri", googleOauthConfig.getRedirectUri())
                .queryParam("scope", String.join(" ", googleOauthConfig.getScope()))
                .queryParam("token_uri", googleOauthConfig.getTokenUri())
                .queryParam("resource_uri", googleOauthConfig.getResourceUri())
                .build()
                .toUriString();
    }
}
