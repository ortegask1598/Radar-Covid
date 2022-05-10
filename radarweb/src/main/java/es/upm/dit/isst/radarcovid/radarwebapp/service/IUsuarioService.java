package es.upm.dit.isst.radarcovid.radarwebapp.service;

import es.upm.dit.isst.radarcovid.radarwebapp.model.Usuario;
import es.upm.dit.isst.radarcovid.radarwebapp.model.Contacto_Estrecho;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
	List<Usuario> findAll();
	Usuario findOne(String id);
	Usuario createUsuario();
	void saveUsuario(Usuario c);
	void deleteUsuario(String id);
	void updateUsuario(String id, Usuario newUsuario);
}
