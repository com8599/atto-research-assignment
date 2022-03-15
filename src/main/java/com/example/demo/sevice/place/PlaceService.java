package com.example.demo.sevice.place;

import com.example.demo.domain.place.Place;
import com.example.demo.domain.place.PlaceRepository;
import com.example.demo.dto.ResultDto;
import com.example.demo.dto.place.PlaceRequestDto;
import com.example.demo.dto.place.PlaceSaveRequestDto;
import com.example.demo.dto.place.PlaceUpdateRequestDto;
import com.example.demo.model.StateKind;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.dto.ResultDto.makeResult;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    @Transactional
    public ResponseEntity<ResultDto> save(PlaceSaveRequestDto requestDto) {
        return makeResult(HttpStatus.OK, placeRepository.save(requestDto.toEntity()).getId());
    }

    @Transactional
    public ResponseEntity<ResultDto> update(Long id, PlaceUpdateRequestDto requestDto) {
        Place place = placeRepository.findByIdAndStateIsLessThan(id, StateKind.DELETE.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 장소가 존재하지 않습니다. id=" + id));

        place.update(requestDto);
        return makeResult(HttpStatus.OK, place.getId());
    }

    public ResponseEntity<ResultDto> findById(Long id) {
        Place place = placeRepository.findByIdAndStateIsLessThan(id, StateKind.DELETE.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 장소가 존재하지 않습니다. id=" + id));

        return makeResult(HttpStatus.OK, new PlaceRequestDto(place));
    }

    public ResponseEntity<ResultDto> findAll() {
        List<Place> place = placeRepository.findAllByStateIsLessThan(StateKind.DELETE.getId());

        List<PlaceRequestDto> result = new ArrayList<>();
        for (Place p : place) {
            result.add(new PlaceRequestDto(p));
        }
        return makeResult(HttpStatus.OK, result);
    }
}
