package com.example.authservice.entity;

import lombok.Data;

@Data
public class Reviews {
    private Integer reviewId;
    private Integer movieId;
    private Double rating;
    private String comments;
}
