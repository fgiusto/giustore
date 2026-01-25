package at.giusto.giustore.search.app.rest;

import at.giusto.giustore.search.commons.service.SearchService;
import com.meilisearch.sdk.model.SearchResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Item rest controller.
 */
@RestController
@RequestMapping("/api")
public class ItemRestController {

    private final SearchService searchService;

    /**
     * Instantiates a new Item rest controller.
     *
     * @param searchService the search service
     */
    public ItemRestController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public SearchResult search(@RequestParam String query) {
        return searchService.search(query);
    }
}
