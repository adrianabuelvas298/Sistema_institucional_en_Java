/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csr_modulo_notas;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author adriana
 */
public class conexion {
       Connection conectar=null;
public  conexion(){   
  try{
    Class.forName("com.mysql.jdbc.Driver");//.newInstance();
      conectar= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/CSR_BD","root","986530");
    //conectar=DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/personas","luis","");
    //conectar= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bd_csr","root","986530");
  }catch(SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error de conexion de la base de datos");
                                   }catch(ClassNotFoundException ex) {                                                                }
    
}

    public Connection getConnection() {
        return conectar; 
    }
}
