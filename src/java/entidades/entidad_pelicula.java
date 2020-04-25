 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author HAIMER
 */
public class entidad_pelicula {

    private String id_pelicula;

    private String pelicula;

    private String director;

    private String tipo;
    private String descripcion;
    private  String imagen ;
    
    private int rating;            
     private int cantidadvotos;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCantidadvotos() {
        return cantidadvotos;
    }

    public void setCantidadvotos(int cantidadvotos) {
        this.cantidadvotos = cantidadvotos;
    }

    public entidad_pelicula(String id_pelicula, String pelicula, String director, String tipo, String descripcion, String imagen, int rating, int cantidadvotos) {
        this.id_pelicula = id_pelicula;
        this.pelicula = pelicula;
        this.director = director;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.rating = rating;
        this.cantidadvotos = cantidadvotos;
    }
    

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

   

    private boolean select;

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

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(String id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public entidad_pelicula() {
    }

    public entidad_pelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }
}
