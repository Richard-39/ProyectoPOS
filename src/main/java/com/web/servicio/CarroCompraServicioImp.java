package com.web.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.web.modelo.ItemBoleta;

@Service
public class CarroCompraServicioImp implements ICarroCompra {

	List<ItemBoleta> itemsBoleta = new ArrayList<ItemBoleta>();
	
	@Override
	public void agregarItem(ItemBoleta itemProducto) {
		itemsBoleta.add(itemProducto);
		
	}

	@Override
	public void limpiarCarro() {
		itemsBoleta.clear();
		
	}

	@Override
	public List<ItemBoleta> obtenerItems() {
		
		return itemsBoleta;
	}

}
