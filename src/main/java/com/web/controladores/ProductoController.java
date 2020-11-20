package com.web.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView crearProducto(Model model) {
		ModelAndView modelAndView = new ModelAndView("crearProducto");
		modelAndView.addObject("producto", new Producto());
		return modelAndView;
	}

	@PostMapping("/guardar")
	public RedirectView crearProducto(@ModelAttribute Producto producto) {
		productoDao.save(producto);
		return new RedirectView("/productos/administrar");

	}

	@GetMapping("/administrar")
	public String admiProducto(Model model) {
		ProductoVO productoVo = productoDao.findAll();
		model.addAttribute("productos", productoVo.getProductos());
		model.addAttribute("mensaje", productoVo.getMensaje());
		model.addAttribute("codigo", productoVo.getCodigo());
		return "administrarProductos";
	}

	@GetMapping("/listar")
	public String producto(Model model) {
		model.addAttribute("titulo", "Listado de productos");
		ProductoVO productoVo = productoDao.findAll();
		model.addAttribute("productos", productoVo.getProductos());
		model.addAttribute("mensaje", productoVo.getMensaje());
		model.addAttribute("codigo", productoVo.getCodigo());
		return "producto";
	}

	@PostMapping("/buscar")
	public String buscarProducto(@RequestParam String criterio, Model model) {
		ProductoVO productoVo = null;
		try {
		productoVo = productoDao.findById(Integer.parseInt(criterio));
			
		} catch (Exception e) {
			productoVo = productoDao.findByNombre(criterio);
		}
			model.addAttribute("productos", productoVo.getProductos());
			model.addAttribute("mensaje", productoVo.getMensaje());
			model.addAttribute("codigo", productoVo.getCodigo());
		
			return "administrarProductos";}
	
	@GetMapping("/eliminar")
	public String eliminarProducto (@RequestParam Integer id) {
		ProductoVO productoVo = productoDao.findById(id);
		System.out.println(productoVo.getProductos().get(0));
		productoDao.delete(productoVo.getProductos().get(0));
		
		return "redirect:/productos/administrar";
	}
	

}
