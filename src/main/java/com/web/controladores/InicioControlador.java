package com.web.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioControlador {

	@GetMapping({"/", "/inicio", ""})
	public String inicio() {
		return "redirect:/productos/listar";
	}
	
}
