package com.web.vo;

import java.util.List;

import com.web.modelo.Boleta;

import lombok.Data;

@Data
public class BoletaVO extends GenericVO {

	private List<Boleta> boletas;

	public BoletaVO(String mensaje, String codigo, List<Boleta> boletas) {
		super(mensaje, codigo);
		this.boletas = boletas;
	}

	public BoletaVO() {
	}
	
	
	
}
