package com.web.controladores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.modelo.Boleta;
import com.web.servicio.IBoleta;

@RequestMapping("/informes")
@Controller
public class InformesControlador {

	@Autowired
	IBoleta boletaServicio;
	
	Comparator<Boleta> compareByFecha = (Boleta o1, Boleta o2) -> o1.getFecha().compareTo( o2.getFecha());
	Comparator<Boleta> compareByMonto = (Boleta o1, Boleta o2) -> o1.getMonto().compareTo( o2.getMonto());
	
	@GetMapping({"/ventaPorDia", "/", ""})
	public String ventaPorDia(Model model, @RequestParam(required = false) String fecha, @RequestParam(required = false) String orderBy) throws ParseException {
		if (fecha == null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			fecha = dateFormat.format(Calendar.getInstance().getTime());
		}
		
		if (orderBy == null) {
			orderBy = "fecha";
		}
				
//		List<Boleta> boletas = boletaServicio.findAllByFecha(new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-05")).getBoletas();
		List<Boleta> boletas = boletaServicio.findAllByFecha(new SimpleDateFormat("yyyy-MM-dd").parse(fecha)).getBoletas();
		
		
		if (orderBy.equals("fecha")) {
			Collections.sort(boletas, compareByFecha.reversed());
		} else if (orderBy.equals("monto")) {
			Collections.sort(boletas, compareByMonto.reversed());
		}
				
		model.addAttribute("boletas", boletas);
		model.addAttribute("tipoBusqueda", "Día");
		
		return "informes";
	}
	
	@GetMapping({"/ventaPorMes"})
	public String ventaPorMes(Model model, @RequestParam Integer ano, @RequestParam Integer mes, @RequestParam String orderBy, @RequestParam String orderMode) throws ParseException {
				
		YearMonth month = YearMonth.of(2020, mes);
		LocalDate start = month.atDay(1);
		LocalDate stop = start.plusMonths(1);
		
		List<Boleta> boletas = boletaServicio.findAllByFechaTimeBetween(
				(new SimpleDateFormat("yyyy-MM-dd").parse(start.toString())), 
				(new SimpleDateFormat("yyyy-MM-dd").parse(stop.toString()))).getBoletas();
		
		if (orderBy.equals("fecha")) {
			if (orderMode.equals("ascendente")) {
				Collections.sort(boletas, compareByFecha);
			} else if (orderMode.equals("descendente")){
				Collections.sort(boletas, compareByFecha.reversed());
			}
		} else if (orderBy.equals("monto")) {
			if (orderMode.equals("ascendente")) {
				Collections.sort(boletas, compareByMonto);
			} else if (orderMode.equals("descendente")){
				Collections.sort(boletas, compareByMonto.reversed());
			}
		}
		
		model.addAttribute("boletas", boletas);
		model.addAttribute("tipoBusqueda", "Mes");
		
		return "informes";
	}
	
	@GetMapping({"/ventaPorPeriodo"})
	public String ventaPorPeriodo(Model model, @RequestParam String periodoDateStart, @RequestParam String periodoDateEnd, @RequestParam String orderBy, @RequestParam String orderMode) throws ParseException {
			
		List<Boleta> boletas = boletaServicio.findAllByFechaTimeBetween(
				(new SimpleDateFormat("yyyy-MM-dd").parse(periodoDateStart)), 
				(new SimpleDateFormat("yyyy-MM-dd").parse(periodoDateEnd)))
				.getBoletas();
		
		ordenarBoletas(orderBy, orderMode, boletas);
		
		model.addAttribute("boletas", boletas);
		model.addAttribute("tipoBusqueda", "Periodo");
		
		return "informes";
	}
	
	private void ordenarBoletas(String orderBy, String orderMode, List<Boleta> boletas) {
		if (orderBy.equals("fecha")) {
			if (orderMode.equals("ascendente")) {
				Collections.sort(boletas, compareByFecha);
			} else if (orderMode.equals("descendente")){
				Collections.sort(boletas, compareByFecha.reversed());
			}
		} else if (orderBy.equals("monto")) {
			if (orderMode.equals("ascendente")) {
				Collections.sort(boletas, compareByMonto);
			} else if (orderMode.equals("descendente")){
				Collections.sort(boletas, compareByMonto.reversed());
			}
		}
	}
	
}
