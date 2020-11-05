package com.web.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/informes")
public class InformesControlador {

	@GetMapping({"/", ""})
	public String informes() {
		return "informes";
	}
	
	
}
