package com.example.demo.domain.place;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByIdAndStateIsLessThan(Long id, int limit);

    List<Reservation> findAllByStateIsLessThan(int limit, Pageable pageable);

    List<Reservation> findAllByStateIsLessThanAndAccountId(int limit, Long id, Pageable pageable);

    @Query("select r.place.id from Reservation r where r.state < ?1 and r.account.id = ?2")
    List<Long> findPlaceIdByStateIsLessThanAndAccountId(int limit, Long id);
}
