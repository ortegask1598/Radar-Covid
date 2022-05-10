
package es.upm.dit.isst.radarcovid.radarwebapp.repository;

import es.upm.dit.isst.radarcovid.radarwebapp.model.Contacto_Estrecho;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
    public interface ContactoRepository extends CrudRepository<Contacto_Estrecho, Long> {
        //@Query("Seleccionar contacto from Contacto_Estrecho u donde contacto_estrecho.id = :id")
        public Contacto_Estrecho getContactoById(@Param("id") Long id);
    
    }
