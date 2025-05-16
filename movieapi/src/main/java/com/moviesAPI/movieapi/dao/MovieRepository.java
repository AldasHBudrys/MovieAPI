package com.moviesAPI.movieapi.dao;

import com.moviesAPI.movieapi.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    List<Movie> findByGenre(String genre);
}
