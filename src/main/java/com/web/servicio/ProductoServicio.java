package com.web.servicio;

import com.web.modelo.Producto;

public interface ProductoServicio {

	public ProductoVO getAllProductos();
	public ProductoVO findByNombre(String nombre);
	public ProductoVO add(Producto producto);
	public ProductoVO update(Producto producto);
	public ProductoVO delete(Producto producto);
	public ProductoVO findById(Integer id);
}
