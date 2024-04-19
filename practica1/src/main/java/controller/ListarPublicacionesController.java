/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.PublicacionesFacadeLocal;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Publicaciones;

/**
 *
 * @author dnarc
 */
@Named
@SessionScoped
public class ListarPublicacionesController implements Serializable{
    
    private Publicaciones publicacion;
    private List<Publicaciones> listaPublicaciones;
    
    @EJB
    private PublicacionesFacadeLocal publicacionEJB;
    
    @PostConstruct
    public void init(){
        publicacion = new Publicaciones();
        listaPublicaciones = publicacionEJB.findAll();
    }
    
    public String establecerPublicacion(Publicaciones publicacion){
        this.publicacion = publicacion;
        return "editarPublicacion.xhtml?faces-redirect=true";
    }

    public Publicaciones getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicaciones publicacion) {
        this.publicacion = publicacion;
    }

    public List<Publicaciones> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void setListaPublicaciones(List<Publicaciones> listaPublicaciones) {
        this.listaPublicaciones = listaPublicaciones;
    }

    public PublicacionesFacadeLocal getPublicacionEJB() {
        return publicacionEJB;
    }

    public void setPublicacionEJB(PublicacionesFacadeLocal publicacionEJB) {
        this.publicacionEJB = publicacionEJB;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.publicacion);
        hash = 47 * hash + Objects.hashCode(this.listaPublicaciones);
        hash = 47 * hash + Objects.hashCode(this.publicacionEJB);
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
        final ListarPublicacionesController other = (ListarPublicacionesController) obj;
        if (!Objects.equals(this.publicacion, other.publicacion)) {
            return false;
        }
        if (!Objects.equals(this.listaPublicaciones, other.listaPublicaciones)) {
            return false;
        }
        if (!Objects.equals(this.publicacionEJB, other.publicacionEJB)) {
            return false;
        }
        return true;
    }
    
}
