package com.sivasathees.movies.service;

import com.sivasathees.movies.model.Movie;

import java.util.List;

public interface MovieService {
    public Movie addMovie(Movie movie);
    public List<Movie> findAllMovies();
    public Movie findMovieById(int id);
    public String deleteMovieById(int id);
    public Movie updateMovieById(Movie movie);
}
