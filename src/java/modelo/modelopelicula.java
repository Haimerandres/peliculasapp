/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Control.ControlPelicula;
import conexionbd.Persistencia;
import entidades.entidad_pelicula;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HAIMER
 */
public class modelopelicula {
    
     Persistencia p = new Persistencia();
     
     public int contarpeliculas() {
        int numero = 0;
        String sql = "SELECT count(p.id_pelicula) FROM pelicula p;";
        ResultSet res = p.ejecutarconsulta(sql);
        try {
            while (res.next()) {
                numero = res.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControlPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numero;
    }

    public Object[][] mostrarpeliculas() {
        Object data[][] = new Object[contarpeliculas()][8];
        ResultSet res = null;
        String sql = "SELECT * FROM pelicula; ";

        res = p.ejecutarconsulta(sql);

        try {
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString(1);
                data[i][1] = res.getString(2);
                data[i][2] = res.getString(3);
                data[i][3] = res.getString(4);
                data[i][4] = res.getString(5);
                data[i][5] = res.getString(6);
                 data[i][6] = res.getString(7);
                  data[i][7] = res.getString(8);
                
                
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControlPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;

    }
    
    public  boolean eliminarpeliculas(String id){
         boolean eliminar =false ;
         
        String sql = "Delete from pelicula where id_pelicula= '" +id+ "'";
                eliminar = p.ejecutarDML(sql);
                
        return eliminar;
    }
    
    public  boolean  actualizarpelicula ( entidad_pelicula selectedCar ) {
          boolean actualizo = false;
           String sql = "UPDATE pelicula SET  nombre_pelicula='"+selectedCar.getPelicula()+"', director='"+selectedCar.getDirector()
                +"', tipo='"+selectedCar.getTipo()+"', descripcion='"+selectedCar.getDescripcion()+"' WHERE id_pelicula='"+selectedCar.getId_pelicula()+"'";

        actualizo = p.ejecutarDML(sql);         
        
        return actualizo;
    }
    
    
       public   boolean  insertarpeliculas(String titulo, String director,   String tipo ,   String descripcion,
        String imagen) {
       
        boolean inserto = false;
        String sql = "INSERT INTO pelicula(nombre_pelicula, director, tipo, descripcion,imagen,rating, cantidadvotos)"
                + " VALUES ('" + titulo + "','" + director + "','"+tipo+"','"+descripcion+"','"+imagen+"','0','0')";

        inserto = p.ejecutarDML(sql);
       
        return  inserto;
       
             
   }
       
        public  boolean reitenactualizar (int nuevotos,int nuret,int nid_pelicula){
           boolean actualizo = false;
            String sql = "UPDATE pelicula SET  rating='"+nuevotos+"', cantidadvotos='"+nuret+"'  WHERE id_pelicula='"+nid_pelicula+"'";

       actualizo = p.ejecutarDML(sql);
       
       return  actualizo;
      }
    
    
}
