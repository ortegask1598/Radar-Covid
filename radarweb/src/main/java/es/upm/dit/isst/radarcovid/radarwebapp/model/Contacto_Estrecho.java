package es.upm.dit.isst.radarcovid.radarwebapp.model;
import java.util.Date;
import javax.validation.constraints.NotEmpty;


public class Contacto_Estrecho {

    private String id;
    @NotEmpty
    private String id_usu1;
    @NotEmpty
    private String id_usu2;
    @NotEmpty
    private Long duracion;
    @NotEmpty
    private Date caducidad;
    

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