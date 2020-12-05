package com.web.servicio;

import java.util.Date;
import java.util.List;

import com.web.modelo.Boleta;
import com.web.vo.BoletaVO;

public interface IBoleta {

	public BoletaVO findAll();
	public BoletaVO findById(Integer id);
	public BoletaVO save(Boleta boleta);
	public BoletaVO update(Boleta boleta);
	public BoletaVO delete(Integer id);
	public BoletaVO findAllByFecha(Date fecha);
	public BoletaVO findAllByFechaTimeBetween(Date fechaInicial, Date fechaFinal);
	public BoletaVO findLastBoleta();
}
