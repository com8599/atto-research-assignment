package com.example.demo.domain.place;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
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
}
