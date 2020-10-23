package com.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.modelo.Producto;

public interface ProductoDao extends JpaRepository<Producto, Integer>{

}
