package com.moviesAPI.movieapi.dao;

import com.moviesAPI.movieapi.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByMovieId(Long moveId);
}
