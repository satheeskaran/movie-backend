package com.sivasathees.movies.service;

import com.sivasathees.movies.model.Movie;
import com.sivasathees.movies.repository.MovieRepository;
import com.sivasathees.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImp implements MovieService{
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteMovieById(int id) {
        movieRepository.deleteById(id);
        return "success";
    }

    @Override
    public Movie updateMovieById(Movie movie) {
        Movie existingMovie = movieRepository.findById(movie.getId()).orElse(null);
        existingMovie.setCategory(movie.getCategory());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setImage(movie.getImage());
        existingMovie.setLength(movie.getLength());
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setYear(movie.getYear());
        return movieRepository.save(existingMovie);
    }
}
