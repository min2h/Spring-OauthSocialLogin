package com.example.demo.global.oauth.google.dto;

import com.example.demo.domain.oauth.dto.OauthId;
import com.example.demo.domain.oauth.dto.OauthMember;
import com.example.demo.domain.oauth.dto.OauthServerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GoogleMemberResponse {
    String id;
    String email;
    String given_name;
    String family_name;
    String picture;

    public OauthMember toDomain() {
        return OauthMember.builder()
                .oauthId(new OauthId(String.valueOf(id), OauthServerType.GOOGLE))
                .email(email)
                .nickname(given_name + " " + family_name)
                .profileImageUrl(picture)
                .build();
    }
}
