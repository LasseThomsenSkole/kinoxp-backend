package org.example.kinoxpbackend.service;

import org.example.kinoxpbackend.model.Movie;
import org.example.kinoxpbackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<Movie> getMovieList(){
        return movieRepository.findAll();
    }


    //jeg er i tvivl om den her
    public String findAllMovies(){
        List<Movie> movies = getMovieList();
        StringBuilder movieList = new StringBuilder();

        for (Movie movie : movies){
            movieList.append(movie.getTitle());
        }
        return movieList.toString();
    }
    //ved ik hvor det her skal bruges - lasse
    public String getMovieDetails(int id, Movie movie){
        movieRepository.findMovieById(id);
        StringBuilder movieDetails = new StringBuilder();

        movieDetails.append(movie.getTitle());
        movieDetails.append(movie.getGenre());
        movieDetails.append(movie.getDuration());
        movieDetails.append(movie.getAgeLimit());
        movieDetails.append(movie.getDescription());
        movieDetails.append(movie.getMoviePoster());

        return movieDetails.toString();
    }
    public Movie getMovieById(int id){
        return movieRepository.findMovieById(id);
    }

    public List<Movie> getUpcomingMovies() {
        Date today = new Date();  // Få dagens dato
        return movieRepository.findByReleaseDateAfter(today);
    }

}
