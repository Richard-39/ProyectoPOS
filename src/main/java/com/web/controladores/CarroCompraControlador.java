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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.web.dao.BoletaDao;
import com.web.modelo.Boleta;
import com.web.modelo.ItemBoleta;
import com.web.modelo.Producto;
import com.web.servicio.IBoleta;
import com.web.servicio.IProducto;

@Controller
@RequestMapping("/carroCompra")
public class CarroCompraControlador {
	
	@Autowired
	IBoleta boletaServicio;
	
	@Autowired
	IProducto productoServicio;
	
	@GetMapping({"/", ""})
	public String carroCompra(Model model) {
		
		ItemBoleta itemBoleta1 = new ItemBoleta(new Producto(1, "Cocaloca 1 LT retornable", 1990, "Bebida de fantasía"), 2);
		ItemBoleta itemBoleta2= new ItemBoleta(new Producto(2, "Galletas triton 10 un.", 990, "Galleta sabor chocolate"), 1);
		ItemBoleta itemBoleta3 = new ItemBoleta(new Producto(3, "Chocolate trensito 300 g", 1490, "Chocolate sucedaneo a base de manteca"), 3);
		ItemBoleta itemBoleta4 = new ItemBoleta(new Producto(4, "Pan ideal 20 un.", 2390, "Bolsa de pan de molde"), 1);
		
		List<ItemBoleta> listaItemBoleta = new ArrayList<ItemBoleta>(Arrays.asList(itemBoleta1, itemBoleta2, itemBoleta3, itemBoleta4));
		
		Boleta boleta = new Boleta();
		boleta.setItemBoleta(listaItemBoleta);
		
		model.addAttribute("boleta", boleta);
		model.addAttribute("total", Boleta.calcularMonto(listaItemBoleta));
		
		return "carroCompra";
	} 
	
	@PostMapping("/resumenPago")
	public String saveBooks(@ModelAttribute Boleta boleta, Model model) {
		for (ItemBoleta iterable_element : boleta.getItemBoleta()) {
			System.out.println(iterable_element);
		}
		
		for (ItemBoleta item : boleta.getItemBoleta()) {
			item.setProducto(productoServicio.findById(item.getProducto().getIdProducto()).getProductos().get(0));
		}
		
		boleta.setFecha(new Date());
		boleta.setMonto(Boleta.calcularMonto(boleta.getItemBoleta()));
		boletaServicio.save(boleta);
		
		model.addAttribute("boleta", boleta);
		
	    return "resumenPago";
	}
}
