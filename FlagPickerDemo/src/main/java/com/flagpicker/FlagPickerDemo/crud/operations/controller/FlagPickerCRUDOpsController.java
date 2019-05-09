package com.flagpicker.FlagPickerDemo.crud.operations.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flagpicker.FlagPickerDemo.beans.Continent;
import com.flagpicker.FlagPickerDemo.beans.Country;
import com.flagpicker.FlagPickerDemo.exceptions.DatabaseInsertionException;
import com.flagpicker.FlagPickerDemo.helpers.HelperMessage;
import com.flagpicker.FlagPickerDemo.repositories.ContinentRepository;
import com.flagpicker.FlagPickerDemo.repositories.CountryRepository;
/**
 * 
 * @author manoj penupothu
 * This class is responsible for CREATE,READ,UPDATE,DELETE 
 * operations of continents data into database.
 */
@RestController
public class FlagPickerCRUDOpsController {

	private static final Logger logger=LoggerFactory.getLogger(FlagPickerCRUDOpsController.class);
	
	@Autowired
	private ContinentRepository continentRepo;
	
	@Autowired
	private CountryRepository countryRepo;
	
	/**
	 * This method is responsible for inserting data into the database
	 * @param AllContinents - Actual json consists of continents,countries and their
	 * respective flags
	 * @return success-HelperMessage class with success message
	 * failure - throws DatabaseInsertionException with failure message
	 */
	@PostMapping("/flagpicker/save/continents")
	public HelperMessage saveContinents(@Valid @RequestBody Continent[] AllContinents)
	{
		
		try {
					logger.info("Entered into saveContinents method of FlagPickerCRUDOpsController class");
					for(Continent con:AllContinents) {
						
						
						List<Continent> persistedContinentList=continentRepo.findAll();
						List<Country> persistedCountryList=countryRepo.findAll();
						boolean isContinentExists=false;
						for(Continent persistedContinent:persistedContinentList)
						{
							if(persistedContinent.getContinent().equals(con.getContinent()))
							{
								isContinentExists=true;
								con.setId(persistedContinent.getId());
							}
						}
						logger.debug("Is Continent:"+con.getContinent()+" exists in database"+isContinentExists);
						if(isContinentExists)
						{
								for(Country country:con.getCountries())
								{
									for(Country persistedCountry:persistedCountryList) 
									{
										if(persistedCountry.getName().equals(country.getName()))
										{
											country.setId(persistedCountry.getId());
										}
									}
									country.setContinentName(con);
									countryRepo.save(country);
								}
						}
						else 
						{
							Continent conSaved=continentRepo.save(con);
							for(Country country:con.getCountries())
							{
								for(Country persistedCountry:persistedCountryList)
								{
									if(persistedCountry.getName().equals(country.getName()))
									{
									    country.setId(persistedCountry.getId());
									}
								}
									country.setContinentName(conSaved);
									countryRepo.save(country);

							}
						}
					}
					logger.info("Exiting from saveContinents method of FlagPickerCRUDOpsController class");
		}catch(Exception e) {
			logger.error("Exception occured in saveContinents method of FlagPickerCRUDOpsController class"+e);
			throw new DatabaseInsertionException("Database is down.Please try after sometime!!!");
		}
		return new HelperMessage("Data Saved successfully!!!");
		
	}
	
}
