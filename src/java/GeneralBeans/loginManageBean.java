/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneralBeans;

import GeneralController.Controller.UsuariosFacade;
import dbEntities.Usuarios;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author usuario
 */
@Named(value = "loginManageBean")
@SessionScoped
public class loginManageBean implements Serializable {
    
    private String nombreUsuario;
    private String password;
    
    @EJB
    private UsuariosFacade usuarioFacade;
    
    private Usuarios  usuarioLogueado;

    public Usuarios getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuarios usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public String getnombreUsuario() {
        return nombreUsuario;
    }

    public void setnombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    /**
     * Creates a new instance of LoginManageBean
     */
    public loginManageBean() {
    }
        
    public String iniciarSesion(){
        
        usuarioLogueado = usuarioFacade.ObtenerNombreusuario(nombreUsuario);
        
        if(usuarioLogueado != null){
             String pass = usuarioLogueado.getContraseña();
            if(usuarioLogueado.getContraseña().equals(password)){               
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogueado", usuarioLogueado);
               return "/principal?faces-redirect=true";
            }else 
            {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Contraseña inválida","Contraseña inválida"));
               return null;
            }
           
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Usuario no existe!","Usuario no existe!"));
        return null;  
    }
}
