package com.example.demo.controller.place;

import com.example.demo.dto.ResultDto;
import com.example.demo.dto.reservation.ReservationSaveRequestDto;
import com.example.demo.sevice.place.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
@RestController
@Tag(name = "장소 - 예약 관련 api")
public class ReservationApiController {
    private final ReservationService reservationService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping()
    @Operation(summary = "예약추가")
    public ResponseEntity<ResultDto> save(@RequestBody ReservationSaveRequestDto requestDto) {
        return reservationService.save(requestDto);
    }
}
