package com.web.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
public class ItemBoletaKey implements Serializable{
	
	@Column(name = "id_boleta")
	private Integer idBoleta;
	@Column(name = "id_producto")
	private Integer idProducto;
	
	
	
	public ItemBoletaKey() {
		super();
	}

	public ItemBoletaKey(Integer idBoleta, Integer idProducto) {
		super();
		this.idBoleta = idBoleta;
		this.idProducto = idProducto;
	}
	
	public Integer getIdBoleta() {
		return idBoleta;
	}
	public void setIdBoleta(Integer idBoleta) {
		this.idBoleta = idBoleta;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBoleta == null) ? 0 : idBoleta.hashCode());
		result = prime * result + ((idProducto == null) ? 0 : idProducto.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemBoletaKey other = (ItemBoletaKey) obj;
		if (idBoleta == null) {
			if (other.idBoleta != null)
				return false;
		} else if (!idBoleta.equals(other.idBoleta))
			return false;
		if (idProducto == null) {
			if (other.idProducto != null)
				return false;
		} else if (!idProducto.equals(other.idProducto))
			return false;
		return true;
	}

	
	
}
