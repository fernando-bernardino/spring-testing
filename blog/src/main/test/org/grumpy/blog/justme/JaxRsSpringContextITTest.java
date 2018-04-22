package org.grumpy.blog.justme;

import org.grumpy.blog.notme.Dependency;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class JaxRsSpringContextITTest {

    @Test
    public void testSimplyStartingSpringContext(){
        ApplicationContext springContext = new AnnotationConfigApplicationContext("org.grumpy.blog.justme");

        Dependency dependency = springContext.getBean(Dependency.class);
        JaxRsController jaxRcController =  (JaxRsController) springContext.getBean("jaxRcController");
        String id = "id";
        String description = "description";
        when(dependency.getItemDescription(id)).thenReturn(description);

        assertThat(jaxRcController.getItemDescription(id), is(description));
    }

}
