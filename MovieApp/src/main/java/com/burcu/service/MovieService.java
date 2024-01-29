package com.burcu.service;

import com.burcu.entity.Movie;

import com.burcu.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService implements ICrudService<Movie, Long> {
    private final MovieRepository movieRepository;
    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Movie movie) {
        return null;
    }

    @Override
    public Iterable<Movie> saveAll(Iterable<Movie> t) {
        return movieRepository.saveAll(t);
    }

    @Override
    public Movie deleteById(Long aLong) {
        return null;
    }

    @Override
    public Optional<Movie> findById(Long id) {
        Optional<Movie> optionalMovie=movieRepository.findById(id);
        if (optionalMovie.isEmpty()){
            throw new NullPointerException("Movie not found");
        }
        return optionalMovie;
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movieList=movieRepository.findAll();
        if (movieList.isEmpty()){
            throw new NullPointerException("Movie not found");
        }
        return movieList;
    }

    //Belli bir rating üzerindeki Movie'leri getiren bir metot yazalım
    public List<Movie> findAllByRatingGreaterThan(Double rating){
        return movieRepository.findAllByRatingGreaterThan(rating);
    }

    public List<Movie> findAllByPremieredBefore(LocalDate date) {
        return movieRepository.findAllByPremieredBefore(date);
    }

    public List<Movie> findAllByRatingIn(List<Double> ratings) {
        return movieRepository.findAllByRatingIn(ratings);
    }
}
