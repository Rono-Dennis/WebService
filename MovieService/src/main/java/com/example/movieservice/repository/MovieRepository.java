package com.example.movieservice.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.example.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Movie findByMovieName(String movieName);
    void deleteMovieById(int id);

}
