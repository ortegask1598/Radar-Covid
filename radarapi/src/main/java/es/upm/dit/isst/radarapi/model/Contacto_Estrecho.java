package es.upm.dit.isst.radarapi.model;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Contacto_Estrecho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private String id;


    private String id_usu1;
    private String id_usu2;
    private Long duracion;
    private Date caducidad;


    public Contacto_Estrecho(){
        this.id=null;
        this.id_usu1=null;
        this.id_usu2=null;
        this.duracion=null;
        this.caducidad=null;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId_usu1(){
        return id_usu1;
    }

    public void setId_usu1(String id_usu1){
        this.id_usu1 = id_usu1;
    }

    public String getId_usu2(){
        return id_usu2;
    }

    public void setId_usu2(String id_usu2){
        this.id_usu2 = id_usu2;
    }

    public Long getDuracion(){
        return duracion;
    }

    public void setDuracion(Long duracion){
        this.duracion = duracion;
    }

    public Date getCaducidad(){
        return caducidad;
    }

    public void setCaducidad(Date caducidad){
        this.caducidad = caducidad;
    }

}