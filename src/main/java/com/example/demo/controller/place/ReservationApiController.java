package com.example.demo.controller.place;

import com.example.demo.sevice.place.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
@RestController
@Tag(name = "장소 - 예약 관련 api")
public class ReservationApiController {
    private final ReservationService reservationService;
}
