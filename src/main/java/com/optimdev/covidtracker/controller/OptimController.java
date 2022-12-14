package com.optimdev.covidtracker.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimdev.covidtracker.model.CovidConfirmedDataModel;
import com.optimdev.covidtracker.model.CovidDailyDataModel;
import com.optimdev.covidtracker.model.CovidDeathsDataModel;
import com.optimdev.covidtracker.model.CovidRecoveredDataModel;
import com.optimdev.covidtracker.services.CovidDataService;

@RestController
public class OptimController {
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getConfirmedCasesGlobal")
	public List<CovidConfirmedDataModel> getConfirmedCasesGlobal() throws IOException, InterruptedException{
		return CovidDataService.getConfirmedCasesGlobal();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getDeathCasesGlobal")
	public List<CovidDeathsDataModel> getDeathCasesGlobal() throws IOException, InterruptedException{
		return CovidDataService.getDeathCasesGlobal();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getRecoveredCasesGlobal")
	public List<CovidRecoveredDataModel> getRecoveredCasesGlobal() throws IOException, InterruptedException{
		return CovidDataService.getRecoveredCasesGlobal();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getDailyCasesGlobal")
	public List<CovidDailyDataModel> getDailyCasesGlobal() throws IOException, InterruptedException{
		return CovidDataService.getDailyCasesGlobal();
	}
}
