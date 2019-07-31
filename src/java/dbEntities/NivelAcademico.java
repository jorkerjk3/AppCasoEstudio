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
@Table(name = "NivelAcademico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelAcademico.findAll", query = "SELECT n FROM NivelAcademico n")
    , @NamedQuery(name = "NivelAcademico.findByIdnivelacademico", query = "SELECT n FROM NivelAcademico n WHERE n.idnivelacademico = :idnivelacademico")
    , @NamedQuery(name = "NivelAcademico.findByDescripcion", query = "SELECT n FROM NivelAcademico n WHERE n.descripcion = :descripcion")})
public class NivelAcademico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_nivel_academico")
    private Integer idnivelacademico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idnivelacademico")
    private List<Personas> personasList;

    public NivelAcademico() {
    }

    public NivelAcademico(Integer idnivelacademico) {
        this.idnivelacademico = idnivelacademico;
    }

    public NivelAcademico(Integer idnivelacademico, String descripcion) {
        this.idnivelacademico = idnivelacademico;
        this.descripcion = descripcion;
    }

    public Integer getIdnivelacademico() {
        return idnivelacademico;
    }

    public void setIdnivelacademico(Integer idnivelacademico) {
        this.idnivelacademico = idnivelacademico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnivelacademico != null ? idnivelacademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelAcademico)) {
            return false;
        }
        NivelAcademico other = (NivelAcademico) object;
        if ((this.idnivelacademico == null && other.idnivelacademico != null) || (this.idnivelacademico != null && !this.idnivelacademico.equals(other.idnivelacademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbEntities.NivelAcademico[ idnivelacademico=" + idnivelacademico + " ]";
    }
    
}
