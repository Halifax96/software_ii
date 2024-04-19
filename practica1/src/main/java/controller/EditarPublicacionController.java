/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.PublicacionesFacadeLocal;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Publicaciones;

/**
 *
 * @author dnarc
 */
@Named
@ViewScoped
public class EditarPublicacionController implements Serializable{
    
    @Inject
    private ListarPublicacionesController listPubCon;
    
    private Publicaciones publicacion;
    
    @EJB
    private PublicacionesFacadeLocal publicacionEJB;
    
    @PostConstruct
    public void init(){
        publicacion = listPubCon.getPublicacion();
    }
    
    public String actualizar(){
        publicacionEJB.edit(publicacion);
        FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Publicacion actualizada"));
        return "listarPublicaciones.xhtml?faces-redirect=true";
    }
    
    public void establecerPublicacion(Publicaciones publicacion){
        this.publicacion = publicacion;
    }

    public Publicaciones getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicaciones publicacion) {
        this.publicacion = publicacion;
    }

    public PublicacionesFacadeLocal getPublicacionEJB() {
        return publicacionEJB;
    }

    public void setPublicacionEJB(PublicacionesFacadeLocal publicacionEJB) {
        this.publicacionEJB = publicacionEJB;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.publicacion);
        hash = 97 * hash + Objects.hashCode(this.publicacionEJB);
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
        final EditarPublicacionController other = (EditarPublicacionController) obj;
        if (!Objects.equals(this.publicacion, other.publicacion)) {
            return false;
        }
        if (!Objects.equals(this.publicacionEJB, other.publicacionEJB)) {
            return false;
        }
        return true;
    }
    
    
}
