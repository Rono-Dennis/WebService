package com.example.authservice.feign;

import com.example.authservice.entity.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FeignClient(name = "MOVIE-SERVICE")
public interface MovieServiceClient {
    @PostMapping(path = "/authenticate/addMovie")
    public Movie saveMovie(Movie movie);
}
