package com.example.demo.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Table;

@Table
@Getter
@AllArgsConstructor
public class AccountAuthorities {
    private Long accountId;
    private String authorityName;
}
