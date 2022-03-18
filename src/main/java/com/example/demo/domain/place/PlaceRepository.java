package com.example.demo.domain.place;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByIdAndStateIsLessThan(Long id, int limit);

    List<Place> findAllByStateIsLessThan(int limit, Pageable pageable);

    List<Place> findAllByStateIsLessThanAndIdIn(int limit, List<Long> ids, Pageable pageable);

    List<Place> findAllByStateIsLessThanAndNameLike(int limit, String name, Pageable pageable);

    List<Place> findAllByStateIsLessThanAndIdInAndNameLike(int limit, List<Long> placeIds, String name, Pageable pageable);
}
