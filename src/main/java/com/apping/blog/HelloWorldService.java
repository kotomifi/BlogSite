package com.apping.blog;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by baisu on 15-3-27.
 */
@Path("/hello")
public class HelloWorldService {
    @GET
    @Path("/{param}")
    public Response getMessage(@PathParam("param") String message) {
        String output = "Jersey says " + message;
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/wd")
    public String getwd() {
        return "haha";
    }
}
