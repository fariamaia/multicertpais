package com.rm.pais.internal;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.rm.pais.external.model.countryinfo.Pais;
import com.rm.pais.external.model.countryinfo.PaisToCountryAdapter;
import com.rm.pais.rest.model.Country;

import jakarta.enterprise.concurrent.ManagedScheduledExecutorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class DataRepo {

	@Inject
    private ManagedScheduledExecutorService executorService;
	
	private static final Logger logger = Logger.getLogger(DataRepo.class.getName());
	
	private ConcurrentHashMap<String, Country> countries;
	
	private Client client;
	private WebTarget target;
	private Builder builder;
	private Response response;


	public void initialize() {
		logger.info("Singleton Data Repository is being created.");

		
		// Data storage in memory
		// Allow multiple read threads and only blocks chuncks for write
		countries = new ConcurrentHashMap<String, Country>();
		
		// Init JAX-RS REST client
		client = ClientBuilder.newClient();
		target = client.target(
				"https://countryinfoapi.com/api/countries/?expand=true&fields=cca2,cca3,population,currencies,capital,capitalInfo,languages");
		builder = target.request(MediaType.APPLICATION_JSON);
		
		refresh();
		
		
		//Create Scheduler to invoke the data refresh periodically
		executorService.scheduleAtFixedRate(this::refresh, 0, 10, TimeUnit.SECONDS);
	}
	
	
	private void refresh() {
		
		response = builder.get();

		if (response.getStatus() == 200) {
            List<Pais> paises = response.readEntity(new GenericType<List<Pais>>() {});

            // Process the list of dynamic objects
            for (Pais pais : paises) {
            	
            	countries.put(pais.getCca2().toLowerCase(), PaisToCountryAdapter.transform(pais));
            	
            }
        } else {
        	logger.severe("Failed to retrieve data. Status code: " + response.getStatus());
        }
		
	
	}
	
	
	public Country countrybyCCA2(String id) {
		
		if(countries.containsKey(id.toLowerCase())) {
			return countries.get(id.toLowerCase());
		}
		
		return null;
	}


}
