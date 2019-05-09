package com.flagpicker.FlagPickerDemo.operations.service;

import java.util.List;

import com.flagpicker.FlagPickerDemo.beans.Country;

public interface FlagPickerDemoOpsService {

	public List<Country> getAllCountries(String continentName);
	
	public byte[] getFlagByCountry(String countryName);
	
	public int getNumberOfViewsByCountry(String countryName);
}
