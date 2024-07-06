package com.example.demo.domain.oauth.service;

import com.example.demo.domain.global.oauth.client.OauthMemberClientComposite;
import com.example.demo.domain.global.oauth.oauthcode.OauthCodeRequestUrlProviderComposite;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.oauth.dto.OauthMember;
import com.example.demo.domain.oauth.dto.OauthServerType;
import com.example.demo.domain.oauth.dto.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OauthService {

    private final OauthCodeRequestUrlProviderComposite oauthCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final MemberRepository memberRepository;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return oauthCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    public Map<String, Long> login(OauthServerType oauthServerType, String authCode) {
        OauthMember oauthMember;
        try {
            oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        } catch (Exception e) {
            log.error("Failed to fetch OAuth member: {}", e.getMessage(), e);
            throw new RuntimeException();
        }

        Member member = memberRepository.findByEmail(oauthMember.getEmail())
                .orElseGet(
                        () -> memberRepository.save(
                                Member.builder()
                                        .oauthServerId(oauthMember.getOauthId().getOauthServerId())
                                        .oauthServerType(oauthMember.getOauthId().getOauthServerType())
                                        .email(oauthMember.getEmail())
                                        .birthday(oauthMember.getBirthday())
                                        .nickname(oauthMember.getNickname())
                                        .profileImageUrl(oauthMember.getProfileImageUrl())
                                        .role(Role.USER)
                                        .build()
                        )
                );

        if ("N".equals(member.getCheckStatus())) {
            member.updateCheckstatus("Y");
        }

        Map<String, Long> response = new HashMap<>();
        response.put("id", member.getId());
        return response;
    }
}

