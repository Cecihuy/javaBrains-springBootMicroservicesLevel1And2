package io.javabrains.movieinfoservice.models.resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.javabrains.movieinfoservice.models.Movie;

@RestController
@RequestMapping(path = "/movies")
public class MovieResource {
    @RequestMapping(path = "/{movieId}")
    public Movie getMovieInfo(@PathVariable(name = "movieId") String movieId){
        return new Movie(movieId, "Test Name");
    }
}