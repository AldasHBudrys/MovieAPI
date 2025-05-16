package com.moviesAPI.movieapi.controllers;

import com.moviesAPI.movieapi.dto.ReviewCreateDTO;
import com.moviesAPI.movieapi.dto.ReviewResponseDTO;
import com.moviesAPI.movieapi.Entity.Movie;
import com.moviesAPI.movieapi.Entity.Review;
import com.moviesAPI.movieapi.mapper.ReviewMapper;
import com.moviesAPI.movieapi.service.MoviesService;
import com.moviesAPI.movieapi.service.ReviewsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/movies/reviews")
public class ReviewsController {

    private final ReviewsService reviewsService;
    private final MoviesService moviesService;

    @Autowired
    public ReviewsController(ReviewsService reviewsService, MoviesService moviesService) {
        this.reviewsService = reviewsService;
        this.moviesService = moviesService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviews() {
        List<Review> reviews = reviewsService.findAll();
        List<ReviewResponseDTO> response = reviews.stream()
                .map(ReviewMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("{movieId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByHotelId(@PathVariable Long movieId) {
        List<Review> reviews = reviewsService.findByMovieId(movieId);
        List<ReviewResponseDTO> response = reviews.stream()
                .map(ReviewMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ReviewResponseDTO> addReview(@Valid @RequestBody ReviewCreateDTO reviewDTO) {
        Movie movie = moviesService.findById(reviewDTO.getMovieId().intValue());
        Review review = ReviewMapper.toEntity(reviewDTO, movie);
        Review saved = reviewsService.save(review);
        return ResponseEntity.ok(ReviewMapper.toResponse(saved));
    }
}
