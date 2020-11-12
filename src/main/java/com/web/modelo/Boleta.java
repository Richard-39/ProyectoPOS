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
    
    @Column(name="forma_pago")
    private String formaPago;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="id_boleta")
    List<ItemBoleta> itemBoleta = new ArrayList<ItemBoleta>();
    
    @Column(name="estado")
    private String estado;
	   
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
