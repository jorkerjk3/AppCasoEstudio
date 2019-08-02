/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneralController.Controller;

import dbEntities.Usuarios;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author usuario
 */
@Named(value = "ValidarLogueoController")
@ViewScoped
public class ValidarLogueoController implements Serializable {
    
    public void validarLogueo ()
    {    
        
        try{
           FacesContext ctx = FacesContext.getCurrentInstance();
            Usuarios us = (Usuarios)  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogueado");
           
           if(us == null){
              ctx.getExternalContext().redirect("noAutorizado");
           }else
           {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES,"Correcto"));
           }
        }catch( IOException ex){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,ex.getMessage(),"Error interno del servidor."));
           
        }
       
    }
    
}
