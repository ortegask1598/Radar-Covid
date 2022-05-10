package es.upm.dit.isst.radarcovid.radarwebapp.controllerAPI;

import java.net.*;
import java.util.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import es.upm.dit.isst.radarcovid.radarwebapp.model.Usuario;
import es.upm.dit.isst.radarcovid.radarwebapp.repository.*;



@RestController

public class UsuarioController_API {

    private final UsuarioRepository usuarioRepository;

    public static final Logger log = LoggerFactory.getLogger(UsuarioController_API.class);

    public UsuarioController_API(UsuarioRepository t) {

        this.usuarioRepository = t;

    }

    @GetMapping("/usuarios")
    List<Usuario> readAll() {
        return (List<Usuario>) usuarioRepository.findAll();

    }

    @PostMapping("/usuarios")
    ResponseEntity<Usuario> create(@RequestBody Usuario newUsuario) throws URISyntaxException {
        Usuario result = usuarioRepository.save(newUsuario);
        return ResponseEntity.created(new URI("/usuarios/" + result.getId())).body(result);
    }

    @GetMapping("/usuarios/{id}")
    ResponseEntity<Usuario> read(@PathVariable Long id) {
        return usuarioRepository.findById(id).map(usuario -> ResponseEntity.ok().body(usuario)).orElse(new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/usuarios/{id}")
    ResponseEntity<Usuario> update(@RequestBody Usuario newUsuario, @PathVariable Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setId(usuario.getId());
            usuario.setEmail(usuario.getEmail());
            usuario.setPassword(usuario.getPassword());
            usuario.setPositivo(newUsuario.getPositivo());
            usuario.setContactos(usuario.getContactos());
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().body(usuario);
        }).orElse(new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND));
    }
    
    @DeleteMapping("/usuarios/{id}")
    ResponseEntity<Usuario> delete(@PathVariable Long id) {
      usuarioRepository.deleteById(id);
      return ResponseEntity.ok().body(null); 
    } 

/*     @GetMapping("usuarios/{id}/contactos")
        ResponseEntity<Usuario> read(@PathVariable String id) {
            return usuarioRepository.findById(contactos).map(usuario ->{
            return ResponseEntity.ok().body(usuario.contactos) ;
             } ).orElse(new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND));  
       }
        */
     

}