/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import conexionbd.Persistencia;
import entidades.entidad_pelicula;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.*;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author HAIMER
 */
@Named(value = "controlPelicula")
@ApplicationScoped
public class ControlPelicula {

    Persistencia p = new Persistencia();

    modelopelicula mp = new modelopelicula();

    /**
     * Creates a new instance of controlpelicula
     */
    public ControlPelicula() {
        llenar();
    }

    private Integer nid_pelicula;
    private String ntitulo;
    private String ndirector;
    private String ntipo;
    private String ndescripcion;
    private String nimagen;
    private int nrating;
    private int ncantidadvotos;
    private String pelicula;
    private String director;
    private String tipo;
    private String descripcion;
    private int rating;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    private String titulo;
    private Integer id_pelicula;

    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    private entidad_pelicula selectedCar;

    public entidad_pelicula getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(entidad_pelicula selectedCar) {
        this.selectedCar = selectedCar;
    }

    public Integer getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(Integer id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNimagen() {
        return nimagen;
    }

    public void setNimagen(String nimagen) {
        this.nimagen = nimagen;
    }

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    ArrayList<entidad_pelicula> listapelicula;

    ArrayList<String> idpelicula;

    public void llenar() {

        listapelicula = new ArrayList<entidad_pelicula>();
        Object datos[][] = mp.mostrarpeliculas();

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

    public ArrayList<entidad_pelicula> getListapelicula() {
        return listapelicula;
    }

    public void setListapelicula(ArrayList<entidad_pelicula> listapelicula) {
        this.listapelicula = listapelicula;
    }

    public void insertpelicula() {

        String ruta = file.getFileName();
        Path path = Paths.get(ruta);

        // call getFileName() and get FileName path object 
        Path fileName = path.getFileName();

        // print FileName 
        this.imagen = fileName.toString();

        boolean inserto = false;

        inserto = mp.insertarpeliculas(this.titulo, this.director, this.tipo, this.descripcion,
                this.imagen);

        String fromFile = ruta;
        String toFile = "C:\\Users\\HAIMER\\Documents\\NetBeansProjects\\peliculasapp\\web\\imagenes\\" + this.imagen + "";
        boolean result = copyFile(fromFile, toFile);
        System.out.println(result
                ? "Success! File copying (Éxito! Fichero copiado)"
                : "Error! Failed to copy the file (Error! No se ha podido copiar el fichero)");

        if (inserto == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("pelicula ingresada "));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("pelicula no ingresada"));

        }

        this.titulo = null;
        this.director = null;
        this.descripcion = null;

        llenar();
    }

    public void seleccioneliminar() {
        idpelicula = new ArrayList<String>();
        for (entidad_pelicula p : listapelicula) {

            if (p.isSelect()) {

                idpelicula.add(p.getId_pelicula());
            }

        }
    }

    public void eliminarpelicula() {
        seleccioneliminar();

        if (!idpelicula.isEmpty()) {
            for (int i = 0; i < idpelicula.size(); i++) {
                boolean elimino = false;
                elimino = mp.eliminarpeliculas(idpelicula.get(i));
                if (elimino == true) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("datos eliminados"));
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("seleccione datos para eliminar"));
        }
        llenar();
    }

    public void actualizar() {
        boolean actualizo = false;
        actualizo = mp.actualizarpelicula(selectedCar);

    }

    public void actualizarreting() {

        boolean actualizo = false;

        int id = this.nid_pelicula;

        int votos = this.ncantidadvotos;
        int re = this.nrating;

        int nuevotos = votos + 1;
        int nuret = (re + this.rating) / 2;
        actualizo = mp.reitenactualizar(nuevotos, nuret, this.nid_pelicula);

    }

    public boolean copyFile(String fromFile, String toFile) {
        File origin = new File(fromFile);
        File destination = new File(toFile);
        if (origin.exists()) {
            try {
                InputStream in = new FileInputStream(origin);
                OutputStream out = new FileOutputStream(destination);
                // We use a buffer for the copy (Usamos un buffer para la copia).
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                return true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

}
