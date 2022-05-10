package es.upm.dit.isst.radarcovid.radarwebapp.repository;

import es.upm.dit.isst.radarcovid.radarwebapp.model.Usuario;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Object findById(String id);

    void deleteById(String id);

    List<Usuario> findAll();

}