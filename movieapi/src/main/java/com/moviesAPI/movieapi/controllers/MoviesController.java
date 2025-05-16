package com.moviesAPI.movieapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.moviesAPI.movieapi.dto.MovieCreateDTO;
import com.moviesAPI.movieapi.dto.MovieResponseDTO;
import com.moviesAPI.movieapi.Entity.Movie;
import com.moviesAPI.movieapi.mapper.MovieMapper;
import com.moviesAPI.movieapi.service.MoviesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/movies")
public class MoviesController {
    private final MoviesService moviesService;
    private final ObjectMapper objectMapper;


    @Autowired
    public MoviesController(MoviesService theMoviesService, ObjectMapper theObjectMapper){
        moviesService = theMoviesService;
        objectMapper = theObjectMapper;

    }

    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> findAll(){
        List<MovieResponseDTO> movies = moviesService.findAll()
                .stream()
                .map(MovieMapper::toResponse)
                .collect(Collectors.toList());

        Map<String, Object> response = Map.of(
                "status", "success",
                "results", movies.size(),
                "data", movies
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{movieId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MovieResponseDTO> getMovie(@PathVariable Long movieId){

        Optional<Movie> results = Optional.ofNullable(moviesService.findById(movieId.intValue()));

        return results.map(movie->ResponseEntity.ok(MovieMapper.toResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/genre/{genre}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> findByGenre(@PathVariable String genre) {
        List<MovieResponseDTO> movies = moviesService.findByGenre(genre)
                .stream()
                .map(MovieMapper::toResponse)
                .collect(Collectors.toList());
        Map<String, Object> response = Map.of(
                "status", "success",
                "results", movies.size(),
                "data", movies
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieResponseDTO> addMovie(@Valid @RequestBody MovieCreateDTO movieDTO){
        Movie saved = moviesService.save(MovieMapper.toEntity(movieDTO));
        return ResponseEntity.ok(MovieMapper.toResponse(saved));

    }

    @DeleteMapping("/{movieId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMovie(@PathVariable int movieId){
        Movie theMovie = moviesService.findById(movieId);

        if(theMovie == null){
            return ResponseEntity.notFound().build();
        }

        moviesService.deleteById(movieId);

        return ResponseEntity.ok("deleted the movie: " + movieId);
    }

    //update all movie objects
    @PutMapping("/{movieId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable int movieId, @Valid @RequestBody MovieCreateDTO movie){
        Movie existing = moviesService.findById(movieId);
        if(existing == null){
            return ResponseEntity.notFound().build();
        }

        Movie updatedMovie = MovieMapper.toEntity(movie);
        updatedMovie.setId((long)movieId);

        Movie saved = moviesService.save(updatedMovie);

        return ResponseEntity.ok(MovieMapper.toResponse(saved));
    }

    @PatchMapping("{movieId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieResponseDTO> patchMovie(@PathVariable int movieId, @RequestBody Map<String, Object> pathPayLoad){

        Movie tempMovie = moviesService.findById(movieId);

        if(tempMovie == null){
            return ResponseEntity.notFound().build();
        }

        if(pathPayLoad.containsKey("id")){
            return ResponseEntity.badRequest().build();
        }

        ObjectNode movieNode = objectMapper.convertValue(tempMovie, ObjectNode.class);
        ObjectNode pathNode = objectMapper.convertValue(pathPayLoad, ObjectNode.class);
        movieNode.setAll(pathNode);

        Movie patchedMovie = objectMapper.convertValue(movieNode, Movie.class);

        Movie saved = moviesService.save(patchedMovie);

        return ResponseEntity.ok(MovieMapper.toResponse(saved));
    }
}
