package com.johnmanko.services.data.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Collection;
import com.johnmanko.services.data.services.DataRecord;
import com.johnmanko.services.data.services.DataService;
import com.johnmanko.services.data.services.RedisException;
import jakarta.enterprise.context.RequestScoped;

@Path("/data")
@RequestScoped
public class DataEndpointResource {

    @Inject
    DataService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllValues() {

        Collection<DataRecord> values;
        try {
            values = service.getAllValues();
        } catch (RedisException ex) {
            return Response.serverError().build();
        }

        return Response.ok(values).build();

    }
}
