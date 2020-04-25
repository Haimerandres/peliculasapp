/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import conexionbd.Persistencia;
import entidades.entidad_pelicula;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import modelo.*;

/**
 *
 * @author HAIMER
 */
@Named(value = "controlprincipal")
@ApplicationScoped
public class controlprincipal {

    /**
     * Creates a new instance of controlprincipal
     */
    modeloprincipal m = new modeloprincipal();

    Persistencia p = new Persistencia();

    /**
     * Creates a new instance of controlprincipal
     */
    public controlprincipal() {

        llenar();
    }

    entidad_pelicula en = new entidad_pelicula();

    ArrayList<entidad_pelicula> listapelicula;

    public ArrayList<entidad_pelicula> getListapelicula() {
        return listapelicula;
    }

    public void setListapelicula(ArrayList<entidad_pelicula> listapelicula) {
        this.listapelicula = listapelicula;
    }

    private Integer nid_pelicula;
    private String ntitulo;
    private String ndirector;
    private String ntipo;
    private String ndescripcion;
    private String nimagen;

    private int nrating;
    private int ncantidadvotos;

    private entidad_pelicula pelicula;

    public entidad_pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(entidad_pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public void llenar() {

        listapelicula = new ArrayList<entidad_pelicula>();
        Object datos[][] = m.mostrarpeliculas();

        for (int i = 0; i < datos.length; i++) {
            for (int j = 0; j < datos[i].length; j++) {

                nid_pelicula = Integer.parseInt(String.valueOf(datos[i][0]));
                ntitulo = String.valueOf(datos[i][1]);
                ndirector = String.valueOf(datos[i][2]);
                ntipo = String.valueOf(datos[i][3]);
                ndescripcion = String.valueOf(datos[i][4]);
                nimagen = String.valueOf(datos[i][5]);
                nrating = Integer.parseInt(String.valueOf(datos[i][6]));
                ncantidadvotos = Integer.parseInt(String.valueOf(datos[i][7]));
            }

            listapelicula.add(new entidad_pelicula(Integer.toString(nid_pelicula), ntitulo, ndirector, ntipo, ndescripcion, nimagen, nrating, ncantidadvotos));
        }
    }

}
