package io.javabrains.moviecatalogservice.resources;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping(path = "/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable(name = "userId") String userId){
        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
        return ratings.getUserRating().stream()
            .map(rating -> {
                Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);                
                return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
            })
            .collect(Collectors.toList());
    }
}