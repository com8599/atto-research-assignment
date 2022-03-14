package com.example.demo.domain.place;

import com.example.demo.dto.place.PlaceUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;
    private Date editedAt;

    @Column(length = 3)
    private int state;

    @Column(length = 50, nullable = false)
    private String name;

    private String address;

    private Date openAt;
    private Date closeAt;

    public void update(PlaceUpdateRequestDto requestDto) {
        Optional.ofNullable(requestDto.getState()).ifPresent(e -> this.state = e);
        Optional.ofNullable(requestDto.getName()).ifPresent(e -> this.name = e);
        Optional.ofNullable(requestDto.getAddress()).ifPresent(e -> this.address = e);
        Optional.ofNullable(requestDto.getOpenAt()).ifPresent(e -> this.openAt = e);
        Optional.ofNullable(requestDto.getCloseAt()).ifPresent(e -> this.closeAt = e);
    }
}
