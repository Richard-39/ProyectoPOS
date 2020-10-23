package com.web.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.Servicio.ProductoServicio;

@Controller
public class ProductoController {

	@Autowired
	private ProductoServicio productoDao;
	
	@GetMapping("/listarProductos")
	public String producto(Model model){
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productoDao.getAllProductos());
		return "productos";
	}
}
