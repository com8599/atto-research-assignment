package com.example.demo.controller.place;

import com.example.demo.sevice.place.PlaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "장소 - 장소 관련 api")
public class PlaceApiController {
    private final PlaceService placeService;
}
