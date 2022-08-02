package com.example.movieservice.service;

import com.example.movieservice.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    Movie saveMovie(Movie movie);
    Movie getMovie(String movieName);
    Optional<Movie> getMovieById(int id);
    List<Movie> getAllMovies();
    Optional<Movie> findById(int id);
    void deleteById(int id);
    Movie update(Movie movie,int id);


}
