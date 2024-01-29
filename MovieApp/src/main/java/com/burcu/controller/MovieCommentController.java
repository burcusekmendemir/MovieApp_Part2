package com.burcu.controller;

import com.burcu.entity.Movie;
import com.burcu.entity.MovieComment;
import com.burcu.service.MovieCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moviecomment")
@RequiredArgsConstructor
public class MovieCommentController {
    private final MovieCommentService movieCommentService;

    @PostMapping("/save")
    public ResponseEntity<MovieComment> save(@RequestBody MovieComment movieComment) {
        return ResponseEntity.ok(movieCommentService.save(movieComment));
    }

    /**
     * @RequestParam ->> property isimleri url de gözükür.
     * @PathVariable ->> property isimleri url'de gözükmez,
     * yalnızca atılan sorgudaki parametre degeri gözükür.
     */
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Optional<MovieComment>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(movieCommentService.findById(id));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<MovieComment>> findAll() {
        return ResponseEntity.ok(movieCommentService.findAll());
    }

    //Bir filme ait yorumları listeleyen metodu yazalım.
    @GetMapping("/find-by-movie-id")
    public ResponseEntity<List<MovieComment>> findAllByMovieId(Long movieId){
        return ResponseEntity.ok(movieCommentService.findAllByMovieId(movieId));
    }

    //Verilen tarih aralıklarında belirli bir filme yapılmış olan yorumları gösteren bir metot yazalım.
    @GetMapping("/find-by-movie-id-and-date-between/{id}")
    public ResponseEntity<List<Movie>> findByMovieIdAndDateBetween(@PathVariable Long id, String startDate, String endDate){
        return ResponseEntity.ok(movieCommentService.findByMovieIdAndDateBetween(id,startDate, endDate));
    }

    //Yorum uzunluğu 20 karakterden büyük olan yorumları getiren bir metot yazalım.
    @GetMapping("/find-by-comment-length")
    public ResponseEntity<List<MovieComment>> findByCommentLengthGreaterThan(Integer length){
        return ResponseEntity.ok(movieCommentService.findByCommentLengthGreaterThan(length));
    }
}
