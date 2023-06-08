package net.azarquiel.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PublicacionRepository extends CrudRepository<Publicacion, Integer>{

    @Query("SELECT pub FROM Publicacion pub WHERE pub.idPerfil.idPerfil = :idPerfil") 
    Iterable<Publicacion> findPublicacionByIdPerfil(@Param("idPerfil") Integer idPerfil);
    
    @Query("SELECT p FROM Publicacion p WHERE p.idAsignatura.idAsignatura = :idAsignatura") 
    Iterable<Publicacion> findPublicacionByIdAsignatura(@Param("idAsignatura") Integer idAsignatura);

}