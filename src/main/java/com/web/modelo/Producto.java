package com.web.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="producto")
public class Producto implements Serializable {

		@Id
	    @Column(name = "id_producto")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer idProducto;
	    private String nombre;
	    private Integer precio;
	    private String descripcion;

		private static final long serialVersionUID = 1L;
	}
