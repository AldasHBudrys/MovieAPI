package com.moviesAPI.movieapi.mapper;

import com.moviesAPI.movieapi.dto.ReviewResponseDTO;
import com.moviesAPI.movieapi.dto.ReviewCreateDTO;
import com.moviesAPI.movieapi.Entity.Movie;
import com.moviesAPI.movieapi.Entity.Review;

public class ReviewMapper {

    public static Review toEntity(ReviewCreateDTO dto, Movie movie) {
        Review review = new Review();
        review.setReview(dto.getReview());
        review.setMovie(movie);

        return review;
    }

    public static ReviewResponseDTO toResponse(Review review) {
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setId(review.getId());
        dto.setReview(review.getReview());
        dto.setMovieId(review.getMovie().getId());

        return dto;
    }
}
