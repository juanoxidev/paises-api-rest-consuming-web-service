package com.paisesapirestconsumingwebservice.model.apiresponse;

import java.util.Map;

import lombok.Data;

@Data
public class CountryData {
	private Map<String, String>	languages;
	private Map<String, String>	maps;
}
