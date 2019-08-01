/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneralBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author usuario
 */
@Named(value = "principalMangeBean")
@RequestScoped
public class principalMangeBean {

    /**
     * Creates a new instance of principalMangeBean
     */
    public principalMangeBean() {
    }

public String redireccionarVistas(Integer tipoVista){//1 = Clientes
      
        String resultado = "";
        
        if(tipoVista == 1){
           resultado = "redirectClientes";
        }else if(tipoVista == 2){
           resultado = "redirectCiudad";
        }
        
        return resultado;   
    }    
}
