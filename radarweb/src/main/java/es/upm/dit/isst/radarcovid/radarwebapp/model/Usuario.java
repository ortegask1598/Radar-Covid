package es.upm.dit.isst.radarcovid.radarwebapp.model;
//import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Email obligatorio, será el username")
    @Column(name = "username")
    private String email;
    @NotBlank(message = "Contraseña es obligatoria")
    @Column(name = "password")
    private String password;
    @NotBlank(message = "Obligatorio marcar si es ud positivo")
    @Column(name = "positivo")
    private Boolean positivo;
    @NotBlank(message = "Obligatorio marcar si tiene contacto positivo")
    @Column(name = "contacto")
    private Boolean contactos;
  
    public Usuario(){
    }
/*     public Usuario(){
        this.id=null;
        this.email=null;
        this.contraseña=null;
        this.positivo=null;
        this.contactos=null;
    } */
    public Usuario(Long id, String email, String password, Boolean positivo, Boolean contactos){
        this.id=id;
        this.email=email;
        this.password=password;
        this.positivo=positivo;
        this.contactos=contactos;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Boolean getPositivo(){
        return positivo;
    }

    public void setPositivo(Boolean positivo){
        this.positivo = positivo;
    }

    public Boolean getContactos() {
        return contactos;
    }

    public void setContactos(Boolean contactos){
        this.contactos = contactos;
    }

}
