/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csr_modulo_notas;

/**
 *
 * @author adriana
 */
public class Estudiante {
    String  Apellido1,Apellido2, nombres,id,acudiente,celular;

    public Estudiante(String Apellido1, String Apellido2, String nombres, String id, String acudiente, String celular) {
        this.Apellido1 = Apellido1;
        this.Apellido2 = Apellido2;
        this.nombres = nombres;
        this.id = id;
        this.acudiente = acudiente;
        this.celular = celular;
    }

    public String getApellido1() {
        return Apellido1;
    }

    public void setApellido1(String Apellido1) {
        this.Apellido1 = Apellido1;
    }

    public String getApellido2() {
        return Apellido2;
    }

    public void setApellido2(String Apellido2) {
        this.Apellido2 = Apellido2;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcudiente() {
        return acudiente;
    }

    public void setAcudiente(String acudiente) {
        this.acudiente = acudiente;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
   
    
    
    
    }
   
    
    
   

    
 

    



    

    

