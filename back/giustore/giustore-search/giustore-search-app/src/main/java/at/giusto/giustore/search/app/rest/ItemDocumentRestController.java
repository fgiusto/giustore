package at.giusto.giustore.search.app.rest;

import at.giusto.giustore.search.commons.entity.ItemDocument;
import at.giusto.giustore.search.commons.record.ItemSearchRequest;
import at.giusto.giustore.search.commons.service.ItemSearchService;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Item rest controller.
 */
@RestController
@RequestMapping("/api/items")
public class ItemDocumentRestController {

    private final ItemSearchService itemSearchService;

    /**
     * Instantiates a new Item rest controller.
     *
     * @param itemSearchService the search service
     */
    public ItemDocumentRestController(ItemSearchService itemSearchService) {
        this.itemSearchService = itemSearchService;
    }

    @GetMapping("/search")
    public SearchHits<ItemDocument> search(@RequestParam String query) {
        return itemSearchService.search(new ItemSearchRequest(query, null, null, 0, 10));
    }

    @GetMapping("/search/test")
    public SearchHits<ItemDocument> searchTest() {
        return itemSearchService.search(new ItemSearchRequest("test", null, null, 0, 10));
    }
}
