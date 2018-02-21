/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Indexes.*;
import static com.mongodb.client.model.Updates.set;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author javier MV
 */
public class BaseDatos {
    
    static MongoClient cliente;
    static MongoDatabase BBDD;   
    
    public BaseDatos(){
        
        cliente = new MongoClient();
        BBDD = cliente.getDatabase("mibasedatos");
    }
    
    public static MongoDatabase getDB(){
        
        return BBDD;
    }
    
    public static Producto obtenerProductoDB(int idproducto){
   
        MongoCollection<Document> coleccion = BBDD.getCollection("productosfile");
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
    
        MongoCollection<Document> coleccion = BBDD.getCollection("productosfile");
        try{
            Document doc = (Document) coleccion.find().sort(descending("id")).first();
            return doc.getInteger("id")+1;
            
        }catch(MongoException e){
        
            return 0;
        }
    }
    
    public static int obtenerNumeroPedido(){
    
        MongoCollection<Document> coleccion = BBDD.getCollection("pedidosfile");
        try{
            Document doc = (Document) coleccion.find().sort(descending("numpedido")).first();
            return doc.getInteger("numpedido")+1;
            
        }catch(MongoException e){
        
            return 0;
        }
    }
    
    public static int obtenerNumeroVentas(){
    
        MongoCollection<Document> coleccion = BBDD.getCollection("ventasfile");
        try{
            Document doc = (Document) coleccion.find().sort(descending("numventas")).first();
            return doc.getInteger("numventas")+1;
            
        }catch(MongoException e){
        
            return 0;
        }    
    }
    
    public boolean insertarProducto(Producto producto){
    
        try{
            Document nuevo = new Document();
            nuevo.put("idprod", obtenerNumeroProducto());
            nuevo.put("stockactual", producto.getStockactual());
            nuevo.put("stockminimo", producto.getStockminimo());
            nuevo.put("pvp", producto.getPvp());
            MongoCollection<Document> coleccion = BBDD.getCollection("productosfile");
            coleccion.insertOne(nuevo);
            return true;
        }catch(Exception e){return false;}   
    }
    
    
    public boolean borrarProducto(Producto producto){
    
        return borrarProducto(producto.getIdprod());
    }
    
    public boolean borrarProducto(int idproducto){
        
        MongoCollection<Document> coleccion = BBDD.getCollection("productosfile");
        try{
            coleccion.deleteOne(eq("id",idproducto));
            return true;
        }catch(Exception e ){return false;}  
    }
    
    public boolean insertarPedido(Producto producto, int cantidad){
        
        Producto prodenpedido = new Producto();
        MongoCollection<Document> coleccion;
        try{
            coleccion = BBDD.getCollection("productosfile");
            Document doped = coleccion.find(eq("idproducto",producto.getIdprod())).first();
            
            Document pedido = new Document()
                    .append("numeropedido",obtenerNumeroPedido())
                    .append("producto",doped);
            coleccion = BBDD.getCollection("pedidosfile");
            coleccion.insertOne(pedido);
            
            return true;  
        }catch(Exception e){return false;}
    }
    
    public boolean insertarVenta(Producto producto, int cantidad, String observaciones){
         
        
        return insertarVenta(producto.getIdprod(),cantidad,observaciones);
    }
    
    public boolean insertarVenta(int idproducto, int cantidad, String observaciones){
            
        MongoCollection<Document> coleccion = BBDD.getCollection("ventasfile");
        try{
            Document ventainsertar = new Document()
                .append("numeroventa",obtenerNumeroVentas())
                .append("idproducto",idproducto)
                .append("fecahventa", getFechaActual())
                .append("cantidad",cantidad)
                .append("observaciones",observaciones);
            coleccion.insertOne(ventainsertar);
        return true;
        }catch(Exception e){return false;}   
    }
    
    private static java.sql.Date getFechaActual(){
       
        return new java.sql.Date(new java.util.Date().getTime());
    }
    
    private void actualizarStock(Producto producto, int cantidad){
        
        MongoCollection<Document> col = BBDD.getCollection("productosfile");
        col.updateOne(eq("idproducto",producto.getIdprod()),set("stockactual",cantidad));
    }
    
