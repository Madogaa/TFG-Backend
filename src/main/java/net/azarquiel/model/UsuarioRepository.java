package net.azarquiel.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

    @Query("SELECT u from Usuario u where nombre = :nombre") 
    Usuario findUsuarioByName(@Param("nombre") String nombre);
    
    @Query("SELECT u FROM Usuario u WHERE u.idUsuario IN (SELECT p.idUsuario from Perfil p where p.idPerfil = :idPerfil)") 
    Usuario getNickByPerfilId(@Param("idPerfil") Integer idPerfil);

}
