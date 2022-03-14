package com.example.demo.domain.user;

import com.example.demo.dto.user.AccountUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;
    private Date editedAt;

    @Column(length = 3)
    private int kind;

    @Column(length = 3)
    private int state;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 80, nullable = false)
    private String pw;

    @Column(length = 50, nullable = false)
    private String nickname;

    @Column(length = 20)
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            joinColumns = {@JoinColumn(name = "AccountId", referencedColumnName = "Id")},
            inverseJoinColumns = {@JoinColumn(name = "AuthorityName", referencedColumnName = "AuthorityName")}
    )
    private Set<Authority> authorities = new java.util.LinkedHashSet<>();

    public void update(AccountUpdateRequestDto requestDto) {
        Optional.ofNullable(requestDto.getKind()).ifPresent(e -> this.kind = e);
        Optional.ofNullable(requestDto.getState()).ifPresent(e -> this.state = e);
        Optional.ofNullable(requestDto.getEmail()).ifPresent(e -> this.email = e);
        Optional.ofNullable(requestDto.getPw()).ifPresent(e -> this.pw = e);
        Optional.ofNullable(requestDto.getNickname()).ifPresent(e -> this.nickname = e);
        Optional.ofNullable(requestDto.getPhoneNumber()).ifPresent(e -> this.phoneNumber = e);
    }
}
