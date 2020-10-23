package com.web.modelo;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "item_boleta")
public class ItemBoleta implements Serializable {

//	@EmbeddedId
//    private ItemBoletaKey id;
//     
//    @ManyToOne(fetch = FetchType.EAGER)
//    @MapsId("idProducto")
//    @JoinColumn(name = "id_producto")
//    private Producto producto;
// 
//    @ManyToOne(fetch = FetchType.EAGER)
//    @MapsId("idBoleta")
//    @JoinColumn(name = "id_boleta")
//    private Boleta boleta;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_item_boleta")
	private Integer id_item_boleta;
	
    @Column(name = "cantidad")
    private Integer cantidad;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_producto")
    private Producto producto;

	public ItemBoleta() {
		super();
	}
	
	public Integer carcularSubTotal() {
		return producto.getPrecio() * cantidad;
	}

	private static final long serialVersionUID = 1L;
}
