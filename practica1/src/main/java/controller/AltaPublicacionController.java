/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.CategoriasFacadeLocal;
import EJB.PublicacionesFacadeLocal;
import EJB.UsuariosFacadeLocal;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Categorias;
import modelo.Publicaciones;
import modelo.Usuarios;

/**
 *
 * @author dnarc
 */
@Named
@ViewScoped

public class AltaPublicacionController implements Serializable{
    
    @Inject
    private Publicaciones publicacion;
    @Inject
    private Categorias categoria;
    
    private List<Categorias> listaCategorias;
    @Inject
    private Usuarios usuario;
    
    @EJB
    private PublicacionesFacadeLocal publicacionEJB;
    
    @EJB
    private UsuariosFacadeLocal usuarioEJB;
    
    @EJB
    private CategoriasFacadeLocal categoriaEJB;
    
    @PostConstruct
    public void init(){
        publicacion = new Publicaciones();
        listaCategorias = categoriaEJB.findAll();
        usuario = new Usuarios();
    }

    public void insertarPublicacion(){

        //FECHA
        LocalDate ld = LocalDate.now();
        ZonedDateTime zdt = ld.atStartOfDay(ZoneId.systemDefault());
        Instant instant = zdt.toInstant();
        Date date = Date.from(instant);
        
        publicacion.setFecha(date);
        
        //Usuario que la crea
        //publicacion.setIdPersona(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"));
        
        publicacion.setIdCategoria(categoria);
        
       try{
           publicacionEJB.create(publicacion);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Publicacion registrada con exito"));
       }catch(Exception e){
           System.out.println("Error al insertar la publicacion:"+e.getMessage());
       }
       
    }

    public Publicaciones getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicaciones publicacion) {
        this.publicacion = publicacion;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public List<Categorias> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categorias> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public PublicacionesFacadeLocal getPublicacionEJB() {
        return publicacionEJB;
    }

    public void setPublicacionEJB(PublicacionesFacadeLocal publicacionEJB) {
        this.publicacionEJB = publicacionEJB;
    }

    public UsuariosFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuariosFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

    public CategoriasFacadeLocal getCategoriaEJB() {
        return categoriaEJB;
    }

    public void setCategoriaEJB(CategoriasFacadeLocal categoriaEJB) {
        this.categoriaEJB = categoriaEJB;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.publicacion);
        hash = 83 * hash + Objects.hashCode(this.categoria);
        hash = 83 * hash + Objects.hashCode(this.listaCategorias);
        hash = 83 * hash + Objects.hashCode(this.usuario);
        hash = 83 * hash + Objects.hashCode(this.publicacionEJB);
        hash = 83 * hash + Objects.hashCode(this.usuarioEJB);
        hash = 83 * hash + Objects.hashCode(this.categoriaEJB);
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
        final AltaPublicacionController other = (AltaPublicacionController) obj;
        if (!Objects.equals(this.publicacion, other.publicacion)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.listaCategorias, other.listaCategorias)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.publicacionEJB, other.publicacionEJB)) {
            return false;
        }
        if (!Objects.equals(this.usuarioEJB, other.usuarioEJB)) {
            return false;
        }
        if (!Objects.equals(this.categoriaEJB, other.categoriaEJB)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
