package com.example.demo.sevice.user;

import com.example.demo.decorator.AccountDecorator;
import com.example.demo.domain.user.Account;
import com.example.demo.domain.user.AccountRepository;
import com.example.demo.dto.*;
import com.example.demo.dto.user.AccountLoginRequestDto;
import com.example.demo.dto.user.AccountRequestDto;
import com.example.demo.dto.user.AccountSaveRequestDto;
import com.example.demo.dto.user.AccountUpdateRequestDto;
import com.example.demo.model.StateKind;
import com.example.demo.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.dto.ResultDto.makeResult;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;
    private final AccountDecorator accountDecorator;

    private static final String ACCOUNT_NULL = "해당하는 유저가 없습니다.";
    private static final String ACCOUNT_NULL_EMAIL = ACCOUNT_NULL + " email=";

    @Transactional
    public ResponseEntity<ResultDto> save(
            AccountSaveRequestDto requestDto
    ) {
        if (accountRepository.findOneWithAuthoritiesByEmailAndStateIsLessThan(requestDto.getEmail(), StateKind.DELETE.getId()).orElse(null) != null) {
            throw new IllegalArgumentException("이미 가입되어 있는 유저입니다.");
        }

        Account account = accountDecorator.save(requestDto);
        return makeResult(HttpStatus.OK, new AccountRequestDto(account));
    }

    @Transactional
    public ResponseEntity<ResultDto> update(
            AccountUpdateRequestDto requestDto
    ) {
        Account account = SecurityUtil.getCurrentEmail().flatMap(accountRepository::findOneWithAuthoritiesByEmail)
                .orElseThrow(() -> new IllegalArgumentException(ACCOUNT_NULL));

        if (requestDto!=null) {
            if (requestDto.getPw() != null)
                requestDto.setPw(passwordEncoder.encode(requestDto.getPw()));
            account.update(requestDto);
        }

        return makeResult(HttpStatus.OK, account.getId());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ResultDto> findById(Long id) {
        Account entity = accountRepository.findOneWithAuthoritiesByIdAndStateIsLessThan(id, StateKind.DELETE.getId())
                .orElseThrow(() -> new IllegalArgumentException(ACCOUNT_NULL + " id="+id));

        return makeResult(HttpStatus.OK, new AccountRequestDto(entity));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ResultDto> emailExisted(String email) {
        return makeResult(HttpStatus.OK, accountRepository.findByEmailAndStateIsLessThan(email, StateKind.DELETE.getId()).isPresent());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ResultDto> login(AccountLoginRequestDto requestDto) {
        Account account = accountRepository.findOneWithAuthoritiesByEmailAndStateIsLessThan(requestDto.getEmail(), StateKind.DELETE.getId())
                .orElseThrow(() -> new IllegalArgumentException(ACCOUNT_NULL_EMAIL + requestDto.getEmail()));
        if (passwordEncoder.matches(requestDto.getPassword(), account.getPw())) {
            return makeResult(HttpStatus.OK, new AccountRequestDto(account));
        }
        return makeResult(HttpStatus.BAD_REQUEST, null);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ResultDto> findMe() {
        Account account = SecurityUtil.getCurrentEmail().flatMap(accountRepository::findOneWithAuthoritiesByEmail)
                .orElseThrow(() -> new IllegalArgumentException(ACCOUNT_NULL));
        return makeResult(HttpStatus.OK, new AccountRequestDto(account));
    }
}
