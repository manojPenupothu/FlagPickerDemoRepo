package com.flagpicker.FlagPickerDemo.operations.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.flagpicker.FlagPickerDemo.beans.Continent;
import com.flagpicker.FlagPickerDemo.beans.Country;
import com.flagpicker.FlagPickerDemo.helpers.JsonToPojoHelper;
import com.flagpicker.FlagPickerDemo.repositories.CountryRepository;
@Component
public class FlagPickerDemoOpsServiceImpl implements FlagPickerDemoOpsService{

	private static final Logger logger=LoggerFactory.getLogger(FlagPickerDemoOpsServiceImpl.class);
	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired
	JsonToPojoHelper jsonHelper;
	
	@Autowired
	Environment environment;
	
	
	@Override
	/**
	 * This service method is responsible for 
	 * calling country repository to return list of countries
	 */
	public List<Country> getAllCountries(String continentName) {
		
		logger.info("Entered getAllCountries method of FlagPickerDemoOpsServiceImpl with param:"+continentName);
		if(environment.getProperty("read.from.json").equals("Y"))
		{
			try 
			{
				List<Continent> result = jsonHelper.mapJsonToPojo(environment.getProperty("json.read.file.name")).stream()               
				            			.filter(continent -> continent.getContinent().equals(continentName))     
				            			.collect(Collectors.toList());
				return result.get(0).getCountries();
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				logger.error("Exception occured in getAllCountries method of FlagPickerDemoOpsServiceImpl"+e);
			}  
			 
		     
		}
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
		if(environment.getProperty("read.from.json").equals("Y"))
		{
			try 
			{
				List<Continent> continents=jsonHelper.mapJsonToPojo(environment.getProperty("json.read.file.name"));
				for(Continent continent:continents) 
				{
					List<Country> countryList = continent.getCountries()
											.stream()
											.filter(country ->country.getName().equals(countryName))
											.collect(Collectors.toList());
					if(countryList.size()>0)
						return Base64.decodeBase64(countryList.get(0).getFlag());
				}
			}
			catch(Exception e) 
			{
				logger.error("Error occured in getFlagByCountry method of FlagPickerDemoOpsServiceImpl"+e);
			}
		}
		else {
				logger.info("Entered getFlagByCountry method of FlagPickerDemoOpsServiceImpl with param:"+countryName);
				Country country=countryRepo.getFlagByCountry(countryName);
				int counter=country.getRequestCount();
				logger.debug("Number of views for the flag from database:"+counter);
				counter++;
				countryRepo.updateNoOfViewsCounterByCountry(counter, countryName);
				logger.info("Number of views counter updation successfull");
				return Base64.decodeBase64(country.getFlag());
		}
		return null;
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
