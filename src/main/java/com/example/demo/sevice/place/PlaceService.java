package com.example.demo.sevice.place;

import com.example.demo.domain.place.Place;
import com.example.demo.domain.place.PlaceRepository;
import com.example.demo.domain.place.ReservationRepository;
import com.example.demo.domain.user.Account;
import com.example.demo.domain.user.AccountRepository;
import com.example.demo.dto.ResultDto;
import com.example.demo.dto.place.PlaceRequestDto;
import com.example.demo.dto.place.PlaceSaveRequestDto;
import com.example.demo.dto.place.PlaceUpdateRequestDto;
import com.example.demo.model.StateKind;
import com.example.demo.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    private final ReservationRepository reservationRepository;
    private final AccountRepository accountRepository;

    private static final String ACCOUNT_NULL = "해당하는 유저가 없습니다.";

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

    @Transactional(readOnly = true)
    public ResponseEntity<ResultDto> findAll(int sort, int page, String name) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        List<Place> place = new ArrayList<>();

        boolean undefined = name != null && !name.equals("") && !name.equals("undefined");
        if (sort == 0) {
            if (undefined)
                place = placeRepository.findAllByStateIsLessThanAndNameLike(StateKind.DELETE.getId(), '%' + name + '%', pageRequest);
            else
                place = placeRepository.findAllByStateIsLessThan(StateKind.DELETE.getId(), pageRequest);
        } else if (sort == 1) {
            Account account = SecurityUtil.getCurrentEmail().flatMap(accountRepository::findOneWithAuthoritiesByEmail)
                    .orElseThrow(() -> new IllegalArgumentException(ACCOUNT_NULL));

            List<Long> placeIds = reservationRepository.findPlaceIdByStateIsLessThanAndAccountId(StateKind.DELETE.getId(), account.getId());

            if (undefined)
                place = placeRepository.findAllByStateIsLessThanAndIdInAndNameLike(StateKind.DELETE.getId(), placeIds, '%' + name + '%', pageRequest);
            else
                place = placeRepository.findAllByStateIsLessThanAndIdIn(StateKind.DELETE.getId(), placeIds, pageRequest);
        }

        List<PlaceRequestDto> result = new ArrayList<>();
        for (Place p : place) {
            result.add(new PlaceRequestDto(p));
        }
        return makeResult(HttpStatus.OK, result);
    }
}
