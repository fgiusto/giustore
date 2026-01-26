package at.giusto.giustore.search.commons.service;

import at.giusto.giustore.search.commons.entity.ItemDocument;
import at.giusto.giustore.search.commons.record.ItemSearchRequest;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;


/**
 * The type Search service.
 */
@Service
public class ItemSearchService {

    private final ElasticsearchOperations operations;

    public ItemSearchService(ElasticsearchOperations operations) {
        this.operations = operations;
    }

    public SearchHits<ItemDocument> search(ItemSearchRequest req) {

        BoolQuery.Builder bool = new BoolQuery.Builder();

        // Full-text search
        if (req.text() != null && !req.text().isBlank()) {
            bool.must(m -> m
                    .multiMatch(mm -> mm
                            .query(req.text())
                            .fields("title", "description")
                    )
            );
        }

        // Filter by category
        if (req.categoryId() != null) {
            bool.filter(f -> f
                    .term(t -> t
                            .field("category.id")
                            .value(req.categoryId())
                    )
            );
        }

        // Filter by owner
        if (req.ownerId() != null) {
            bool.filter(f -> f
                    .term(t -> t
                            .field("owner.id")
                            .value(req.ownerId())
                    )
            );
        }

        NativeQuery query = NativeQuery.builder()
                .withQuery(bool.build()._toQuery())
                .withPageable(
                        PageRequest.of(
                                req.page() == null ? 0 : req.page(),
                                req.size() == null ? 10 : req.size()
                        )
                )
                .build();

        return operations.search(query, ItemDocument.class);
    }
}
