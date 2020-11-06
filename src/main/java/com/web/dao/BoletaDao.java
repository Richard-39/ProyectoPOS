package com.web.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.modelo.Boleta;

public interface BoletaDao extends JpaRepository<Boleta, Integer>{
	
	List<Boleta> findAllByFecha(Date fecha);

	List<Boleta> findAllByFechaBetween(Date fechaTimeStart, Date fechaTimeEnd);
	
}