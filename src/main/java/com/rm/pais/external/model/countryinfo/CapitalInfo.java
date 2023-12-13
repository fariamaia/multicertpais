package com.rm.pais.external.model.countryinfo;

public class CapitalInfo {

	private float[] latlng;

	
	public CapitalInfo() {
		super();
		latlng  = new float[]{ };
	}

	public CapitalInfo(float[] latlng) {
		super();
		this.latlng = latlng;
	}

	public float[] getLatlng() {
		return latlng;
	}

	public void setLatlng(float[] latlng) {
		this.latlng = latlng;
	}
	
	
	
}
