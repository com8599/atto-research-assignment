package com.example.demo.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountUpdateRequestDto {

    @Schema(description = "계정의 권한 종류(0:일반, 1:어드민)")
    private Integer kind;

    @Schema(description = "계정의 상태")
    private Integer state;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "비밀번호")
    private String pw;

    @Schema(description = "이름")
    private String nickname;

    @Schema(description = "휴대폰 번호")
    private String phoneNumber;
}
