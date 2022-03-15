package com.example.demo.dto.place;

import com.example.demo.domain.place.Reservation;
import com.example.demo.dto.user.AccountRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Date;

@Getter
public class ReservationRequestDto {
    @Schema(description = "예약의 id")
    private final Long id;

    @Schema(description = "예약 상태")
    private final int state;

    @Schema(description = "장소")
    private final PlaceRequestDto place;

    @Schema(description = "예약한사람")
    private final AccountRequestDto account;

    @Schema(description = "예약시간")
    private final Date reserveAt;

    public ReservationRequestDto(Reservation reservation) {
        this.id = reservation.getId();
        this.state = reservation.getState();
        this.place = new PlaceRequestDto(reservation.getPlace());
        this.account = new AccountRequestDto(reservation.getAccount());
        this.reserveAt = reservation.getReserveAt();
    }
}
