package com.example.demo.decorator;

import com.example.demo.domain.user.Account;
import com.example.demo.domain.user.AccountRepository;
import com.example.demo.domain.user.Authority;
import com.example.demo.domain.user.AuthorityRepository;
import com.example.demo.dto.user.AccountSaveRequestDto;
import com.example.demo.model.AccountRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RequiredArgsConstructor
@Component
public class AccountDecorator {
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Account save(AccountSaveRequestDto requestDto) {
        Authority authority = Authority.builder()
                .authorityName(AccountRole.valueOf(requestDto.getKind()))
                .build();

        if (authorityRepository.findByAuthorityName(AccountRole.ROLE_USER.name()).isEmpty()) {
            authorityRepository.save(authority);
        }

        // pw encoding
        requestDto.setPw(passwordEncoder.encode(requestDto.getPw()));

        decorating(requestDto);
        return accountRepository.save(requestDto.toEntity(Collections.singleton(authority)));
    }

    private void decorating(AccountSaveRequestDto requestDto) {
        if (requestDto.getEmail().length() > 255) {
            requestDto.setEmail(slice(requestDto.getEmail(), 255));
        }
        if (requestDto.getNickname() != null && requestDto.getNickname().length() > 50) {   // 이름 끊기
            requestDto.setNickname(slice(requestDto.getNickname(), 50));
        }
        if (requestDto.getPhoneNumber() != null && requestDto.getPhoneNumber().length() > 20) {   // 이름 끊기
            requestDto.setPhoneNumber(slice(requestDto.getPhoneNumber(), 20));
        }
    }

    private String slice(String string, int max) {
        return string.substring(0, Math.min(string.length(), max));
    }
}
