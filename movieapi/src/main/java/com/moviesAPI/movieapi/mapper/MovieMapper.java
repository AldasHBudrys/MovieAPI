package com.moviesAPI.movieapi.mapper;

import com.moviesAPI.movieapi.dto.MovieCreateDTO;
import com.moviesAPI.movieapi.dto.MovieResponseDTO;
import com.moviesAPI.movieapi.Entity.Movie;

public class MovieMapper {
    public static Movie toEntity(MovieCreateDTO dto){
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setGenre(dto.getGenre());
        movie.setDescription(dto.getDescription());

        return movie;
    }

    public static MovieResponseDTO toResponse(Movie entity){
        MovieResponseDTO dto = new MovieResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setGenre(entity.getGenre());
        dto.setDescription(entity.getDescription());

        return dto;
    }
}
