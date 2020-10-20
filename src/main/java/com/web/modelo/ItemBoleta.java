package com.web.modelo;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "item_boleta")
public class ItemBoleta {

    @EmbeddedId
    private ItemBoletaKey id;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto")
    private Producto producto;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idBoleta")
    @JoinColumn(name = "id_boleta")
    private Boleta boleta;
 
    @Column(name = "cantidad")
    private int cantidad;

	
}
