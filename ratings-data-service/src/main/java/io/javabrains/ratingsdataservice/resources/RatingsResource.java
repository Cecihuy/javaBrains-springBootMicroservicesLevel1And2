package io.javabrains.ratingsdataservice.resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.javabrains.ratingsdataservice.models.Rating;
import io.javabrains.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping(path = "/ratingsdata")
public class RatingsResource {
    @RequestMapping(path = "/movies/{movieId}")
    public Rating getRating(@PathVariable(name = "movieId") String movieId){
        return new Rating(movieId, 4);
    }
    @RequestMapping(path = "/users/{userId}")
    public UserRating getUserRating(@PathVariable(name = "userId") String userId){        
        UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;
    }
}