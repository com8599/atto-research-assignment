package com.example.demo.dto.place;

import com.example.demo.domain.place.Place;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Date;

@Getter
public class PlaceRequestDto {
    @Schema(description = "장소의 id")
    private final Long id;

    @Schema(description = "장소 상태")
    private final int state;

    @Schema(description = "장소 이름")
    private final String name;

    @Schema(description = "주소")
    private final String address;

    @Schema(description = "여는 시간")
    private final Date openAt;

    @Schema(description = "닫는 시간")
    private final Date closeAt;

    public PlaceRequestDto(Place place) {
        this.id = place.getId();
        this.state = place.getState();
        this.name = place.getName();
        this.address = place.getAddress();
        this.openAt = place.getOpenAt();
        this.closeAt = place.getCloseAt();
    }
}
