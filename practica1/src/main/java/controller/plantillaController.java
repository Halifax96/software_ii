/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.UsuariosFacadeLocal;
import java.io.Serializable;
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
public class plantillaController implements Serializable{
    
    private Usuarios usuario;

    @EJB
    private UsuariosFacadeLocal usuarioEJB;
    
    public void verificarYMostrar(){
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") == null){
            String url = "publico/permisoInsuficiente.xhtml?faces-redirect=true";
            try{
                FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            }catch(Exception e){
                System.out.println("Eror verificarYMostrar: "+e);
            }
        }
    }
}
