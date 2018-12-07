/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Notas;

import csr_modulo_notas.Gestion_matriculas;
import static csr_modulo_notas.Gestion_matriculas.fechaMySQL;
import csr_modulo_notas.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author adriana
 */
public class INGRESAR_NOTAS extends javax.swing.JFrame {
          
    /**
     * Creates new form INGRESAR_NOTAS
     * 
     */
    public String id="";
    public INGRESAR_NOTAS() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Gestión de notas");
       
          /**Select s= new Select();
          String  cAno= s.getAno();
          String  cPeriodo= s.getPeriodo();
          String  cGrado= s.getGrado();
          this.jAno.setText(cAno);
          this.jPeriodo.setText(cPeriodo);
          this.jGrado.setText(cGrado);
         */
        
          
          
     conectar_dos(jGrado.getText(),jAno.getText());       
    }
        
    public void conectar_dos(String valor, String valores){
                         conexion cc=new conexion();
                        Connection cn=cc.getConnection();
                        DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (0 == column)
					return false;
				return super.isCellEditable(row, column);
			}
		};
                                           
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("ESTADO");

                                 jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   
    }
    
    
    
    
    
    
    
          public void mostrardatos(String valor, String valores){
          conexion cc=new conexion();
          Connection cn=cc.getConnection();
          DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (0 == column)
					return false;
				return super.isCellEditable(row, column);
			}
		};
        
           
          modelo.addColumn("COD. ESTU");
          modelo.addColumn("PRIMER APELLIDO");
          modelo.addColumn("SEGUNDO APELLIDO"); 
          modelo.addColumn("NOMBRES");
          modelo.addColumn("NOTA");
          modelo.addColumn("ACUMULADO");
          modelo.addColumn("DESEMPEÑO");
          modelo.addColumn("INASISTENCIA");
          modelo.addColumn("ACUMULADO");
          modelo.addColumn("ESTADO");
                    
         jTNotas.setModel(modelo);
        String sql="";
        if (valor.equals("") && valores.equals(""))
        {
            sql="SELECT * FROM Estudiante";
        }
        else{
            sql="SELECT * FROM Estudiante WHERE"
                    + ""
                    + "(Grado_matricula='"+valor+"'  and Ano_matricula='"+valores+"'OR Apellido_materno='"+valor+"'OR Nombres='"+valor+"')";
        }  
        
        String []datos=new String [5];
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                   
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);
            datos[3]=rs.getString(4);
            
            
            modelo.addRow(datos);
            }modelo.addTableModelListener(new TableModelListener(){
                @Override
                @SuppressWarnings("empty-statement")
                public void tableChanged(TableModelEvent e) {
                    if(e.getType()==TableModelEvent.UPDATE){
                     int  columna= e.getColumn();
                     int  fila=e.getFirstRow();
                     if (columna==1){
                            String  sql= "UPDATE Periodo_Uno SET Nota='"+jTNotas.getValueAt(fila, 8)+"'WHERE  Cod_Estudiante='"+jTNotas.getValueAt(fila,0)+"'";
                          
                        
                         try {
                             if(st.execute(sql)){
                                
                                         } else {                     
                             }
                         } catch (SQLException ex) {
                             Logger.getLogger(Gestion_matriculas.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     
                    }
                   
                }
             
             
             
                }
                     });
              jTNotas.setModel(modelo); 
    
                    
        }catch(SQLException ex){
            Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
          public void mostrardatos_segundo(String valor, String valores){
          conexion cc=new conexion();
          Connection cn=cc.getConnection();
          DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (0 == column)
					return false;
				return super.isCellEditable(row, column);
			}
		};
        
            
          modelo.addColumn("COD. ESTU");
          modelo.addColumn("PRIMER APELLIDO");
          modelo.addColumn("SEGUNDO APELLIDO"); 
          modelo.addColumn("NOMBRES");
          modelo.addColumn("NOTA");
          modelo.addColumn("ACUMULADO");
          modelo.addColumn("DESEMPEÑO");
          modelo.addColumn("INASISTENCIA");
          
                    
         jTNotas.setModel(modelo);
        String sql="";
        if (valor.equals("") && valores.equals(""))
        {
            sql="SELECT * FROM Estudiante";
        }
        else{
            sql="SELECT * FROM Estudiante WHERE"
                    + ""
                    + "(Grado_matricula='"+valor+"'  and Ano_matricula='"+valores+"'OR Apellido_materno='"+valor+"'OR Nombres='"+valor+"')";
        }  
        
        String []datos=new String [5];
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                   
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);
            datos[3]=rs.getString(4);
            
            
            modelo.addRow(datos);
            }modelo.addTableModelListener(new TableModelListener(){
                @Override
                @SuppressWarnings("empty-statement")
                public void tableChanged(TableModelEvent e) {
                    if(e.getType()==TableModelEvent.UPDATE){
                     int  columna= e.getColumn();
                     int  fila=e.getFirstRow();
                     if (columna==1){
                            String  sql= "UPDATE Periodo_Dos SET Nota='"+jTNotas.getValueAt(fila, 8)+"'WHERE  Cod_Estudiantes='"+jTNotas.getValueAt(fila,0)+"'";
                          
                        
                         try {
                             if(st.execute(sql)){
                                
                                         } else {                     
                             }
                         } catch (SQLException ex) {
                             Logger.getLogger(Gestion_matriculas.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     
                    }
                   
                }
             
             
             
                }
                     });
              jTNotas.setModel(modelo); 
    
                    
        }catch(SQLException ex){
            Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
           public void mostrardatos_tercero(String valor, String valores){
          conexion cc=new conexion();
          Connection cn=cc.getConnection();
          DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (0 == column)
					return false;
				return super.isCellEditable(row, column);
			}
		};
        
            
          modelo.addColumn("COD. ESTU");
          modelo.addColumn("PRIMER APELLIDO");
          modelo.addColumn("SEGUNDO APELLIDO"); 
          modelo.addColumn("NOMBRES");
          modelo.addColumn("NOTA");
          modelo.addColumn("ACUMULADO");
          modelo.addColumn("DESEMPEÑO");
          modelo.addColumn("INASISTENCIA");
          
                    
         jTNotas.setModel(modelo);
        String sql="";
        if (valor.equals("") && valores.equals(""))
        {
            sql="SELECT * FROM Estudiante";
        }
        else{
            sql="SELECT * FROM Estudiante WHERE"
                    + ""
                    + "(Grado_matricula='"+valor+"'  and Ano_matricula='"+valores+"'OR Apellido_materno='"+valor+"'OR Nombres='"+valor+"')";
        }  
        
        String []datos=new String [5];
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                   
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);
            datos[3]=rs.getString(4);
            
            
            modelo.addRow(datos);
            }modelo.addTableModelListener(new TableModelListener(){
                @Override
                @SuppressWarnings("empty-statement")
                public void tableChanged(TableModelEvent e) {
                    if(e.getType()==TableModelEvent.UPDATE){
                     int  columna= e.getColumn();
                     int  fila=e.getFirstRow();
                     if (columna==1){
                            String  sql= "UPDATE Periodo_Tres SET Nota='"+jTNotas.getValueAt(fila, 8)+"'WHERE  Cod_Estudiante3='"+jTNotas.getValueAt(fila,0)+"'";
                          
                        
                         try {
                             if(st.execute(sql)){
                                
                                         } else {                     
                             }
                         } catch (SQLException ex) {
                             Logger.getLogger(Gestion_matriculas.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     
                    }
                   
                }
             
             
             
                }
                     });
              jTNotas.setModel(modelo); 
    
                    
        }catch(SQLException ex){
            Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
        
          
       
                    
            // Metodo para visualizar
          public void mostrardatos_visualizar(String valor, String valores){
          conexion cc=new conexion();
          Connection cn=cc.getConnection();
          DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (0 == column)
					return false;
				 return super.isCellEditable(row, column);
			}
		};
        
        
             String periodo=jPeriodo.getText().toString();
                if(periodo=="PRIMERO"){
               String Consul_area= JCArea.getSelectedItem().toString();
              if(Consul_area=="HUMANIDADES-CASTELLANO"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(8);
                                      datos[5]=rs.getString(10);
                                      datos[6]=rs.getString(9);
                                      datos[7]=rs.getString(11);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }      else if(Consul_area=="CIENCIAS NATURALES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(12);
                                      datos[5]=rs.getString(14);
                                      datos[6]=rs.getString(13);
                                      datos[7]=rs.getString(15);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
                        }else if(Consul_area=="CIENCIAS SOCIALES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(16);
                                      datos[5]=rs.getString(18);
                                      datos[6]=rs.getString(17);
                                      datos[7]=rs.getString(19);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN ARTÍSTICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(20);
                                      datos[5]=rs.getString(22);
                                      datos[6]=rs.getString(21);
                                      datos[7]=rs.getString(23);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN FÍSICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(24);
                                      datos[5]=rs.getString(26);
                                      datos[6]=rs.getString(25);
                                      datos[7]=rs.getString(27);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN RELIGIOSA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(28);
                                      datos[5]=rs.getString(30);
                                      datos[6]=rs.getString(29);
                                      datos[7]=rs.getString(31);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN ÉTICA Y VALORES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(32);
                                      datos[5]=rs.getString(34);
                                      datos[6]=rs.getString(33);
                                      datos[7]=rs.getString(35);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="INGLES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(36);
                                      datos[5]=rs.getString(38);
                                      datos[6]=rs.getString(37);
                                      datos[7]=rs.getString(39);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="MATEMÁTICAS"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(40);
                                      datos[5]=rs.getString(42);
                                      datos[6]=rs.getString(41);
                                      datos[7]=rs.getString(43);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="TECNOLOGÍA E INFORMÁTICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(44);
                                      datos[5]=rs.getString(46);
                                      datos[6]=rs.getString(45);
                                      datos[7]=rs.getString(47);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="FILOSOFÍA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(48);
                                      datos[5]=rs.getString(50);
                                      datos[6]=rs.getString(49);
                                      datos[7]=rs.getString(51);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="CÁTEDRA DE LA PAZ"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(52);
                                      datos[5]=rs.getString(54);
                                      datos[6]=rs.getString(53);
                                      datos[7]=rs.getString(55);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
              else if(Consul_area=="ESTADÍSTICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(56);
                                      datos[5]=rs.getString(58);
                                      datos[6]=rs.getString(57);
                                      datos[7]=rs.getString(59);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="QUÍMICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(60);
                                      datos[5]=rs.getString(62);
                                      datos[6]=rs.getString(61);
                                      datos[7]=rs.getString(63);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
              else if(Consul_area=="CIENCIAS POL. Y ECONÓMICAS"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(64);
                                      datos[5]=rs.getString(66);
                                      datos[6]=rs.getString(65);
                                      datos[7]=rs.getString(67);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
              else if(Consul_area=="FÍSICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Uno WHERE"

                                              + "(Grado='"+valor+"'  and Ano='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(68);
                                      datos[5]=rs.getString(70);
                                      datos[6]=rs.getString(69);
                                      datos[7]=rs.getString(71);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
//////////////////////////////////////////////////////////////////////////SEGUNDO PERÍODO //////////////////////////////////////////////////            
              } //Fin si el periodo es uno
                else if(periodo=="SEGUNDO"){
               String Consul_area= JCArea.getSelectedItem().toString();
              if(Consul_area=="HUMANIDADES-CASTELLANO"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(8);
                                      datos[5]=rs.getString(10);
                                      datos[6]=rs.getString(9);
                                      datos[7]=rs.getString(11);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }      else if(Consul_area=="CIENCIAS NATURALES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                 else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }  

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(12);
                                      datos[5]=rs.getString(14);
                                      datos[6]=rs.getString(13);
                                      datos[7]=rs.getString(15);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
                        }else if(Consul_area=="CIENCIAS SOCIALES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                 else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(16);
                                      datos[5]=rs.getString(18);
                                      datos[6]=rs.getString(17);
                                      datos[7]=rs.getString(19);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN ARTÍSTICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                 else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(20);
                                      datos[5]=rs.getString(22);
                                      datos[6]=rs.getString(21);
                                      datos[7]=rs.getString(23);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN FÍSICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(24);
                                      datos[5]=rs.getString(26);
                                      datos[6]=rs.getString(25);
                                      datos[7]=rs.getString(27);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN RELIGIOSA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                 else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(28);
                                      datos[5]=rs.getString(30);
                                      datos[6]=rs.getString(29);
                                      datos[7]=rs.getString(31);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN ÉTICA Y VALORES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                 else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(32);
                                      datos[5]=rs.getString(34);
                                      datos[6]=rs.getString(33);
                                      datos[7]=rs.getString(35);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="INGLES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(36);
                                      datos[5]=rs.getString(38);
                                      datos[6]=rs.getString(37);
                                      datos[7]=rs.getString(39);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="MATEMÁTICAS"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                 else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }  

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(40);
                                      datos[5]=rs.getString(42);
                                      datos[6]=rs.getString(41);
                                      datos[7]=rs.getString(43);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="TECNOLOGÍA E INFORMÁTICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                 else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }  

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(44);
                                      datos[5]=rs.getString(46);
                                      datos[6]=rs.getString(45);
                                      datos[7]=rs.getString(47);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="FILOSOFÍA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(48);
                                      datos[5]=rs.getString(50);
                                      datos[6]=rs.getString(49);
                                      datos[7]=rs.getString(51);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="CÁTEDRA DE LA PAZ"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                 else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(52);
                                      datos[5]=rs.getString(54);
                                      datos[6]=rs.getString(53);
                                      datos[7]=rs.getString(55);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
              else if(Consul_area=="ESTADÍSTICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(56);
                                      datos[5]=rs.getString(58);
                                      datos[6]=rs.getString(57);
                                      datos[7]=rs.getString(59);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="QUÍMICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }     

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(60);
                                      datos[5]=rs.getString(62);
                                      datos[6]=rs.getString(61);
                                      datos[7]=rs.getString(63);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
              else if(Consul_area=="CIENCIAS POL. Y ECONÓMICAS"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                 else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(64);
                                      datos[5]=rs.getString(66);
                                      datos[6]=rs.getString(65);
                                      datos[7]=rs.getString(67);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
              else if(Consul_area=="FÍSICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                 else{
                                      sql="SELECT * FROM Periodo_Dos WHERE"

                                              + "(Grados='"+valor+"'  and Anos='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(68);
                                      datos[5]=rs.getString(70);
                                      datos[6]=rs.getString(69);
                                      datos[7]=rs.getString(71);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
             
              }   
/////////////////////////////////////////////// TERCER PERÍODO//////////////////////////////////////////////////////////////////
                else if(periodo=="TERCERO"){
               String Consul_area= JCArea.getSelectedItem().toString();
              if(Consul_area=="HUMANIDADES-CASTELLANO"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(8);
                                      datos[5]=rs.getString(10);
                                      datos[6]=rs.getString(9);
                                      datos[7]=rs.getString(11);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }      else if(Consul_area=="CIENCIAS NATURALES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }  

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(12);
                                      datos[5]=rs.getString(14);
                                      datos[6]=rs.getString(13);
                                      datos[7]=rs.getString(15);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
                        }else if(Consul_area=="CIENCIAS SOCIALES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                    if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }  

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(16);
                                      datos[5]=rs.getString(18);
                                      datos[6]=rs.getString(17);
                                      datos[7]=rs.getString(19);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN ARTÍSTICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                    if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(20);
                                      datos[5]=rs.getString(22);
                                      datos[6]=rs.getString(21);
                                      datos[7]=rs.getString(23);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN FÍSICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                    if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }   
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(24);
                                      datos[5]=rs.getString(26);
                                      datos[6]=rs.getString(25);
                                      datos[7]=rs.getString(27);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN RELIGIOSA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(28);
                                      datos[5]=rs.getString(30);
                                      datos[6]=rs.getString(29);
                                      datos[7]=rs.getString(31);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN ÉTICA Y VALORES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                    if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(32);
                                      datos[5]=rs.getString(34);
                                      datos[6]=rs.getString(33);
                                      datos[7]=rs.getString(35);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="INGLES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                   if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(36);
                                      datos[5]=rs.getString(38);
                                      datos[6]=rs.getString(37);
                                      datos[7]=rs.getString(39);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="MATEMÁTICAS"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                    if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(40);
                                      datos[5]=rs.getString(42);
                                      datos[6]=rs.getString(41);
                                      datos[7]=rs.getString(43);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="TECNOLOGÍA E INFORMÁTICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                   if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }  

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(44);
                                      datos[5]=rs.getString(46);
                                      datos[6]=rs.getString(45);
                                      datos[7]=rs.getString(47);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="FILOSOFÍA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                     if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }   
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(48);
                                      datos[5]=rs.getString(50);
                                      datos[6]=rs.getString(49);
                                      datos[7]=rs.getString(51);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="CÁTEDRA DE LA PAZ"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                    if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(52);
                                      datos[5]=rs.getString(54);
                                      datos[6]=rs.getString(53);
                                      datos[7]=rs.getString(55);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
              else if(Consul_area=="ESTADÍSTICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(56);
                                      datos[5]=rs.getString(58);
                                      datos[6]=rs.getString(57);
                                      datos[7]=rs.getString(59);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="QUÍMICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                   if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(60);
                                      datos[5]=rs.getString(62);
                                      datos[6]=rs.getString(61);
                                      datos[7]=rs.getString(63);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
              else if(Consul_area=="CIENCIAS POL. Y ECONÓMICAS"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                     if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(64);
                                      datos[5]=rs.getString(66);
                                      datos[6]=rs.getString(65);
                                      datos[7]=rs.getString(67);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
              else if(Consul_area=="FÍSICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                    if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Tres WHERE"

                                              + "(Grado3='"+valor+"'  and Ano3='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(68);
                                      datos[5]=rs.getString(70);
                                      datos[6]=rs.getString(69);
                                      datos[7]=rs.getString(71);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
             
              }
                
////////////////////////////////////////////////////////////////////7CUARTO PERIODO////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 if(periodo=="CUARTO"){
               String Consul_area= JCArea.getSelectedItem().toString();
              if(Consul_area=="HUMANIDADES-CASTELLANO"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");
                                    modelo.addColumn("ACUM_AREA");
                                 


                                  jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                      sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(8);
                                      datos[5]=rs.getString(10);
                                      datos[6]=rs.getString(9);
                                      datos[7]=rs.getString(11);
                                      


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }      else if(Consul_area=="CIENCIAS NATURALES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                        sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }  

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(12);
                                      datos[5]=rs.getString(14);
                                      datos[6]=rs.getString(13);
                                      datos[7]=rs.getString(15);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
                        }else if(Consul_area=="CIENCIAS SOCIALES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                    if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                        sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }  

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(16);
                                      datos[5]=rs.getString(18);
                                      datos[6]=rs.getString(17);
                                      datos[7]=rs.getString(19);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN ARTÍSTICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                    if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                              sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(20);
                                      datos[5]=rs.getString(22);
                                      datos[6]=rs.getString(21);
                                      datos[7]=rs.getString(23);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN FÍSICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                    if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                        sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }   
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(24);
                                      datos[5]=rs.getString(26);
                                      datos[6]=rs.getString(25);
                                      datos[7]=rs.getString(27);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN RELIGIOSA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                        sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(28);
                                      datos[5]=rs.getString(30);
                                      datos[6]=rs.getString(29);
                                      datos[7]=rs.getString(31);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="EDUCACIÓN ÉTICA Y VALORES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                    if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                         sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(32);
                                      datos[5]=rs.getString(34);
                                      datos[6]=rs.getString(33);
                                      datos[7]=rs.getString(35);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="INGLES"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                   if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                         sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(36);
                                      datos[5]=rs.getString(38);
                                      datos[6]=rs.getString(37);
                                      datos[7]=rs.getString(39);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="MATEMÁTICAS"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                    if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                         sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(40);
                                      datos[5]=rs.getString(42);
                                      datos[6]=rs.getString(41);
                                      datos[7]=rs.getString(43);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="TECNOLOGÍA E INFORMÁTICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                   if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                         sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }  

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(44);
                                      datos[5]=rs.getString(46);
                                      datos[6]=rs.getString(45);
                                      datos[7]=rs.getString(47);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="FILOSOFÍA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                     if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                         sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }   
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(48);
                                      datos[5]=rs.getString(50);
                                      datos[6]=rs.getString(49);
                                      datos[7]=rs.getString(51);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="CÁTEDRA DE LA PAZ"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                    if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                         sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(52);
                                      datos[5]=rs.getString(54);
                                      datos[6]=rs.getString(53);
                                      datos[7]=rs.getString(55);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
              else if(Consul_area=="ESTADÍSTICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                  if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                        sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(56);
                                      datos[5]=rs.getString(58);
                                      datos[6]=rs.getString(57);
                                      datos[7]=rs.getString(59);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }else if(Consul_area=="QUÍMICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                   if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                         sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(60);
                                      datos[5]=rs.getString(62);
                                      datos[6]=rs.getString(61);
                                      datos[7]=rs.getString(63);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
              else if(Consul_area=="CIENCIAS POL. Y ECONÓMICAS"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                     if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                         sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }  
                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(64);
                                      datos[5]=rs.getString(66);
                                      datos[6]=rs.getString(65);
                                      datos[7]=rs.getString(67);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
              else if(Consul_area=="FÍSICA"){      
                                    modelo.addColumn("COD. ESTU");
                                    modelo.addColumn("PRIMER APELLIDO");
                                    modelo.addColumn("SEGUNDO APELLIDO"); 
                                    modelo.addColumn("NOMBRES");
                                    modelo.addColumn("NOTA");
                                    modelo.addColumn("ACUMULADO");
                                    modelo.addColumn("DESEMPEÑO");
                                    modelo.addColumn("INASISTENCIA");


                                   jTNotas.setModel(modelo);
                                  String sql="";
                                    if (valor.equals("") && valores.equals(""))
                                  {
                                      sql="SELECT * FROM Estudiante";
                                  }
                                  else{
                                         sql="SELECT * FROM Periodo_Cuatro WHERE"

                                              + "(Grado4='"+valor+"'  and Ano4='"+valores+"')";
                                   }   

                                  String []datos=new String [9];
                                  try{
                                      Statement st=cn.createStatement();
                                      ResultSet rs=st.executeQuery(sql);
                                      while(rs.next()){

                                      datos[0]=rs.getString(1);
                                      datos[1]=rs.getString(2);
                                      datos[2]=rs.getString(3);
                                      datos[3]=rs.getString(4);
                                      datos[4]=rs.getString(68);
                                      datos[5]=rs.getString(70);
                                      datos[6]=rs.getString(69);
                                      datos[7]=rs.getString(71);


                                      modelo.addRow(datos);
                                      }
                                        jTNotas.setModel(modelo); 


                                  }catch(SQLException ex){
                                      Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
                                  }
              }
             
              } 
                
                
                  
                    
                  
                
          }
                 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTNotas = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        JCArea = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jGrado = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPeriodo = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jAno = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.Color.white);

        jPanel2.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 2, 18)); // NOI18N
        jLabel2.setText("           GESTION DE NOTAS");

        jTNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTNotas);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Backward.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(202, 221, 245));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(0));

        JCArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CIENCIAS NATURALES", "CIENCIAS SOCIALES", "EDUCACIÓN ARTÍSTICA", "EDUCACIÓN FÍSICA", "EDUCACIÓN RELIGIOSA", "EDUCACIÓN ÉTICA Y VALORES", "HUMANIDADES-CASTELLANO", "INGLES", "MATEMÁTICAS", "TECNOLOGÍA E INFORMÁTICA", "FILOSOFÍA", "CÁTEDRA DE LA PAZ", "ESTADÍSTICA", "FÍSICA", "QUÍMICA", "CIENCIAS POL. Y ECONÓMICAS" }));
        JCArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCAreaActionPerformed(evt);
            }
        });

        jLabel5.setText("Seleccionar  área:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JCArea, 0, 215, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JCArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel3.setText("Grado:");

        jGrado.setBackground(new java.awt.Color(101, 119, 210));
        jGrado.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jGrado.setText("7");
        jGrado.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jGradoComponentAdded(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel4.setText("Período:");

        jPeriodo.setBackground(new java.awt.Color(101, 119, 210));
        jPeriodo.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jPeriodo.setText("Primero");

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel6.setText("Año:");

        jAno.setBackground(new java.awt.Color(101, 119, 210));
        jAno.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jAno.setText("2017");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/png/32x32/page_process.png"))); // NOI18N
        jButton4.setText("CALCULAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/png/32x32/add.png"))); // NOI18N
        jButton6.setText("GUARDAR NOTAS");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/png/48x48/users.png"))); // NOI18N
        jButton1.setText("VISUALIZAR ESTUDIANTES Y NOTAS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/png/48x48/download.png"))); // NOI18N
        jButton2.setText("CARGAR ESTUDIANTES ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jAno, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jGrado, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(348, 348, 348)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(175, 175, 175)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(jButton5)
                            .addGap(53, 53, 53)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1043, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jGrado))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jAno))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jPeriodo)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton6))
                    .addComponent(jButton5))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/png/32x32/promotion.png"))); // NOI18N
        jMenu1.setText("Opciones");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/png/32x32/delete.png"))); // NOI18N
        jMenuItem1.setText("Cerrar Sesión");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String periodo= jPeriodo.getText().toString();
        if (periodo.equals("PRIMERO")){
        
         try{
         if(jTNotas.getRowCount()>0){
            double acum=0;
             //int ina=0;
             for (int i = 0; i < jTNotas.getRowCount(); i++) {
                  
                  //acum=nota*0.20;
                 DecimalFormatSymbols simbolos= new  DecimalFormatSymbols();
                 simbolos.setDecimalSeparator('.');
                  DecimalFormat formateador= new DecimalFormat("0.00",simbolos);
                 double nota =Double.parseDouble(String.valueOf(jTNotas.getValueAt(i, 4)));
                 String total =formateador.format((nota*0.20));
                                 
                // jTNotas.setValueAt(nota_formato, i, 4);
                 jTNotas.setValueAt(total, i, 5);
                // jTNotas.setValueAt(ina, i, 7);
                 
                       if(nota>5.0){
                               JOptionPane.showMessageDialog(this,"La nota no puede ser mayor a 5.0, recuerde que el rango de notas es de: 1.0 hasta 5.0 Intente de nuevo");
                        }else if(nota<1.0){
                               JOptionPane.showMessageDialog(this,"La nota no puede ser menor a 1.0, recuerde que el rango de notas es de: 1.0 hasta 5.0 Intente de nuevo");
                                           }
                                            else if(nota>=1.0 && nota<3.49){
                                                     String desemp= "BAJO";
                                                     jTNotas.setValueAt(desemp, i, 6);
                                                    }else if(nota>=3.50 && nota<=3.99){
                                                            String desemp= "BÁSICO";
                                                              jTNotas.setValueAt(desemp, i, 6);
                                                            }else if(nota>=4.0 && nota<=4.59){
                                                                    String desemp= "ALTO";
                                                                    jTNotas.setValueAt(desemp, i, 6);
                                                                }else if(nota>=4.60 && nota<=5.0){
                                                                      String desemp= "SUPERIOR";
                                                                      jTNotas.setValueAt(desemp, i, 6);
                                                                      }

                                                                      acum+=acum;
                             
                       } 
                    
         }
                 }catch(Exception e){
                 JOptionPane.showMessageDialog(this,"Errores al digitar. Verifique e intente de nuevo"+e);
                }
        }else if (periodo.equals("SEGUNDO")){
         try{
         if(jTNotas.getRowCount()>0){
            double acum=0;
            //int ina=0;
             for (int i = 0; i < jTNotas.getRowCount(); i++) {
                  
                  //acum=nota*0.20;
                 DecimalFormatSymbols simbolos= new  DecimalFormatSymbols();
                 simbolos.setDecimalSeparator('.');
                  DecimalFormat formateador= new DecimalFormat("0.00",simbolos);
                 double nota =Double.parseDouble(String.valueOf(jTNotas.getValueAt(i, 4)));
                 String total =formateador.format((nota*0.30));
                                 
                // jTNotas.setValueAt(nota_formato, i, 4);
                 jTNotas.setValueAt(total, i, 5);
                // jTNotas.setValueAt(ina, i, 7);
                 
                       if(nota>5.0){
                               JOptionPane.showMessageDialog(this,"La nota no puede ser mayor a 5.0, recuerde que el rango de notas es de: 1.0 hasta 5.0 Intente de nuevo");
                        }else if(nota<1.0){
                               JOptionPane.showMessageDialog(this,"La nota no puede ser menor a 1.0, recuerde que el rango de notas es de: 1.0 hasta 5.0 Intente de nuevo");
                                           }
                                            else if(nota>=1.0 && nota<3.49){
                                                     String desemp= "BAJO";
                                                     jTNotas.setValueAt(desemp, i, 6);
                                                    }else if(nota>=3.50 && nota<=3.99){
                                                            String desemp= "BÁSICO";
                                                              jTNotas.setValueAt(desemp, i, 6);
                                                            }else if(nota>=4.0 && nota<=4.59){
                                                                    String desemp= "ALTO";
                                                                    jTNotas.setValueAt(desemp, i, 6);
                                                                }else if(nota>=4.60 && nota<=5.0){
                                                                      String desemp= "SUPERIOR";
                                                                      jTNotas.setValueAt(desemp, i, 6);
                                                                      }

                                                                      acum+=acum;
                       }
                    }
                 }catch(Exception e){
                 JOptionPane.showMessageDialog(this,"Errores al digitar. Verifique e intente de nuevo"+e);
                }
        }
           
        else if (periodo.equals("TERCERO")||periodo.equals("CUARTO")){
         try{
         if(jTNotas.getRowCount()>0){
            double acum=0;
             //int ina=0;
             for (int i = 0; i < jTNotas.getRowCount(); i++) {
                  
                  //acum=nota*0.20;
                 DecimalFormatSymbols simbolos= new  DecimalFormatSymbols();
                 simbolos.setDecimalSeparator('.');
                  DecimalFormat formateador= new DecimalFormat("0.00",simbolos);
                 double nota =Double.parseDouble(String.valueOf(jTNotas.getValueAt(i, 4)));
                 String total =formateador.format((nota*0.25));
                                 
                // jTNotas.setValueAt(nota_formato, i, 4);
                 jTNotas.setValueAt(total, i, 5);
                // jTNotas.setValueAt(ina, i, 7);
                 
                       if(nota>5.0){
                               JOptionPane.showMessageDialog(this,"La nota no puede ser mayor a 5.0, recuerde que el rango de notas es de: 1.0 hasta 5.0 Intente de nuevo");
                        }else if(nota<1.0){
                               JOptionPane.showMessageDialog(this,"La nota no puede ser menor a 1.0, recuerde que el rango de notas es de: 1.0 hasta 5.0 Intente de nuevo");
                                           }
                                            else if(nota>=1.0 && nota<3.49){
                                                     String desemp= "BAJO";
                                                     jTNotas.setValueAt(desemp, i, 6);
                                                    }else if(nota>=3.50 && nota<=3.99){
                                                            String desemp= "BÁSICO";
                                                              jTNotas.setValueAt(desemp, i, 6);
                                                            }else if(nota>=4.0 && nota<=4.59){
                                                                    String desemp= "ALTO";
                                                                    jTNotas.setValueAt(desemp, i, 6);
                                                                }else if(nota>=4.60 && nota<=5.0){
                                                                      String desemp= "SUPERIOR";
                                                                      jTNotas.setValueAt(desemp, i, 6);
                                                                      }

                                                                      acum+=acum;
                       }
                      JOptionPane.showMessageDialog(this,"Los notas han sido calculadas por un valor de 25%");
                    }
                 }catch(Exception e){
                 JOptionPane.showMessageDialog(this,"Errores al digitar. Verifique e intente de nuevo"+e);
                }
        }
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jGradoComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jGradoComponentAdded

    }//GEN-LAST:event_jGradoComponentAdded

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Seleccion_periodo index= new Seleccion_periodo  ();
        index.setVisible(true);
        this.setVisible(false);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
            conexion cc=new conexion();
           Connection cn=cc.getConnection();

           String periodo= jPeriodo.getText().toString();
                String Area=JCArea.getSelectedItem().toString();
              
                if (periodo=="PRIMERO"){
             
              
               if(Area=="HUMANIDADES-CASTELLANO"){
                                if(jTNotas.getRowCount()>0){
                                for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                    
                               try{
                                    double acum=Double.parseDouble(String.valueOf(jTNotas.getValueAt(i, 5)));
                                     id=jTNotas.getValueAt(i,0).toString(); 
                                   
                                    PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Hum='"+jTNotas.getValueAt(i, 4).toString()
                                    +"',Acum_Hum='"+jTNotas.getValueAt(i,5).toString()
                                    +"',Desemp_Hum='"+jTNotas.getValueAt(i,6).toString()
                                    +"',Inasi_Hum='"+jTNotas.getValueAt(i, 7).toString()
                                    +"'WHERE Cod_estudiante='"+id+"'"
                                    + "ORDER BY Apellido_paterno");
                                    pst.executeUpdate();

                                              }catch(Exception e){
                                                     JOptionPane.showMessageDialog(null,"Error"+e);   
                                             }
                                             }
                                              
                                             }
                                 JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                   }  //END IF  
                          else if(Area=="CIENCIAS NATURALES"){
                                if(jTNotas.getRowCount()>0){
                                for (int i = 0; i < jTNotas.getRowCount(); i++) {
                               try{
                                    id=jTNotas.getValueAt(i,0).toString(); 
                                    //jTNotas.setValueAt(ina, i, 7);
                                    PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Natu='"+jTNotas.getValueAt(i, 4).toString()
                                    +"',Acum_Natu='"+jTNotas.getValueAt(i,5).toString()
                                    +"',Desemp_Natu='"+jTNotas.getValueAt(i,6).toString()
                                    +"',Inasi_Natu='"+jTNotas.getValueAt(i, 7).toString()
                                    +"'WHERE Cod_estudiante='"+id+"'"
                                    + "ORDER BY Apellido_paterno");
                                    pst.executeUpdate();

                                              }catch(Exception e){
                                                     JOptionPane.showMessageDialog(null,"Error"+e);   
                                             }
                                             }
                                               JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                             }
                                     } 
                            
                              else if(Area=="EDUCACIÓN ARTÍSTICA"){
                                             if(jTNotas.getRowCount()>0){
                                                 for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                try{
                                                     id=jTNotas.getValueAt(i,0).toString(); 
                                       //              jTNotas.setValueAt(ina, i, 7);
                                                     PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Arti='"+jTNotas.getValueAt(i, 4).toString()
                                                     +"',Acum_Arti='"+jTNotas.getValueAt(i,5).toString()
                                                     +"',Desemp_Arti='"+jTNotas.getValueAt(i,6).toString()
                                                     +"',Inasi_Arti='"+jTNotas.getValueAt(i, 7).toString()
                                                     +"'WHERE Cod_estudiante='"+id+"'"
                                                     + "ORDER BY Apellido_paterno");
                                                     pst.executeUpdate();

                                                               }catch(Exception e){
                                                                      JOptionPane.showMessageDialog(null,"Error"+e);   
                                                              }
                                                              }
                                                                JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                              }
                                                      } 
                                else if(Area=="EDUCACIÓN FÍSICA"){
                                                if(jTNotas.getRowCount()>0){
                                                 for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                  try{
                                                     id=jTNotas.getValueAt(i,0).toString(); 
                                         //            jTNotas.setValueAt(ina, i, 7);
                                                     PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Edu_Fisi='"+jTNotas.getValueAt(i, 4).toString()
                                                      +"',Acum_Edu_Fisi='"+jTNotas.getValueAt(i,5).toString()
                                                      +"',Desemp_Edu_Fisi='"+jTNotas.getValueAt(i,6).toString()
                                                      +"',Inasi_Edu_Fisi='"+jTNotas.getValueAt(i, 7).toString()
                                                      +"'WHERE Cod_estudiante='"+id+"'"
                                                      + "ORDER BY Apellido_paterno");
                                                      pst.executeUpdate();

                                                                       }catch(Exception e){
                                                                              JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                      }
                                                                      }
                                                                        JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                      }
                                                              }  
                                  else if(Area=="EDUCACIÓN RELIGIOSA"){
                                                 if(jTNotas.getRowCount()>0){
                                                     for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                    try{
                                                         id=jTNotas.getValueAt(i,0).toString(); 
                                           //              jTNotas.setValueAt(ina, i, 7);
                                                         PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Reli='"+jTNotas.getValueAt(i, 4).toString()
                                                         +"',Acum_Reli='"+jTNotas.getValueAt(i,5).toString()
                                                         +"',Desemp_Reli='"+jTNotas.getValueAt(i,6).toString()
                                                         +"',Inasi_Reli='"+jTNotas.getValueAt(i, 7).toString()
                                                         +"'WHERE Cod_estudiante='"+id+"'"
                                                         + "ORDER BY Apellido_paterno");
                                                         pst.executeUpdate();

                                                                   }catch(Exception e){
                                                                          JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                  }
                                                                  }
                                                                    JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                  }
                                                                                } 
                                  else if(Area=="EDUCACIÓN ÉTICA Y VALORES"){
                                                             if(jTNotas.getRowCount()>0){
                                                              for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                               try{
                                                                  id=jTNotas.getValueAt(i,0).toString(); 
                                             //                     jTNotas.setValueAt(ina, i, 7);
                                                                  PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Etica='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Etica='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Etica='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Etica='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_estudiante='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno");
                                                                   pst.executeUpdate();

                                                                                    }catch(Exception e){
                                                                                           JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                   }
                                                                                   }
                                                                                     JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                   }
                                                                           }  
                                    else if(Area=="INGLES"){
                                                     if(jTNotas.getRowCount()>0){
                                                         for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                        try{
                                                             id=jTNotas.getValueAt(i,0).toString(); 
                                               //              jTNotas.setValueAt(ina, i, 7);
                                                             PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Ingles='"+jTNotas.getValueAt(i, 4).toString()
                                                             +"',Acum_Ingles='"+jTNotas.getValueAt(i,5).toString()
                                                             +"',Desemp_Ingles='"+jTNotas.getValueAt(i,6).toString()
                                                             +"',Inasi_Ingles='"+jTNotas.getValueAt(i, 7).toString()
                                                             +"'WHERE Cod_estudiante='"+id+"'"
                                                             + "ORDER BY Apellido_paterno");
                                                             pst.executeUpdate();

                                                                       }catch(Exception e){
                                                                              JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                      }
                                                                      }
                                                                        JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                      else if(Area=="MATEMÁTICAS"){
                                                                      if(jTNotas.getRowCount()>0){
                                                                       for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                                        try{
                                                                           id=jTNotas.getValueAt(i,0).toString(); 
                                                 //                          jTNotas.setValueAt(ina, i, 7);
                                                                           PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Mate='"+jTNotas.getValueAt(i, 4).toString()
                                                                            +"',Acum_Mate='"+jTNotas.getValueAt(i,5).toString()
                                                                            +"',Desemp_Mate='"+jTNotas.getValueAt(i,6).toString()
                                                                            +"',Inasi_Mate='"+jTNotas.getValueAt(i, 7).toString()
                                                                            +"'WHERE Cod_estudiante='"+id+"'"
                                                                            + "ORDER BY Apellido_paterno");
                                                                            pst.executeUpdate();

                                                                                             }catch(Exception e){
                                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                            }
                                                                                            }
                                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                            }
                                                                                    }  
                                        else if(Area=="TECNOLOGÍA E INFORMÁTICA"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                   //                jTNotas.setValueAt(ina, i, 7);
                                                                   PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Infor='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Infor='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Infor='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Infor='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_estudiante='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                          else if(Area=="FILOSOFÍA"){
                                                            if(jTNotas.getRowCount()>0){
                                                             for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                 id=jTNotas.getValueAt(i,0).toString(); 
                                                     //            jTNotas.setValueAt(ina, i, 7);
                                                                 PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Filo='"+jTNotas.getValueAt(i, 4).toString()
                                                                  +"',Acum_Filo='"+jTNotas.getValueAt(i,5).toString()
                                                                  +"',Desemp_Filo='"+jTNotas.getValueAt(i,6).toString()
                                                                  +"',Inasi_Filo='"+jTNotas.getValueAt(i, 7).toString()
                                                                  +"'WHERE Cod_estudiante='"+id+"'"
                                                                  + "ORDER BY Apellido_paterno");
                                                                  pst.executeUpdate();

                                                                                   }catch(Exception e){
                                                                                          JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                  }
                                                                                  }
                                                                                    JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                  }
                                                                          }  
                                          else if(Area=="CÁTEDRA DE LA PAZ"){
                                                                      if(jTNotas.getRowCount()>0){
                                                                       for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                                        try{
                                                                           id=jTNotas.getValueAt(i,0).toString(); 
                                                       //                    jTNotas.setValueAt(ina, i, 7);
                                                                           PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Catpaz='"+jTNotas.getValueAt(i, 4).toString()
                                                                            +"',Acum_Catpaz='"+jTNotas.getValueAt(i,5).toString()
                                                                            +"',Desemp_Catpaz='"+jTNotas.getValueAt(i,6).toString()
                                                                            +"',Inasi_Catpaz='"+jTNotas.getValueAt(i,7).toString()
                                                                            +"'WHERE Cod_estudiante='"+id+"'"
                                                                            + "ORDER BY Apellido_paterno");
                                                                            pst.executeUpdate();

                                                                                             }catch(Exception e){
                                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                            }
                                                                                            }
                                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                            }
                                                                                    }  
                                        else if(Area=="ESTADÍSTICA"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                         //          jTNotas.setValueAt(ina, i, 7);
                                                                   PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Estad='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Estad='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Estad='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Estad='"+jTNotas.getValueAt(i,7).toString()
                                                                   +"'WHERE Cod_estudiante='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                          else if(Area=="QUÍMICA"){
                                                            if(jTNotas.getRowCount()>0){
                                                             for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                 id=jTNotas.getValueAt(i,0).toString(); 
                                                           //      jTNotas.setValueAt(ina, i, 7);
                                                                 PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Quim='"+jTNotas.getValueAt(i, 4).toString()
                                                                  +"',Acum_Quim='"+jTNotas.getValueAt(i,5).toString()
                                                                  +"',Desemp_Quim='"+jTNotas.getValueAt(i,6).toString()
                                                                  +"',Inasi_Quim='"+jTNotas.getValueAt(i, 7).toString()
                                                                  +"'WHERE Cod_estudiante='"+id+"'"
                                                                  + "ORDER BY Apellido_paterno");
                                                                  pst.executeUpdate();

                                                                                   }catch(Exception e){
                                                                                          JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                  }
                                                                                  }
                                                                                    JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                  }
                                                                          }
                                          else if(Area=="CIENCIAS POL. Y ECONÓMICAS"){
                                                                      if(jTNotas.getRowCount()>0){
                                                                       for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                                        try{
                                                                           id=jTNotas.getValueAt(i,0).toString(); 
                                                             //              jTNotas.setValueAt(ina, i, 7);
                                                                           PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Cpolit='"+jTNotas.getValueAt(i, 4).toString()
                                                                            +"',Acum_Cpolit='"+jTNotas.getValueAt(i,5).toString()
                                                                            +"',Desemp_Cpolit='"+jTNotas.getValueAt(i,6).toString()
                                                                            +"',Inasi_Cpolit='"+jTNotas.getValueAt(i, 7).toString()
                                                                            +"'WHERE Cod_estudiante='"+id+"'"
                                                                            + "ORDER BY Apellido_paterno");
                                                                            pst.executeUpdate();

                                                                                             }catch(Exception e){
                                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                            }
                                                                                            }
                                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                            }
                                                                                    }  
                                       
                                           else if(Area=="CIENCIAS SOCIALES"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                                 PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Soci='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Soci='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Soci='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Soci='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_estudiante='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                   
                                           else if(Area=="FÍSICA"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                                                                                                     PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Uno SET  Nota_Fisica='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Fisica='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Fisica='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Fisica='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_estudiante='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                           
                  else {
                 JOptionPane.showMessageDialog(null,"ERROR EN EL NOMBRE DE CAMPO");
               }
               }//END IF /////////////////////////////////////////////////////////////////7////////////////
               
               
               
 /////////////////////////////////////////////7/////////////////////////////////////////////////////////////////////7
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               else if(periodo=="SEGUNDO"){
               
               if(Area=="HUMANIDADES-CASTELLANO"){
                                if(jTNotas.getRowCount()>0){
                                for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                    
                               try{
                                    double acum=Double.parseDouble(String.valueOf(jTNotas.getValueAt(i, 5)));
                                     id=jTNotas.getValueAt(i,0).toString(); 
                                   
                                    PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Hum2='"+jTNotas.getValueAt(i, 4).toString()
                                    +"',Acum_Hum2='"+jTNotas.getValueAt(i,5).toString()
                                    +"',Desemp_Hum2='"+jTNotas.getValueAt(i,6).toString()
                                    +"',Inasi_Hum2='"+jTNotas.getValueAt(i, 7).toString()
                                    +"'WHERE Cod_Estudiantes='"+id+"'"
                                    + "ORDER BY Apellido_paternos");
                                    pst.executeUpdate();

                                              }catch(Exception e){
                                                     JOptionPane.showMessageDialog(null,"Error"+e);   
                                             }
                                             }
                                              
                                             }
                                 JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                   }  //END IF  
                          else if(Area=="CIENCIAS NATURALES"){
                                if(jTNotas.getRowCount()>0){
                                for (int i = 0; i < jTNotas.getRowCount(); i++) {
                               try{
                                    id=jTNotas.getValueAt(i,0).toString(); 
                                    //jTNotas.setValueAt(ina, i, 7);
                                    PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Natu2='"+jTNotas.getValueAt(i, 4).toString()
                                    +"',Acum_Natu2='"+jTNotas.getValueAt(i,5).toString()
                                    +"',Desemp_Natu2='"+jTNotas.getValueAt(i,6).toString()
                                    +"',Inasi_Natu2='"+jTNotas.getValueAt(i, 7).toString()
                                    +"'WHERE Cod_Estudiantes='"+id+"'"
                                    + "ORDER BY Apellido_paternos");
                                    pst.executeUpdate();

                                              }catch(Exception e){
                                                     JOptionPane.showMessageDialog(null,"Error"+e);   
                                             }
                                             }
                                               JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                             }
                                     } 
                            
                              else if(Area=="EDUCACIÓN ARTÍSTICA"){
                                             if(jTNotas.getRowCount()>0){
                                                 for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                try{
                                                     id=jTNotas.getValueAt(i,0).toString(); 
                                       //              jTNotas.setValueAt(ina, i, 7);
                                                     PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Arti2='"+jTNotas.getValueAt(i, 4).toString()
                                                     +"',Acum_Arti2='"+jTNotas.getValueAt(i,5).toString()
                                                     +"',Desemp_Arti2='"+jTNotas.getValueAt(i,6).toString()
                                                     +"',Inasi_Arti2='"+jTNotas.getValueAt(i, 7).toString()
                                                     +"'WHERE Cod_Estudiantes='"+id+"'"
                                                     + "ORDER BY Apellido_paternos");
                                                     pst.executeUpdate();

                                                               }catch(Exception e){
                                                                      JOptionPane.showMessageDialog(null,"Error"+e);   
                                                              }
                                                              }
                                                                JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                              }
                                                      } 
                                else if(Area=="EDUCACIÓN FÍSICA"){
                                                 if(jTNotas.getRowCount()>0){
                                                 for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                try{
                                                     id=jTNotas.getValueAt(i,0).toString(); 
                                       //              jTNotas.setValueAt(ina, i, 7);
                                                     PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Edu_Fisi2='"+jTNotas.getValueAt(i, 4).toString()
                                                     +"',Acum_Edu_Fisi2='"+jTNotas.getValueAt(i,5).toString()
                                                     +"',Desemp_Edu_Fisi2='"+jTNotas.getValueAt(i,6).toString()
                                                     +"',Inasi_Edu_Fisi2='"+jTNotas.getValueAt(i, 7).toString()
                                                     +"'WHERE Cod_Estudiantes='"+id+"'"
                                                     + "ORDER BY Apellido_paternos");
                                                     pst.executeUpdate();

                                                               }catch(Exception e){
                                                                      JOptionPane.showMessageDialog(null,"Error"+e);   
                                                              }
                                                              }
                                                                JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                              }
                                                              }  
                                  else if(Area=="EDUCACIÓN RELIGIOSA"){
                                                 if(jTNotas.getRowCount()>0){
                                                     for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                    try{
                                                         id=jTNotas.getValueAt(i,0).toString(); 
                                           //              jTNotas.setValueAt(ina, i, 7);
                                                         PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Reli2='"+jTNotas.getValueAt(i, 4).toString()
                                                         +"',Acum_Reli2='"+jTNotas.getValueAt(i,5).toString()
                                                         +"',Desemp_Reli2='"+jTNotas.getValueAt(i,6).toString()
                                                         +"',Inasi_Reli2='"+jTNotas.getValueAt(i, 7).toString()
                                                         +"'WHERE Cod_Estudiantes='"+id+"'"
                                                         + "ORDER BY Apellido_paternos");
                                                         pst.executeUpdate();

                                                                   }catch(Exception e){
                                                                          JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                  }
                                                                  }
                                                                    JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                  }
                                                                                } 
                                  else if(Area=="EDUCACIÓN ÉTICA Y VALORES"){
                                                             if(jTNotas.getRowCount()>0){
                                                              for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                               try{
                                                                  id=jTNotas.getValueAt(i,0).toString(); 
                                             //                     jTNotas.setValueAt(ina, i, 7);
                                                                  PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Etica2='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Etica2='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Etica2='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Etica2='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_Estudiantes='"+id+"'"
                                                                   + "ORDER BY Apellido_paternos");
                                                                   pst.executeUpdate();

                                                                                    }catch(Exception e){
                                                                                           JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                   }
                                                                                   }
                                                                                     JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                   }
                                                                           }  
                                    else if(Area=="INGLES"){
                                                     if(jTNotas.getRowCount()>0){
                                                         for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                        try{
                                                             id=jTNotas.getValueAt(i,0).toString(); 
                                               //              jTNotas.setValueAt(ina, i, 7);
                                                             PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Ingles2='"+jTNotas.getValueAt(i, 4).toString()
                                                             +"',Acum_Ingles2='"+jTNotas.getValueAt(i,5).toString()
                                                             +"',Desemp_Ingles2='"+jTNotas.getValueAt(i,6).toString()
                                                             +"',Inasi_Ingles2='"+jTNotas.getValueAt(i, 7).toString()
                                                             +"'WHERE Cod_Estudiantes='"+id+"'"
                                                             + "ORDER BY Apellido_paternos");
                                                             pst.executeUpdate();

                                                                       }catch(Exception e){
                                                                              JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                      }
                                                                      }
                                                                        JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                      else if(Area=="MATEMÁTICAS"){
                                                                      if(jTNotas.getRowCount()>0){
                                                         for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                        try{
                                                             id=jTNotas.getValueAt(i,0).toString(); 
                                               //              jTNotas.setValueAt(ina, i, 7);
                                                             PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Mate2='"+jTNotas.getValueAt(i, 4).toString()
                                                             +"',Acum_Mate2='"+jTNotas.getValueAt(i,5).toString()
                                                             +"',Desemp_Mate2='"+jTNotas.getValueAt(i,6).toString()
                                                             +"',Inasi_Mate2='"+jTNotas.getValueAt(i, 7).toString()
                                                             +"'WHERE Cod_Estudiantes='"+id+"'"
                                                             + "ORDER BY Apellido_paternos");
                                                             pst.executeUpdate();

                                                                       }catch(Exception e){
                                                                              JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                      }
                                                                      }
                                                                        JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                                    }  
                                        else if(Area=="TECNOLOGÍA E INFORMÁTICA"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                   //                jTNotas.setValueAt(ina, i, 7);
                                                                   PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Infor2='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Infor2='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Infor2='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Infor2='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_estudiantes='"+id+"'"
                                                                   + "ORDER BY Apellido_paternos");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                          else if(Area=="FILOSOFÍA"){
                                                            if(jTNotas.getRowCount()>0){
                                                             for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                 id=jTNotas.getValueAt(i,0).toString(); 
                                                     //            jTNotas.setValueAt(ina, i, 7);
                                                                 PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Filo2='"+jTNotas.getValueAt(i, 4).toString()
                                                                  +"',Acum_Filo2='"+jTNotas.getValueAt(i,5).toString()
                                                                  +"',Desemp_Filo2='"+jTNotas.getValueAt(i,6).toString()
                                                                  +"',Inasi_Filo2='"+jTNotas.getValueAt(i, 7).toString()
                                                                  +"'WHERE Cod_estudiantes='"+id+"'"
                                                                  + "ORDER BY Apellido_paternos");
                                                                  pst.executeUpdate();

                                                                                   }catch(Exception e){
                                                                                          JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                  }
                                                                                  }
                                                                                    JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                  }
                                                                          }  
                                          else if(Area=="CÁTEDRA DE LA PAZ"){
                                                                      if(jTNotas.getRowCount()>0){
                                                                       for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                                        try{
                                                                           id=jTNotas.getValueAt(i,0).toString(); 
                                                       //                    jTNotas.setValueAt(ina, i, 7);
                                                                           PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Catpaz2='"+jTNotas.getValueAt(i, 4).toString()
                                                                            +"',Acum_Catpaz2='"+jTNotas.getValueAt(i,5).toString()
                                                                            +"',Desemp_Catpaz2='"+jTNotas.getValueAt(i,6).toString()
                                                                            +"',Inasi_Catpaz2='"+jTNotas.getValueAt(i,7).toString()
                                                                            +"'WHERE Cod_Estudiantes='"+id+"'"
                                                                            + "ORDER BY Apellido_paternos");
                                                                            pst.executeUpdate();

                                                                                             }catch(Exception e){
                                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                            }
                                                                                            }
                                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                            }
                                                                                    }  
                                        else if(Area=="ESTADÍSTICA"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                         //          jTNotas.setValueAt(ina, i, 7);
                                                                   PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Estad2='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Estad2='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Estad2='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Estad2='"+jTNotas.getValueAt(i,7).toString()
                                                                   +"'WHERE Cod_Estudiantes='"+id+"'"
                                                                   + "ORDER BY Apellido_paternos");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                          else if(Area=="QUÍMICA"){
                                                            if(jTNotas.getRowCount()>0){
                                                             for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                 id=jTNotas.getValueAt(i,0).toString(); 
                                                           //      jTNotas.setValueAt(ina, i, 7);
                                                                 PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Quim2='"+jTNotas.getValueAt(i, 4).toString()
                                                                  +"',Acum_Quim2='"+jTNotas.getValueAt(i,5).toString()
                                                                  +"',Desemp_Quim2='"+jTNotas.getValueAt(i,6).toString()
                                                                  +"',Inasi_Quim2='"+jTNotas.getValueAt(i, 7).toString()
                                                                  +"'WHERE Cod_Estudiantes='"+id+"'"
                                                                  + "ORDER BY Apellido_paternos");
                                                                  pst.executeUpdate();

                                                                                   }catch(Exception e){
                                                                                          JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                  }
                                                                                  }
                                                                                    JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                  }
                                                                          }
                                          else if(Area=="CIENCIAS POL. Y ECONÓMICAS"){
                                                                      if(jTNotas.getRowCount()>0){
                                                                       for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                                        try{
                                                                           id=jTNotas.getValueAt(i,0).toString(); 
                                                             //              jTNotas.setValueAt(ina, i, 7);
                                                                           PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Cpolit2='"+jTNotas.getValueAt(i, 4).toString()
                                                                            +"',Acum_Cpolit2='"+jTNotas.getValueAt(i,5).toString()
                                                                            +"',Desemp_Cpolit2='"+jTNotas.getValueAt(i,6).toString()
                                                                            +"',Inasi_Cpolit2='"+jTNotas.getValueAt(i, 7).toString()
                                                                            +"'WHERE Cod_Estudiantes='"+id+"'"
                                                                            + "ORDER BY Apellido_paternos");
                                                                            pst.executeUpdate();

                                                                                             }catch(Exception e){
                                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                            }
                                                                                            }
                                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                            }
                                                                                    }  
                                       
                                           else if(Area=="CIENCIAS SOCIALES"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                                 PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Soci2='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Soci2='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Soci2='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Soci2='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_Estudiantes='"+id+"'"
                                                                   + "ORDER BY Apellido_paternos");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                   
                                           else if(Area=="FÍSICA"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                                    PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Dos SET  Nota_Fisica2='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Fisica2='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Fisica2='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Fisica2='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_Estudiantes='"+id+"'"
                                                                   + "ORDER BY Apellido_paternos");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                           
                  else {
                 JOptionPane.showMessageDialog(null,"ERROR EN EL NOMBRE DE CAMPO");
               }
                
               }
 ////////////////////////////////////////////////////////////////////////////               
                //////////////////////////////////////////// TERCER PERIODO/////////////////////////////////////////////
                 else if(periodo=="TERCERO"){
               
               if(Area=="HUMANIDADES-CASTELLANO"){
                                if(jTNotas.getRowCount()>0){
                                for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                    
                               try{
                                    double acum=Double.parseDouble(String.valueOf(jTNotas.getValueAt(i, 5)));
                                     id=jTNotas.getValueAt(i,0).toString(); 
                                   
                                    PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Hum3='"+jTNotas.getValueAt(i, 4).toString()
                                    +"',Acum_Hum3='"+jTNotas.getValueAt(i,5).toString()
                                    +"',Desemp_Hum3='"+jTNotas.getValueAt(i,6).toString()
                                    +"',Inasi_Hum3='"+jTNotas.getValueAt(i, 7).toString()
                                    +"'WHERE Cod_Estudiante3='"+id+"'"
                                    + "ORDER BY Apellido_paterno3");
                                    pst.executeUpdate();

                                              }catch(Exception e){
                                                     JOptionPane.showMessageDialog(null,"Error"+e);   
                                             }
                                             }
                                              
                                             }
                                 JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                   }  //END IF  
                          else if(Area=="CIENCIAS NATURALES"){
                                if(jTNotas.getRowCount()>0){
                                for (int i = 0; i < jTNotas.getRowCount(); i++) {
                               try{
                                    id=jTNotas.getValueAt(i,0).toString(); 
                                    //jTNotas.setValueAt(ina, i, 7);
                                    PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Natu3='"+jTNotas.getValueAt(i, 4).toString()
                                    +"',Acum_Natu3='"+jTNotas.getValueAt(i,5).toString()
                                    +"',Desemp_Natu3='"+jTNotas.getValueAt(i,6).toString()
                                    +"',Inasi_Natu3='"+jTNotas.getValueAt(i, 7).toString()
                                    +"'WHERE Cod_Estudiante3='"+id+"'"
                                    + "ORDER BY Apellido_paterno3");
                                    pst.executeUpdate();

                                              }catch(Exception e){
                                                     JOptionPane.showMessageDialog(null,"Error"+e);   
                                             }
                                             }
                                               JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                             }
                                     } 
                            
                              else if(Area=="EDUCACIÓN ARTÍSTICA"){
                                             if(jTNotas.getRowCount()>0){
                                                 for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                try{
                                                     id=jTNotas.getValueAt(i,0).toString(); 
                                       //              jTNotas.setValueAt(ina, i, 7);
                                                     PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Arti3='"+jTNotas.getValueAt(i, 4).toString()
                                                     +"',Acum_Arti3='"+jTNotas.getValueAt(i,5).toString()
                                                     +"',Desemp_Arti3='"+jTNotas.getValueAt(i,6).toString()
                                                     +"',Inasi_Arti3='"+jTNotas.getValueAt(i, 7).toString()
                                                     +"'WHERE Cod_Estudiante3='"+id+"'"
                                                     + "ORDER BY Apellido_paterno3");
                                                     pst.executeUpdate();

                                                               }catch(Exception e){
                                                                      JOptionPane.showMessageDialog(null,"Error"+e);   
                                                              }
                                                              }
                                                                JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                              }
                                                      } 
                                else if(Area=="EDUCACIÓN FÍSICA"){
                                                 if(jTNotas.getRowCount()>0){
                                                 for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                try{
                                                     id=jTNotas.getValueAt(i,0).toString(); 
                                       //              jTNotas.setValueAt(ina, i, 7);
                                                     PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Edu_Fisi3='"+jTNotas.getValueAt(i, 4).toString()
                                                     +"',Acum_Edu_Fisi3='"+jTNotas.getValueAt(i,5).toString()
                                                     +"',Desemp_Edu_Fisi3='"+jTNotas.getValueAt(i,6).toString()
                                                     +"',Inasi_Edu_Fisi3='"+jTNotas.getValueAt(i, 7).toString()
                                                     +"'WHERE Cod_Estudiante3='"+id+"'"
                                                     + "ORDER BY Apellido_paterno3");
                                                     pst.executeUpdate();

                                                               }catch(Exception e){
                                                                      JOptionPane.showMessageDialog(null,"Error"+e);   
                                                              }
                                                              }
                                                                JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                              }
                                                              }  
                                  else if(Area=="EDUCACIÓN RELIGIOSA"){
                                                 if(jTNotas.getRowCount()>0){
                                                     for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                    try{
                                                         id=jTNotas.getValueAt(i,0).toString(); 
                                           //              jTNotas.setValueAt(ina, i, 7);
                                                         PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Reli3='"+jTNotas.getValueAt(i, 4).toString()
                                                         +"',Acum_Reli3='"+jTNotas.getValueAt(i,5).toString()
                                                         +"',Desemp_Reli3='"+jTNotas.getValueAt(i,6).toString()
                                                         +"',Inasi_Reli3='"+jTNotas.getValueAt(i, 7).toString()
                                                         +"'WHERE Cod_Estudiante3='"+id+"'"
                                                         + "ORDER BY Apellido_paterno3");
                                                         pst.executeUpdate();

                                                                   }catch(Exception e){
                                                                          JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                  }
                                                                  }
                                                                    JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                  }
                                                                                } 
                                  else if(Area=="EDUCACIÓN ÉTICA Y VALORES"){
                                                             if(jTNotas.getRowCount()>0){
                                                              for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                               try{
                                                                  id=jTNotas.getValueAt(i,0).toString(); 
                                             //                     jTNotas.setValueAt(ina, i, 7);
                                                                  PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Etica3='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Etica3='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Etica3='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Etica3='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_Estudiante3='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno3");
                                                                   pst.executeUpdate();

                                                                                    }catch(Exception e){
                                                                                           JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                   }
                                                                                   }
                                                                                     JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                   }
                                                                           }  
                                    else if(Area=="INGLES"){
                                                     if(jTNotas.getRowCount()>0){
                                                         for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                        try{
                                                             id=jTNotas.getValueAt(i,0).toString(); 
                                               //              jTNotas.setValueAt(ina, i, 7);
                                                             PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Ingles3='"+jTNotas.getValueAt(i, 4).toString()
                                                             +"',Acum_Ingles3='"+jTNotas.getValueAt(i,5).toString()
                                                             +"',Desemp_Ingles3='"+jTNotas.getValueAt(i,6).toString()
                                                             +"',Inasi_Ingles3='"+jTNotas.getValueAt(i, 7).toString()
                                                             +"'WHERE Cod_Estudiante3='"+id+"'"
                                                             + "ORDER BY Apellido_paterno3");
                                                             pst.executeUpdate();

                                                                       }catch(Exception e){
                                                                              JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                      }
                                                                      }
                                                                        JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                      else if(Area=="MATEMÁTICAS"){
                                                                      if(jTNotas.getRowCount()>0){
                                                         for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                        try{
                                                             id=jTNotas.getValueAt(i,0).toString(); 
                                               //              jTNotas.setValueAt(ina, i, 7);
                                                             PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Mate3='"+jTNotas.getValueAt(i, 4).toString()
                                                             +"',Acum_Mate3='"+jTNotas.getValueAt(i,5).toString()
                                                             +"',Desemp_Mate3='"+jTNotas.getValueAt(i,6).toString()
                                                             +"',Inasi_Mate3='"+jTNotas.getValueAt(i, 7).toString()
                                                             +"'WHERE Cod_Estudiante3='"+id+"'"
                                                             + "ORDER BY Apellido_paterno3");
                                                             pst.executeUpdate();

                                                                       }catch(Exception e){
                                                                              JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                      }
                                                                      }
                                                                        JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                                    }  
                                        else if(Area=="TECNOLOGÍA E INFORMÁTICA"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                   //                jTNotas.setValueAt(ina, i, 7);
                                                                   PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Infor3='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Infor3='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Infor3='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Infor3='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_estudiante3='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno3");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                          else if(Area=="FILOSOFÍA"){
                                                            if(jTNotas.getRowCount()>0){
                                                             for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                 id=jTNotas.getValueAt(i,0).toString(); 
                                                     //            jTNotas.setValueAt(ina, i, 7);
                                                                 PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Filo3='"+jTNotas.getValueAt(i, 4).toString()
                                                                  +"',Acum_Filo3='"+jTNotas.getValueAt(i,5).toString()
                                                                  +"',Desemp_Filo3='"+jTNotas.getValueAt(i,6).toString()
                                                                  +"',Inasi_Filo3='"+jTNotas.getValueAt(i, 7).toString()
                                                                  +"'WHERE Cod_estudiante3='"+id+"'"
                                                                  + "ORDER BY Apellido_paterno3");
                                                                  pst.executeUpdate();

                                                                                   }catch(Exception e){
                                                                                          JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                  }
                                                                                  }
                                                                                    JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                  }
                                                                          }  
                                          else if(Area=="CÁTEDRA DE LA PAZ"){
                                                                      if(jTNotas.getRowCount()>0){
                                                                       for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                                        try{
                                                                           id=jTNotas.getValueAt(i,0).toString(); 
                                                       //                    jTNotas.setValueAt(ina, i, 7);
                                                                           PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Catpaz3='"+jTNotas.getValueAt(i, 4).toString()
                                                                            +"',Acum_Catpaz3='"+jTNotas.getValueAt(i,5).toString()
                                                                            +"',Desemp_Catpaz3='"+jTNotas.getValueAt(i,6).toString()
                                                                            +"',Inasi_Catpaz3='"+jTNotas.getValueAt(i,7).toString()
                                                                            +"'WHERE Cod_Estudiante3='"+id+"'"
                                                                            + "ORDER BY Apellido_paterno3");
                                                                            pst.executeUpdate();

                                                                                             }catch(Exception e){
                                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                            }
                                                                                            }
                                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                            }
                                                                                    }  
                                        else if(Area=="ESTADÍSTICA"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                         //          jTNotas.setValueAt(ina, i, 7);
                                                                   PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Estad3='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Estad3='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Estad3='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Estad3='"+jTNotas.getValueAt(i,7).toString()
                                                                   +"'WHERE Cod_Estudiante3='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno3");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                          else if(Area=="QUÍMICA"){
                                                            if(jTNotas.getRowCount()>0){
                                                             for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                 id=jTNotas.getValueAt(i,0).toString(); 
                                                           //      jTNotas.setValueAt(ina, i, 7);
                                                                 PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Quim3='"+jTNotas.getValueAt(i, 4).toString()
                                                                  +"',Acum_Quim3='"+jTNotas.getValueAt(i,5).toString()
                                                                  +"',Desemp_Quim3='"+jTNotas.getValueAt(i,6).toString()
                                                                  +"',Inasi_Quim3='"+jTNotas.getValueAt(i, 7).toString()
                                                                  +"'WHERE Cod_Estudiante3='"+id+"'"
                                                                  + "ORDER BY Apellido_paterno3");
                                                                  pst.executeUpdate();

                                                                                   }catch(Exception e){
                                                                                          JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                  }
                                                                                  }
                                                                                    JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                  }
                                                                          }
                                          else if(Area=="CIENCIAS POL. Y ECONÓMICAS"){
                                                                      if(jTNotas.getRowCount()>0){
                                                                       for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                                        try{
                                                                           id=jTNotas.getValueAt(i,0).toString(); 
                                                             //              jTNotas.setValueAt(ina, i, 7);
                                                                           PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Cpolit3='"+jTNotas.getValueAt(i, 4).toString()
                                                                            +"',Acum_Cpolit3='"+jTNotas.getValueAt(i,5).toString()
                                                                            +"',Desemp_Cpolit3='"+jTNotas.getValueAt(i,6).toString()
                                                                            +"',Inasi_Cpolit3='"+jTNotas.getValueAt(i, 7).toString()
                                                                            +"'WHERE Cod_Estudiante3='"+id+"'"
                                                                            + "ORDER BY Apellido_paterno3");
                                                                            pst.executeUpdate();

                                                                                             }catch(Exception e){
                                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                            }
                                                                                            }
                                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                            }
                                                                                    }  
                                       
                                           else if(Area=="CIENCIAS SOCIALES"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                                 PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Soci3='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Soci3='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Soci3='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Soci3='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_Estudiante3='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno3");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                   
                                           else if(Area=="FÍSICA"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                                    PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Tres SET  Nota_Fisica3='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Fisica3='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Fisica3='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Fisica3='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_Estudiante3='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno3");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                           
                  else {
                 JOptionPane.showMessageDialog(null,"ERROR EN EL NOMBRE DE CAMPO");
               }
                
               }
//////////////////////////////////////////////////////////////////////////CUARTO /////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////PERIODO/////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                 
                 
                 
                 
                 
                 
                 else if(periodo=="CUARTO"){
                    if(Area=="HUMANIDADES-CASTELLANO"){
                                if(jTNotas.getRowCount()>0){
                                for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                    
                               try{
                                    double acum=Double.parseDouble(String.valueOf(jTNotas.getValueAt(i, 5)));
                                     id=jTNotas.getValueAt(i,0).toString(); 
                                   
                                    PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Hum4='"+jTNotas.getValueAt(i, 4).toString()
                                    +"',Acum_Hum4='"+jTNotas.getValueAt(i,5).toString()
                                    +"',Desemp_Hum4='"+jTNotas.getValueAt(i,6).toString()
                                    +"',Inasi_Hum4='"+jTNotas.getValueAt(i, 7).toString()
                                    +"'WHERE Cod_Estudiante4='"+id+"'"
                                    + "ORDER BY Apellido_paterno4");
                                    pst.executeUpdate();

                                              }catch(Exception e){
                                                     JOptionPane.showMessageDialog(null,"Error"+e);   
                                             }
                                             }
                                              
                                             }
                                 JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                   }  //END IF  
                          else if(Area=="CIENCIAS NATURALES"){
                                if(jTNotas.getRowCount()>0){
                                for (int i = 0; i < jTNotas.getRowCount(); i++) {
                               try{
                                    id=jTNotas.getValueAt(i,0).toString(); 
                                    //jTNotas.setValueAt(ina, i, 7);
                                    PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Natu4='"+jTNotas.getValueAt(i, 4).toString()
                                    +"',Acum_Natu4='"+jTNotas.getValueAt(i,5).toString()
                                    +"',Desemp_Natu4='"+jTNotas.getValueAt(i,6).toString()
                                    +"',Inasi_Natu4='"+jTNotas.getValueAt(i, 7).toString()
                                    +"'WHERE Cod_Estudiante4='"+id+"'"
                                    + "ORDER BY Apellido_paterno4");
                                    pst.executeUpdate();

                                              }catch(Exception e){
                                                     JOptionPane.showMessageDialog(null,"Error"+e);   
                                             }
                                             }
                                               JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                             }
                                     } 
                            
                              else if(Area=="EDUCACIÓN ARTÍSTICA"){
                                             if(jTNotas.getRowCount()>0){
                                                 for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                try{
                                                     id=jTNotas.getValueAt(i,0).toString(); 
                                       //              jTNotas.setValueAt(ina, i, 7);
                                                     PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Arti4='"+jTNotas.getValueAt(i, 4).toString()
                                                     +"',Acum_Arti4='"+jTNotas.getValueAt(i,5).toString()
                                                     +"',Desemp_Arti4='"+jTNotas.getValueAt(i,6).toString()
                                                     +"',Inasi_Arti4='"+jTNotas.getValueAt(i, 7).toString()
                                                     +"'WHERE Cod_Estudiante4='"+id+"'"
                                                     + "ORDER BY Apellido_paterno4");
                                                     pst.executeUpdate();

                                                               }catch(Exception e){
                                                                      JOptionPane.showMessageDialog(null,"Error"+e);   
                                                              }
                                                              }
                                                                JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                              }
                                                      } 
                                else if(Area=="EDUCACIÓN FÍSICA"){
                                                 if(jTNotas.getRowCount()>0){
                                                 for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                try{
                                                     id=jTNotas.getValueAt(i,0).toString(); 
                                       //              jTNotas.setValueAt(ina, i, 7);
                                                     PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Edu_Fisi4='"+jTNotas.getValueAt(i, 4).toString()
                                                     +"',Acum_Edu_Fisi4='"+jTNotas.getValueAt(i,5).toString()
                                                     +"',Desemp_Edu_Fisi4='"+jTNotas.getValueAt(i,6).toString()
                                                     +"',Inasi_Edu_Fisi4='"+jTNotas.getValueAt(i, 7).toString()
                                                     +"'WHERE Cod_Estudiante4='"+id+"'"
                                                     + "ORDER BY Apellido_paterno4");
                                                     pst.executeUpdate();

                                                               }catch(Exception e){
                                                                      JOptionPane.showMessageDialog(null,"Error"+e);   
                                                              }
                                                              }
                                                                JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                              }
                                                              }  
                                  else if(Area=="EDUCACIÓN RELIGIOSA"){
                                                 if(jTNotas.getRowCount()>0){
                                                     for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                    try{
                                                         id=jTNotas.getValueAt(i,0).toString(); 
                                           //              jTNotas.setValueAt(ina, i, 7);
                                                         PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Reli4='"+jTNotas.getValueAt(i, 4).toString()
                                                         +"',Acum_Reli4='"+jTNotas.getValueAt(i,5).toString()
                                                         +"',Desemp_Reli4='"+jTNotas.getValueAt(i,6).toString()
                                                         +"',Inasi_Reli4='"+jTNotas.getValueAt(i, 7).toString()
                                                         +"'WHERE Cod_Estudiante4='"+id+"'"
                                                         + "ORDER BY Apellido_paterno4");
                                                         pst.executeUpdate();

                                                                   }catch(Exception e){
                                                                          JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                  }
                                                                  }
                                                                    JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                  }
                                                                                } 
                                  else if(Area=="EDUCACIÓN ÉTICA Y VALORES"){
                                                             if(jTNotas.getRowCount()>0){
                                                              for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                               try{
                                                                  id=jTNotas.getValueAt(i,0).toString(); 
                                             //                     jTNotas.setValueAt(ina, i, 7);
                                                                  PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET Nota_Etica4='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Etica4='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Etica4='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Etica4='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_Estudiante4='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno4");
                                                                   pst.executeUpdate();

                                                                                    }catch(Exception e){
                                                                                           JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                   }
                                                                                   }
                                                                                     JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                   }
                                                                           }  
                                    else if(Area=="INGLES"){
                                                     if(jTNotas.getRowCount()>0){
                                                         for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                        try{
                                                             id=jTNotas.getValueAt(i,0).toString(); 
                                               //              jTNotas.setValueAt(ina, i, 7);
                                                             PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Ingles4='"+jTNotas.getValueAt(i, 4).toString()
                                                             +"',Acum_Ingles4='"+jTNotas.getValueAt(i,5).toString()
                                                             +"',Desemp_Ingles4='"+jTNotas.getValueAt(i,6).toString()
                                                             +"',Inasi_Ingles4='"+jTNotas.getValueAt(i, 7).toString()
                                                             +"'WHERE Cod_Estudiante4='"+id+"'"
                                                             + "ORDER BY Apellido_paterno4");
                                                             pst.executeUpdate();

                                                                       }catch(Exception e){
                                                                              JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                      }
                                                                      }
                                                                        JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                      else if(Area=="MATEMÁTICAS"){
                                                                      if(jTNotas.getRowCount()>0){
                                                         for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                        try{
                                                             id=jTNotas.getValueAt(i,0).toString(); 
                                               //              jTNotas.setValueAt(ina, i, 7);
                                                             PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Mate4='"+jTNotas.getValueAt(i, 4).toString()
                                                             +"',Acum_Mate4='"+jTNotas.getValueAt(i,5).toString()
                                                             +"',Desemp_Mate4='"+jTNotas.getValueAt(i,6).toString()
                                                             +"',Inasi_Mate4='"+jTNotas.getValueAt(i, 7).toString()
                                                             +"'WHERE Cod_Estudiante4='"+id+"'"
                                                             + "ORDER BY Apellido_paterno4");
                                                             pst.executeUpdate();

                                                                       }catch(Exception e){
                                                                              JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                      }
                                                                      }
                                                                        JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                                    }  
                                        else if(Area=="TECNOLOGÍA E INFORMÁTICA"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                   //                jTNotas.setValueAt(ina, i, 7);
                                                                   PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Infor4='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Infor4='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Infor4='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Infor4='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_estudiante4='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno4");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                          else if(Area=="FILOSOFÍA"){
                                                            if(jTNotas.getRowCount()>0){
                                                             for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                 id=jTNotas.getValueAt(i,0).toString(); 
                                                     //            jTNotas.setValueAt(ina, i, 7);
                                                                 PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Filo4='"+jTNotas.getValueAt(i, 4).toString()
                                                                  +"',Acum_Filo4='"+jTNotas.getValueAt(i,5).toString()
                                                                  +"',Desemp_Filo4='"+jTNotas.getValueAt(i,6).toString()
                                                                  +"',Inasi_Filo4='"+jTNotas.getValueAt(i, 7).toString()
                                                                  +"'WHERE Cod_estudiante4='"+id+"'"
                                                                  + "ORDER BY Apellido_paterno4");
                                                                  pst.executeUpdate();

                                                                                   }catch(Exception e){
                                                                                          JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                  }
                                                                                  }
                                                                                    JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                  }
                                                                          }  
                                          else if(Area=="CÁTEDRA DE LA PAZ"){
                                                                      if(jTNotas.getRowCount()>0){
                                                                       for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                                        try{
                                                                           id=jTNotas.getValueAt(i,0).toString(); 
                                                       //                    jTNotas.setValueAt(ina, i, 7);
                                                                           PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Catpaz4='"+jTNotas.getValueAt(i, 4).toString()
                                                                            +"',Acum_Catpaz4='"+jTNotas.getValueAt(i,5).toString()
                                                                            +"',Desemp_Catpaz4='"+jTNotas.getValueAt(i,6).toString()
                                                                            +"',Inasi_Catpaz4='"+jTNotas.getValueAt(i,7).toString()
                                                                            +"'WHERE Cod_Estudiante4='"+id+"'"
                                                                            + "ORDER BY Apellido_paterno4");
                                                                            pst.executeUpdate();

                                                                                             }catch(Exception e){
                                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                            }
                                                                                            }
                                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                            }
                                                                                    }  
                                        else if(Area=="ESTADÍSTICA"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                         //          jTNotas.setValueAt(ina, i, 7);
                                                                   PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Estad4='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Estad4='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Estad4='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Estad4='"+jTNotas.getValueAt(i,7).toString()
                                                                   +"'WHERE Cod_Estudiante4='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno4");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                          else if(Area=="QUÍMICA"){
                                                            if(jTNotas.getRowCount()>0){
                                                             for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                 id=jTNotas.getValueAt(i,0).toString(); 
                                                           //      jTNotas.setValueAt(ina, i, 7);
                                                                 PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Quim4='"+jTNotas.getValueAt(i, 4).toString()
                                                                  +"',Acum_Quim4='"+jTNotas.getValueAt(i,5).toString()
                                                                  +"',Desemp_Quim4='"+jTNotas.getValueAt(i,6).toString()
                                                                  +"',Inasi_Quim4='"+jTNotas.getValueAt(i, 7).toString()
                                                                  +"'WHERE Cod_Estudiante4='"+id+"'"
                                                                  + "ORDER BY Apellido_paterno4");
                                                                  pst.executeUpdate();

                                                                                   }catch(Exception e){
                                                                                          JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                  }
                                                                                  }
                                                                                    JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                  }
                                                                          }
                                          else if(Area=="CIENCIAS POL. Y ECONÓMICAS"){
                                                                      if(jTNotas.getRowCount()>0){
                                                                       for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                                        try{
                                                                           id=jTNotas.getValueAt(i,0).toString(); 
                                                             //              jTNotas.setValueAt(ina, i, 7);
                                                                           PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Cpolit4='"+jTNotas.getValueAt(i, 4).toString()
                                                                            +"',Acum_Cpolit4='"+jTNotas.getValueAt(i,5).toString()
                                                                            +"',Desemp_Cpolit4='"+jTNotas.getValueAt(i,6).toString()
                                                                            +"',Inasi_Cpolit4='"+jTNotas.getValueAt(i, 7).toString()
                                                                            +"'WHERE Cod_Estudiante4='"+id+"'"
                                                                            + "ORDER BY Apellido_paterno4");
                                                                            pst.executeUpdate();

                                                                                             }catch(Exception e){
                                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                                            }
                                                                                            }
                                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                                            }
                                                                                    }  
                                       
                                           else if(Area=="CIENCIAS SOCIALES"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                                 PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Soci4='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Soci4='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Soci4='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Soci4='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_Estudiante4='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno4");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                   
                                           else if(Area=="FÍSICA"){
                                                           if(jTNotas.getRowCount()>0){
                                                               for (int i = 0; i < jTNotas.getRowCount(); i++) {
                                                              try{
                                                                   id=jTNotas.getValueAt(i,0).toString(); 
                                                                    PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Nota_Fisica4='"+jTNotas.getValueAt(i, 4).toString()
                                                                   +"',Acum_Fisica4='"+jTNotas.getValueAt(i,5).toString()
                                                                   +"',Desemp_Fisica4='"+jTNotas.getValueAt(i,6).toString()
                                                                   +"',Inasi_Fisica4='"+jTNotas.getValueAt(i, 7).toString()
                                                                   +"'WHERE Cod_Estudiante4='"+id+"'"
                                                                   + "ORDER BY Apellido_paterno4");
                                                                   pst.executeUpdate();

                                                                             }catch(Exception e){
                                                                                    JOptionPane.showMessageDialog(null,"Error"+e);   
                                                                            }
                                                                            }
                                                                              JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                                                                            }
                                                                    } 
                                           
                  else {
                 JOptionPane.showMessageDialog(null,"ERROR EN EL NOMBRE DE CAMPO");
               }
               }
               
               
    }//GEN-LAST:event_jButton6ActionPerformed

    private void JCAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCAreaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCAreaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir del sistema?");
        if(JOptionPane.OK_OPTION==confirmar) {

            try{
                System.exit(0);
            } catch (Exception e){
            }
        }else{
            this.setVisible(true);
        }
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
            mostrardatos_visualizar(jGrado.getText(),jAno.getText());
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         conexion cc=new conexion();
        Connection cn=cc.getConnection();
        String periodo=jPeriodo.getText().toString();
        if(periodo=="PRIMERO"){
            mostrardatos(jGrado.getText(),jAno.getText());

            if(jTNotas.getRowCount()>0){
               
                    try {
                         for (int i = 0; i < jTNotas.getRowCount(); i++) {
                        PreparedStatement pst= cn.prepareStatement("INSERT INTO Periodo_Uno(Cod_Estudiante,Apellido_paterno,Apellido_materno,Nombres,Grado,Ano,Periodo)VALUES (?,?,?,?,?,?,?)");
                        pst.setString(1,jTNotas.getValueAt(i, 0).toString());
                        pst.setString(2,jTNotas.getValueAt(i, 1).toString());
                        pst.setString(3,jTNotas.getValueAt(i, 2).toString());
                        pst.setString(4,jTNotas.getValueAt(i, 3).toString());
                        pst.setString(5,jGrado.getText());
                        pst.setString(6,jAno.getText());
                        pst.setString(7,jPeriodo.getText());
                        pst.executeUpdate();

                        // st.execute("INSERT INTO Periodo_Uno VALUES ("+jTNotas.getValueAt(i,0)
                            //  +","+jTNotas.getValueAt(i,1)+","+jTNotas.getValueAt(i,2)+","+jTNotas.getValueAt(i,3)+","+jTNotas.getValueAt(i,4)+","+jTNotas.getValueAt(i,5)+","+jTNotas.getValueAt(i,6)+")");
                    }
                    }catch (SQLException ex) {
                        Logger.getLogger(INGRESAR_NOTAS.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null,"Los datos ya han sido cargados a la Base de Datos, seleccione nuevamente el grado, año y periodo a digitar");
                    }

                     JOptionPane.showMessageDialog(this,"Los datos han sido cargados ");
            }else{
                JOptionPane.showMessageDialog(this,"La tabla se encuentra vacía");
            }
        }

        /////////////////////////////////////////////////////////////////////////
        //// END IF////////////////////////

        else if(periodo=="SEGUNDO"){
            mostrardatos_segundo(jGrado.getText(),jAno.getText());
            if(jTNotas.getRowCount()>0){
               
                    try {
                         for (int i = 0; i < jTNotas.getRowCount(); i++) {
                        PreparedStatement pst= cn.prepareStatement("INSERT INTO Periodo_Dos(Cod_Estudiantes,Apellido_paternos,Apellido_maternos,Nombre,Grados,Anos,Periodo2)VALUES (?,?,?,?,?,?,?)");
                        pst.setString(1,jTNotas.getValueAt(i, 0).toString());
                        pst.setString(2,jTNotas.getValueAt(i, 1).toString());
                        pst.setString(3,jTNotas.getValueAt(i, 2).toString());
                        pst.setString(4,jTNotas.getValueAt(i, 3).toString());
                        pst.setString(5,jGrado.getText());
                        pst.setString(6,jAno.getText());
                        pst.setString(7,jPeriodo.getText());
                        pst.executeUpdate();

                        // st.execute("INSERT INTO Periodo_Uno VALUES ("+jTNotas.getValueAt(i,0)
                            //  +","+jTNotas.getValueAt(i,1)+","+jTNotas.getValueAt(i,2)+","+jTNotas.getValueAt(i,3)+","+jTNotas.getValueAt(i,4)+","+jTNotas.getValueAt(i,5)+","+jTNotas.getValueAt(i,6)+")");
                    }
                    }catch (SQLException ex) {
                        Logger.getLogger(INGRESAR_NOTAS.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null,"Los datos ya han sido cargados a la Base de Datos, seleccione nuevamente el grado, año y periodo a digitar");
                    }
                JOptionPane.showMessageDialog(this,"Los datos han sido cargados");
            }else{
                JOptionPane.showMessageDialog(this,"La tabla se encuentra vacía");
            }
        }
        
 /////////////////////////////////////////////////// TERCER PERIODO///////////////////////////////////////////////////////       
         if(periodo=="TERCERO"){
             mostrardatos_tercero(jGrado.getText(),jAno.getText());

            if(jTNotas.getRowCount()>0){
                
                    try {
                        for (int i = 0; i < jTNotas.getRowCount(); i++) {
                        PreparedStatement pst= cn.prepareStatement("INSERT INTO Periodo_Tres(Cod_Estudiante3,Apellido_paterno3,Apellido_materno3,Nombre3,Grado3,Ano3,Periodo3)VALUES (?,?,?,?,?,?,?)");
                        pst.setString(1,jTNotas.getValueAt(i, 0).toString());
                        pst.setString(2,jTNotas.getValueAt(i, 1).toString());
                        pst.setString(3,jTNotas.getValueAt(i, 2).toString());
                        pst.setString(4,jTNotas.getValueAt(i, 3).toString());
                        pst.setString(5,jGrado.getText());
                        pst.setString(6,jAno.getText());
                        pst.setString(7,jPeriodo.getText());
                        pst.executeUpdate();

                        // st.execute("INSERT INTO Periodo_Uno VALUES ("+jTNotas.getValueAt(i,0)
                            //  +","+jTNotas.getValueAt(i,1)+","+jTNotas.getValueAt(i,2)+","+jTNotas.getValueAt(i,3)+","+jTNotas.getValueAt(i,4)+","+jTNotas.getValueAt(i,5)+","+jTNotas.getValueAt(i,6)+")");
                       } 
                    }catch (SQLException ex) {
                        Logger.getLogger(INGRESAR_NOTAS.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null,"Los datos ya han sido cargados a la Base de Datos, seleccione nuevamente el grado, año y periodo a digitar");
                    }
               JOptionPane.showMessageDialog(this,"Los datos han sido cargados");
                
             }else{
                JOptionPane.showMessageDialog(this,"La tabla se encuentra vacía");
            }
        }
         ///////////////////////////////////////////////////////////////// cuarto
           if(periodo=="CUARTO"){
            mostrardatos(jGrado.getText(),jAno.getText());

            if(jTNotas.getRowCount()>0){
               
                    try {
                         for (int i = 0; i < jTNotas.getRowCount(); i++) {
                        PreparedStatement pst= cn.prepareStatement("INSERT INTO Periodo_Cuatro(Cod_Estudiante4,Apellido_paterno4,Apellido_materno4,Nombre4,Grado4,Ano4,Periodo4)VALUES (?,?,?,?,?,?,?)");
                        pst.setString(1,jTNotas.getValueAt(i, 0).toString());
                        pst.setString(2,jTNotas.getValueAt(i, 1).toString());
                        pst.setString(3,jTNotas.getValueAt(i, 2).toString());
                        pst.setString(4,jTNotas.getValueAt(i, 3).toString());
                        pst.setString(5,jGrado.getText());
                        pst.setString(6,jAno.getText());
                        pst.setString(7,jPeriodo.getText());
                        pst.executeUpdate();

                        // st.execute("INSERT INTO Periodo_Uno VALUES ("+jTNotas.getValueAt(i,0)
                            //  +","+jTNotas.getValueAt(i,1)+","+jTNotas.getValueAt(i,2)+","+jTNotas.getValueAt(i,3)+","+jTNotas.getValueAt(i,4)+","+jTNotas.getValueAt(i,5)+","+jTNotas.getValueAt(i,6)+")");
                    }
                    }catch (SQLException ex) {
                        Logger.getLogger(INGRESAR_NOTAS.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null,"Los datos ya han sido cargados a la Base de Datos, seleccione nuevamente el grado, año y periodo a digitar");
                    }

                     JOptionPane.showMessageDialog(this,"Los datos han sido cargados ");
            }else{
                JOptionPane.showMessageDialog(this,"La tabla se encuentra vacía");
            }
        }

         
         
         
         
 
    }//GEN-LAST:event_jButton2ActionPerformed
                                
    /**
     * @param args the command line arguments
     */
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> JCArea;
    public static javax.swing.JLabel jAno;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    public static javax.swing.JLabel jGrado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JLabel jPeriodo;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTNotas;
    // End of variables declaration//GEN-END:variables
}
