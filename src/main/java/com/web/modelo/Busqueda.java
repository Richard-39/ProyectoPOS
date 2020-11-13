package com.web.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Busqueda {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaTermino;
	
	private String orderBy;
	
	private String orderMode;
	
	private String tiempo;
	
}
