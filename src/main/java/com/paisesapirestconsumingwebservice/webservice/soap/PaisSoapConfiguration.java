package com.paisesapirestconsumingwebservice.webservice.soap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

// configuracion del marshaller que pasa de codigo java a codigo de lo que recibe el soap.
@Configuration
public class PaisSoapConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// debe llamarse igual que el packagename del pom donde se autogeneran las clases
		marshaller.setContextPath("localhost.ws");
		return marshaller;
	}

	@Bean
	public PaisSoapClient paisSoapClient(Jaxb2Marshaller marshaller) {
		PaisSoapClient client = new PaisSoapClient();

		client.setDefaultUri("http://localhost:8081/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
