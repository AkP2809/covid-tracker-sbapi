package com.optimdev.covidtracker.model;

public class CovidDataModel {
	private String country;
	private String state;
	private int latestTotalCases;
	private int previousDayTotalCases;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getLatestTotalCases() {
		return latestTotalCases;
	}
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	public int getPreviousDayTotalCases() {
		return previousDayTotalCases;
	}
	public void setPreviousDayTotalCases(int previousDayTotalCases) {
		this.previousDayTotalCases = previousDayTotalCases;
	}
	
	@Override
	public String toString() {
		return "CovidDataModel [country=" + country + ", state=" + state + ", latestTotalCases=" + latestTotalCases
				+ "]";
	}
}