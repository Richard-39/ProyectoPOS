package com.web.servicio;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.ProductoDao;
import com.web.modelo.Producto;
import com.web.vo.ProductoVO;

@Service
public class ProductoServicioImp implements IProducto {

	@Autowired
	ProductoDao productoDao;
	
	private static final Logger log = LoggerFactory.getLogger(BoletaServicioImp.class);
	
	@Override
	public ProductoVO findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductoVO findById(Integer id) {
		ProductoVO productoVO = new ProductoVO("Ha ocurrido un error", "101", new ArrayList<Producto>());
		try {
			productoVO.getProductos().add(productoDao.findById(id).get());
			productoVO.setMensaje("Se ha encontrado el producto.");
			productoVO.setCodigo("0");
		} catch (Exception e) {
			log.info("Se ha encontrado un error en ProductoServicioImp : findById " + e);
		}
		return productoVO;
	}

	@Override
	public ProductoVO save(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductoVO delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
