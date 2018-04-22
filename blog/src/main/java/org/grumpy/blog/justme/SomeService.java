package org.grumpy.blog.justme;

import org.grumpy.blog.notme.Dependency;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class SomeService {

    @Inject
    Dependency dependency;

    @Inject
    public SomeService(Dependency dependency){
        this.dependency = dependency;
    }

    public String getItemDescription(String id){
        return dependency.getItemDescription(id);
    }
}
