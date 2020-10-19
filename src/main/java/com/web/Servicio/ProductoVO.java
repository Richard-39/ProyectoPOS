package com.web.Servicio;

import java.util.List;

import com.web.modelo.Producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductoVO extends GenericoVO{

	List <Producto> productos;

	public ProductoVO(String mensaje, String codigo, List <Producto> productos) {
		super(mensaje, codigo);
		this.productos = productos;
		// TODO Auto-generated constructor stub
	}
	
}
