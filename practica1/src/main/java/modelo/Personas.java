/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dnarc
 */
@Entity
@Table(name="personas")
public class Personas implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPersona;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido1")
    private String apellido1;
    
    @Column(name="apellido2")
    private String apellido2;
    
    @Column(name="sexo")
    private char sexo;   //ENUM en BBDD
    
    @Column(name="fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;   //DATETIME en BBDD

    public int getIdPersonas() {
        return idPersona;
    }

    public void setIdPersonas(int idPersonas) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.idPersona;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + Objects.hashCode(this.apellido1);
        hash = 29 * hash + Objects.hashCode(this.apellido2);
        hash = 29 * hash + this.sexo;
        hash = 29 * hash + Objects.hashCode(this.fechaNacimiento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Personas other = (Personas) obj;
        if (this.idPersona != other.idPersona) {
            return false;
        }
        if (this.sexo != other.sexo) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido1, other.apellido1)) {
            return false;
        }
        if (!Objects.equals(this.apellido2, other.apellido2)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        return true;
    }
}
