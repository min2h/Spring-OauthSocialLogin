package com.example.demo.domain.oauth;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class StatusEntity extends BaseEntity{
    protected String checkStatus;
}
