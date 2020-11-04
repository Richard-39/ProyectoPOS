package com.web.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.web.dao.BoletaDao;
import com.web.modelo.Boleta;
import com.web.modelo.ItemBoleta;
import com.web.modelo.Producto;
import com.web.servicio.IBoleta;
import com.web.servicio.ICarroCompra;
import com.web.servicio.IProducto;
import com.web.vo.ProductoVO;

@Controller
@RequestMapping("/carroCompra")
public class CarroCompraControlador {
	
	@Autowired
	IBoleta boletaServicio;
	
	@Autowired
	IProducto productoServicio;
	
	@Autowired
	ICarroCompra carroCompraServicio;
	
	
	@GetMapping("/agregarCarro")
	public String agregarCarro(@RequestParam Integer idProducto,@RequestParam Integer cantidad, Model model ) {
		ProductoVO  productoVo = productoServicio.findById(idProducto);
		Producto producto = productoVo.getProductos().get(0);
		ItemBoleta itemBoleta = new ItemBoleta();
		itemBoleta.setProducto(producto);
		itemBoleta.setCantidad(cantidad);
		carroCompraServicio.agregarItem(itemBoleta);
		System.out.println(carroCompraServicio.obtenerItems());
	
	return "forward:/listarProductos";
	}
	
	@GetMapping("/")
	public String carroCompra(Model model) {
		
		Boleta boleta = new Boleta();
		boleta.setItemBoleta(carroCompraServicio.obtenerItems());
		
		model.addAttribute("boleta", boleta);
		model.addAttribute("total", Boleta.calcularMonto(carroCompraServicio.obtenerItems()));
		
		return "carroCompra";
	} 
	
	@GetMapping("/agregarProducto")
	public String agregarProducto(Model model, @PathVariable Integer id) {
		
		// toma el id y lo busca en la base de datos y trae el producto que corresponde
		// toma el producto y lo guarda en una lista de producto (item producto)
		// se regresa a la lista de productos
		
		return "redirect: /listarProductos";
	} 
	
	@PostMapping("/resumenPago")
	public String saveBooks(@ModelAttribute Boleta boleta, Model model, @RequestParam Integer montoPago) {
		for (ItemBoleta iterable_element : boleta.getItemBoleta()) {
			System.out.println(iterable_element);
		}
		
		System.out.println("monto pago: " + montoPago);
		System.out.println("forma pago: " + boleta.getFormaPago());
		
		for (ItemBoleta item : boleta.getItemBoleta()) {
			item.setProducto(productoServicio.findById(item.getProducto().getIdProducto()).getProductos().get(0));
		}
		
		boleta.setFecha(new Date());
		boleta.setMonto(Boleta.calcularMonto(boleta.getItemBoleta()));
//		boletaServicio.save(boleta);
		
		model.addAttribute("boleta", boleta);
		
	    return "resumenPago";
	}
}
