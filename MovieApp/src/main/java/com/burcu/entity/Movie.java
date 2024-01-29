package com.burcu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String language;
    private String image;
    private String name;
    private String country;
    private Double rating;
    @Column(length = 2400)
    private String summary;
    private String url;
    private LocalDate premiered;

    //@ManyToMany
    @ElementCollection
    private List<Long> genres;

    //@OneToMany
    @ElementCollection
    private List<Long> comments;



}
