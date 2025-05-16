package com.moviesAPI.movieapi.service;

import com.moviesAPI.movieapi.dao.ReviewRepository;
import com.moviesAPI.movieapi.Entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewsServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> findByMovieId(Long movieId) {
        return reviewRepository.findByMovieId(movieId);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }
}
