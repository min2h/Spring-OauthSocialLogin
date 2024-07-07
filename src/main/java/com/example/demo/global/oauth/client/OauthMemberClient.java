package com.example.demo.global.oauth.client;

import com.example.demo.domain.oauth.dto.OauthMember;
import com.example.demo.domain.oauth.dto.OauthServerType;

public interface OauthMemberClient {

    OauthServerType supportServer();

    OauthMember fetch(String code);
}