package com.web.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.BoletaDao;
import com.web.modelo.Boleta;
import com.web.vo.BoletaVO;

@Service
public class BoletaServicioImp implements IBoleta{

	@Autowired
	BoletaDao boletaDao;

	private static final Logger log = LoggerFactory.getLogger(BoletaServicioImp.class);

	@Override
	@Transactional
	public BoletaVO findAll() {
		BoletaVO boletaVo = new BoletaVO("Ha ocurrido un error", "101", new ArrayList<Boleta>());
		try {
			boletaVo.setBoletas(boletaDao.findAll());
			boletaVo.setMensaje(String.format("Se han encontrado %d registros.", boletaVo.getBoletas().size()));
			boletaVo.setCodigo("0");
		} catch (Exception e) {
			log.info("Se ha encontrado un error en BoletaServicioImp : findAll " + e);
			}
		return boletaVo;
	}

	@Override
	public BoletaVO findById(Integer id) {
		BoletaVO boletaVo = new BoletaVO("Ha ocurrido un error", "102", new ArrayList<Boleta>());
		try {
			boletaVo.getBoletas().add(boletaDao.findById(id).get());
			boletaVo.setMensaje(String.format("Se han encontrado %d registros.", boletaVo.getBoletas().size()));
			boletaVo.setCodigo("0");
		} catch (Exception e) {
			log.info("Se ha encontrado un error en BoletaServicioImp : findById " + e);
			}
		return boletaVo;
	}

	@Override
	@Transactional
	public BoletaVO save(Boleta boleta) {
		BoletaVO boletaVo = new BoletaVO("Ha ocurrido un error", "103", new ArrayList<Boleta>());
		try {
			boletaVo.getBoletas().add(boletaDao.save(boleta));
			boletaVo.setMensaje("Se ha guardado la Boleta correctamente.");
			boletaVo.setCodigo("0");
		} catch (Exception e) {
			log.info("Se ha encontrado un error en BoletaServicioImp : save " + e);
			}
		return boletaVo;
	}

	@Override
	public BoletaVO update(Boleta boleta) {
		BoletaVO boletaVo = new BoletaVO("Ha ocurrido un error", "104", new ArrayList<Boleta>());
		try {
			boletaVo.getBoletas().add(boletaDao.save(boleta));
			boletaVo.setMensaje("Se ha actualizado la Boleta correctamente.");
			boletaVo.setCodigo("0");
		} catch (Exception e) {
			log.info("Se ha encontrado un error en BoletaServicioImp : update " + e);
			}
		return boletaVo;
	}

	@Override
	public BoletaVO delete(Integer id) {
		BoletaVO boletaVo = new BoletaVO("Ha ocurrido un error", "105", new ArrayList<Boleta>());
		try {
			boletaVo.getBoletas().add(boletaDao.findById(id).get());
			boletaDao.delete(boletaDao.findById(id).get());
			boletaVo.setMensaje("Se ha Eliminado la Boleta correctamente.");
			boletaVo.setCodigo("0");
		} catch (Exception e) {
			log.info("Se ha encontrado un error en BoletaServicioImp : delete " + e);
			}
		return boletaVo;
	}

	@Override
	public BoletaVO findAllByFecha(Date fecha) {
		BoletaVO boletaVo = new BoletaVO("Ha ocurrido un error", "106", new ArrayList<Boleta>());
		try {
			boletaVo.setBoletas(boletaDao.findAllByFecha(fecha));
			boletaVo.setMensaje(String.format("Se han encontrado %d registros", boletaVo.getBoletas().size()));
			boletaVo.setCodigo("0");
		} catch (Exception e) {
			log.info("Se ha encontrado un error en BoletaServicioImp : findAllByFecha " + e);
			}
		return boletaVo;
	}

	@Override
	public BoletaVO findAllByFechaTimeBetween(Date fechaInicial, Date fechaFinal) {
		BoletaVO boletaVo = new BoletaVO("Ha ocurrido un error", "107", new ArrayList<Boleta>());
		try {
			boletaVo.setBoletas(boletaDao.findAllByFechaBetween(fechaInicial, fechaFinal));
			boletaVo.setMensaje(String.format("Se han encontrado %d registros", boletaVo.getBoletas().size()));
			boletaVo.setCodigo("0");
		} catch (Exception e) {
			log.info("Se ha encontrado un error en BoletaServicioImp : findAllByFecha " + e);
			}
		return boletaVo;
	}

	@Override
	public BoletaVO findLastBoleta() {
		BoletaVO boletaVo = new BoletaVO("Ha ocurrido un error", "108", new ArrayList<Boleta>());
		try {
			List<Boleta> lista = boletaDao.findAll();
			boletaVo.getBoletas().add(lista.get(lista.size()-1));
			boletaVo.setMensaje(String.format("Se han encontrado %d registros", boletaVo.getBoletas().size()));
			boletaVo.setCodigo("0");
		} catch (Exception e) {
			log.info("Se ha encontrado un error en BoletaServicioImp : findLastBoleta " + e);
			}
		return boletaVo;
	}



}
