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
public class Ventas implements Serializable, PropertyChangeListener {
    
    
    private int numeroventa;
    private int idproducto;
    private Date fechaventa;
    private int cantidad;
    private String obvervaciones;
    
    
    public Ventas() {
       
    }

    public Ventas(int numeroventa, int idproducto, int cantidad, String obvervaciones) {
        this.numeroventa = numeroventa;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.obvervaciones = obvervaciones;
    }

    public int getNumeroventa() {
        return numeroventa;
    }

    public void setNumeroventa(int numeroventa) {
        this.numeroventa = numeroventa;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public Date getFechaventa() {
        return fechaventa;
    }

    public void setFechaventa(Date fechaventa) {
        this.fechaventa = fechaventa;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObvervaciones() {
        return obvervaciones;
    }

    public void setObvervaciones(String obvervaciones) {
        this.obvervaciones = obvervaciones;
    }

    
    
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        this.obvervaciones = "pendiende de pedido por falta de stock";
    }
    
   
    
}
