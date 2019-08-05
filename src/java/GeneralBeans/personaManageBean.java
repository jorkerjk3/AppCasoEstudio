/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneralBeans;

import GeneralController.Controller.PersonasFacade;
import dbEntities.Personas;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author usuario
 */
@ManagedBean
@Named(value = "personaManageBean")
@RequestScoped
public class personaManageBean  implements Serializable{

    private String numerodocumento;
    private Personas selected;
    private String result;

    
    @EJB
    private PersonasFacade personasFacade;

    public Personas getSelected() {
        return selected;
    }

    public void setSelected(Personas selected) {
        this.selected = selected;
    }

  
    
    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }
      
   
    public personaManageBean() {
    }
        
    public void listenerDocumento(AjaxBehaviorEvent evento){
      String respuesta = numerodocumento;    
      result = "called by " + evento.getComponent().getClass().getName();
      Personas p =   personasFacade.find(1);
      Personas pers = selected;  
     
    }
    
        public String getResult() {
        return result;
    }
    
}
