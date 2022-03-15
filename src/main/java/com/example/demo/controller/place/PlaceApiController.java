package com.example.demo.controller.place;

import com.example.demo.dto.ResultDto;
import com.example.demo.dto.place.PlaceSaveRequestDto;
import com.example.demo.dto.place.PlaceUpdateRequestDto;
import com.example.demo.sevice.place.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/place")
@RestController
@Tag(name = "장소 - 장소 관련 api")
public class PlaceApiController {
    private final PlaceService placeService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping()
    @Operation(summary = "장소 추가")
    public ResponseEntity<ResultDto> save(@RequestBody PlaceSaveRequestDto requestDto) {
        return placeService.save(requestDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "장소 수정")
    public ResponseEntity<ResultDto> update(@PathVariable Long id, @RequestBody PlaceUpdateRequestDto requestDto) {
        return placeService.update(id, requestDto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "장소 조회")
    @Parameter(name = "id", required = true, example = "0")
    public ResponseEntity<ResultDto> findById(@PathVariable Long id) {
        return placeService.findById(id);
    }
}
