package es.upm.dit.isst.radarcovid.radarwebapp.controllerAPI;

import java.net.*;
import java.util.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import es.upm.dit.isst.radarcovid.radarwebapp.model.Contacto_Estrecho;
import es.upm.dit.isst.radarcovid.radarwebapp.repository.*;



@RestController

public class Contacto_EstrechoController_API {

    private final ContactoRepository contacto_estrechoRepository;

    public static final Logger log = LoggerFactory.getLogger(Contacto_EstrechoController_API.class);

    public Contacto_EstrechoController_API(ContactoRepository t) {

        this.contacto_estrechoRepository = t;

    }

    @GetMapping("/contactos")
    List<Contacto_Estrecho> readAll() {
        return (List<Contacto_Estrecho>) contacto_estrechoRepository.findAll();

    }

    @PostMapping("/contactos")
    ResponseEntity<Contacto_Estrecho> create(@RequestBody Contacto_Estrecho newContacto) throws URISyntaxException {
        Contacto_Estrecho result = contacto_estrechoRepository.save(newContacto);
        return ResponseEntity.created(new URI("/contactos/" + result.getId())).body(result);
    }

    @GetMapping("/contactos/{id}")
    ResponseEntity<Contacto_Estrecho> read(@PathVariable Long id) {
        return contacto_estrechoRepository.findById(id).map(contacto -> ResponseEntity.ok().body(contacto)).orElse(new ResponseEntity<Contacto_Estrecho>(HttpStatus.NOT_FOUND));
    }

    
    @DeleteMapping("/contactos/{id}")
    ResponseEntity<Contacto_Estrecho> delete(@PathVariable long id) {
        contacto_estrechoRepository.deleteById(id);
      return ResponseEntity.ok().body(null);
    } 


     

}