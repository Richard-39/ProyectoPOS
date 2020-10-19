package com.web.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.web.modelo.Producto;

@Repository
public interface ProductoRepositorio extends CrudRepository<Producto, Integer> {

}
