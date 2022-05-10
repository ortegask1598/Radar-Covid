package es.upm.dit.isst.radarcovid.radarwebapp.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "contacto_estrecho")
public class Contacto_Estrecho {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Id usuario 1")
    @Column(name = "id_usu1")
    private long id_usu1;
    @NotBlank(message = "Id usuario 2")
    @Column(name = "id_usu2")
    private long id_usu2;
    @NotBlank(message = "Duración contacto")
    @Column(name = "duración")
    private Long duracion;
    @NotBlank(message = "Caducidad contacto")
    @Column(name = "caducidad")
    private Date caducidad;

    public Contacto_Estrecho(){

    }
    
    public Contacto_Estrecho(Long id, long id_usu1, long id_usu2, long duracion, Date caducidad){
        this.id=id;
        this.id_usu1=id_usu1;
        this.id_usu2=id_usu2;
        this.duracion=duracion;
        this.caducidad=caducidad;
    }

    public long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public long getId_usu1(){
        return id_usu1;
    }

    public void setId_usu1(Long id_usu1){
        this.id_usu1 = id_usu1;
    }

    public long getId_usu2(){
        return id_usu2;
    }

    public void setId_usu2(Long id_usu2){
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