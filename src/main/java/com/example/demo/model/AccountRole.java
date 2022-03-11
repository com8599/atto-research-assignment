package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountRole {
    ROLE_ADMIN(0),
    ROLE_USER(1)
    ;

    int index;

    public static String valueOf(int index) {
        for (AccountRole accountRole : AccountRole.values()) {
            if (index == accountRole.index)
                return accountRole.name();
        }
        return null;
    }
}
