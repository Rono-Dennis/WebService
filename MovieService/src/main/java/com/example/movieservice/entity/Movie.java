package com.example.movieservice.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MOVIES")
public class Movie {
    @Id
    private int id;
    private String movieName;
    private String description;
    /*private Integer languageId;
    private Integer genreId;*/
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @JsonUnwrapped
    private Ratings rating;
    @Transient
    private List<Reviews> reviews;
    /*@Transient
    private String genre;
    @Transient
    private String language;*/
}
