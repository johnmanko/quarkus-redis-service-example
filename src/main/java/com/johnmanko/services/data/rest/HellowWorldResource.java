package com.johnmanko.services.data.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
@RequestScoped
public class HellowWorldResource {

    public static final String WORLD = "World!";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getWorld() {

        return Response.ok(WORLD).build();

    }
}
