package com.web.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
		
		@NotEmpty
		@NotBlank
		@Size(min = 3, max = 20)
	    private String nombre;
		
	    private Integer precio;
		
		@NotEmpty
	    private String descripcion;
		
		@NotEmpty
	    private String urlImagen;
		
	    private Boolean disponibilidad;

		private static final long serialVersionUID = 1L;
	}
