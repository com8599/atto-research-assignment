package com.example.demo.dto;

import com.example.demo.domain.user.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class AccountRequestDto {
    @Schema(description = "계정의 id")
    private final Long id;    // 계정의 id

    @Schema(description = "계정의 권한 종류(0:일반, 1:어드민)")
    private final int kind;

    @Schema(description = "계정의 상태")
    private final int state;

    @Schema(description = "계정의 이메일이자 아이디")
    private final String email;   // 계정의 이메일이자 아이디

    @Schema(description = "계정의 이름이자 닉네임")
    private final String nickname;

    @Schema(description = "휴대폰 번호")
    private final String phoneNumber;

    public AccountRequestDto(Account entity) {
        this.id = entity.getId();
        this.kind = entity.getKind();
        this.state = entity.getState();
        this.email = entity.getEmail();
        this.nickname = entity.getNickname();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
