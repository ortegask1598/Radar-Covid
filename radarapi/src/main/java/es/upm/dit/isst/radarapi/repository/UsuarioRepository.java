package es.upm.dit.isst.radarapi.repository;
import java.util.*;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.radarapi.model.Usuario;

public interface UsuarioRepository extends CrudRepository <Usuario, String> {

    List<Usuario> findByEmail(String email);

} 