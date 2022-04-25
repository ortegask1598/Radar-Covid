package es.upm.dit.isst.radarapi.model;
import javax.persistence.*;

@Entity   
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private String id;


    private String email;
    private String contraseña;
    private Boolean positivo;
    private Boolean contactos;

    public Usuario(){
        this.id=null;
        this.email=null;
        this.contraseña=null;
        this.positivo=null;
        this.contactos=null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña){
        this.contraseña = contraseña;
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
