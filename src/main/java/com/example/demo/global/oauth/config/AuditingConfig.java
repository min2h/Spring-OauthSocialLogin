package com.example.demo.global.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/***
 * 테스트코드 실행 중 발생하는 오류로 인해
 * JPA Auditing Config 생성
 */
@Configuration
@EnableJpaAuditing // JPA Auditing 활성화
public class AuditingConfig {
}