package org.grumpy.blog.justme;

import org.grumpy.blog.notme.Dependency;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
@ComponentScan(basePackages = {"org.grumpy.blog.justme"})
public class SomeConfig {

    @Bean
    public Dependency dependency() {
        return mock(Dependency.class);
    }
}
