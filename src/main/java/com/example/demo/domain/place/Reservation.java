package com.example.demo.domain.place;

import com.example.demo.domain.user.Account;
import com.example.demo.dto.place.ReservationUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date editedAt;

    @Column(length = 3, columnDefinition = "INT DEFAULT 0")
    private int state;

    @OneToOne
    @JoinColumn
    private Place place;

    @OneToOne
    @JoinColumn
    private Account account;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date reserveAt;

    public void update(ReservationUpdateRequestDto requestDto, Place place) {
        Optional.ofNullable(requestDto.getState()).ifPresent(e -> this.state = e);
        Optional.ofNullable(place).ifPresent(e -> this.place = e);
        Optional.ofNullable(requestDto.getReserveAt()).ifPresent(e -> this.reserveAt = e);
    }
}
