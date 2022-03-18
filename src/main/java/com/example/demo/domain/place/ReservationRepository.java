package com.example.demo.domain.place;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByIdAndStateIsLessThan(Long id, int limit);

    List<Reservation> findAllByStateIsLessThan(int limit, Pageable pageable);

    List<Reservation> findAllByStateIsLessThanAndAccountId(int limit, Long id, Pageable pageable);
}
