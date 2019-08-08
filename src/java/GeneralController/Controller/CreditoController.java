package GeneralController.Controller;

import dbEntities.Credito;
import GeneralController.Controller.util.JsfUtil;
import GeneralController.Controller.util.JsfUtil.PersistAction;
import dbEntities.Ciudad;
import dbEntities.Estadocivil;
import dbEntities.NivelAcademico;
import dbEntities.Personas;
import dbEntities.TipoDocumento;
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
import javax.validation.constraints.Pattern;

@Named("creditoController")
@SessionScoped
public class CreditoController implements Serializable {

    @EJB
    private GeneralController.Controller.CreditoFacade ejbFacade;
    
    @EJB
    private GeneralController.Controller.PersonasFacade ejbPersonasFacade;
     
    private List<Credito> items = null;
    private Credito selected;
    private String documento;

    private TipoDocumento idtipodocumento;
    private Character genero;
    
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    private String email;
   
    private long celular;
    private String ingresosmensuales;
    private Estadocivil estadocivil;
    private Integer estrato;
    private NivelAcademico idnivelacademico;
    private Ciudad idciudad;
    private String nombres;
    private String apellidos;
    private Date fcehanacimiento;
    private Integer idPersona = 0;
    Personas perUpdate = new Personas();
    private Personas personaObj = new Personas();
       
    public Date getFcehanacimiento() {
        return fcehanacimiento;
    }

    public void setFcehanacimiento(Date fcehanacimiento) {
        this.fcehanacimiento = fcehanacimiento;
    }

    
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
   
    public Ciudad getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(Ciudad idciudad) {
        this.idciudad = idciudad;
    }

    public NivelAcademico getIdnivelacademico() {
        return idnivelacademico;
    }

    public void setIdnivelacademico(NivelAcademico idnivelacademico) {
        this.idnivelacademico = idnivelacademico;
    }

    public Integer getEstrato() {
        return estrato;
    }

    public void setEstrato(Integer estrato) {
        this.estrato = estrato;
    }
    

    public Estadocivil getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(Estadocivil estadocivil) {
        this.estadocivil = estadocivil;
    }
    
    public String getIngresosmensuales() {
        return ingresosmensuales;
    }

    public void setIngresosmensuales(String ingresosmensuales) {
        this.ingresosmensuales = ingresosmensuales;
    }
    
    
    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }
   
    public TipoDocumento getIdtipodocumento() {
        return idtipodocumento;
    }

    public void setIdtipodocumento(TipoDocumento idtipodocumento) {
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
    
    
    public Personas setValuesPersona(Date fActual){
                        
                        personaObj.setNombres(nombres);
                        personaObj.setApellidos(apellidos);
                        personaObj.setIdtipodocumento(idtipodocumento);
                        personaObj.setNumerodocumento(documento);
                        personaObj.setFcehanacimiento(fcehanacimiento);
                        personaObj.setGenero(genero);
                        personaObj.setEmail(email);
                        personaObj.setCelular(celular);
                        personaObj.setIngresosmensuales(ingresosmensuales);
                        personaObj.setIdciudad(idciudad);
                        personaObj.setIdnivelacademico(idnivelacademico);
                        personaObj.setIdestadocivil(estadocivil);
                        personaObj.setEstrato(estrato);
                        personaObj.setIdtipopersona(2);
                        personaObj.setFechacreacion(fActual);
                        personaObj.setFechamodificacion(fActual);
                        personaObj.setEstado(true);
                        
                        return personaObj;
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
         boolean personaExiste = false;
        if (selected != null) {
          // selected.getIdpersonas().setIdtipodocumento(idtipodocumento);
            
            setEmbeddableKeys();
            try {
                 Date fActual = new Date();
                if (persistAction != PersistAction.DELETE) {
                                                          
                    String action = persistAction.name();
                    if(action.equals("CREATE")){
                       
                        personaObj = setValuesPersona(fActual);
                        
                        personaExiste = ejbPersonasFacade.ValidarPersona(personaObj);
                      
                        if(personaExiste){
                             ejbPersonasFacade.create(personaObj);
                             idPersona = personaObj.getIdpersonas();
                        }                           
                        else if(!personaExiste && action.equals("CREATE")){
                        
                            //Integer tipoDocId = idtipodocumento.getIdtipodocumento();
                        perUpdate = ejbPersonasFacade.ObtenerPersona(0, documento);
                        idPersona = perUpdate.getIdpersonas();
                           
                        perUpdate.setNombres(nombres);
                        perUpdate.setApellidos(apellidos);
                        perUpdate.setIdtipodocumento(idtipodocumento);
                        perUpdate.setNumerodocumento(documento);
                        perUpdate.setFcehanacimiento(fcehanacimiento);
                        perUpdate.setGenero(genero);
                        perUpdate.setEmail(email);
                        perUpdate.setCelular(celular);
                        perUpdate.setIngresosmensuales(ingresosmensuales);
                        perUpdate.setIdciudad(idciudad);
                        perUpdate.setIdnivelacademico(idnivelacademico);
                        perUpdate.setIdestadocivil(estadocivil);
                        perUpdate.setEstrato(estrato);
                        perUpdate.setIdtipopersona(2);
                        perUpdate.setFechacreacion(fActual);
                        perUpdate.setFechamodificacion(fActual);
                        perUpdate.setEstado(true);
                            
                        ejbPersonasFacade.edit(perUpdate);
                         
                        }                            
                       selected.setIdpersonas(personaObj);
                       selected.setFechacreacion(fActual);
                       selected.setEstado(true);
                       selected.setEstadocredito(1);//pre aprobado
                       selected.setFechamodificacion(fActual);
                       
                    }else {
                      selected.setFechamodificacion(fActual);
                      idPersona = 1;
                    }
                                        
                    if(idPersona>0){
                        getFacade().edit(selected);                    
                        personaObj = new Personas();
                    }
                    
                } else {
                    selected.setFechamodificacion(fActual);
                    getFacade().remove(selected);
                    personaObj = new Personas();
                    perUpdate = new Personas();
                    idPersona = 0;
                }
                if(idPersona == 0 && persistAction != PersistAction.DELETE)
                   JsfUtil.addErrorMessage("Se ha presentado un error al tratar de insertar los datos básicos");                   
                else if(persistAction == PersistAction.CREATE)
                     JsfUtil.addSuccessMessage("SOLICITUD PRE-APROBADA, un asesor se contactará con usted en las próximas 24 horas para continuar el proceso.");
                else 
                    JsfUtil.addSuccessMessage("Correcto, cambios realizados correctamente.");
                
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

