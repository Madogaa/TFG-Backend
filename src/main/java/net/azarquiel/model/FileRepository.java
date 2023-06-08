package net.azarquiel.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FileRepository extends CrudRepository<Archivo, Integer>{

    @Modifying
    @Query("DELETE FROM Archivo a WHERE a.idPublicacion = :idPublicacion")
    void deleteByIdPublicacion(@Param("idPublicacion") Integer idPublicacion);

}
