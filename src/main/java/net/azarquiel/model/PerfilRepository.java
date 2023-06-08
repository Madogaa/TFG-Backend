package net.azarquiel.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PerfilRepository extends CrudRepository<Perfil, Integer> {

    @Query("SELECT p FROM Perfil p WHERE p.idUsuario IN (SELECT u.idUsuario FROM Usuario u WHERE u.nombre = :nombre)") 
    Perfil findPerfilByName(@Param("nombre") String nombre);
    
    @Query("SELECT p FROM Perfil p WHERE p.idUsuario IN (SELECT u.idUsuario FROM Usuario u WHERE u.nombre LIKE %:nombre%)") 
    Iterable<Perfil> findPerfilLikeName(@Param("nombre") String nombre);
    
    @Modifying
    @Query("UPDATE Perfil p set p.nombre = :nombre, p.img = :img, p.imgBanner = :banner WHERE p.idPerfil = :idPerfil") 
    Perfil update(@Param("idPerfil") Integer idPerfil,@Param("nombre") String nombre,@Param("img") String img,@Param("banner") String banner);
}