package net.azarquiel.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.azarquiel.model.Asignatura;
import net.azarquiel.model.AsignaturaRepository;
import net.azarquiel.model.Archivo;
import net.azarquiel.model.FileRepository;
import net.azarquiel.model.Grado;
import net.azarquiel.model.GradoRepository;
import net.azarquiel.model.Perfil;
import net.azarquiel.model.PerfilRepository;
import net.azarquiel.model.Publicacion;
import net.azarquiel.model.PublicacionRepository;
import net.azarquiel.model.RelacionRepository;
import net.azarquiel.model.RelacionSeguidos;
import net.azarquiel.model.Usuario;
import net.azarquiel.model.UsuarioRepository;


/*al poner RestController en vez de Controller,
  ya no tenemos que poner la anotacion ResponseBody*/
@RestController
@CrossOrigin
@EnableAutoConfiguration
public class Controller {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PerfilRepository perfilRepository;
    @Autowired
    GradoRepository gradoRepository;
    @Autowired
    AsignaturaRepository asignaturaRepository;
    @Autowired
    PublicacionRepository publicacionRepository;
    @Autowired
    FileRepository fileRepository;
    @Autowired
    RelacionRepository relacionRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    // Welcome de nuestra api por ejemplo podríamos poner aquí
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public String get() {
		String cadena = "<h1 style='text-align: center; background-color: #0000c0; color: #C0C0FF;'>DotShare Api with SpringBoot - Mario Dominguez</h1>";
		cadena += "<table border='1' style='width: 40%;margin: 0 auto; background-color: #C0C0FF; color:#0000c0;'>";
		cadena += "<tr style='background-color: #0000c0; color: #C0C0FF;'><th>Method</th><th>Url</th><th>Description</th></tr>";
		cadena += "<tr><td>GET </td><td>/usuarios</td><td>Lista de usuarios</td></tr>";
		cadena += "<tr><td>POST </td><td>/usuario</td><td>Usuario</td></tr>";
		cadena += "<tr><td>POST </td><td>/publicacion</td><td>Insertar una publicación</td></tr>";
		cadena += "<tr><td>GET </td><td>/publicaciones/{idPerfil}</td><td>Obtener publicaciones por ID de perfil</td></tr>";
		cadena += "<tr><td>GET </td><td>/publicaciones/perfil/{idAsignatura}/{idPerfil}</td><td>Obtener publicaciones por ID de asignatura y perfil</td></tr>";
		cadena += "<tr><td>GET </td><td>/publicaciones/borrar/{idPublicacion}</td><td>Eliminar publicación por ID de publicación</td></tr>";
		cadena += "<tr><td>POST </td><td>/perfil</td><td>Insertar un perfil</td></tr>";
		cadena += "<tr><td>GET </td><td>/perfilbyid/{id}</td><td>Obtener perfil por ID</td></tr>";
		cadena += "<tr><td>GET </td><td>/perfil/{nombre}</td><td>Obtener perfil por nombre</td></tr>";
		cadena += "<tr><td>GET </td><td>/perfillike/{nombre}</td><td>Obtener perfiles que coinciden con el nombre</td></tr>";
		cadena += "<tr><td>POST </td><td>/perfilupdate/{id}</td><td>Actualizar perfil por ID</td></tr>";
		cadena += "<tr><td>GET </td><td>/perfil/{perfilId}/seguidos</td><td>Obtener perfiles seguidos por ID de perfil</td></tr>";
		cadena += "<tr><td>GET </td><td>/perfil/{perfilId}/seguidores</td><td>Obtener seguidores por ID de perfil</td></tr>";
		cadena += "<tr><td>GET </td><td>/perfil/{seguidorId}/sigue/{seguidoId}</td><td>Verificar si un perfil sigue a otro</td></tr>";
		cadena += "<tr><td>POST </td><td>/perfil/{perfilId}/seguir/{seguidoId}</td><td>Seguir un perfil</td></tr>";
		cadena += "<tr><td>GET </td><td>/borrar/seguidos/{idPerfil}/{idSeguido}</td><td>Dejar de seguir un perfil (seguidos)</td></tr>";
		cadena += "<tr><td>GET </td><td>/borrar/seguidores/{idPerfil}/{idSeguido}</td><td>Dejar de seguir un perfil (seguidores)</td></tr>";
		cadena += "</table>";
		return cadena;
    }

    ////                 ////
    ////    USUARIO      ////
    ////                 ////

