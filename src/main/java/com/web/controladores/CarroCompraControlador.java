package com.web.controladores;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@PostConstruct
	public void prductoInicialCarro() {
		carroCompraServicio.agregarItem(new ItemBoleta(productoServicio.findById(1).getProductos().get(0), 2));
		carroCompraServicio.agregarItem(new ItemBoleta(productoServicio.findById(2).getProductos().get(0), 3));
		carroCompraServicio.agregarItem(new ItemBoleta(productoServicio.findById(3).getProductos().get(0), 1));
		carroCompraServicio.agregarItem(new ItemBoleta(productoServicio.findById(4).getProductos().get(0), 5));
	}
	
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
	
	@GetMapping({"/", ""})
	public String carroCompra(Model model) {
			
		Boleta boleta = new Boleta();
		boleta.setItemBoleta(carroCompraServicio.obtenerItems());
		
		model.addAttribute("boleta", boleta);
		model.addAttribute("total", Boleta.calcularMonto(carroCompraServicio.obtenerItems()));
		
		return "carroCompra";
	} 

	@GetMapping("/eliminarProducto")
	public String eliminarProducto(@RequestParam Integer idProducto) {
		carroCompraServicio.eliminarProducto(idProducto);
		return "forward:/carroCompra/";
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
		boletaServicio.save(boleta);
		model.addAttribute("boleta", boleta);
		model.addAttribute("montoPago", montoPago);
		model.addAttribute("vuelto", montoPago - boleta.getMonto());
		
		carroCompraServicio.limpiarCarro();
		
	    return "resumenPago";
	}
}