    public ArrayList<Producto> datosProductos(){
                
        ArrayList<Producto> arrayproductos = new ArrayList<Producto>();
        Producto productoenarray = null;
        Document documentoacargar = null;
        MongoCollection<Document> col = BBDD.getCollection("productosfile");
        MongoCursor<Document> cursor = col.find().iterator();
   
        while(cursor.hasNext()){
        
            documentoacargar = cursor.next();
            try{
                productoenarray = new Producto( documentoacargar.getString("descripcion"),
                documentoacargar.getInteger("idproducto"),
                documentoacargar.getInteger("stockactual"),
                documentoacargar.getInteger("stockminimo"),
                Float.parseFloat(documentoacargar.getDouble("pvp").toString()));
                arrayproductos.add(productoenarray);
            }catch(Exception e ){System.out.println("Error de construcción con el document "+documentoacargar.toJson());}
        }
        return arrayproductos;
    }
    
    public Producto convertirDocumentoProducto(Document docu){
        
        Producto prod=null;
        try{
             prod = new Producto(
                docu.getString("descripcion"),
                docu.getInteger("idproducto"),
                docu.getInteger("stockactual"),
                docu.getInteger("stockminimo"),
                Float.parseFloat(docu.getDouble("pvp").toString()));
        }catch(Exception e) {e.printStackTrace();}    
        return prod;
    }
  
    public boolean inicializarBasedatos() throws IOException{
        
        Document docu;
        FileReader fr =null;
        BufferedReader lector;
        String cadenajson;
        
        try{
            MongoCollection<Document> coleccionventas = BBDD.getCollection("ventasfile");
            coleccionventas.drop();
        }catch(Exception e){e.printStackTrace();}
        try{
            MongoCollection<Document> coleccionpedido = BBDD.getCollection("pedidosfile");
            coleccionpedido.drop();
        }catch(Exception e){e.printStackTrace();}
        try{      
            MongoCollection<Document> coleccionproduto = BBDD.getCollection("productosfile");
            coleccionproduto.drop();
        }catch(Exception e){e.printStackTrace();}
        
        try{
            BBDD.createCollection("ventasfile");
            BBDD.createCollection("pedidosfile");
            BBDD.createCollection("productosfile");
            System.out.println("Coleccion de productos creada con éxito");
            System.out.println("Coleccion de pedidos creada con éxito");
            System.out.println("Coleccion de ventas creadaa con éxito");
           
        }catch(Exception e){
            e.printStackTrace(); 
            System.out.println("Algo ha explotado...");
            return false;
        }       
         try{
             fr = new FileReader(new File("productos.json"));
             lector = new BufferedReader(fr);            
             while ((cadenajson=lector.readLine())!=null){
                 System.out.println(cadenajson);
                 docu = new Document(Document.parse(cadenajson));
                 MongoCollection<Document> coleccionventas = BBDD.getCollection("productosfile");
                 coleccionventas.insertOne(docu);
             }
        }catch(IOException e){
            e.printStackTrace(); 
            System.out.println("Algo ha explotado...");
            return false;
        }
        try{
            fr = new FileReader(new File("ventas.json"));
            lector = new BufferedReader(fr);            
            while ((cadenajson=lector.readLine())!=null){
                System.out.println(cadenajson);

                docu = new Document(Document.parse(cadenajson));
                MongoCollection<Document> coleccionventas = BBDD.getCollection("ventasfile");
                coleccionventas.insertOne(docu);
            }
        }catch(IOException e){
            e.printStackTrace(); 
            System.out.println("Algo ha explotado...");
            return false;}
        try{
            fr = new FileReader(new File("pedidos.json"));
            lector = new BufferedReader(fr);            

            while ((cadenajson=lector.readLine())!=null){
                System.out.println(cadenajson);

                docu = new Document(Document.parse(cadenajson));
                MongoCollection<Document> coleccionventas = BBDD.getCollection("pedidosfile");
                coleccionventas.insertOne(docu);
            }
        }catch(IOException e){
            e.printStackTrace(); 
            System.out.println("Algo ha explotado...");
            
            return false;
        }
        fr.close();
        return true;
            
    } 
    
}

    
