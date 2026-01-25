package at.giustore.search.commons.repository;

import at.giustore.search.commons.record.ItemDocument;
import com.meilisearch.sdk.Client;
import tools.jackson.databind.ObjectMapper;

public class ItemMeiliRepository extends MeiliRepositoryImpl<ItemDocument> {
    /**
     * Instantiates a new Meili repository.
     *
     * @param client the client
     * @param mapper the mapper
     */
    public ItemMeiliRepository(Client client, ObjectMapper mapper) {
        super(client, mapper);
    }
}
