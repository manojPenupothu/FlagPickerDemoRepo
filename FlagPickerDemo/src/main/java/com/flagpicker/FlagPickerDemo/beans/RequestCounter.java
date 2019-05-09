package com.flagpicker.FlagPickerDemo.beans;
/**
 * 
 * @author manoj penupothu
 *	@comments -This class acts as a response bean for
 *	number of views of a flag endpoint
 */
public class RequestCounter {

	private String countryName;
	private int numberOfViews;
	public RequestCounter(String countryName, int numberOfViews) {
		this.countryName = countryName;
		this.numberOfViews = numberOfViews;
	}
	public RequestCounter() {
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getNumberOfViews() {
		return numberOfViews;
	}
	public void setNumberOfViews(int numberOfViews) {
		this.numberOfViews = numberOfViews;
	}
	
	
	
}
