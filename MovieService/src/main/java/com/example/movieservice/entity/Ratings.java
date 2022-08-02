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
@Table(name = "RATINGS")
public class Ratings {
    @Id
    private Integer movieId;
    private Double rating;
    private Integer totalRatings;
}
