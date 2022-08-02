package com.example.movieservice.controller;

import com.example.movieservice.entity.Movie;
import com.example.movieservice.entity.Reviews;
import com.example.movieservice.repository.MovieRepository;
import com.example.movieservice.repository.ReviewRepository;
import com.example.movieservice.service.MovieService;
import com.example.movieservice.service.MovieServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
//@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    ResponseEntity authenticationResponse ;
    @Autowired
    private RestTemplate restTemplate;

    private final MovieService movieService;

    private final MovieRepository movieRepository;

    private final ReviewRepository reviewRepository;

    private final MovieServiceImpl movieServiceimpl;

    private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);

    /*RequestMapping(value = "/authenticate/addMovies", method = RequestMethod.POST)
    public Movie transactionResponse(Movie movie) throws JsonProcessingException {

        Movie paymentResponse = restTemplate.postForObject("http://localhost:9392/addMovie", movie, Movie.class);

        return paymentResponse;
    }*/

    /*RequestMapping(value = "/login", method = RequestMethod.POST)
    public String logintoMCs(@RequestParam String userName,@RequestParam String password)
    {
        String AUTHENTICATION_URL="http://localhost:9395/authenticate";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("userName", userName);
        map.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
                HttpMethod.POST, request, String.class);
        if (authenticationResponse.getStatusCode().equals(HttpStatus.OK))
        {
            return "Login Successfull and Got Token";
        }
        return "Invalid credential";
    }*/

    /*@RequestMapping(value = "/enterData", method = RequestMethod.POST)
    public String logic(@RequestParam String username,@RequestParam String password){
        String AUTHENTICATION_URL = "http://localhost:9392/authenticate";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String,String>();
        map.add("username",username);
        map.add("password",password);

//        HttpEntity<MultiValueMap<String,String>> request=new HttpEntity<MultiValueMap<String,String>>(map.get(i));
        return null;

    }
    @RequestMapping("helloClient")
    public String helloClient()
    {
        ResponseEntity authenticationResponse = null;
        if (authenticationResponse !=null && authenticationResponse.getStatusCode().equals(HttpStatus.OK))
        {
            String token = "Bearer " + authenticationResponse.getBody();
            String CLIENT_URL="http://localhost:8086/hello";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
            ResponseEntity<String> helloResponse = restTemplate.exchange(CLIENT_URL, HttpMethod.GET, jwtEntity,
                    String.class);
            if (helloResponse.getStatusCode().equals(HttpStatus.OK)) {
                String data = helloResponse.getBody();
                return data;
            }
        }
        return "Please login";
    }*/

    /*@RequestMapping(value = "/authenticate/addMovies", method = RequestMethod.POST)
    public Movie createMovies(@RequestBody Movie movie) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Movie> entity = new HttpEntity<Movie>(movie,headers);
//        return movieService.saveMovie(movie);
        Movie paymentResponse = restTemplate.postForObject("http://localhost:9392/addMovie", movie, Movie.class);

        return paymentResponse;*/
//        return restTemplate.exchange("http://localhost:9395/authenticate/addMovie", HttpMethod.POST, entity, String.class).getBody();
    //}


    /*@PostMapping("/authenticate/addMovies")
    public ResponseEntity<Movie>
    post(@RequestBody Movie userData)
    {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(userData, headers,
                HttpStatus.CREATED);
    }
*/
    @PostMapping(path = "/authenticate/addMovie")
    public Movie addMovie(@RequestBody Movie movie) throws JsonProcessingException {
        return movieService.saveMovie(movie);
    }
    @GetMapping("/getAllMovies")
    public List<Movie> findAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Optional<Movie> findMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @PutMapping("/movie/{id}")
    public Movie updateMovie(@RequestBody Movie movie, @PathVariable("id") Integer id) {
        return movieService.update(movie,id);
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable int id) {
        movieService.deleteById(id);
        return "deleted successfully";
    }


    @GetMapping("/movieid/{movieId}")
    public List<Movie> getMovieInfo(@PathVariable("movieId") Integer movieId) {
        return movieServiceimpl.getMovieInfo(movieId);
    }

    @PostMapping("/review")
    public List<Movie> addMovieReview(@RequestBody Reviews reviews) {
        LOG.info("Add Movie Reviews...");
        movieServiceimpl.addReview(reviews);

        return movieServiceimpl.getMovieInfo(reviews.getMovieId());
    }


}
