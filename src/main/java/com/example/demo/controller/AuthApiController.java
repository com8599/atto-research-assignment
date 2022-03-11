package com.example.demo.controller;

import com.example.demo.dto.AccountLoginRequestDto;
import com.example.demo.dto.ResultDto;
import com.example.demo.sevice.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Tag(name = "계정 - jwt 관련 api")
@Slf4j
public class AuthApiController {
    private final AuthService authService;

    @Operation(summary = "jwt 토큰 발급하기")
    @PostMapping("/api/v1/authenticate")
    public ResponseEntity<ResultDto> authorize(@Valid @RequestBody AccountLoginRequestDto requestDto) {
        log.info("/api/v1/authenticate post controller");
        return authService.authorize(requestDto);
    }
}
