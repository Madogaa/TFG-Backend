package net.azarquiel.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author mario
 */
@Entity
@Table(name = "perfil_sigue_perfil")
public class RelacionSeguidos implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private PerfilSiguePerfil id;

	@ManyToOne
	@MapsId("perfilId")
	@JoinColumn(name = "id_perfil", nullable = false)
	private Perfil perfil_id;

	@ManyToOne
	@MapsId("seguidoId")
	@JoinColumn(name = "id_seguido", nullable = false)
	private Perfil seguido;

	public RelacionSeguidos() {
	}

	public RelacionSeguidos(Perfil seguidor, Perfil seguido) {
		this.perfil_id = seguidor;
		this.seguido = seguido;
		this.id = new PerfilSiguePerfil(seguidor.getIdPerfil(), seguido.getIdPerfil());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, perfil_id, seguido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelacionSeguidos other = (RelacionSeguidos) obj;
		return Objects.equals(id, other.id) && Objects.equals(perfil_id, other.perfil_id)
				&& Objects.equals(seguido, other.seguido);
	}

	public PerfilSiguePerfil getId() {
		return id;
	}

	public void setId(PerfilSiguePerfil id) {
		this.id = id;
	}

	public Perfil getPerfil_id() {
		return perfil_id;
	}

	public void setPerfil_id(Perfil perfil_id) {
		this.perfil_id = perfil_id;
	}

	public Perfil getSeguido() {
		return seguido;
	}

	public void setSeguido(Perfil seguido) {
		this.seguido = seguido;
	}

}
