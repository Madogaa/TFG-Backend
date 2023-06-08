package net.azarquiel.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author mario
 */
@Entity
@Table(name = "asignatura")
    public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignatura")
    private Integer idAsignatura;
    @Column(name = "nombre")
    private String nombre;
    @JoinTable(name = "grado_tiene_asignatura", joinColumns = {
        @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")}, inverseJoinColumns = {
        @JoinColumn(name = "id_grado", referencedColumnName = "id_grado")})
    @ManyToMany
    @JsonIgnore
    private List<Grado> gradoList;
    @OneToMany(mappedBy = "idAsignatura")
    @JsonIgnore
    private List<Publicacion> publicacionList;

    public Asignatura() {
    }

    public Asignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }
    
    

    public List<Publicacion> getPublicacionList() {
		return publicacionList;
	}

	public void setPublicacionList(List<Publicacion> publicacionList) {
		this.publicacionList = publicacionList;
	}

	public Integer getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Grado> getGradoList() {
        return gradoList;
    }

    public void setGradoList(List<Grado> gradoList) {
        this.gradoList = gradoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignatura != null ? idAsignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.idAsignatura == null && other.idAsignatura != null) || (this.idAsignatura != null && !this.idAsignatura.equals(other.idAsignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Asignatura[ idAsignatura=" + idAsignatura + " ]";
    }
    
}
