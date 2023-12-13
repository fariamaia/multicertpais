package com.rm.pais.rest.model;

public class Country {

	private String languagues;
	private int population;
	private String currencies;
	private String capital;
	private String icao;
	private String location;
	
	// Default constructor required for JSON mapping
    public Country(String languagues, int population, String currencies, String capital, String icao, String location) {
		super();
		this.languagues = languagues;
		this.population = population;
		this.currencies = currencies;
		this.capital = capital;
		this.icao = icao;
		this.location = location;
	}

	public String getLanguagues() {
		return languagues;
	}

	public void setLanguagues(String languagues) {
		this.languagues = languagues;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getCurrencies() {
		return currencies;
	}

	public void setCurrencies(String currencies) {
		this.currencies = currencies;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

    
    
}
