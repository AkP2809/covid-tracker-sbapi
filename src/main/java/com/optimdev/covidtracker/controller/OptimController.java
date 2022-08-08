package com.optimdev.covidtracker.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimdev.covidtracker.model.CovidDataModel;
import com.optimdev.covidtracker.services.CovidDataService;

@RestController
public class OptimController {
	
	@RequestMapping("/getData")
	public List<CovidDataModel> getCovidData() throws IOException, InterruptedException{
		return CovidDataService.fetchCovidData();
	}
}
