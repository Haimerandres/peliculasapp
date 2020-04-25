
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ANDRES
 */
public class conexionbd {
    
    
    
    static  String bd = "peliculas" ;
    static  String login ="postgres";
    static  String password="root" ;
    static  String url ="jdbc:postgresql://localhost/" + bd ;
    
    Connection con  = null ;
    
       public conexionbd () {
           
           try {
               Class.forName("org.postgresql.Driver");
               con  = DriverManager.getConnection(url, login, password);
                if  (con != null ){
                    System.out.println("conexion establecida ");
                }
           } catch (SQLException e) {
                System.out.println(e.toString());
           }
           catch(ClassNotFoundException e){
               System.out.println(e.toString());
               
           }
       
       }
       
       public Connection getConnection () {
           return  con ;
       }
       
        public  void desconectar () {
            con= null ;
            
        }
       
       public  static  void main (String args []){
           conexionbd cbd = new conexionbd() ;
           cbd.getConnection();
       }
    
    
}
