package com.optimdev.covidtracker.model;

public class CovidDeathsDataModel {
	private String country;
	private String state;
	private int latestDeathCases;
	private int previousDayDeathCases;
	
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
	public int getLatestDeathCases() {
		return latestDeathCases;
	}
	public void setLatestDeathCases(int latestDeathCases) {
		this.latestDeathCases = latestDeathCases;
	}
	public int getPreviousDayDeathCases() {
		return previousDayDeathCases;
	}
	public void setPreviousDayDeathCases(int previousDayDeathCases) {
		this.previousDayDeathCases = previousDayDeathCases;
	}
	
	@Override
	public String toString() {
		return "CovidDeathsDataModel [country=" + country + ", state=" + state + ", latestDeathCases="
				+ latestDeathCases + ", previousDayDeathCases=" + previousDayDeathCases + "]";
	}
}
