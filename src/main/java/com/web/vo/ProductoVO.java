package com.web.vo;

import java.util.List;

import com.web.modelo.Producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductoVO extends GenericVO {

	private List<Producto> productos;

	public ProductoVO(String mensaje, String codigo, List<Producto> productos) {
		super(mensaje, codigo);
		this.productos = productos;
	}
	
	
	
}
