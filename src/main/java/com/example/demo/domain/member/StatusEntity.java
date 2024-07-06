package com.example.demo.domain.member;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class StatusEntity {
    protected String checkStatus;
}
