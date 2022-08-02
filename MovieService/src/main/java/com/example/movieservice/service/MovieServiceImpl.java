package com.example.movieservice.service;

import com.example.movieservice.entity.Movie;
import com.example.movieservice.entity.Ratings;
import com.example.movieservice.entity.Reviews;
import com.example.movieservice.repository.MovieRepository;
import com.example.movieservice.repository.RatingsRepository;
import com.example.movieservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final RatingsRepository ratingsRepository;
    private final ReviewRepository reviewRepository;
    private static final Logger LOG = LoggerFactory.getLogger(MovieServiceImpl.class);
    @Override
    public Movie saveMovie(Movie movie) {
        movie.setRating(null);
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovie(String movieName) {
        return movieRepository.findByMovieName(movieName);
    }

    @Override
    public Optional<Movie> getMovieById(int id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(int id) {
        return movieRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {

        movieRepository.deleteMovieById(id);
    }

    @Override
    public Movie update(Movie movie, int id) {
        Movie movie1 = movieRepository.findById(id).get();

        if (Objects.nonNull(movie.getMovieName())
                && !"".equalsIgnoreCase(
                movie.getMovieName())) {
            movie1.setMovieName(
                    movie.getMovieName());
        }

        if (Objects.nonNull(
                movie.getDescription())
                && !"".equalsIgnoreCase(
                movie.getDescription())) {
            movie.setDescription(
                    movie.getDescription());
        }

        return movieRepository.save(movie1);
    }


    public List<Movie> getMovieInfo(Integer movieId) {

        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        Movie movie = movieOptional.get();
        // Update Reviews
        List<Reviews> reviews = reviewRepository.fetchReviewByMovieId(movie.getId());
        movie.setReviews(reviews);

        // getting list
        List<Movie> list = Arrays.asList(new Movie[] { movie });

        return list;
    }

    public void addReview(Reviews reviews) {
        reviewRepository.save(reviews);
        Ratings ratings = null;
        List<Ratings> ratingList = ratingsRepository.fetchRatingByMovieId(reviews.getMovieId());
        if (!ratingList.isEmpty()) {
            LOG.info("Update existing ratings...");
            ratings = ratingList.get(0);
        } else {
            LOG.info("Add a new ratings...");
            ratings = new Ratings(reviews.getMovieId(),  (double) 0, 0);
        }

        /*if ("Y".equalsIgnoreCase(reviews.getLikeMovie())) {
            ratings.setLikes(ratings.getLikes() + 1);
        } else {
            ratings.setDislike(ratings.getDislike() + 1);
        }*/
        ratings.setTotalRatings(ratings.getTotalRatings() + 1);
        ratingsRepository.save(ratings);
    }



}
