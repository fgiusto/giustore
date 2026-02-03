package at.giusto.giustore.search.app.rest;

import at.giusto.giustore.search.commons.entity.ItemDocument;
import at.giusto.giustore.search.commons.service.ItemSaveService;
import at.giusto.giustore.search.commons.service.ItemSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Item rest controller.
 */
@RestController
@RequestMapping("/items")
public class ItemDocumentRestController {

    private final ItemSearchService itemSearchService;
    private final ItemSaveService itemSaveService;

    /**
     * Instantiates a new Item rest controller.
     *
     * @param itemSearchService the search service
     */
    public ItemDocumentRestController(ItemSearchService itemSearchService, ItemSaveService itemSaveService) {
        this.itemSearchService = itemSearchService;
        this.itemSaveService = itemSaveService;
    }

    @GetMapping("/search")
    public List<ItemDocument> search(@RequestParam String query) {
        return itemSearchService.search(query);
    }

    @PostMapping("/create")
    public ItemDocument create(@RequestBody ItemDocument document) {
        return itemSaveService.save(document);
    }
}
