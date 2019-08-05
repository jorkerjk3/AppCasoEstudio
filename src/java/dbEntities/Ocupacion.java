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
@Table(name = "Ocupacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ocupacion.findAll", query = "SELECT o FROM Ocupacion o")
    , @NamedQuery(name = "Ocupacion.findByIdocupacion", query = "SELECT o FROM Ocupacion o WHERE o.idocupacion = :idocupacion")
    , @NamedQuery(name = "Ocupacion.findByDescripcion", query = "SELECT o FROM Ocupacion o WHERE o.descripcion = :descripcion")})
public class Ocupacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_ocupacion")
    private Integer idocupacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idocupacion")
    private List<Credito> creditoList;

    public Ocupacion() {
    }

    public Ocupacion(Integer idocupacion) {
        this.idocupacion = idocupacion;
    }

    public Ocupacion(Integer idocupacion, String descripcion) {
        this.idocupacion = idocupacion;
        this.descripcion = descripcion;
    }

    public Integer getIdocupacion() {
        return idocupacion;
    }

    public void setIdocupacion(Integer idocupacion) {
        this.idocupacion = idocupacion;
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
        hash += (idocupacion != null ? idocupacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ocupacion)) {
            return false;
        }
        Ocupacion other = (Ocupacion) object;
        if ((this.idocupacion == null && other.idocupacion != null) || (this.idocupacion != null && !this.idocupacion.equals(other.idocupacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbEntities.Ocupacion[ idocupacion=" + idocupacion + " ]";
    }
    
}
