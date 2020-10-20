package com.web.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ItemBoletaKey implements Serializable{
	
	@Column(name = "id_boleta")
	private Integer idBoleta;
	@Column(name = "id_producto")
	private Integer idProducto;

}
