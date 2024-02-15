package com.paisesapirestconsumingwebservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paisesapirestconsumingwebservice.model.PaisDTO;
import com.paisesapirestconsumingwebservice.model.apiresponse.CountryData;
import com.paisesapirestconsumingwebservice.webservice.apirest.PaisRestService;
import com.paisesapirestconsumingwebservice.webservice.soap.PaisSoapClient;

import localhost.ws.ObtenerPaisResponse;

@RestController
@CrossOrigin(origins = "*")
public class PaisController {

	private final PaisSoapClient paisSoapClient;
	private final PaisRestService paisRestService;
	private static final Logger log = LoggerFactory.getLogger(PaisController.class);
	
	@Autowired
	public PaisController (PaisSoapClient paisSoapClient, PaisRestService paisRestService) {
	 this.paisSoapClient = paisSoapClient;	
	 this.paisRestService = paisRestService;
	}
	
	
	@GetMapping("/obtenerPais")
	public PaisDTO obtenerPais(@RequestParam String nombre) {
		ObtenerPaisResponse soapResponse = obtenerDatosSOAP(nombre);
		CountryData obtenerDatosAPI = obtenerDatosAPI(nombre);
		return construirPaisDTO(soapResponse, obtenerDatosAPI);
	}
	
	private ObtenerPaisResponse obtenerDatosSOAP(String nombre) {
		try {
			return paisSoapClient.obtenerPais(nombre);
		}catch (Exception e){
			log.error("No se obtuvo info del SOAP", e);
			return null;
		}
	}
	
	private CountryData obtenerDatosAPI(String nombre) {
		try {
			return paisRestService.obtenerPaisInfoApi(nombre);
		}catch (Exception e){
			log.error("No se obtuvo info de la API", e);
			return null;
		}
	}
	
	 private PaisDTO construirPaisDTO(ObtenerPaisResponse soapResponse, CountryData apiResponse){
	        PaisDTO.PaisDTOBuilder builder = PaisDTO.builder();

	        if (soapResponse != null) {
	            builder.nombre(soapResponse.getPais().getNombre())
	                    .capital(soapResponse.getPais().getCapital())
	                    .moneda(soapResponse.getPais().getMoneda())
	                    .poblacion(soapResponse.getPais().getPoblacion())
	                    .bandera(soapResponse.getPais().getBandera());
	        } else {
	            log.error("La respuesta del SOAP es nula");
	        }

	        if (apiResponse != null) {
	            builder.lenguajes(apiResponse.getLanguages())
	                    .mapas(apiResponse.getMaps());
	        } else {
	            log.error("La respuesta de la API es nula");
	        }

	        return builder.build();
	    }
	}
