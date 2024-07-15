package org.dasun;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.dasun.test.TestBean;

@Path("/hello")
public class GreetingResource {

    @Inject
    private TestBean testBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return testBean.SayHello();
    }
}
