package at.giusto.giustore.search.commons.service;

import at.giusto.giustore.search.commons.entity.ItemDocument;
import at.giusto.giustore.search.commons.repository.ItemDocumentRepository;
import at.giusto.giustore.search.commons.request.CreateItemRequest;
import org.springframework.stereotype.Service;

/**
 * The type Item save service.
 */
@Service
public class ItemSaveService {

    private final ItemDocumentRepository itemDocumentRepository;

    /**
     * Instantiates a new Item save service.
     *
     * @param itemDocumentRepository the item document repository
     */
    public ItemSaveService(ItemDocumentRepository itemDocumentRepository) {
        this.itemDocumentRepository = itemDocumentRepository;
    }

    /**
     * Save item document.
     *
     * @param title       the title
     * @param description the description
     * @return the item document
     */
    public ItemDocument save(String title, String description) {
        return itemDocumentRepository.save(new ItemDocument(title, description));
    }

    /**
     * Save item document.
     *
     * @param createItemRequest the create item request
     * @return the item document
     */
    public ItemDocument save(CreateItemRequest createItemRequest) {
        return save(createItemRequest.title(), createItemRequest.description());
    }

    /**
     * Save item document.
     *
     * @param itemDocument the item document
     * @return the item document
     */
    public ItemDocument save(ItemDocument itemDocument) {
        return itemDocumentRepository.save(itemDocument);
    }
}
