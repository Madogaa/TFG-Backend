/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.azarquiel.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author mario
 */

@Entity
@Table(name = "perfil")
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_perfil")
    private Integer idPerfil;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne(optional = false)
    @JsonBackReference
    private Usuario idUsuario;
    @Column(name = "img")
    private String img;
    @Column(name = "img_banner")
    private String imgBanner;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @Column(name = "npublicaciones")
    private Integer npublicaciones;
    @Basic(optional = false)
    @Column(name = "nseguidores")
    private Integer nseguidores;
    @Basic(optional = false)
    @Column(name = "nseguidos")
    private Integer nseguidos;
    @OneToMany(mappedBy = "idPerfil")
    @JsonIgnore
    private List<Publicacion> publicacionList;
    
    public Perfil() {
    }

    public Perfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }
    
    public List<Publicacion> getPublicacionList() {
		return publicacionList;
	}

	public void setPublicacionList(List<Publicacion> publicacionList) {
		this.publicacionList = publicacionList;
	}

	public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgBanner() {
        return imgBanner;
    }

    public void setImgBanner(String imgBanner) {
        this.imgBanner = imgBanner;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public Integer getNpublicaciones() {
        return npublicaciones;
    }

    public void setNpublicaciones(Integer npublicaciones) {
        this.npublicaciones = npublicaciones;
    }

    public Integer getNseguidores() {
        return nseguidores;
    }

    public void setNseguidores(Integer nseguidores) {
        this.nseguidores = nseguidores;
    }

    public Integer getNseguidos() {
        return nseguidos;
    }

    public void setNseguidos(Integer nseguidos) {
        this.nseguidos = nseguidos;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
    

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Perfil [idPerfil=" + idPerfil + ", idUsuario=" + idUsuario + ", img=" + img + ", imgBanner=" + imgBanner
				+ ", nombre=" + nombre + ", creationDate=" + creationDate + ", publicacionList=" + publicacionList
				+ "]";
	}

    
    
}
