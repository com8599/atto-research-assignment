package com.example.demo.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByIdAndStateIsLessThan(Long id, int limit);
}
