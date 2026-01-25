package at.giusto.giustore.search.commons.repository;

import at.giusto.giustore.search.commons.record.ItemDocument;
import com.meilisearch.sdk.Client;
import org.springframework.stereotype.Service;

@Service
public class ItemDocumentRepository extends MeiliRepositoryImpl<ItemDocument> {
    /**
     * Instantiates a new Meili repository.
     *
     * @param client the client
     */
    public ItemDocumentRepository(Client client) {
        super(client);
    }
}
