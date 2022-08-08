package com.optimdev.covidtracker.model;

public class CovidRecoveredDataModel {
	private String country;
	private String state;
	private int latestRecoveredCases;
	private int previousDayRecoveredCases;
	
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
	public int getLatestRecoveredCases() {
		return latestRecoveredCases;
	}
	public void setLatestRecoveredCases(int latestRecoveredCases) {
		this.latestRecoveredCases = latestRecoveredCases;
	}
	public int getPreviousDayRecoveredCases() {
		return previousDayRecoveredCases;
	}
	public void setPreviousDayRecoveredCases(int previousDayRecoveredCases) {
		this.previousDayRecoveredCases = previousDayRecoveredCases;
	}
	
	@Override
	public String toString() {
		return "CovidRecoveredDataModel [country=" + country + ", state=" + state + ", latestRecoveredCases="
				+ latestRecoveredCases + ", previousDayRecoveredCases=" + previousDayRecoveredCases + "]";
	}
}
