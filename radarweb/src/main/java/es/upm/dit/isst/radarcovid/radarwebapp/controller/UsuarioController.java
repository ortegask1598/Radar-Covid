package es.upm.dit.isst.radar.radarwebapp.controller;

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

import es.upm.dit.isst.radar.radarwebapp.model.Usuario;



@Controller

public class UsuarioController {

        public final String USERMANAGER_STRING= "http://localhost:8083/usuarios/";

        public static final String VISTA_LISTA = "lista";

        public static final String VISTA_FORMULARIO = "formulario";

        private RestTemplate restTemplate = new RestTemplate();

        @GetMapping("/")

        public String inicio() {

                return "redirect:/" + VISTA_LISTA;

        }


        @GetMapping("/login")

        public String login() {

                return "redirect:/" + VISTA_LISTA;

        }


       @GetMapping("/lista")

        public String lista(Model model, Principal principal) {

                List<Usuario> lista = new ArrayList<Usuario>();

                if (principal == null || principal.getName().equals(""))

                        lista = Arrays.asList(restTemplate.getForEntity(USERMANAGER_STRING,

                                           Usuario[].class).getBody());

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

                model.addAttribute("usuarios", lista);

                return VISTA_LISTA;

        }

        @GetMapping("/crear")

        public String crear(Map<String, Object> model) {

                Usuario Usuario = new Usuario();

                model.put("Usuario", Usuario);

                model.put("accion", "guardar");

                return VISTA_FORMULARIO;

        }

        @PostMapping("/guardar")

        public String guardar(@Validated Usuario Usuario, BindingResult result) {

                if (result.hasErrors()) {

                        return VISTA_FORMULARIO;

                }

                try { restTemplate.postForObject(USERMANAGER_STRING, Usuario, Usuario.class);

                } catch(Exception e) {}

                return "redirect:" + VISTA_LISTA;

        }


        @GetMapping("/editar/{id}")

        public String editar(@PathVariable(value = "id") String id,

                   Map<String, Object> model, Principal principal) {

                if (principal == null || ! principal.getName().equals(id))

                        return "redirect:/" + VISTA_LISTA;

                        Usuario usuario = null;

                try { usuario = restTemplate.getForObject(USERMANAGER_STRING + id, Usuario.class);

                } catch (HttpClientErrorException.NotFound ex) {}

                model.put("Usuario", usuario);

                model.put("accion", "../actualizar"); 

                return usuario != null ? VISTA_FORMULARIO : "redirect:/" + VISTA_LISTA;

        }

        @PostMapping("/actualizar")

        public String actualizar(@Validated Usuario usuario, BindingResult result) {

                if (result.hasErrors()) {

                        return VISTA_FORMULARIO;

                }

                try { restTemplate.put(USERMANAGER_STRING + usuario.getId(),

                    usuario, Usuario.class);

                } catch(Exception e) {}

                return "redirect:" + VISTA_LISTA;

        }
    @GetMapping("/eliminar/{id}")

    public String eliminar(@PathVariable(value = "id") String id) {

        restTemplate.delete(USERMANAGER_STRING+ id);

        return "redirect:/" + VISTA_LISTA;

    }

}