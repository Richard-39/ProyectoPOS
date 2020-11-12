package com.web.controladores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.modelo.Boleta;
import com.web.modelo.Busqueda;
import com.web.modelo.ItemBoleta;
import com.web.modelo.Producto;
import com.web.servicio.IBoleta;
import com.web.vo.BoletaVO;

@RequestMapping("/informes")
@Controller
@SessionAttributes("busqueda")
public class InformesControlador {

	@Autowired
	IBoleta boletaServicio;
	
	Comparator<Boleta> compareByFecha = (Boleta o1, Boleta o2) -> o1.getFecha().compareTo( o2.getFecha());
	Comparator<Boleta> compareByMonto = (Boleta o1, Boleta o2) -> o1.getMonto().compareTo( o2.getMonto());
	Comparator<Boleta> compareByFormaPago = (Boleta o1, Boleta o2) -> o1.getFormaPago().compareTo( o2.getFormaPago());
	Comparator<Boleta> compareByEstado = (Boleta o1, Boleta o2) -> o1.getEstado().compareTo( o2.getEstado());
	
	@ModelAttribute("busqueda")
	public Busqueda busqueda() {
		Busqueda busquedaInicial = new Busqueda();
		busquedaInicial.setOrderBy("fecha");
		busquedaInicial.setOrderMode("ascendente");
		busquedaInicial.setTiempo("diaActual");
	    return busquedaInicial;
	}
	
	@GetMapping({"/", ""})
	public String informes(RedirectAttributes redirectModel, @ModelAttribute("mensaje") String mensaje){
		redirectModel.addAttribute("tiempo", "diaActual");
		return "redirect:/informes/busqueda";
	}
	
	@GetMapping({"/verInforme"})
	public String verInforme(Model model, @ModelAttribute("busqueda") Busqueda busqueda) throws ParseException {
							
		List<Boleta> boletas = boletaServicio.findAllByFechaTimeBetween(
				busqueda.getFechaInicio(), 
				busqueda.getFechaTermino()
				)
				.getBoletas();
		
		Integer total = calcularTotal(boletas);
		
		LinkedHashMap<Producto, Integer> productos =  ordenarProductos(boletas);
		
		ordenarBoletas(busqueda.getOrderBy(), busqueda.getOrderMode(), boletas);
		
		model.addAttribute("boletas", boletas);
		model.addAttribute("productos", productos);
		model.addAttribute("total", total);
//		model.addAttribute("busqueda", new Busqueda()); // solo si queremos vaciar el atributo de la session
			
		return "informes";
	}
	
	@GetMapping("/busqueda")
	public String busqueda(
			Busqueda nuevaBusqueda, 
			@RequestParam(required = false) String tiempo,
			Model model) {
		
		System.out.println("Busqueda actual: " + nuevaBusqueda);

		if (tiempo != null && tiempo.equals("diaActual")) {
			nuevaBusqueda.setFechaInicio(new Date());
			nuevaBusqueda.setFechaTermino(new Date());
		} else if (tiempo != null && tiempo.equals("mesActual")) {
			Calendar fecha = Calendar.getInstance();
			YearMonth month = YearMonth.of(2020, fecha.get(Calendar.MONTH) + 1);
			
			LocalDate start = month.atDay(1);
			LocalDate stop = start.plusMonths(1);
			
			ZoneId defaultZoneId = ZoneId.systemDefault();
			
			nuevaBusqueda.setFechaInicio(Date.from(start.atStartOfDay(defaultZoneId).toInstant()));
			nuevaBusqueda.setFechaTermino(Date.from(stop.atStartOfDay(defaultZoneId).toInstant()));
		}
				
		model.addAttribute("busqueda", nuevaBusqueda);
		System.out.println("Busqueda nueva: " + nuevaBusqueda);
		return "redirect:/informes/verInforme";
	}
	
	@GetMapping("/boleta")
	public String verBoleta(Model model, @RequestParam Integer idBoleta, RedirectAttributes redirectModel) {
		BoletaVO boleta = boletaServicio.findById(idBoleta);
		if(boleta.getBoletas().size() > 0) {
			model.addAttribute("boleta", boleta.getBoletas().get(0));
			model.addAttribute("mensaje", boleta.getMensaje());
			model.addAttribute("codigo", boleta.getCodigo());
			return "verBoleta";
		} else {
			redirectModel.addFlashAttribute("mensaje", "Boleta ya ha sido eliminada.");
			return "redirect:/informes";
		}
	}
	
	@GetMapping("/anular")
	public String elimiarRegistro(Model model, @RequestParam Integer idBoleta, RedirectAttributes redirectModel) {
		BoletaVO boletaVO = boletaServicio.findById(idBoleta);

		boletaVO.getBoletas().get(0).setEstado("anulada");
		
		boletaServicio.save(boletaVO.getBoletas().get(0));
		
		model.addAttribute("boleta",boletaVO.getBoletas().get(0));
		model.addAttribute("titulo", "Anulación exitosa !");
		model.addAttribute("mensaje", "Se ha anulado con éxito");
		model.addAttribute("detalle", String.format("Boleta número %d, fecha %s.",
				boletaVO.getBoletas().get(0).getIdBoleta(),
				boletaVO.getBoletas().get(0).getFecha().toString()
				));
		return "confirmacion";
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
		} else if (orderBy.equals("formaPago")) {
			if (orderMode.equals("ascendente")) {
				Collections.sort(boletas, compareByFormaPago);
			} else if (orderMode.equals("descendente")){
				Collections.sort(boletas, compareByFormaPago.reversed());
			}
		} else if (orderBy.equals("estado")) {
			if (orderMode.equals("ascendente")) {
				Collections.sort(boletas, compareByEstado);
			} else if (orderMode.equals("descendente")){
				Collections.sort(boletas, compareByEstado.reversed());
			}
		}
	}
		
	private LinkedHashMap<Producto, Integer> ordenarProductos(List<Boleta> boletas){
		
		Map<Producto, Integer> productoCantidad = new HashMap<Producto, Integer>();
		for (Boleta boleta : boletas) {
			if (!boleta.getEstado().equals("anulada")) {
				for (ItemBoleta itemBoleta : boleta.getItemBoleta()) {
					if(productoCantidad.containsKey(itemBoleta.getProducto())){
						productoCantidad.put(itemBoleta.getProducto(), productoCantidad.get(itemBoleta.getProducto()) + itemBoleta.getCantidad()) ;
					} else {
						productoCantidad.put(itemBoleta.getProducto(), itemBoleta.getCantidad());
					}
				}
			}
		}
		
		LinkedHashMap<Producto, Integer> sortedMap = 
				productoCantidad.entrySet().stream().
			    sorted(Entry.<Producto, Integer>comparingByValue().reversed()).
			    collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                             (e1, e2) -> e1, LinkedHashMap::new));
		
		return sortedMap;
	}
	
	private Integer calcularTotal(List<Boleta> boletas) {
		Integer total = 0;
		for (Boleta boleta : boletas) {
			if(!boleta.getEstado().equals("anulada")) {
				total += Boleta.calcularMonto(boleta.getItemBoleta());
			}
		}
		return total;
	}
	
}
