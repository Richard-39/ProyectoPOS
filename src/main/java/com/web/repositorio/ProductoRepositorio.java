package com.web.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.web.modelo.Producto;

@Repository
public interface ProductoRepositorio extends CrudRepository<Producto, Integer> {

	@Query("SELECT * FROM productos WHERE nombre LIKE '%nombre%'" )
	public List<Producto> findByNombre(String nombre);
}
