package com.example.demo.domain.global.oauth.naver.dto;


import com.example.demo.domain.oauth.dto.OauthId;
import com.example.demo.domain.oauth.dto.OauthMember;
import com.example.demo.domain.oauth.dto.OauthServerType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


@JsonNaming(value = SnakeCaseStrategy.class)
public record NaverMemberResponse(
        String resultcode,
        String message,
        Response response
) {

    public OauthMember toDomain() {

        return OauthMember.builder()
                .oauthId(new OauthId(String.valueOf(response.id), OauthServerType.NAVER))
                .nickname(response.nickname)
                .profileImageUrl(response.profileImage)
                .email(response.email)
                .birthday(response.birthday)
                .build();
    }

    @JsonNaming(value = SnakeCaseStrategy.class)
    public record Response(
            String id,
            String nickname,
            String name,
            String email,
            String gender,
            String age,
            String birthday,
            String profileImage,
            String birthyear,
            String mobile
    ) {
    }
}