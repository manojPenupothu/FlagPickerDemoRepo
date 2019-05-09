package com.flagpicker.FlagPickerDemo.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author manoj penupothu
 * This bean is created to represent Continent and its countries
 */
@Entity
@ApiModel(description="Continent class")
public class Continent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Size(min=2,message="Continent name should have atleast 2 characters")
	@ApiModelProperty(notes="Continent name should have atleast 2 characters")
	private String continent;
	
	private int searchCount;
	
	
	@OneToMany(mappedBy="continentName")
	private List<Country> countries;
	
	
	
	
	
	public Continent(int id,String continent,int searchCount)
	{
		this.id = id;
		this.continent = continent;
		this.searchCount = searchCount;
	}

	public Continent() {
	}

	public String getContinent()
	{
		return continent;
	}

	public void setContinent(String continent) 
	{
		this.continent = continent;
	}
	public int getId()
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public List<Country> getCountries()
	{
		return countries;
	}

	public void setCountries(List<Country> countries)
	{
		this.countries = countries;
	}

	public int getSearchCount()
	{
		return searchCount;
	}

	public void setSearchCount(int searchCount)
	{
		this.searchCount = searchCount;
	}

	@Override
	public String toString() 
	{
		return "Continent [id=" + id + ", continent=" + continent + ", searchCount=" + searchCount + ", countries="
				+ countries + "]";
	}

	
	
	
	
}
