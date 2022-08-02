package com.example.authservice.entity;

import lombok.Data;

@Data
public class Ratings {
    private Integer movieId;
    private Double rating;
    private Integer totalRatings;
}
