package com.web.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.modelo.Boleta;
import com.web.modelo.ItemBoleta;
import com.web.modelo.Producto;

@Controller
@RequestMapping("/carroCompra")
public class CarroCompraControlador {
	
	@GetMapping({"/", ""})
	public String carroCompra(Model model) {
		
//		ItemBoleta itemBoleta1 = new ItemBoleta(new Producto(1, "Cocaloca 1 LT retornable", 1990, "Bebida de fantasía"), 2);
//		ItemBoleta itemBoleta2= new ItemBoleta(new Producto(2, "Galletas triton 10 un.", 990, "Galleta sabor chocolate"), 1);
//		ItemBoleta itemBoleta3 = new ItemBoleta(new Producto(3, "Chocolate trensito 300 g", 1490, "Chocolate sucedaneo a base de manteca"), 3);
//		ItemBoleta itemBoleta4 = new ItemBoleta(new Producto(4, "Pan ideal 20 un.", 2390, "Bolsa de pan de molde"), 1);
		
//		List<ItemBoleta> listaItemBoleta = new ArrayList<ItemBoleta>(Arrays.asList(itemBoleta1, itemBoleta2, itemBoleta3, itemBoleta4));
//		
//		model.addAttribute("listaItemBoleta", listaItemBoleta);
//		model.addAttribute("total", Boleta.calcularMonto(listaItemBoleta));
		
		return "carroCompra";
	} 
	
	@PostMapping("/resumenPago")
	public String resumenPago(@RequestParam String formaPago, @RequestParam String efectivoRecibido, @RequestParam String total, Model model) {
		
		model.addAttribute("formaPago", formaPago);
		model.addAttribute("efectivoRecibido", efectivoRecibido);
		model.addAttribute("total", total);
		
		return "resumenPago";
	}
}
