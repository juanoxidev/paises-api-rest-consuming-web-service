package com.paisesapirestconsumingwebservice.webservice.soap;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import localhost.ws.ObtenerPaisRequest;
import localhost.ws.ObtenerPaisResponse;


public class PaisSoapClient extends WebServiceGatewaySupport{

	private static final Logger log = LoggerFactory.getLogger(PaisSoapClient.class);
	private static final String baseUrl = "http://localhost:8081/ws";
	
	public ObtenerPaisResponse obtenerPais (String pais) {
		ObtenerPaisRequest request = new ObtenerPaisRequest();
		request.setName(pais);
		log.info("Obteniendo info del pais : " + pais);
		ObtenerPaisResponse response = (ObtenerPaisResponse) getWebServiceTemplate().marshalSendAndReceive(baseUrl+"/paises", request, new SoapActionCallback(baseUrl+"/ObtenerPaisRequest"));
		return response;
		
		
	}
}
