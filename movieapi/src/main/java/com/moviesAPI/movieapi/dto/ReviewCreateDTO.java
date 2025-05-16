package com.moviesAPI.movieapi.dto;

import jakarta.validation.constraints.*;

public class ReviewCreateDTO {

    @NotBlank(message = "Review cannot be empty!")
    private String review;

    @NotNull(message = "Movie ID is required")
    private Long movieId;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
