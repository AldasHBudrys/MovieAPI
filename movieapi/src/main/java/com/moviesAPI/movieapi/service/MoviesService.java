package com.moviesAPI.movieapi.service;

import com.moviesAPI.movieapi.Entity.Movie;


import java.util.List;

public interface MoviesService {
    List<Movie> findAll();

    Movie findById(int id);

    Movie save(Movie movie);

    void deleteById(int id);

   List<Movie> findByGenre(String genre);

}
