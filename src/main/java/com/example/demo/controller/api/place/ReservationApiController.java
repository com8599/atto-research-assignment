package com.example.demo.controller.api.place;

import com.example.demo.dto.ResultDto;
import com.example.demo.dto.place.ReservationSaveRequestDto;
import com.example.demo.dto.place.ReservationUpdateRequestDto;
import com.example.demo.sevice.place.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/reservations")
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

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("{id}")
    @Operation(summary = "예약수정")
    public ResponseEntity<ResultDto> update(@PathVariable Long id, @RequestBody ReservationUpdateRequestDto requestDto) {
        return reservationService.update(id, requestDto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("{id}")
    @Operation(summary = "예약 상세보기")
    public ResponseEntity<ResultDto> findById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("list/{sort}/{page}/{name}")
    @Operation(summary = "예약다가져오기")
    public ResponseEntity<ResultDto> findAll(@PathVariable int sort, @PathVariable int page, @PathVariable String name) {
        return reservationService.findAll(sort, page, name);
    }
}
