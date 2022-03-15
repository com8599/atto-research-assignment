package com.example.demo.dto.place;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Date;

@Getter
public class ReservationUpdateRequestDto {
    @Schema(description = "상태")
    private Integer state;

    @Schema(description = "장소 id")
    private Long placeId;

    @Schema(description = "예약시간")
    private Date reserveAt;
}
