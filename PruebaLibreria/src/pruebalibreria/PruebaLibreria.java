/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebalibreria;

import MisBeans.Pedido;
import MisBeans.Producto;
import java.util.Date;

/**
 *
 * @author Alumno
 */
public class PruebaLibreria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Producto productito = new Producto("Dabber sur femme",1,10,3, (float) 16.0);
        
        Pedido pedidito = new Pedido(1,productito,new Date(),50);
        
        productito.addPropertyChangeListener(pedidito);
        
        productito.setStockactual(2);
    }
    
}
