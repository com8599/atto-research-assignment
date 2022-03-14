package com.example.demo.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountLoginRequestDto {
    @Schema(description = "계정의 이메일이자 아이디")
    private String email;   // 계정의 이메일이자 아이디

    @Schema(description = "비밀번호")
    private String password;    // 비밀번호

    @Builder
    public AccountLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
