/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author Alumno
 */
public class Producto implements Serializable {
    
    private String descripcion;
    private int idprod;
    private int stockactual;
    private int stockminimo;
    private float pvp;
    
    
    private PropertyChangeSupport propertySupport;
    
    public Producto() {
        propertySupport = new PropertyChangeSupport(this);
    }

    public Producto(String descripcion, int idprod, int stockactual, int stockminimo, float pvp) {
        this.descripcion = descripcion;
        this.idprod = idprod;
        this.stockactual = stockactual;
        this.stockminimo = stockminimo;
        this.pvp = pvp;
        propertySupport = new PropertyChangeSupport(this);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdprod() {
        return idprod;
    }

    public int getStockactual() {
        return stockactual;
    }

    public int getStockminimo() {
        return stockminimo;
    }

    public float getPvp() {
        return pvp;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdprod(int idprod) {
        this.idprod = idprod;
    }

    public void setStockactual(int stockactual) {
        int valoranterior = this.stockactual;
        this.stockactual = stockactual;
        if(this.stockactual<this.getStockminimo()){
        
            propertySupport.firePropertyChange("stockactual", valoranterior, this.stockactual);
            this.stockactual = valoranterior;
            //esto digamos que sería un rollback, de manera que impediría el cambio de stock y desencadenaría un evento.
        }          
    }

    public void setStockminimo(int stockminimo) {
        this.stockminimo = stockminimo;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
}
