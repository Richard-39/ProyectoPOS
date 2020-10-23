package com.web.modelo;

import java.util.ArrayList;
import java.util.Date;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_boleta")
	private Integer idBoleta; 
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha")
    private Date fecha;
    private Integer monto;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="id_boleta")
    List<ItemBoleta> itemBoleta = new ArrayList<ItemBoleta>();
	
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "boleta", cascade = CascadeType.ALL)
//    List<ItemBoleta> itemBoleta = new ArrayList<ItemBoleta>();
    
    public static Integer calcularMonto(List<ItemBoleta> listaItemBoleta) {
    	Integer monto = 0;
		for (ItemBoleta itemBoleta : listaItemBoleta) {
			monto += (itemBoleta.getCantidad() * itemBoleta.getProducto().getPrecio()); 
		}
    	return monto;
    }
    
    public void addItemBoleta(ItemBoleta itemBoleta) {
    	this.itemBoleta.add(itemBoleta);
    }
    
}
