package at.giusto.giustore.search.commons.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MeiliSearchConfig.class)
@ComponentScan("at.giusto.giustore.search.commons")
public class GiustoreSearchCommonsConfig {
}
