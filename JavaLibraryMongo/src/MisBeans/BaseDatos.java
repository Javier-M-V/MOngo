/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import org.bson.Document;
//import org.bson.Document;

/**
 *
 * @author Alumno
 */
public class BaseDatos {
    
    static MongoClient cliente;
    static MongoDatabase DDBB;
    
    
    public BaseDatos(){
        cliente = new MongoClient();
        DDBB = cliente.getDatabase("mibasedatos");
    }
    
    public static MongoDatabase getDB(){
        return DDBB;
    
    }
    
    public static Producto obtenerProductoDB(int idproducto){
    
    return null;
    
    }
    
    public static int obtenerNumeroProducto(){
    
        //se devuelve el numero del siguente producto a insertar
        return 1;
    }
    
    public static int obtenerNumeroPedido(){
    
        return 0;
        
        //numero sigfuiente al numero maximo que hay insertado en la base de datos
    }
    
    public static int obtenerNumeroVentas(){
    
    //devolver el primer registro +1 de los numero de venta ordenados de forma descendente
    return 0;
    
    }
    
    public boolean insertarProducto(Producto producto){
    
        return true;//TODO OK
        
    }
    
    public boolean insertarPedido(Producto producto, int cantidad){
    
        return true;//OK
    
    }
    
    public boolean insertarVenta(Producto producto, int cantidad, String observaciones){
         
        
        return insertarVenta(producto.getIdprod(),cantidad,observaciones);
    }
    
    public boolean insertarVenta(int idproducto, int cantidad, String observaciones){
            
        
        return true;
    }
    
    private static java.sql.Date getFechaActual(){
    
    
        return new java.sql.Date(new java.util.Date().getTime());
    }
    private void actualizarStock(Producto producto, int cantidad){
    
    
    }
    
    public ArrayList<Producto> datosProductos(){
        //retorno 
    
    return null;
    }
    
    public Producto convertirDocumentoProducto(Document docu){
    //cargamos las etiquetas BSON
        return null;
    
    }
}
