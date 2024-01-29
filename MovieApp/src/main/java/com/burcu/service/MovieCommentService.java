package com.burcu.service;

import com.burcu.entity.Movie;
import com.burcu.entity.MovieComment;
import com.burcu.entity.User;
import com.burcu.repository.MovieCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieCommentService implements ICrudService<MovieComment, Long>  {

    private final MovieCommentRepository movieCommentRepository;
    private final MovieService movieService;
    @Override
    public MovieComment save(MovieComment movieComment) {
        return movieCommentRepository.save(movieComment);
    }

    @Override
    public MovieComment update(MovieComment movieComment) {
        return null;
    }

    @Override
    public Iterable<MovieComment> saveAll(Iterable<MovieComment> t) {
        return movieCommentRepository.saveAll(t);
    }

    @Override
    public MovieComment deleteById(Long aLong) {
        return null;
    }

    @Override
    public Optional<MovieComment> findById(Long aLong) {
        Optional<MovieComment> optionalMovieComment=movieCommentRepository.findById(aLong);
        if (optionalMovieComment.isEmpty()){
            throw new NullPointerException("MovieComment not found");
        }
        return optionalMovieComment;
    }

    @Override
    public List<MovieComment> findAll() {
        return movieCommentRepository.findAll();
    }

    public List<MovieComment> findAllByMovieId(Long movieId) {
        return movieCommentRepository.findAllByMovieId(movieId);
    }


    public List<Movie> findByMovieIdAndDateBetween(Long id,String startDate, String endDate) {
        if (movieService.findById(id).isEmpty()){
            throw new NullPointerException("movie not found");
        }
        LocalDate start= LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate end= LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return movieCommentRepository.findByMovieIdAndDateBetween(id,start, end);
    }

    public List<MovieComment> findByCommentLengthGreaterThan(Integer length) {
        return movieCommentRepository.findByCommentLengthGreaterThan(length);
    }
}

