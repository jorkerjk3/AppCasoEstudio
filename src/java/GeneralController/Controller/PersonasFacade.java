/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneralController.Controller;

import dbEntities.Personas;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author usuario
 */
@Stateless
public class PersonasFacade extends AbstractFacade<Personas> {

    @PersistenceContext(unitName = "AppCasoEstudioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonasFacade() {
        super(Personas.class);
    }
           
    //Si la persona no existe, retorna un true para crearla
    //Si la persona existe, retorna un false para actualizarla
    //Retorna null cuando existe un error
     public Boolean ValidarPersona (Personas personaObj){
       Boolean result = false;
        try{        
        
          Integer tipoDoc = personaObj.getIdtipodocumento().getIdtipodocumento();
          String documento = personaObj.getNumerodocumento();         
          Date fActual = new Date();
                   
         //verificar si existe la persona con ese numero documento
         Query q = em.createNamedQuery("Personas.findByNumerodocumento", Personas.class).setParameter("numerodocumento", documento);
     
         List<Personas> personaEncontrada = q.getResultList();
         
         if(personaEncontrada.isEmpty())
            result = true;
         else 
            result = false;
                  
         } catch(Exception ex){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al intentar ingresar los datos básicos persona","Error al intentar ingresar los datos básicos"));
              return null;
         }
         return result;
     }
    
     //Pte validar tambien por tipoDocumentoId
     public Personas ObtenerPersona (Integer tipoDocumentoId, String documento){
     
          Personas per;         
          Query q = em.createNamedQuery("Personas.findByNumerodocumento", Personas.class).setParameter("numerodocumento", documento);
          //q.setParameter("idtipodocumento", tipoDocumentoId);
          
           List<Personas> perList = q.getResultList();
           per = perList.get(0);
          
           return per;
     }
}
