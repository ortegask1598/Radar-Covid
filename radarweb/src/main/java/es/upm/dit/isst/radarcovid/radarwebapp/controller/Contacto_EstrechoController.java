package es.upm.dit.isst.radarcovid.radarwebapp.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;
import org.springframework.web.multipart.MultipartFile;

import es.upm.dit.isst.radarcovid.radarwebapp.model.Contacto_Estrecho;


@Controller

public class Contacto_EstrechoController {

        public final String CONTACT_MANAGER_STRING= "http://localhost:8083/contactos/";

        public static final String VISTA_INICIO = "inicio";

        public static final String VISTA_LOGGIN = "registro";

        public static final String VISTA_ALL_CONTACTS = "lista_contactos";

        public static final String VISTA_FORMULARIO_CONTACTOS = "registrocontacto";

        private RestTemplate restTemplate = new RestTemplate();



        @GetMapping("/lista_contactos")//DONE EXCEPT ALLOWS

        public String lista(Model model, Principal principal) {

                List<Contacto_Estrecho> lista = new ArrayList<Contacto_Estrecho>();

                if (principal == null || principal.getName().equals(""))

                        lista = Arrays.asList(restTemplate.getForEntity(CONTACT_MANAGER_STRING,

                        Contacto_Estrecho[].class).getBody());

                /* else if (principal.getName().contains("@upm.es"))

                        lista = Arrays.asList(restTemplate.getForEntity(USERMANAGER_STRING

                                    + "profesor/" + principal.getName()

                                              ,Usuario[].class).getBody());

                else if (principal.getName().contains("@alumnos.upm.es")){

                        try { Usuario usuario = restTemplate.getForObject(USERMANAGER_STRING

                                    + principal.getName(), Usuario.class);

                          if (usuario != null)

                                lista.add(usuario);

                        } catch (Exception e) {}

                } */

                model.addAttribute("contactos", lista);

                return VISTA_ALL_CONTACTS;

        }

        @GetMapping("/crear")

        public String crear(Map<String, Object> model) {

                Contacto_Estrecho Contacto = new Contacto_Estrecho();

                model.put("Contacto_Estrecho", Contacto);

                model.put("accion", "guardar");

                return VISTA_FORMULARIO_CONTACTOS;

        }

        @PostMapping("/guardar")

        public String guardar(@Validated Contacto_Estrecho Contacto, BindingResult result) {

                if (result.hasErrors()) {

                        return VISTA_FORMULARIO_CONTACTOS;

                }

                try { restTemplate.postForObject(CONTACT_MANAGER_STRING, Contacto, Contacto_Estrecho.class);

                } catch(Exception e) {}

                return "redirect:" + VISTA_INICIO;

        }

        @GetMapping("/eliminar/{id}")

        public String eliminar(@PathVariable(value = "id") String id) {

                restTemplate.delete(CONTACT_MANAGER_STRING+ id);

                return "redirect:/" + VISTA_ALL_CONTACTS;

         }

       
}