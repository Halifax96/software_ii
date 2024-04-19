/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Menus;
import modelo.Roles;
import modelo.Usuarios;

/**
 *
 * @author dnarc
 */
@Stateless
public class MenusFacade extends AbstractFacade<Menus> implements MenusFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenusFacade() {
        super(Menus.class);
    }
    
   @Override
    public List<Menus> obtenerMenusUsuario(Usuarios usuario){
        List<Menus> menusUsuario = new ArrayList();
        Roles r = null;
        for (Menus menu : this.findAll()){
            r = menu.getIdRol();
            int valor = r.getIdRol();
            r = usuario.getIdRol();
            int valor2 = r.getIdRol();
            System.out.println("V:"+valor+" "+valor2);
            if(valor == valor2){
                menusUsuario.add(menu);
            }
        }
        return menusUsuario;
    }
    
}
