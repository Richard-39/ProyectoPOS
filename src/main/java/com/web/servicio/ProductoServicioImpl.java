package com.web.servicio;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.ProductoDao;
import com.web.modelo.Producto;
import com.web.vo.ProductoVO;

@Service
public class ProductoServicioImpl implements IProducto {

	private static final Logger log = LoggerFactory.getLogger(ProductoServicioImpl.class);

	@Autowired
	ProductoDao dao;
	
	ProductoVO respuesta;
	
	@Override
	public ProductoVO findAll() {
		respuesta = new ProductoVO("Ha ocurrido un error", "101", new ArrayList<Producto>());
		try {
			respuesta.setProductos((List<Producto>) dao.findAll());
			respuesta.setMensaje(String.format("Se ha/n encontrado %d registro/s", respuesta.getProductos().size()));
			respuesta.setCodigo("0");
		} catch (Exception e) {
			log.trace("Usuario Service: Error en getAllProductos", e);
		}
		return respuesta;
	}


	@Override
	public ProductoVO findByNombre(String nombre) {
		respuesta = new ProductoVO("Ha ocurrido un error", "102", new ArrayList<Producto>());
		try {
			List<Producto> productos = dao.findByNombreLike(nombre);
			if (productos.size() > 0) {
				respuesta.setProductos(productos);
				respuesta.setMensaje("Producto encontrado correctamente.");
				respuesta.setCodigo("0");
			} else {
				respuesta.setMensaje("Producto no encontrado.");
			}
		} catch (Exception e) {
			log.trace("Producto Service: Error en findByNombre", e);
		}
		return respuesta;
	}

	@Override
	public ProductoVO save(Producto producto) {
		respuesta = new ProductoVO ( "Ha ocurrido un error", "103", new ArrayList<Producto>());
		try {
			dao.save(producto);
			respuesta.setMensaje("El producto ha sido guardado exitosamente");
			respuesta.setCodigo("0");
		}catch (Exception e){
			log.trace("Producto servicio: ha ocurrido un error en add", e);
		}
		return respuesta;
	}

	@Override
	public ProductoVO update(Producto producto) {
		respuesta = new ProductoVO("Ha ocurrido un error", "104", new ArrayList<Producto>());
		try {
			dao.save(producto);
			respuesta.setMensaje("El producto ha sido actualizado exitosamente");
			respuesta.setCodigo("0");
		}catch (Exception e){
			log.trace("Producto servicio: ha ocurrido un error en update", e);
		}
		return respuesta;
	}

	@Override
	public ProductoVO delete(Producto producto) {
		respuesta = new ProductoVO ("Ha ocurrido un error", "105", new ArrayList<Producto>());
		try {
			dao.delete(producto);
			respuesta.setMensaje("El producto ha sido eliminado exitosamente");
			respuesta.setCodigo("0");
		}catch (Exception e){
			log.trace("Producto servicio: ha ocurrido un error en delete", e);
		}
		return respuesta;
	}

	@Override
	public ProductoVO findById(Integer id) {
		respuesta = new ProductoVO ("Ha ocurrido un error", "106", new ArrayList<Producto>());
		try {
			Producto producto = dao.findById(id).get();
			respuesta.getProductos().add(producto);
			respuesta.setMensaje("El producto ha sido encontrado exitosamente");
			respuesta.setCodigo("0");
		}catch (Exception e){
			log.trace("Producto servicio: ha ocurrido un error en findById", e);
		}
		return respuesta;
	}
}
