package com.web.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.ManyToAny;

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
	

