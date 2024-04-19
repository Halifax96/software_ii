/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Usuarios;

/**
 *
 * @author dnarc
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
 
    @Override
    public Usuarios verificarUsuario(Usuarios usuario){

        String consulta = "FROM Usuarios u WHERE u.user=:param1 and u.password=:param2";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", usuario.getUser());
        query.setParameter("param2", usuario.getPassword());
        
        List<Usuarios> resultado = query.getResultList();
        
        return (resultado.size() == 0) ? null : resultado.get(0);
    }
}
