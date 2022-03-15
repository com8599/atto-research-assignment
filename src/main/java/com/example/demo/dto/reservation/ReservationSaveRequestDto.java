package com.example.demo.dto.reservation;

import com.example.demo.domain.place.Place;
import com.example.demo.domain.place.Reservation;
import com.example.demo.domain.user.Account;
import lombok.Getter;

@Getter
public class ReservationSaveRequestDto {
    private Long placeId;

    public Reservation toEntity(Place place, Account account) {
        return Reservation.builder()
                .place(place)
                .account(account)
                .build();
    }
}
