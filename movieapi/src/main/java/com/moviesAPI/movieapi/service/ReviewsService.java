package com.moviesAPI.movieapi.service;

import com.moviesAPI.movieapi.Entity.Review;

import java.util.List;

public interface ReviewsService {

    List<Review> findAll();

    List<Review> findByMovieId(Long movieId);

    Review save(Review review);
}
