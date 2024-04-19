/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.MenusFacadeLocal;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Menus;
import modelo.Usuarios;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author dnarc
 */
@Named
@SessionScoped
public class MenuController implements Serializable{
    
    @EJB
    private MenuModel modelo;
    private Usuarios usuario;   //?
    private Menus menu; //?
    private List<Menus> menus;

    @EJB
    private MenusFacadeLocal menuEJB;

     @PostConstruct
   public void init(){
       
       usuario = new Usuarios();
       menus = menuEJB.obtenerMenusUsuario(usuario);
       modelo = obtenerMenu();
   }

    public MenuModel obtenerMenu(){
     for (Menus menu : menus){
         //Submenu
         if(menu.getTipo() == 'S'){
             DefaultSubMenu subMenu = DefaultSubMenu.builder().label(menu.getNombre()).build();
             modelo.getElements().add(subMenu);
             for (Menus i : menus){
                 //Item
                 if(i.getTipo() == 'I'){
                     DefaultMenuItem item = DefaultMenuItem.builder().value(i.getNombre()).url(i.getUrl()).build();
                     item.setUrl(i.getUrl());
                     //Tiene padre
                     if(i.getIdMenu_Menu().equals(menu)){
                      subMenu.getElements().add(item);
                      //No tiene padre
                     }else{
                         modelo.getElements().add(item);
                     }    
                 }
             }
         }
     }
     return modelo;
    }

   public String destruirSesion(){
       FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
       return "/index.xhtml?faces-redirect=true";
   }
   
    public MenuModel getModelo() {
        return modelo;
    }

    public void setModelo(MenuModel modelo) {
        this.modelo = modelo;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu) {
        this.menu = menu;
    }

    public MenusFacadeLocal getMenuEJB() {
        return menuEJB;
    }

    public void setMenuEJB(MenusFacadeLocal menuEJB) {
        this.menuEJB = menuEJB;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.modelo);
        hash = 41 * hash + Objects.hashCode(this.usuario);
        hash = 41 * hash + Objects.hashCode(this.menu);
        hash = 41 * hash + Objects.hashCode(this.menuEJB);
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
        final MenuController other = (MenuController) obj;
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.menu, other.menu)) {
            return false;
        }
        if (!Objects.equals(this.menuEJB, other.menuEJB)) {
            return false;
        }
        return true;
    }
    
    

}
