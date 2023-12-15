package com.rm.pais.external.model.ninja;

import java.util.List;

public class AirportsToCountryAdapter {

	
	public static String transform(List<Airport> airports) {
		
		StringBuilder icao = new StringBuilder();
		boolean first = true;

			for (Airport airport : airports) {
				if (!first) {
					icao.append(", ");
				}
				icao.append(airport.getIcao());
				first = false;
			}

		
		return icao.toString();
	}
}
