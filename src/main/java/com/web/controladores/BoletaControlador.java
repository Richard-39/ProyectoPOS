package com.web.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.servicio.IBoleta;
import com.web.vo.BoletaVO;

@Controller
@RequestMapping("/boletas")
public class BoletaControlador {
	
	@Autowired
	IBoleta boletaServicio;
	
	@GetMapping("/listar")
	public String listarBoletas(Model model) {
		BoletaVO boletaVO = boletaServicio.findAll();
		model.addAttribute("listaBoletas", boletaVO.getBoletas());
		model.addAttribute("mensaje", boletaVO.getMensaje());
		model.addAttribute("codigo", boletaVO.getCodigo());
		return "listarBoletas";
	}

}
