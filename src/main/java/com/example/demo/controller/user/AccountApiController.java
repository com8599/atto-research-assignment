package com.example.demo.controller.user;

import com.example.demo.dto.user.AccountLoginRequestDto;
import com.example.demo.dto.user.AccountSaveRequestDto;
import com.example.demo.dto.user.AccountUpdateRequestDto;
import com.example.demo.dto.ResultDto;
import com.example.demo.sevice.user.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
@RestController
@Tag(name = "계정 - 계정 관련 api")
@Slf4j
public class AccountApiController {
    private final AccountService accountService;

    @PostMapping()
    @Operation(summary = "회원 가입")
    public ResponseEntity<ResultDto> save(
            @RequestBody AccountSaveRequestDto requestDto
    ) {
        log.info("/api/v1/account post controller");
        return accountService.save(requestDto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping()
    @Operation(summary = "회원 정보 수정-자신")
    public ResponseEntity<ResultDto> update(
            @RequestBody AccountUpdateRequestDto requestDto
    ) {
        log.info("/api/v1/account put controller");
        return accountService.update(requestDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "회원 정보 조회")
    @Parameter(name = "id", required = true, example = "0")
    public ResponseEntity<ResultDto> findById(@PathVariable Long id) {
        log.info("/api/v1/account/{id} get controller");
        return accountService.findById(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping()
    @Operation(summary = "회원 자신의 정보 조회")
    public ResponseEntity<ResultDto> findMe() {
        log.info("/api/v1/account get controller");
        return accountService.findMe();
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "존재하는 이메일인지")
    @Parameter(name = "email", description = "회원의 고유 email", required = true)
    public ResponseEntity<ResultDto> emailExisted(@PathVariable String email) {
        log.info("/api/v1/account/email/{email} get controller");
        return accountService.emailExisted(email);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<ResultDto> login(@RequestBody AccountLoginRequestDto requestDto) {
        log.info("/api/v1/account/login post controller");
        return accountService.login(requestDto);
    }
}
