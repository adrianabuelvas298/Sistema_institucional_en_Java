/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csr_modulo_notas;

import csr_modulo_notas.Pagina_principal;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author adriana
 */
public class Valores extends javax.swing.JFrame {

    /**
     * Creates new form Imprimir_listados
     */
     public String id="";
    public Valores() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("INSERTAR VALORES DE MATRICULA");
        jTableEst.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
              if(jTableEst.getSelectedRow()!=-1){
                int fila =jTableEst.getSelectedRow();
                // id = jTableEst.getValueAt(fila,0).toString();
                 Reg.setText(jTableEst.getValueAt(fila,0).toString());
                 jCAno.setSelectedItem(jTableEst.getValueAt(fila,6));
                 txtEstado.setText(jTableEst.getValueAt(fila,7).toString());
                 LbApellido1.setText(jTableEst.getValueAt(fila,2).toString());
                 LbAPellido2.setText(jTableEst.getValueAt(fila,3).toString());
                 LbNombres.setText(jTableEst.getValueAt(fila,4).toString());
                 
          }
         }
        }); 
        
        
        
    }
     public  void limpiar(){
                txtEstado.setText("");
                Reg.setText("");
                } 
   
         public void mostrardatos(String valor, String valores){
         conexion cc=new conexion();
          Connection cn=cc.getConnection();
          DefaultTableModel modelo=new DefaultTableModel();
          modelo.addColumn("N°");
          modelo.addColumn("COD");
          modelo.addColumn("PRIMER APELLIDO");
          modelo.addColumn("SEGUNDO APELLIDO");
          modelo.addColumn("NOMBRES");
          modelo.addColumn("GRADO"); 
          modelo.addColumn("AÑO");
          modelo.addColumn("ESTADO");
          
         
        jTableEst.setModel(modelo);
        String sql="";
        if (valor.equals(""))
        {
            sql="SELECT * FROM Periodo_Cuatro";
        }
        else{
            sql="SELECT * FROM Periodo_Cuatro WHERE (Cod_Estudiante4='"+valor+"'  AND Ano4='"+valores+"')";
        }  
        
        String []datos=new String [9];
        try{
                Statement st=cn.createStatement();
                ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
            
            datos[0]=rs.getString(73);   
            datos[1]=rs.getString(1); 
            datos[2]=rs.getString(2); 
            datos[3]=rs.getString(3);
            datos[4]=rs.getString(4);
            datos[5]=rs.getString(5);
            datos[6]=rs.getString(6);
            datos[7]=rs.getString(72);
                  
            
            modelo.addRow(datos);
           
            }
            
            
             
             
             modelo.addTableModelListener(new TableModelListener(){
                @Override
                @SuppressWarnings("empty-statement")
                public void tableChanged(TableModelEvent e) {
                    if(e.getType()==TableModelEvent.UPDATE){
                     int  columna= e.getColumn();
                     int  fila=e.getFirstRow();
                     if (columna==1){
                            String  sql= "UPDATE Periodo_Cuatro SET Estado='"+jTableEst.getValueAt(fila, columna)+"'WHERE  idPeriodo'"+jTableEst.getValueAt(fila,0)+"'";
                          
                        
                         try {
                             if(st.execute(sql)){
                                
                                         } else {                     
                             }
                         } catch (SQLException ex) {
                             Logger.getLogger(Valores.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     
                    }
                   
                }
             
             
             
                }
                     });
              jTableEst.setModel(modelo); 
             
        }catch(SQLException ex){
            Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
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
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jCAno = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        LbAPellido2 = new javax.swing.JLabel();
        LbApellido1 = new javax.swing.JLabel();
        LbNombres = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEst = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Reg = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jPanel1.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 129, 45)), "CAMBIAR ESTADO ", 3, 0, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(1, 0, 255))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/home-icon.png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue), "SELECCIONE AÑO Y GRADO", 0, 0, new java.awt.Font("Dialog", 0, 12), java.awt.Color.blue)); // NOI18N
        jPanel3.setForeground(java.awt.Color.blue);

        jLabel4.setText("AÑO:");

        jCAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2024", "2025", "2026", "2027" }));
        jCAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCAnoActionPerformed(evt);
            }
        });

        jLabel3.setText("INSERTAR NUEVO ESTADO:");

        txtEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoActionPerformed(evt);
            }
        });

        jLabel2.setText("PRIMER APELLIDO:");

        jLabel5.setText("PRIMER APELLIDO:");

        jLabel8.setText("NOMBRES:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(36, 36, 36)
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(jLabel6))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LbApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LbAPellido2, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LbNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCAno, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LbApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LbAPellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LbNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addComponent(txtEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jCAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTableEst.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableEst);

        txtBuscar.setBackground(new java.awt.Color(0, 175, 255));
        txtBuscar.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        txtBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtBuscarMouseEntered(evt);
            }
        });
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        jButton4.setText("BUSCAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("N°");

        jButton6.setText("ACTUALIZAR ESTADO DEL ESTUDIANTE");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Reg, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(27, 27, 27)))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtBuscar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(Reg))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", 0, 0, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(0, 26, 255))); // NOI18N

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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCAnoActionPerformed
     
    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        Pagina_principal inicio=new Pagina_principal();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
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

    private void txtBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseEntered

    }//GEN-LAST:event_txtBuscarMouseEntered

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        char cTeclaPresionada = evt.getKeyChar();
        //Da click al boton elegido
        if(cTeclaPresionada==KeyEvent.VK_ENTER){
            if (txtBuscar.getText()==null){
                JOptionPane.showMessageDialog(null,"¡Ingrese un valor válido para realizar la búsqueda!","Error, ¡intente de nuevo!",JOptionPane.ERROR_MESSAGE);
            }
            else{
           mostrardatos(txtBuscar.getText(),jCAno.getSelectedItem().toString());
            }
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (txtBuscar.getText()==null){
            JOptionPane.showMessageDialog(null,"¡Ingrese un valor válido para realizar la búsqueda!","Error, ¡intente de nuevo!",JOptionPane.ERROR_MESSAGE);
        }
        else{
            mostrardatos(txtBuscar.getText(),jCAno.getSelectedItem().toString());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if(Reg.getText()==""){
            JOptionPane.showMessageDialog(null,"¡No se ha seleccionado registro para actualizar!Por favor,  seleccionelo e intente de nuevo","Error",JOptionPane.ERROR_MESSAGE);
        }

        else{

            conexion cc=new conexion();
            Connection cn=cc.getConnection();

            try{
                id=Reg.getText();
                PreparedStatement pst=cn.prepareStatement("UPDATE Periodo_Cuatro SET  Estado='"+txtEstado.getText()
                   +"' WHERE idPeriodo='"+id+"'");

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"El estado ha sido cambiado");
                limpiar();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"aqui"+e);
                //System.out.print(e.getMessage());
            }
            limpiar();
            mostrardatos(txtBuscar.getText(),jCAno.getSelectedItem().toString());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LbAPellido2;
    private javax.swing.JLabel LbApellido1;
    private javax.swing.JLabel LbNombres;
    private javax.swing.JLabel Reg;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jCAno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEst;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtEstado;
    // End of variables declaration//GEN-END:variables
}
