package com.example.demo.security.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Slf4j
public class SecurityUtil {

    private SecurityUtil() {

    }

    public static Optional<String> getCurrentEmail() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String email = null;
        if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
            email = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String e) {
            email = e;
        }

        return Optional.ofNullable(email);
    }
}
