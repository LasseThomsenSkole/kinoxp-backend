package org.example.kinoxpbackend.repository;

import org.example.kinoxpbackend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {


    Movie findMovieById(int id);

    List<Movie> findByReleaseDateAfter(Date currentDate);
}
