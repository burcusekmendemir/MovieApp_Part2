package com.burcu.controller;

import com.burcu.entity.Movie;
import com.burcu.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/save")
    public ResponseEntity<Movie> save(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.save(movie));
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Optional<Movie>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }
    @GetMapping("/find-all")
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    //Belli bir rating üzerindeki Movie'leri getiren bir metot yazalım
    @GetMapping("/find-by-rating-greater-than")
    public ResponseEntity<List<Movie>> findAllByRatingGreaterThan(Double rating) {
        return ResponseEntity.ok(movieService.findAllByRatingGreaterThan(rating));
    }

    //Girilen tarihten önce çıkmış filmleri listeleyen bir metot yazalım.
    // (Tarihi dışarıdan string olarak alalım. formatımız (dd-MM-yyyy) -> gün-ay-yıl şeklinde olsun.
    @GetMapping("/find-by-premiered-before")
    public ResponseEntity<List<Movie>> findAllByPremieredBefore(LocalDate date){
        return ResponseEntity.ok(movieService.findAllByPremieredBefore(date));
    }

    // Filmler içerisinde puanları 7, 8, 9 olan filmleri getiren bir metot yazalım.
    @GetMapping("/find-by-rating-in")
    public ResponseEntity<List<Movie>> findAllByRatingBetween() {
         List<Double> ratings = Arrays.asList(7.0, 8.0, 9.0);
         return ResponseEntity.ok(movieService.findAllByRatingIn(ratings));
    }


}
