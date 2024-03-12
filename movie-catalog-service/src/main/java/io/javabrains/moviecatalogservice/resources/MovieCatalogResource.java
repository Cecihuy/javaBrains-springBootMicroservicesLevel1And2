package io.javabrains.moviecatalogservice.resources;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.javabrains.moviecatalogservice.model.CatalogItem;

@RestController
@RequestMapping(path = "/catalog")
public class MovieCatalogResource {
    @RequestMapping(path = "/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable(name = "userId") String userId){
        return Collections.singletonList(new CatalogItem("Transformers", "Test", 4));
    }
}