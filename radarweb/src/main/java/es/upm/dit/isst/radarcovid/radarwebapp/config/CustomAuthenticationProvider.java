package es.upm.dit.isst.radarcovid.radarwebapp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    public final String USERMANAGER_STRING= 
	"http://localhost:8083/usuarios/";
    
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
        String name = authentication.getPrincipal().toString(); //getName();
        if (name.contains("@admin.es")) {
            List<SimpleGrantedAuthority> ga = new ArrayList<SimpleGrantedAuthority>();
            ga.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new UsernamePasswordAuthenticationToken(name, "", ga);
        }
        if (name.contains("@hotmail.com")) {
            List<SimpleGrantedAuthority> ga = new ArrayList<SimpleGrantedAuthority>();
            ga.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(name, "", ga);
        }
        throw new UsernameNotFoundException ("could not login");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}