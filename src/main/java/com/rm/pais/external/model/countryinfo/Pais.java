package com.rm.pais.external.model.countryinfo;

import java.util.HashMap;
import java.util.Map;

public class Pais {

	private String cca2;
	private String cca3;
	private int population;
	private String[] capital;
	private CapitalInfo capitalInfo;
	private Map<String, Currency> currencies;
	private Map<String, String> languages;

	public Pais() {
		super();
		this.cca2 = "";
		this.cca3 = "";
		this.population = 0;
		this.capital = new String[] {};
		this.capitalInfo = new CapitalInfo();
		this.currencies = new HashMap<String, Currency>();
		this.languages = new HashMap<String, String>();
	}

	public String getCca2() {
		return cca2;
	}

	public void setCca2(String cca2) {
		this.cca2 = cca2;
	}

	public String getCca3() {
		return cca3;
	}

	public void setCca3(String cca3) {
		this.cca3 = cca3;
	}

	public String[] getCapital() {
		return capital;
	}

	public void setCapital(String[] capital) {
		this.capital = capital;
	}

	public CapitalInfo getCapitalInfo() {
		return capitalInfo;
	}

	public void setCapitalInfo(CapitalInfo capitalInfo) {
		this.capitalInfo = capitalInfo;
	}

	public Map<String, Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(Map<String, Currency> currencies) {
		this.currencies = currencies;
	}


	public Map<String, String> getLanguages() {
		return languages;
	}

	public void setLanguages(Map<String, String> languages) {
		this.languages = languages;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

}
