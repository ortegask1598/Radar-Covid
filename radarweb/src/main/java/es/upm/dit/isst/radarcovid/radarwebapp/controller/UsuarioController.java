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
import org.springframework.web.bind.annotation.ModelAttribute;
import es.upm.dit.isst.radarcovid.radarwebapp.model.Usuario;
import es.upm.dit.isst.radarcovid.radarwebapp.model.Contacto_Estrecho;
import es.upm.dit.isst.radarcovid.radarwebapp.repository.UsuarioRepository;



@Controller

public class UsuarioController {

        public final String USERMANAGER_STRING= "http://localhost:8083/usuarios/";

        public final String CONTACT_MANAGER_STRING= "http://localhost:8083/contactos/";

        public static final String VISTA_INICIO = "main";

        public static final String VISTA_LOGGIN = "iniciosesion";

        public static final String VISTA_ALL_USERS = "listausuarios";

        public static final String VISTA_FORMULARIO = "registro";

        public static final String VISTA_ADMIN = "admin";

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
/*                 if(id.contains("@alumnos.upm.es")){

                        model.put("Usuario", Usuario);

                        model.put("accion", "../notificar/" + id);
        
                        return VISTA_INICIO;
                }else  */
                if (id.contains("@admin.com")){

                        return "admin";
                } else{

                model.put("Usuario", Usuario);

                model.put("accion", "../notificar/" + id);

                return VISTA_INICIO;
                }

        }  


        @GetMapping("/listausuarios")//DONE EXCEPT ALLOWS

        public String lista(Model model, Principal principal) {

                List<Usuario> lista = new ArrayList<Usuario>();
                        lista = Arrays.asList(restTemplate.getForEntity(USERMANAGER_STRING,

                                           Usuario[].class).getBody());
                
                model.addAttribute("usuarios", lista);


                return VISTA_ALL_USERS;

        }

/*         @GetMapping("/listausuarios")//DONE EXCEPT ALLOWS

        public String lista(Model model, Principal principal, @RequestParam(value="id") String id) {

                List<Usuario> lista = new ArrayList<Usuario>();

                        try { Usuario usuario = restTemplate.getForObject(USERMANAGER_STRING

                                    + id, Usuario.class);

                          if (usuario != null)

                                lista.add(usuario);

                        } catch (Exception e) {}

                

                model.addAttribute("usuarios", lista);

                return VISTA_ALL_USERS;

        }  */



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

/*     @RequestMapping(value = "/comprobar", method = RequestMethod.POST)
    public String comprobar(Model model, String email, Usuario Usuario, BindingResult result){
        //Usuario usuario = new Usuario();
        @ModelAttribute("usuario") Usuario usuario;
        model.addAttribute("email", email);
        if (result.hasErrors()) {

                return VISTA_FORMULARIO;

        }
        model.addAttribute("email", usuario.getEmail());

        if (model.email = ""){
                
        }
    } */
 

     @PostMapping("/comprobar")

    public String comprobar(Usuario Usuario, BindingResult result) {

        if (result.hasErrors()) {

                return VISTA_FORMULARIO;

        }

        return  "redirect:/inicio/" + Usuario.getEmail();

        }



}