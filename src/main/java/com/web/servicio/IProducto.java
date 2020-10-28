package com.web.servicio;

import com.web.modelo.Producto;
import com.web.vo.ProductoVO;

public interface IProducto {

	public ProductoVO findAll();
	public ProductoVO findByNombre(String nombre);
	public ProductoVO save(Producto producto);
	public ProductoVO update(Producto producto);
	public ProductoVO delete(Producto producto);
	public ProductoVO findById(Integer id);
	
}
