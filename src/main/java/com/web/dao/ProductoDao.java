package com.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.modelo.Producto;

public interface ProductoDao extends JpaRepository<Producto, Integer>{

	public List<Producto> findByNombreLike(String nombre);
}
