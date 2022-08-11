package com.optimdev.covidtracker.model;

import java.util.Date;

public class CovidDailyDataModel {
	private String country;
	private String state;
	private String lastUpdated;
	private int confirmedCases;
	private int recoveredCases;
	private int deathCases;
	private int activeCases;
	private double incidentRate;
	private double caseFatalityRatio;
	
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
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public int getConfirmedCases() {
		return confirmedCases;
	}
	public void setConfirmedCases(int confirmedCases) {
		this.confirmedCases = confirmedCases;
	}
	public int getRecoveredCases() {
		return recoveredCases;
	}
	public void setRecoveredCases(int recoveredCases) {
		this.recoveredCases = recoveredCases;
	}
	public int getDeathCases() {
		return deathCases;
	}
	public void setDeathCases(int deathCases) {
		this.deathCases = deathCases;
	}
	public int getActiveCases() {
		return activeCases;
	}
	public void setActiveCases(int activeCases) {
		this.activeCases = activeCases;
	}
	public double getIncidentRate() {
		return incidentRate;
	}
	public void setIncidentRate(double incidentRate) {
		this.incidentRate = incidentRate;
	}
	public double getCaseFatalityRatio() {
		return caseFatalityRatio;
	}
	public void setCaseFatalityRatio(double caseFatalityRatio) {
		this.caseFatalityRatio = caseFatalityRatio;
	}
	
	@Override
	public String toString() {
		return "CovidDailyDataModel [country=" + country + ", state=" + state + ", lastUpdated=" + lastUpdated
				+ ", confirmedCases=" + confirmedCases + ", recoveredCases=" + recoveredCases + ", deathCases="
				+ deathCases + ", incidentRate=" + incidentRate + ", caseFatalityRatio=" + caseFatalityRatio + "]";
	}
}
