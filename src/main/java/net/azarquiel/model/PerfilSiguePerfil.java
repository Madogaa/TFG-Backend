package net.azarquiel.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PerfilSiguePerfil implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id_perfil")
    private Integer seguidorId;
    
    @Column(name = "id_seguido")
    private Integer seguidoId;
    
    public PerfilSiguePerfil() {}
    
    public PerfilSiguePerfil(Integer seguidorId, Integer seguidoId) {
        this.seguidorId = seguidorId;
        this.seguidoId = seguidoId;
    }

	@Override
	public int hashCode() {
		return Objects.hash(seguidoId, seguidorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilSiguePerfil other = (PerfilSiguePerfil) obj;
		return Objects.equals(seguidoId, other.seguidoId) && Objects.equals(seguidorId, other.seguidorId);
	}

	public Integer getSeguidorId() {
		return seguidorId;
	}

	public void setSeguidorId(Integer seguidorId) {
		this.seguidorId = seguidorId;
	}

	public Integer getSeguidoId() {
		return seguidoId;
	}

	public void setSeguidoId(Integer seguidoId) {
		this.seguidoId = seguidoId;
	}
    
    // getters y setters
    
}