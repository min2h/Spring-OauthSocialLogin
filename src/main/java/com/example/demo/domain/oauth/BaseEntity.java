package com.example.demo.domain.oauth;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * '@MappedSuperclass' : JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들(createdDate, modifiedDate)도 칼럼으로 인식하도록 합니다.
 * '@EntityListeners(AuditingEntityListener.class)': BaseTimeEntiy 클래스에 Auditing 기능을 포함시킵니다.
 * '@CreatedDate': Entity가 생성되어 저장될 때 시간이 자동 저장됩니다.
 * '@LastModifiedDate': 조회한 Entity의 값을 변경할 때 시간이 자동 저장됩니다.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreatedDate
    protected LocalDateTime createDateTime;

    @LastModifiedDate
    protected LocalDateTime lastModifiedDateTime;
}