    // Get lista con todos los usuarios
    @RequestMapping(value = "usuarios", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getUsuarios() {
   	 try {
   		 Iterable<Usuario> usuarios = usuarioRepository.findAll();
   		 return new ResponseEntity<>(usuarios, HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }

    // Insert un usuario
    @RequestMapping(value = "usuario", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> postUsuario(@RequestBody Usuario usuario) {
   	 try {
   		 Usuario lineaResponse = usuarioRepository.save(usuario);

   		 return new ResponseEntity<>(lineaResponse, HttpStatus.CREATED);

   	 } catch (Exception e) {
   		 return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
   	 }
    }

    ////                 ////
    ////  PUBLICACION    ////
    ////                 ////


    // Insert una publicacion
    @RequestMapping(value = "publicacion", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> postPublicacion(@RequestBody Publicacion publicacion) {
   	 try {
   		 System.out.println(publicacion);
   		 Publicacion lineaResponse = publicacionRepository.save(publicacion);

   		 return new ResponseEntity<>(lineaResponse, HttpStatus.CREATED);

   	 } catch (Exception e) {
   		 return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
   	 }
    }


    // Get una linea indicando el numero de linea en la url
    @RequestMapping(value = "publicaciones/{idPerfil}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getPublicacionesByIdPerfil(@PathVariable(value = "idPerfil") Integer idPerfil) {
   	 try {
   		 Iterable<Publicacion> lineaResponse = publicacionRepository.findPublicacionByIdPerfil(idPerfil);
   		 return new ResponseEntity<>(lineaResponse, HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }

    // Get una linea indicando el numero de linea en la url
    @RequestMapping(value = "publicaciones/perfil/{idAsignatura}/{idPerfil}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getPublicacionesByIdAsignatura(@PathVariable(value = "idAsignatura") Integer idAsignatura,@PathVariable(value = "idPerfil") Integer idPerfil) {
   	 try {
   		 Iterable<Publicacion> lineaResponse = publicacionRepository.findPublicacionByIdAsignatura(idAsignatura,idPerfil);
   		 return new ResponseEntity<>(lineaResponse, HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }

    // Get una linea indicando el numero de linea en la url
    @RequestMapping(value = "publicaciones/borrar/{idPublicacion}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> delPublicacionesByIdPublicacion(@PathVariable(value = "idPublicacion") Integer idPublicacion) {
   	 try {
   		 publicacionRepository.deleteById(idPublicacion);
   		 return new ResponseEntity<>("Deleted: " + idPublicacion, HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }

    ////                 ////
    ////     PERFIL      ////
    ////                 ////

    // Insert una publicacion
    @RequestMapping(value = "perfil", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> postPublicacion(@RequestBody Perfil perfil) {
   	 try {
   		 perfil.setNpublicaciones(0);
   		 perfil.setNseguidores(0);
   		 perfil.setNseguidos(0);
   		 Perfil lineaResponse = perfilRepository.save(perfil);

   		 return new ResponseEntity<>(lineaResponse, HttpStatus.CREATED);

   	 } catch (Exception e) {
   		 return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
   	 }
    }

    @RequestMapping(value = "perfilbyid/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getPerfilById(@PathVariable(value = "id") Integer id) {
   	 try {
   		 Usuario lineaResponse = usuarioRepository.getNickByPerfilId(id);

   		 return new ResponseEntity<>(lineaResponse, HttpStatus.CREATED);

   	 } catch (Exception e) {
   		 return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
   	 }
    }

    // Get una linea indicando el numero de linea en la url
    @RequestMapping(value = "perfil/{nombre}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getPerfilByName(@PathVariable(value = "nombre") String nombre) {
   	 try {
   		 Perfil lineaResponse = perfilRepository.findPerfilByName(nombre);
   		 return new ResponseEntity<>(lineaResponse, HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }

    // Get una linea indicando el numero de linea en la url
    @RequestMapping(value = "perfillike/{nombre}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getPerfilLike(@PathVariable(value = "nombre") String nombre) {
   	 try {
   		 Iterable<Perfil> lineaResponse = perfilRepository.findPerfilLikeName(nombre);
   		 return new ResponseEntity<>(lineaResponse, HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }


    @RequestMapping(value = "perfilupdate/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updatePerfil(@PathVariable(value = "id") Integer id, @RequestBody Perfil perfil) {
   	 try {
   		 Perfil lineaResponse = null;
   		 Optional<Perfil> optionalPerfil = perfilRepository.findById(id);
         if (optionalPerfil.isPresent()) {
        	 Perfil perfilC = optionalPerfil.get();
        	 perfilC.setNombre(perfil.getNombre());
        	 perfilC.setImg(perfil.getImg());
        	 perfilC.setImgBanner(perfil.getImgBanner());
        	 lineaResponse = perfilRepository.save(perfilC);

         }
   		 return new ResponseEntity<>(lineaResponse, HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }

    @RequestMapping(value = "/perfil/{perfilId}/seguidos", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> seguidosPerfil(@PathVariable Integer perfilId) {
   	 try {

         Iterable<Perfil> response = relacionRepository.buscaSeguidos(perfilId);

   		 return new ResponseEntity<>(response, HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }

    @RequestMapping(value = "/perfil/{perfilId}/seguidores", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> seguidoresPerfil(@PathVariable Integer perfilId) {
   	 try {

         Iterable<Perfil> response = relacionRepository.buscaSeguidores(perfilId);

   		 return new ResponseEntity<>(response, HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }

    @RequestMapping(value = "/perfil/{seguidorId}/sigue/{seguidoId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> siguenAPerfil(@PathVariable Integer seguidorId,@PathVariable Integer seguidoId) {
   	 try {

         Iterable<Perfil> response = relacionRepository.buscaSeguidor(seguidorId,seguidoId);

   		 return new ResponseEntity<>(response, HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }

    @RequestMapping(value = "/perfil/{perfilId}/seguir/{seguidoId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> seguirPerfil(@PathVariable Integer perfilId, @PathVariable Integer seguidoId) {
   	 try {
   		System.out.println(perfilId + "/"+ seguidoId);
         Optional<Perfil> perfilOpt = perfilRepository.findById(perfilId);
         Optional<Perfil> seguidoOpt = perfilRepository.findById(seguidoId);
         if (perfilOpt.isPresent() && seguidoOpt.isPresent()) {
             Perfil perfil = perfilOpt.get();
             Perfil seguido = seguidoOpt.get();
             RelacionSeguidos rs = new RelacionSeguidos(perfil,seguido);
             RelacionSeguidos response = relacionRepository.save(rs);
         }
   		 return new ResponseEntity<>("ok", HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }

    @RequestMapping(value = "/borrar/seguidos/{idPerfil}/{idSeguido}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> delPerfilSiguePerfilSeguidos(@PathVariable(value = "idPerfil") Integer idPerfil,@PathVariable(value = "idSeguido") Integer idSeguido) {
   	 try {
   		 String sql = "DELETE FROM perfil_sigue_perfil where id_perfil=? and id_seguido=?;";
   		 jdbcTemplate.update(sql, idPerfil,idSeguido);
   		 return new ResponseEntity<>("OK", HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }

    @RequestMapping(value = "/borrar/seguidores/{idPerfil}/{idSeguido}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> delPerfilSiguePerfilSeguidores(@PathVariable(value = "idPerfil") Integer idPerfil,@PathVariable(value = "idSeguido") Integer idSeguido) {
   	 try {
   		 String sql = "DELETE FROM perfil_sigue_perfil where id_perfil=? and id_seguido=?;";
   		 jdbcTemplate.update(sql, idSeguido,idPerfil);
   		 return new ResponseEntity<>("OK", HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }


    // Get una linea indicando el numero de linea en la url
    @RequestMapping(value = "asignaturas/{idPerfil}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAsignaturasByName(@PathVariable(value = "idPerfil") Integer idPerfil) {
   	 try {
   		 Iterable<Asignatura> lineaResponse = asignaturaRepository.findAsignaturasByNombre(idPerfil);
   		 return new ResponseEntity<>(lineaResponse, HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }
    }

    @RequestMapping(value = "grados", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getPerfiles() {
   	 try {
   		 Iterable<Grado> lineaResponse = gradoRepository.findAll();
   		 return new ResponseEntity<>(lineaResponse, HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }

    @RequestMapping(value = "archivos", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getFile() {
   	 try {

   		 Iterable<Archivo> lineaResponse = fileRepository.findAll();

   		 return new ResponseEntity<>(lineaResponse, HttpStatus.CREATED);

   	 } catch (Exception e) {
   		 return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
   	 }
    }

    // Insert una publicacion
    @RequestMapping(value = "archivo", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> insertarFile(@RequestBody Archivo file) {
   	 try {
   		 System.out.println(file);
   		 Archivo lineaResponse = fileRepository.save(file);

   		 return new ResponseEntity<>(lineaResponse, HttpStatus.CREATED);

   	 } catch (Exception e) {
   		 return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
   	 }
    }

    @RequestMapping(value = "archivo/borrar/{idPublicacion}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> delArchivosByIdPublicacion(@PathVariable(value = "idPublicacion") Integer idPublicacion) {
   	 try {
   		 System.out.println(idPublicacion);
   		 String sql = "DELETE FROM archivo WHERE id_publicacion = ?";
   		 jdbcTemplate.update(sql, idPublicacion);
   		 return new ResponseEntity<>("OK", HttpStatus.OK);

   	 } catch (Exception ex) {
   		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   	 }

    }


}
