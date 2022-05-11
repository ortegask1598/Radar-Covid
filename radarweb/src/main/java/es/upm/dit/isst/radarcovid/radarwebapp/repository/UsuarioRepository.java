package es.upm.dit.isst.radarcovid.radarwebapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.upm.dit.isst.radarcovid.radarwebapp.model.Usuario;




@Repository
public interface UsuarioRepository extends CrudRepository <Usuario, String> { 
    Optional<Usuario> findById(String id);
    List<Usuario> findByEmail(String email); 
}