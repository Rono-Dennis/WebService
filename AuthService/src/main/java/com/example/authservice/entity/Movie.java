package com.example.authservice.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.List;

@Data
public class Movie {
    private int id;
    private String movieName;
    private String description;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @JsonUnwrapped
    private Ratings rating;
    @Transient
    private List<Reviews> reviews;
}
