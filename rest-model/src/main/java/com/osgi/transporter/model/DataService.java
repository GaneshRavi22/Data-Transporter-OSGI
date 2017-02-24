package com.osgi.transporter.model;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes({MediaType.APPLICATION_JSON, "text/json"})
@Produces({MediaType.APPLICATION_JSON, "text/json"})
public interface DataService {
    @GET
    @Path("/")
    public DataRecord[] getAll();
    
    @POST
    @Path("/")
    public void addDataRecord(DataRecord data);
}
