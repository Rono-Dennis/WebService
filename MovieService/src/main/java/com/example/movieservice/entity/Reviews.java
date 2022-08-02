package com.example.movieservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REVIEWS")
public class Reviews {
    @Id
    private Integer reviewId;
    private Integer movieId;
    private Double rating;
    private String comments;
}
