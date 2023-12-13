package com.rm.pais.external.model.countryinfo;
	
import java.util.Map;

import com.rm.pais.rest.model.Country;

public class PaisToCountryAdapter {

	public static Country transform(Pais pais) {

		String capital = "";
		if (pais.getCapital().length > 0) {
			capital = pais.getCapital()[0];
		}

		String location = "";
		if (pais.getCapitalInfo().getLatlng().length == 2) {
			location = pais.getCapitalInfo().getLatlng()[0] + ", " + pais.getCapitalInfo().getLatlng()[1];
		}

		try {
			return new Country(adaptLanguagues(pais.getLanguages()), pais.getPopulation(),
					adaptCurrencies(pais.getCurrencies()), capital, pais.getCca3(), location);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	private static String adaptLanguagues(Map<String, String> listOfLangs) {

		StringBuilder languagues = new StringBuilder();

		boolean first = true;

		if (listOfLangs.size() > 0) {

				for (Map.Entry<String, String> entry : listOfLangs.entrySet()) {
					if (!first) {
						languagues.append(", ");
					}
					languagues.append(entry.getValue());
					first = false;
				}

		}

		return languagues.toString();
	}

	private static String adaptCurrencies(Map<String, Currency> currencyMap) {

		StringBuilder currency = new StringBuilder();

		boolean first = true;

		if (currencyMap.size() > 0) {

			for (Map.Entry<String, Currency> entry : currencyMap.entrySet()) {
				if (!first) {
					currency.append(", ");
				}
				currency.append(entry.getValue().getName());
				first = false;
			}

		}

		return currency.toString();
	}

}
