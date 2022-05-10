package es.upm.dit.isst.radarcovid.radarwebapp.repository;

import es.upm.dit.isst.radarcovid.radarwebapp.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    //@Query("Seleccionar usuario from Usuario u donde usuario.id = :id")
	public Usuario getUserById(@Param("id") String id);

}