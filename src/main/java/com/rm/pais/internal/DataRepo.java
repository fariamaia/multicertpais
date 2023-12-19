package com.rm.pais.internal;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.rm.pais.external.model.countryinfo.Pais;
import com.rm.pais.external.model.countryinfo.PaisToCountryAdapter;
import com.rm.pais.external.model.ninja.Airport;
import com.rm.pais.external.model.ninja.AirportsToCountryAdapter;
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
	private Client clientNinja;
	private WebTarget target;
	private Builder builder;
	private Response response;

	private static String url = "https://countryinfoapi.com/api/countries/?expand=true&fields=cca2,cca3,population,currencies,capital,capitalInfo,languages";
	private static String urlNinjas = "https://api.api-ninjas.com/v1/airports?city=%s&country=%s";
	// TODO remove API Key from source code
	private static String apiKeyNinjas = "YSqpgMPcJ8wCMUDY8aTluL1KJRpe0KeFdJm2phXJ";

	public void initialize() {
		logger.info("Singleton Data Repository is being created.");

		// Data storage in memory
		// Allow multiple read threads and only blocks chuncks for write
		countries = new ConcurrentHashMap<String, Country>();

		// Init JAX-RS REST client
		client = ClientBuilder.newClient();
		target = client.target(url);

		builder = target.request(MediaType.APPLICATION_JSON);

		refresh();

		//Start Client for Ninja APi
		//a new WebTarget per request
		clientNinja = ClientBuilder.newClient();

		// Create Scheduler to invoke the data refresh periodically
		executorService.scheduleAtFixedRate(this::refresh, 0, 60, TimeUnit.SECONDS);
	}

	private void refresh() {

		response = builder.get();

		if (response.getStatus() == 200) {
			List<Pais> paises = response.readEntity(new GenericType<List<Pais>>() {
			});

			// Process the list of dynamic objects
			for (Pais pais : paises) {

				countries.put(pais.getCca2().toLowerCase(), PaisToCountryAdapter.transform(pais));

			}
		} else {
			logger.severe("Failed to retrieve data from Country Info API. Status code: " + response.getStatus());
		}

	}

	public Country countrybyCCA2(String id) {

		if (countries.containsKey(id.toLowerCase())) {

			Country toreturn = countries.get(id.toLowerCase());

			// Add ICAO code to Country model
			String urln = String.format(urlNinjas, toreturn.getCapital(), id.toUpperCase());
			
			// Add custom HTTP header
			Builder builderninja = clientNinja.target(urln)
					.request(MediaType.APPLICATION_JSON);
			builderninja = builderninja.header("x-api-key", apiKeyNinjas);

			Response responseNinja = builderninja.get();

			if (responseNinja.getStatus() == 200) {
				List<Airport> airports = responseNinja.readEntity(new GenericType<List<Airport>>() {
				});
				toreturn.setIcao(AirportsToCountryAdapter.transform(airports));
			} else {
				logger.severe("Failed to retrieve data from API Ninjas. Status code: " + responseNinja.getStatus());
			}

			return toreturn;
		}

		return null;
	}

}
