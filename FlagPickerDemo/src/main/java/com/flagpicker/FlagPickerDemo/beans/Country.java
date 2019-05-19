package com.flagpicker.FlagPickerDemo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author manoj penupothu
 * This bean is created to represent country and its flag
 */
@Entity
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Integer id;
	
	
	private String name;
	
	@Lob
	@Column(length=100000)
	private String flag;
	
	@JsonIgnore
	private int requestCount;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Continent continentName;
	
	

	

	public Country(Integer id, String name, String flag, int requestCount) 
	{
		this.id = id;
		this.name = name;
		this.flag = flag;
		this.requestCount = requestCount;
	}


	public Country()
	{
	}

	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}

	
	public int getRequestCount() 
	{
		return requestCount;
	}

	public void setRequestCount(int requestCount)
	{
		this.requestCount = requestCount;
	}
	
	

	

	public Continent getContinentName()
	{
		return continentName;
	}

	public void setContinentName(Continent continentName)
	{
		this.continentName = continentName;
	}

	@Override
	public String toString()
	{
		return "Country [id=" + id + ", name=" + name + ", flag=" + flag + "]";
	}
	
	
	
}
