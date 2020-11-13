package com.web.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.web.modelo.Producto;
import com.web.servicio.IProducto;
import com.web.vo.ProductoVO;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProducto productoDao;
	
	@GetMapping("/crear")
	public ModelAndView crearProducto (Model model) {
		ModelAndView modelAndView = new ModelAndView ("crearProducto");
		modelAndView.addObject("producto", new Producto());
		return modelAndView;
	}
	
	@PostMapping("/crear")
	public RedirectView crearProducto (@ModelAttribute Producto producto) {
		productoDao.save(producto);
		return new RedirectView("/productos/listar");
		
	}
	@GetMapping("/administrar")
	public String admiProducto(Model model){
		ProductoVO productoVo = productoDao.findAll();
		model.addAttribute("productos", productoVo.getProductos());
		model.addAttribute("mensaje", productoVo.getMensaje());
		model.addAttribute("codigo", productoVo.getCodigo());
		return "administrarProductos";
	}
	
	
	@GetMapping("/listar")
	public String producto(Model model){
		model.addAttribute("titulo", "Listado de productos");
		ProductoVO productoVo = productoDao.findAll();
		model.addAttribute("productos", productoVo.getProductos());
		model.addAttribute("mensaje", productoVo.getMensaje());
		model.addAttribute("codigo", productoVo.getCodigo());
		return "producto";
	}
}
