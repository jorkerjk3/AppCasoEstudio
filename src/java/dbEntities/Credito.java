/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbEntities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "Credito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credito.findAll", query = "SELECT c FROM Credito c")
    , @NamedQuery(name = "Credito.findByIdcredito", query = "SELECT c FROM Credito c WHERE c.idcredito = :idcredito")
    , @NamedQuery(name = "Credito.findByNombreempresa", query = "SELECT c FROM Credito c WHERE c.nombreempresa = :nombreempresa")
    , @NamedQuery(name = "Credito.findByFechaingreso", query = "SELECT c FROM Credito c WHERE c.fechaingreso = :fechaingreso")
    , @NamedQuery(name = "Credito.findByTelefonoempresa", query = "SELECT c FROM Credito c WHERE c.telefonoempresa = :telefonoempresa")
    , @NamedQuery(name = "Credito.findByDireccionempresa", query = "SELECT c FROM Credito c WHERE c.direccionempresa = :direccionempresa")
    , @NamedQuery(name = "Credito.findByEstadocredito", query = "SELECT c FROM Credito c WHERE c.estadocredito = :estadocredito")
    , @NamedQuery(name = "Credito.findByEstado", query = "SELECT c FROM Credito c WHERE c.estado = :estado")
    , @NamedQuery(name = "Credito.findByFechacreacion", query = "SELECT c FROM Credito c WHERE c.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Credito.findByFechamodificacion", query = "SELECT c FROM Credito c WHERE c.fechamodificacion = :fechamodificacion")})
public class Credito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_credito")
    private Integer idcredito;
    @Size(max = 80)
    @Column(name = "Nombre_empresa")
    private String nombreempresa;
    @Column(name = "Fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaingreso;
    @Size(max = 50)
    @Column(name = "Telefono_empresa")
    private String telefonoempresa;
    @Size(max = 150)
    @Column(name = "Direccion_empresa")
    private String direccionempresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Estado_credito")
    private int estadocredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Estado")
    private boolean estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    @JoinColumn(name = "Id_ocupacion", referencedColumnName = "Id_ocupacion")
    @ManyToOne(optional = false)
    private Ocupacion idocupacion;
    
    @JoinColumn(name = "Id_tipo_contrato", referencedColumnName = "Id_tipo_contrato")
    @ManyToOne
    private TipoContrato idtipocontrato;
    
    @JoinColumn(name = "Id_tipo_credito", referencedColumnName = "Id_tipo_credito")
    @ManyToOne(optional = false)
    private TipoCredito idtipocredito;
    
    @JoinColumn(name = "Id_personas", referencedColumnName = "Id_personas")
    @ManyToOne(optional = true)
    private Personas idpersonas;

    public Credito() {
    }

    public Credito(Integer idcredito) {
        this.idcredito = idcredito;
    }

    public Credito(Integer idcredito, int estadocredito, boolean estado, Date fechacreacion, Date fechamodificacion) {
        this.idcredito = idcredito;
        this.estadocredito = estadocredito;
        this.estado = estado;
        this.fechacreacion = fechacreacion;
        this.fechamodificacion = fechamodificacion;
    }

    public Integer getIdcredito() {
        return idcredito;
    }

    public void setIdcredito(Integer idcredito) {
        this.idcredito = idcredito;
    }

    public String getNombreempresa() {
        return nombreempresa;
    }

    public void setNombreempresa(String nombreempresa) {
        this.nombreempresa = nombreempresa;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public String getTelefonoempresa() {
        return telefonoempresa;
    }

    public void setTelefonoempresa(String telefonoempresa) {
        this.telefonoempresa = telefonoempresa;
    }

    public String getDireccionempresa() {
        return direccionempresa;
    }

    public void setDireccionempresa(String direccionempresa) {
        this.direccionempresa = direccionempresa;
    }

    public int getEstadocredito() {
        return estadocredito;
    }

    public void setEstadocredito(int estadocredito) {
        this.estadocredito = estadocredito;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public Ocupacion getIdocupacion() {
        return idocupacion;
    }

    public void setIdocupacion(Ocupacion idocupacion) {
        this.idocupacion = idocupacion;
    }

    public TipoContrato getIdtipocontrato() {
        return idtipocontrato;
    }

    public void setIdtipocontrato(TipoContrato idtipocontrato) {
        this.idtipocontrato = idtipocontrato;
    }

    public TipoCredito getIdtipocredito() {
        return idtipocredito;
    }

    public void setIdtipocredito(TipoCredito idtipocredito) {
        this.idtipocredito = idtipocredito;
    }

   public Personas getIdpersonas() {
        return idpersonas;
    }

    public void setIdpersonas(Personas idpersonas) {
        this.idpersonas = idpersonas;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcredito != null ? idcredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credito)) {
            return false;
        }
        Credito other = (Credito) object;
        if ((this.idcredito == null && other.idcredito != null) || (this.idcredito != null && !this.idcredito.equals(other.idcredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbEntities.Credito[ idcredito=" + idcredito + " ]";
    }
    
}
