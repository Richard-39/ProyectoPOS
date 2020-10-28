package com.web.servicio;

import com.web.modelo.Producto;
import com.web.vo.ProductoVO;

public interface IProducto {

	public ProductoVO findAll();
	public ProductoVO findById(Integer id);
	public ProductoVO save(Producto producto);
	public ProductoVO delete(Integer id);
	
	
}
