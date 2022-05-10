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

import es.upm.dit.isst.radarcovid.radarwebapp.model.Usuario;



@Controller

public class UsuarioController {

        public final String USERMANAGER_STRING= "http://localhost:8083/usuarios/";

        public static final String VISTA_INICIO = "main";

        public static final String VISTA_LOGGIN = "iniciosesion";

        public static final String VISTA_ALL_USERS = "listausuarios";

        public static final String VISTA_FORMULARIO = "registro";

        public static final String VISTA_NOTIFICAR_POSITIVO = "Notificar-positivo";


        private RestTemplate restTemplate = new RestTemplate();

        @GetMapping("/")

        public String inicio( Map<String, Object> model) {

                Usuario Usuario = new Usuario();

                model.put("Usuario", Usuario);

                model.put("accion", "/comprobar");


                return VISTA_LOGGIN;

        }


        @GetMapping("/inicio/{id}")

        public String login(@PathVariable(value = "id") String id,

        Map<String, Object> model) {
/*                 List<Usuario> usuarios = new ArrayList<Usuario>();
                Usuario Usuario = new Usuario(); */

               Usuario Usuario = restTemplate.getForObject(USERMANAGER_STRING + id, Usuario.class);

                model.put("Usuario", Usuario);

                model.put("accion", "../notificar/" + id);

                return VISTA_INICIO;

        }  


       @GetMapping("/lista_usuarios")//DONE EXCEPT ALLOWS

        public String lista(Model model, Principal principal) {

                List<Usuario> lista = new ArrayList<Usuario>();
/* 
                if (principal == null || principal.getName().equals("")) */

                        lista = Arrays.asList(restTemplate.getForEntity(USERMANAGER_STRING,

                                           Usuario[].class).getBody());
                
                model.addAttribute("usuarios", lista);
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


                return VISTA_ALL_USERS;

        }

        @GetMapping("/crear")

        public String crear(Map<String, Object> model) {

                Usuario Usuario = new Usuario();

                model.put("Usuario", Usuario);

                model.put("accion", "guardar");

                return VISTA_FORMULARIO;

        }

        @PostMapping("/guardar")

        public String guardar(Usuario Usuario, BindingResult result) {

                if (result.hasErrors()) {

                        return VISTA_FORMULARIO;

                }

                try { restTemplate.postForObject(USERMANAGER_STRING, Usuario, Usuario.class);
                        
                } catch(Exception e) {}

                return  "redirect:/inicio/" + Usuario.getEmail();
        }


        @PostMapping("/notificar/{id}")

        public String notificar(@PathVariable(value = "id") String id, Usuario usuario, BindingResult result){

                 if (result.hasErrors()) {

                        return "redirect:/inicio" ;

                }

                Usuario u = restTemplate.getForObject(USERMANAGER_STRING + id, Usuario.class);

                u.setPositivo(usuario.getPositivo());

                try { restTemplate.put(USERMANAGER_STRING + id, u, Usuario.class);
                        
                } catch(Exception e) {} 

                return "redirect:/inicio/" + id ;
        }



        @PostMapping("/actualizar")

        public String actualizar(@Validated Usuario usuario, BindingResult result) {

                if (result.hasErrors()) {

                        return VISTA_FORMULARIO;

                }

                try { restTemplate.put(USERMANAGER_STRING + usuario.getId(),

                    usuario, Usuario.class);

                } catch(Exception e) {}

                return "redirect:" + VISTA_INICIO;

        }
    @GetMapping("/eliminar/{id}")

    public String eliminar(@PathVariable(value = "id") String id) {

        restTemplate.delete(USERMANAGER_STRING+ id);

        return "redirect:/" + VISTA_INICIO;

    }

    @PostMapping("/comprobar")

    public String comprobar(Usuario Usuario, BindingResult result) {

        if (result.hasErrors()) {

                return VISTA_LOGGIN;

        }

        return  "redirect:/inicio/" + Usuario.getEmail();


}

}