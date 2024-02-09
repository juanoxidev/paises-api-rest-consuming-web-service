package com.paisesapirestconsumingwebservice.model;

import java.util.Map;

import localhost.ws.Moneda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaisDTO {
	private String nombre;
	private String capital;
	private Moneda moneda;
	private int poblacion;
	private Map<String, String> lenguajes;
	private Map<String, String> mapas;
}
