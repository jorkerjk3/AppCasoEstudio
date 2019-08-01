/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneralController.Controller;

import dbEntities.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author usuario
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "AppCasoEstudioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
        
    public Usuarios ObtenerNombreusuario(String nombreUsuario){
     Query q = em.createNamedQuery("Usuarios.findByNombreusuario", Usuarios.class).setParameter("nombreusuario", nombreUsuario);
     
     List<Usuarios> u = q.getResultList();
     
     if(u.size()>0)
     {
        return u.get(0);
     }else {
        return null;
     }
    }
}
