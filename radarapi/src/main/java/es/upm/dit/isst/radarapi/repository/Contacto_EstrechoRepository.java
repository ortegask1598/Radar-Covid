package es.upm.dit.isst.radarapi.repository;
import java.util.*;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.radarapi.model.Contacto_Estrecho;

public interface Contacto_EstrechoRepository extends CrudRepository <Contacto_Estrecho, String> {

    List<Contacto_Estrecho> findByDuracion(String duracion);

} 