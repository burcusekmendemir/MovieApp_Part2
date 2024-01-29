package com.burcu.repository;

import com.burcu.entity.Genre;
import com.burcu.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
