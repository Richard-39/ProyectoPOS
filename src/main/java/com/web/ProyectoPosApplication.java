package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.web.modelo.Producto;
import com.web.repositorio.ProductoRepositorio;

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
	}

}
