package com.moviesAPI.movieapi.service;

import com.moviesAPI.movieapi.dao.MovieRepository;
import com.moviesAPI.movieapi.Entity.Movie;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesServiceImpl implements MoviesService{
    private MovieRepository movieRepository;

    @Autowired
    public MoviesServiceImpl(MovieRepository theMovieRepository){
        movieRepository = theMovieRepository;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(int id) {
        Optional<Movie> result = movieRepository.findById(id);
        Movie movie = null;

        if(result.isPresent()){
            movie = result.get();
        }else{
            throw new RuntimeException("Did not find movie by id: " + id);
        }

        return movie;
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteById(int id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> findByGenre(String genre) {
        List<Movie> movies = movieRepository.findByGenre(genre);
        System.out.println("Movies found: " + movies.size());
        return movies;
    }
}
