package com.flagpicker.FlagPickerDemo.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flagpicker.FlagPickerDemo.beans.Country;

@Repository
@Transactional
public interface CountryRepository extends JpaRepository<Country,Integer>{
	
	/**
	 * 
	 * @param continentName
	 * @return-This respository method will return the list of countries filtered bases on continent
	 */
	@Query(value="select c from Country c inner join c.continentName cn where cn.continent=?1")
	public List<Country> getAllCountries(String continentName);
	
	
	/**
	 * 
	 * @param countryName
	 * @return - This reposotiry method will return country object bases on country name parameter
	 */
	@Query(value="select c from Country c where c.name=?1")
	public Country getFlagByCountry(String countryName);
	
	/**
	 * 
	 * @param countryName
	 * @return- This respository method returns the number of views of the flag
	 */
	@Query(value="select c.requestCount from Country c where c.name=?1")
	public Integer getNumberOfViewsByCountry(String countryName);
	
	/**
	 * This DML method is reponsible for updating number of views counter of particular country
	 * @param count
	 * @param countryName
	 */
	@Modifying(clearAutomatically = true)
	@Query("update Country c set c.requestCount=:updatedCount where c.name=:countryName")
	public void updateNoOfViewsCounterByCountry(@Param("updatedCount") Integer count,@Param("countryName") String countryName );
}
