package com.moviesAPI.movieapi.dto;

import jakarta.validation.constraints.*;

public class MovieCreateDTO {
    @NotBlank(message = "A Movie must have a title")
    private String title;

    @NotBlank(message = "A Movie must have a genre")
    private String genre;

    @NotBlank(message = "A Movie must have a description")
    @Size(max = 255)
    private String description;


    public @NotBlank(message = "A Movie must have a title") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "A Movie must have a title") String title) {
        this.title = title;
    }

    public @NotBlank(message = "A Movie must have a genre") String getGenre() {
        return genre;
    }

    public void setGenre(@NotBlank(message = "A Movie must have a genre") String genre) {
        this.genre = genre;
    }

    public @NotBlank(message = "A Movie must have a description") @Size(max = 255) String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "A Movie must have a description") @Size(max = 255) String description) {
        this.description = description;
    }
}
