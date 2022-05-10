package es.upm.dit.isst.radarcovid.radarwebapp.service;


/* import java.util.ArrayList; */
import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.upm.dit.isst.radarcovid.radarwebapp.model.Usuario;
import es.upm.dit.isst.radarcovid.radarwebapp.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

	private UsuarioRepository repository;

	@Override
	public List<Usuario> findAll() {
		var usuarios = (List<Usuario>) repository.findAll();
		return usuarios;
	}
	public Usuario findOne(String id) {
		var usuario = (Usuario) repository.findById(id);
		return usuario;
	}
	public Usuario createUsuario(){
		Usuario usuario = new Usuario();
		return usuario;
	}
/* 	public void saveUsuario(Usuario c){
		repository.save(c);
	}
	public void deleteUsuario(String id){
		repository.deleteById(id);
	}
	public void updateUsuario(String id, Usuario newUsuario){
		newUsuario.setId(id);
		repository.save(newUsuario);
	}
	@Override
	public Usuario findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteUsuario(String id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateUsuario(String id, Usuario newUsuario) {
		// TODO Auto-generated method stub
		
	} */
/* 	@Override
	public void saveUsuario(Usuario c) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteUsuario(String id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateUsuario(String id, Usuario newUsuario) {
		// TODO Auto-generated method stub
		
	} */
	@Override
	public void saveUsuario(Usuario c) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteUsuario(String id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateUsuario(String id, Usuario newUsuario) {
		// TODO Auto-generated method stub
		
	}
}
