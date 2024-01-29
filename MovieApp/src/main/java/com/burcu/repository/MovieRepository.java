package com.burcu.repository;

import com.burcu.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    //Belli bir rating üzerindeki Movie'leri getiren bir metot yazalım
    List<Movie> findAllByRatingGreaterThan(Double rating);


    //Girilen tarihten önce çıkmış filmleri listeleyen bir metot yazalım.
    // (Tarihi dışarıdan string olarak alalım. formatımız (dd-MM-yyyy) -> gün-ay-yıl şeklinde olsun.
    List<Movie> findAllByPremieredBefore(LocalDate date);

    //Filmler içerisinde puanları 7, 8, 9 olan filmleri getiren bir metot yazalım.
    List<Movie> findAllByRatingIn(List<Double> ratings);
}
