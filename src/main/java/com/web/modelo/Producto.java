package com.web.modelo;

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
public class Producto {

	    @Id
	    @Column(name = "id_producto")
	    private Integer idProducto;
	    private String nombre;
	    private Integer precio;
	    private String descripcion;
	    
	    @OneToMany(fetch = FetchType.EAGER, mappedBy = "producto", cascade = CascadeType.ALL)
	    List<ItemBoleta> itemBoleta = new ArrayList<ItemBoleta>();

		@Override
		public String toString() {
			return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", precio=" + precio + ", descripcion="
					+ descripcion + "]";
		}
	    
	    
	}
	

