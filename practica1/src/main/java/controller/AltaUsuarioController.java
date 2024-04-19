/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.PersonasFacadeLocal;
import EJB.RolesFacadeLocal;
import EJB.UsuariosFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Personas;
import modelo.Roles;
import modelo.Usuarios;

/**
 *
 * @author dnarc
 */
@Named
@ViewScoped

public class AltaUsuarioController implements Serializable{
    
    
    private Usuarios usuario;
    private Personas persona;
    private Roles rol;
    private List<Roles> listaRoles; //Lista de roles que se cargaran en el formulario
    
    @EJB
    private RolesFacadeLocal rolEJB;
    @EJB
    private UsuariosFacadeLocal usuarioEJB;
    @EJB
    private PersonasFacadeLocal personaEJB;
    
    @PostConstruct
   public void init(){
       rol = new Roles();
       listaRoles = rolEJB.findAll();
       persona = new Personas();
       usuario = new Usuarios();
   }
   
    public String insertarUsuario(){
       //Creamos la persona y el usuario
       
       try{
           usuario.setIdPersona(persona);
           for (Roles r: listaRoles){
                if (r.getIdRol() == rol.getIdRol()) {
                    usuario.setIdRol(r);
                }
           }
           usuarioEJB.create(usuario);
       }catch(Exception e){
           System.out.println("Error al insertar un nuevo usuario:"+e.getMessage());
       }
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario.getUser());
       return "/privado/inicio.xhtml?faces-redirect=true";
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public List<Roles> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Roles> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public RolesFacadeLocal getRolEJB() {
        return rolEJB;
    }

    public void setRolEJB(RolesFacadeLocal rolEJB) {
        this.rolEJB = rolEJB;
    }

    public UsuariosFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuariosFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

    public PersonasFacadeLocal getPersonaEJB() {
        return personaEJB;
    }

    public void setPersonaEJB(PersonasFacadeLocal personaEJB) {
        this.personaEJB = personaEJB;
    }

}
