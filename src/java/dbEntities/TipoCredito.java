/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbEntities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "TipoCredito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCredito.findAll", query = "SELECT t FROM TipoCredito t")
    , @NamedQuery(name = "TipoCredito.findByIdtipocredito", query = "SELECT t FROM TipoCredito t WHERE t.idtipocredito = :idtipocredito")
    , @NamedQuery(name = "TipoCredito.findByDescripcion", query = "SELECT t FROM TipoCredito t WHERE t.descripcion = :descripcion")})
public class TipoCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_tipo_credito")
    private Integer idtipocredito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Descripcion")
    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipocredito")
    private List<Credito> creditoList;

    public TipoCredito() {
    }

    public TipoCredito(Integer idtipocredito) {
        this.idtipocredito = idtipocredito;
    }

    public TipoCredito(Integer idtipocredito, String descripcion) {
        this.idtipocredito = idtipocredito;
        this.descripcion = descripcion;
    }

    public Integer getIdtipocredito() {
        return idtipocredito;
    }

    public void setIdtipocredito(Integer idtipocredito) {
        this.idtipocredito = idtipocredito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Credito> getCreditoList() {
        return creditoList;
    }

    public void setCreditoList(List<Credito> creditoList) {
        this.creditoList = creditoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipocredito != null ? idtipocredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCredito)) {
            return false;
        }
        TipoCredito other = (TipoCredito) object;
        if ((this.idtipocredito == null && other.idtipocredito != null) || (this.idtipocredito != null && !this.idtipocredito.equals(other.idtipocredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbEntities.TipoCredito[ idtipocredito=" + idtipocredito + " ]";
    }
    
}
