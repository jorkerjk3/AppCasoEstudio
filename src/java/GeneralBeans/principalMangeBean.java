
package GeneralBeans;

import dbEntities.Usuarios;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "principalMangeBean")
@RequestScoped
public class principalMangeBean {

    
    public principalMangeBean() {
    }

 public void validarLogueo ()
    {    
        try{
           FacesContext ctx = FacesContext.getCurrentInstance();
            Usuarios us = (Usuarios)  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogueado");
           
           if(us == null){
              ctx.getExternalContext().redirect("noAutorizado.xhtml");
           }
           
        }catch( IOException ex){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,ex.getMessage(),"Error interno del servidor."));
           
        }
       
    }   
}
