package at.giusto.giustore.search.commons.service;

import at.giusto.giustore.search.commons.entity.ItemDocument;
import at.giusto.giustore.search.commons.repository.ItemDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * The type Search service.
 */
@Service
public class ItemSearchService {
    private final ItemDocumentRepository itemDocumentRepository;

    /**
     * Instantiates a new Item search service.
     *
     * @param itemDocumentRepository the item document repository
     */
    public ItemSearchService(ItemDocumentRepository itemDocumentRepository) {
        this.itemDocumentRepository = itemDocumentRepository;
    }

    /**
     * Search list.
     *
     * @param query the query
     * @return the list
     */
    public List<ItemDocument> search(String query) {
        return itemDocumentRepository.fuzzySearch(query);
    }
}
