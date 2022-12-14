package com.optimdev.covidtracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.optimdev.covidtracker.model.CovidConfirmedDataModel;
import com.optimdev.covidtracker.model.CovidDailyDataModel;
import com.optimdev.covidtracker.model.CovidDeathsDataModel;
import com.optimdev.covidtracker.model.CovidRecoveredDataModel;

@Service
public class CovidDataService {

	private static String COVID_DATA_CONFIMRED_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private static String COVID_DATA_DEATHS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	private static String COVID_DATA_RECOVERED_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
	
	private static String COVID_DATA_DAILY_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/";
	
	private static List<CovidConfirmedDataModel> confirmedStats = new ArrayList<>();
	private static List<CovidDeathsDataModel> deathStats = new ArrayList<>();
	private static List<CovidRecoveredDataModel> recoveredStats = new ArrayList<>();
	private static List<CovidDailyDataModel> dailyStats = new ArrayList<>();
	
	@PostConstruct
	@Scheduled(cron = "0 0 1 * * ?")
	public static List<CovidConfirmedDataModel> getConfirmedCasesGlobal() throws IOException, InterruptedException {
		List<CovidConfirmedDataModel> currentStats = new ArrayList<>();
		
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder()
									  .uri(URI.create(COVID_DATA_CONFIMRED_URL))
									  .build();
		
		HttpResponse<String> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
		
		StringReader csvDataReader = new StringReader(resp.body());
		
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvDataReader);
		for (CSVRecord record : records) {
			CovidConfirmedDataModel cdm = new CovidConfirmedDataModel();
			cdm.setCountry(record.get("Country/Region"));
			cdm.setState(record.get("Province/State"));
			cdm.setLatestTotalCases(Integer.parseInt(record.get(record.size() - 1)));
			cdm.setPreviousDayTotalCases(Integer.parseInt(record.get(record.size() - 2)));
			
			currentStats.add(cdm);
		}
		
		confirmedStats = currentStats; 
		
//		int totalConfirmedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		
		return confirmedStats;
	}
	
	@PostConstruct
	@Scheduled(cron = "0 0 1 * * ?")
	public static List<CovidDeathsDataModel> getDeathCasesGlobal() throws IOException, InterruptedException {
		List<CovidDeathsDataModel> currentStats = new ArrayList<>();
		
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder()
									  .uri(URI.create(COVID_DATA_DEATHS_URL))
									  .build();
		
		HttpResponse<String> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
		
		StringReader csvDataReader = new StringReader(resp.body());
		
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvDataReader);
		for (CSVRecord record : records) {
			CovidDeathsDataModel cdm = new CovidDeathsDataModel();
			cdm.setCountry(record.get("Country/Region"));
			cdm.setState(record.get("Province/State"));
			cdm.setLatestDeathCases(Integer.parseInt(record.get(record.size() - 1)));
			cdm.setPreviousDayDeathCases(Integer.parseInt(record.get(record.size() - 2)));
			
			currentStats.add(cdm);
		}
		
		deathStats = currentStats; 
		
//		int totalDeathCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		
		return deathStats;
	}
	
	@PostConstruct
	@Scheduled(cron = "0 0 1 * * ?")
	public static List<CovidRecoveredDataModel> getRecoveredCasesGlobal() throws IOException, InterruptedException {
		List<CovidRecoveredDataModel> currentStats = new ArrayList<>();
		
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder()
									  .uri(URI.create(COVID_DATA_RECOVERED_URL))
									  .build();
		
		HttpResponse<String> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
		
		StringReader csvDataReader = new StringReader(resp.body());
		
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvDataReader);
		for (CSVRecord record : records) {
			CovidRecoveredDataModel cdm = new CovidRecoveredDataModel();
			cdm.setCountry(record.get("Country/Region"));
			cdm.setState(record.get("Province/State"));
			cdm.setLatestRecoveredCases(Integer.parseInt(record.get(record.size() - 1)));
			cdm.setPreviousDayRecoveredCases(Integer.parseInt(record.get(record.size() - 2)));
			
			currentStats.add(cdm);
		}
		
		recoveredStats = currentStats; 
		
//		int totalDeathCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		
		return recoveredStats;
	}
	
	@PostConstruct
	@Scheduled(cron = "0 0 10,16,22 * * *")
	public static List<CovidDailyDataModel> getDailyCasesGlobal() throws IOException, InterruptedException {
		List<CovidDailyDataModel> currentStats = new ArrayList<>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy"); 
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Using today's date
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest req;
		HttpResponse<String> resp;
		
		while(true) {
			String output = formatter.format(c.getTime());
			
			StringBuilder sb = new StringBuilder(COVID_DATA_DAILY_URL);
			sb.append(output);
			sb.append(".csv");
			
			req = HttpRequest.newBuilder()
							 .uri(URI.create(sb.toString()))
							 .build();
			
			resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
			if((resp.body()).equals("404: Not Found"))
				c.add(Calendar.DATE, -1);
			else
				break;
		}
		
		StringReader csvDataReader = new StringReader(resp.body());
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvDataReader);
		for (CSVRecord record : records) {
			CovidDailyDataModel cdm = new CovidDailyDataModel();
			cdm.setCountry(record.get("Country_Region"));
			cdm.setState(record.get("Province_State"));
			cdm.setLastUpdated((record.get("Last_Update")).split(" ")[0]);
			cdm.setConfirmedCases(Integer.valueOf((record.get("Confirmed").length() == 0 ? "-1" : record.get("Confirmed"))));
			cdm.setRecoveredCases(Integer.valueOf((record.get("Recovered")).length() == 0 ? "-1" : record.get("Recovered")));
			cdm.setDeathCases(Integer.valueOf((record.get("Deaths")).length() == 0 ? "-1" : record.get("Deaths")));
			cdm.setActiveCases(Integer.valueOf((record.get("Active")).length() == 0 ? "-1" : record.get("Active")));
			cdm.setIncidentRate(Double.valueOf((record.get("Incident_Rate")).length() == 0 ? "-1" : record.get("Incident_Rate")));
			cdm.setCaseFatalityRatio(Double.valueOf((record.get("Case_Fatality_Ratio")).length() == 0 ? "-1" : record.get("Case_Fatality_Ratio")));
			
			currentStats.add(cdm);
		}
		
		dailyStats = currentStats;
		
		return dailyStats;
	}
}
