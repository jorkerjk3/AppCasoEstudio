/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbEntities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "TipoContrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoContrato.findAll", query = "SELECT t FROM TipoContrato t")
    , @NamedQuery(name = "TipoContrato.findByIdtipocontrato", query = "SELECT t FROM TipoContrato t WHERE t.idtipocontrato = :idtipocontrato")
    , @NamedQuery(name = "TipoContrato.findByDescripcion", query = "SELECT t FROM TipoContrato t WHERE t.descripcion = :descripcion")})
public class TipoContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_tipo_contrato")
    private Integer idtipocontrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idtipocontrato")
    private List<Credito> creditoList;

    public TipoContrato() {
    }

    public TipoContrato(Integer idtipocontrato) {
        this.idtipocontrato = idtipocontrato;
    }

    public TipoContrato(Integer idtipocontrato, String descripcion) {
        this.idtipocontrato = idtipocontrato;
        this.descripcion = descripcion;
    }

    public Integer getIdtipocontrato() {
        return idtipocontrato;
    }

    public void setIdtipocontrato(Integer idtipocontrato) {
        this.idtipocontrato = idtipocontrato;
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
        hash += (idtipocontrato != null ? idtipocontrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoContrato)) {
            return false;
        }
        TipoContrato other = (TipoContrato) object;
        if ((this.idtipocontrato == null && other.idtipocontrato != null) || (this.idtipocontrato != null && !this.idtipocontrato.equals(other.idtipocontrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbEntities.TipoContrato[ idtipocontrato=" + idtipocontrato + " ]";
    }
    
}
