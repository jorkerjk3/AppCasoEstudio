package GeneralController.Controller;

import dbEntities.TipoCredito;
import GeneralController.Controller.util.JsfUtil;
import GeneralController.Controller.util.JsfUtil.PersistAction;

import java.io.Serializable;
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

@Named("tipoCreditoController")
@SessionScoped
public class TipoCreditoController implements Serializable {

    @EJB
    private GeneralController.Controller.TipoCreditoFacade ejbFacade;
    private List<TipoCredito> items = null;
    private TipoCredito selected;

    public TipoCreditoController() {
    }

    public TipoCredito getSelected() {
        return selected;
    }

    public void setSelected(TipoCredito selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TipoCreditoFacade getFacade() {
        return ejbFacade;
    }

    public TipoCredito prepareCreate() {
        selected = new TipoCredito();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleCredito").getString("TipoCreditoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleCredito").getString("TipoCreditoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleCredito").getString("TipoCreditoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TipoCredito> getItems() {
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

    public TipoCredito getTipoCredito(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TipoCredito> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TipoCredito> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TipoCredito.class)
    public static class TipoCreditoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoCreditoController controller = (TipoCreditoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoCreditoController");
            return controller.getTipoCredito(getKey(value));
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
            if (object instanceof TipoCredito) {
                TipoCredito o = (TipoCredito) object;
                return getStringKey(o.getIdtipocredito());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoCredito.class.getName()});
                return null;
            }
        }

    }

}
