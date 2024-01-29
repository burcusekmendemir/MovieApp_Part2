package com.burcu.repository;

import com.burcu.entity.Movie;
import com.burcu.entity.MovieComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MovieCommentRepository extends JpaRepository<MovieComment, Long> {

    //Bir filme ait yorumları listeleyen metodu yazalım.
    List<MovieComment> findAllByMovieId(Long movieId);

    //Verilen tarih aralıklarında belirli bir filme yapılmış olan yorumları gösteren bir metot yazalım.
    List<Movie> findByMovieIdAndDateBetween(Long id, LocalDate startDate, LocalDate endDate);

    //Yorum uzunluğu 20 karakterden büyük olan yorumları getiren bir metot yazalım.
    @Query("SELECT m FROM MovieComment m WHERE LENGTH(m.content) >?1 ")
    List<MovieComment> findByCommentLengthGreaterThan(Integer length);
}
