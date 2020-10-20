package com.web.modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="boleta")
public class Boleta {

	@Id
	private Integer idBoleta; 
    private Date fecha;
    private Integer monto;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "boleta", cascade = CascadeType.ALL)
    List<ItemBoleta> itemBoleta = new ArrayList<ItemBoleta>();
	
    public static Integer calcularMonto(List<ItemBoleta> listaItemBoleta) {
    	Integer monto = 0;
		for (ItemBoleta itemBoleta : listaItemBoleta) {
			monto += (itemBoleta.getCantidad() * itemBoleta.getProducto().getPrecio()); 
		}
    	return monto;
    }

	@Override
	public String toString() {
		return "Boleta [idBoleta=" + idBoleta + ", fecha=" + fecha + ", monto=" + monto + "]";
	}
    
    
    
}
