package com.rm.pais.external.model.countryinfo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.rm.pais.rest.model.Country;

public class PaisToCountryAdapterTest {

	@Test
	public void testtransformDefaultValues() {
		
		Pais pais = new Pais();
		
		Country country = PaisToCountryAdapter.transform(pais);
		
		assertEquals("",country.getCapital());
	}
}
