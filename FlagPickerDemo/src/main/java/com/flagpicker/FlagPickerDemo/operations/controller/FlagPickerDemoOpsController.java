package com.flagpicker.FlagPickerDemo.operations.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.flagpicker.FlagPickerDemo.beans.Country;
import com.flagpicker.FlagPickerDemo.beans.RequestCounter;
import com.flagpicker.FlagPickerDemo.operations.service.FlagPickerDemoOpsService;
/**
 * 
 * @author manoj penupothu
 * This class acts as a front controller for all the requests 
 *  related to Flag picker application
 */
@RestController
public class FlagPickerDemoOpsController {

	private static final Logger logger =LoggerFactory.getLogger(FlagPickerDemoOpsController.class);
	@Autowired
	private FlagPickerDemoOpsService flagPickService;
	
	/**
	 * This method takes input as a continent name and returns the list of counties
	 * @param continentName-provide continent name as a path variable
	 * @return -returns the list of countries associated with the continent name param.
	 */
	@GetMapping("/flagpicker/displayCountries/{continentName}")
	public List<Country> getCountries(@PathVariable String continentName){
		logger.info("Entered getCountries method of FlagPickerDemoOpsController class");
		logger.debug("Request paramater passed:"+continentName);
		return flagPickService.getAllCountries(continentName);
		
	}
	
	/**
	 * This method takes the input as country name and returns the byte stream of corresponding country flag
	 * @param countryName - provide country name as a path variable
	 * @return - returns the decoded byte stream of corresponding country
	 */
	@GetMapping(value="/flagpicker/displayFlag/{countryName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] getFlagsByCountry(@PathVariable String countryName) {
		logger.info("Entered getFlagsByCountry method of FlagPickerDemoOpsController class");
		logger.debug("Request paramater passed:"+countryName);
		return flagPickService.getFlagByCountry(countryName);
	}
	
	/**
	 * This method takes input as country name and returns the number of views of the flag of that country
	 * @param countryName- provide country name as a path variable
	 * @return -returns the number of views of a particular flag
	 */
	@GetMapping("/flagpicker/displayNoOfHits/{countryName}")
	public RequestCounter getNumberOfViewsByCountry(@PathVariable String countryName) {
		logger.info("Entered getNumberOfViewsByCountry method of FlagPickerDemoOpsController class");
		logger.debug("Request paramater passed:"+countryName);
		return new RequestCounter(countryName, flagPickService.getNumberOfViewsByCountry(countryName));
	}
}
