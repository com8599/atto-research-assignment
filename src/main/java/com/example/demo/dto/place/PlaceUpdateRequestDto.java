package com.example.demo.dto.place;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class PlaceUpdateRequestDto {

    @Schema(description = "장소 상태")
    private Integer state;

    @Schema(description = "장소 이름")
    private String name;

    @Schema(description = "주소")
    private String address;

    @Schema(description = "여는 시간")
    private Date openAt;

    @Schema(description = "닫는 시간")
    private Date closeAt;
}
