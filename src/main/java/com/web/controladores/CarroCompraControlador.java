package com.web.controladores;

import java.util.Date;

import javax.annotation.PostConstruct;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.web.modelo.Boleta;
import com.web.modelo.ItemBoleta;
import com.web.modelo.Producto;
import com.web.servicio.IBoleta;
import com.web.servicio.ICarroCompra;
import com.web.servicio.IProducto;
import com.web.vo.BoletaVO;
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
	public String agregarCarro(@RequestParam Integer idProducto, @RequestParam Integer cantidad, Model model) {
		ProductoVO productoVo = productoServicio.findById(idProducto);
		Producto producto = productoVo.getProductos().get(0);
		ItemBoleta itemBoleta = new ItemBoleta();
		itemBoleta.setProducto(producto);
		itemBoleta.setCantidad(cantidad);
		carroCompraServicio.agregarItem(itemBoleta);
		System.out.println(carroCompraServicio.obtenerItems());

		return "forward:/productos/listar";
	}

	@GetMapping({ "/", "" })
	public String carroCompra(Model model) {

		Boleta boleta = new Boleta();
		boleta.setItemBoleta(carroCompraServicio.obtenerItems());

		if (boleta.getItemBoleta().size() <= 0) {
			model.addAttribute("mensaje", "El carro de compra está vacio.");
			return "forward:/productos/listar";
		}

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
	public ModelAndView resumenPago(@ModelAttribute Boleta boleta, RedirectAttributes model,
			@RequestParam(required = false, defaultValue = "0") Integer montoPago) {
//		for (ItemBoleta iterable_element : boleta.getItemBoleta()) {
//			System.out.println(iterable_element);
//		}
//		
//		System.out.println("monto pago: " + montoPago);
//		System.out.println("forma pago: " + boleta.getFormaPago());

		// Asigna a cada item boleta el producto correspondiente

		if (boleta.getItemBoleta().size() <= 0 || carroCompraServicio.obtenerItems().size() <= 0) {
			model.addAttribute("mensaje", "El carro de compra está vacio.");
			return new ModelAndView("redirect:/listarProductos");
		}

		for (ItemBoleta item : boleta.getItemBoleta()) {
			item.setProducto(productoServicio.findById(item.getProducto().getIdProducto()).getProductos().get(0));
		}

		boleta.setFecha(new Date());
		boleta.setEstado("emitida");
		boleta.setMonto(Boleta.calcularMonto(boleta.getItemBoleta()));

		if (boleta.getFormaPago().equals("efectivo")) {

			if (montoPago - boleta.getMonto() < 0) {
				model.addAttribute("mensaje", "Monto Pago no puede ser menor que el total de la Boleta.");
				return new ModelAndView("redirect:/carroCompra/");
			}
			model.addFlashAttribute("montoPago", montoPago);
			model.addFlashAttribute("vuelto", montoPago - boleta.getMonto());

		} else {
			model.addFlashAttribute("montoPago", 0);
			model.addFlashAttribute("vuelto", 0);
		}

		model.addFlashAttribute("boleta", boleta);
		boletaServicio.save(boleta);
		carroCompraServicio.limpiarCarro();

		System.out.println("Boleta vendida !");

		ModelAndView mav = new ModelAndView("redirect:/resumenPagoView");

		return new ModelAndView("redirect:/carroCompra/resumenPagoView");
	}

	@GetMapping("/resumenPagoView")
	public ModelAndView resumenPagoView(@ModelAttribute("boleta") Boleta boleta, ModelMap model) {

		if (boleta.getItemBoleta().size() <= 0) {
			return new ModelAndView("redirect:/listarProductos");
		}

		model.addAttribute("boleta", boleta);
		return new ModelAndView("resumenPago", model);
	}

	@GetMapping("/vaciar")
	public String vaciarCarro() {
		carroCompraServicio.limpiarCarro();
		return "forward:/carroCompra/";

	}
}
