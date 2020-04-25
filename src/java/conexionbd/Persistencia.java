/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ANDRES
 */
public class Persistencia {
    
        
   conexionbd cdb = null ;
    
   public  boolean ejecutarDML(String sql){
       boolean ejecuto  = false  ;
       cdb = new conexionbd();
       
       try {
           PreparedStatement ps = cdb.getConnection().prepareStatement(sql);
           int recibe = ps.executeUpdate();
           if(recibe > 0){
               ejecuto = true ;
           }
           
       } catch (Exception e) {
           System.out.println("error "+e.toString());
       }
       cdb.desconectar();
       return ejecuto;
   }
   
    public ResultSet ejecutarconsulta ( String sql ){
        ResultSet datos = null ;
        cdb= new conexionbd();
        try {
            PreparedStatement ps =cdb.getConnection().prepareCall(sql);
            datos =ps.executeQuery();
        } catch (Exception e) {
            System.out.println("error"+e.toString());
        }
        return datos ;
    }

    public Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
