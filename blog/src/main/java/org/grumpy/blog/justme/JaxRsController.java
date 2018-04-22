package org.grumpy.blog.justme;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/item")
@Component
public class JaxRsController {

    @Inject
    SomeService someService;

    @Path("{id}")
    @GET
    @Produces("application/json")
    public String getItemDescription(@PathParam("id") String id) {
        return someService.getItemDescription(id);
    }
}
