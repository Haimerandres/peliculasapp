/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import conexionbd.Persistencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HAIMER
 */
public class modeloprincipal {
    
    Persistencia  p = new Persistencia();
    
      public int contarpeliculas() {
        int numero = 0;
        String sql = "SELECT count(p.id_pelicula) FROM pelicula p";
        ResultSet res = p.ejecutarconsulta(sql);
        try {
            while (res.next()) {
                numero = res.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(modeloprincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numero;
    }

    public Object[][] mostrarpeliculas() {
        Object data[][] = new Object[contarpeliculas()][8];
        ResultSet res = null;
        String sql = "SELECT id_pelicula, nombre_pelicula, director, tipo, descripcion, imagen, rating, cantidadvotos\n" +
"	FROM public.pelicula  p ORDER by  p.rating DESC ";

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
            Logger.getLogger(modeloprincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;

    }
}
