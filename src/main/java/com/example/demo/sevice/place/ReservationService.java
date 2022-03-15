package com.example.demo.sevice.place;

import com.example.demo.domain.place.Place;
import com.example.demo.domain.place.PlaceRepository;
import com.example.demo.domain.place.Reservation;
import com.example.demo.domain.place.ReservationRepository;
import com.example.demo.domain.user.Account;
import com.example.demo.domain.user.AccountRepository;
import com.example.demo.dto.ResultDto;
import com.example.demo.dto.place.ReservationRequestDto;
import com.example.demo.dto.place.ReservationSaveRequestDto;
import com.example.demo.dto.place.ReservationUpdateRequestDto;
import com.example.demo.model.StateKind;
import com.example.demo.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.dto.ResultDto.makeResult;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final PlaceRepository placeRepository;
    private final AccountRepository accountRepository;

    private static final String ACCOUNT_NULL = "해당하는 유저가 없습니다.";
    private static final String PLACE_NULL = "해당 장소가 존재하지 않습니다. id=";

    @Transactional
    public ResponseEntity<ResultDto> save(ReservationSaveRequestDto requestDto) {
        Place place = placeRepository.findByIdAndStateIsLessThan(requestDto.getPlaceId(), StateKind.DELETE.getId())
                .orElseThrow(() -> new IllegalArgumentException(PLACE_NULL + requestDto.getPlaceId()));

        Account account = SecurityUtil.getCurrentEmail().flatMap(accountRepository::findOneWithAuthoritiesByEmail)
                .orElseThrow(() -> new IllegalArgumentException(ACCOUNT_NULL));

        return makeResult(HttpStatus.OK, reservationRepository.save(requestDto.toEntity(place, account)).getId());
    }

    @Transactional
    public ResponseEntity<ResultDto> update(Long id, ReservationUpdateRequestDto requestDto) {
        Reservation reservation = reservationRepository.findByIdAndStateIsLessThan(id, StateKind.DELETE.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 예약이 없습니다. id=" + id));

        Place place = placeRepository.findByIdAndStateIsLessThan(requestDto.getPlaceId(), StateKind.DELETE.getId())
                .orElseThrow(() -> new IllegalArgumentException(PLACE_NULL + requestDto.getPlaceId()));

        reservation.update(requestDto, place);
        return makeResult(HttpStatus.OK, reservation.getId());
    }

    public ResponseEntity<ResultDto> findById(Long id) {
        Reservation reservation = reservationRepository.findByIdAndStateIsLessThan(id, StateKind.DELETE.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 예약이 없습니다. id=" + id));

        return makeResult(HttpStatus.OK, new ReservationRequestDto(reservation));
    }
}
