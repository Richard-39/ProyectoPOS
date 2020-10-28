package com.web;

import java.sql.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.web.modelo.Producto;
import com.web.dao.BoletaDao;
import com.web.dao.ProductoDao;
import com.web.modelo.Boleta;
import com.web.modelo.ItemBoleta;
import com.web.modelo.ItemBoletaKey;


@SpringBootApplication
public class ProyectoPosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoPosApplication.class, args);

//		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(AppConfig.class);
//		ProductoRepositorio productoDao = acac.getBean(ProductoRepositorio.class);
//		System.out.println(productoDao.findAll());
//		Producto producto = new Producto();
//		producto.setNombre("ramitas de queso");
//		producto.setPrecio(300);
//		producto.setDescripcion("paquete de 30gr");
//		productoDao.save(producto);
		
//		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(AppConfig.class);
//		BoletaDao boletadao = acac.getBean(BoletaDao.class);
//		ProductoDao productoDao = acac.getBean(ProductoDao.class);
//		
//		System.out.println(productoDao.findAll());
//		System.out.println(boletadao.findAll());
//	
//		Boleta boleta1 = new Boleta();
//		boleta1.setMonto(1222);
//		boleta1.setFecha(new Date(2020, 10, 20));
//		
//		Producto producto1 = new Producto();
//		producto1.setIdProducto(1);
//
//		ItemBoleta itemBoleta1= new ItemBoleta();
//		itemBoleta1.setProducto(producto1);
//		itemBoleta1.setCantidad(1);
//		
//		boleta1.getItemBoleta().add(itemBoleta1);
//		
//		boletadao.save(boleta1);

	}

}
