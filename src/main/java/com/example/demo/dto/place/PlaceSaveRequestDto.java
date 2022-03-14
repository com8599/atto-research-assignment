package com.example.demo.dto.place;

import com.example.demo.domain.place.Place;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.sql.Time;

@Getter
@Builder
public class PlaceSaveRequestDto {

    @Schema(description = "장소 이름")
    private String name;

    @Schema(description = "주소")
    private String address;

    @Schema(description = "여는 시간")
    private Time openAt;

    @Schema(description = "닫는 시간")
    private Time closeAt;

    @Schema(description = "예약 가능 여부")
    private boolean reserve;

    @Schema(description = "최대 예약 인원")
    private int reserveMax;

    public Place toEntity() {
        return Place.builder()
                .name(name)
                .address(address)
                .openAt(openAt)
                .closeAt(closeAt)
                .reserve(reserve)
                .reserveMax(reserveMax)
                .build();
    }
}
