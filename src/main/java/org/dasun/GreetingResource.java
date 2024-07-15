package org.dasun;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.dasun.test.TestBean;

@Path("/hello")
public class GreetingResource {

    // This is field injection
    @Inject
    private TestBean testBean1;

    // This is the method injection
    private TestBean testBean2;
    @Inject
    public void greetingService(TestBean testBean){
        this.testBean2 = testBean;
    }

    // This is the constructor injection
    private TestBean testBean3;
    @Inject
    public GreetingResource(TestBean testBean) {
        this.testBean3= testBean;
    }


    @GET
    @Path("1")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello1() {
        return "Field injection saying "  + testBean1.SayHello();
    }

    @GET
    @Path("2")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello2() {
        return "Method injection saying " + testBean2.SayHello();
    }

    @GET
    @Path("3")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello3() {
        return "Constructor injection saying " + testBean3.SayHello();
    }

}
