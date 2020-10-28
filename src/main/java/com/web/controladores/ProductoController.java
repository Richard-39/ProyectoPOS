package com.web.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.servicio.IProducto;
import com.web.vo.ProductoVO;

@Controller
public class ProductoController {

	@Autowired
	private IProducto productoDao;
	
	@GetMapping("/listarProductos")
	public String producto(Model model){
		model.addAttribute("titulo", "Listado de productos");
		ProductoVO productoVo = productoDao.findAll();
		model.addAttribute("productos", productoVo.getProductos());
		model.addAttribute("mensaje", productoVo.getMensaje());
		model.addAttribute("codigo", productoVo.getCodigo());
		return "producto";
	}
}
