package net.azarquiel.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AsignaturaRepository extends CrudRepository<Asignatura, Integer>{

    @Query("SELECT a FROM Asignatura a WHERE a.idAsignatura IN (SELECT p.idAsignatura FROM Publicacion p WHERE p.idPerfil.idPerfil = :idPerfil)") 
    Iterable<Asignatura> findAsignaturasByNombre(@Param("idPerfil") Integer idPerfil);

    
	
}

