package com.example.demo.dto;

import com.example.demo.domain.user.Account;
import com.example.demo.domain.user.Authority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class AccountSaveRequestDto {

    @Schema(description = "계정의 권한 종류(0:일반, 1:어드민)")
    private Integer kind;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "비밀번호")
    private String pw;

    @Schema(description = "이름")
    private String nickname;

    @Schema(description = "휴대폰 번호")
    private String phoneNumber;

    // make account with auth
    public Account toEntity(Set<Authority> authorities) {
        return Account.builder()
                .kind(kind)
                .email(email)
                .pw(pw)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .authorities(authorities)
                .build();
    }
}
