package at.giustore.search.commons.repository;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.model.SearchResult;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * The type Meili repository.
 *
 * @param <T> the type parameter
 */
@Service
public class MeiliRepositoryImpl<T> implements MeiliRepository<T> {

    private final Client client;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Instantiates a new Meili repository.
     *
     * @param client the client
     */
    public MeiliRepositoryImpl(Client client) {
        this.client = client;
    }

    @Override
    public void save(String indexName, T document) {
        try {
            String json = mapper.writeValueAsString(List.of(document));
            client.index(indexName).addDocuments(json);
        } catch (Exception e) {
            throw new RuntimeException("Failed to index document", e);
        }
    }

    @Override
    public void saveAll(String indexName, List<T> documents) {
        try {
            String json = mapper.writeValueAsString(documents);
            client.index(indexName).addDocuments(json);
        } catch (Exception e) {
            throw new RuntimeException("Failed to index documents", e);
        }
    }

    @Override
    public SearchResult search(String indexName, String query) {
        return client.index(indexName).search(query);
    }

    @Override
    public void delete(String indexName, String id) {
        client.index(indexName).deleteDocument(id);
    }
}
