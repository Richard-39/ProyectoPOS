package com.web.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.Servicio.ProductoServicio;
import com.web.Servicio.ProductoVO;

@Controller
public class ProductoController {

	@Autowired
	private ProductoServicio productoDao;
	
	@GetMapping("/listarProductos")
	public String producto(Model model){
		model.addAttribute("titulo", "Listado de productos");
		ProductoVO productoVo = productoDao.getAllProductos();
		model.addAttribute("productos", productoVo.getProductos());
		model.addAttribute("mensaje", productoVo.getMensaje());
		model.addAttribute("codigo", productoVo.getCodigo());
		return "producto";
	}
}
