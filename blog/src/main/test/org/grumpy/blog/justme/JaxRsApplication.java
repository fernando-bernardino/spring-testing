package org.grumpy.blog.justme;

import com.google.common.collect.ImmutableSet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import java.util.Set;

@ApplicationPath("rs")
public class JaxRsApplication extends Application {
    protected ApplicationContext springContext;

    @Context
    protected ServletContext servletContext;

    public Set<Object> getSingletons() {
        try {

            springContext = new AnnotationConfigApplicationContext("org.grumpy.blog.justme");

            return ImmutableSet.of(springContext.getBean("jaxRcController"));

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public ApplicationContext springContext() {
        return springContext;
    }

}
