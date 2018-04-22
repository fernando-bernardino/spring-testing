package org.grumpy.blog.justme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaxRsConfig {

    @Bean
    public JaxRsController jaxRcController() {
        return new JaxRsController();
    }
}
