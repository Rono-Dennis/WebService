package com.example.authservice.service;

import com.example.authservice.entity.Movie;
import com.example.authservice.feign.MovieServiceClient;

public class MovieService implements MovieServiceClient {
    @Override
    public Movie saveMovie(Movie movie) {
        return null;
    }
}
