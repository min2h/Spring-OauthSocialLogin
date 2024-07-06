package com.example.demo.domain.global.oauth.google.client;

import com.example.demo.domain.global.oauth.google.dto.GoogleMemberResponse;
import com.example.demo.domain.global.oauth.google.dto.GoogleToken;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface GoogleApiClient {

    @PostMapping("https://oauth2.googleapis.com/token")
    GoogleToken fetchToken(MultiValueMap<String, String> params);

    GoogleMemberResponse fetchMember(@RequestParam("access_token") String accessToken);


}

