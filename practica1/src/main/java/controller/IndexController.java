/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.UsuariosFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuarios;

/**
 *
 * @author dnarc
 */

@Named
@ViewScoped
public class IndexController implements Serializable{
    
    private Usuarios usuario;
    private String nombre;

    @EJB
    private UsuariosFacadeLocal usuarioEJB;
    
   @PostConstruct
   public void init(){
       usuario = new Usuarios();
   }
   
   //Metodos propios para dotar de mas paginas a la interfaz
   public String abrirInicioSesion(){   
     return "publico/iniciarSesion.xhtml?faces-redirect=true";
   }
      
   public String abrirRegistro(){
       return "publico/registrarse.xhtml?face-redirect=true";
   }
   //
 
   public String verificarUsuario(){
       Usuarios user = usuarioEJB.verificarUsuario(usuario);
       if(user != null){
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
           return "/privado/inicio.xhtml?faces-redirect=true";
       }else{
           return "permisoInsuficiente.xhtml?faces-redirect=true";
       }
   }
   
   public String altaPublicacion(){
       return "altaPublicacion.xhtml?faces-redirect=true";
   }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
   public String altaCategoria(){
       return "altaCategoria.xhtml?faces-redirect=true";
   }
   
   public String listarPublicaciones(){
       return "profesor/listarPublicaciones.xhtml?faces-redirect=true";
   }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public UsuariosFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuariosFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }
}
