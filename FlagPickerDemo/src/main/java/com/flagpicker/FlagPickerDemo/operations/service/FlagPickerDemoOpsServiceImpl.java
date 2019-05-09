package com.flagpicker.FlagPickerDemo.operations.service;

import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flagpicker.FlagPickerDemo.beans.Country;
import com.flagpicker.FlagPickerDemo.repositories.CountryRepository;
@Component
public class FlagPickerDemoOpsServiceImpl implements FlagPickerDemoOpsService{

	private static final Logger logger=LoggerFactory.getLogger(FlagPickerDemoOpsServiceImpl.class);
	@Autowired
	private CountryRepository countryRepo;
	
	@Override
	/**
	 * This service method is responsible for 
	 * calling country repository to return list of countries
	 */
	public List<Country> getAllCountries(String continentName) {
		// TODO Auto-generated method stub
		logger.info("Entered getAllCountries method of FlagPickerDemoOpsServiceImpl with param:"+continentName);
		return countryRepo.getAllCountries(continentName);
	}

	@Override
	/**
	 * This service method is responsible for
	 * getting byte array of flag and updation 
	 * of number of views of a flag
	 */
	public byte[] getFlagByCountry(String countryName) {
		// TODO Auto-generated method stub
		logger.info("Entered getFlagByCountry method of FlagPickerDemoOpsServiceImpl with param:"+countryName);
		Country country=countryRepo.getFlagByCountry(countryName);
		int counter=country.getRequestCount();
		logger.debug("Number of views for the flag from database:"+counter);
		counter++;
		countryRepo.updateNoOfViewsCounterByCountry(counter, countryName);
		logger.info("Number of views counter updation successfull");
		return Base64.decodeBase64(country.getFlag());
	}

	@Override
	/**
	 * This service method is responsible for 
	 * returning the number of views of the flag of particular country
	 */
	public int getNumberOfViewsByCountry(String countryName) {
		// TODO Auto-generated method stub
		logger.info("Entered getNumberOfViewsByCountry method of FlagPickerDemoOpsServiceImpl with param:"+countryName);
		return countryRepo.getNumberOfViewsByCountry(countryName);
	}

}
