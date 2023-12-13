package com.rm.pais.rest;

import com.rm.pais.internal.DataRepo;
import com.rm.pais.rest.model.Country;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/pais")
public class PaisResource {

    @Inject
	private DataRepo dataRepo;
	
	@GET
	@Path("/{cca2 : [a-zA-Z]{2}}")
    @Produces(MediaType.APPLICATION_JSON)
	public Country getCountry(@PathParam("cca2") String id) {
		
		Country country = dataRepo.countrybyCCA2(id);
		
		if(country != null) {
			return country;
		}
		
		throw new NotFoundException("Resource not found");
	}
}
