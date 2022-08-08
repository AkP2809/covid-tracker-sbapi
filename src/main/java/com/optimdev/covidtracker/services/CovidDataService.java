package com.optimdev.covidtracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.optimdev.covidtracker.model.CovidDataModel;

@Service
public class CovidDataService {

	private static String COVID_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private static List<CovidDataModel> allStats = new ArrayList<>();
	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public static List<CovidDataModel> fetchCovidData() throws IOException, InterruptedException {
		List<CovidDataModel> currentStats = new ArrayList<>();
		
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder()
									  .uri(URI.create(COVID_DATA_URL))
									  .build();
		
		HttpResponse<String> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
		
		StringReader csvDataReader = new StringReader(resp.body());
		
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvDataReader);
		for (CSVRecord record : records) {
			CovidDataModel cdm = new CovidDataModel();
			cdm.setCountry(record.get("Country/Region"));
			cdm.setState(record.get("Province/State"));
			cdm.setLatestTotalCases(Integer.parseInt(record.get(record.size() - 1)));
			cdm.setPreviousDayTotalCases(Integer.parseInt(record.get(record.size() - 2)));
			
			currentStats.add(cdm);
		}
		
		allStats = currentStats; 
		
//		int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
//		System.out.println(totalCases);
		
		return allStats;
	}
}
