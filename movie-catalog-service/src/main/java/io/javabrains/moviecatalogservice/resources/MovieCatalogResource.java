package io.javabrains.moviecatalogservice.resources;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;

@RestController
@RequestMapping(path = "/catalog")
public class MovieCatalogResource {
    @RequestMapping(path = "/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable(name = "userId") String userId){
        RestTemplate restTemplate = new RestTemplate();
        
        List<Rating> ratings =  Arrays.asList(
            new Rating("1234", 4),
            new Rating("5678", 3)
        );
        return ratings.stream()
            .map(rating -> {
                Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                return new CatalogItem(movie.getName(), "Desc", rating.getRating());
            })
            .collect(Collectors.toList());
    }
}