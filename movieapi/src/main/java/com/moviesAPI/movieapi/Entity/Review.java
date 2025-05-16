package com.moviesAPI.movieapi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String review;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

     @ManyToOne
     @JoinColumn(name = "user_id", nullable = false)
     private User user;

    public Review() {}

    public Review(String review, Movie movie) {
        this.review = review;
        this.movie = movie;
    }

    public @NotNull String getReview() {
        return review;
    }

    public void setReview(@NotNull String review) {
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


