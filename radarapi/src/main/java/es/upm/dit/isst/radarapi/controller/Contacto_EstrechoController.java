package es.upm.dit.isst.radarapi.controller;

import java.net.*;
import java.util.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import es.upm.dit.isst.radarapi.model.Contacto_Estrecho;
import es.upm.dit.isst.radarapi.repository.*;


@RestController

public class Contacto_EstrechoController {

    private final Contacto_EstrechoRepository contacto_estrechoRepository;

    public static final Logger log = LoggerFactory.getLogger(Contacto_EstrechoController.class);

    public Contacto_EstrechoController(Contacto_EstrechoRepository t) {

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
    ResponseEntity<Contacto_Estrecho> read(@PathVariable String id) {
        return contacto_estrechoRepository.findById(id).map(contacto -> ResponseEntity.ok().body(contacto)).orElse(new ResponseEntity<Contacto_Estrecho>(HttpStatus.NOT_FOUND));
    }

    
    @DeleteMapping("/contactos/{id}")
    ResponseEntity<Contacto_Estrecho> delete(@PathVariable String id) {
        contacto_estrechoRepository.deleteById(id);
      return ResponseEntity.ok().body(null); 
    } 


     

}