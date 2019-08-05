/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneralController.Controller;

import dbEntities.Ocupacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author usuario
 */
@Stateless
public class OcupacionFacade extends AbstractFacade<Ocupacion> {

    @PersistenceContext(unitName = "AppCasoEstudioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OcupacionFacade() {
        super(Ocupacion.class);
    }
    
}
