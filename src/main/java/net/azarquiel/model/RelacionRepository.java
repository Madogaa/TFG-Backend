package net.azarquiel.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RelacionRepository extends CrudRepository<RelacionSeguidos, PerfilSiguePerfil> {
	/*
	@Query("SELECT r.id FROM RelacionSeguidos r WHERE r.id.seguidorId = :id")
	Iterable<PerfilSiguePerfil> buscaSeguidos(@Param("id") Integer id);
	*/
	@Query("SELECT r.seguido FROM RelacionSeguidos r WHERE r.id.seguidorId = :id")
	Iterable<Perfil> buscaSeguidos(@Param("id") Integer id);
	
	@Query("SELECT r.perfil_id FROM RelacionSeguidos r WHERE r.id.seguidoId = :id")
	Iterable<Perfil> buscaSeguidores(@Param("id") Integer id);
	
	@Query("SELECT r.perfil_id FROM RelacionSeguidos r WHERE r.id.seguidoId = :ido and r.id.seguidorId= :idr")
	Iterable<Perfil> buscaSeguidor(@Param("idr") Integer idr, @Param("ido") Integer ido);
	/*
	@Query("SELECT r.id FROM RelacionSeguidos r WHERE r.id.seguidoId = :id")
	Iterable<PerfilSiguePerfil> buscaSeguidores(@Param("id") Integer id);
	*/
	
}