package at.giusto.giustore.search.commons.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@ComponentScan("at.giusto.giustore.search.commons")
@EnableElasticsearchRepositories("at.giusto.giustore.search.commons.repository")
public class GiustoreSearchCommonsConfig {
}
