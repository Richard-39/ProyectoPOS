package com.web.servicio;

import java.util.ArrayList;

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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoletaVO delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


}