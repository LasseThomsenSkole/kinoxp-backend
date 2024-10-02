package org.example.kinoxpbackend.service;

import org.example.kinoxpbackend.model.Movie;
import org.example.kinoxpbackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;


    public Movie createMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public void deleteMovie(int id){
        movieRepository.deleteById(id);
    }

    public Movie editMovie(int id, Movie updatedMovie) {
        Optional <Movie> movie = Optional.ofNullable(movieRepository.findMovieById(id));
        //Optional er en boolean, og giver true eller false på om der er en værdi i stedet for null - bagefter kan vi bruge isPresent()
        //såfremt der er en værdi

        if (movie.isPresent()) {
            Movie currentMovie = movie.get();

            currentMovie.setTitle(updatedMovie.getTitle());
            currentMovie.setGenre(updatedMovie.getGenre());
            currentMovie.setDuration(updatedMovie.getDuration());
            currentMovie.setAgeLimit(updatedMovie.getAgeLimit());
            currentMovie.setReleaseDate(updatedMovie.getReleaseDate());
            currentMovie.setEndDate(updatedMovie.getEndDate());
            currentMovie.setDescription(updatedMovie.getDescription());

            return movieRepository.save(updatedMovie);
        }
        else {
            throw new RuntimeException();
        }
    }

    public List<Movie>


}
