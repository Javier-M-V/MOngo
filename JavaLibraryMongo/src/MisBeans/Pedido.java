/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Alumno
 */
public class Pedido implements Serializable,PropertyChangeListener {
     
    private int numpedido;
    private Producto producto;
    private java.util.Date fecha;
    private int cantidad;
    
    public Pedido() {
      
    }

    public Pedido(int numpedido, Producto producto, Date fecha, int cantidad) {
        this.numpedido = numpedido;
        this.producto = producto;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getNumpedido() {
        return numpedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNumpedido(int numpedido) {
        this.numpedido = numpedido;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        System.out.println("Stock anterior: "+ pce.getOldValue());
        System.out.println("Stock actual: "+ pce.getNewValue());
        System.out.println("Realizar pedido en producto: "+producto.getDescripcion());
    }
    
    
    
}
