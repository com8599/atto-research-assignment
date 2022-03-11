package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateKind {
    NORMAL(0,"일반 상태","일반 상태"),
    READED(1,"읽은 상태","읽은 상태"),
    DELETE(100,"삭제","삭제")
    ;

    private Integer id;
    private String title;
    private String description;
}
