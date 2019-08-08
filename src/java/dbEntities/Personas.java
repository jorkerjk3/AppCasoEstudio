/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbEntities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "Personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personas.findAll", query = "SELECT p FROM Personas p")
    , @NamedQuery(name = "Personas.findByIdpersonas", query = "SELECT p FROM Personas p WHERE p.idpersonas = :idpersonas")
    , @NamedQuery(name = "Personas.findByNombres", query = "SELECT p FROM Personas p WHERE p.nombres = :nombres")
    , @NamedQuery(name = "Personas.findByApellidos", query = "SELECT p FROM Personas p WHERE p.apellidos = :apellidos")
    , @NamedQuery(name = "Personas.findByNumerodocumento", query = "SELECT p FROM Personas p WHERE p.numerodocumento = :numerodocumento")
    , @NamedQuery(name = "Personas.findByFcehanacimiento", query = "SELECT p FROM Personas p WHERE p.fcehanacimiento = :fcehanacimiento")
    , @NamedQuery(name = "Personas.findByGenero", query = "SELECT p FROM Personas p WHERE p.genero = :genero")
    , @NamedQuery(name = "Personas.findByEmail", query = "SELECT p FROM Personas p WHERE p.email = :email")
    , @NamedQuery(name = "Personas.findByCelular", query = "SELECT p FROM Personas p WHERE p.celular = :celular")
    , @NamedQuery(name = "Personas.findByIngresosmensuales", query = "SELECT p FROM Personas p WHERE p.ingresosmensuales = :ingresosmensuales")
    , @NamedQuery(name = "Personas.findByEstrato", query = "SELECT p FROM Personas p WHERE p.estrato = :estrato")
    , @NamedQuery(name = "Personas.findByIdtipopersona", query = "SELECT p FROM Personas p WHERE p.idtipopersona = :idtipopersona")
    , @NamedQuery(name = "Personas.findByFechacreacion", query = "SELECT p FROM Personas p WHERE p.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Personas.findByFechamodificacion", query = "SELECT p FROM Personas p WHERE p.fechamodificacion = :fechamodificacion")
    , @NamedQuery(name = "Personas.findByEstado", query = "SELECT p FROM Personas p WHERE p.estado = :estado")})
public class Personas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_personas")
    private Integer idpersonas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "Nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "Apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Numero_documento")
    private String numerodocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fceha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fcehanacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Genero")
    private Character genero;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Celular")
    private long celular;
    @Size(max = 50)
    @Column(name = "Ingresos_mensuales")
    private String ingresosmensuales;
    @Column(name = "Estrato")
    private Integer estrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_tipo_persona")
    private int idtipopersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Column(name = "Fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Estado")
    private boolean estado;
    @JoinColumn(name = "Id_ciudad", referencedColumnName = "Id_ciudad")
    @ManyToOne(optional = false)
    private Ciudad idciudad;
    @JoinColumn(name = "Id_estado_civil", referencedColumnName = "Id_estado_civil")
    @ManyToOne(optional = false)
    private Estadocivil idestadocivil;
    @JoinColumn(name = "Id_nivel_academico", referencedColumnName = "Id_nivel_academico")
    @ManyToOne(optional = false)
    private NivelAcademico idnivelacademico;
    @JoinColumn(name = "Id_tipo_documento", referencedColumnName = "Id_tipo_documento")
    @ManyToOne(optional = false)
    private TipoDocumento idtipodocumento;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpersonas")
    private List<Usuarios> usuariosList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpersonas")
    private List<Credito> creditoList;

    public Personas() {
    }

    public Personas(Integer idpersonas) {
        this.idpersonas = idpersonas;
    }

    public Personas(Integer idpersonas, String nombres, String apellidos, String numerodocumento, Date fcehanacimiento, Character genero, String email, int celular, int idtipopersona, Date fechacreacion, boolean estado) {
        this.idpersonas = idpersonas;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numerodocumento = numerodocumento;
        this.fcehanacimiento = fcehanacimiento;
        this.genero = genero;
        this.email = email;
        this.celular = celular;
        this.idtipopersona = idtipopersona;
        this.fechacreacion = fechacreacion;
        this.estado = estado;
    }

    public Integer getIdpersonas() {
        return idpersonas;
    }

    public void setIdpersonas(Integer idpersonas) {
        this.idpersonas = idpersonas;
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

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public Date getFcehanacimiento() {
        return fcehanacimiento;
    }

    public void setFcehanacimiento(Date fcehanacimiento) {
        this.fcehanacimiento = fcehanacimiento;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public String getIngresosmensuales() {
        return ingresosmensuales;
    }

    public void setIngresosmensuales(String ingresosmensuales) {
        this.ingresosmensuales = ingresosmensuales;
    }

    public Integer getEstrato() {
        return estrato;
    }

    public void setEstrato(Integer estrato) {
        this.estrato = estrato;
    }

    public int getIdtipopersona() {
        return idtipopersona;
    }

    public void setIdtipopersona(int idtipopersona) {
        this.idtipopersona = idtipopersona;
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

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Ciudad getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(Ciudad idciudad) {
        this.idciudad = idciudad;
    }

    public Estadocivil getIdestadocivil() {
        return idestadocivil;
    }

    public void setIdestadocivil(Estadocivil idestadocivil) {
        this.idestadocivil = idestadocivil;
    }

    public NivelAcademico getIdnivelacademico() {
        return idnivelacademico;
    }

    public void setIdnivelacademico(NivelAcademico idnivelacademico) {
        this.idnivelacademico = idnivelacademico;
    }

    public TipoDocumento getIdtipodocumento() {
        return idtipodocumento;
    }

    public void setIdtipodocumento(TipoDocumento idtipodocumento) {
        this.idtipodocumento = idtipodocumento;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
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
        hash += (idpersonas != null ? idpersonas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personas)) {
            return false;
        }
        Personas other = (Personas) object;
        if ((this.idpersonas == null && other.idpersonas != null) || (this.idpersonas != null && !this.idpersonas.equals(other.idpersonas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbEntities.Personas[ idpersonas=" + idpersonas + " ]";
    }
    
}
