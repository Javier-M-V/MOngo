/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Indexes.*;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author javier MV
 */
public class BaseDatos {
    
    static MongoClient cliente;
    static MongoDatabase ddbb;
    
    
    public BaseDatos(){
        
        cliente = new MongoClient();
        ddbb = cliente.getDatabase("mibasedatos");
    }
    
    public static MongoDatabase getDB(){
        
        return ddbb;
    }
    
    public static Producto obtenerProductoDB(int idproducto){
   
        MongoCollection<Document> coleccion = ddbb.getCollection("productos");
        Document doc = (Document) coleccion.find(eq("id",idproducto)).first();
        if(doc != null){
      
            Producto requestedProduct = new Producto();
            requestedProduct.setIdprod(Integer.parseInt(doc.getString("id")));
            requestedProduct.setDescripcion(doc.getString("Desc"));
            requestedProduct.setPvp((doc.getDouble("pvp").floatValue()));
            requestedProduct.setStockactual(doc.getInteger("stockactual"));
            requestedProduct.setStockminimo(doc.getInteger("stockminimo"));
            return requestedProduct;
        }
        else{
            
            return null;
        }   
    }
    
    public static int obtenerNumeroProducto(){
    
        MongoCollection<Document> coleccion = ddbb.getCollection("productos");
        try{
            Document doc = (Document) coleccion.find().sort(descending("id")).first();
            return doc.getInteger("id")+1;
            
        }catch(MongoException e){
        
            return 0;
        }
    }
    
    public static int obtenerNumeroPedido(){
    
        MongoCollection<Document> coleccion = ddbb.getCollection("pedidos");
        try{
            Document doc = (Document) coleccion.find().sort(descending("numpedido")).first();
            return doc.getInteger("numpedido")+1;
            
        }catch(MongoException e){
        
            return 0;
        }
    }
    
    public static int obtenerNumeroVentas(){
    
        MongoCollection<Document> coleccion = ddbb.getCollection("ventas");
        try{
            Document doc = (Document) coleccion.find().sort(descending("numventas")).first();
            return doc.getInteger("numventas")+1;
            
        }catch(MongoException e){
        
            return 0;
        }
    
    }
    
    public boolean insertarProducto(Producto producto){
    
        return true;//TODO OK
        
    }
    
    
    public boolean borrarProducto(Producto producto){
    
        return borrarProducto(producto.getIdprod());
    }
    
    public boolean borrarProducto(int idproducto){
        
            return true;
        
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
    
    
    
    public boolean inicializarBasedatos(){
        //borrar la base de datos
        //crear las colecciones
        //insertar los datos de 3 documentos desde joson
        
        return true;
        
    }
    
    
}
