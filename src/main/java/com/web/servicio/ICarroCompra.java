package com.web.servicio;

import java.util.List;

import com.web.modelo.ItemBoleta;


public interface ICarroCompra {

	public void agregarItem(ItemBoleta itemProducto);
	
	public void limpiarCarro();
	
	public List<ItemBoleta> obtenerItems();
	
	public void eliminarProducto(Integer idProducto);
	
}
