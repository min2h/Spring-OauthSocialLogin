package com.example.demo.domain.oauth.dto.response;

import com.example.demo.domain.oauth.entity.OauthMember;
import com.example.demo.domain.oauth.dto.OauthServerType;
import com.example.demo.domain.oauth.dto.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMemberResponse {

    private String email;
    private String oauthServerId;
    private OauthServerType oauthServerType;
    private String birthday;
    private String profileImageUrl;
    private String nickname;
    private Role role;

    public GetMemberResponse(OauthMember oauthMemberEntity) {
        this.email = oauthMemberEntity.getEmail();
        this.oauthServerId = oauthMemberEntity.getOauthServerId();
        this.oauthServerType = oauthMemberEntity.getOauthServerType();
        this.birthday = oauthMemberEntity.getBirthday();
        this.profileImageUrl = oauthMemberEntity.getProfileImageUrl();
        this.nickname = oauthMemberEntity.getNickname();
        this.role = Role.USER; // Assuming all members have USER role by default, adjust accordingly
    }
}
