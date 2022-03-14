package com.example.demo.domain.place;

import com.example.demo.domain.user.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;
    private Date editedAt;

    @Column(length = 3)
    private int state;

    @OneToOne
    @JoinColumn
    private Place place;

    @OneToOne
    @JoinColumn
    private Account account;
}
