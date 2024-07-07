package com.example.demo.domain.oauth.repository;

import com.example.demo.domain.oauth.entity.OauthMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthMemberRepository extends JpaRepository<OauthMember, Long> {
    Optional<OauthMember> findById(Long id);

    Optional<OauthMember> findByEmail(String email);
}
