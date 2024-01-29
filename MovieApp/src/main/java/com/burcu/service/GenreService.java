package com.burcu.service;

import com.burcu.entity.Genre;
import com.burcu.entity.User;
import com.burcu.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService implements ICrudService<Genre, Long> {
    private final GenreRepository genreRepository;
    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre update(Genre genre) {
        return null;
    }

    @Override
    public Iterable<Genre> saveAll(Iterable<Genre> t) {
        return genreRepository.saveAll(t);
    }

    @Override
    public Genre deleteById(Long aLong) {
        return null;
    }

    @Override
    public Optional<Genre> findById(Long aLong) {
        Optional<Genre> optionalGenre = genreRepository.findById(aLong);
        if (optionalGenre.isEmpty()) {
            throw new NullPointerException("Genre not found");
        }
        return optionalGenre;
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
