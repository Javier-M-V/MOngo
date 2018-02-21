/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author Alumno
 */
public class Cliente_DDBB {
    
    public static void main (String[]args) throws IOException{
           
        int opcion = -1;
        BaseDatos base = new BaseDatos(); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do{
           System.out.println("¿Qué quieres hacer?");
           try{
               opcion = Integer.parseInt(br.readLine());
           }catch(Exception e){opcion = 9;}
           
           switch(opcion){

           case 1:
               /*           
               private String descripcion;
                private int idprod;
                private int stockactual;
                private int stockminimo;
                private float pvp;*/
               if (base.insertarProducto(new Producto("Tijeras",9,6,7,(float)5.5))){
                   System.out.println("Insertado con éxito");             
               }
               break;
           case 8:
                if(base.inicializarBasedatos()){
       
                    System.out.println("inicializada con éxito");
                }
                else{

                    System.out.println("Algo ha ido muy mal...");
                }
                break;
           default:
               System.out.println("debes meter un número");
               break;
               
 
       }//switch             
        }while(opcion<=8 && opcion>0);
        
    }//main   
}//clase
