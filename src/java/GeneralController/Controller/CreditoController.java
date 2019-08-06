package GeneralController.Controller;

import dbEntities.Credito;
import GeneralController.Controller.util.JsfUtil;
import GeneralController.Controller.util.JsfUtil.PersistAction;
import dbEntities.Estadocivil;
import dbEntities.Personas;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;

@Named("creditoController")
@SessionScoped
public class CreditoController implements Serializable {

    @EJB
    private GeneralController.Controller.CreditoFacade ejbFacade;
 
    
    private List<Credito> items = null;
    private Personas personaObj = null;
    private Credito selected;
    private String documentoResult;
    private Integer idtipodocumento;

    public Integer getIdtipodocumento() {
        
        return idtipodocumento;
    }

    public void setIdtipodocumento(Integer idtipodocumento) {
        this.idtipodocumento = idtipodocumento;
    }

    
    public CreditoController() {
    }

    public Credito getSelected() {
        return selected;
    }

    public void setSelected(Credito selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CreditoFacade getFacade() {
        return ejbFacade;
    }
   
    public Credito prepareCreate() {
        selected = new Credito();
       
        initializeEmbeddableKey();
        return selected;
    }
    
    public void listenerDocumento(AjaxBehaviorEvent evento){    
      //resultado = "called by " + evento.getComponent().getClass().getName();      
      Credito pers = selected;  
      documentoResult = selected.getIdpersonas().getNumerodocumento();
     
    }
     
    public String getResult(){
       return documentoResult;
    }
   
   
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleCredito").getString("CreditoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleCredito").getString("CreditoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleCredito").getString("CreditoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Credito> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    Date fActual = new Date();
                    String action = persistAction.name();
                    if(action.equals("CREATE")){
                       selected.setFechacreacion(fActual);
                       selected.setEstado(true);
                       selected.setEstadocredito(1);//pre aprobado
                       selected.setFechamodificacion(fActual);
                       
                    }else {
                       selected.setFechamodificacion(fActual);
                    }
                    
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleCredito").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleCredito").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Credito getCredito(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Credito> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Credito> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Credito.class)
    public static class CreditoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CreditoController controller = (CreditoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "creditoController");
            return controller.getCredito(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Credito) {
                Credito o = (Credito) object;
                return getStringKey(o.getIdcredito());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Credito.class.getName()});
                return null;
            }
        }

    }

}
